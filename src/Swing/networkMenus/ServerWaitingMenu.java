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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import MainPackage.Start;
import Network.SimpleServer;

public class ServerWaitingMenu extends JFrame {

	ServerMenu servermenu;
	int[] infoArray;
	private JLabel nameLabel;
	private JLabel portLabel;
	private JLabel levelLabel;
	private JLabel playersLabel;
	private	JButton startButton;
	private	JButton updateButton;
	private BufferedImage background;

	public ServerWaitingMenu(ServerMenu servermenu, int[] infoArray) throws HeadlessException {
		super("SERVER WAITING!!!");
		this.servermenu = servermenu;
		this.infoArray = infoArray;
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
	private void imageWorks() {
		try {
			background = ImageIO.read(new File("resources/onlineServerImage.png"));
			this.setContentPane(new JLabel(new ImageIcon(background)));
		}catch(IOException ex) {
			System.out.println("Image IO");
		}
	}
	private void creatingButton() {
		startButton = new JButton("START");
		updateButton = new JButton("UPDATE");
		buttonWorks(updateButton);
		buttonWorks(startButton);
		startButton.setBounds(50, 420, 200, 40);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					startButtonWorks();
				}catch(Exception ex) {
					oo(ex);
				}

			}
		});
		updateButton.setBounds(50, 350, 200, 40);
		updateButton.addActionListener(new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateButtonWorks();
				}catch(Exception ex) {
					oo(ex);
				}
			}
		});

		this.add(updateButton);
		this.add(startButton);
	}

	protected void startButtonWorks() {

	}
	public void oo(Exception e) {
		JOptionPane.showMessageDialog(null, "An ERROR OCCURE");
		System.out.println("ERROR!");
	}

	private void updateButtonWorks() {
		int loc=100;
		ServerWaitingMenu serverWaitingMenuPrim = new ServerWaitingMenu(servermenu, infoArray);
		JLabel l = new JLabel(servermenu.server.nameOfClientInServer + "  IS CONNECTED");
		l.setBounds(20, loc, 200, 50);
		serverWaitingMenuPrim.add(l);	
		serverWaitingMenuPrim.setVisible(true);
		this.dispose();
	}

	private void buttonWorks(JButton button) {
		button.setBackground(Color.ORANGE);
		button.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		button.setForeground(Color.white);

	}

	private void textWorks() {
		nameLabel = new JLabel(servermenu.nameOfServer+ "  " + "Is Wating For Any Client!");
		nameLabel.setBounds(20, 20, 200, 50);
		portLabel = new JLabel("ON PORT :" + "  " + infoArray[2] );
		portLabel.setBounds(20, 40, 200, 50);
		levelLabel = new JLabel("NUMBER OF LEVELS :" + "  " + infoArray[0] );
		levelLabel.setBounds(20, 60, 200, 50);
		playersLabel = new JLabel("NUMBER OF PLAYERS :" + "  " + infoArray[1] );
		playersLabel.setBounds(20, 80, 200, 50);

		try {
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.add(nameLabel);
		this.add(portLabel);
		this.add(levelLabel);
		this.add(playersLabel);
	}

}
