package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Button extends Objects {
	static float[] dimension = {2,1};
	static int[] taille = {16,16};
	button_state state = button_state.OFF;
	private int speak;
	private boolean oneUse = false;
	private boolean use = false;
	private static Sound_lector song;
	public Button(int x, int y, int Speak) {
		super(x, y, "Herse", false, Texture.loadTexture("res/textures/entity/Button.png"), dimension, taille);
		this.speak = Speak;
		ini();
		}
	public Button(int x, int y, int Speak, boolean OneUse) {
		super(x, y, "Herse", false, Texture.loadTexture("res/textures/entity/Button.png"), dimension, taille);
		this.speak = Speak;
		this.oneUse = OneUse;
		ini();
		}
		
	
	public void ini() {
		HP = 200;
		xo = 0;
		yo = 0;
	}
	public void setState(button_state State) {

		this.state = State;
		song = new Sound_lector("res/sound/sou/objects/pressButton.wav", Main.save.soundVolume);
		song.start();
		
		
	}
	@Override
	protected void die() {
		
	}

	@Override
	public void trueUpdate() {
		
	}

	@Override
	public boolean interact() {
		
		if(!use && Math.abs(Game.getPlayer().coord[0]-x) <= 16 &&  Math.abs((Game.getPlayer().getCoord()[1]-y-taille[1]/2)) <= 22 && state == button_state.OFF) {
			interat();
			return true;
		}return  false;

	}

	@Override
	protected boolean interat() {
		if(oneUse)use = true;
		xo = 1;		
		Game.level.speakToLevel(speak);
		return !oneUse;
	}

	@Override
	public void dialogueRender() {
		
	}
	@Override
	public int getBottomY() {
		return (int)y;
	}



	@Override
	protected void otherRender() {
		
	}
	@Override
	public void start() {
		
	}

}
