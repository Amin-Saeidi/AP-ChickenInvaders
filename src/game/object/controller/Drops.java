package game.object.controller;

import java.util.ArrayList;
import java.util.Random;

import engine.TimeThread;
import game.object.GiantEggTir;
import game.object.Tir;
import game.objects.Coin;
import game.objects.Egg;
import game.objects.PowerUps1;
import game.objects.PowerUps2;
import game.objects.hens.CircleGroup;
import game.objects.hens.GiantEgg;
import game.objects.hens.RectangleGroup;
import game.objects.hens.SingleHen;
import levels.Level;

public class Drops {
	private RectangleGroup rG;
	private CircleGroup cG;
	private GiantEgg gE;
	private Random rand;
	private Random rand1;
	private Random rand2;
	private TimeThread eggTimeThread;
	private TimeThread giantEggTimeThread;
	private boolean eggDropingTimeThread = true;
	private boolean giantEggDropingTimeThread = true;
	private ArrayList<Tir> tirs;


	public Drops(RectangleGroup rg, CircleGroup cg,GiantEgg giantEgg ,ArrayList<Tir> tirs) {
		this.tirs = tirs;
		this.rG = rg;
		this.cG = cg;
		this.gE = giantEgg;
	}
	
	public void rectangularEggDrops() {
		int cul = rG.cul;
		int row = rG.row;
		for (int i=0 ; i < row ; i++ ) {
			for (int j=0 ; j<cul ; j++) {
				if (rG.rectangleHens[i][j].paintCheck == true) {
					rand = new Random();
					boolean posibility = randomEgg (rand ,rG.rectangleHens[i][j] );
					if (posibility == true) {
						if (rG.rectangleHens[i][j].egg == null) {
							rG.rectangleHens[i][j].egg = new Egg(
									rG.rectangleHens[i][j].getX()+20 ,rG.rectangleHens[i][j].getY()+20,
									20,20);
							rG.rectangleHens[i][j].producedEgg = true;
							if (rG.rectangleHens[i][j].whichHen == "1") {
								rG.rectangleHens[i][j].egg.egg_vy =10;
							}
							if (rG.rectangleHens[i][j].whichHen == "2") {
								rG.rectangleHens[i][j].egg.egg_vy =10;
							}
							if (rG.rectangleHens[i][j].whichHen == "3") {
								rG.rectangleHens[i][j].egg.egg_vy =20;
							}
							if (rG.rectangleHens[i][j].whichHen == "4") {
								rG.rectangleHens[i][j].egg.egg_vy =20;
							}
						}

					}
				}
			}
		}
	}

	public void circularEggDrops() {
		circularEggDrop(cG.circularHenArray1);
		circularEggDrop(cG.circularHenArray2);
		circularEggDrop(cG.circularHenArray3);
	}

	public void circularEggDrop(ArrayList<SingleHen> circularHenArray) {
		for (SingleHen singlehen : circularHenArray) {
			if (singlehen.paintCheck == true) {
				rand = new Random();
				boolean posibility = randomEgg (rand , singlehen);
				if (posibility == true) {
					if (singlehen.egg == null) {
						singlehen.egg = new Egg(
								singlehen.getX()+20 ,singlehen.getY()+20,
								20,20);
						if (singlehen.whichHen == "1") {
							singlehen.egg.egg_vy =10;
						}
						if (singlehen.whichHen == "2") {
							singlehen.egg.egg_vy =10;
						}
						if (singlehen.whichHen == "3") {
							singlehen.egg.egg_vy =20;
						}
						if (singlehen.whichHen == "4") {
							singlehen.egg.egg_vy =20;
						}
					}
				}
			}
		}
	}

