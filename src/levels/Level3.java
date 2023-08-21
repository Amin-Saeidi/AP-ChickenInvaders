package levels;

import java.awt.Graphics2D;
import java.net.URL;
import java.util.Random;

import IO.User;
import Swing.MainPanel;
import engine.Game;
import game.object.EndOfLevelMessage;
import game.object.Rocket;
import game.objects.hens.SingleHen;

public class Level3 implements LevelSample {

	public int health;
	public int numOfBombs;
	public int coins;
	public int score;
	public User thisGameUser;
	public Game wave1;
	public Game wave2;
	public Game wave3;
	public Game wave4;
	public Game finalWave;
	private int numberOfWave=1;
	private Rocket rocket;
	private Level level;
	private EndOfLevelMessage endOfLevelMessage;
	@SuppressWarnings("unused")
	private MainPanel panel;
	///reflection Fields
	public boolean reflected=false;
	public boolean createNew;
	public String classLoaderPath;
	public String reflectedPath;
	private int counter =0;
	public URL[] classLoaderURLPath;



	public Level3(Level level,MainPanel panel) {
		this.level = level;
		this.panel = panel;
		wave1 = new Game(1200, 700, "rectangle", "2", "3", "1",panel);
		wave2 = new Game(1200, 700, "circular", "2", "3", "2",panel);
		wave3 = new Game(1200, 700, "rectangle", "3", "3", "1",panel);
		wave4 = new Game(1200, 700, "circular", "3", "2", "3",panel);
		finalWave = new Game(1200, 700, "GiantEgg",panel);
		endOfLevelMessage = new  EndOfLevelMessage(250, 0, 600, 200);

	}

	public Level3(Level level) {
		this.level = level;
		wave1 = new Game(1200, 700, "rectangle", "2", "3", "1");
		wave2 = new Game(1200, 700, "circular", "2", "3", "2");
		wave3 = new Game(1200, 700, "rectangle", "3", "3", "1");
		wave4 = new Game(1200, 700, "circular", "3", "2", "3");
		finalWave = new Game(1200, 700, "GiantEgg");
		endOfLevelMessage = new  EndOfLevelMessage(250, 0, 600, 200);

	}

