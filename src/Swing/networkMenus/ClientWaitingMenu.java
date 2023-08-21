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
import javax.swing.WindowConstants;

import engine.TimeThread;

public class ClientWaitingMenu extends JFrame implements Runnable{

	ClientMenu clientMenu;
	int[] infoArray;
	private JLabel nameLabel;
	private JLabel portLabel;
	private JLabel levelLabel;
	private JLabel playersLabel;
	private BufferedImage background;

	public ClientWaitingMenu(ClientMenu clientmenu, int[] infoArray) throws HeadlessException {
		super("CLIENT WAITING!!!");
		this.clientMenu = clientmenu;
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
	}


	private void imageWorks() {
		try {
			background = ImageIO.read(new File("resources/onlineServerImage.png"));
			this.setContentPane(new JLabel(new ImageIcon(background)));
		}catch(IOException ex) {
			System.out.println("Image IO");
		}
	}




	private void textWorks() {
		nameLabel = new JLabel(clientMenu.client.nameOfServer + "  " + "IS WATING ...!");
		nameLabel.setBounds(20, 20, 200, 50);
		portLabel = new JLabel("ON PORT :" + "  " + infoArray[0] );
		portLabel.setBounds(20, 40, 200, 50);
		levelLabel = new JLabel("NUMBER OF LEVELS :" + "  " + clientMenu.client.numberOfLevels );
		levelLabel.setBounds(20, 60, 200, 50);
		playersLabel = new JLabel("NUMBER OF PLAYERS :" + "  " + clientMenu.client.numberOfPlayers );
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

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int counter=0;
			if(counter<=1) {
				this.setVisible(true);
				textWorks();
			}
			counter++;
		}
	}

}



