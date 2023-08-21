package game.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Animatable;

public class EndOfLevelMessage implements Animatable {

	private double messageX;
	private double messageY;
	private double message_vy = 3;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage messageImage;
	public boolean showMessage=false;
	public EndOfLevelMessage(double messageX, double messageY, double heightOfImage , double widthOfImage) {
		this.messageX = messageX;
		this.messageY = messageY;
		this.heightOfImage = heightOfImage;
		this.widthOfImage = widthOfImage;
		initialize();
	}
	private void initialize() {
		try {
			messageImage = ImageIO.read(new File("resources/message.png"));
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
		if (showMessage == true) {
			g2.drawImage(messageImage ,(int) messageX,(int) messageY, null);
			messageY += message_vy;

		}		

	}
	@Override
	public void move() {
	}

}

