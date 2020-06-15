package fr.pierrehb.entities;

import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.attaques.attaque.Joueur_Mouse;
import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Texture;

abstract public class PNJ extends Human {
	protected int[][] whoToReadAttack = {{0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};
	protected int reflex = 0;
	protected int reflexDelay = 0;
	protected boolean wantMove = false;
	protected float speed = 0.8f;
	protected int event = 0;
	protected int dialogue = 0;
	protected int[] lowReflex = {40,60};
	protected int[] hightReflex= {25,40};
	protected List<Caractere[]> bigDialogue = new ArrayList<Caractere[]>();
	protected int[] invent;
	protected int arme;
	protected int armure;
	
	
	
	public PNJ(float x, float y, String name, classes Classe, boolean canmove, direction direction, Texture tex) {
		super(x, y, name, Classe, canmove, direction);
		texture = tex;
		idTexture = texture.getid();
		
	}
	

	@Override
	public void trueUpdate() {
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		humanUpdate();
		if(reflex >= reflexDelay) {
			reflex = 0;
			if(!hasTarget)reflexDelay = random(lowReflex[0], lowReflex[1]);
			else reflexDelay = random(hightReflex[0], hightReflex[1]);
		PNJUpdate();
		}else reflex++;
		if(wantMove) {
		switch(vers) {
		case N:
			moveY(-speed);
			break;
		case S :
			moveY(speed);
			break;
		case O:
			moveX(-speed);
			break;
		case E:
			moveX(speed);
			break;
		case NO:
			moveY(-speed);
			moveX(-speed);
			break;
		case NE :
			moveY(-speed);
			moveX(speed);
			break;
		case SE:
			moveY(speed);
			moveX(speed);
			break;
		case SO:
			moveY(speed);
			moveX(-speed);
			break;
		default:
			
		}}else moving = false;
	}
	public abstract void PNJUpdate();

	@Override
	public void setMouse(Human joueur) {

		mouse = new Joueur_Mouse(joueur);
		MOUSE = true;
		iniAttaques(whoToReadAttack);
		setMouse(mouse);
		iniInventory(invent, arme,armure);
	}
	protected abstract void setMouse(Souris Mouse);
	
	protected boolean nextDialogue(Caractere[] text) {
		if(Annimation.stopDialogue()) {
			Annimation.startDialogue(text.length);
			return true;
		}
		dialogue--;
		return false;
	}
	protected void startDialogue(Caractere[] text) {
			Annimation.startDialogue(text.length);
			Game.getPlayer().canMove = false;
			canMove = false;
			interacting = true;
	}
	protected boolean endDialogue() {
		if (Annimation.stopDialogue()) {
			Game.getPlayer().canMove = true;
			canMove = true;
			dialogue = 0;
			interacting = false;
			return true;
		}else {
			dialogue--;
			return false;
		}
	}
	protected void sayDialogue() {
		if (dialogue == 1) startDialogue(bigDialogue.get(0));
		else if (dialogue > 1 && dialogue < bigDialogue.size()+1) nextDialogue(bigDialogue.get(dialogue-1));
		else endDialogue();
	}
	@Override
	protected void renderGiver(String text) {
		
	}
	@Override
	protected void renderLevelUp() {
		
	}
	

}
