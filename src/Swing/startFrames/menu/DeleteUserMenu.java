package Swing.startFrames.menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import org.json.simple.JSONObject;

import DataBaseWorks.DataBaseFunctionsClass;
import IO.User;
import IO.WriteDataToFile;
import Swing.startFrames.PreStartFrame;
import soundation.SoundEffect;

public class DeleteUserMenu extends JFrame{

	PreStartFrame preStartFrame;
	private ArrayList<JRadioButton> jRadioButtons;
	private BufferedImage StartIcon;
	private JButton deleteButton;
	private String clickSound;
	private SoundEffect sE;

	
	public DeleteUserMenu(PreStartFrame preStartFrame) {
		super("DELETE USER");
		this.preStartFrame = preStartFrame;	
		jRadioButtons = new ArrayList<JRadioButton>();
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		init();
		buttonWorks();
		makingUsersList();
	}

	private void buttonWorks() {
		deleteButton = new JButton("DELETE");
		deleteButton.setBounds(550, 500, 200, 50);
		deleteButton.setBackground(Color.blue);
		deleteButton.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		deleteButton.setForeground(Color.white);
		this.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				deleteFunction();
			}
		});
	}

	protected void deleteFunction() {
		if(PreStartFrame.kindOfCommunication == "FILE") {
			deleteUserFromList();
			deleteFromFile();
			updateList();
		}
		if(PreStartFrame.kindOfCommunication == "DATABASE") {
			try {
				deleteFromDataBase();
			} catch (Exception e1) {
			}
			updateList();
		}

		this.setVisible(false);
		preStartFrame.setVisible(true);
	}


	private void deleteUserFromList() {
		synchronized(PreStartFrame.usersArray) {
			for(JRadioButton radioButton : jRadioButtons) {
				if(radioButton.isSelected()) {
					for(int i=0 ; i< PreStartFrame.usersArray.size(); i++) {
						if(PreStartFrame.usersArray.get(i).name == radioButton.getText()) {
							PreStartFrame.usersArray.remove(i);
						}
					}
				}
			}
		}
	}

	private void deleteFromDataBase() throws SQLException {
		synchronized(PreStartFrame.usersArray) {
			for(JRadioButton radioButton : jRadioButtons) {
				if(radioButton.isSelected()) {
					for(User user :PreStartFrame.usersArray ) {
						if(user.name == radioButton.getText()) {
							PreStartFrame.usersArray.remove(user);
							DataBaseFunctionsClass.deleteQuery(PreStartFrame.connection, radioButton.getText());
						}
					}
				}
			}
		}
	}

	private void updateList() {
		int loc = 50;
		synchronized(PreStartFrame.usersArray) {
			for (User user : PreStartFrame.usersArray) {
				JRadioButton radio = new JRadioButton(user.name);
				radio.setBounds(50, loc , 200 , 50);
				jRadioButtons.add(radio);
				this.preStartFrame.add(radio);
				this.preStartFrame.jRadioButtons.add(radio);
				loc += 50;
			}	
		}
	}

	private void deleteFromFile() {
		synchronized(PreStartFrame.usersArray) {
			JSONObject object = new JSONObject();
			for(User user : PreStartFrame.usersArray) {
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
	}


	private void init() {
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
	}

	private void makingUsersList() {
		synchronized (PreStartFrame.usersArray) {
			if(PreStartFrame.usersArray!= null) {
				int loc = 50;
				for (User user : PreStartFrame.usersArray) {
					JRadioButton radio = new JRadioButton(user.name);
					radio.setBounds(50, loc , 200 , 50);
					jRadioButtons.add(radio);
					this.add(radio);
					loc += 50;
				}	
			}
		}
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
