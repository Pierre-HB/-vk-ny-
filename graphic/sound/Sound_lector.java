package fr.pierrehb.sound;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Sound_lector extends Thread {
		String sound;
		float volume;
	public Sound_lector(String sound, float Volume) {
		this.sound = sound;
		this.volume = Volume;
	}
	
public void run() {
	Sound player = new Sound(sound, volume);
	InputStream stream = new ByteArrayInputStream(player.getSamples());
	player.play(stream);
}
public String getSong() {
	return sound;
}
}