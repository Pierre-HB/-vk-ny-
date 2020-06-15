package fr.pierrehb.items.armors;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;

public abstract class Armor extends Equipement{
	protected Texture equipedArmor;

	public Armor(Texture texture, Texture equipedArmor, String itemName, String description) {
		super(texture, itemName, description);
		this.type=equipement.ARMOR;
		this.equipedArmor = equipedArmor;
		}
	public Texture getEquipeRender() {
		return equipedArmor;
	}
}