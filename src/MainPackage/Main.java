package MainPackage;

import java.awt.HeadlessException;
import java.sql.SQLException;

import Swing.startFrames.PreStartFrame;


public class Main {
	public static void main(String[] args) throws HeadlessException, ClassNotFoundException, SQLException {
		System.gc();
		PreStartFrame psf = new PreStartFrame("DATABASE");
		psf.setVisible(true);

		//    	MainFrame mainFrame = new MainFrame();
		//    	MainPanel mainPanel = new MainPanel();
		//    	mainFrame.add(mainPanel);
		//    	mainFrame.setVisible(true);
		//    	new PaintLoop(mainPanel).start();
	}
}

