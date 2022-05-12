package eg.edu.alexu.csd.oop.game.myGame.model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Sound extends Thread{

	public void run() {
		try {
			File musicPath = new File(".//res//videoplayback.wav");
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}else {
				System.out.println("Can't find sound file");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
