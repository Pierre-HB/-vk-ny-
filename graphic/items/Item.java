package fr.pierrehb.items;


import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Listable;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Polices.polices;
import fr.pierrehb.other.Dialogue;


public abstract class Item implements Dialogue, Listable{
	protected Texture texture;
	protected Caractere[] name;
	protected Caractere[] listeName;
	protected Caractere[] description;
	protected Caractere[] actionName;
	protected Caractere[] throwing = Renderer.getText(160, 117, str_jetter, polices.COMIC_SANS_MS, 0.6f);
	protected Caractere[] back = Renderer.getText(55, 117, str_retourItem, polices.COMIC_SANS_MS, 0.6f);
	protected int locationInInventory;
	protected Human owner;
	public String itemName;
	
	public Item(Texture texture, String itemName, String description, String action) {
		this.texture = texture;
		this.listeName = Renderer.getText(0, 0, itemName, polices.COMIC_SANS_MS, 0.6f);
		this.name = Renderer.getText(100, 25, itemName, polices.COMIC_SANS_MS, 0.8f);
		this.description = Renderer.getText(55, 52, description, polices.COMIC_SANS_MS, 0.4f);
		this.actionName = Renderer.getText(108, 117, action, polices.COMIC_SANS_MS, 0.6f);
		this.itemName= itemName;
	}

	public Caractere[] getName() {
		return name;
	}
	public Caractere[] getDescription() {
		return description;
	}
	public Caractere[] getActionName() {
		return actionName;
	}
	public Caractere[] getThrowingName() {
		return throwing;
	}
	public Caractere[] getBack() {
		return back;
	}
	
	public abstract void action();
	
	public void setLocation(int location) {
		this.locationInInventory=location;
	}
	
	public Texture getTexture() {
	return texture;
	}

	public void setOwner(Human owner) {
		this.owner=owner;
		
	}
	public void removeSelf() {
		owner.removeItem(locationInInventory);
	}
	public abstract int getID();
	
	public Caractere[] getListeName() {
		return listeName;
	}
	public void actionListe() {
		action();
		}
		
	

}
