package Swing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends JFrame {
	private BufferedImage StartIcon;
//	private static DegreeOfRocket dOF;
	public MainFrame() {
        setTitle("Chicken Atack");
        try {
			StartIcon = ImageIO.read(new File("resources/StarMenu.png"));
		} catch (IOException e) {
		}
        setSize(1100, 700);
        setIconImage(StartIcon);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
    }
//	public static DegreeOfRocket getdOF() {
//		return dOF;
//	}
	
}