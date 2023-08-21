package game.objects.hens;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import engine.Animatable;

public class SuicidalGroup implements Animatable{

	
	public int numberOfEnemies;
	public ArrayList<SingleHen> suicidalHenArray;
	private String whichHenString;
	private String whichGroup = "Suicidal";
	double henHeight = 90;
	double henWidth = 80;
	

	public SuicidalGroup(int numberOfEnemies, String henStrengthLevel) {
		this.numberOfEnemies = numberOfEnemies;
		whichHenString = henStrengthLevel;
		init();
	}

	private void init() {
		suicidalHenArray = new ArrayList<SingleHen>();
		generateArray();
	}

	private void generateArray() {
		for (int i=0; i<this.numberOfEnemies; i++) {
			double x = 100 + 150 * i;
			double y = 80;
			suicidalHenArray.add(new SingleHen(x, y, henHeight, henWidth, whichGroup ,whichHenString, this));
		}
	}

	private int probablityDeterminer() {
		Random random = new Random();
		int choosenEnemyIndex = random.nextInt(numberOfEnemies);
		return choosenEnemyIndex;
	}

	@Override
	public void paint(Graphics2D g) {
		for (SingleHen hen: suicidalHenArray) {
			if (hen.choosingEnemyTimeThread != null) {
				if (hen == suicidalHenArray.get(probablityDeterminer()) && hen.choosingEnemyTimeThread.isAlive() == false) {
					hen.threadCreatingBoolean = true;
					if (!hen.linearMovementBoolean) {
						hen.randomMovementBoolean = true;
						hen.linearMovementBoolean = true;
					}else if (hen.firstTimeBoolean) {
						hen.randomMovementBoolean = true;
						hen.linearMovementBoolean = true;
					}
				};

			}
			hen.paint(g);
		}
	}



	@Override
	public void move() {
		
	}

}
