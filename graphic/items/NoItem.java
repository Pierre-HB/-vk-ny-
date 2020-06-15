package fr.pierrehb.items;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.armors.Armor;

public class NoItem extends Armor{

	public NoItem(){
		super(Texture.loadTexture("res/textures/entity/Focus.png"),Texture.loadTexture("res/textures/entity/Focus.png"),"No Item","No description");
		this.type=equipement.ALL;

	}

	@Override
	public void action() {
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
