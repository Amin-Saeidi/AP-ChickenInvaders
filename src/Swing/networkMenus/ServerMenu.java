package Swing.networkMenus;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Network.SimpleServer;

public class ServerMenu extends JFrame{

	JTextField numberOfPlayers;
	JTextField numberOfLevels;
	JTextField port;
	JTextField name;
	JLabel players;
	JLabel levels;
	JLabel portNumber;
	JLabel serverName;
	JButton flushButton;
	BufferedImage background;
	int[] informationsArray;
	ServerWaitingMenu serverWaitingMenu;
	String nameOfServer;
	SimpleServer server; 
	MultiPlayerGameMenu multiPlayerGameMenu;
//	SimpleServer server;

	public ServerMenu(String title, MultiPlayerGameMenu multiPlayerGameMenu) throws HeadlessException {
		super(title);
		this.multiPlayerGameMenu = multiPlayerGameMenu;
		init();
	}

	private void init() {
		setSize(300 , 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		imageWorks();
		textWorks();
		creatingButton();
	}


	private void creatingButton() {
		flushButton = new JButton("FLUSH");
		buttonWorks(flushButton);
		flushButton.setBounds(50, 420, 200, 40);
		flushButton.addActionListener(new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					flushButtonWorks();
				}catch(Exception ex) {
					oo();
				}
			}
		});

		this.add(flushButton);

	}

	public void oo() {
		JOptionPane.showMessageDialog(null, "FILL THE BOXES CORRECTLY");
		System.out.println("ERROR!");
	}

	private void flushButtonWorks() {
		nameOfServer =name.getText();
		int numOfLevels =(int) Integer.parseInt( numberOfLevels.getText());
		int numOfPlayers =(int) Integer.parseInt( numberOfPlayers.getText());
		int portNum =(int) Integer.parseInt( port.getText());
		informationsArray = new int[3];
		informationsArray[0]= numOfLevels;
		informationsArray[1]= numOfPlayers;
		informationsArray[2]= portNum;
		serverWaitingMenu = new ServerWaitingMenu(this , informationsArray);
		serverWaitingMenu.setVisible(true);
		server = new SimpleServer(portNum , nameOfServer);
		server.start();
		this.setVisible(false);

	}

	private void buttonWorks(JButton button) {
		button.setBackground(Color.ORANGE);
		button.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		button.setForeground(Color.white);

	}

	private void textWorks() {
		name = new JTextField();
		numberOfLevels = new JTextField();
		numberOfPlayers = new JTextField();
		port = new JTextField();
		serverName = new JLabel("Name Of Server:");
		portNumber = new JLabel("Port:");
		levels = new JLabel("Number Of Levels:");
		players = new  JLabel("Number Of Players:");
		name.setBounds(175, 50, 100, 30);
		numberOfLevels.setBounds(175, 150, 100, 30);
		numberOfPlayers.setBounds(175, 250, 100, 30);
		port.setBounds(175, 350, 100, 30);
		serverName.setBounds(30, 50, 100, 30);
		levels.setBounds(30, 150, 100, 30);
		players.setBounds(30, 250, 100, 30);
		portNumber.setBounds(30, 350, 100, 30);
		this.add(serverName);
		this.add(levels);
		this.add(players);
		this.add(portNumber);		
		this.add(name);
		this.add(numberOfLevels);
		this.add(numberOfPlayers);
		this.add(port);

	}

	private void imageWorks() {
		try {
			background = ImageIO.read(new File("resources/onlineServerImage.png"));
			this.setContentPane(new JLabel(new ImageIcon(background)));
		}catch(IOException ex) {
			System.out.println("Image IO");
		}
	}





}
