package fr.pierrehb.items.wands;

import fr.pierrehb.graphic.Texture;

public class BasicWand extends Wand{

	public BasicWand() {super(Texture.loadTexture("res/textures/Items/BasicWand.png"),str_basicWandName,str_basicWandDescription);
	this.damage=3;

}


@Override
public int getID() {
	return 2;
}
}
