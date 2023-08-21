package Swing;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class reflectionFrame extends JFrame{

	private JTextField packageTextField,classTextField;
	private JLabel packageLabel,classLabel;
	JButton flushButton;
	private EScFrame es;
	private BufferedImage StartIcon;	

	public reflectionFrame(EScFrame es) {
		super("CLASS DATA");
		this.es=es;
		try {
			StartIcon = ImageIO.read(new File("resources/StarMenu.png"));
			this.setContentPane(new JLabel(new ImageIcon(StartIcon)));

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		packageTextField = new JTextField();
		classTextField = new JTextField();
		packageLabel = new JLabel("PACKAGE: ");
		classLabel = new JLabel("CLASS: ");
		flushButton = new JButton("FLUSH");
		classLabel.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 15));
		packageLabel.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 15));
		classLabel.setForeground(Color.WHITE);
		packageLabel.setForeground(Color.WHITE);
		packageTextField.setBounds(100, 50, 150, 30);
		classTextField.setBounds(100,100,150,30);
		packageLabel.setBounds(20, 50, 100, 30);
		classLabel.setBounds(20, 100, 100, 30);
		flushButton.setBounds(200, 400, 250, 50);
		buttonWorks(flushButton);
		flushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String packageName = packageTextField.getText().toString();
				String className = classTextField.getText().toString();
				if(es.mainPanel.level != null) {
					es.mainPanel.level.reflectedPath = packageName+"."+className;
				}
				if(es.mainPanel.game != null) {
					es.mainPanel.game.reflectedPath = packageName+"."+className;
				}
				closeInnerFrame();
			}
		});
		this.add(flushButton);
		this.add(packageTextField);
		this.add(classTextField);
		this.add(packageLabel);
		this.add(classLabel);
		this.add(flushButton);
	}

	private void buttonWorks(JButton jButton) {
		jButton.setBackground(Color.blue);
		jButton.setFont(new Font("Times New Roman", Font.ITALIC|Font.CENTER_BASELINE , 25));
		jButton.setForeground(Color.white);
	}

	protected void closeInnerFrame() {
		this.setVisible(false);
		es.setVisible(true);
	}



}
