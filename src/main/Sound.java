package main;

//import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/mondstadt.wav");
		soundURL[1] = getClass().getResource("/sound/itemCollect.wav");
		soundURL[2] = getClass().getResource("/sound/doorOpen.wav");
		soundURL[3] = getClass().getResource("/sound/walk_1.wav");
		soundURL[4] = getClass().getResource("/sound/walk_2.wav");
		soundURL[5] = getClass().getResource("/sound/masterJean.wav");
		soundURL[6] = getClass().getResource("/sound/kleeLaLa.wav");
		soundURL[7] = getClass().getResource("/sound/victory.wav");
		soundURL[8] = getClass().getResource("/sound/backgroundMusic.wav");
		soundURL[9] = getClass().getResource("/sound/kleeHurt.wav");
		soundURL[10] = getClass().getResource("/sound/kleeBomb.wav");
		soundURL[11] = getClass().getResource("/sound/hitMonster.wav");
		soundURL[12] = getClass().getResource("/sound/monsterDeath.wav");
		soundURL[13] = getClass().getResource("/sound/gameOver.wav");
	}
	
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			//e.printStackTrace();
			
		}
		
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}

}
