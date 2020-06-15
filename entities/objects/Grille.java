package fr.pierrehb.entities.objects;


import fr.pierrehb.graphic.Texture;

public class Grille extends Objects {
static float[] dimension = {1,1};
static int[] taille = {16,16};
	public Grille(int x, int y, boolean canmove) {
		super(x, y, "Grille", canmove, Texture.loadTexture("res/textures/entity/Grille.png"), dimension, taille);
		ini();
}
	public Grille(int x, int y) {
		super(x, y, "Grille", false, Texture.loadTexture("res/textures/entity/Grille.png"), dimension, taille);
		ini();
		}

	@Override
	protected void die() {
		
	}

	@Override
	public void trueUpdate() {
		
	}
	public void ini() {
		HP = 2000;
		xo = 0;
		yo = 0;
	}
	@Override
	protected boolean interat() {
		
		return false;
	}
	@Override
	public boolean interact() {
		return false;
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
