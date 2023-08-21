package game.objects.hens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.Animatable;
import engine.TimeThread;
import game.objects.Coin;
import game.objects.Egg;
import game.objects.PowerUps1;
import game.objects.PowerUps2;

public class SingleHen implements Animatable {

	private double henX;
	private double henY;
	private double vx ;
	private double vy ;
	public double heightOfImage;
	public double widthOfImage;
	public boolean paintCheck = true;
	private BufferedImage hen1Image;
	private BufferedImage hen2Image;
	private BufferedImage hen3Image;
	private BufferedImage hen4Image;
	private double changingDegree = Math.PI/180;
	private CircleGroup Cg;
	private RectangleGroup Rg;
	@SuppressWarnings("unused")
	private SuicidalGroup Sg;
	public Egg egg = null;
	public Coin coin = null;
	public PowerUps1 powerUps1 = null;
	public PowerUps2 powerUps2 = null;
	private String whichGroup;
	double circularRadius ;
	double degree ;
	public double hitToDeath;
	public String whichHen = "1";
	public boolean hit = false;
	public boolean producedEgg = false;
	private double henNextX;
	private double henNextY;
	private double randomNumberForDx;
	private double randomNumberForDy;
	public boolean randomMovementBoolean = true;
	public boolean linearMovementBoolean = true;
	public boolean firstTimeBoolean = true;
	public boolean threadCreatingBoolean = true;
	public TimeThread choosingEnemyTimeThread;

	public SingleHen() {
		initialize();
	}

	public SingleHen(double x, double y,double heightOfImage, double widthOfImage ,String whichGroup ,String whichHen , CircleGroup Cg ) {
		this.whichGroup = whichGroup;
		this.Cg = Cg;
		this.whichHen = whichHen;
		this.heightOfImage=heightOfImage;
		this.widthOfImage=widthOfImage;
		this.henX = x;
		this.henY = y;
		this.hitToDeath = (double) Integer.valueOf(whichHen);
		initialize();
	}
	public SingleHen(double x, double y, double vx, double vy ,double heightOfImage, double widthOfImage,String whichGroup, String whHens, RectangleGroup Rg)
	{
		this.whichHen = whHens;
		this.whichGroup = whichGroup;
		this.heightOfImage=heightOfImage;
		this.widthOfImage=widthOfImage;
		this.Rg = Rg;
		this.henX = x;
		this.henY = y;
		this.vx = vx;
		this.vy = vy;
		this.hitToDeath = (double) Integer.valueOf(whichHen);
		initialize();
	}
	public SingleHen(double x, double y ,double heightOfImage, double widthOfImage,String whichGroup, String whHens, SuicidalGroup Sg)
	{
		this.whichHen = whHens;
		this.whichGroup = whichGroup;
		this.heightOfImage=heightOfImage;
		this.widthOfImage=widthOfImage;
		this.Sg = Sg;
		this.henX = x;
		this.henY = y;
		this.hitToDeath = (double) Integer.valueOf(whichHen);
		initialize();
	}
	private void initialize() {
		try {
			hen1Image = ImageIO.read(new File("resources/hen1.png"));
			hen2Image = ImageIO.read(new File("resources/hen2.png"));
			hen3Image = ImageIO.read(new File("resources/hen3.png"));
			hen4Image = ImageIO.read(new File("resources/hen4.png"));
		} 
		catch (IOException ex) {
		}


	}
	public void dead() {
		if (hitToDeath <= 0) {
			this.paintCheck = false;
			this.hit = true;
		}
	}

