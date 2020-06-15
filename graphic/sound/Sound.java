package fr.pierrehb.sound;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	
	private AudioFormat format;
	private byte[] samples;
	private float volume;
	
	public Sound(String filename, float Volume) {
		this.volume = Volume;//volume compris entre 0.9f et 4.9f
		try{
			// on vas chercher le dossier contenant le on
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
			format = stream.getFormat();
			samples = getSamples(stream);
			for(int a = 0; a < samples.length; a++) {
				samples[a] = armoniser(samples[a]);
				}
		}
		catch (UnsupportedAudioFileException e){
			e.printStackTrace();
	}
	catch (IOException e){
			e.printStackTrace();
		}
	}
	private byte armoniser(byte sample) {
		int ajustement = 2;
		byte song = (byte)(sample*volume);
		while(Math.abs(sample) < 116 && Math.abs(song) > 116) {
			song = (byte)(sample*(volume)/ajustement);
			ajustement = ajustement *2;
			if(ajustement == 536870912) break;
		}
		while(Math.abs(sample) > 5 && Math.abs(song) < 5) {
			song = (byte)(sample*(volume)*ajustement);
			ajustement = ajustement *2;
			if(ajustement == 536870912) break;
		}
		return song;
		
	}

	public void play(InputStream source){
		// on crée un buffer de 100ms perméttant de fractionner la lecture en morceau de 100ms
		int bufferSize = format.getFrameSize() * Math.round(format.getSampleRate() / 10);
		byte[] buffer = new byte[bufferSize];
		SourceDataLine line;
		try{
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			line = (SourceDataLine)AudioSystem.getLine(info);
			// on ouvre le flux permettant de remplir le buffer
			line.open(format, bufferSize);
		}
		catch (LineUnavailableException e){
			e.printStackTrace();
			return;
		}
		// on "joue" la musique:
		line.start();
		try{//on fait "derouler" le son
			int numBytesRead = 0;
			// tant que le buffer' est plein, on joue
			while (numBytesRead != -1){
				numBytesRead = source.read(buffer, 0, buffer.length);

				// si le buffer n'est pas plein, on joue les derniére notes
				if (numBytesRead != -1)
					//for(int a = 0; a < buffer.length; a++)buffer[a] = (byte)(buffer[a]+100);
					line.write(buffer, 0, numBytesRead);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		// ferme tout les flux
		line.drain();
		line.close();
	}
	public byte[] getSamples(AudioInputStream stream){
		int length = (int)(stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		try{
			in.readFully(samples);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return samples;
	}
	public byte[] getSamples(){
		return samples;
	}
	
	
}