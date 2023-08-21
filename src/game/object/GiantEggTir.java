package game.object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class GiantEggTir implements Animatable{

	public double giantTirX;
	public double giantTirY;
	public BufferedImage giantTirBufferedImg;
	private File giantTirFile;
	public boolean paintCheck = true;
	private double dY;
	private double dX;
	private double degree;

	public GiantEggTir(double giantTirX, double giantTirY, double dX, double dY) {
		this.giantTirX = giantTirX;
		this.giantTirY = giantTirY;
		this.dX = dX;
		this.dY = dY;

		giantTirFile = new File("resources\\tirG.png");
		try {
			giantTirBufferedImg = ImageIO.read(giantTirFile);

			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.atan2(dY, dX)+Math.PI/2, giantTirBufferedImg.getWidth() / 2, giantTirBufferedImg.getHeight() / 2);

			AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
			giantTirBufferedImg = op.filter(giantTirBufferedImg, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double getgiantTirX() {
		return giantTirX;
	}

	public double getgiantTirY() {
		return giantTirY;
	}
	
	public void setDegree(double degree) {
		this.degree = degree;
	}
	
	public double getDegree() {
		return this.degree;
	}

	
	@Override
	public void move() {
		giantTirY += dY;
		giantTirX += dX;
	} 

	@Override
	public void paint(Graphics2D g) {
		if (this.paintCheck == true) {
			g.drawImage(giantTirBufferedImg, (int) giantTirX, (int) giantTirY,null);
		}
	}


}
