package IO;

import java.io.Serializable;



@SuppressWarnings("serial")
public class User implements Serializable{
	public int health;
	public int numOfBombs;
	public int coins;
	public int score;
	public int numberOfLevel;
	public int numberOfWave;
	public String name;
	public int id;
	
	public User(int health, int numOfBombs, int coins, int numberOfLevel, int score, String name) {
		this.health = health;
		this.numOfBombs = numOfBombs;
		this.coins = coins;
		this.score = score;
		this.numberOfLevel = numberOfLevel;
		this.name = name;
	}

	public User(int health, int numOfBombs, int coins, int numberOfLevel,int numberOfWave ,int score, String name) {
		this.health = health;
		this.numOfBombs = numOfBombs;
		this.coins = coins;
		this.score = score;
		this.numberOfLevel = numberOfLevel;
		this.numberOfWave = numberOfWave;
		this.name = name;
	}
	
}
