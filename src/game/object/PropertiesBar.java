package game.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import engine.Animatable;

@SuppressWarnings("unused")
public class PropertiesBar implements Animatable  {
	private int height, width, health, numOfBombs, coins, chickens;
	private BufferedImage heartImg, bombsImg, coinImg, chickenImg;
 	private JLabel heartImgLabel, bombImgLabel, coinImgLabel, chickenImgLabel;

	public PropertiesBar(int width, int height, int health, int numOfBombs, int coins, int chickens) {
		this.health = health;
		this.numOfBombs = numOfBombs;
		this.coins = coins;
		this.chickens = chickens;
		this.height = height;
		this.width = width;
		initialize();
	}

	public void initialize() {
		try {
			heartImg = ImageIO.read(new File("resources\\heart.png"));
			coinImg = ImageIO.read(new File("resources\\\\coin.png"));
			bombsImg = ImageIO.read(new File("resources\\\\bomb.png"));
			chickenImg = ImageIO.read( new File("resources\\\\chicken.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getHealth() {
		return health;
	}

	public int getCoins() {
		return coins;
	}

	public int getChickens() {
		return chickens;
	}

	public int getNumOfBombs() {
		return numOfBombs;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setNumOfBombs(int numOfBombs) {
		this.numOfBombs = numOfBombs;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setChickens(int chickens) {
		this.chickens = chickens;
	}

	public String intToString(int value) {
		String strValue = Integer.toString(value);
		return strValue;
	}
	@Override
	public void paint(Graphics2D g) {
		g.setColor(new Color(51, 0, 102));
		
		g.fillRoundRect(width/15 - width/3, height - height/11, width/2, height/6 , 200, 200);
		
		g.drawImage(heartImg, width/19 - width/20, height - height/11, width/38, height/25, null);
		g.setColor(Color.white);
		g.drawString(intToString(health), width/12 - width/20 - 5, height - height/15);
		
		g.drawImage(coinImg, width/10 - width/20, height - height/11, width/38, height/25, null);
		g.setColor(Color.white);
		g.drawString(intToString(coins), width/8 - width/22 - 5, height - height/15);
		
		g.drawImage(bombsImg, width/7 - width/20, height - height/11 + 2, width/38, height/27, null);
		g.setColor(Color.white);
		g.drawString(intToString(numOfBombs), width/6 - width/22 - 2, height - height/15);
		
		g.drawImage(chickenImg, width/5 - width/15, height - height/11 + 2, width/38, height/27, null);
		g.setColor(Color.white);
		g.drawString(intToString(chickens), width/5 - width/28 - 2, height - height/15);
		
		
		
	}

	@Override
	public void move() {		
	}
	
	

}
