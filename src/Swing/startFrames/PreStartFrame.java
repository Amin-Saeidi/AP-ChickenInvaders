package Swing.startFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import DataBaseWorks.DataBaseFunctionsClass;
import IO.ReadDataFromFile;
import IO.User;
import IO.WriteDataToFile;
import Swing.startFrames.menu.DeleteUserMenu;
import Swing.startFrames.menu.addUserFrame;
import soundation.SoundEffect;


public class PreStartFrame extends JFrame {

	public StartFrame startFrame;
	public addUserFrame addUserFrame;
	private Border myBorder;
	private JButton addUserButton,deleteUserButton,Enter;
	private BufferedImage StartIcon;
	public ReadDataFromFile reading;
	public static ArrayList<User> usersArray;
	public ArrayList<JRadioButton> jRadioButtons;
	private User pickedUser;
	private boolean firstTime =true;
	public static String kindOfCommunication;
	public static Connection connection;
	private String clickSound;
	private SoundEffect sE;


	public PreStartFrame(String kindOfCommunication) throws HeadlessException, ClassNotFoundException, SQLException {
		super("Chicken Attack");
		this.kindOfCommunication=kindOfCommunication;
		jRadioButtons = new ArrayList<JRadioButton>();
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		try {
			StartIcon = ImageIO.read(new File("resources/StarMenu.png"));
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/StarMenu.png")))));

		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null); 
		setIconImage(StartIcon);
		if(kindOfCommunication == "FILE") {
			reading = new ReadDataFromFile();
			usersArray = reading.makingArray();
			makingUsersRadioList();	
		}
		if (kindOfCommunication == "DATABASE") {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:4000/ap_project","root","10289802");

				System.out.println("YOU ARE NOW CONNECT TO DATABASE");
				try {
					DataBaseFunctionsClass.createTable(connection);
				}catch(Exception e) {
					System.out.println("NOW TABLE IS CREATE");
				}

			} catch (SQLException e) {
				//HERE WILL BE FILL WITH SHOW ERROR FRAME
				JOptionPane.showMessageDialog(null, "DATABASE CONNECTION FAILED");
				System.out.println("CONNECTION FAILED");
				System.exit(HIDE_ON_CLOSE);
			}
			makingUsersListWithDataBase();
			makingUsersRadioList();	
		}
		System.out.println(usersArray);
		borderCreator();
		creatingButtons();
	}

	private static void makingUsersListWithDataBase() throws SQLException {
		usersArray = new ArrayList<User>();
		synchronized(usersArray) {
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Integer> coins = new ArrayList<Integer>();
			ArrayList<Integer> bombs = new ArrayList<Integer>();
			ArrayList<Integer> levels = new ArrayList<Integer>();
			ArrayList<Integer> waves = new ArrayList<Integer>();
			ArrayList<Integer> healthsArray = new ArrayList<Integer>();
			ArrayList<Integer> scoresArray = new ArrayList<Integer>();
			ResultSet usernames =DataBaseFunctionsClass.selectRowQuery(connection,"username");
			ResultSet numberOfCoins =DataBaseFunctionsClass.selectRowQuery(connection,"numberOfCoins");
			ResultSet numberOfLevels =DataBaseFunctionsClass.selectRowQuery(connection,"numberOfLevel");
			ResultSet numberOfWave =DataBaseFunctionsClass.selectRowQuery(connection,"numberOfWave");
			ResultSet healths =DataBaseFunctionsClass.selectRowQuery(connection,"health");
			ResultSet numberOfBombs =DataBaseFunctionsClass.selectRowQuery(connection,"numberOfBombs");
			ResultSet scores =DataBaseFunctionsClass.selectRowQuery(connection,"score");

			while(usernames.next()) {
				names.add(usernames.getString(1));
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
			while(numberOfWave.next()) {
				waves.add(numberOfWave.getInt(1));
			}
			while(healths.next()) {
				healthsArray.add(healths.getInt(1));
			}
			while(scores.next()) {
				scoresArray.add(scores.getInt(1));
			}
			int id =1;
			for(int i=0 ; i<names.size() ; i++) {
				User user = new User(healthsArray.get(i), bombs.get(i),
						coins.get(i), levels.get(i), waves.get(i),scoresArray.get(i), names.get(i));
				user.id = id;
				id++;
				usersArray.add(user);
			}
		}



	}
	public void creatingButtons() {

		Enter = new JButton("Enter");
		addUserButton = new JButton("AddNewUser");
		deleteUserButton = new JButton("DeleteUser");
		Enter.setBounds(580, 500 , 200, 50);
		addUserButton.setBounds(20, 500, 240, 50);
		deleteUserButton.setBounds(300, 500, 240, 50);
		buttonWorks(Enter);
		buttonWorks(addUserButton);
		buttonWorks(deleteUserButton);
		this.add(Enter,0);
		this.add(addUserButton,0);
		this.add(deleteUserButton,0);
		Enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				enterFunction();
			}
		});
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.start();
				addFrame();		
			}
		});
		deleteUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				deleteFunction();
			}
		});
	}


	private void deleteFunction() {
		DeleteUserMenu deleteUserMenu = new DeleteUserMenu(this);
		deleteUserMenu.setVisible(true);
		this.setVisible(false);
		for(JRadioButton radioButton :jRadioButtons) {
			this.remove(radioButton);
		}
	}

	private void makingUsersRadioList() {
		synchronized(usersArray) {
			if(usersArray!= null) {
				int loc = 50;
				for (User user : usersArray) {
					JRadioButton radio = new JRadioButton(user.name);
					radio.setBounds(50, loc , 200 , 50);
					jRadioButtons.add(radio);
					this.add(radio);
					loc += 50;
				}	
			}
		}
	}
	protected void addFrame() {
		JTextField nameInput = new JTextField("delete this statement and write your name please");
		JButton flushButton = new JButton("if you write your name,click!");
		addUserFrame=new addUserFrame(nameInput,flushButton,usersArray,this);
	}

	protected void enterFunction() {
		for(JRadioButton radioButton : jRadioButtons) {
			if(radioButton.isSelected()) {
				for(User user : usersArray) {
					if(user.name == radioButton.getText()) {
						pickedUser = user;
						StartFrame startFrame = new StartFrame(pickedUser,this);
						startFrame.setVisible(true);
						this.setVisible(false);
						System.out.println(pickedUser.name);
						this.setVisible(false);
					}
				}
			}
		}		
	}

	public void buttonWorks(JButton jButton) {
		jButton.setBackground(Color.blue);
		jButton.setBorder(myBorder);
		jButton.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		jButton.setForeground(Color.white);
	}

	public Border borderCreator(){
		myBorder = new Border() {
			int radius = 40;
			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
			}
			@Override
			public boolean isBorderOpaque() {
				return true;
			}
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				g.drawRoundRect(x, y, width-1, height-1, radius, radius);
			}
		};
		return myBorder;
	}


}