	@Override
	public void paint(Graphics2D g2) {
		if (paintCheck == true) {
			if(whichHen == "1") {
				g2.drawImage(hen1Image,(int) henX,(int) henY,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if(whichHen == "2") {
				g2.drawImage(hen2Image,(int) henX,(int) henY,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if(whichHen == "3") {
				g2.drawImage(hen3Image,(int) henX,(int) henY,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if(whichHen == "4") {
				g2.drawImage(hen4Image,(int) henX,(int) henY,(int) heightOfImage ,(int) widthOfImage , null);

			}
			if (whichGroup == "rectangle") {

				if (Rg.rectangleHens[3][11].henY < 250) {
					henY +=2;	
				}
				else{
					henX += vx;
					henY += vy;			
				}
			}
			if (whichGroup == "circular") {
				if(Cg.center_y >= 210  ) {
					degree += changingDegree;
					henX += (+circularRadius) * (Math.cos(degree + changingDegree)-Math.cos(degree)); 
					henY += (-circularRadius) * (Math.sin(degree + changingDegree)-Math.sin(degree));	
				}
				else {
					henY+= Cg.center_vy;
				}

			}
			if (whichGroup == "Suicidal") {
				if (threadCreatingBoolean == true) {
					choosingEnemyTimeThread = new TimeThread(100);
					choosingEnemyTimeThread.start();
					threadCreatingBoolean = false;
				}
				if (randomMovementBoolean == true) {
					if (this.linearMovementBoolean) {
						if (this.firstTimeBoolean ) {
							randomMovement();
							this.firstTimeBoolean = false;
						}
						linearMovement();
						if (Math.abs(henX - henNextX) < 4.5 && Math.abs(henY - henNextY) < 4.5) {
							choosingEnemyTimeThread = new TimeThread(100);
							choosingEnemyTimeThread.start();
							henX = henNextX;
							henY = henNextY;
							linearMovementBoolean = false;
							randomMovement();
						}
					}
				}
			}


			if (egg != null) {
				egg.paint(g2);
				producedEgg = false;
				if (egg.getEggY() > 800) {
					egg.paintCheck = false;
					egg = null;
				}
			}
		}
		if (hit == true) {
			if (powerUps1 != null) {
				powerUps1.paint(g2);
				if (powerUps1.getPowerUps1Y() > 800) {
					powerUps1.paintCheck = false;
					powerUps1 = null;
				}
			}
		}
		if (hit == true) {
			if (powerUps2 != null) {
				powerUps2.paint(g2);
				if (powerUps2.getPowerUps2Y() > 800) {
					powerUps2.paintCheck = false;
					powerUps2 = null;
				}
			}
		}
		if (hit == true) {
			if (coin != null) {
				coin.paint(g2);
				if (coin.getCoinY() > 800) {
					coin.paintCheck = false;
					coin = null;
				}
			}
		}



	}
	public double getVx() {
		return vx;
	}

	public double getX() {
		return henX;
	}

	public double getY() {
		return henY;
	}
	public double getVy() {
		return vy;
	}

	public void setX(double x) {
		this.henX = x;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setY(double y) {
		this.henY = y;
	}

	@Override
	public void move() {
	}



	public void setEnemyNextX(double enemyNextX) {
		this.henNextX = enemyNextX;
	}

	public void setEnemyNextY(double enemyNextY) {
		this.henNextY = enemyNextY;
	}


	private void randomMovement() {
		if (this.getX() <= 1100/2 + 20 && this.getY() <= 700/2 + 20) {
			probablityDetermineForXorY(2);
			this.setEnemyNextY(this.getY() + randomNumberForDy);
			this.setEnemyNextX(henX);
		}else if (this.getX() >= 1100/2 + 20 && this.getY() <= 700/2 + 20) {
			probablityDetermineForXorY(2);
			this.setEnemyNextY(this.getY() + randomNumberForDy);
			this.setEnemyNextX(henX);
		}else if (this.getX() >= 1100/2 + 20 && this.getY() >= 700/2 + 20) {
			probablityDetermineForXorY(1);
			this.setEnemyNextX(this.getX() - randomNumberForDx);
			probablityDetermineForXorY(2);
			this.setEnemyNextY(this.getY() - randomNumberForDy);
		}else if (this.getX() <= 1100/2 + 20 && this.getY() >= 700/2 + 20) {
			probablityDetermineForXorY(1);
			this.setEnemyNextX(this.getX() + randomNumberForDx);
			probablityDetermineForXorY(2);
			this.setEnemyNextY(this.getY() - randomNumberForDy);
		}
	}

	private void probablityDetermineForXorY(int portion) {
		switch (portion) {
		case 1:
			Random random1 = new Random();
			randomNumberForDx = random1.nextInt((int) (widthOfImage/2)+200) + 400;
			break;
		case 2:
			Random random2 = new Random();
			randomNumberForDy = random2.nextInt((int) (heightOfImage/2) +200) + 100;
			break;
		}
	}

	private void linearMovement() {
		if (henNextX - henX != 0) {
			double slope = (henNextY - henY)/(henNextX - henX);
			double b =  henNextY - slope*henNextX;
			if (henNextX > henX) {
				henX += 5;
			}else if (henNextX < henX) {
				henX -= 5;
			}
			henY = henX*slope + b;
		}else {
			henY += 5;
		}
	}


}
