package fr.pierrehb.entities.joueurs;

import fr.pierrehb.entities.Human;
import fr.pierrehb.game.Team;
import fr.pierrehb.geometrie.hyperpoints.sparadrap.*;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.Level;
import fr.pierrehb.main.Main;

public class Sparadrap extends Joueur {
	protected int[][] whoToReadAttackSparadrap = {{1,1,0,0,0},{1,1,0,0,0,0,0,0,0,0},{1,0,0,0,0},{2,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};
	private Caractere[] SparadrapHello1;
	private Caractere[] SparadrapHello2;
	private Caractere[] sparadrapHeal;
	
	public Sparadrap(int x, int y) {
		super(x, y, str_sparadrapName, classes.MAGE, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Joueurs/SparadrapSprit.png"), polices.COMIC_SANS_MS);
		ini();
	}
	public Sparadrap(int x, int y, boolean canmove, direction direction) {
		super(x, y, str_sparadrapName, classes.MAGE, canmove, direction, Texture.loadTexture("res/textures/Human/Joueurs/SparadrapSprit.png"), polices.COMIC_SANS_MS);
		ini();
	}
	
	private void ini() {
		HP = 30+SparadrapStats.getLvl()*2;
		maxHP = 30+SparadrapStats.getLvl()*2;
		classe = classes.MAGE;
		mortal = true;
		mortalID = Main.getMortalID();
		dieGold=100;
		dieXp=500;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		this.invent = SparadrapStats.getInventory();
		this.arme=SparadrapStats.getWeapon();
		this.armure=SparadrapStats.getArmor();
		switch (level) {
		case 1:
			SparadrapHello1 = Renderer.getText(0, 0, str_SparadrapHello1, polices.COMIC_SANS_MS, 0.4f);
			SparadrapHello2 = Renderer.getText(0, 0, str_SparadrapHello2, polices.COMIC_SANS_MS, 0.4f);
		case 3:
			sparadrapHeal = Renderer.getText(0, 0, str_SparadrapHeal, polices.COMIC_SANS_MS, 0.4f);
		case 14:
			bigDialogue.add(Renderer.getText(0, 0, str_SparadrapButton, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 15:
			bigDialogue.add(Renderer.getText(0, 0, str_SparadrapCannon, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 16:
			bigDialogue.add(Renderer.getText(0, 0, str_SparadrapPostFirstBoss, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 17:
			bigDialogue.add(Renderer.getText(0, 0, str_SparadrapFirstBoss, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 18:
			bigDialogue.add(Renderer.getText(0, 0, str_sparadrap_beggeningCastel, polices.COMIC_SANS_MS, 0.4f));
			break;
		}
		for(int a = 0; a < whoToReadAttack.length; a++) {
			for(int b = 0; b < whoToReadAttack[a].length; b++) {
				whoToReadAttack[a][b] = SparadrapStats.getAttaque()[a][b];
			}
		}
	}
	@Override
	public void PNJUpdate() {
		if(hasTarget) {
			if(!ennemyTeams.contains(target.team) && target.team != duelTeam)ennemyTeams.add(target.team);
			targetAdverse = target;
			mouseAdverse.setTarget(target);
			mouse.setTarget(target);

			haveAdverse = true;
			if(haveAdverse && !ennemyTeams.contains(target.team) && duelTeam==-1)haveAdverse=false;
		}
		
		if(haveAdverse && !targetAdverse.life) {
			haveAdverse = false;
		}
		if(haveAllie && !targetAllie.life) {
			haveAdverse = false;
		}
		if(!haveAllie)getAllieTarget(128);
		if(!haveAdverse)getAdverseTarget(128);
		if(!haveParticle)getParticulTarget(128);
		doSomething(getAction());
		
	}

	@Override
	public float getAngle() {
		switch(act.action) {
		case ATTAQUE1:
			return mouseAdverse.getAngle();
		case ATTAQUE2:
			break;
		case ATTAQUE3:
			break;
		case ATTAQUE4:
			break;
		case ATTAQUE5:
			break;
		case CLOSEALLIE:
			break;
		case CLOSEENNEMIE:
			break;
		case ESQUIVEENNEMIE:
			break;
		case ESQUIVEPARTICLE:
			break;
		case FARALLIE:
			break;
		case FARENNEMIE:
			break;
		case NONE:
			break;
		case STATE:
			break;
		default:
			break;
		
		}
		return mouse.getAngle();
	}

	

	

	@Override
	protected boolean interat() {
		dialogue++;
		switch (level) {
		case 1: 
			if (dialogue == 1) {startDialogue(SparadrapHello1);}
			else if (dialogue == 2) {
				if (nextDialogue(SparadrapHello2)){
				Team.askForParty((Human)mouse.joueur, Level.player);
			}}else endDialogue();
			break;
		case 3:
			if (dialogue == 1) startDialogue(sparadrapHeal);
			else endDialogue();
			break;
				
			
		default:
			sayDialogue();
		}
		return interacting;
	}

	@Override
	public void dialogueRender() {
		switch (level) {
		case 1:
			if(dialogue == 1)	renderDialogue(SparadrapHello1);
			if(dialogue == 2)	renderDialogue(SparadrapHello2);
			break;
		case 3:
			if(dialogue == 1)	renderDialogue(sparadrapHeal);
			break;
		default:
			if(dialogue != 0)	renderDialogue(bigDialogue.get(dialogue-1));
				
		}
	}
	@Override
	protected void addActions() {
		actions.add(new Nothing());

		if(haveAllie) {
			actions.add(new CloseAllie());
			actions.add(new FarAllie());
		}
		if(haveAdverse){
			actions.add(new CloseEnnemie());
			actions.add(new FarEnnemie());
			if(!attacks[0].lock && whoToReadAttack[0][0] != 0) {
				actions.add(new Attaque1());
			}

		}
		if(haveParticle) actions.add(new EsquiveParticle());
		if(!attacks[1].lock && whoToReadAttack[0][1] != 0) actions.add(new Attaque2());
		if(!attacks[2].lock && whoToReadAttack[0][2] != 0) actions.add(new Attaque3());
		if(!attacks[3].lock && whoToReadAttack[0][3] != 0) actions.add(new Attaque4());
		if(!attacks[4].lock && whoToReadAttack[0][4] != 0) actions.add(new Attaque5());
	}
	@Override
	protected void joueurRender() {		
	}
	@Override
	protected void joueurDie() {
		Main.save.Sparadrap = false;	
		
	}
	@Override
	protected void resetmouse(int attaque) {
		switch (attaque) {
		case 1:
			mouse.setTarget(mouseAdverse.target);

			break;
		case 2:
		mouse.setTarget(mouseAllie.target);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}
	}
	@Override
	public Friend getFriend() {
		return new SparadrapFriend();
	}
	@Override
	public boolean askForFriend() {
		return true;
	}
	
	
	@Override
	public boolean askDuel(Human demandeur) {
		if(this.HP>=0.75*getMaxHP())return true;
		return false;
	}
	
	

}
