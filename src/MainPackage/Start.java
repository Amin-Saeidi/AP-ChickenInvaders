package MainPackage;


import java.util.ArrayList;

import IO.User;
import Swing.MainFrame;
import Swing.MainPanel;
import engine.Game;
import engine.PaintLoop;
import levels.Level;

public class Start {
	MainFrame mainFrame;
	MainPanel mainPanel;
	ArrayList<User> users;
	Game game;
	Level level;
	
	
	public Start(Game game) {
		this.game= game;
		mainFrame = new MainFrame();
		mainPanel = new MainPanel(mainFrame, game);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		PaintLoop p = new PaintLoop(mainPanel);
		mainPanel.paintThread = p;
		p.start();
	}

	public Start(Level level,ArrayList<User> users) {
		this.level= level;
		this.users=users;
		mainFrame = new MainFrame();
		mainPanel = new MainPanel(mainFrame, level,users);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		PaintLoop p = new PaintLoop(mainPanel);
		mainPanel.paintThread = p;
		p.start();
	}

	
}
