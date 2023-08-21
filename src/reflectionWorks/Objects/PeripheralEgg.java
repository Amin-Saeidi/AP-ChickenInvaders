package reflectionWorks.Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.TimeThread;
import game.object.GiantEggTir;
import game.object.Tir;
import levels.Level;


@SuppressWarnings("unused")
public class PeripheralEgg implements ReflectionPainter{
	private double giantEggX, giantEggY; 
	private double giantEggDxForVibration = 4; 
	private double giantEggDyForVibration = 4;
	public BufferedImage bufferedImgGiantEgg;
	private File giantEggfile;
	private double giantEggRadius;
	private boolean giantEggPaintCheck = true;
	public double giantEggHeight;
	public double giantEggWidth;
	private ArrayList<GiantEggTir> giantEggtirsArray;
	private int numberOfTirsInCircle = 8;
	private double centerX;
	private double centerY;
	private int giantEggEnemyHealth;
	private boolean reflex = true;
	public boolean giantEggEnemyDrawCheck = true;
	public int giantEnemyNumberOfTirsHitten = 0;
	private boolean notMargin = true;
	private int marginState = 1;  
	private int step = 5;
	private TimeThread giantEggTirTimeThread;
	private boolean giantEggTirDropingTimeThread=true;
	private Random rand2;
	private boolean shotAgainBoolean = true;


	public PeripheralEgg() {
		this.giantEggX = 4;
		this.giantEggY = 51;
		this.giantEggHeight = 200;
		this.giantEggWidth =200;
		this.giantEggEnemyHealth = 500;
		init();
	}


