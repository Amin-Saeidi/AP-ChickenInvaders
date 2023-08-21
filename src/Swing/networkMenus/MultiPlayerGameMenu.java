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
import javax.swing.WindowConstants;

import engine.Game;
import engine.PaintLoop;
import soundation.SoundEffect;

public class MultiPlayerGameMenu extends JFrame{

	private JButton server,clientButton;
	private BufferedImage background;
	public ServerMenu serverMenu;
	public ClientMenu clientMenu;
	private String clickSound;
	private SoundEffect sE;
	
	public MultiPlayerGameMenu(String title) throws HeadlessException {
		super(title);
		init();
	}

	private void init() {
		setSize(400 , 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		imageWorks();
		creatingButtons();
	}

	private void creatingButtons() {
		server = new JButton("Enter as a Srever");
		clientButton = new JButton("Enter as a Client");
		buttonWorks(server);
		buttonWorks(clientButton);
		server.setBounds(50, 150, 300, 50);
		clientButton.setBounds(50, 300, 300, 50);
		enterAsASereverWorks();
		enterAsAClientWorks();
		this.add(server);
		this.add(clientButton);
	}

	private void enterAsAClientWorks() {
		clientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				clientShow();
			}
		});
	}
	public void clientShow() {
		clientMenu = new ClientMenu("CLIENT",this);
		clientMenu.setVisible(true);
		this.setVisible(false);
	}
	private void enterAsASereverWorks() {
		server.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				serverShow();
			}
		} );
	}

	public void serverShow() {
		serverMenu = new ServerMenu("SERVER",this);
		serverMenu.setVisible(true);
		this.setVisible(false);
	}
	private void buttonWorks(JButton button) {
		button.setBackground(Color.blue);
		button.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		button.setForeground(Color.white);

	}

	private void imageWorks() {
		try {
			background = ImageIO.read(new File("resources/menuImage.jpg"));
			this.setContentPane(new JLabel(new ImageIcon(background)));
		}catch(IOException ex) {
			System.out.println("Image IO");
		}
	}


}
