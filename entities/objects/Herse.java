package fr.pierrehb.entities.objects;

import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.Level;

public class Herse extends Objects {
	static float[] dimension = {2,1};
	static int[] taille = {16,32};
	herse_state state = herse_state.UP;
	public Herse(int x, int y, herse_state State, float[] Color) {
		super(x, y, "Herse", false, Texture.loadTexture("res/textures/entity/Herse.png"), dimension, taille);
		setState(State);
		this.color = Color;
		ini();
		}
	public Herse(int x, int y, herse_state State) {
		super(x, y, "Herse", false, Texture.loadTexture("res/textures/entity/Herse.png"), dimension, taille);
		setState(State);
		ini();
		}
	public Herse(int x, int y) {
		super(x, y, "Herse", false, Texture.loadTexture("res/textures/entity/Herse.png"), dimension, taille);
		setState(herse_state.UP);
		ini();
	}
		
	
	public void ini() {
		TRUERENDER = false;
		HP = 1;
		maxHP = 1;
		yo = 0;
	}
	public void setState(herse_state State) {

		this.state = State;
		if(state == herse_state.UP) {
			xo = 1;
			Level.solidList[(int)(x/16)][(int)((y+16)/16)] = true;
		}
		else {
			xo = 0;
			Level.solidList[(int)(x/16)][(int)((y+16)/16)] = false;
		}
		
	}
	@Override
	protected void die() {
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
		return (int)y+16;
	}
	@Override
	protected void otherRender() {
		texture.bind();
		Renderer.renderObject((int)x, (int)y, xo, yo, dimension, taille, color);
		texture.unbind();		
	}
	@Override
	public void start() {
		
	}

}
