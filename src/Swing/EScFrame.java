package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.simple.JSONObject;

import DataBaseWorks.DataBaseFunctionsClass;
import IO.User;
import IO.WriteDataToFile;
import Swing.startFrames.PreStartFrame;
import Swing.startFrames.menu.addUserFrame;
import levels.Level;
import soundation.SoundEffect;

@SuppressWarnings("unused")
public class EScFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private JButton Exit,Continue,reflectButton;
	private reflectionFrame reFrame;
	public MainFrame mainframe;
	public MainPanel mainPanel;
	public JFileChooser j; 
	public String path;
	public URL url;
	public URL[] urls;
	private String clickSound;
	private SoundEffect sE;


	public EScFrame(MainFrame mainframe, MainPanel mainPanel) {
		this.mainframe = mainframe;
		this.mainPanel = mainPanel;
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		try {
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/StarMenu.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Exit = new JButton("Exit");
		Continue = new JButton("Continue");
		reflectButton = new JButton("Choose a Class");
		Continue.setBounds(250, 200, 300, 50);
		Exit.setBounds(250, 350, 300, 50);
		reflectButton.setBounds(250, 275, 300, 50);
		buttonWorks(Continue);
		Continue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				close();

			}
		});
		buttonWorks(Exit);
		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				try {
					exit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonWorks(reflectButton);
		reflectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				chooseFile();
				creatingInputFrame();
				if (mainPanel.level != null) {
					mainPanel.level.reflected=true;
					mainPanel.level.addNewClassWithReflection = true;
					mainPanel.level.classLoaderPath = path;
					mainPanel.level.classLoaderURLPath = urls;				
				}
				if (mainPanel.game != null) {
					mainPanel.game.reflected = true;
					mainPanel.game.addNewClassWithReflection = true;
					mainPanel.game.classLoaderPath = path;
					mainPanel.game.classLoaderURLPath =urls;
				}
			}

		});


		this.add(reflectButton);
		this.add(Continue);
		this.add(Exit);
	}

	protected void chooseFile() {
		try {
			j = new JFileChooser();
			j.showSaveDialog(null);
			System.out.println(j.getSelectedFile().getPath());
			j.getDescription(j.getSelectedFile());

		}catch (Exception ex) {
			System.out.println("come on");
		}

		path = j.getSelectedFile().getPath();
		File file = j.getSelectedFile();
		try {
			url = file.toURI().toURL();
			urls = new URL[] {url};
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		path = path.replace("\\", "/");
		System.out.println(path);
		path = "file:///" + path;
		System.out.println(path);

	}

	protected void creatingInputFrame() {
		reFrame = new reflectionFrame(this);
		reFrame.setVisible(true);
		this.setVisible(false);
	}


	protected  void exit() throws SQLException {
		//saving with file
		if(PreStartFrame.kindOfCommunication == "FILE") {
			this.mainPanel.level.save=true;
			this.mainPanel.level.saving();
			String savedUserName =this.mainPanel.level.pickeduser.name;
			this.mainPanel.level.pickeduser.health=this.mainPanel.level.health;
			this.mainPanel.level.pickeduser.coins=this.mainPanel.level.coins;
			this.mainPanel.level.pickeduser.numberOfLevel=Level.numberOfLevel;	
			this.mainPanel.level.pickeduser.numberOfWave=this.mainPanel.level.numberOfWave;	
			this.mainPanel.level.pickeduser.numOfBombs=this.mainPanel.level.numOfBombs;
			this.mainPanel.level.pickeduser.score=this.mainPanel.level.score;
			int health = this.mainPanel.level.pickeduser.health;
			int coins = this.mainPanel.level.pickeduser.coins;
			int numOfBombs=this.mainPanel.level.pickeduser.numOfBombs;
			int numberOfLevel=this.mainPanel.level.pickeduser.numberOfLevel;
			int numberOfWave=this.mainPanel.level.pickeduser.numberOfWave;
			int score =this.mainPanel.level.pickeduser.score;
			for (int i=0 ; i<mainPanel.users.size() ; i++) {
				if (mainPanel.users.get(i).name == mainPanel.level.pickeduser.name ) {
					mainPanel.users.remove(i);
				}
				break;
			}
			User user = new User(health, numOfBombs, coins, numberOfLevel, numberOfWave,score, savedUserName);
			mainPanel.users.add(user);
			saveToFile(mainPanel.users);
		}

		if(PreStartFrame.kindOfCommunication=="DATABASE") {
			this.mainPanel.level.save=true;
			this.mainPanel.level.saving();
			String savedUserName =this.mainPanel.level.pickeduser.name;
			this.mainPanel.level.pickeduser.health=this.mainPanel.level.health;
			this.mainPanel.level.pickeduser.coins=this.mainPanel.level.coins;
			this.mainPanel.level.pickeduser.numberOfLevel=Level.numberOfLevel;	
			this.mainPanel.level.pickeduser.numberOfWave=this.mainPanel.level.numberOfWave;	
			this.mainPanel.level.pickeduser.numOfBombs=this.mainPanel.level.numOfBombs;
			this.mainPanel.level.pickeduser.score=this.mainPanel.level.score;
			int health = this.mainPanel.level.pickeduser.health;
			int coins = this.mainPanel.level.pickeduser.coins;
			int numOfBombs=this.mainPanel.level.pickeduser.numOfBombs;
			int numberOfLevel=this.mainPanel.level.pickeduser.numberOfLevel;
			int numberOfWave=this.mainPanel.level.pickeduser.numberOfWave;
			int score =this.mainPanel.level.pickeduser.score;
			System.out.println(this.mainPanel.level.pickeduser.id);
			int id =this.mainPanel.level.pickeduser.id;
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"username" ,"'"+savedUserName+"'" , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"health" , health , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"numberOfCoins" ,coins , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"score" ,score , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"numberOfLevel" ,numberOfLevel , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"numberOfWave" ,numberOfWave , id);
			DataBaseFunctionsClass.updateQuery(PreStartFrame.connection,"numberOfBombs" ,numOfBombs , id);
		}
		System.exit(EXIT_ON_CLOSE);
	}

	@SuppressWarnings("unchecked")
	private void saveToFile(ArrayList<User> users) {
		JSONObject object = new JSONObject();
		for(User user : PreStartFrame.usersArray) {
			String health =StringToIntegerString(String.valueOf(user.health));
			String numOfBomb =StringToIntegerString(String.valueOf(user.numOfBombs));
			String coin =StringToIntegerString(String.valueOf(user.coins));
			String numOfLevel =StringToIntegerString(String.valueOf(user.numberOfLevel));
			String numOfWave =StringToIntegerString(String.valueOf(user.numberOfWave));
			String score= String.valueOf(user.score);
			object.put(user.name,health+numOfBomb+coin+numOfLevel+numOfWave+score);
		}
		WriteDataToFile data = new WriteDataToFile(object);
	}

	protected void close() {	
		this.setVisible(false);
		//		this.mainframe.setEnabled(true);
		this.mainPanel.mainframe.setVisible(true);
		this.mainPanel.works=true;
	}

	public void buttonWorks(JButton jButton) {
		jButton.setBackground(Color.blue);
		jButton.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		jButton.setForeground(Color.white);
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
