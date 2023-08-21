package IO;


import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class WriteDataToFile {

	public User newUser;
	public JSONObject newUserObject;
	public String userName;
	public ArrayList<User> users;

	public WriteDataToFile(User newUser ,String userName) {
		this.newUser = newUser;
		this.userName = userName;
		newUserObject = new JSONObject();
		newUserObject.put(userName, newUser);

		try(FileWriter writer = new FileWriter("gameData.json",true)){
			writer.write(newUserObject.toString());
			writer.close();

		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	public WriteDataToFile(JSONObject object) {
		this.newUserObject = object;
		try(FileWriter writer = new FileWriter("gameData.json")){
			writer.write(newUserObject.toString());
			writer.flush();
			writer.close();

		}catch(Exception ex) {
			ex.printStackTrace();
		}


	}

	public WriteDataToFile(ArrayList<User> users) {
		this.users = users;
		try(FileWriter writer = new FileWriter("gameData.json",true)){
			writer.write(users.toString());
			writer.close();

		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
}