	private void init() {
		giantEggtirsArray = new ArrayList<GiantEggTir>();
		giantEggfile = new File("resources\\Egg.png");
		try {
			bufferedImgGiantEgg = ImageIO.read(giantEggfile);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void eggVibration() {
		if (reflex == true) {
			this.giantEggX += giantEggDxForVibration;
			this.reflex = false;
		}else {
			this.giantEggX -= giantEggDxForVibration;
			this.reflex = true;
		}
	}

	public double getHugeEggY() {
		return giantEggY;
	}

	public double getHugeEggX() {
		return giantEggX;
	}




	@Override
	public void paint(Graphics2D g) {
		eggVibration();
		try {
			boomboom(g);
		}catch(Exception ex) {
		}
		
		if (giantEggPaintCheck  == true) {
			g.drawImage(bufferedImgGiantEgg, (int) giantEggX, (int) giantEggY, (int) giantEggHeight, (int) giantEggWidth , null);
		}

		if (giantEggX==4 && giantEggY>50 && marginState == 1) {
			notMargin = true;
			giantEggY = giantEggY - step; 
			if (giantEggY>=50) {
				giantEggX=4;
			}
			if (giantEggY <= 50) {
				marginState = 2;
			}
		}else if (giantEggX>=4 && giantEggX<840 && giantEggY<=50 && marginState == 2) {
			notMargin = true;
			giantEggX = giantEggX + step; 
			if (giantEggX >= 4) {
				giantEggY=50;
			}
			if (giantEggX >= 840) {
				giantEggX = 840;
				giantEggY=50;
				notMargin = false;
				marginState = 3;
			}
		}else if (giantEggX==840 && giantEggY>=50 && giantEggY<455 && marginState == 3) {
			notMargin = true;
			giantEggY = giantEggY + step; 
			giantEggX=840;
			if (giantEggY>=455) {
				notMargin = false;
				giantEggY=455;
				giantEggX=840;
				marginState = 4;
			}
		}else if (giantEggX<=840 && giantEggY==455 && marginState == 4) {
			notMargin = true;
			giantEggX = giantEggX - step;
			if (giantEggX <= 4) {
				notMargin = false;
				marginState = 1;
				giantEggX = 4;
			}
		}
	}

	public void giantEggTimeThread() {
		giantEggTirTimeThread = new TimeThread(500/Level.numberOfLevel);
		giantEggTirTimeThread.start();
		giantEggTirDropingTimeThread = false;
	}

	public boolean randomGiantEggDrops(Random random1) {
		int r = random1.nextInt(100);
		if (giantEggTirDropingTimeThread == true) {
			giantEggTimeThread();
		}
		if (r< 25 && giantEggTirTimeThread.isAlive()==false) {
			giantEggTirDropingTimeThread = true;
			return true;
		}
		else {
			return false;
		}

	}



	public void tirDrop() {
		double hugeEggRadius = 8*(Math.sqrt(Math.pow(this.giantEggHeight/2 + this.giantEggHeight/8, 2)
				+ Math.pow(this.giantEggWidth/2, 2)))/(2*Math.PI);
		double centerX = this.getHugeEggX() + 80;
		double centerY = this.getHugeEggY();
		double r = hugeEggRadius;
		for (int i= 0; i < this.numberOfTirsInCircle; i++) {
			double degree = (2*(1+i)*Math.PI)/this.numberOfTirsInCircle;
			GiantEggTir tir = new GiantEggTir(centerX+50, centerY+60, 7*Math.cos(degree), -7*Math.sin(degree));
			this.giantEggtirsArray.add(tir);
			tir.setDegree(degree);
		}
	}



	public void boomboom(Graphics2D g) {
		if (giantEggTirTimeThread != null && giantEggTirTimeThread.isAlive() == false) 
		{
			giantEggTirDropingTimeThread = true;
		}
		if (giantEggTirDropingTimeThread == true) {

			if (giantEggTirTimeThread == null || (giantEggTirTimeThread != null && giantEggTirTimeThread.isAlive() == false)) {

				if (this.giantEggtirsArray.size() == 0) {
					this.tirDrop();
					giantEggTimeThread();
				}else {
					for (GiantEggTir tir : this.giantEggtirsArray) {
						if (tir != null) {
							shotAgainBoolean  = false;
						}
					}
					if (shotAgainBoolean == true) {
						this.tirDrop();
						giantEggTimeThread();
					}
				}
			}
		}
		synchronized (this.giantEggtirsArray) {
			if (this.giantEggtirsArray.size() != 0) {
				for (GiantEggTir tir : this.giantEggtirsArray) {
					switch (this.marginState) {
					case 1:
						if (tir.getDegree() == 2*3*Math.PI/8 || tir.getDegree() == 2*4*Math.PI/8
						|| tir.getDegree() == 2*5*Math.PI/8) {
							this.giantEggtirsArray.remove(tir);
							break;
						}
						break;
					case 2:
						if (tir.getDegree() == 2*3*Math.PI/8 || tir.getDegree() == 2*2*Math.PI/8
						|| tir.getDegree() == 2*1*Math.PI/8) {
							this.giantEggtirsArray.remove(tir);
							break;
						}
						break;
					case 3:
						if (tir.getDegree() == 2*1*Math.PI/8 || tir.getDegree() == 2*8*Math.PI/8
						|| tir.getDegree() == 2*7*Math.PI/8) {
							this.giantEggtirsArray.remove(tir);
							break;
						}
						break;
					case 4:
						if (tir.getDegree() == 2*7*Math.PI/8 || tir.getDegree() == 2*6*Math.PI/8
						|| tir.getDegree() == 2*5*Math.PI/8) {
							this.giantEggtirsArray.remove(tir);
							break;
						}
						break;
					}
					synchronized(this.giantEggtirsArray) {
						if (tir != null) {
							tir.move();
							tir.paint(g);
						}
					}
					if (tir.getgiantTirX()>1100 || tir.getgiantTirX()<0 
							|| tir.getgiantTirY()<0 ||  tir.getgiantTirY()>800) {
						this.giantEggtirsArray.remove(tir);
						break;
					}
				}
			}
		}
		if (giantEggtirsArray != null) {
			synchronized (giantEggtirsArray) {
				for (GiantEggTir tir : giantEggtirsArray) {
					tir.paint(g);
					tir.move();
				}	
			} 
		}
	}

}
