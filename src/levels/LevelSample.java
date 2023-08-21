package levels;

import java.awt.Graphics2D;
import java.net.URL;

import game.object.Rocket;

public interface LevelSample {

	void endOfWave1();
	void endOfWave2();
	void endOfWave3();
	void endOfWave4();
	void endOfFinalWave();
	void paintLoop(Graphics2D g);
	void saveContect();
	void gameOverCheck();
	void reflect();
	void moveLoop();
	Rocket getRocket();
	void shelik();
	void increasing();
	void trueDecreasing();
	void falseDecreasing();
	void shelikBomb();
	void reflectMethod(URL[] classLoaderURLPath, String reflectedPath);
	void loadContent();


}
