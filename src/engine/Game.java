package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Swing.MainPanel;
import game.object.Bomb;
import game.object.GameOverMessage;
import game.object.PropertiesBar;
import game.object.Rocket;
import game.object.TemperatureBar;
import game.object.Tir;
import game.object.controller.Crashs;
import game.object.controller.Drops;
import game.object.controller.Hits;
import game.objects.hens.CircleGroup;
import game.objects.hens.GiantEgg;
import game.objects.hens.RectangleGroup;
import game.objects.hens.SingleHen;
import game.objects.hens.SuicidalGroup;
import levels.Level;
import reflectionWorks.AddNewClassWithPaintMethod;
import reflectionWorks.Objects.PeripheralEgg;
import reflectionWorks.Objects.PeripheralHens;


public class Game implements Animatable {
	public MainPanel mainPanel;
	private int width;
	private int height;
	private int numOfBombs;
	private BufferedImage background;
	public Rocket rocket;
	public ArrayList<Rocket> rockets;
	public PropertiesBar propertiesBar;
	public TemperatureBar temperatureBar;
	private TimeThread rocketBackTimeThread;
	private TimeThread increasingTemperatureTimeThread;
	public boolean decreasingTemperature = false;
	private boolean shot = true;
	public ArrayList<Tir> tirs = new ArrayList<>();
	public ArrayList<Bomb> bombs = new ArrayList<>();
	private double heightOfImage = 65; 
	private double widthOfImage = 65;
	public String whichGroup;
	public CircleGroup circleGroup;
	public RectangleGroup rectangleHens;
	public GiantEgg giantEgg;
	public Hits hits; 
	public Drops drops;
	private Crashs crashs;
	private int rocketTemp;
	private boolean eggAndPlayerIncident;
	private boolean henAndPlayerIncident;
	private boolean isHittenStart = true;
	private String whichHens1;  
	private String whichHens2;
	private String whichHens3;
	public boolean paintCheck =  true;
	public boolean moveCheck = true;
	private GameOverMessage gameOverMessage;
	public boolean dropAddimision = true;
	private PeripheralEgg peripheralEgg;
	private PeripheralHens peripheralHens;
	private SuicidalGroup suicidalGroup;
	public  AddNewClassWithPaintMethod classWithPaintMethod;
	public Level level;
	public boolean gameOverCheck= false;
	//REFLECTION NEED
	public boolean reflected = false;
	public String classLoaderPath;
	public String reflectedPath;
	public boolean addNewClassWithReflection = false;
	public URL[] classLoaderURLPath;
	public ArrayList<URL[]> classLoaderURLPaths = new ArrayList<URL[]>();
	public ArrayList<String> reflectedPaths = new ArrayList<String>();
	
	
	public Game(int width, int height , String whichgroup , String whichHens1  , String whichHens2 ,String whichHens3,MainPanel panel) {
		this.whichHens1 = whichHens1;
		this.whichHens2 = whichHens2;
		this.whichHens3 = whichHens3;
		this.whichGroup = whichgroup;
		this.width = width;
		this.height = height;
		this.mainPanel = panel;
		initialize();
	}

	public Game(int width, int height, String whichgroup,MainPanel panel) {
		this.whichGroup = whichgroup;
		this.width = width;
		this.height = height;
		this.mainPanel =panel;
		initialize();
	}

	public Game(int width, int height , String whichgroup , String whichHens1  , String whichHens2 ,String whichHens3,Level level) {
		this.whichHens1 = whichHens1;
		this.whichHens2 = whichHens2;
		this.whichHens3 = whichHens3;
		this.whichGroup = whichgroup;
		this.level=level;
		this.width = width;
		this.height = height;
		initialize();
	}
	public Game(int width, int height , String whichgroup , String whichHens1  , String whichHens2 ,String whichHens3) {
		this.whichHens1 = whichHens1;
		this.whichHens2 = whichHens2;
		this.whichHens3 = whichHens3;
		this.whichGroup = whichgroup;
		this.width = width;
		this.height = height;
		initialize();
	}

