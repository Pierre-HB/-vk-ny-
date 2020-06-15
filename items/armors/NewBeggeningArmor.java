package fr.pierrehb.items.armors;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Dialogue;

public class NewBeggeningArmor extends Armor implements Dialogue{

	public NewBeggeningArmor() {
		super(Texture.loadTexture("res/textures/Items/BeggeningArmorSquare.png"),Texture.loadTexture("res/textures/Items/BeggeningArmor.png"),str_beggeningArmorName,str_beggeningArmorDescription);
		this.armor = 15;
		this.health = 60;
	}

	@Override
	public int getID() {
		return -2;
	}

}