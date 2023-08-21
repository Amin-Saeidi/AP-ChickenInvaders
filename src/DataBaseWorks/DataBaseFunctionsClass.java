package DataBaseWorks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import IO.User;
import Swing.startFrames.PreStartFrame;

@SuppressWarnings("unused")
public class DataBaseFunctionsClass {


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

	public static int selectIDWithNameQuery(Connection connection,String name) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id from users");
		ArrayList<Integer> id = new ArrayList<Integer>();
		while(resultSet.next()) {
			id.add(resultSet.getInt(1));
		}
		int idNumber=0;
		for(Integer i : id) {
			if(i> idNumber) {
				idNumber=i;
			}
		}
		idNumber+=1;
		System.out.println(idNumber);
		return idNumber;
	}

	public static ResultSet selectALLQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from users");
		return resultSet;
	}

	public static  void createTable(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users");
		}catch(SQLException ex) {
			Statement statement;
			int set; 
			try {
				statement = connection.createStatement();
				set = statement.executeUpdate("CREATE TABLE users(id INT ,username VARCHAR(20) ,health INT , numberOfBombs INT, numberOfCoins INT, numberOfLevel INT , numberOfWave INT , score int , PRIMARY KEY (id))");
				System.out.println("TABLE CREATE");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static int updateQuery(Connection connection, String culomnName, Object object, int ID) throws SQLException {
		Statement statement = connection.createStatement();
		int updatedQuery = 0;
		if (object.getClass().getName() == "java.lang.Integer") {
			updatedQuery = statement.executeUpdate("UPDATE users SET " + culomnName + "=" + (int) object + " where id = " + ID);
		}else if (object.getClass().getName() == "java.lang.String") {
			updatedQuery = statement.executeUpdate("UPDATE users SET " + culomnName + "=" + (String) object + " where id = " + ID);
		}
		return updatedQuery;
	}
	
	public static void deleteQuery(Connection connection , String name) throws SQLException {
		Statement statement = connection.createStatement();
		int deleteQuery = 0;
		deleteQuery = statement.executeUpdate("delete from users where username =" + "'"+name+"'");
	}

	public static void insertQuery(Connection connection, String userName) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id from users");
		int newID = idGenerator(resultSet);
		statement.executeUpdate("insert into users values(" + newID + "," + userName + "," + 5 + "," + 3 + "," + 0 + "," + 1 + "," + 1 + "," + 0 + ")");
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

	private static int idDeterminer(ResultSet resultSet) throws SQLException {
		int ID = 0;
		while(resultSet.next()) {
			ID = resultSet.getInt(1);
		}
		return ID;
	}

}
