package fr.pierrehb.items.swords;

import fr.pierrehb.graphic.Texture;

public class BasicSword extends Sword{

	public BasicSword() {
		super(Texture.loadTexture("res/textures/Items/BasicSword.png"),str_basicSwordName,str_basicSwordDescription);
		this.damage=3;

	}
	

	@Override
	public int getID() {
		return 1;
	}

	

}
