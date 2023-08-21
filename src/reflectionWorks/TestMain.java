package reflectionWorks;

import MainPackage.Start;
import engine.Game;

public class TestMain {

	@SuppressWarnings("unused")

	public static void main(String[] args) {
//		Start start = new Start(new Level());
		Start start = new Start(new Game(1200, 700, "PeripheralHens","1","1","1"));		
	}

	
	
}