	public void endOfWave1(){
		if(Level.loaded != true) {
			if(counter  == 0) {
				wave1.propertiesBar.setHealth(level.level2.finalWave.propertiesBar.getHealth());
				wave1.propertiesBar.setCoins(0);
				wave1.propertiesBar.setChickens(level.level2.finalWave.propertiesBar.getChickens());
				wave1.temperatureBar.setScore(level.level2.finalWave.temperatureBar.getScore()+level.level2.finalWave.propertiesBar.getCoins());
				wave1.temperatureBar.maximumTemp = level.level2.finalWave.temperatureBar.maximumTemp;
				wave1.propertiesBar.setNumOfBombs(level.level2.finalWave.propertiesBar.getNumOfBombs()+1);
				counter+=1;
			}
			int cul = wave1.rectangleHens.cul;
			int row = wave1.rectangleHens.row;
			int numberOfHens = row*cul;
			int numberOfDeadHens =0;
			for (int i=0 ; i< row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					if (wave1.rectangleHens.rectangleHens[i][j].paintCheck == false) {
						numberOfDeadHens +=1;
					}
				}
			}
			if (numberOfDeadHens == numberOfHens) {
				numberOfWave =2;
				wave2.temperatureBar.setScore(wave1.temperatureBar.getScore());
				wave2.temperatureBar.maximumTemp=(wave1.temperatureBar.maximumTemp);
				wave2.propertiesBar.setHealth(wave1.propertiesBar.getHealth());
				wave2.propertiesBar.setChickens((wave1.propertiesBar.getChickens()));
				wave2.propertiesBar.setCoins(wave1.propertiesBar.getCoins());
				wave2.propertiesBar.setNumOfBombs(wave1.propertiesBar.getNumOfBombs());

			}
		}
	}
	public void endOfFinalWave() {
		if(Level.loaded != true) {
			if (finalWave.giantEgg.paintCheck == false) {
				endOfLevelMessage.showMessage =true;
				if (endOfLevelMessage.getMessageY()>800) {
					Level.numberOfLevel  = 4;
				}
			}
		}
	}

	public void endOfWave4() {
		if(Level.loaded != true) {
			double numberOfHens=wave4.circleGroup.numberOfHensInLayer_one + wave4.circleGroup.numberOfHensInLayer_two
					+ wave4.circleGroup.numberOfHensInLayer_three;
			double numberOfDeadHens =0;
			for (SingleHen singlehen : wave4.circleGroup.circularHenArray1) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			for (SingleHen singlehen : wave4.circleGroup.circularHenArray2) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			for (SingleHen singlehen : wave4.circleGroup.circularHenArray3) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			if (numberOfDeadHens == numberOfHens) {
				numberOfWave =5;
				finalWave.temperatureBar.setScore(wave4.temperatureBar.getScore());
				finalWave.temperatureBar.maximumTemp=(wave4.temperatureBar.maximumTemp);
				finalWave.propertiesBar.setHealth(wave4.propertiesBar.getHealth());
				finalWave.propertiesBar.setChickens((wave4.propertiesBar.getChickens()));
				finalWave.propertiesBar.setCoins(wave4.propertiesBar.getCoins());
				finalWave.propertiesBar.setNumOfBombs(wave4.propertiesBar.getNumOfBombs());
				System.out.println("FINAL WAVE");
			}
		}
	}

	public void endOfWave3() {
		if (Level.loaded != true) {
			int cul = wave3.rectangleHens.cul;
			int row = wave3.rectangleHens.row;
			int numberOfHens = row*cul;
			int numberOfDeadHens =0;
			for (int i=0 ; i< row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					if (wave3.rectangleHens.rectangleHens[i][j].paintCheck == false) {
						numberOfDeadHens +=1;
					}
				}
			}
			if (numberOfDeadHens == numberOfHens) {
				numberOfWave = 4;
				wave4.temperatureBar.setScore(wave3.temperatureBar.getScore());
				wave4.temperatureBar.maximumTemp=(wave3.temperatureBar.maximumTemp);
				wave4.propertiesBar.setHealth(wave3.propertiesBar.getHealth());
				wave4.propertiesBar.setChickens((wave3.propertiesBar.getChickens()));
				wave4.propertiesBar.setCoins(wave3.propertiesBar.getCoins());
				wave4.propertiesBar.setNumOfBombs(wave3.propertiesBar.getNumOfBombs());

			}
		}
	}

	public void endOfWave2() {
		if(Level.loaded != true) {
			double numberOfHens=wave2.circleGroup.numberOfHensInLayer_one + wave2.circleGroup.numberOfHensInLayer_two
					+ wave2.circleGroup.numberOfHensInLayer_three;
			double numberOfDeadHens =0;
			for (SingleHen singlehen : wave2.circleGroup.circularHenArray1) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			for (SingleHen singlehen : wave2.circleGroup.circularHenArray2) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			for (SingleHen singlehen : wave2.circleGroup.circularHenArray3) {
				if (singlehen.paintCheck == false) {
					numberOfDeadHens +=1;
				}
			}
			if (numberOfDeadHens == numberOfHens) {
				numberOfWave =3;
				wave3.temperatureBar.setScore(wave2.temperatureBar.getScore());
				wave3.temperatureBar.maximumTemp=(wave2.temperatureBar.maximumTemp);
				wave3.propertiesBar.setHealth(wave2.propertiesBar.getHealth());
				wave3.propertiesBar.setChickens((wave2.propertiesBar.getChickens()));
				wave3.propertiesBar.setCoins(wave2.propertiesBar.getCoins());
				wave3.propertiesBar.setNumOfBombs(wave2.propertiesBar.getNumOfBombs());

			}
		}
	}

	@Override
	public void paintLoop(Graphics2D g2) {
		//		gameOverCheck();
		//		saveContect();
		if(Level.loaded ==true) {
			loadContent();
		}
		if (numberOfWave ==1) {
			wave1.paint(g2);
			endOfWave1();
		}
		if (numberOfWave ==2) {
			wave2.paint(g2);
			endOfWave2();
		}
		if (numberOfWave ==3) {			
			wave3.paint(g2);
			endOfWave3();
		}
		if (numberOfWave ==4) {
			wave4.paint(g2);
			endOfWave4();
		}
		if (numberOfWave ==5) {
			finalWave.paint(g2);
			endOfFinalWave();
		}
		if (endOfLevelMessage.showMessage == true) {
			endOfLevelMessage.paint(g2);
		}
		reflect();
	}

	public void gameOverCheck() {
		if (numberOfWave ==1) {
			if(wave1.gameOverCheck== true) {
				level.save = true;
			}
		}
		if (numberOfWave ==2) {
			if(wave2.gameOverCheck== true) {
				level.save = true;
			}
		}
		if (numberOfWave ==3) {			
			if(wave3.gameOverCheck== true) {
				level.save = true;
			}
		}
		if (numberOfWave ==4) {
			if(wave4.gameOverCheck== true) {
				level.save = true;
			}
		}
		if (numberOfWave ==5) {
			if(finalWave.gameOverCheck== true) {
				level.save = true;
			}
		}
	}

	public void loadContent() {
		if(Level.loaded == true) {
			if(level.numberOfWave==1) {
				this.numberOfWave = 1;
				wave1.temperatureBar.setScore(level.score);
				wave1.propertiesBar.setHealth(level.health);
				wave1.propertiesBar.setChickens(0);
				wave1.propertiesBar.setCoins(level.coins);
				wave1.propertiesBar.setNumOfBombs(level.numOfBombs);
				counter++;
				endOfWave1();
				Level.loaded = false;
			}
			else if (level.numberOfWave ==2 ) {
				this.numberOfWave = 2;
				wave2.temperatureBar.setScore(level.score);
				wave2.propertiesBar.setHealth(level.health);
				wave2.propertiesBar.setChickens(0);
				wave2.propertiesBar.setCoins(level.coins);
				wave2.propertiesBar.setNumOfBombs(level.numOfBombs);
				endOfWave2();
				Level.loaded = false;
			}

			else if (level.numberOfWave ==3 ) {
				this.numberOfWave = 3;
				wave3.temperatureBar.setScore(level.score);
				wave3.propertiesBar.setHealth(level.health);
				wave3.propertiesBar.setChickens(0);
				wave3.propertiesBar.setCoins(level.coins);
				wave3.propertiesBar.setNumOfBombs(level.numOfBombs);
				endOfWave3();
				Level.loaded = false;
			}
			else if (level.numberOfWave ==4 ) {
				this.numberOfWave = 4;
				wave4.temperatureBar.setScore(level.score);
				wave4.propertiesBar.setHealth(level.health);
				wave4.propertiesBar.setChickens(0);
				wave4.propertiesBar.setCoins(level.coins);
				wave4.propertiesBar.setNumOfBombs(level.numOfBombs);
				endOfWave4();
				Level.loaded = false;
			}
			else if (level.numberOfWave ==5 ) {
				this.numberOfWave = 5;
				finalWave.temperatureBar.setScore(level.score);
				finalWave.propertiesBar.setHealth(level.health);
				finalWave.propertiesBar.setChickens(0);
				finalWave.propertiesBar.setCoins(level.coins);
				finalWave.propertiesBar.setNumOfBombs(level.numOfBombs);
				endOfFinalWave();
				Level.loaded = false;

			}

		}
	}

	public void saveContect() {
		if(level.save = true) {
			if (numberOfWave ==1) {
				level.health=wave1.propertiesBar.getHealth();
				level.coins=wave1.propertiesBar.getCoins();
				level.chickens=wave1.propertiesBar.getChickens();
				level.score=wave1.temperatureBar.getScore();
				level.numOfBombs=wave1.propertiesBar.getNumOfBombs();
				level.numberOfWave =1; 
			}
			if (numberOfWave ==2) {
				level.health=wave2.propertiesBar.getHealth();
				level.coins=wave2.propertiesBar.getCoins();
				level.chickens=wave2.propertiesBar.getChickens();
				level.score=wave2.temperatureBar.getScore();
				level.numOfBombs=wave2.propertiesBar.getNumOfBombs();
				level.numberOfWave =2;
			}
			if (numberOfWave ==3) {			
				level.health=wave3.propertiesBar.getHealth();
				level.coins=wave3.propertiesBar.getCoins();
				level.chickens=wave3.propertiesBar.getChickens();
				level.score=wave3.temperatureBar.getScore();
				level.numOfBombs=wave3.propertiesBar.getNumOfBombs();
				level.numberOfWave =3;
			}
			if (numberOfWave ==4) {
				level.health=wave4.propertiesBar.getHealth();
				level.coins=wave4.propertiesBar.getCoins();
				level.chickens=wave4.propertiesBar.getChickens();
				level.score=wave4.temperatureBar.getScore();
				level.numOfBombs=wave4.propertiesBar.getNumOfBombs();
				level.numberOfWave =4;
			}
			if (numberOfWave ==5) {
				level.health=finalWave.propertiesBar.getHealth();
				level.coins=finalWave.propertiesBar.getCoins();
				level.chickens=finalWave.propertiesBar.getChickens();
				level.score=finalWave.temperatureBar.getScore();
				level.numOfBombs=finalWave.propertiesBar.getNumOfBombs();
				level.numberOfWave =5;
			}
		}

	}



	public void reflect() {
		if(reflected == true) {
			if (numberOfWave ==1) {
				Random random = new Random();
				int r = random.nextInt(100);
				if(r<100) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(5<r && r<80) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(15<r && r<80) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(25<r && r<80) {
					if(reflectedPath.contains("Egg")) {
						finalWave.classLoaderURLPath =classLoaderURLPath;
						finalWave.reflectedPath = reflectedPath;
						finalWave.reflected = true;
						finalWave.addNewClassWithReflection = true;
					}
				}
			}
			if (numberOfWave ==2) {
				Random random2 = new Random();
				int r2 = random2.nextInt(100);
				if(5<r2 && r2<80) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(15<r2 && r2<80) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(25<r2 && r2<80) {
					if(reflectedPath.contains("Egg")) {
						finalWave.classLoaderURLPath =classLoaderURLPath;
						finalWave.reflectedPath = reflectedPath;
						finalWave.reflected = true;
						finalWave.addNewClassWithReflection = true;
					}
				}
			}
			if (numberOfWave ==3) {
				Random random3 = new Random();
				int r3 = random3.nextInt(100);
				if(15<r3 && r3<80) {
					if(reflectedPath.contains("Hen")) {
						wave2.classLoaderURLPath =classLoaderURLPath;
						wave2.reflectedPath = reflectedPath;
						wave2.reflected = true;
						wave2.addNewClassWithReflection = true;
					}
				}
				if(25<r3 && r3<80) {
					if(reflectedPath.contains("Egg")) {
						finalWave.classLoaderURLPath =classLoaderURLPath;
						finalWave.reflectedPath = reflectedPath;
						finalWave.reflected = true;
						finalWave.addNewClassWithReflection = true;
					}
				}
			}
			if (numberOfWave ==4) {
				Random random4 = new Random();
				int r4 = random4.nextInt(100);
				if(25<r4 && r4<80) {
					if(reflectedPath.contains("Egg")) {
						finalWave.classLoaderURLPath =classLoaderURLPath;
						finalWave.reflectedPath = reflectedPath;
						finalWave.reflected = true;
						finalWave.addNewClassWithReflection = true;
					}
				}
			}
			reflected = false;	
		}
	}

	@Override
	public void moveLoop() {
		if (numberOfWave ==1) {
			wave1.move();
		}
		if (numberOfWave ==2) {
			wave2.move();}	
		if (numberOfWave ==3) {
			wave3.move();}	
		if (numberOfWave ==4) {
			wave4.move();}	
		if (numberOfWave ==5) {
			finalWave.move();}	
	}

	public Rocket getRocket() {
		if (numberOfWave ==1) {
			rocket = wave1.getRocket();
		}
		if (numberOfWave ==2) {
			rocket = wave2.getRocket();			
		}
		if (numberOfWave ==3) {
			rocket = wave3.getRocket();			
		}
		if (numberOfWave ==4) {
			rocket = wave4.getRocket();			
		}
		if (numberOfWave ==5) {
			rocket = finalWave.getRocket();			
		}
		return rocket;
	}

	public void shelik() {
		if (numberOfWave ==1) {
			wave1.shelik();
		}
		if (numberOfWave ==2) {
			wave2.shelik();			
		}
		if (numberOfWave ==3) {
			wave3.shelik();			
		}
		if (numberOfWave ==4) {
			wave4.shelik();			
		}
		if (numberOfWave ==5) {
			finalWave.shelik();			
		}
	}

	public void increasing() {
		if (numberOfWave ==1) {
			wave1.increasingTemperatur();
		}
		if (numberOfWave ==2) {
			wave2.increasingTemperatur();			
		}
		if (numberOfWave ==3) {
			wave3.increasingTemperatur();			
		}
		if (numberOfWave ==4) {
			wave4.increasingTemperatur();			
		}
		if (numberOfWave ==5) {
			finalWave.increasingTemperatur();			
		}
	}

	public void trueDecreasing() {
		if (numberOfWave ==1) {
			wave1.decreasingTemperature = true;
		}
		if (numberOfWave ==2) {
			wave2.decreasingTemperature = true;
		}
		if (numberOfWave ==3) {
			wave3.decreasingTemperature = true;
		}
		if (numberOfWave ==4) {
			wave4.decreasingTemperature = true;
		}
		if (numberOfWave ==5) {
			finalWave.decreasingTemperature = true;
		}
	}

	public void falseDecreasing() {
		if (numberOfWave ==1) {
			wave1.decreasingTemperature = false;
		}
		if (numberOfWave ==2) {
			wave2.decreasingTemperature = false;
		}	
		if (numberOfWave ==3) {
			wave3.decreasingTemperature = false;
		}	
		if (numberOfWave ==4) {
			wave4.decreasingTemperature = false;
		}	
		if (numberOfWave ==5) {
			finalWave.decreasingTemperature = false;
		}	
	}

	public void shelikBomb() {
		if (numberOfWave ==1) {
			wave1.shelikBomb();
		}
		if (numberOfWave ==2) {
			wave2.shelikBomb();
		}	
		if (numberOfWave ==3) {
			wave3.shelikBomb();
		}	
		if (numberOfWave ==4) {
			wave4.shelikBomb();
		}	
		if (numberOfWave ==5) {
			finalWave.shelikBomb();
		}	
	}
	public void reflectMethod(URL[] classLoaderURLPath, String reflectedPath) {
		this.classLoaderURLPath = classLoaderURLPath;
		this.reflectedPath = reflectedPath;
		reflected = true;
	}



}
