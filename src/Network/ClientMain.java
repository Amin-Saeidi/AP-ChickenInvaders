package Network;

import MainPackage.Start;

public class ClientMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		SimpleClient simpleClient = new SimpleClient("127.0.0.1", 9092,"Client Computer");
		Start start = new Start(simpleClient.game);	
		simpleClient.start();
	}
}

