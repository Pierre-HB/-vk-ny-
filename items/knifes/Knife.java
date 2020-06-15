package fr.pierrehb.items.knifes;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;

public abstract class Knife extends Equipement{

	public Knife(Texture texture, String itemName, String description) {
		super(texture, itemName, description);
		this.type=equipement.KNIFE;
	}
}