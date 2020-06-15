package fr.pierrehb.items.others;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Item;

public class Candy extends Item {

	public Candy() {
		super(Texture.loadTexture("res/textures/Items/Candy.png"),str_candyName,str_candyDescription,str_manger);
		this.itemName="Bonbon";
	}

	@Override
	public void action() {
		owner.getHeal(15);
		owner.removeItem(locationInInventory);
		
	}

	@Override
	public int getID() {
		return 5;
	}

}
