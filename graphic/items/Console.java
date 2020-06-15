package fr.pierrehb.items;

import fr.pierrehb.graphic.Interface;
import fr.pierrehb.graphic.Texture;

public class Console extends Item{

	public Console() {
		super(Texture.loadTexture("res/textures/entity/Focus.png"),"Console","???","use");
	}

	@Override
	public void action() {
		Interface.INCONSOLE=true;
	}

	@Override
	public int getID() {
		return -42;
	}

}
