package fr.pierrehb.entities.joueurs;

import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.attaques.attaque.Joueur_Mouse;
import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.PNJ;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.game.Team;
import fr.pierrehb.geometrie.HyperEspace;
import fr.pierrehb.geometrie.HyperPoint;
import fr.pierrehb.geometrie.hyperpoints.None;
import fr.pierrehb.geometrie.hyperpoints.State;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Listable;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Actions;
import fr.pierrehb.sound.Sound_lector;

abstract public class Joueur extends PNJ implements Actions , Listable{
	public Entity targetAdverse;
	public Entity targetAllie;
	public boolean haveAllie = false;
	public boolean haveAdverse = false;
	public boolean haveParticle = false;
	public Particle targetParticle;
	protected Joueur_Mouse mouseAdverse;
	protected Joueur_Mouse mouseAllie;
	protected Joueur_Mouse mouseParticle;
	protected List<HyperPoint> actions = new ArrayList<HyperPoint>();
	protected List<Integer> ennemyTeams = new ArrayList<Integer>();
	protected Texture cursor = Texture.loadTexture("res/textures/Human/Joueurs/Cursor.png");
	protected Texture teamCursor = Texture.loadTexture("res/textures/Human/Joueurs/TeamCursor.png");
	private int[] minColor1 = {55, 170, 0};
	private int[] maxColor1 = {100, 225, 55};
	private int[] minColor2 = {200, 200, 200};
	private int[] maxColor2 = {255, 255, 255};
	private float[] speedDie = {0.0f, 0.3f};
	private float[] speedDie1 = {1.5f, 0.0f};
	private float[] speedDie2 = {0.0f, 1.5f};
	private float[] speedDie3 = {0.0f, -1.5f};
	private float[] speedDie4 = {-1.5f, 0.0f};
	private static HyperPoint none = new None();
	protected HyperPoint act;
	public Caractere[] partyName;
	public float partyNameLenght;
	private Caractere[] listeName;
	protected boolean RENDERCURSOR=true;
	private static Sound_lector sound;
	protected int dieXp = 0;
	protected int dieGold = 0;


	


	public Joueur(float x, float y, String name, classes Classe, boolean canmove, direction direction, Texture tex, polices favorite) {
		super(x, y, name, Classe, canmove, direction, tex);
		mortal = true;
		mortalID = Main.getMortalID();
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		speed = 1.0f;
		ennemyTeams.add(-1);
		this.favorite_police = favorite;
		partyName = Renderer.getText(0, 0, name , Main.save.favorite_police, 0.2f);
		listeName=Renderer.getText(0, 0, name, polices.COMIC_SANS_MS, 0.6f);
		for(Caractere chara : partyName) partyNameLenght = (chara.getCoord()[0]+chara.getOffset())*chara.getTaille();
	}
	
