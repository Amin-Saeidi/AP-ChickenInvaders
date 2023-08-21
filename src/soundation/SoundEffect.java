package soundation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class SoundEffect extends Thread{

	public String clickSound;
	private Clip clip;
	private File soundFile;
	private AudioInputStream sound; 
	public static boolean soundCondition = true;

	public void setFile(String soundFileName) {
		if(soundCondition == true) {
			try {
				soundFile = new File(soundFileName);
				sound = AudioSystem.getAudioInputStream(soundFile);
				clip = AudioSystem.getClip();
				clip.open(sound);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void play() {
		if(soundCondition==true) {
			clip.setFramePosition(0);
			clip.start();
			try {
				sound.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	@Override
	public void run() {
		clip.setFramePosition(0);
		clip.start();
		try {
			sound.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

