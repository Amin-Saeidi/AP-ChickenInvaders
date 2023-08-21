package game.objects.hens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.Animatable;
import game.object.GiantEggTir;
import levels.Level;


public class GiantEgg implements Animatable{

	private double giantEggX, giantEggY; 
	private double giantEggDx; 
	private double giantEggDy = 5;
	public BufferedImage bufferedImgGiantEgg;
	private File giantEggfile;
	public double giantEggRadius;
	public double giantEggHeight;
	public double giantEggWidth;
	public ArrayList<GiantEggTir> giantEggTirsArray;
	public int numberOfTirsInCircle = 8;
	public double centerX;
	public double centerY;
	public int width;
	public int height;
	private boolean pingPong = true;
	public boolean paintCheck = true;
	public int giantEggHitToDeath = 0;

	public GiantEgg(int width,int height, double giantEggX, double giantEggY, double giantEggHeight, double giantEggWidth) {
		this.giantEggX = giantEggX;
		this.giantEggY = giantEggY;
		this.giantEggHeight = giantEggHeight;
		this.giantEggWidth = giantEggWidth;
		this.width = width;
		this.height = height;
		initialize();
	}

	public void initialize() {
		giantEggTirsArray = new ArrayList<GiantEggTir>();
		giantEggfile = new File("resources\\giantegg.png");
		try {
			bufferedImgGiantEgg = ImageIO.read(giantEggfile);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	public void eggVibration() {
		this.giantEggDx = 4;
		if (pingPong == true) {
			this.giantEggX += giantEggDx;
			this.pingPong = false;
		}else {
			this.giantEggX -= giantEggDx;
			this.pingPong = true;
		}
	}

	public double getGiantEggY() {
		return giantEggY;
	}

	public double getGiantEggX() {
		return giantEggX;
	}


	@Override
	public void move() {
		if (giantEggY <= this.height/14) {
			this.giantEggDy = 5;
			this.giantEggY += giantEggDy;
			eggVibration();
		}
		if (giantEggY >= this.height/14) {
			eggVibration();
		}
		if (giantEggTirsArray != null) {
			for (GiantEggTir tir : giantEggTirsArray) {
				tir.move();
			}
		}
	}

	@Override
	public void paint(Graphics2D g) {
		if (giantEggHitToDeath>500*Level.numberOfLevel) {
			paintCheck = false;
		}
		if (paintCheck  == true) {
			if (giantEggTirsArray != null) {
				synchronized (giantEggTirsArray) {
					for (GiantEggTir tir : giantEggTirsArray) {
						tir.paint(g);
					}	
				} 
			}
			g.drawImage(bufferedImgGiantEgg, (int) giantEggX, (int) giantEggY ,null);
			
		}
	}
}



