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
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;


import IO.User;
import Swing.startFrames.PreStartFrame;
import Swing.startFrames.StartFrame;
import soundation.SoundEffect;

public class SettingFrame extends JFrame {


	private BufferedImage StartIcon;
	private JButton back;
	private JLabel soundLabel;
	private JRadioButton offRadioButton;
	private JRadioButton onRadioButton;
	private ArrayList<JRadioButton> radioButtons;
	private String clickSound;
	private SoundEffect sE;
	private StartFrame startFrame;


	public SettingFrame(StartFrame startFrame) throws HeadlessException {
		super("Chicken Attack");
		this.startFrame = startFrame;
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		radioButtons = new ArrayList<JRadioButton>();
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
		creatingButtons();
		creatingLabel();
		creatingRadioButtons();
	}

	private void creatingRadioButtons() {
		onRadioButton = new JRadioButton("ON");
		onRadioButton.setBounds(150, 50 , 50 , 50);
		radioButtons.add(onRadioButton);
		this.add(onRadioButton);
		offRadioButton = new JRadioButton("OFF");
		offRadioButton.setBounds(150, 150 , 50 , 50);
		radioButtons.add(offRadioButton);
		this.add(offRadioButton);
	}

	private void creatingLabel() {
		soundLabel = new JLabel("SOUND:");
		soundLabel.setBounds(50, 100 , 100 , 30);
		soundLabel.setForeground(Color.ORANGE);
		this.add(soundLabel);
	}

	private void creatingButtons() {
		back = new JButton("BACK");
		back.setBackground(Color.blue);
		back.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		back.setForeground(Color.white);
		back.setBounds(500, 500, 250, 50);
		this.add(back);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sE.setFile(clickSound);
				sE.play();
				close();	
			}
		});
	}

	protected void close() {
		for(JRadioButton jRadioButton : radioButtons) {
			if(jRadioButton.isSelected() == true) {
				if(jRadioButton.getText() == "ON") {
					SoundEffect.soundCondition = true;
				}else {
					SoundEffect.soundCondition = false;
				}
			}
		}
		this.setVisible(false);
		startFrame.setVisible(true);
	}

	

}
