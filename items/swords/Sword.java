package fr.pierrehb.items.swords;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;

public abstract class Sword extends Equipement{

	public Sword(Texture texture, String itemName, String description) {
		super(texture, itemName, description);
		this.type=equipement.SWORD;
		
	}
}