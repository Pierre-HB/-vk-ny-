package fr.pierrehb.items.wands;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;

public abstract class Wand extends Equipement{

	public Wand(Texture texture, String itemName, String description) {
		super(texture, itemName, description);
		this.type=equipement.WAND;
	}

}
