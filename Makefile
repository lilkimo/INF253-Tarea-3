all:
	javac -d bin Juego.java
	java -cp bin Juego
clean:
	rm bin/*.class
