package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import engine.Game;
import game.object.Rocket;

@SuppressWarnings("unchecked")
public class SimpleServer extends Thread{
	public Game game;
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	InputStream dataInPuts;
	OutputStream dataOutPuts;
	ServerSocket serverSocket;
	public int port;
	public Socket socket; 
	public Object serverReadObject;
	public boolean newRocketAdd = true;
	public String nameOfServer;
	public String nameOfClientInServer;
	public int numberOfClients;
	public int numberOfLevels;

	public SimpleServer(int port, String nameOfServer) {
		this.port = port;
		this.nameOfServer = nameOfServer;
		game = new Game(1200, 700, "rectangle","1","3","1");
	}

	@Override
	public void run() {
		try {
			System.out.println("Server started on port: "+port);
			serverSocket = new ServerSocket(port);
			socket= serverSocket.accept();
			dataInPuts=socket.getInputStream();
			dataOutPuts=socket.getOutputStream();	
			objectOutputStream = new ObjectOutputStream(dataOutPuts);
			objectOutputStream.flush();
			objectInputStream = new ObjectInputStream(dataInPuts);
			while(true) {
				if(socket.isConnected() == true) {
					if(socket.isConnected() == true && newRocketAdd == true) {
						game.rockets.add(new Rocket(500 ,500));	
						newRocketAdd= false;
					}
					//writing object
					writeJSONObject();
					//reading object
					readJSONObject();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readJSONObject() {
		try {
			JSONObject object = (JSONObject) readingObject();
			JSONArray locationOfServerRocket =(JSONArray) object.get("RocketLocation");
			JSONArray liveOfHens = (JSONArray) object.get("HensLive");
			int NameReadingcounter=0;
			if(NameReadingcounter ==0) {
				String nameOfClient = (String) object.get("nameOfClient");
				this.nameOfClientInServer = nameOfClient;
				NameReadingcounter++;	
			}
			int cul = game.rectangleHens.cul;
			int row = game.rectangleHens.row;
			int counter =0;
			for (int i=0 ; i < row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					if ((boolean) liveOfHens.get(counter) == false) {
						game.rectangleHens.rectangleHens[i][j].paintCheck = (boolean) liveOfHens.get(counter);
					}
					counter++;
				}
			}
			game.rockets.get(0).setX((double)locationOfServerRocket.get(0));
			game.rockets.get(0).setY((double)locationOfServerRocket.get(1));
			System.out.println(object);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray rocketLocation = new JSONArray();
		JSONArray hensLocation = new JSONArray();
		JSONArray hensLive = new JSONArray();
		JSONArray hensEgg = new JSONArray();
		if(game.getRocket() !=null) {
			double rocketX = game.getRocket().getX();
			double rocketY = game.getRocket().getY();
			rocketLocation.add(rocketX);
			rocketLocation.add(rocketY);
		}
		int cul = game.rectangleHens.cul;
		int row = game.rectangleHens.row;
		for (int i=0 ; i < row ; i++ ) {
			for (int j=0 ; j<cul ; j++) {
				double x = game.rectangleHens.rectangleHens[i][j].getX();
				double y = game.rectangleHens.rectangleHens[i][j].getY();
				double[] recHensLoc = {x , y};
				hensLive.add(game.rectangleHens.rectangleHens[i][j].paintCheck);
				if(game.rectangleHens.rectangleHens[i][j].egg != null) {
					hensEgg.add(game.rectangleHens.rectangleHens[i][j].producedEgg);
				}else {
					hensEgg.add(game.rectangleHens.rectangleHens[i][j].producedEgg);
				}
				hensLocation.add(recHensLoc);
			}
		}
		obj.put("HensLive", hensLive);
		obj.put("nameOfServer", nameOfServer);
		obj.put("NUMBEROFCLIENT" , numberOfClients);
		obj.put("RocketLocation",rocketLocation);
		obj.put("hensLocation", hensLocation);
		obj.put("HensEgg", hensEgg);
		try {
			writingObject(obj);
			System.out.println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized void writingObject(Object obj) throws IOException {
		objectOutputStream.writeObject(obj);
		objectOutputStream.flush();
	}

	public synchronized Object readingObject() throws IOException, ClassNotFoundException {
		serverReadObject =objectInputStream.readObject();
		return serverReadObject;
	}


}
