package fr.pierrehb.entities.ennemis;

import fr.pierrehb.graphic.Texture;
import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.ObjectsStates;

public class Rafter extends Ennemy implements ObjectsStates{
	protected int[][] whoToReadAttackWarrior = {{1,0,0,0,0},{1,0,0,0,0,0,0,0,0,0},{1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};


	public Rafter(int x, int y) {
		super(x, y, "Rafter", classes.ARCHER, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Ennemy/RafterSprit.png"));
		ini();
	}
	public Rafter(int x, int y, boolean canMove, direction direction) {
		super(x, y, "Rafter", classes.ARCHER, canMove, direction, Texture.loadTexture("res/textures/Human/Ennemy/RafterSprit.png"));
		ini();
	}
	public Rafter(int x, int y, boolean canMove) {
		super(x, y, "Rafter", classes.ARCHER, canMove, direction.DOWN, Texture.loadTexture("res/textures/Human/Ennemy/RafterSprit.png"));
		ini();
	}
	private void ini() {
		HP = 5;
		maxHP = 5;
		this.dieGold=4;
		this.dieXp=25;
		//classe = classes.ARCHER;
		mortal = true;
		mortalID = Main.getMortalID();
		team = -1;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		this.invent = clearInventory;
		this.arme=3;
		this.armure=0;
		//iniInventory(clearInventory,3,0);
		for(int a = 0; a < whoToReadAttack.length; a++) {
			for(int b = 0; b < whoToReadAttack[a].length; b++) {
				whoToReadAttack[a][b] = whoToReadAttackWarrior[a][b];
			}
		}
	}
	

	@Override
	public void trueRender() {
		humanRender();
	}
	@Override
	public void PNJUpdate() {
		
	if(!hasTarget) {
		switch(random(0,20)) {
		case 0:
			vers = sens.N;
			wantMove = true;
			break;
		case 1:
			vers = sens.S;
			wantMove = true;
			break;
		case 2:
			vers = sens.O;
			wantMove = true;
			break;
		case 3:
			vers = sens.E;
			wantMove = true;
			break;
		case 4:
			vers = sens.NO;
			wantMove = true;
			break;
		case 5:
			vers = sens.NE;
			wantMove = true;
			break;
		case 6:
			vers = sens.SE;
			wantMove = true;
			break;
		case 7:
			vers = sens.SO;
			wantMove = true;
			break;
		default:
			wantMove = false;
		}
		getTarget(128);
		}else {

			if(target.y-y>64) {
				vers = sens.S;
				wantMove = true;
				if(target.x-x>64) vers = sens.SE;
				else if(target.x-x<-64) vers = sens.SO;				
			}else if(target.y-y<-64) {
				vers = sens.N;
				wantMove = true;
				if(target.x-x>64) vers = sens.NE;
				else if(target.x-x<-64) vers = sens.NO;				
			}else if(target.x-x<-64) {
				wantMove = true;
				vers = sens.O;
			}else if(target.x-x>64) {
				wantMove = true;
				vers = sens.E;
			}else wantMove = false;
			
			if(Math.sqrt((target.x-x)*(target.x-x)+(target.y-y)*(target.y-y)) < 128) {
				if(random(0,1) == 0) attacks[0].startAttaque();
			}
		}
	}
	@Override
	public float getAngle() {
		return mouse.getAngle()+(float)(Math.random()-0.5);
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
