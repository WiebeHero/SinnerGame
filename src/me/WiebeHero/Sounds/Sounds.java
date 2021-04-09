package me.WiebeHero.Sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public class Sounds {
	
	public static Clip hover_button;
	
	public static void init() {
		try {
			System.out.println(Sounds.class.getResource("/sounds/Hover.wav").toURI());
			File file = new File(Sounds.class.getResource("/sounds/Hover.wav").toURI());
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			hover_button = AudioSystem.getClip();
			hover_button.addLineListener(e -> {
				if(e.getType() == LineEvent.Type.STOP) {
					if(hover_button.getFrameLength() == hover_button.getFramePosition()) {
						hover_button.stop();
						hover_button.setMicrosecondPosition(0L);
					}
				}
			});
			hover_button.open(sound);
			FloatControl volume = (FloatControl) hover_button.getControl(FloatControl.Type.MASTER_GAIN);
//			float range = volume.getMaximum() - volume.getMinimum();
//			volume.setValue(100 * Settings.volume / 100);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
