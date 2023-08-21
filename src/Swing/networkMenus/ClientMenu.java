package Swing.networkMenus;

import java.awt.Color;
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

import MainPackage.Start;
import Network.SimpleClient;
import engine.TimeThread;

public class ClientMenu extends JFrame {

	JButton connectButton;
	JTextField port;
	JTextField IP;
	JTextField name;
	String nameOfClient;
	String ipp;
	public SimpleClient client; 
	private int[] informationsArray;
	private JLabel clientName;
	private JLabel portNumber;
	private JLabel ip;
	private BufferedImage background;
	MultiPlayerGameMenu multiPlayerGameMenu;

	public ClientMenu(String string, MultiPlayerGameMenu multiPlayerGameMenu) {
		super(string);
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
		connectButton = new JButton("CONNECT");
		buttonWorks(connectButton);
		connectButton.setBounds(50, 420, 200, 40);
		connectButton.addActionListener(new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connectButtonWorks();
				}catch(Exception ex) {
					oo(ex);
				}

			}
		});
		this.add(connectButton);
	}
	protected void oo(Exception ex) {
		JOptionPane.showMessageDialog(null, "FILL THE BOXES CORRECTLY");
		System.out.println("ERROR!");
		ex.printStackTrace();
	}

	private void buttonWorks(JButton button) {
		button.setBackground(Color.ORANGE);
		button.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		button.setForeground(Color.white);

	}

	private void connectButtonWorks() throws InterruptedException {
		nameOfClient =name.getText();
		ipp = IP.getText();
		int portNum =(int) Integer.parseInt( port.getText());
		client = new SimpleClient(ipp, portNum , nameOfClient);
		client.start();
		informationsArray = new int[1];
		informationsArray[0]= portNum;
		this.setVisible(false);
		ClientWaitingMenu clientWaitingMenu = new ClientWaitingMenu(this, informationsArray);
		Thread clientWaitingMenuthred = new Thread(clientWaitingMenu);
		clientWaitingMenuthred.start();
		System.out.println(client.nameOfServer);

	}


	private void textWorks() {
		name = new JTextField();
		IP = new JTextField();
		port = new JTextField();
		clientName = new JLabel("Name Of Client:");
		portNumber = new JLabel("Port:");
		ip = new  JLabel("IP:");
		name.setBounds(175, 50, 100, 30);
		IP.setBounds(175, 150, 100, 30);
		port.setBounds(175, 250, 100, 30);
		clientName.setBounds(30, 50, 100, 30);
		ip.setBounds(30, 150, 100, 30);
		portNumber.setBounds(30, 250, 100, 30);
		this.add(clientName);
		this.add(IP);
		this.add(ip);
		this.add(portNumber);		
		this.add(name);
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
