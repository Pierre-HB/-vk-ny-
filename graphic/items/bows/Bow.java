package fr.pierrehb.items.bows;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;

public abstract class Bow extends Equipement{

	public Bow(Texture texture, String itemName, String description) {
		super(texture, itemName, description);
		this.type=equipement.BOW;
		}
}