	@Override
	public void trueRender() {
		humanRender();
		if(RENDERCURSOR) {Renderer.renderCursor(x, y, cursor.getid(), getCursorColor());
		if(haveParty) Renderer.renderCursor(x, y, teamCursor.getid(), Team.getTeamColor(team));
		}
	}
	protected abstract void joueurRender();
	private float[] getCursorColor() {
		return GREY;
	}
	protected abstract void resetmouse(int attaque);
	protected void doSomething(List<HyperPoint> standartCases) {
		HyperPoint state = new State((Joueur)(Human)mouse.getEntity());
		act = HyperEspace.getNearestPoint(standartCases, state);
		switch (act.action) {
		case ATTAQUE1:
			reflexDelay = random(10,25);
			resetmouse(1);
			attacks[0].startAttaque();
			break;
		case ATTAQUE2:
			reflexDelay = random(10,25);
			resetmouse(2);
			attacks[1].startAttaque();
			break;
		case ATTAQUE3:
			reflexDelay = random(10,25);
			break;
		case ATTAQUE4:
			reflexDelay = random(10,25);
			break;
		case ATTAQUE5:
			reflexDelay = random(10,25);
			break;
		case CLOSEALLIE:
			reflexDelay = random(10,25);
			resetmouse(2);
			moveNear(mouseAllie.getAngle());
			break;
		case CLOSEENNEMIE:
			reflexDelay = random(10,25);
			moveNear(mouseAdverse.getAngle());
			break;
		case ESQUIVEENNEMIE:
			reflexDelay = random(10,25);
			break;
		case ESQUIVEPARTICLE:
			reflexDelay = random(10,25);
			break;
		case FARALLIE:
			reflexDelay = random(10,25);
			moveFar(mouseAllie.getAngle());
			break;
		case FARENNEMIE:
			reflexDelay = random(10,25);
			moveFar(mouseAdverse.getAngle());

			break;
		case NONE:
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
			break;
		case STATE:
			break;
		default:
			break;
		
		}
		
	}
	@Override
	protected void die() {
		Game.level.giveXpGold(dieXp, dieGold);
		Team.playerDie((Joueur)(Human)mouse.getEntity());
		joueurDie();
		Main.save();
		life = false;
		remove = true;
		for(int a = 0; a < 100; a++) {
			Game.level.addParticle(new Smoke((int)x+8, (int)y+16, random(40, 200),random(100, 200)/200.0f, (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie1, (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie2, (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie3, (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+6+random(0,4), (int)y+14+random(0,4), random(40, 150), random(0, 100), speedDie4, (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		
		for(int a = 0; a < 400; a++) {
			Game.level.addParticle(new Smoke((int)x+random(0, 16), (int)y+random(0, 32), random(40, 150), random(0, 400), speedDie, (Human)mouse.getEntity(), minColor1, maxColor1, minColor1, maxColor1));
		}
		sound = new Sound_lector("res/sound/sou/joueurDie.wav", Main.save.soundVolume);
		sound.start();
		
	}
	protected abstract void joueurDie();
	@Override
	public void setMouse(Souris Mouse) {
		mouseAdverse = new Joueur_Mouse((Human)Mouse.getEntity());
		mouseAllie = new Joueur_Mouse((Human)Mouse.getEntity());
		mouseParticle = new Joueur_Mouse((Human)Mouse.getEntity());
		Team.iniTeam((Human)mouse.getEntity());

	}
	
	
	public void getAllieTarget(int distance) {
		float D;
		float littleD = distance +1;
		for(Entity target : Game.level.mortalObjects.values()) {
			D = (float)Math.sqrt(((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))*((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))+((y+(taille[1]/2))-(target.y+(target.taille[1]/2)))*((y+(taille[1]/2))-(target.y+(target.taille[1]/2))));
			if(D < distance && D*(target.HP/target.maxHP) < littleD && target.team == team && target.mortalID != mortalID) {
				littleD = D*(target.HP/target.maxHP);
				haveAllie = true;
				targetAllie = target;
				mouseAllie.setTarget(target);
			}
		}
	}
	protected void getAdverseTarget(int distance) {
		boolean ennemy = false;
		float D;
		float littleD = distance +1;
		for(Entity target : Game.level.mortalObjects.values()) {
			D = (float)Math.sqrt(((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))*((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))+((y+(taille[1]/2))-(target.y+(target.taille[1]/2)))*((y+(taille[1]/2))-(target.y+(target.taille[1]/2))));
			if(D < distance && D < littleD && target.team != -2 && target.team != team) {
				for(int TEAM : ennemyTeams) if(target.team == TEAM|| target.team==duelTeam) ennemy = true;
				if(ennemy) {
				littleD = D;
				haveAdverse = true;
				targetAdverse = target;
				mouseAdverse.setTarget(target);
			}}ennemy = false;
		}
	}
	protected void getParticulTarget(int distance) {
		float D;
		float littleD = distance +1;
		for(Particle target : Game.level.mortalParticles.values()) {
			D = (float)Math.sqrt(((x-(taille[0]/2))-(target.x))*((x-(taille[0]/2))-(target.x))+((y+(taille[1]/2))-(target.y))*((y+(taille[1]/2))-(target.y)));
			if(D < distance && D < littleD && target.joueur.team != team) {
				littleD = D;
				haveParticle = true;
				targetParticle = target;
				mouseParticle.setTarget(target);
			}
		}
	}
	protected abstract void addActions();
	protected List<HyperPoint> getAction() {
		actions.clear();		
		actions.add(none);		
		addActions();
		return actions;
	}
	@Override
	public void setParty(Human demandeur) {
		Team.answerForParty(true);
	}
	protected void moveFar(float Angle) {
		if(Angle<=0) moveNear(Angle+(float)Math.PI);
		else moveNear(Angle-(float)Math.PI);
		return;
	}
	protected void moveNear(float Angle) {
		//System.out.println(Angle);
		float PI = (float)Math.PI;
		if(Angle<= -PI/8) {
			if(Angle <= -5*PI/8) {
				if(Angle <= -7*PI/8) {
					vers = sens.O;
					wantMove = true;
				}else {
					vers = sens.SO;
					wantMove = true;
				}
			}else if(Angle <= -3*PI/8){
				vers = sens.S;
				wantMove = true;
			}else {
				vers = sens.SE;
				wantMove = true;
			}
		}else if(Angle<= 3*PI/8){
				if(Angle<= PI/8) {
					vers = sens.E;
					wantMove = true;
				}else {
					vers = sens.NE;
					wantMove = true;
			}
			
		}else if(Angle<= 5*PI/8) {
				vers = sens.N;
				wantMove = true;
			}else if(Angle<= 7*PI/8){
				vers = sens.NO;
				wantMove = true;
		}else {
			vers = sens.O;
			wantMove = true;
		}
		
		
		
		
		
		
		
	}
	@Override
	public Caractere[] getListeName() {
		return listeName;
	}
	@Override
	public void actionListe() {
		
	}
	@Override
	public void endDuel(int winner) {
		duelTeam=-1;
		haveAdverse=false;
		hasTarget=false;
		
	}
	@Override
	public void setDuel(int dueleur) {
		duelTeam=dueleur;
		
	}
	public abstract Friend getFriend();
	public abstract boolean askForFriend();

}
