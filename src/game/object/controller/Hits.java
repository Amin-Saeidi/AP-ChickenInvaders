package game.object.controller;

import java.util.ArrayList;
import engine.Game;
import game.object.Bomb;
import game.object.Tir;
import game.objects.hens.CircleGroup;
import game.objects.hens.GiantEgg;
import game.objects.hens.RectangleGroup;
import game.objects.hens.SingleHen;
import soundation.SoundEffect;

public class Hits {
	private Game game;
	private RectangleGroup Rg;
	private CircleGroup Cg;
	private GiantEgg Ge;
	private ArrayList<Tir> tirs;
	private ArrayList<Bomb> bombs;
	private String henDieSound;
	private String bombExplosionSound;
	private SoundEffect henDieSoundEffect;
	private SoundEffect bombExplosionSoundEffect;
	

	public Hits(RectangleGroup Rg ,CircleGroup Cg ,GiantEgg Ge ,ArrayList<Tir> tirs,ArrayList<Bomb> bombs ,Game game ) {
		this.game = game;
		this.Rg = Rg ;
		this.Cg = Cg;
		this.Ge=Ge;
		this.tirs =tirs ;
		this.bombs = bombs;
		henDieSoundEffect = new SoundEffect();
		bombExplosionSoundEffect = new SoundEffect();
		henDieSound = ".//resources//henDie.wav";
		bombExplosionSound = ".//resources//bombExplosion.wav";
		henDieSoundEffect.setFile(henDieSound);
		bombExplosionSoundEffect.setFile(bombExplosionSound);
	}

	public void hitCircularArray(ArrayList<SingleHen> circularHenArray ){
		for(SingleHen singleHen : circularHenArray){
			if (singleHen.paintCheck == true) {
				singleHen.paintCheck = false;
				int point =Integer.valueOf(singleHen.whichHen);
				game.temperatureBar.setScore(game.temperatureBar.getScore() + point);
			}
		}
	}
	public void hitBombCircular() {
		for(int z=0 ; z < bombs.size() ; z++) {
			if(bombs.get(z).getY() <200) {
				bombs.remove(z);
				bombExplosionSoundEffect.play();
				hitCircularArray(Cg.circularHenArray1);
				hitCircularArray(Cg.circularHenArray2);
				hitCircularArray(Cg.circularHenArray3);
			}
		}
	}

	public void hitBombRectangle(){
		try {
			for(int z=0 ; z < bombs.size() ; z++) {
				if(bombs.get(z).getY() <200) {
					bombs.remove(z);
					bombExplosionSoundEffect.play();
					for (int i=0 ; i<Rg.row ; i++) {
						for (int j=0 ;j<Rg.cul ; j++ ) {
							if (Rg.rectangleHens[i][j].paintCheck == true) {
								Rg.rectangleHens[i][j].paintCheck = false;
								int point =Integer.valueOf(Rg.rectangleHens[i][j].whichHen);
								game.temperatureBar.setScore(game.temperatureBar.getScore() + point);
							}
						}
					}
				}
			}
		}catch(Exception ex) {
			hitBombRectangle();
		}
	}


	public void hitCoinsRectangle(){
		for(int z=0 ; z < tirs.size() ; z++) {
			for (int i=0 ; i<Rg.row ; i++) {
				for (int j=0 ;j<Rg.cul ; j++ ) {
					if (Rg.rectangleHens[i][j].coin != null) {
						if((tirs.get(z).getX()< (Rg.rectangleHens[i][j].coin.getCoinX()+30)) && 
								((tirs.get(z).getX()> (Rg.rectangleHens[i][j].coin.getCoinX()-30))))
						{
							if (((tirs.get(z).getX()< (Rg.rectangleHens[i][j].coin.getCoinY()+30)) && 
									((tirs.get(z).getX()> (Rg.rectangleHens[i][j].coin.getCoinY()-30))))) {
								System.out.println("rect coin destroyed");
								Rg.rectangleHens[i][j].coin.paintCheck = false;
								Rg.rectangleHens[i][j].coin = null;
								tirs.remove(z);
								break;
							}
						}
					}

				}
			}
		}
	}
	public void hitCoinsCircular() {
		hitCoinsCircle(Cg.circularHenArray1);
		hitCoinsCircle(Cg.circularHenArray3);
		hitCoinsCircle(Cg.circularHenArray3);		
	}

	public void hitCoinsCircle(ArrayList<SingleHen> circularHenList) {
		for(int i=0 ; i<tirs.size() ; i++) 
		{
			for (SingleHen singlehen : circularHenList) {
				if (singlehen.coin != null) {
					if((tirs.get(i).getX()< (singlehen.coin.getCoinX()+30)) && (tirs.get(i).getX()> singlehen.coin.getCoinX()-30))
					{
						if ((tirs.get(i).getY()< (singlehen.coin.getCoinY()+30)) && 
								(tirs.get(i).getY()> (singlehen.coin.getCoinY()-30))) {
							System.out.println("coin destroyed");
							singlehen.coin = null;
							tirs.remove(i);
							break;
						}
					}	
				}

			}
		}
	}

