package game.object;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class Bomb implements Animatable {
	private double x;
	private double y;
	private double vx;
	private double vy;
	public boolean paintCheck= true;
	private BufferedImage bombImageFromLeft;
	private BufferedImage bombImageFromRight;
	private Rocket rocket;
	private boolean isTheFirstTime = true;
	private boolean leftSideDetermined = false;
	private boolean rightSideDetermined = false;

	public Bomb(Rocket rocket) {
		this.rocket = rocket;
		init();
	}



	private void init() {
		try {
			bombImageFromLeft = ImageIO.read(new File("resources/bombToDesForLeftPart.png"));
			bombImageFromRight = ImageIO.read(new File("resources/bombToDes.png"));
			this.x = rocket.getX() - 55;
			this.y = rocket.getY() - 75;
			this.vx = 5;
			this.vy = 5;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void moveForLeftPart() {
		y -= vy;
		x += vx;
	}

	public void moveForRightPart() {
		y -= vy;
		x -= vx;
	}


	public void move() {

	}

	public void paint(Graphics2D g2) {
		if (paintCheck ==true) {
			if (this.isTheFirstTime  == true) {
				this.isTheFirstTime = false;
				if (rocket.getX() > 0 && rocket.getX() < 500) {
					System.out.println("T------------>left");
					leftSideDetermined = true;
					moveForLeftPart();
					g2.drawImage(bombImageFromLeft, (int)x, (int)y,null);
				}else if (rocket.getX() > 500 && rocket.getX() < 1000) {
					System.out.println("T------------>right");
					rightSideDetermined  = true;
					moveForRightPart();
					g2.drawImage(bombImageFromRight, (int)x, (int)y,null);
				}
			}else {
				if (leftSideDetermined == true) {
					System.out.println("F------------>left");
					moveForLeftPart();
					g2.drawImage(bombImageFromLeft, (int)x, (int)y,null);
				}else if (rightSideDetermined == true) {
					System.out.println("F------------>right");
					moveForRightPart();
					g2.drawImage(bombImageFromRight, (int)x, (int)y, null);
				}
			}
		}
	}


}
