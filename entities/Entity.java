package fr.pierrehb.entities;

import fr.pierrehb.entities.ennemis.Ennemy;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.ObjectsStates;
import fr.pierrehb.sound.Sound_lector;

public abstract class Entity implements Comparable<Entity>, Color, ObjectsStates {
	public float x,y;
	protected String name;
	protected Texture texture;
	public int idTexture;
	public boolean remove = false;
	public float[] coord = new float[2];
	protected int[] rez = Main.save.size;
	public boolean canMove;
	public boolean cantMove = false;
	public int HP, maxHP;
	public int[] taille;
	public boolean life = true;
	protected int healthTimer;
	private int timehealth = 60;
	public int[] hitBox = {0,0,0,0};
	public boolean mortal = false;
	public int team = -3;
	public int mortalID = 2147483647;
	public health_state healthState = health_state.NONE;
	public int shieldLife = 0;
	protected Human target;
	protected boolean hasTarget = false;
	protected float[] color = WHITE;
	protected boolean VULNERABLE = true;
	private static Sound_lector sound;



	


	
	public Entity(float x, float y, int[] taille, String name, boolean canmove) {
		this.x= x;
		this.y = y;
		this.name = name;
		coord[0] = x;
		coord[1] = y;
		this.canMove = canmove;
		this.taille = taille;

	}
	public abstract void start();
	public float[] getCoord() {
		float[] coord = {x+taille[0]/2, y+taille[1]/2};
		return coord;
	}
	protected abstract void die();
	public abstract void trueUpdate();
	public void update() {
		if(hasTarget && !target.life) hasTarget = false;
		if(!remove){
		if(HP <= 0) {
			if(mortal)Game.level.mortalObjects.remove(mortalID);
			if(life) {
			die();
			Main.save();}
			life = false;
		}
		if(healthState != health_state.NONE) {
			healthTimer++;
			if(healthTimer > timehealth) {
				healthTimer = 0;
				healthState = health_state.NONE;
				
			}
		}
		trueUpdate();
		attackUpdate();
		}
	}
	public int[] getHitBox() {
		return hitBox;
	}
	
	protected abstract void attackRender();
	protected abstract void attackUpdate();
	public abstract void trueRender();
	public void render(){
		if(!remove) {
		int tx= (int)Game.translateView[0];
		int ty = (int)Game.translateView[1];
		int x2 = (int)x*rez[0]/256;
		int y2 = (int)y*rez[1]/144;
		int xoffset = taille[0]*rez[0]/256;
		int yoffset = taille[1]*rez[1]/144;
		attackRender();
		if(x2+xoffset < -tx-rez[0]/16 || y2+yoffset < -ty-rez[1]/9 || x2 > -tx+rez[0] || y2 > -ty+rez[1]) return;
		trueRender();
		return;
		}
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
		coord[0] = x;
	}
	public void setY (float y) {
		this.y = y;
		coord[1] = y;
	}
	
	public Texture getTexture() {
		return texture;
	}
	public int getTextureID() {
		return texture.getid();
	}
	public String getName() {
		return name;
	}
	
	protected abstract int getBottomY();
	
	@Override
	public int compareTo(Entity other) {
		return (this.getBottomY() < other.getBottomY()) ? -1 : ((this.getBottomY() == other.getBottomY()) ? 0 : 1); // permet de classer les entités des plus haute (sur Y) au plus basse
	}
	protected abstract int getShield();
	public void getDamage(int damage, Entity attaquant) {
		if(VULNERABLE) {
		if(((attaquant.getClass().getName() == Player.class.getName()) ||
			(attaquant.getClass().getSuperclass().getName() == Joueur.class.getName()) ||
			(attaquant.getClass().getSuperclass().getName() == Ennemy.class.getName())) && 
			attaquant.team != team && !hasTarget) {
			target = (Human)attaquant;
			hasTarget = true;
		}
		if(damage != 0) {
			if(name==Main.save.name) {
				sound = new Sound_lector("res/sound/sou/hit.wav", Main.save.soundVolume);
				sound.start();
			}
			damage -= (shieldLife+getDefense());
			if(damage<0)return;
		
		HP-=damage;
		healthState = health_state.DAMAGE;
		healthTimer = 0;
	}}}
	protected abstract int getDefense();
	public void getHeal(int heal) {
		if(heal > 0 && HP < getMaxHP()) {
			if(HP+heal > getMaxHP()) HP = getMaxHP();
			else HP+=heal;
		healthState = health_state.HEAL;
		healthTimer = 0;
		
	}}
	protected void actualiseHP() {
		if(HP > getMaxHP()) HP = getMaxHP();
	}
	public abstract int getHealthBonus();
	public int getMaxHP() {
		return this.maxHP+getHealthBonus();
	}
	abstract public boolean interact();
	abstract protected boolean interat();
	abstract public void dialogueRender();
	protected float[] getHealthColor() {
		if(life) {
			if(healthTimer == 0) return color;
			return Annimation.getDamageColor(healthTimer, healthState, color);
		}
		return this.color;

	}
	protected int random(int min, int max) {
		return (int)(Math.random()*(max+1-min)+min);
	}
}
