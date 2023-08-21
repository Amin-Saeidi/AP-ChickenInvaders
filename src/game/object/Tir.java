package game.object;

import javax.imageio.ImageIO;

import engine.Animatable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tir implements Animatable {
	private double x;
	private double y;
	private double vx;
	private double vy;
	public static String whichTir = "0";
	public static double rateOfHeat;
	private BufferedImage tirImage;
	private BufferedImage tir1Image;
	private BufferedImage tir2Image;
	private BufferedImage tir3Image;
	public boolean paintcheck = true;

	public Tir(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;

		try {

			// copied from http://www.java2s.com/Code/Java/Advanced-Graphics/RotatingaBufferedImage.htm
			tirImage = ImageIO.read(new File("resources/tir.png"));
			tir1Image = ImageIO.read(new File("resources/tir1.png"));
			tir2Image = ImageIO.read(new File("resources/tir2.png"));
			tir3Image = ImageIO.read(new File("resources/tir3.png"));

			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.atan2(vy, vx), tirImage.getWidth() / 2, tirImage.getHeight() / 2);

			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			tirImage = op.filter(tirImage, null);
		} catch (IOException ex) {

		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void move() {
		if (whichTir == "0") {
			x += vx;
			y += vy;
		}
		if (whichTir == "1") {
			y -= 20;
		}
		if (whichTir == "2") {
			y -= 30;
		}
		if (whichTir == "3") {
			y -= 40;
		}
	}

	public void paint(Graphics2D g2) {
		if(paintcheck == true) {
			if (whichTir == "0") {
				g2.drawImage(tirImage, (int)x, (int)y, null);
				rateOfHeat = 5;
			}
			if (whichTir == "1") {
				g2.drawImage(tir1Image, (int)x, (int)y, null);
				rateOfHeat=10;
			}
			if (whichTir == "2") {
				g2.drawImage(tir2Image, (int)x, (int)y, null);
				rateOfHeat=15;
				
			}
			if (whichTir == "3") {
				g2.drawImage(tir3Image, (int)x, (int)y, null);
				rateOfHeat=20;
			}	
		}


	}
}