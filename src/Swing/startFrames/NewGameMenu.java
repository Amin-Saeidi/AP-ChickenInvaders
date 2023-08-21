package Swing.startFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import IO.User;
import Swing.MainFrame;
import Swing.MainPanel;
import Swing.networkMenus.MultiPlayerGameMenu;
import engine.Game;
import engine.PaintLoop;
import levels.Level;
import soundation.SoundEffect;

public class NewGameMenu extends JFrame{

	private JButton singleGame,multiPlayerGame;
	private BufferedImage background;
	private MultiPlayerGameMenu multiPlayerGameMenu;
	public ArrayList<User> users;
	public User pUser;
	private String clickSound;
	private SoundEffect sE;


	public NewGameMenu(String title,ArrayList<User> users,User pickedUser) throws HeadlessException {
		super(title);
		this.users = users;
		this.pUser=pickedUser;
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		init();
	}

	private void init() {
		setSize(400 , 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		imageWorks();
		creatingButtons();
	}

	private void creatingButtons() {
		singleGame = new JButton("Single Game");
		multiPlayerGame = new JButton("MultiPlayer Game");
		buttonWorks(singleGame);
		buttonWorks(multiPlayerGame);
		singleGame.setBounds(50, 150, 300, 50);
		multiPlayerGame.setBounds(50, 300, 300, 50);
		singleGameWorks();
		multiPlyerGameWorks();
		this.add(singleGame);
		this.add(multiPlayerGame);
	}

	private void multiPlyerGameWorks() {
		multiPlayerGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				multiPlayerMenuShow();
			}
		});
	}
	public void multiPlayerMenuShow() {
		multiPlayerGameMenu = new MultiPlayerGameMenu("MultiPlayer Menu");
		multiPlayerGameMenu.setVisible(true);
		this.setVisible(false);
	}
	private void singleGameWorks() {
		singleGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				singleGameShow();
			}
		} );
	}

	public void singleGameShow() {
		Level level = new Level(pUser,true);
		MainFrame mainFrame = new MainFrame();
		MainPanel mainPanel = new MainPanel(mainFrame ,level,users);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		new PaintLoop(mainPanel).start();
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
