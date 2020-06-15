package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.Level;


public class Door extends Objects{
	static float[] dimension = {1,1};
	static int[] taille = {24,48};
	private Half_Door left;
	private Half_Door right;
	private boolean open = false;
	private boolean lock = false;
	public Door(int x, int y, boolean Lock, float[] Color) {
		super(x, y, "Cristal", false, Texture.loadTexture("res/textures/entity/Door.png"), dimension, taille);
		this.color = Color;
		this.lock = Lock;
		ini();
		}
	
	public Door(int x, int y) {
		super(x, y, "Cristal", false, Texture.loadTexture("res/textures/entity/Door.png"), dimension, taille);
		ini();
	}
		
	
	public void ini() {
		this.left = new Half_Door((int)x, (int)y, door_state.LEFT, color);
		this.right = new Half_Door((int)x+24, (int)y, door_state.RIGHT, color);
		TRUERENDER = false;
		if(lock) {
			left.lock();
			right.lock();
		}

		HP = 40;
		maxHP = 40;
		Level.solidList[(int)(x/16)][(int)((y+32)/16)] = true;
		Level.solidList[(int)((x+16)/16)][(int)((y+32)/16)] = true;
		Level.solidList[(int)((x+32)/16)][(int)((y+32)/16)] = true;


	}
	public void lock() {
		lock = true;
		left.lock();
		right.lock();
	}
	public void Unlock() {
		lock = false;
		left.unLock();
		right.unLock();
	}
	
	@Override
	protected void die() {
		life = false;
		remove = true;
		Level.solidList[(int)(x/16)][(int)((y+32)/16)] = false;
		Level.solidList[(int)((x+16)/16)][(int)((y+32)/16)] = false;
		Level.solidList[(int)((x+32)/16)][(int)((y+32)/16)] = false;

	}

	@Override
	public void trueUpdate() {
		left.update();
		right.update();
	}

	@Override
	public boolean interact() {
		if(!lock && Math.abs(Game.getPlayer().getCoord()[0]-(x+12)) <= 32 &&  Math.abs((Game.getPlayer().getCoord()[1]-(y+24))) <= 48 && !open) {
			interat();
			return true;
		}return false;
		
	}

	@Override
	protected boolean interat() {
		open = true;
		left.interat();
		right.interat();
		Level.solidList[(int)(x/16)][(int)((y+32)/16)] = false;
		Level.solidList[(int)((x+16)/16)][(int)((y+32)/16)] = false;
		Level.solidList[(int)((x+32)/16)][(int)((y+32)/16)] = false;	
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
		right.render();
		left.render();
	}
	@Override
	public void start() {
		
	}

}