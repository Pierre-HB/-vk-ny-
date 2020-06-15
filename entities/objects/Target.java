package fr.pierrehb.entities.objects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Commands;


public class Target extends Objects implements Commands {
	static float[] dimension = {2,1};
	static int[] taille = {16,16};
	private float[] coordCannon = new float[2];
	public boolean isTargeted = false;
	private boolean UP, DOWN, LEFT, RIGHT;
	public int range = 160;

	public Target(int x, int y, float[] Cannon) {
		super(x, y, "Target", true, Texture.loadTexture("res/textures/entity/Target.png"), dimension, taille);
		coordCannon = Cannon;
		xo = 1;
		yo = 0;
		}

	@Override
	protected void otherRender() {
		
	}

	@Override
	protected void die() {
		
	}
	public void targeted() {
		isTargeted = true;
		xo = 0;
	}
	public void unTargeted() {
		isTargeted = false;
		xo = 1;
	}
	private boolean canGo(float X, float Y) {
		if(X > 0 && X+16 < Game.level.size[0]*16 && Y > 0 && Y+16 < Game.level.size[1]*16) {
			if(Math.sqrt((X-coordCannon[0])*(X-coordCannon[0])+(Y-coordCannon[1])*(Y-coordCannon[1])) < range) ;return true;
		}
		return false;
	}
	@Override
	public void trueUpdate() {
		if(isTargeted) {
			if(( upkey != -1 && Keyboard.isKeyDown(upkey)) || Mouse.isButtonDown(upmouse)) {if(!DOWN)UP = true;;}
			else UP = false;
			if(( downkey != -1 && Keyboard.isKeyDown(downkey)) || Mouse.isButtonDown(downmouse)) {if(!UP)DOWN = true;}
			else DOWN = false;
			if(( leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {if(!RIGHT)LEFT = true;}
			else LEFT = false;
			if(( rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {if(!LEFT)RIGHT = true;}
			else RIGHT = false;
				
				if( UP && LEFT && canGo(x-1.4f, y-1.4f)) {
					x -=1.4f;
					y -=1.4f;
				}
				else if( UP && RIGHT && canGo(x+1.4f, y-1.4f)) {
					x +=1.4f;
					y -=1.4f;
				}
				else if( DOWN && LEFT && canGo(x-1.4f, y+1.4f)) {
					x -=1.4f;
					y +=1.4f;
				}
				else if( DOWN && RIGHT && canGo(x+1.4f, y+1.4f)) {
					x +=1.4f;
					y +=1.4f;
				}
				else if( UP && canGo(x, y-2)) {
					y -=2;
				}
				else if( DOWN && canGo(x, y+2)) {
					y +=2;
				}else if( LEFT && canGo(x-2, y)) {
					x -=2;
				}else if( RIGHT && canGo(x+2, y)) {
					x +=2;
				}
				}
				
				
				
			
	}

	@Override
	protected int getBottomY() {
		return Game.level.size[1]*16;
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
	public void start() {
		
	}

}
