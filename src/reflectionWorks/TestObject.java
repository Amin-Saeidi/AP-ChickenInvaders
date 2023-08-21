package reflectionWorks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class TestObject implements Animatable{
	
	private double messageX;
	private double messageY;
	private double message_vy = 3;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage messageImage;
	public boolean showMessage=false;
	
	public TestObject() {
		this.messageX = 50;
		this.messageY = 50;
		this.heightOfImage =100;
		this.widthOfImage = 100;
		initialize();
	}
	private void initialize() {
		try {
			messageImage = ImageIO.read(new File("resources/chicken.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public double getMessageX() {
		return messageX;
	}
	public void setMessageX(double messageX) {
		this.messageX = messageX;
	}
	public double getMessageY() {
		return messageY;
	}
	public void setMessageY(double messageY) {
		this.messageY = messageY;
	}
	@Override
	public void paint(Graphics2D g2) {
			g2.drawImage(messageImage ,(int) messageX,(int) messageY, null);
			move();
	}
	
	@Override
	public void move() {
		messageY += message_vy;
	}

}
