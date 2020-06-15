package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.armors.Armor;

public class ArmorStand extends Objects{
	private static final float[] dimension = {1,1};
	private static final int[] taille = {16,32};
	private static final float[] nb_position = {5.0f, 8.0f};
	private Armor armor;
	private boolean isEquiped = false;

	public ArmorStand(int x, int y, Armor armor_) {
		super(x, y, "Armor Stand", false, Texture.loadTexture("res/textures/entity/ArmorStand.png"), dimension,taille);
		this.armor=armor_;
		TRUERENDER=false;
		isEquiped=true;

	}

	@Override
	protected void otherRender() {
			texture.bind();
			Renderer.renderObject((int)x, (int)y, xo, yo, dimension, taille, getHealthColor());
			texture.unbind();
			armor.getEquipeRender().bind();
			Renderer.renderObject((int)x, (int)y, 0, 4, nb_position, taille, WHITE);
			armor.getEquipeRender().unbind();
					
	}

	@Override
	public void start() {
		
	}

	@Override
	protected void die() {
		
	}

	@Override
	public void trueUpdate() {
		
		
	}

	@Override
	protected int getBottomY() {
		return (int)y + taille[1];
	}

	@Override
	public boolean interact() {
		if(Math.abs(Game.getPlayer().coord[0]-x) < 16 && Math.abs(Game.getPlayer().coord[1]-y) < 16) {
			
			return interat();
		}
		return false;
	}

	@Override
	protected boolean interat() {
		
		if(isEquiped) {
			Game.getPlayer().giveItem(Game.getItem(armor.getID()));
			isEquiped=false;
			TRUERENDER=true;
			}
	return false;
	}

	@Override
	public void dialogueRender() {
		
	}

}
