package Network;

import java.io.IOException;

import MainPackage.Start;

public class ServerMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, InterruptedException {
		SimpleServer server = new SimpleServer(9092 , "Amin Computer");
		Start start = new Start(server.game);
		server.start();		
	}
}

