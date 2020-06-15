package fr.pierrehb.items.knifes;

import fr.pierrehb.graphic.Texture;

public class BasicKnife extends Knife{

	public BasicKnife() {
		super(Texture.loadTexture("res/textures/Items/BasicKnife.png"),str_basicKnifeName,str_basicKnifeDescription);
	this.damage=3;

}


@Override
public int getID() {
	return 4;
}
}
