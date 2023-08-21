package game.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class Coin  implements Animatable{
	private double coinX;
	private double coinY;
	private double coin_vy = 10;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage coinImage1;
	
	public Coin(double coinX, double coinY, double heightOfImage, double widthOfImage) {
		this.coinX = coinX;
		this.coinY = coinY;
		this.heightOfImage = heightOfImage;
		this.widthOfImage = widthOfImage;
		initialize();
	}

	private void initialize() {
		
		try {
			coinImage1 = ImageIO.read(new File("resources/coin1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getCoinX() {
		return coinX;
	}

	public void setCoinX(double coinX) {
		this.coinX = coinX;
	}

	public double getCoinY() {
		return coinY;
	}

	public void setCoinY(double coinY) {
		this.coinY = coinY;
	}

	@Override
	public void paint(Graphics2D g2) {
		if (paintCheck == true) {
			g2.drawImage(coinImage1,(int) coinX,(int) coinY,(int) heightOfImage ,(int) widthOfImage , null);
		}
		coinY += coin_vy;
	}

	@Override
	public void move() {
		
	}

	
}
