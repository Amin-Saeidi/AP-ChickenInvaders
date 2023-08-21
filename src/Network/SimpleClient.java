package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import engine.Game;
import game.object.Rocket;
import game.objects.Egg;


@SuppressWarnings("unchecked")
public class SimpleClient extends Thread{
	public String IP;
	public int port;
	public Socket socket;
	public Game game;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public boolean newRocketAdd= true;
	public String nameOfServer;
	public String nameOfClient;
	public int numberOfLevels = 1;
	public int numberOfPlayers = 1;

	public SimpleClient(String IP, int port , String nameOfclient) {	
		this.IP = IP;
		this.port = port;
		this.nameOfClient = nameOfclient;
		game = new Game(1200, 700, "rectangle","1","3","1");
		game.dropAddimision = false;
	}

	public void run() {
		try {
			
			System.out.println("Client connected to server at: "+IP+":"+port);
			socket = new Socket(IP, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			while (true) {
				if(socket.isConnected() == true) {
					if(socket.isConnected() == true && newRocketAdd == true) {
						game.rockets.add(new Rocket(500, 500));
						newRocketAdd= false;
					}
					//reading objects
					readJSONObject();
					// writing objects
					writeJSONObject();
				}
			}

		}catch (Exception e) {	
			e.printStackTrace();
		}
	}

	private void readJSONObject() {	
		try {
			JSONObject object = (JSONObject) readingObject();
			System.out.println(object);
			JSONArray locationOfServerRocket =(JSONArray) object.get("RocketLocation");
			JSONArray locationOfHens = (JSONArray) object.get("hensLocation");
			JSONArray liveOfHens = (JSONArray) object.get("HensLive");
			JSONArray eggsOfHens = (JSONArray) object.get("HensEgg");
			nameOfServer = (String) ((Object) object.get("nameOfServer"));
			System.out.println(nameOfServer);
			int cul = game.rectangleHens.cul;
			int row = game.rectangleHens.row;
			int counter =0;
			for (int i=0 ; i < row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					double[] recHensLoc = (double[]) locationOfHens.get(counter);
					game.rectangleHens.rectangleHens[i][j].setX(recHensLoc[0]);
					game.rectangleHens.rectangleHens[i][j].setY(recHensLoc[1]);					
					if ((boolean) liveOfHens.get(counter)==false) {
						game.rectangleHens.rectangleHens[i][j].paintCheck = (boolean) liveOfHens.get(counter);
					}
					if ((boolean) eggsOfHens.get(counter) == true ) {
						game.rectangleHens.rectangleHens[i][j].egg = new Egg(game.rectangleHens.rectangleHens[i][j].getX()+20,
								game.rectangleHens.rectangleHens[i][j].getY()+20,20,20);
					}
					counter++;
				}
			}
			game.rockets.get(0).setX((double)locationOfServerRocket.get(0));
			game.rockets.get(0).setY((double)locationOfServerRocket.get(1));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private void writeJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray rocketLocation = new JSONArray();
		JSONArray hensLive = new JSONArray();
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
				hensLive.add(game.rectangleHens.rectangleHens[i][j].paintCheck);
			}
		}
		obj.put("HensLive", hensLive);
		int counter=0;
		if(counter ==0) {
			obj.put("nameOfClient", nameOfClient);
			counter++;
		}
		obj.put("RocketLocation",rocketLocation);
		try {
			writingObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public synchronized void writingObject(Object obj) throws IOException {
		out.writeObject(obj);
		out.flush();
	}

	public synchronized Object readingObject() throws IOException {
		Object obj;
		try {
			obj = in.readObject();
			return obj;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "ooo";
		}
	}
}