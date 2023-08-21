package game.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.Animatable;

public class PowerUps2 implements Animatable{

	private double powerUps2X;
	private double powerUps2Y;
	private double powerUps2_vy = 10;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	public String whichPowerUps;
	private Random random;
	private BufferedImage powerUps2Image1;
	private BufferedImage powerUps2Image2;
	private BufferedImage powerUps2Image3;

	public PowerUps2(double powerUps2X, double powerUps2Y, double heightOfImage , double widthOfImage) {
		this.powerUps2X = powerUps2X;
		this.powerUps2Y = powerUps2Y;
		this.heightOfImage = heightOfImage;
		this.widthOfImage = widthOfImage;
		initialize();
		randing();
	}
	private void initialize() {
		try {
			powerUps2Image1 = ImageIO.read(new File("resources/powerUps1(TirPower).png"));
			powerUps2Image2 = ImageIO.read(new File("resources/powerUps2(TirPower).png"));
			powerUps2Image3 = ImageIO.read(new File("resources/powerUps3(TirPower).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public double getPowerUp2sX() {
		return powerUps2X;
	}
	public void setPowerUps2X(double powerUpsX) {
		this.powerUps2X = powerUpsX;
	}
	public double getPowerUps2Y() {
		return powerUps2Y;
	}
	public void setPowerUps2Y(double powerUpsY) {
		this.powerUps2Y = powerUpsY;
	}
	@Override
	public void paint(Graphics2D g2) {
		if (paintCheck == true) {
			if (whichPowerUps =="1") {
				g2.drawImage(powerUps2Image1 ,(int) powerUps2X,(int) powerUps2Y,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if (whichPowerUps =="2") {
				g2.drawImage(powerUps2Image2 ,(int) powerUps2X,(int) powerUps2Y,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if (whichPowerUps =="3") {
				g2.drawImage(powerUps2Image3 ,(int) powerUps2X,(int) powerUps2Y,(int) heightOfImage ,(int) widthOfImage , null);
			}
		}
		powerUps2Y += powerUps2_vy;


	}
	@Override
	public void move() {
	}
	
	public void randing() {
		random = new Random();
		int r = random.nextInt(5);
		if (r == 3) {
			whichPowerUps = "3";
		}
		else if (r == 2) {
			whichPowerUps = "2";
		}
		else {
			whichPowerUps = "1";
		}
		
	}

}

