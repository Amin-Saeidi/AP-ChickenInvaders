package Swing;
import levels.Level;
import soundation.SoundEffect;

import javax.swing.*;

import IO.User;
import Network.SimpleServer;
import engine.Game;
import engine.PaintLoop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


@SuppressWarnings("unused")
public class MainPanel extends JPanel {

	/**
	 * JUST NOTHING
	 */
	private static final long serialVersionUID = 1;
	public MainFrame mainframe;
	public Level level;
	public boolean EscFrameShow = false;
	public ArrayList<User> users;
	public Game game;
	boolean onlineCheck = true;
	public PaintLoop paintThread;
	public boolean works=true;
	private String clickSound;
	private SoundEffect sE;
	
	public MainPanel(MainFrame mainframe,Game game) {
		this.mainframe = mainframe;
		this.game = game;
		initialize();
		mouseWorks();
		keyboardWorks();
	}
	public MainPanel(MainFrame mainframe,Level level,ArrayList<User> users) {
		this.mainframe = mainframe;
		this.level = level;
		this.users=users;
		initialize();
		mouseWorks();
		keyboardWorks();
	}

	private void initialize() {
		sE = new SoundEffect();
		clickSound = ".//resources//punchVoice.wav";
		setFocusable(true);
		setBounds(0, 0, 1200, 700);
	}

	private void keyboardWorks() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					esc();
				}
			}
		});


	}

	private void mouseWorks() {
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (level!=null) {
					if (level.getRocket() != null) {
						level.getRocket().setX(e.getX());
						level.getRocket().setY(e.getY());

					}
				}
				if(game!=null) {
					if (game.getRocket() != null) {
						game.getRocket().setX(e.getX());
						game.getRocket().setY(e.getY());					
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (level!=null) {
					if (level.getRocket() != null) {
						level.getRocket().setX(e.getX());
						level.getRocket().setY(e.getY());

					}
				}
				if(game!=null) {
					if (game.getRocket() != null) {
						game.getRocket().setX(e.getX());
						game.getRocket().setY(e.getY());					
					}
				}

			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (level!=null) {
					level.increasing();
				}
				if (game !=null) {
					game.increasingTemperatur();
				}
			}


			@Override
			public void mousePressed(MouseEvent e) {

				if (level!=null) {
					level.shelik();
					sE.setFile(clickSound);
					sE.play();
					if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
						level.shelikBomb();
					}
				}
				if (game !=null) {
					game.shelik();
					sE.setFile(clickSound);
					sE.play();
					if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
						game.shelikBomb();
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});		
	}

	protected void esc()  {
		EScFrame es = new EScFrame(this.getMainFrame(),this);
		mainframe.setVisible(false);
		es.setVisible(true);
//		this.getMainFrame().setEnabled(false);
		works=false;
		back();

	}

	private MainFrame getMainFrame() {
		return this.mainframe;
	}

	protected void back() {
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(works==true) {
			if (level!=null) {
				level.paint(g2);
			}
			if (game !=null) {
				game.paint(g2);
			}
		}
	}

	public void moveGame() {
		if(works==true) {
			if (level!=null) {
				level.move();		
			}
			if (game !=null) {
				game.move();	
			}
		}
	}

}