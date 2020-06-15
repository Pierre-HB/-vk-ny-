package fr.pierrehb.entities.villageois;



import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.PNJ;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Texture;

abstract public class Villageoi extends PNJ {

	public Villageoi(int x, int y, String name, boolean canmove, direction direction, Texture tex) {
		super(x, y, name, classes.NONE, canmove, direction, tex);
		team = -2;
		hightReflex[0]=1;
		hightReflex[1]=1;
		lowReflex[0]=1;
		lowReflex[1]=1;
		this.invent = clearInventory;
		this.arme=0;
		this.armure=0;
	}
	@Override
	public void setMouse(Souris Mouse) {
	}
	@Override
	public void setParty(Human demandeur) {
		Team.answerForParty(false);
	}
	
}
