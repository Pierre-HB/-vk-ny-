package fr.pierrehb.entities.objects;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.ObjectsStates;

public abstract class Objects extends Entity implements ObjectsStates {
	Texture texture;
	float[] dimension;
	protected int xo;
	protected int yo;
	protected boolean TRUERENDER = true;
	
	public Objects(int x, int y, String name, boolean canmove, Texture texture, float[] dimension, int[] taille) {
		super(x, y, taille, name, canmove);
		this.dimension = dimension;
		this.texture = texture;
	}
	@Override
	public void trueRender() {
		if(!remove) {
			if(TRUERENDER) {
		texture.bind();
		Renderer.renderObject((int)x, (int)y, xo, yo, dimension, taille, getHealthColor());
		texture.unbind();
			}else {
				otherRender();
			}
	}}
	protected abstract void otherRender();
	@Override
	public void attackUpdate() {
		
	}
	@Override
	public void attackRender() {
	}
	@Override
	protected int getShield() {
		return 0;
	}
	@Override
	public int getHealthBonus() {
		return 0;
	}
	@Override
	protected int getDefense() {
		return 0;
	}

}
