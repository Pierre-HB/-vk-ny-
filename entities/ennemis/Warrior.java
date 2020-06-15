package fr.pierrehb.entities.ennemis;

import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.ObjectsStates;

public class Warrior extends Ennemy implements ObjectsStates{
	protected int[][] whoToReadAttackWarrior = {{1,0,0,0,0},{1,0,0,0,0,0,0,0,0,0},{1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};


	public Warrior(int x, int y) {
		super(x, y, "Warrior", classes.GUERRIER, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Ennemy/WarriorSprit.png"));
		ini();
	}
	public Warrior(int x, int y, boolean canMove, direction direction) {
		super(x, y, "Warrior", classes.GUERRIER, canMove, direction, Texture.loadTexture("res/textures/Human/Ennemy/WarriorSprit.png"));
		ini();
	}
	public Warrior(int x, int y, boolean canMove) {
		super(x, y, "Warrior", classes.GUERRIER, canMove, direction.DOWN, Texture.loadTexture("res/textures/Human/Ennemy/WarriorSprit.png"));
		ini();
	}
	private void ini() {
		HP = 5;
		maxHP = 5;
		this.dieGold=4;
		this.dieXp=25;
		classe = classes.GUERRIER;
		mortal = true;
		mortalID = Main.getMortalID();
		team = -1;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		this.invent = clearInventory;
		this.arme=1;
		this.armure=0;
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
		getTarget(48);
		}else {
			if(target.y-y>16) {
				vers = sens.S;
				wantMove = true;
				if(target.x-x>16) vers = sens.SE;
				else if(target.x-x<-16) vers = sens.SO;				
			}else if(target.y-y<-16) {
				vers = sens.N;
				wantMove = true;
				if(target.x-x>16) vers = sens.NE;
				else if(target.x-x<-16) vers = sens.NO;				
			}else if(target.x-x<-16) {
				wantMove = true;
				vers = sens.O;
			}else if(target.x-x>16) {
				wantMove = true;
				vers = sens.E;
			}else wantMove = false;
			
			if(Math.sqrt((target.x-x)*(target.x-x)+(target.y-y)*(target.y-y)) < 32) attacks[0].startAttaque();

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
