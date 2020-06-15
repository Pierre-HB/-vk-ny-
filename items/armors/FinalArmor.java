package fr.pierrehb.items.armors;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Dialogue;

public class FinalArmor extends Armor implements Dialogue{

	public FinalArmor() {
		super(Texture.loadTexture("res/textures/Items/FinalArmorSquare.png"),Texture.loadTexture("res/textures/Items/FinalArmor.png"),str_finalArmorName,str_finalArmorDescription);
		this.damage = 10;
		this.armor = 10;
		this.health = 30;
	}

	@Override
	public int getID() {
		return -1;
	}

}
