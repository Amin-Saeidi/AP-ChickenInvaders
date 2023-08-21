package Swing.startFrames.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.json.simple.JSONObject;

import DataBaseWorks.DataBaseFunctionsClass;
import IO.User;
import IO.WriteDataToFile;
import Swing.startFrames.PreStartFrame;
import game.objects.Coin;
import soundation.SoundEffect;

public class addUserFrame extends JFrame{
	private JButton ok; 
	private JTextField writeName;
	private String name;
	private JPanel jp;
	public ArrayList<User> usersArray ;
	public  User user;
	private ArrayList<JRadioButton> jRadioButtons = new ArrayList<JRadioButton>();
	PreStartFrame preStartFrame;
	private String clickSound;
	private SoundEffect sE;


	public addUserFrame(JTextField name,JButton ok,ArrayList<User> users,PreStartFrame preStartFrame) {

		super("AddUser Frame");
		this.ok = ok;
		this.preStartFrame =preStartFrame;
		this.writeName = name;
		this.usersArray = users;
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		setSize(300, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		preStartFrame.setVisible(false);
		setVisible(true);
		paint();

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				createNewUser();
				if(PreStartFrame.kindOfCommunication == "FILE") {
					writeUsersToFile();
					updateList();
				}
				if(PreStartFrame.kindOfCommunication == "DATABASE") {
					try {
						writeUsersToDataBase();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					updateList();
				}
			}
		});
	}

	protected void writeUsersToDataBase() throws SQLException {
		String name ="'"+ user.name + "'";
		DataBaseFunctionsClass.insertQuery(PreStartFrame.connection, name);
	}

	private void updateList() {
		int loc = 50;
		for (User user : usersArray) {
			JRadioButton radio = new JRadioButton(user.name);
			radio.setBounds(50, loc , 200 , 50);
			jRadioButtons.add(radio);
			this.preStartFrame.add(radio);
			this.preStartFrame.jRadioButtons.add(radio);
			loc += 50;
		}	
	}


	protected void writeUsersToFile() {
		JSONObject object = new JSONObject();
		for(User user : usersArray) {
			String health =StringToIntegerString(String.valueOf(user.health));
			String numOfBomb =StringToIntegerString(String.valueOf(user.numOfBombs));
			String coin =StringToIntegerString(String.valueOf(user.coins));
			String numOfLevel =StringToIntegerString(String.valueOf(user.numberOfLevel));
			String numOfWave = StringToIntegerString(String.valueOf(user.numberOfWave));
			String score= String.valueOf(user.score);
			object.put(user.name,health+numOfBomb+coin+numOfLevel+numOfWave+score);
		}
		WriteDataToFile data = new WriteDataToFile(object);
	}

	public void paint() {
		jp = new JPanel();
		jp.setBackground(Color.pink);
		ok.setBackground(Color.RED);
		ok.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 16));
		writeName.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 14));
		ok.setForeground(Color.white);
		jp.add(writeName);
		jp.add(ok);
		add(jp);		
	}

	public void createNewUser(){
		name = writeName.getText();
		user = new User(5,3,0,1,1,0,name);
		usersArray.add(user);
		PreStartFrame.usersArray = usersArray;
		if(PreStartFrame.kindOfCommunication=="DATABASE") {
			try {
				user.id=DataBaseFunctionsClass.selectIDWithNameQuery(PreStartFrame.connection, user.name);
				System.out.println(user.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		preStartFrame.setVisible(true);
		setVisible(false);
	}

	public String StringToIntegerString(String value){
		if (value.length()==1) {
			value = "00"+value;
			return value;
		}
		if (value.length()==2) {
			value = "0"+value;
			return value;
		}
		else {
			return value;
		}
	}

}
