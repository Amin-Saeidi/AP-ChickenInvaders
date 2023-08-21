package game.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class Egg  implements Animatable{
	private double eggX;
	private double eggY;
	public  double egg_vy = 10;
	public  double egg_vx = 0;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage eggImage1;
	
	public Egg(double eggX, double eggY, double heightOfImage, double widthOfImage) {
		this.eggX = eggX;
		this.eggY = eggY;
		this.heightOfImage = heightOfImage;
		this.widthOfImage = widthOfImage;
		initialize();
	}

	private void initialize() {
		
		try {
			eggImage1 = ImageIO.read(new File("resources/Egg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getEggX() {
		return eggX;
	}

	public void setEggX(double eggX) {
		this.eggX = eggX;
	}

	public double getEggY() {
		return eggY;
	}

	public void setEggY(double eggY) {
		this.eggY = eggY;
	}

	@Override
	public void paint(Graphics2D g2) {
		if (paintCheck == true) {
			g2.drawImage(eggImage1,(int) eggX,(int) eggY,(int) heightOfImage ,(int) widthOfImage , null);
		}
		eggY += egg_vy;
		eggX += egg_vx;
	}

	@Override
	public void move() {
		
	}

	
}
