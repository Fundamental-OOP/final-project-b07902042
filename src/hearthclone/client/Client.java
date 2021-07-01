package hearthclone.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hearthclone.controller.Controller;
import hearthclone.event.ClientEventManager;
import hearthclone.event.Event;
import hearthclone.event.EventClientInitalize;
import hearthclone.event.Message;
import hearthclone.model.GameInfo;
import hearthclone.view.View;


public class Client {

	private String playerName = "";
	private static int playerID = -2;
	private String serverIP;
	private int serverPort;
	private static ObjectOutputStream oos;

	public void start(String IP, int port) {
		serverIP = IP;
		serverPort = port;

		JTextField playerNameField = new JTextField(playerName, 10);
		JPanel playerNamePanel = new JPanel();
		playerNamePanel.add(new JLabel("Type your name here:"));
		playerNamePanel.add(playerNameField);
		JOptionPane.showConfirmDialog(null, playerNamePanel, "Welcome to Hearthclone", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		this.playerName = playerNameField.getText();

		ClientEventManager eventManager = new ClientEventManager();

		System.out.format("IP: %s Port: %s%n", serverIP, serverPort);

		GameInfo gameinfo = new GameInfo(eventManager);
		Controller controller = new Controller(eventManager, gameinfo);
		View view = new View(eventManager, gameinfo, controller);
		System.out.println("MAIN: " + eventManager.getListenerslen());
		this.makeConnection(eventManager);
	}

	public void makeConnection(ClientEventManager eventManager) {
		try {
			Socket serverSocket = new Socket(InetAddress.getByName(serverIP), serverPort);
			oos = new ObjectOutputStream(serverSocket.getOutputStream());

			Thread t = new Thread(new serverListener(serverSocket, eventManager));
			t.start();

			System.out.println("Connected Success to server at " + serverIP);

			sendMessage(new Message(Message.JOIN, playerID));

			Thread.sleep(500);

		} catch (Exception ex) {
			System.out.println("Server no respond.");
		}
	}

	private class serverListener implements Runnable {
		private ObjectInputStream oistream;
		private ClientEventManager eManager;

		private serverListener(Socket socket, ClientEventManager eventManager) {
			try {
				oistream = new ObjectInputStream(socket.getInputStream());
				eManager = eventManager;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void run() {
			Message receiveObj;
			try {
				while ((receiveObj = (Message) oistream.readObject()) != null) {
					
					// =====Do something=====
					switch (receiveObj.getType()) {
						case Message.JOIN:
							playerID = (Integer) receiveObj.getObj();
							System.out.println("My ID is " + playerID);
							eManager.localPost(new EventClientInitalize(playerID));
							break;
						case Message.EVENT:
							eManager.localPost((Event) receiveObj.getObj());
							break;
					}
				}
			} catch (Exception ex) {
				System.out.println("Connection lost");
				ex.printStackTrace();
			}
		}
	}

	public static synchronized void sendMessage(Message message) {
		try {
			System.out.println("Message Sent Success, Type: " + message.getType());
			oos.writeObject(message);
			oos.flush();
		} catch (Exception ex) {
			System.out.println("Message Sent Failed");
			ex.printStackTrace();
		}
	}

	public static int getPlayerID() {
		return playerID;
	}

}