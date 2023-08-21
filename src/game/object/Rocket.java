package game.object;

import javax.imageio.ImageIO;

import engine.Animatable;
import engine.TimeThread;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rocket implements Animatable {
    private double x;
    private double y;
    private BufferedImage RocketImage;
    public boolean paintcheck = true;
	private double leftFlameX;
	private double rightFlameX;
	private double leftFlameY;
	private double rightFlameY;
	private boolean flameDrawCheck = true;
	private TimeThread flameTimeThread;
	private BufferedImage bombFlameImage;


    public Rocket(double x, double y) {
        this.x = x;
        this.y = y;

        try {
            RocketImage = ImageIO.read(new File("resources/rocket.png"));
            bombFlameImage = ImageIO.read(new File("resources/redFlameWB.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double loc) {
        this.x = loc;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void determineFlameCoordinates() {
		leftFlameX = this.getX() - 21;
		leftFlameY = this.getY() + 25;

		rightFlameX = leftFlameX + 13;
		rightFlameY = leftFlameY;
	}


    @Override
    public void paint(Graphics2D g2) {
    	if (paintcheck == true) {
    		if(x>1050) {x=1050;}
        	if(x<50) {x=50;}
        	if(y<50) {y=50;};
        	if(y>625) {y=625;};
            g2.drawImage(RocketImage, (int)x - RocketImage.getWidth()/2,(int) y - RocketImage.getHeight()/2, null);
            determineFlameCoordinates();
    		if (flameTimeThread != null && flameTimeThread.isAlive() == false) {
    			flameDrawCheck = true;
    		}
    		if (flameDrawCheck  == true) {
    			if (flameTimeThread == null || flameTimeThread.isAlive() == false) {
    				g2.drawImage(bombFlameImage, (int) leftFlameX, (int) leftFlameY, 30, 30, null);
    				g2.drawImage(bombFlameImage, (int) rightFlameX, (int) rightFlameY, 30, 30, null);
    				flameDrawCheck = false;
    				flameTimeThread = new TimeThread(50);
    				flameTimeThread.start();
    			}
    		}
    	}
    	
    }

    @Override
    public void move() {

    }
}