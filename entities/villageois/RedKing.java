package fr.pierrehb.entities.villageois;

import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.BossDie;


public class RedKing  extends Villageoi{



	public RedKing(int x, int y,  boolean canmove, direction direction) {
		super(x, y, str_redKingName, canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/RedKingSprit.png"));
		ini();
	}
	public RedKing(int x, int y, direction direction   ) {
		super(x, y, str_redKingName, true, direction, Texture.loadTexture("res/textures/Human/Villageois/RedKingSprit.png"));
		ini();
	}
	public RedKing(int x, int y ) {
		super(x, y, str_redKingName, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Villageois/RedKingSprit.png"));
		ini();
	}
	private void ini() {
		
		mortal = true;
		mortalID = Main.getMortalID();
		switch (level) {

		case -1:
			bigDialogue.add(Renderer.getText(0, 0, "test", polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, "bla", polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, "bla", polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, "bla...", polices.COMIC_SANS_MS, 0.4f));

			
			break;

			
		}	
		HP = 20;

	}
	@Override
	protected boolean interat() {
		dialogue++;
		switch (level) {
		default:
			sayDialogue();
		}
return interacting;
}



	
	@Override
	public void trueRender() {
		humanRender();

	}
	@Override
	protected void die() {
		life = false;
		remove = true;
		BossDie.bossDie(x, y);
	}
	@Override
	public void dialogueRender() {
		switch (level) {
		default:
			if(dialogue != 0)	renderDialogue(bigDialogue.get(dialogue-1));

			}
	}
	@Override
	public void PNJUpdate() {
		switch(level) {
		case 2:
			if (event == 1) {
				wantMove = true;
				vers = sens.O;
			}break;
		case 1:
			if (x < 160 && x > 126) {
				wantMove = true;
				vers = sens.NO;
			}else if( x < 160 ) {
				wantMove = true;
				vers = sens.N;
			}else {
				wantMove = true;
				vers = sens.O;
			}break;

			
				
		}
		
		
	}
	@Override
	public float getAngle() {
		return mouse.getAngle();
	}
	@Override
	public void setDuel(int dueleur) {
		
	}
	@Override
	public void endDuel(int winner) {
		
	}
	@Override
	public boolean askDuel(Human demandeur) {
		return false;
	}
	
	
}