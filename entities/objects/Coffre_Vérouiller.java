package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Coffre_Vérouiller extends Objects {
static float[] dimension = {2,1};
static int[] taille = {16,32};
coffre_state state = coffre_state.CLOSE;
private static Sound_lector song;
	public Coffre_Vérouiller(int x, int y, boolean canmove) {
		super(x, y, "Coffre Vérouiller", canmove, Texture.loadTexture("res/textures/entity/CoffreVérouiller.png"), dimension, taille);
		ini();
}
	public Coffre_Vérouiller(int x, int y) {
		super(x, y, "Coffre Vérouiller", false, Texture.loadTexture("res/textures/entity/CoffreVérouiller.png"), dimension, taille);
		ini();
		}

	@Override
	protected void die() {
		
	}

	@Override
	public void trueUpdate() {
		switch (state) {
		case OPEN:
			xo = 1;
			break;
		case CLOSE:
			xo = 0;
			break;
		}
	}
	public void setState(coffre_state State) {

		if(State != state) {
		if(state == coffre_state.CLOSE) {
			song = new Sound_lector("res/sound/sou/objects/closeingChest.wav", Main.save.soundVolume);
			song.start();
		}else {
			song = new Sound_lector("res/sound/sou/objects/openingChest.wav", Main.save.soundVolume);
			song.start();
		}
		}
		this.state = State;
		
	}
	public void ini() {
		HP = 200;
		xo = 0;
		yo = 0;
	}
	@Override
	protected boolean interat() {
		if(state == coffre_state.CLOSE) {
			Game.level.speakToLevel(1);
			return true;
		}return false;
	}
	@Override
	public boolean interact() {
		if(Math.abs(Game.getPlayer().coord[0]-x) < 24 && Math.abs(Game.getPlayer().coord[1]-(y+16)) < 24 && state == coffre_state.CLOSE) {
			interat();
			return true;
		}return false;
	}
	@Override
	public void dialogueRender() {
		
	}
	@Override
	public int getBottomY() {
		return (int)y + taille[1];
	}
	@Override
	protected void otherRender() {
		
	}
	@Override
	public void start() {
		
	}
	

}