	public void circularPowerUpsAndCoinDrops(){
		circularPowerUpsAndCoinDrop(cG.circularHenArray1);
		circularPowerUpsAndCoinDrop(cG.circularHenArray2);
		circularPowerUpsAndCoinDrop(cG.circularHenArray3);
	}
	public void circularPowerUpsAndCoinDrop(ArrayList<SingleHen> circularHenArray){
		synchronized (tirs) {
			for (Tir tir : tirs ) {
				if(tir.paintcheck == true) {
					for (SingleHen singlehen : circularHenArray) {
						if (singlehen.paintCheck == true) {
							if((tir.getX()< (singlehen.getX()+30)) && (tir.getX()> (singlehen.getX()-30)))
							{
								if ((tir.getY()< (singlehen.getY()+30)) && 
										(tir.getY()> (singlehen.getY()-30))) {
									rand1 = new Random();
									if (randomCoin(rand1)) {
										singlehen.coin = new Coin(singlehen.getX(), singlehen.getY(), 30 , 30);  
									}
									if (randomPowerUps1(rand1) == "1") {
										singlehen.powerUps1 = new PowerUps1(singlehen.getX() + 35, singlehen.getY(), 20 , 20);
									}
									else if (randomPowerUps2(rand1) == "2") {
										singlehen.powerUps2 = new PowerUps2(singlehen.getX(), singlehen.getY(), 20 , 20);
									}
								}

							}	
						}
						if (tir == null) {break;}	
					}
					if (tir == null) {break;}
				}
			}

		}
	}
	public void recrangularPowerUpsAndCoinDrops() {
		for (Tir tir : tirs ) {
			if(tir.paintcheck == true) {
				for (int i=0  ; i<rG.row ; i++) {
					for (int j=0 ; j<rG.cul ; j++ ) {
						if (rG.rectangleHens[i][j].paintCheck == true) {
							if((tir.getX()< (rG.rectangleHens[i][j].getX()+30)) && (tir.getX()> (rG.rectangleHens[i][j].getX()-30)))
							{
								if ((tir.getY()< (rG.rectangleHens[i][j].getY()+30)) && 
										(tir.getY()> (rG.rectangleHens[i][j].getY()-30))) {
									rand1 = new Random();
									if (randomCoin(rand1)) {
										rG.rectangleHens[i][j].coin = new Coin(rG.rectangleHens[i][j].getX(), rG.rectangleHens[i][j].getY(), 30 , 30);  
									}
									if (randomPowerUps1(rand1) == "1") {
										rG.rectangleHens[i][j].powerUps1 = new PowerUps1(rG.rectangleHens[i][j].getX() + 35, rG.rectangleHens[i][j].getY(), 20 , 20);
									}
									else if (randomPowerUps2(rand1) == "2") {
										rG.rectangleHens[i][j].powerUps2 = new PowerUps2(rG.rectangleHens[i][j].getX(), rG.rectangleHens[i][j].getY(), 20 , 20);
									}
								}

							}	
						}
						if (tir == null) {break;}	
					}
					if (tir == null) {break;}
				}
			}
		}

	}

	public String randomPowerUps2(Random rand2 ) {
		int r = rand2.nextInt(99);
		if (r>10 && r<14 ) {
			return "2";
		}
		else {
			return "0";
		}
	}

	public boolean randomCoin(Random random ) {
		int r = random.nextInt(99);
		if (r<5) {
			return true;
		}
		else {
			return false;
		}
	}


	public String randomPowerUps1(Random random ) {
		int r = random.nextInt(99);
		if (r<3) {
			return "1";
		}
		else {
			return "0";
		}
	}



	public void eggTimeThread() {
		eggTimeThread = new TimeThread(1000);
		eggTimeThread.start();
		eggDropingTimeThread = false;
	}
	public boolean randomEgg(Random random , SingleHen singleHen) {
		int whichhen = Integer.valueOf(singleHen.whichHen);
		int r = random.nextInt(99);
		if (eggDropingTimeThread == true) {
			eggTimeThread();	
		}
		if (whichhen == 1 || whichhen==2) {
			if (r < 10 && eggTimeThread.isAlive()== false) {
				eggDropingTimeThread = true;
				return true;
			}
			else {
				return false;
			}	
		}
		if (whichhen == 3) {
			if (r < 20 && eggTimeThread.isAlive()== false) {
				eggDropingTimeThread = true;
				return true;
			}
			else {
				return false;
			}	
		}
		if (whichhen == 4) {
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

	public void giantEggTimeThread() {
		giantEggTimeThread = new TimeThread(500/Level.numberOfLevel);
		giantEggTimeThread.start();
		giantEggDropingTimeThread = false;
	}

	public boolean randomGiantEggDrops(Random random1) {
		int r = random1.nextInt(100);
		if (giantEggDropingTimeThread == true) {
			giantEggTimeThread();
		}
		if (r< 25 && giantEggTimeThread.isAlive()==false) {
			giantEggDropingTimeThread = true;
			return true;
		}
		else {
			return false;
		}

	}

	public void giantEggTirDrops() {
		//			double hugeEggRadius = 8*(Math.sqrt(Math.pow(Ge.giantEggHeight/2 + Ge.giantEggHeight/8, 2)
		//					+ Math.pow(Ge.giantEggWidth/2, 2)))/(2*Math.PI);
		if(gE.paintCheck == true) {
			double centerX = gE.getGiantEggX() + 180;
			double centerY = gE.height/14 + 210;
			for (int i= 0; i < gE.numberOfTirsInCircle; i++) {
				rand2 = new Random();
				boolean posibility = randomGiantEggDrops(rand2);
				if (posibility == true) {
					double degree = (2*(1+i)*Math.PI)/gE.numberOfTirsInCircle;
					gE.giantEggTirsArray.add(new GiantEggTir(centerX ,
							centerY ,7*Math.cos(degree), -7*Math.sin(degree)));
				}
			}
		}
	}	
}

