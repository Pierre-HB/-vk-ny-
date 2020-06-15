package fr.pierrehb.entities.joueurs;

import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Listable;
import fr.pierrehb.graphic.Texture;

public abstract class Friend implements Listable{

	protected int lvl=0;
	protected int maxHeal=0;
	protected int damage = 0;
	protected int armor=0;
	protected Caractere[] name;
	protected Texture texture;
	
	public Friend() {

	}
	public int getLvl() {
		return lvl;
	}
	public int getMaxHeal() {
		return maxHeal;
	}
	public int getDamage() {
		return damage;
	}
	public int getArmor() {
		return armor;
	}
	public Caractere[] getName() {
		return name;
	}
	public Texture getTexture() {
		return this.texture;
	}
	public abstract boolean inLife();
	public abstract int getSignature();
	@Override
	public Caractere[] getListeName() {
		return name;
	}

	@Override
	public void actionListe() {
	}

	
	
}
