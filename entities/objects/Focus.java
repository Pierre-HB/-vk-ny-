/**
 * 
 */
package fr.pierrehb.entities.objects;

import fr.pierrehb.graphic.Texture;


public class Focus extends Objects {
public static float[] size = {1,1};
public static int[] taille = {1,1};
private float[] wantedCoord = new float[2];
private boolean wantMove = false;
private float speed = 0;


	public Focus(int x, int y) {		
		super(x, y, "Focus", true, Texture.loadTexture("res/textures/entity/Focus.png"), size, taille);
	}


	@Override
	protected void otherRender() {

	}


	@Override
	public void start() {

	}


	@Override
	protected void die() {

	}


	@Override
	public void trueUpdate() {
		if (wantMove) {
			float xoffset = x-wantedCoord[0];
			float yoffset = y-wantedCoord[1];
			if (Math.abs(xoffset) >= speed) {
				if (xoffset > 0) x-=speed;
				else x+= speed;			
			}else {
				x = wantedCoord[0];
			}
			if (Math.abs(yoffset) >= speed) {
				if (yoffset > 0) y-=speed;
				else y+= speed;			
			}else {
				y = wantedCoord[1];
			}
			if(Math.abs(xoffset) < speed && Math.abs(yoffset) < speed) wantMove = false;
			
		}

	}


	@Override
	protected int getBottomY() {
		return 0;
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
	public void go(float X, float Y, float speed) {
		this.wantedCoord[0] = X;
		this.wantedCoord[1] = Y;
		wantMove = true;
		this.speed = speed;
		
	}

}
