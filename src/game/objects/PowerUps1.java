package game.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class PowerUps1 implements Animatable{

	private double powerUpsX;
	private double powerUpsY;
	private double powerUps_vy = 10;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage powerUpsImage;
	public PowerUps1(double powerUpsX, double powerUpsY, double heightOfImage , double widthOfImage) {
		this.powerUpsX = powerUpsX;
		this.powerUpsY = powerUpsY;
		this.heightOfImage = heightOfImage;
		this.widthOfImage = widthOfImage;
		initialize();
	}
	private void initialize() {
		try {
			powerUpsImage = ImageIO.read(new File("resources/powerUps1(FirePower).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public double getPowerUp1sX() {
		return powerUpsX;
	}
	public void setPowerUps1X(double powerUpsX) {
		this.powerUpsX = powerUpsX;
	}
	public double getPowerUps1Y() {
		return powerUpsY;
	}
	public void setPowerUps1Y(double powerUpsY) {
		this.powerUpsY = powerUpsY;
	}
	@Override
	public void paint(Graphics2D g2) {
		if (paintCheck == true) {
			g2.drawImage(powerUpsImage ,(int) powerUpsX,(int) powerUpsY,(int) heightOfImage ,(int) widthOfImage , null);
		}
		powerUpsY += powerUps_vy;


	}
	@Override
	public void move() {
	}

}

