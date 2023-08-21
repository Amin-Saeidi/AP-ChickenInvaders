package DataBaseWorks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import IO.User;

public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//		String userNamePicked = "Asghar";
		//		Class.forName("com.mysql.jdbc.Driver");
		//		Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:4000/ap_project","root","10289802");
		//		Statement statement = con.createStatement();
		//		ResultSet resultSet = statement.executeQuery("select * from users");
		//		while (resultSet.next()) {
		//			System.out.println(resultSet.getString(3));
		//		}

		//		String selectedUserName = "'Ali'";
		ArrayList<User> usersArray = new ArrayList<User>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> coins = new ArrayList<Integer>();
		ArrayList<Integer> bombs = new ArrayList<Integer>();
		ArrayList<Integer> levels = new ArrayList<Integer>();
		ArrayList<Integer> healthsArray = new ArrayList<Integer>();
		ArrayList<Integer> scoresArray = new ArrayList<Integer>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:4000/ap_project","root","10289802");

		ResultSet usernames = selectRowQuery(connection,"username");
		ResultSet numberOfCoins = selectRowQuery(connection,"nuberOfCoins");
		ResultSet numberOfLevels = selectRowQuery(connection,"numberOfLevel");
		ResultSet healths = selectRowQuery(connection,"health");
		ResultSet numberOfBombs = selectRowQuery(connection,"numberOfBombs");
		ResultSet scores = selectRowQuery(connection,"score");

		int counter = 1;
		while(usernames.next()) {
			names.add(usernames.getString(1));
			System.out.println(counter);
			counter+=1;
		}
		while(numberOfCoins.next()) {
			coins.add(numberOfCoins.getInt(1));
		}
		while(numberOfBombs.next()) {
			bombs.add(numberOfBombs.getInt(1));
		}
		while(numberOfLevels.next()) {
			levels.add(numberOfLevels.getInt(1));
		}
		while(healths.next()) {
			healthsArray.add(healths.getInt(1));
		}
		while(scores.next()) {
			scoresArray.add(scores.getInt(1));
		}

		for(int i=0 ; i<names.size() ; i++) {
			User user = new User(healthsArray.get(i), bombs.get(i),
					coins.get(i), levels.get(i), scoresArray.get(i), names.get(i));
			usersArray.add(user);
		}
		System.out.println(usersArray);
		System.out.println(names);
		System.out.println(coins);
		System.out.println(bombs);
		System.out.println(scoresArray);
		System.out.println(levels);
		System.out.println(healthsArray);

		//		updateQuery(connection, "username", "'HEY DAY'", idDeterminer(selectQuery(connection, selectedUserName)));
		//		insertQuery(connection, "'shaftalu'");
	}

	public static ResultSet selectQuery(Connection connection, String selectedUserName) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id from users WHERE username =" + selectedUserName);
		return resultSet;
	}

	public static ResultSet selectRowQuery(Connection connection , String whichRow) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select "+  whichRow  +" from users");
		return resultSet;
	}

	public static ResultSet selectALLQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from users");
		return resultSet;
	}

	public static int idDeterminer(ResultSet resultSet) throws SQLException {
		int ID = 0;
		while(resultSet.next()) {
			ID = resultSet.getInt(1);
		}
		return ID;
	}

	public static int updateQuery(Connection connection, String culomnName, Object object, int ID) throws SQLException {
		Statement statement = connection.createStatement();
		int updatedQuery = 0;
		if (object.getClass().getName() == "java.lang.Integer") {
			updatedQuery = statement.executeUpdate("UPDATE users SET" + culomnName + "=" + (int) object + "where id = " + ID);
		}else if (object.getClass().getName() == "java.lang.String") {
			updatedQuery = statement.executeUpdate("UPDATE users SET " + culomnName + "=" + (String) object + " where id = " + ID);
		}
		return updatedQuery;
	}

	public static void insertQuery(Connection connection, String userName) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id from users");
		int newID = idGenerator(resultSet);
		statement.executeUpdate("insert into users values(" + newID + "," + userName + "," + 5 + "," + 3 + "," + 0 + "," + 1 + "," + 0 + ")");
	}
	public static int idGenerator(ResultSet resultSet) throws SQLException {
		ArrayList<Integer> idArray = new ArrayList<Integer>();
		while(resultSet.next()) {
			idArray.add(resultSet.getInt(1));

		}
		if (idArray.size() != 0) {
			int idArrayMaximum = idArray.get(0);
			for (int i = 0; i<idArray.size(); i++) {
				if (idArray.get(i) > idArrayMaximum) {
					idArrayMaximum = idArray.get(i);
				}
			}
			return idArrayMaximum + 1;
		}else {
			return  1;
		}
	}

}
