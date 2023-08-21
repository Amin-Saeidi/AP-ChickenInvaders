package reflectionWorks.Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.TimeThread;
import game.objects.Egg;

public class PeripheralHens implements ReflectionPainter{

	private int numberOfHens = 5;
	private ArrayList<BufferedImage>  hensImages = new ArrayList<BufferedImage>();
	//	private ArrayList<Integer> xDimensions = new ArrayList<Integer>();
	//	private ArrayList<Integer> yDimensions = new ArrayList<Integer>();
	private ArrayList<Egg> eggs = new ArrayList<Egg>();
	private int whichHen = 4;
	private int xOfFirstHen;
	private int yOfFirstHen;
	private int everyStepMovement;
	private int marginState = 1;  
	private boolean paintCheck = true;
	private TimeThread eggTimeThread;
	private boolean eggDropingTimeThread = true;
	private Random rand;



	public PeripheralHens() throws IOException {
		initialize();
		for (int i=0 ; i<numberOfHens ; i++) {
			if(whichHen==1) {
				hensImages.add(ImageIO.read(new File("resources/hen1.png")));
			}
			if(whichHen==2) {
				hensImages.add(ImageIO.read(new File("resources/hen2.png")));
			}
			if(whichHen==3) {
				hensImages.add(ImageIO.read(new File("resources/hen3.png")));
			}
			if(whichHen==4) {
				hensImages.add(ImageIO.read(new File("resources/hen4.png")));
			}
		}
	}


	private void initialize() {
		xOfFirstHen = 4;
		yOfFirstHen = 51;
		everyStepMovement = 4;
	}

	@Override
	public void paint(Graphics2D g) {
		if (paintCheck   == true) {

			eggDroping(g);

			for(int i=0 ; i<hensImages.size() ; i++){
				if(marginState ==1) {
					g.drawImage(hensImages.get(i), (int) xOfFirstHen , (int) yOfFirstHen + i*70 , (int) 65, (int) 65 , null);
				}
				if(marginState ==2) {
					g.drawImage(hensImages.get(i), (int) xOfFirstHen - i*70, (int) yOfFirstHen , (int) 65, (int) 65 , null);
				}
				if(marginState ==3) {
					g.drawImage(hensImages.get(i), (int) xOfFirstHen  , (int) yOfFirstHen - i*70 , (int) 65, (int) 65 , null);
				}
				if(marginState ==4) {
					g.drawImage(hensImages.get(i), (int) xOfFirstHen + i*70, (int) yOfFirstHen , (int) 65, (int) 65 , null);
				}

			}

			if (xOfFirstHen==4 && yOfFirstHen>50 && marginState == 1) {
				yOfFirstHen = yOfFirstHen - everyStepMovement; 
				if (yOfFirstHen>=50) {
					xOfFirstHen=4;
				}
				if (yOfFirstHen <= 50) {
					marginState = 2;
				}
			}else if (xOfFirstHen>=4 && xOfFirstHen<1000 && yOfFirstHen<=50 && marginState == 2) {
				xOfFirstHen = xOfFirstHen + everyStepMovement; 
				if (xOfFirstHen >= 4) {
					yOfFirstHen=50;
				}
				if (xOfFirstHen >= 1000) {
					xOfFirstHen = 1000;
					yOfFirstHen=50;
					marginState = 3;
				}
			}else if (xOfFirstHen==1000 && yOfFirstHen>=50 && yOfFirstHen<600 && marginState == 3) {
				yOfFirstHen = yOfFirstHen + everyStepMovement; 
				xOfFirstHen=1000;
				if (yOfFirstHen>=600) {
					yOfFirstHen=600;
					xOfFirstHen=1000;
					marginState = 4;
				}
			}else if (xOfFirstHen<=1000 && yOfFirstHen==600 && marginState == 4) {
				xOfFirstHen = xOfFirstHen - everyStepMovement;
				if (xOfFirstHen <= 4) {
					marginState = 1;
					xOfFirstHen = 4;
				}
			}
		}
	}

	private void eggDroping(Graphics2D g) {

		for (int i=0 ; i<hensImages.size() ; i++) {
			if(marginState == 1) {
				rand = new Random();
				boolean posibility = randomEgg (rand);
				if (posibility == true) {
					Egg egg = new Egg(xOfFirstHen+20 ,yOfFirstHen+20+i*70,20,20);
					egg.egg_vy = 0;
					egg.egg_vx = 10;
					eggs.add (egg);
				}
			}
			if(marginState == 2) {
				rand = new Random();
				boolean posibility = randomEgg (rand);
				if (posibility == true) {
					Egg egg = new Egg(xOfFirstHen+20-i*70 ,yOfFirstHen+20,20,20);
					egg.egg_vy = 10;
					egg.egg_vx = 0;
					eggs.add (egg);

				}
			}
			if(marginState == 3) {
				rand = new Random();
				boolean posibility = randomEgg (rand);
				if (posibility == true) {
					Egg egg = new Egg(xOfFirstHen+20 ,yOfFirstHen+20-i*70,20,20);
					egg.egg_vy = 0;
					egg.egg_vx = -10;
					eggs.add (egg);

				}
			}
			if(marginState == 4) {
				rand = new Random();
				boolean posibility = randomEgg (rand);
				if (posibility == true) {
					Egg egg = new Egg(xOfFirstHen+20+i*70 ,yOfFirstHen+20,20,20);
					egg.egg_vy = -10;
					egg.egg_vx = 0;
					eggs.add (egg);
				}
			}
		}
		for (int i=0 ; i< eggs.size() ; i++) {
			if(eggs.get(i).getEggX() > 1500) {
eggs.remove(i);
			}
			if(eggs.get(i).getEggX() < -400) {
				eggs.remove(i);
			}
			if(eggs.get(i).getEggY() > 1100) {
				eggs.remove(i);
			}
			if(eggs.get(i).getEggY() < -400) {
				eggs.remove(i);
			}
			if(eggs.get(i) !=null) {
				eggs.get(i).paint(g);	
			}

		}
	}



	public void eggTimeThread() {
		eggTimeThread = new TimeThread(2000);
		eggTimeThread.start();
		eggDropingTimeThread  = false;

	}


	public boolean randomEgg(Random random ) {
		int r = random.nextInt(99);
		if (eggDropingTimeThread == true) {
			eggTimeThread();	
		}
		if (whichHen == 1 || whichHen==2) {
			if (r < 10 && eggTimeThread.isAlive()== false) {
				eggDropingTimeThread = true;
				return true;
			}
			else {
				return false;
			}	
		}
		if (whichHen == 3) {
			if (r < 20 && eggTimeThread.isAlive()== false) {
				eggDropingTimeThread = true;
				return true;
			}
			else {
				return false;
			}	
		}
		if (whichHen == 4) {
			if (r < 30 && eggTimeThread.isAlive()== false) {
				eggDropingTimeThread = true;
				return true;
			}
			else {
				return false;
			}	
		}
		else
		{
			return false;
		}
	}


}
