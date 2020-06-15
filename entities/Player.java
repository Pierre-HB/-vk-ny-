package fr.pierrehb.entities;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.pierrehb.attaques.attaque.Player_Mouse;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Item;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Commands;
import fr.pierrehb.sound.Sound_lector;

public class Player extends Human implements Commands {
	float[] nb_position = {4.0f, 4.0f};
	private static boolean UP = false;
	private static boolean DOWN = false; 
	private static boolean RIGHT = false;
	private static boolean LEFT = false; 
	private static boolean INTERACT = false;
	private static boolean ATTAQUE1 = false;
	private static boolean ATTAQUE2 = false;
	private static boolean PATTAQUE1 = false;
	private static boolean PATTAQUE2 = false;
	private static boolean MENU = false;
	private static boolean pressedAttaque1 = false;
	private static boolean pressedAttaque2 = false;
	private static boolean pressedAttaque3 = false;
	private static boolean pressedAttaque4 = false;
	private static boolean pressedAttaque5 = false;

	public static click clickstate = click.NONE;
	private static int time_for_clik = 60;
	private static int timer_clik = 0;
	
	private int[] minColor1 = {55, 170, 0};
	private int[] maxColor1 = {100, 225, 55};
	private int[] minColor2 = {200, 200, 200};
	private int[] maxColor2 = {255, 255, 255};
	private float[] speedDie = {0.0f, 0.3f};
	private float[] speedDie1 = {1.5f, 0.0f};
	private float[] speedDie2 = {0.0f, 1.5f};
	private float[] speedDie3 = {0.0f, -1.5f};
	private float[] speedDie4 = {-1.5f, 0.0f};
	protected Texture teamCursor = Texture.loadTexture("res/textures/Human/Joueurs/TeamCursor.png");
	public boolean canAttack = true;
	private static Sound_lector sound;

	
	


	

	public Player(int x, int y, boolean canmove, boolean visible) {
		super(x, y, Main.save.name, Main.save.classe, canmove);
		this.VISIBLE = visible;
		ini();
	}
	public Player(int x, int y) {
		super(x, y, Main.save.name, Main.save.classe, true);
		ini();

	}
	public Player() {
		super(Main.save.coordinates[0], Main.save.coordinates[1], Main.save.name, Main.save.classe, true);
		ini();

	}

	@Override
	public void trueUpdate() {
		if(life) {
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;

		humanUpdate();
		if(canMove)standartPlayerMoves();
		if(canAct)standartPlayerActions();
		if(canAttack)lunchAttacks();
	}}

	@Override
	public void trueRender() {
		humanRender();
		if(haveParty) Renderer.renderCursor(x, y, teamCursor.getid(), Team.getTeamColor(team));
		

	}