	public void hitrectangular() {
		try {
			for(int z=0 ; z< tirs.size() ; z++) 
			{	
				for (int i=0 ; i<Rg.row ; i++) {
					for (int j=0 ;j<Rg.cul ; j++ ) {
						if (Rg.rectangleHens[i][j].paintCheck == true) {

							if((tirs.get(z).getX()< (Rg.rectangleHens[i][j].getX()+30)) && 
									(tirs.get(z).getX()> (Rg.rectangleHens[i][j].getX()-30)))
							{
								if ((tirs.get(z).getY()< (Rg.rectangleHens[i][j].getY()+30)) && 
										(tirs.get(z).getY()> (Rg.rectangleHens[i][j].getY()-30))) {
									if (Tir.whichTir== "0") {
										Rg.rectangleHens[i][j].hitToDeath -= 0.5;
										Rg.deadHen(i, j);
										tirs.remove(z);
										if (Rg.rectangleHens[i][j].hit == true) {
											henDieSoundEffect.play();
											int point =Integer.valueOf(Rg.rectangleHens[i][j].whichHen);
											game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
										}
										break;
									}
									if (Tir.whichTir== "1") {
										Rg.rectangleHens[i][j].hitToDeath -= 1;
										Rg.deadHen(i, j);
										tirs.remove(z);
										if (Rg.rectangleHens[i][j].hit == true) {
											henDieSoundEffect.play();
											int point =Integer.valueOf(Rg.rectangleHens[i][j].whichHen);
											game.temperatureBar.setScore(game.temperatureBar.getScore() + point);
										}
										break;
									}
									if (Tir.whichTir== "2") {
										Rg.rectangleHens[i][j].hitToDeath -= 1.5;
										Rg.deadHen(i, j);
										tirs.remove(z);
										if (Rg.rectangleHens[i][j].hit == true) {
											henDieSoundEffect.play();
											int point =Integer.valueOf(Rg.rectangleHens[i][j].whichHen);
											game.temperatureBar.setScore(game.temperatureBar.getScore() + point);
										}
										break;
									}
									if (Tir.whichTir== "3") {
										Rg.rectangleHens[i][j].hitToDeath -= 2;
										Rg.deadHen(i, j);
										tirs.remove(z);
										if (Rg.rectangleHens[i][j].hit == true) {
											henDieSoundEffect.play();
											int point =Integer.valueOf(Rg.rectangleHens[i][j].whichHen);
											game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			hitrectangular();
		}
	}
	public void hitCircle() {
		hitCircular(Cg.circularHenArray1);
		hitCircular(Cg.circularHenArray2);
		hitCircular(Cg.circularHenArray3);
	}
	public void hitCircular(ArrayList<SingleHen> circularHenList) {
		for(int i=0 ; i<tirs.size() ; i++) {
			for (SingleHen singlehen : circularHenList) {
				if (singlehen.paintCheck == true) {
					if((tirs.get(i).getX()< (singlehen.getX()+30)) && (tirs.get(i).getX()> (singlehen.getX()-30)))
					{
						if ((tirs.get(i).getY()< (singlehen.getY()+30)) && 
								(tirs.get(i).getY()> (singlehen.getY()-30))) {
							if (Tir.whichTir== "0") {
								singlehen.hitToDeath -= 0.5;
								singlehen.dead();
								tirs.remove(i);
								if (singlehen.hit == true) {
									henDieSoundEffect.play();
									int point =Integer.valueOf(singlehen.whichHen);
									game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
								}
								break;	
							}
							if (Tir.whichTir== "1") {
								singlehen.hitToDeath -= 1;
								singlehen.dead();
								tirs.remove(i);
								if (singlehen.hit == true) {
									henDieSoundEffect.play();
									int point =Integer.valueOf(singlehen.whichHen);
									game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
								}
								break;	
							}
							if (Tir.whichTir== "2") {
								singlehen.hitToDeath -= 1.5;
								singlehen.dead();
								tirs.remove(i);
								if (singlehen.hit == true) {
									henDieSoundEffect.play();
									int point =Integer.valueOf(singlehen.whichHen);
									game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
								}
								break;	
							}
							if (Tir.whichTir== "3") {
								singlehen.hitToDeath -= 2;
								singlehen.dead();
								tirs.remove(i);
								if (singlehen.hit == true) {
									henDieSoundEffect.play();
									int point =Integer.valueOf(singlehen.whichHen);
									game.temperatureBar.setScore(game.temperatureBar.getScore() + point);	
								}
								break;	
							}
						}
					}	
				}
			}
		}
	}
	public void hitGiantEgg() {
		synchronized (tirs) {
			for (int i=0 ; i<tirs.size(); i++) {
				if(Ge.paintCheck ==true) {
					if ((tirs.get(i).getX() < (Ge.getGiantEggX() + 400)) && 
							(tirs.get(i).getX() > (Ge.getGiantEggX() - 40)))
					{
						if (((tirs.get(i).getY() < (Ge.getGiantEggY() + 375)) &&
								(tirs.get(i).getY() > (Ge.getGiantEggY() - 450))))
						{
							if(Tir.whichTir == "0") {
								Ge.giantEggHitToDeath += 1;
								tirs.get(i).paintcheck = false;
								tirs.remove(i);
								System.out.println("giantEggHitsByTirNo.1");
							}
							if(Tir.whichTir == "1") {
								Ge.giantEggHitToDeath += 2;
								tirs.remove(i);

							}
							if(Tir.whichTir == "2") {
								Ge.giantEggHitToDeath += 3;
								tirs.remove(i);

							}
							if(Tir.whichTir == "3") {
								Ge.giantEggHitToDeath += 4;
								tirs.remove(i);
							}


						}

					}
				}

			}

		}
	}
	public void hitBombGiantEgg() {
		for(int z=0 ; z < bombs.size() ; z++) {
			if(bombs.get(z).getY() <200) {
				Ge.giantEggHitToDeath+=50;
				bombExplosionSoundEffect.play();
				bombs.get(z).paintCheck = false;
				bombs.remove(z);
			}
		}
	}

}
