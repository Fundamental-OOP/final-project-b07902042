
rm -r out/
javac -cp . -sourcepath ./src/hearthclone -d ./out  ./src/hearthclone/*.java ./src/hearthclone/*/*.java ./src/hearthclone/*/*/*.java
java -cp out/ ClientMain $1 $2
