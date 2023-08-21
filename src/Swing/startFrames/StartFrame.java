package Swing.startFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import IO.User;
import MainPackage.Start;
import Swing.startFrames.menu.RnkingMenu;
import Swing.startFrames.menu.SettingFrame;
import engine.Game;
import engine.PaintLoop;
import levels.Level;
import soundation.SoundEffect;

public class StartFrame extends JFrame{

	//	private StartPanel startPanel;
	private Start start;
	private Border myBorder;
	private BufferedImage StartIcon;
	private JButton LoadGame,NewGame,Ranking,Setting,AboutUs,Exit;
	private NewGameMenu newGameMenu;
	private PreStartFrame preStartFrame;
	public User pickedUser;
	private String clickSound;
	private SoundEffect sE;


	public StartFrame(User user,PreStartFrame preStartFrame)  {
		super("Chicken Attack");
		this.preStartFrame=preStartFrame;
		this.pickedUser = user;
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		try {
			StartIcon = ImageIO.read(new File("resources/StarMenu.png"));
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/StarMenu.png")))));

		} catch (IOException e) {
			e.printStackTrace();
		}
		//		startPanel = new StartPanel();
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setIconImage(StartIcon);
		borderCreator();
		creatingButtons();
		//		this.add(startPanel);
	}
	public void creatingButtons() {

		NewGame = new JButton("NewGame");
		LoadGame = new JButton("LoadGame");
		Ranking = new JButton("Ranking");
		Setting = new JButton("Setting");
		AboutUs = new JButton("AboutUs");
		Exit = new JButton("Exit");
		NewGame.setBounds(250, 200, 300, 50);
		LoadGame.setBounds(250, 275, 300, 50);
		Ranking.setBounds(300, 350, 200, 50);
		Setting.setBounds(300, 425, 200, 50);
		AboutUs.setBounds(540, 500, 250, 50);
		Exit.setBounds(10, 500, 250, 50);
		buttonWorks(NewGame);
		buttonWorks(LoadGame);
		buttonWorks(Ranking);
		buttonWorks(Setting);
		buttonWorks(AboutUs);
		buttonWorks(Exit);
		this.add(NewGame,0);
		this.add(LoadGame,0);
		this.add(Ranking,0);
		this.add(Setting,0);
		this.add(AboutUs,0);
		this.add(Exit,0);
		LoadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				Level level = new Level(pickedUser);
				Start load =new Start(level,preStartFrame.usersArray);
				falsevisible();
			}
		});
		NewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				newGameFunction();
			}
		});
		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				exit();
			}
		});
		Ranking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				rankingFunction();

			}
		});
		Setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				SettingFunction();
			}
		});
	}

	protected void SettingFunction() {
		this.setVisible(false);
		SettingFrame settingFrame = new SettingFrame(this);
		settingFrame.setVisible(true);
	}
	protected void rankingFunction() {
		this.setVisible(false);
		RnkingMenu rMenu = new RnkingMenu(this);
		rMenu.setVisible(true);

	}
	protected void falsevisible() {
		this.setVisible(false);
	}
	protected void exit() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	protected void newGameFunction() {
		newGameMenu = new NewGameMenu("NewGame ----> Menu",preStartFrame.usersArray,pickedUser);
		newGameMenu.setVisible(true);
		this.setVisible(false);
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
