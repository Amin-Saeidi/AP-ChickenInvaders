package game.object;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Animatable;

public class TemperatureBar  implements Animatable{
	public int score;
	public double temperature;
	public int width;
	public int height;
	public double maximumTemp = 100;
	
	

	public TemperatureBar(double temperature, int width, int height) {
		this.temperature = temperature;
		this.width = width;
		this.height = height;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(102 , 0 , 204));
		g2.fillRoundRect(width/20 - width/3, height/7 - height/4, width/2, height/6, 100, 100);
		for (int i=0 ; i<= maximumTemp ; i+=5) {
			g2.setColor(Color.WHITE);
			g2.drawRect(80 + i, 5, 3, 22);	
		}
		for (int i=0 ; i<= temperature ; i+=5) {
			g2.setColor(new Color(51,0,102));
			g2.drawRect(80 + i, 5, 3, 22);	
		}
		g2.setColor(Color.WHITE);
		g2.drawString(Integer.toString(score),20, 20);
	}

	@Override
	public void move() {
		
	}
	

}
