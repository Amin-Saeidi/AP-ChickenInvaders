package levels;

import java.awt.Graphics2D;
import java.net.URL;

import IO.User;
import Swing.MainPanel;
import engine.Animatable;
import game.object.Rocket;

public class Level implements Animatable{

	public int health;
	public int numOfBombs;
	public int coins;
	public int score;
	public int chickens;
	public User pickeduser;
	public static boolean loaded=false;
	public boolean save=false;
	public static int  numberOfLevel=1;
	public int numberOfWave;
	public Rocket rocket;
	public Level1 level1;
	public Level2 level2;
	public Level3 level3;
	public Level4 level4;
	public boolean level2And3Create = true;
	public boolean level3Create= true;
	public boolean level4Create = true;
	private MainPanel panel;
	public boolean reflected;
	public boolean addNewClassWithReflection;
	public String classLoaderPath;
	public String reflectedPath;
	public URL[] classLoaderURLPath;
	



	public Level(MainPanel panel,User user) {
		this.panel = panel;
		this.pickeduser = user;
		level1 = new Level1(this,panel);
		level2 = new Level2(this,panel);
		level3 = new Level3(this,panel);
		level4 = new Level4(panel, this);
	}

	public Level(User user,boolean fakeBoolean) {
		this.pickeduser=user;
		level1 = new Level1(this);
		//		level2 = new Level2(this);
		//		level3 = new Level3(this);
		//		level4 = new Level4();
	}
	public Level(User user) {
		this.pickeduser = user;
		numberOfLevel = pickeduser.numberOfLevel;
		this.numberOfWave = pickeduser.numberOfWave;
		score=pickeduser.score;
		coins = pickeduser.coins;
		numOfBombs= pickeduser.numOfBombs;
		health=pickeduser.health;
		System.out.println(pickeduser.numberOfLevel + "new LEVEL" + coins + numOfBombs + health+score);
		System.out.println(this.numberOfWave);
		loaded=true;
		level1 = new Level1(this);
		//		level2 = new Level2(this);
		//		level3 = new Level3(this);
		//		level4 = new Level4();
	}


	public Level(int health, int numOfBombs, int coins, int score,User user) {
		this.pickeduser=user;
		level1 = new Level1(this,panel);
		level2 = new Level2(this,panel);
		level3 = new Level3(this,panel);
		level4 = new Level4(panel,this);
		this.health = health;
		this.numOfBombs = numOfBombs;
		this.coins = coins;
		this.score = score;
	}




	@Override
	public void paint(Graphics2D g2) {
		if(reflected ==true) {
			if(level1!= null) {
				level1.reflectMethod(classLoaderURLPath , reflectedPath);
				level1.reflected =true;
//				reflected = false;
			}
			if(level2!= null) {
				level2.reflectMethod(classLoaderURLPath , reflectedPath);
				level2.reflected =true;
//				reflected = false;

			}
			if(level3!= null) {
				level3.reflectMethod(classLoaderURLPath , reflectedPath);
				level3.reflected =true;
//				reflected = false;

			}
			if(level4!= null) {
				level4.reflectMethod(classLoaderURLPath , reflectedPath);
				level4.reflected =true;
//				reflected = false;
			}

		}

		if(numberOfLevel ==1) {
			level1.paintLoop(g2);
		}
		if(numberOfLevel ==2) {
			if (level2And3Create == true) {
				level2 = new Level2(this);
				level3 = new Level3(this);
				level2And3Create = false;
			}
			if(level2!= null) {
				level2.paintLoop(g2);
			}
		}
		if(numberOfLevel ==3) {
			if(loaded == true) {
				if (level3Create == true) {
					level3 = new Level3(this);
					level3Create = false;
				}
			}
			if (level4Create == true) {
				level4 = new Level4(this);
				level4Create = false;
			}
			if(level3!= null) {
				level3.paintLoop(g2);
			}
		}
		if(numberOfLevel ==4) {
			if(loaded == true) {
				if (level4Create == true) {
					level4 = new Level4(this);
					level4Create = false;
				}
			}
			if(level4!= null) {
				level4.paintLoop(g2);
			}
		}
	}

	public void saving() {
		if(numberOfLevel ==1) {
			level1.saveContect();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.saveContect();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.saveContect();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.saveContect();
			}
		}

	}


	@Override
	public void move() {
		if(numberOfLevel ==1) {
			level1.moveLoop();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.moveLoop();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.moveLoop();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.moveLoop();
			}
		}
	}

	public void shelik() {
		if(numberOfLevel ==1) {
			level1.shelik();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.shelik();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.shelik();
			}

		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.shelik();
			}

		}

	}
	public Rocket getRocket() {
		if(numberOfLevel ==1) {
			rocket = level1.getRocket();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				rocket = level2.getRocket();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				rocket = level3.getRocket();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				rocket = level4.getRocket();
			}
		}
		return rocket;
	}


	public void increasing() {
		if(numberOfLevel ==1) {
			level1.increasing();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.increasing();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.increasing();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.increasing();
			}
		}

	}
	public void trueDecreasing() {
		if(numberOfLevel ==1) {
			level1.trueDecreasing();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.trueDecreasing();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.trueDecreasing();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.trueDecreasing();
			}
		}

	}
	public void falseDecreasing() {
		if(numberOfLevel ==1) {
			level1.falseDecreasing();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.falseDecreasing();
			}	
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.falseDecreasing();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.falseDecreasing();
			}
		}

	}



	public void shelikBomb() {
		if(numberOfLevel ==1) {
			level1.shelikBomb();
		}
		if(numberOfLevel ==2) {
			if(level2!= null) {
				level2.shelikBomb();
			}
		}
		if(numberOfLevel ==3) {
			if(level3!= null) {
				level3.shelikBomb();
			}
		}
		if(numberOfLevel ==4) {
			if(level4!= null) {
				level4.shelikBomb();
			}
		}
	}

}