	@Override
	protected boolean interat() {
		return false;
	}
	public Item[] getInventaire() {
		return inventaire;
	}
	@Override
	protected void die() {
		canMove = false;
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
	private void ini() {
		texture = Texture.loadTexture("res/textures/Human/Player/PlayerSprit.png");
		
		
		
		idTexture = texture.getid();
		
		maxHP = 30;
		this.gold=Main.save.playergold;
		setXP(Main.save.playerXP);
		HP = Main.save.playerHP;
		mortal = true;
		mortalID = Main.getMortalID();
		team = Team.playerTeam;
		haveParty = Team.playerHaveTeam;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		
		
	}
	@Override
	public void dialogueRender() {
		
	}
	@Override
	public void setMouse(Human joueur) {

		mouse = new Player_Mouse(joueur);
		MOUSE = true;
		iniAttaques(Main.save.playerAttacks);
		iniInventory(Main.save.playerInventory,Main.save.playerWeapon,Main.save.playerArmor);
		
	}
	
	
	public void lunchAttacks() {
		if(!ATTAQUE1 && !ATTAQUE2 && clickstate != click.NONE) {
			timer_clik++;
		}
		if(timer_clik > time_for_clik) {
			clickstate = click.NONE;
			timer_clik = 0;			
		}
		if(PATTAQUE1 && !ATTAQUE1 && clickstate == click.L) {
			attacks[0].startAttaque();
			clickstate = click.NONE;
		}
		if((PATTAQUE2 && !ATTAQUE2) || (PATTAQUE1 && !ATTAQUE1)) {
			switch(clickstate) {
			case RRR:attacks[1].startAttaque();clickstate = click.NONE;break;
			case RRL:attacks[2].startAttaque();clickstate = click.NONE;break;
			case RLR:attacks[3].startAttaque();clickstate = click.NONE;break;
			case RLL:attacks[4].startAttaque();clickstate = click.NONE;break;
			default:
			}
			
		}
		PATTAQUE1 = ATTAQUE1;
		PATTAQUE2 = ATTAQUE2;
	}
	public void attaque1() {

		switch(clickstate) {
		case NONE:clickstate = click.L;attacks[0].prepareAttaque();return;
		case R:clickstate = click.RL;timer_clik = 0;return;
		case RL:clickstate = click.RLL;attacks[4].prepareAttaque();return;
		case RR:clickstate = click.RRL;attacks[2].prepareAttaque();return;
		default:return;
		}
		
	}
	public void attaque2() {
		switch(clickstate) {
		case NONE:clickstate = click.R;timer_clik = 0;return;
		case R:clickstate = click.RR;timer_clik = 0;return;
		case RL:clickstate = click.RLR;attacks[3].prepareAttaque();return;
		case RR:clickstate = click.RRR;attacks[1].prepareAttaque();return;
		default:return;
		
		}
	}
	public void standartPlayerActions() {
		// on regarde s'il interagit
				if((interactkey != -1 && Keyboard.isKeyDown(interactkey)) || Mouse.isButtonDown(interactmouse)) {
				if(!INTERACT && Main.save.avancement>1) Game.level.interact();
				INTERACT = true;
				}else INTERACT = false;
				
				if((inventairekey != -1 && Keyboard.isKeyDown(inventairekey)) || Mouse.isButtonDown(inventairemouse)) {
					if (!MENU && Main.save.avancement>1) {
						Interface.onpenMenu();
					}
					MENU=true;
					
				}else MENU=false;
				
				if(canAttack) {
				if((attaque1key != -1 && Keyboard.isKeyDown(attaque1key)) || Mouse.isButtonDown(attaque1mouse)) {
					if(!ATTAQUE1) attaque1();
					ATTAQUE1 = true;
					}else ATTAQUE1 = false;
				
				if((attaque2key != -1 && Keyboard.isKeyDown(attaque2key)) || Mouse.isButtonDown(attaque2mouse)) {
					if(!ATTAQUE2) attaque2();
					ATTAQUE2 = true;
					}else ATTAQUE2 = false;
				
				if(Keyboard.isKeyDown(attaque1)) {
					attacks[0].prepareAttaque();
					pressedAttaque1=true;
				}else {
					if(pressedAttaque1)attacks[0].startAttaque();
					pressedAttaque1=false;
				}
				
				if(Keyboard.isKeyDown(attaque2)){
					attacks[1].prepareAttaque();
					pressedAttaque2=true;
				}else {
					if(pressedAttaque2) {
						attacks[1].startAttaque();
						pressedAttaque2=false;
					}
				}
				
				if(Keyboard.isKeyDown(attaque3)){
					attacks[2].prepareAttaque();
					pressedAttaque3=true;
				}else {
					if(pressedAttaque3) {
						attacks[2].startAttaque();
						pressedAttaque3=false;
					}
				}
				
				if(Keyboard.isKeyDown(attaque4)){
					attacks[3].prepareAttaque();
					pressedAttaque4=true;
				}else {
					if(pressedAttaque4) {
						attacks[3].startAttaque();
						pressedAttaque4=false;
					}
				}
				
				if(Keyboard.isKeyDown(attaque5)){
					attacks[4].prepareAttaque();
					pressedAttaque5=true;
				}else {
					if(pressedAttaque5) {
						attacks[4].startAttaque();
						pressedAttaque5=false;
					}
				}
				
				}
	}
	
	public void standartPlayerMoves() {
		
		
		
		
		

		
		// on regarde si on appuit sur la touche du haut
		if(directionV == cardinalNS.UP) directionV = cardinalNS.NONE;
		if(( upkey != -1 && Keyboard.isKeyDown(upkey)) || Mouse.isButtonDown(upmouse)) {
			UP = true;
			if ( directionV != cardinalNS.DOWN) directionV = cardinalNS.UP;
			if(DOWN && directionV == cardinalNS.UP) {
			}else {
			moveY(-1);
				
			}
		}else UP = false;
// on regarde si on appui sur la touche du bas
		if(directionV == cardinalNS.DOWN) directionV = cardinalNS.NONE;
		if((downkey != -1 && Keyboard.isKeyDown(downkey)) || Mouse.isButtonDown(downmouse)) {
			DOWN = true;
			if ( directionV != cardinalNS.UP) directionV = cardinalNS.DOWN;
			if(UP && directionV ==cardinalNS.DOWN) {
			}else {
			moveY(1);
			
			}
		}else DOWN = false;
		// on vérifie que l'on ne se prend pas de mur
	

		if(directionH == cardinalEO.RIGHT) directionH = cardinalEO.NONE;
		if((rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
			RIGHT = true;
			if ( directionH != cardinalEO.LEFT) directionH = cardinalEO.RIGHT;
			if(LEFT && directionH ==cardinalEO.RIGHT) {
			}else {
				moveX(1);

		
			
			}
		}else RIGHT = false;
		
		
		if(directionH == cardinalEO.LEFT) directionH = cardinalEO.NONE;
		if((leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
			LEFT = true;
			if ( directionH != cardinalEO.RIGHT) directionH = cardinalEO.LEFT;
			if(RIGHT && directionH ==cardinalEO.LEFT) {
			}else {
				moveX(-1);

			
			
			}
		}else LEFT = false;
		if (!walking) {
			setMoving(false);

		}
		
		walking = false;
		
		
		
	
	
}
	@Override
	public float getAngle() {
		return mouse.getAngle();
	}
	@Override
	public void setParty(Human demandeur) {
		Interface.startAskParty((Joueur)demandeur);
	}
	public int[] getInventory() {
		int[] inv = new int[inventorySize];
		for(int i =0;i<inventorySize;i++) {
			inv[i] = inventaire[i].getID();
		}
		return inv;
	}
	public int getWeapon() {
		return weapon.getID();
	}
	public int getArmor() {
		return armor.getID();
	}
	@Override
	public void setDuel(int dueleur) {
		Interface.endDuelDelay++;
	}
	public boolean askDuel(Human dueleur) {
		Interface.startAskDuel((Joueur)dueleur);
		return false;
	}
	@Override
	public void endDuel(int winner) {
		Game.level.getInterface().renderEndDuel(winner==team);
		
	}
	private void setXP(int xp) {
		this.xp+=xp;
		while (this.xp>=50*2*(lvl+1)*(lvl+1)){
			lvl+=1;
			if(name==Main.save.name) {
			this.maxHP+=4;
			if(HP>maxHP)HP=maxHP;
			}
		}
	}
	@Override
	protected void renderGiver(String text) {		
		Interface.addGivers(text);
	}
	@Override
	protected void renderLevelUp() {
		Game.level.getInterface().renderLevelUp();
	}

}
