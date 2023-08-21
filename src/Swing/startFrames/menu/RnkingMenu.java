package Swing.startFrames.menu;

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
import Swing.startFrames.PreStartFrame;
import Swing.startFrames.StartFrame;
import soundation.SoundEffect;

public class RnkingMenu extends JFrame {

	private ArrayList<JLabel> namesLabelArray;
	private ArrayList<JLabel> scoresLabelArray;
	private ArrayList<User>  usersArrayList;
	private BufferedImage StartIcon;
	private StartFrame startFrame;
	private JButton backButton;
	private String clickSound;
	private SoundEffect sE;


	public RnkingMenu(StartFrame frame) throws HeadlessException {
		super("RANKING");
		this.startFrame = frame; 
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		try {
			StartIcon = ImageIO.read(new File("resources/StarMenu.png"));
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/StarMenu.png")))));

		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
		buttonWorks();
		makingArrayLists();
	}

	private void buttonWorks() {
		backButton = new JButton("BACK");
		backButton.setBounds(550, 500, 200, 50);
		backButton.setBackground(Color.blue);
		backButton.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		backButton.setForeground(Color.white);
		this.add(backButton);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				back();
			}
		});
	}

	protected void back() {
		this.setVisible(false);
		startFrame.setVisible(true);

	}

	private void init() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null); 
		setIconImage(StartIcon);
		namesLabelArray = new ArrayList<JLabel>();
		scoresLabelArray= new ArrayList<JLabel>();
		usersArrayList = PreStartFrame.usersArray;
	}

	private void makingArrayLists() {
		int loc = 30;
		for (User user : usersArrayList) {
			JLabel name = new JLabel(user.name);
			String scoreString = String.valueOf(user.score);
			JLabel scoreLabel = new JLabel(scoreString);
			name.setBounds(50, loc , 100 , 30);
			scoreLabel.setBounds(150, loc, 100, 30);
			name.setForeground(Color.ORANGE);
			scoreLabel.setForeground(Color.ORANGE);
			namesLabelArray.add(name);
			scoresLabelArray.add(scoreLabel);
			this.add(name);
			this.add(scoreLabel);
			loc += 50;
		}	
	}	
}
