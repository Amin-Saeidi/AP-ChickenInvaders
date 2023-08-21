package IO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromFile {
	public FileReader reader;
	public Object obj;
	public JSONParser parser;
	public JSONObject data;
	public ArrayList<User> users;
	

	@SuppressWarnings("unused")
	public ReadDataFromFile() {
		users = new ArrayList<User>();
		parser = new JSONParser();
		try {
			reader =new FileReader("gameData.json");
			if (reader.ready()) {
				obj = parser.parse(reader);
				data = (JSONObject) obj;
				System.out.println(data.toString());
				reader.close();
			}
		} catch (IOException e) {
			File f = new File("gameData.json");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> makingArray() {
		if(data != null) {
			System.out.println(data.keySet().size());
			for(Object obj : data.keySet()) {
				Object object = data.get((String) obj);
				String value = (String) object;
				String healths = value.substring(0, 3);
				String numOfBomb = value.substring(3, 6);
				String coin = value.substring(6, 9);
				String numOfLevel = value.substring(9, 12);
				String numOfWave = value.substring(12, 15);
				String scores = value.substring(15);
				int health =Integer.valueOf(healths); 
				int bomb = Integer.valueOf(numOfBomb);
				int coins =Integer.valueOf(coin) ;
				int numberOfLevel =Integer.valueOf(numOfLevel) ;
				int numberOfWave =Integer.valueOf(numOfWave) ;
				int score =Integer.valueOf(scores);
				User user = new User(health, bomb, coins,numberOfLevel , numberOfWave ,score, (String) obj);	
				users.add(user);
			}
		}
		System.out.println(users);
		return users;
	}
}
