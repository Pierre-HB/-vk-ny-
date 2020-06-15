package fr.pierrehb.items;

import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.ObjectsStates;
import fr.pierrehb.other.Polices.polices;

public abstract class Equipement extends Item implements ObjectsStates{
	protected int damage = 0;
	protected int armor = 0;
	protected int health = 0;
	protected equipement type;
	protected Caractere[] equiper = Renderer.getText(108, 117, str_equiper, polices.COMIC_SANS_MS, 0.6f);
	protected Caractere[] unequiper =Renderer.getText(108, 117, str_unequiper, polices.COMIC_SANS_MS, 0.6f);

	protected boolean equiped = false;

	public Equipement(Texture texture, String itemName, String description) {
		super(texture, itemName, description, str_equiper);
	}
	public int getDamage() {
		return damage;
	}
	public int getArmor() {
		return armor;
	}
	public int getHealth() {
		return health;
	}
	public equipement getType() {
		return type;
	}
	public void equipe() {
		equiped = true;
		this.actionName=unequiper;
		
	}
	public void unequipe() {
		equiped = false;
		this.actionName=equiper;
	}
	@Override
	public void action() {
		if(!equiped)owner.equipeItem(locationInInventory);
		else if(type==equipement.ARMOR) owner.unequipeArmor();
		else if(type!=equipement.NONE && type!= equipement.OTHER)owner.unequipeWeapon();
		
	}

}
