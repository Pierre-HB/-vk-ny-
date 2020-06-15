package fr.pierrehb.other;


import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public interface BossDie {
	int[] minColor1 = {0, 0, 0};
	int[] maxColor1 = {50, 50, 50};
	int[] minColor2 = {0, 0, 0};
	int[] maxColor2 = {50, 50, 50};
	float[] speedDie = {0.0f, 0.3f};
	float[] speedDie1 = {1.5f, 0.0f};
	float[] speedDie2 = {0.0f, 1.5f};
	float[] speedDie3 = {0.0f, -1.5f};
	float[] speedDie4 = {-1.5f, 0.0f};
	
	public static int random(int min, int max) {
		return (int)(Math.random()*(max+1-min)+min);
	}
	
	public static void bossDie(float x, float y) {

		for(int a = 0; a < 100; a++) {
			Game.level.addParticle(new Smoke((int)x+8, (int)y+16, random(40, 200),random(100, 200)/200.0f, Game.getPlayer(), minColor2, maxColor2, minColor2, maxColor2));
		}
		
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie1, Game.getPlayer(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie2, Game.getPlayer(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie3, Game.getPlayer(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie4, Game.getPlayer(), minColor2, maxColor2, minColor2, maxColor2));
		}
		
		for(int a = 0; a < 400; a++) {
			Game.level.addParticle(new Smoke((int)x+random(0, 16), (int)y+random(0, 32), random(40, 150), random(0, 400), speedDie, Game.getPlayer(), minColor1, maxColor1, minColor1, maxColor1));
		}
		Sound_lector sound = new Sound_lector("res/sound/sou/bossDie.wav", Main.save.soundVolume);
		sound.start();
	}
}
