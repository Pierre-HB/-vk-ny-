package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.Level;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Cristal extends Objects{
	static float[] dimension = {1,1};
	static int[] taille = {48,48};
	herse_state state = herse_state.UP;
	private static Sound_lector song;
	public Cristal(int x, int y, float[] Color) {
		super(x, y, "Cristal", false, Texture.loadTexture("res/textures/entity/Cristal.png"), dimension, taille);
		this.color = Color;
		ini();
		}
	
	public Cristal(int x, int y) {
		super(x, y, "Cristal", false, Texture.loadTexture("res/textures/entity/Cristal.png"), dimension, taille);
		ini();
	}
		
	
	public void ini() {
		HP = 40;
		maxHP = 40;
		yo = 0;
		mortal = true;
		mortalID = Main.getMortalID();
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+46;
		hitBox[3] = (int)y+46;
		Level.solidList[(int)(x/16)][(int)((y)/16)] = true;
		Level.solidList[(int)((x+16)/16)][(int)((y)/16)] = true;
		Level.solidList[(int)((x+32)/16)][(int)((y)/16)] = true;
		Level.solidList[(int)(x/16)][(int)((y+16)/16)] = true;
		Level.solidList[(int)((x+16)/16)][(int)((y+16)/16)] = true;
		Level.solidList[(int)((x+32)/16)][(int)((y+16)/16)] = true;
		Level.solidList[(int)(x/16)][(int)((y+32)/16)] = true;
		Level.solidList[(int)((x+16)/16)][(int)((y+32)/16)] = true;
		Level.solidList[(int)((x+32)/16)][(int)((y+32)/16)] = true;

	}
	
	@Override
	protected void die() {
life = false;
remove = true;
Level.solidList[(int)(x/16)][(int)((y)/16)] = false;
Level.solidList[(int)((x+16)/16)][(int)((y)/16)] = false;
Level.solidList[(int)((x+32)/16)][(int)((y)/16)] = false;
Level.solidList[(int)(x/16)][(int)((y+16)/16)] = false;
Level.solidList[(int)((x+16)/16)][(int)((y+16)/16)] = false;
Level.solidList[(int)((x+32)/16)][(int)((y+16)/16)] = false;
Level.solidList[(int)(x/16)][(int)((y+32)/16)] = false;
Level.solidList[(int)((x+16)/16)][(int)((y+32)/16)] = false;
Level.solidList[(int)((x+32)/16)][(int)((y+32)/16)] = false;
Game.level.speakToLevel(3);
song = new Sound_lector("res/sound/sou/objects/cristalDestroy.wav", Main.save.soundVolume);
song.start();
	}

	@Override
	public void trueUpdate() {
		
	}

	@Override
	public boolean interact() {
		return false;
		
	}

	@Override
	protected boolean interat() {
		return false;
		
	}

	@Override
	public void dialogueRender() {
		
	}
	@Override
	public int getBottomY() {
		return (int)y+taille[1];
	}
	@Override
	protected void otherRender() {
		
	}
	@Override
	public void start() {
		
	}

}
