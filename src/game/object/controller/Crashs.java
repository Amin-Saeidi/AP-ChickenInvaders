package game.object.controller;

import game.object.PropertiesBar;
import game.object.Rocket;
import game.object.TemperatureBar;
import game.object.Tir;
import game.objects.hens.CircleGroup;
import game.objects.hens.RectangleGroup;
import game.objects.hens.SingleHen;

public class Crashs {
	private TemperatureBar Tp;
	private RectangleGroup Rg ;
	private CircleGroup Cg;
	private Rocket rocket;
	private PropertiesBar Pr;

	public Crashs(RectangleGroup rg, CircleGroup cg, Rocket rocket, PropertiesBar Pr , TemperatureBar Tp) {
		Rg = rg;
		Cg = cg;
		this.Pr = Pr;
		this.Tp = Tp;
		this.rocket = rocket;
	}

	public void rectangularCrash() {
		int cul = Rg.cul;
		int row = Rg.row;
		for (int i=0 ; i < row ; i++ ) {
			for (int j=0 ; j<cul ; j++) {
				if(rocket !=null) {
					if (Rg.rectangleHens[i][j].powerUps2 != null) {
						if ((rocket.getX()< (Rg.rectangleHens[i][j].powerUps2.getPowerUp2sX()+30)) && 
								(rocket.getX()> (Rg.rectangleHens[i][j].powerUps2.getPowerUp2sX()-30))) {
							if ((rocket.getY()< (Rg.rectangleHens[i][j].powerUps2.getPowerUps2Y()+30)) && 
									(rocket.getY()> (Rg.rectangleHens[i][j].powerUps2.getPowerUps2Y()-30))) {
								Rg.rectangleHens[i][j].powerUps2.paintCheck = false;
								Tir.whichTir = Rg.rectangleHens[i][j].powerUps2.whichPowerUps;

							}

						}

					}
					if (Rg.rectangleHens[i][j].powerUps1 != null) {
						if ((rocket.getX()< (Rg.rectangleHens[i][j].powerUps1.getPowerUp1sX()+30)) && 
								(rocket.getX()> (Rg.rectangleHens[i][j].powerUps1.getPowerUp1sX()-30))) {
							if ((rocket.getY()< (Rg.rectangleHens[i][j].powerUps1.getPowerUps1Y()+30)) && 
									(rocket.getY()> (Rg.rectangleHens[i][j].powerUps1.getPowerUps1Y()-30))) {
								Rg.rectangleHens[i][j].powerUps1 = null;
								Tp.maximumTemp +=5;
								System.out.println("POWER UPS1 CRASH");
							}

						}

					}
					if (Rg.rectangleHens[i][j].coin != null) {
						if ((rocket.getX()< (Rg.rectangleHens[i][j].coin.getCoinX()+30)) && 
								(rocket.getX()> (Rg.rectangleHens[i][j].coin.getCoinX()-30))) {
							if ((rocket.getY()< (Rg.rectangleHens[i][j].coin.getCoinY()+30)) && 
									(rocket.getY()> (Rg.rectangleHens[i][j].coin.getCoinY()-30))) {
								Rg.rectangleHens[i][j].coin = null;
								Pr.setCoins(Pr.getCoins() + 1);	
								System.out.println("rect coin");
							}

						}

					}

				}
			}
		}
	}

	public void circularCrash() {
		if (rocket != null) {
			for (SingleHen singlehen : Cg.circularHenArray1) {
				if (singlehen.powerUps2 != null) {
					if ((rocket.getX()< (singlehen.powerUps2.getPowerUp2sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps2.getPowerUp2sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps2.getPowerUps2Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps2.getPowerUps2Y()-30))) {
							singlehen.powerUps2.paintCheck = false;
							Tir.whichTir = singlehen.powerUps2.whichPowerUps;

						}

					}

				}
				if (singlehen.powerUps1 != null) {
					if ((rocket.getX()< (singlehen.powerUps1.getPowerUp1sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps1.getPowerUp1sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps1.getPowerUps1Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps1.getPowerUps1Y()-30))) {
							singlehen.powerUps1 = null;
							Tp.maximumTemp +=5;
							System.out.println("POWER UPS1 CRASH");
						}

					}

				}
				if (singlehen.coin != null) {
					if ((rocket.getX()< (singlehen.coin.getCoinX()+30)) && 
							(rocket.getX()> (singlehen.coin.getCoinX()-30))) {
						if ((rocket.getY()< (singlehen.coin.getCoinY()+30)) && 
								(rocket.getY()> (singlehen.coin.getCoinY()-30))) {
							singlehen.coin = null;
							Pr.setCoins(Pr.getCoins() + 1);
						}
					}
				}
			}
			for (SingleHen singlehen : Cg.circularHenArray2) {
				if (singlehen.powerUps2 != null) {
					if ((rocket.getX()< (singlehen.powerUps2.getPowerUp2sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps2.getPowerUp2sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps2.getPowerUps2Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps2.getPowerUps2Y()-30))) {
							singlehen.powerUps2.paintCheck = false;
							Tir.whichTir = singlehen.powerUps2.whichPowerUps;

						}

					}

				}
				if (singlehen.powerUps1 != null) {
					if ((rocket.getX()< (singlehen.powerUps1.getPowerUp1sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps1.getPowerUp1sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps1.getPowerUps1Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps1.getPowerUps1Y()-30))) {
							singlehen.powerUps1 = null;
							Tp.maximumTemp +=5;
							System.out.println("POWER UPS1 CRASH");
						}

					}

				}
				if (singlehen.coin != null) {
					if ((rocket.getX()< (singlehen.coin.getCoinX()+30)) && 
							(rocket.getX()> (singlehen.coin.getCoinX()-30))) {
						if ((rocket.getY()< (singlehen.coin.getCoinY()+30)) && 
								(rocket.getY()> (singlehen.coin.getCoinY()-30))) {
							singlehen.coin = null;
							Pr.setCoins(Pr.getCoins() + 1);

						}
					}
				}

			}
			for (SingleHen singlehen : Cg.circularHenArray3) {
				if (singlehen.powerUps2 != null) {
					if ((rocket.getX()< (singlehen.powerUps2.getPowerUp2sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps2.getPowerUp2sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps2.getPowerUps2Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps2.getPowerUps2Y()-30))) {
							singlehen.powerUps2.paintCheck = false;
							Tir.whichTir = singlehen.powerUps2.whichPowerUps;
						}

					}

				}
				if (singlehen.powerUps1 != null) {
					if ((rocket.getX()< (singlehen.powerUps1.getPowerUp1sX()+30)) && 
							(rocket.getX()> (singlehen.powerUps1.getPowerUp1sX()-30))) {
						if ((rocket.getY()< (singlehen.powerUps1.getPowerUps1Y()+30)) && 
								(rocket.getY()> (singlehen.powerUps1.getPowerUps1Y()-30))) {
							singlehen.powerUps1 = null;
							Tp.maximumTemp +=5;
							System.out.println("POWER UPS1 CRASH");
						}

					}

				}
				if (singlehen.coin != null) {
					if ((rocket.getX()< (singlehen.coin.getCoinX()+30)) && 
							(rocket.getX()> (singlehen.coin.getCoinX()-30))) {
						if ((rocket.getY()< (singlehen.coin.getCoinY()+30)) && 
								(rocket.getY()> (singlehen.coin.getCoinY()-30))) {
							singlehen.coin = null;
							Pr.setCoins(Pr.getCoins() + 1);
						}
					}
				}

			}
		}
	}



}
