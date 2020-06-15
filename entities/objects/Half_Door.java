package fr.pierrehb.entities.objects;

import fr.pierrehb.geometrie.Point;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;

public class Half_Door extends Objects{
	static float[] dimension = {2,1};
	static int[] taille = {24,48};
	door_state state = door_state.LEFT;
	private Point p1, p2, p3, p4;
	private float x1, x2, x3, x4, y1, y2, y3, y4;
	private static int openTime = 120;
	private int openCunter = 0;
	private boolean oppening = false;
	private boolean lock = false;
	
	public Half_Door(int x, int y, door_state State, float[] Color) {
		super(x, y, "Cristal", false, Texture.loadTexture("res/textures/entity/Door.png"), dimension, taille);
		this.color = Color;
		this.state = State;
		ini();
		}
	
	
		
	
	public void ini() {
		TRUERENDER = false;
		HP = 40;
		maxHP = 40;
		yo = 0;
		xo = 0;
		switch(state) {
		case LEFT:
			x1 = x;
			x2 = x+taille[0];
			x3 = x+taille[0];
			x4 = x;
			y1 = y;
			y2 = y;
			y3 = y+taille[1];
			y4 = y+taille[1];
			break;
		case RIGHT:
			x1 = x+taille[0];
			x2 = x;
			x3 = x;
			x4 = x+taille[0];
			y1 = y;
			y2 = y;
			y3 = y+taille[1];
			y4 = y+taille[1];
			
			
			break;
		default:
			break;
		
		}
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
		p3 = new Point(x3, y3);
		p4 = new Point(x4, y4);



	}
	
	@Override
	protected void die() {
		life = false;
		remove = true;


	}

	public void oppening() {
		if(!lock) {
		oppening = true;
		openCunter = 0;
		}
	}
	@Override
	public void trueUpdate() {
		if(oppening) {
			openCunter++;
			if(openCunter >= openTime) {
				oppening = false;
			}
		
		switch(state) {
		case LEFT:
			p2.y = y-16*openFunction(openCunter);
			p3.y = y+taille[1]+16*openFunction(openCunter);
			p2.x -= 48.0f/openTime;
			p3.x -= 48.0f/openTime;
			break;
		case RIGHT:
			p2.y = y-16*openFunction(openCunter);
			p3.y = y+taille[1]+16*openFunction(openCunter);
			p2.x += 48.0f/openTime;
			p3.x += 48.0f/openTime;
			break;
		default:
			break;		
		}
		}
	}
	public void lock() {
		lock = true;
		xo = 1;
	}
	public void unLock() {
		lock = false;
		xo = 0;
	}

	@Override
	public boolean interact() {
		return false;
	}

	@Override
	protected boolean interat() {
		oppening();
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
		Renderer.renderPixel(p1, p2, p3, p4, texture, xo, 0, dimension, color);

	}
	private static float openFunction(int i) {
		if(i < openTime/2) {
			double X = (float)i/openTime;
			return (float)Math.log(X/2+1);
		}else {
			float X = Math.abs(((float)i/openTime)-1);
			return (float)Math.log(X/2+1);
		}		
	}
	@Override
	public void start() {
		
	}

}