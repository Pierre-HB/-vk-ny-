package fr.pierrehb.entities;

public abstract class Mortal_Object extends Entity{

	public Mortal_Object(float x, float y, int[] taille, String name, boolean canmove) {
		super(x, y, taille, name, canmove);
	}

	@Override
	protected void die() {
		
	}

	@Override
	public void trueUpdate() {
		
	}

	@Override
	protected void attackRender() {
		
	}

	@Override
	protected void attackUpdate() {
		
	}

	@Override
	public void trueRender() {
		
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

}