	public Game(int width, int height, String whichgroup) {
		this.whichGroup = whichgroup;
		this.width = width;
		this.height = height;
		initialize();
	}

	private void initialize() {
		if (whichGroup == "GiantEgg") {
			giantEgg = new GiantEgg(width, height, width/3.3, -450, 400, 450);
		}
		if (whichGroup == "rectangle") {
			rectangleHens = new RectangleGroup(4,12,heightOfImage, widthOfImage , whichHens1,whichHens2);
		}
		if (whichGroup == "circular") {
			circleGroup	= new CircleGroup(6, 10, 14, heightOfImage, widthOfImage,-120,-120, 8 , 8 ,whichHens1,whichHens2,whichHens3);
		}
		if(whichGroup =="PeripheralEgg") {
			peripheralEgg = new PeripheralEgg();
		}
		if(whichGroup =="PeripheralHens") {
			try {
				peripheralHens = new PeripheralHens();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (whichGroup == "Suicidal") {
			suicidalGroup = new SuicidalGroup(5, whichHens1);
		}
		
		try {
			background = ImageIO.read(new File("resources/Background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameOverMessage = new GameOverMessage(400, 0, 0, 0);
		propertiesBar =  new PropertiesBar(width, height, 5, 250, 0, 0);
		temperatureBar = new TemperatureBar(rocketTemp, width, height);
		rocket = new Rocket(width / 2 - 50, height - 50);
		rockets = new ArrayList<>();
		hits = new Hits(rectangleHens, circleGroup,giantEgg ,tirs,bombs,this);
		drops = new Drops(rectangleHens, circleGroup,giantEgg,tirs);
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.drawImage(background, 0, 0, null);
		if (paintCheck == true) {
			eggHitRocketBack();
			//			henHitRocketBack();
			decreaseTemp();
			paintObjects(g2);
		}
	}


	private void gameOverMessageShow(Graphics2D g2) {
		if (propertiesBar.getHealth() ==0) {
			this.paintCheck = false;
			gameOverMessage.paint(g2);
			int counter = 0;
			if(counter == 0) {
				if (gameOverMessage.getMessageY()>900) {
					this.gameOverCheck= true;
					counter++;
				}
			}
		}	
	}

	private void paintObjects(Graphics2D g2) {
		crashs = new Crashs(rectangleHens, circleGroup, rocket ,propertiesBar ,temperatureBar);
		propertiesBar.paint(g2);
		temperatureBar.paint(g2);
		gameOverMessageShow(g2);

		if (rocket != null) {
			rocket.paint(g2);	
		}

		for (Rocket rocket : rockets) {
			if (rocket != null) {
				rocket.paint(g2);
			}
		}

		if (whichGroup == "rectangle") {
			rectangleHens.paint(g2);
			hits.hitrectangular();
			hits.hitCoinsRectangle();
			crashs.rectangularCrash();
			hits.hitBombRectangle();
		}

		if(whichGroup =="PeripheralEgg") {
			peripheralEgg.paint(g2);
		}
		if(whichGroup =="PeripheralHens") {
			peripheralHens.paint(g2);
		}
		if(whichGroup =="Suicidal") {
			suicidalGroup.paint(g2);
		}

		if (whichGroup == "circular") {
			circleGroup.paint(g2);
			hits.hitCircle();
			hits.hitCoinsCircular();
			crashs.circularCrash();
			hits.hitBombCircular();
		}

		if (whichGroup == "GiantEgg") {
			giantEgg.paint(g2);
			hits.hitGiantEgg();
			hits.hitBombGiantEgg();
		}

		for (Tir tir : tirs) {
			tir.paint(g2);
		}
		for (Bomb bomb : bombs) {
			bomb.paint(g2);
		}

		if (reflected == true) {
			if(addNewClassWithReflection == true) {
				classWithPaintMethod = new AddNewClassWithPaintMethod(classLoaderURLPath, reflectedPath);
				try {
					classWithPaintMethod.generateClass();
					classWithPaintMethod.generatePaintMethodAndObject();
				} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
				addNewClassWithReflection = false;
			}
			try {
				classWithPaintMethod.invokePaintMethod(g2);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void move() {
		if (moveCheck == true) {
			if (rocket != null) {
				rocket.move();

			}
			synchronized (rockets) {
				for (Rocket rocket : rockets) {
					if (rocket != null) {
						rocket.move();
					}
				}	
			}

			synchronized (tirs) {
				for (Tir tir : tirs) {
					tir.move();
				}
			}


			if(whichGroup == "rectangle") {
				if(dropAddimision  == true) {
					drops.rectangularEggDrops();
					drops.recrangularPowerUpsAndCoinDrops();
				}
			}
			if(whichGroup == "circular") {
				drops.circularEggDrops();
				drops.circularPowerUpsAndCoinDrops();
			}

			if (whichGroup == "GiantEgg") {
				giantEgg.move();
				if(giantEgg.getGiantEggY()>= this.height/15) {
					drops.giantEggTirDrops();

				}
			}
		}
	}

	public Rocket getRocket() {
		return rocket;
	}
	public void shelik() {
		if (rocket != null){
			if (shot == true) {
				synchronized (tirs) {
					int r = 50;
					for (int i = 0; i < 1; i++) {
						double degree = Math.PI/2;
						tirs.add(new Tir(rocket.getX() + r * Math.cos(degree)-12,
								rocket.getY() + -r * Math.sin(degree),
								10 * Math.cos(degree),
								-10 * Math.sin(degree)));
					}
				}
			}
		}


	}
	public void  increasingTemperatur(){
		if(rocket!=null) {
			if (rocketTemp >= temperatureBar.maximumTemp) {
				increasingTempTimeThread();
				rocketTemp = 0;
				temperatureBar.setTemperature(rocketTemp);
				shot = false;
			}
			else {
				if (shot == false && increasingTemperatureTimeThread.isAlive() == false ) {
					shot = true;
				}
				if (shot) {
					rocketTemp += Tir.rateOfHeat;
					temperatureBar.setTemperature(rocketTemp);
				}
			}
		}
	}
	public void decreaseTemp() {
		if (rocketTemp < temperatureBar.maximumTemp ) {
			rocketTemp -= 0.5;
			temperatureBar.setTemperature(rocketTemp);
		}	
	}



	private void increasingTempTimeThread() {
		increasingTemperatureTimeThread = new TimeThread(4000);
		increasingTemperatureTimeThread.start();
		shot = false;

	}

	public  void shelikBomb() {
		numOfBombs = propertiesBar.getNumOfBombs();
		if(rocket != null) {
			if(numOfBombs != 0) {
				numOfBombs -=1;
				propertiesBar.setNumOfBombs(numOfBombs);
				synchronized (bombs) {
					for (int i = 0; i < 1; i++) {
						bombs.add(new Bomb(this.rocket));
					}
				}
			} 
		}
	}

	public void eggIsHittenPlayer() {
		if ( whichGroup == "rectangle" ) {
			int cul = rectangleHens.cul;
			int row = rectangleHens.row;
			for (int i=0 ; i < row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					if ( rectangleHens.rectangleHens[i][j].egg != null && this.getRocket()!=null ) {

						if (((this.getRocket()).getX() < (rectangleHens.rectangleHens[i][j].egg.getEggX()+30)) && 
								(this.getRocket().getX() > (rectangleHens.rectangleHens[i][j].egg.getEggX()-30))) {
							if ((this.getRocket().getY() < (rectangleHens.rectangleHens[i][j].egg.getEggY()+30)) && 
									(this.getRocket().getY()> (rectangleHens.rectangleHens[i][j].egg.getEggY()-30))) {
								if (rectangleHens.rectangleHens[i][j].hit == true) {
									rectangleHens.rectangleHens[i][j].egg = null;
								}
								else {
									rectangleHens.rectangleHens[i][j].egg.paintCheck = false;
									propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
									eggAndPlayerIncident = true;	
								}
							} else {
								eggAndPlayerIncident = false; 
							}

						}

					}
				}
			}

		}
		if ( whichGroup == "circular" ) {
			for (SingleHen singleHen : circleGroup.circularHenArray1) {
				if ( singleHen.egg != null && this.getRocket()!=null ) {

					if (((this.getRocket()).getX() < ( singleHen.egg.getEggX()+30)) && 
							(this.getRocket().getX() > ( singleHen.egg.getEggX()-30))) {
						if ((this.getRocket().getY() < ( singleHen.egg.getEggY()+30)) && 
								(this.getRocket().getY()> ( singleHen.egg.getEggY()-30))) {
							if ( singleHen.hit == true) {
								singleHen.egg = null;
							}
							else {
								singleHen.egg.paintCheck = false;
								propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
								eggAndPlayerIncident = true;	
							}
						} else 
						{
							eggAndPlayerIncident = false; 
						}

					}

				}

			}
			for (SingleHen singleHen : circleGroup.circularHenArray2) {
				if ( singleHen.egg != null && this.getRocket()!=null ) {

					if (((this.getRocket()).getX() < ( singleHen.egg.getEggX()+30)) && 
							(this.getRocket().getX() > ( singleHen.egg.getEggX()-30))) {
						if ((this.getRocket().getY() < ( singleHen.egg.getEggY()+30)) && 
								(this.getRocket().getY()> ( singleHen.egg.getEggY()-30))) {
							if ( singleHen.hit == true) {
								singleHen.egg = null;
							}
							else {
								singleHen.egg.paintCheck = false;
								propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
								eggAndPlayerIncident = true;	
							}
						} else 
						{
							eggAndPlayerIncident = false; 
						}

					}

				}

			}
			for (SingleHen singleHen : circleGroup.circularHenArray3) {
				if ( singleHen.egg != null && this.getRocket()!=null ) {

					if (((this.getRocket()).getX() < ( singleHen.egg.getEggX()+30)) && 
							(this.getRocket().getX() > ( singleHen.egg.getEggX()-30))) {
						if ((this.getRocket().getY() < ( singleHen.egg.getEggY()+30)) && 
								(this.getRocket().getY()> ( singleHen.egg.getEggY()-30))) {
							if ( singleHen.hit == true) {
								singleHen.egg = null;
							}
							else {
								singleHen.egg.paintCheck = false;
								propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
								eggAndPlayerIncident = true;	
							}
						} else 
						{
							eggAndPlayerIncident = false; 
						}

					}

				}

			}
		}
		if(whichGroup =="GiantEgg") {
			for(int i=0 ; i<giantEgg.giantEggTirsArray.size(); i++) {
				if ( giantEgg.giantEggTirsArray.get(i)!= null && this.getRocket()!=null ) {
					if ((this.getRocket().getX() < (giantEgg.giantEggTirsArray.get(i).getgiantTirX()+30)) && 
							(this.getRocket().getX() > (giantEgg.giantEggTirsArray.get(i).getgiantTirX()-30))) {
						if ((this.getRocket().getY() < (giantEgg.giantEggTirsArray.get(i).getgiantTirY()+30)) && 
								(this.getRocket().getY()> (giantEgg.giantEggTirsArray.get(i).getgiantTirY()-30))) {
							giantEgg.giantEggTirsArray.get(i).paintCheck = false;
							giantEgg.giantEggTirsArray.remove(i);
							this.setRocket(null);
							this.propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
							this.eggAndPlayerIncident = true;
						} else {
							this.eggAndPlayerIncident = false; 
						}
					}
				}
			}
		}
	}


	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}

	public void rocketBackTimeThread() {
		rocketBackTimeThread = new TimeThread(5000);
		rocketBackTimeThread.start();
	}



	@SuppressWarnings("unused")
	private void henHitRocketBack() {
		if (isHittenStart== true) {
			henisHittenPlayer();
		}
		if (henAndPlayerIncident == true) {
			if (isHittenStart == true) {
				this.setRocket(null);
				rocketBackTimeThread();
				isHittenStart = false;
			}
			if (rocketBackTimeThread.isAlive() == false) {
				this.setRocket(new Rocket(width - 630 , height - 100 ));
				isHittenStart =true;
				henAndPlayerIncident = false;
			}
		}
	}

	private void henisHittenPlayer() {
		if ( whichGroup == "rectangle" ) {
			int cul = rectangleHens.cul;
			int row = rectangleHens.row;
			for (int i=0 ; i < row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					if ( rectangleHens.rectangleHens[i][j].paintCheck == true && this.getRocket()!=null ) {

						if (((this.getRocket()).getX() < (rectangleHens.rectangleHens[i][j].getX()+30)) && 
								(this.getRocket().getX() > (rectangleHens.rectangleHens[i][j].getX()-30))) {
							if ((this.getRocket().getY() < (rectangleHens.rectangleHens[i][j].getY()+30)) && 
									(this.getRocket().getY()> (rectangleHens.rectangleHens[i][j].getY()-30))) {
								if (rectangleHens.rectangleHens[i][j].hit == true) {
									rectangleHens.rectangleHens[i][j].paintCheck = false;
								}
								else {
									rectangleHens.rectangleHens[i][j].paintCheck = false;
									propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
									henAndPlayerIncident = true;	
								}
							} else {
								henAndPlayerIncident = false; 
							}

						}

					}
				}
			}

		}
		if ( whichGroup == "circular" ) {
			for (SingleHen singleHen: circleGroup.circularHenArray1) {
				if ( singleHen.paintCheck == true && this.getRocket()!=null ) {
					if (((this.getRocket()).getX() < (singleHen.getX()+30)) && 
							(this.getRocket().getX() > (singleHen.getX()-30))) {
						if ((this.getRocket().getY() < (singleHen.getY()+30)) && 
								(this.getRocket().getY()> (singleHen.getY()-30))) {
							singleHen.paintCheck = false;
							propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
							henAndPlayerIncident = true;	

						} else {
							henAndPlayerIncident = false; 
						}

					}

				}
			}
			for (SingleHen singleHen: circleGroup.circularHenArray2) {
				if ( singleHen.paintCheck == true && this.getRocket()!=null ) {
					if (((this.getRocket()).getX() < (singleHen.getX()+30)) && 
							(this.getRocket().getX() > (singleHen.getX()-30))) {
						if ((this.getRocket().getY() < (singleHen.getY()+30)) && 
								(this.getRocket().getY()> (singleHen.getY()-30))) {
							singleHen.paintCheck = false;
							propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
							henAndPlayerIncident = true;	

						} else {
							henAndPlayerIncident = false; 
						}

					}

				}
			}
			for (SingleHen singleHen: circleGroup.circularHenArray3) {
				if ( singleHen.paintCheck == true && this.getRocket()!=null ) {
					if (((this.getRocket()).getX() < (singleHen.getX()+30)) && 
							(this.getRocket().getX() > (singleHen.getX()-30))) {
						if ((this.getRocket().getY() < (singleHen.getY()+30)) && 
								(this.getRocket().getY()> (singleHen.getY()-30))) {
							singleHen.paintCheck = false;
							propertiesBar.setHealth(this.propertiesBar.getHealth() - 1);
							henAndPlayerIncident = true;	

						} else {
							henAndPlayerIncident = false; 
						}

					}

				}
			}
		}
	}

	public void eggHitRocketBack() {
		if (isHittenStart== true) {
			eggIsHittenPlayer();
		}
		if (eggAndPlayerIncident == true) {
			if (isHittenStart == true) {
				this.setRocket(null);
				rocketBackTimeThread();
				isHittenStart = false;
			}
			if (rocketBackTimeThread.isAlive() == false) {
				this.setRocket(new Rocket(width - 630 , height - 100 ));
				isHittenStart =true;
				eggAndPlayerIncident = false;
			}
		}

	}
}
