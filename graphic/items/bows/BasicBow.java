package fr.pierrehb.items.bows;

import fr.pierrehb.graphic.Texture;

public class BasicBow extends Bow{

	public BasicBow() {
	super(Texture.loadTexture("res/textures/Items/BasicBow.png"),str_basicBowName,str_basicBowDescription);
	this.damage=4;

}


@Override
public int getID() {
	return 3;
}

}
