package fr.pierrehb.graphic;

import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.ObjectsStates;
import fr.pierrehb.sound.Sound_lector;

public class Annimation implements Color, ObjectsStates{
	private static int time_per_frame = 15;
	private static int time_per_tile = 60;
	private static int reset = 600;
	private static int time_per_letter = 5;
	private static int count;
	private static boolean counting = false;
	private static int maxLetters;
	private static int current_letter;
	public enum direction {UP,DOWN,LEFT,RIGHT;}
	private static int timer = 0;
	private static int letterPerSound = 5;
	private static int lastLetterSound = 0;
	private static Sound_lector sound;
	
	public static void update() {
		if(counting)count++;
		else count = 0;
		timer++;
		if(timer >reset) timer = 1;
	}
	public static int getHumanLine(direction direction, boolean move) {
		int line;
		switch (direction) {
		case UP:if(move)line = 2;else line = 6;break;
		case DOWN:if(move)line = 0;else line = 4;break;
		case LEFT:if(move)line = 3;else line = 7;break;
		case RIGHT:if(move)line = 1;else line = 5;break;
		default:line = 4;}
		return line;
	}
	public static int getHumanColumn(int time, boolean move) {
		int column = 0;
		if(move) {
		int temps = time%(time_per_frame*4);
		if(temps >= time_per_frame && temps < time_per_frame*2) column = 1;
		if(temps >= time_per_frame*2 && temps < time_per_frame*3f) column = 2;
		if(temps > time_per_frame*3) column = 3;
		}else {

			column = getTileLine(4);
		}
		return column;
	}
	public static int getTileLine(int nb_tile) {
		return (time_per_tile*timer/(reset*10)%nb_tile);
	}
	public static int getLetter(float speed) {
		current_letter = (int)((count/time_per_letter)*speed);
		if(current_letter == lastLetterSound + letterPerSound && maxLetters>current_letter) {
			lastLetterSound=current_letter;
			sound = new Sound_lector("res/sound/sou/letterPlay.wav", Main.save.soundVolume);
			sound.start();
		}
		return current_letter;
	}
	public static void startDialogue(int maxletters) {
		maxLetters = maxletters;
		count = 0;
		counting = true;
		lastLetterSound=0;
	}
	public static boolean stopDialogue() {
		
		if(current_letter < maxLetters) {
			count = maxLetters*time_per_letter;
			current_letter = maxLetters;
		}
		else counting = false;
		return !counting;
	}
	public static float[] getDamageColor(int healthTimer, health_state healthState, float[] color) {
		if(healthTimer == 0) return color;
		if(healthTimer <15) return (healthState == health_state.DAMAGE) ?  LITTLERED :  LITTLEGREEN;
		if(healthTimer <30) return color;
		if(healthTimer <45) return (healthState == health_state.DAMAGE) ?  LITTLERED :  LITTLEGREEN;
		return color;

	}


}
