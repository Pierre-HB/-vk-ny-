package fr.pierrehb.entities;



import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.attaques.Attaque;
import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.attaques.attaque.Lock;
import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.attaques.attaque.archer.Fléche;
import fr.pierrehb.attaques.attaque.archer.Recul;
import fr.pierrehb.attaques.attaque.guerrier.Parade;
import fr.pierrehb.attaques.attaque.guerrier.Sword_Hit;
import fr.pierrehb.attaques.attaque.mage.Fire_Ball;
import fr.pierrehb.attaques.attaque.mage.Heal;
import fr.pierrehb.attaques.attaque.ninja.Dash;
import fr.pierrehb.attaques.attaque.ninja.Knife_Hit;
import fr.pierrehb.entities.particles.Tunder;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Commands.cardinalEO;
import fr.pierrehb.other.Commands.cardinalNS;
import fr.pierrehb.sound.Sound_lector;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.other.ObjectsStates;
import fr.pierrehb.other.Polices;
import fr.pierrehb.items.Equipement;
import fr.pierrehb.items.Item;
import fr.pierrehb.items.NoItem;
import fr.pierrehb.items.armors.Armor;
import fr.pierrehb.main.Main;


public abstract class Human extends Entity implements Polices, Dialogue, ObjectsStates {
	protected direction orientation;
	protected sens vers;
	protected boolean moving = false;
	protected int move = 0 ;
	protected float[] nb_position = {5.0f, 8.0f};
	protected int level;
	public boolean interacting = false;
	public Caractere[] writableName;
	public polices favorite_police = polices.COMIC_SANS_MS;
	public static int[] taille = {16, 32};
	public Souris mouse;
	public boolean MOUSE = false;
	protected List<List<Attack>> attaques = new ArrayList<List<Attack>>();
	public Attaque[] attacks = new Attaque[5];
	public classes classe = classes.NONE;
	public boolean canAttack = true;
	public boolean canAct = true;
	public boolean walking = false;
	public cardinalEO directionH;
	public cardinalNS directionV;
	public boolean haveParty = false;
	protected static int inventorySize = 16;
	protected Item[] inventaire = new Item[inventorySize];
	public Equipement weapon;
	public Armor armor;
	protected static final int[] clearInventory = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	protected int xp=0;
	protected int lvl=0;
	protected int gold=0;
	protected int duelTeam=0;
	protected boolean VISIBLE=true;
	private boolean TUNDER = false;
	private int loadTunder = 0;
	private int chargeTunder = 300;
	private static final int tunderRange = 64;
	private static final Texture tunderBeam = Texture.loadTexture("res/textures/Items/TunderBeam.png");
	private static final float[] tunderDimension = {2,1};
	private int[] tunderTaille = new int[2];
	private float[] semiWhite = {1f, 1f, 1f, 1f};
	private int tunderX = 0;
	private int healerCunt = 0;
	private int healDelay = 0;
	private int HPperHeal = 0;
	public boolean HEALING = false;
	private static Sound_lector song;
	
	


	
	public Human(float x, float y, String name, classes Classe, boolean canmove) {
		super(x, y, taille, name, canmove);
		orientation = direction.DOWN;
		this.classe = Classe;
		ini();
	}
	public Human(float x, float y, String name, classes Classe, boolean canmove, direction Direction) {
		super(x, y, taille, name, canmove);
		orientation = Direction;
		this.classe = Classe;
		ini();
	}

	private void updateArmorEffect() {
		if(armor.getID()==-1)TUNDER=true;
		else TUNDER = false;
		if(armor.getID()==-2) {
			HEALING=true;
			healDelay=60;
			HPperHeal=2;
		}
		else HEALING=false;
	}
	public void humanUpdate() {
		if(MOUSE)mouse.update();
		moving();
		updateArmorEffect();
		
		if(TUNDER) {
			if(loadTunder>= chargeTunder)tunder(tunderRange);
			else loadTunder++;
			if(loadTunder<chargeTunder/2) {
				semiWhite[3] = 0f;
			}else if(loadTunder<chargeTunder) {
				tunderX=0;
				semiWhite[3] = 0.2f;
			}else {
				tunderX=1;
				semiWhite[3] = 0.4f;
			}
		}
		if(HEALING) {
			healerCunt++;
			if(healDelay<=healerCunt) {
				if(HP+HPperHeal > getMaxHP()) HP = getMaxHP();
				else HP+=HPperHeal;
				healerCunt = 0;
				
			}
		}
		
		
	}
	@Override
	protected int getDefense() {
		return armor.getArmor();
	}
	public abstract void setParty(Human demandeur);
	public abstract void setDuel(int dueleur);
	public abstract void endDuel(int winner);
	public abstract boolean askDuel(Human demandeur);
	public void humanRender() {
		if(VISIBLE) {
		Renderer.renderHuman((int)x, (int)y,Annimation.getHumanColumn(move, moving), Annimation.getHumanLine(orientation, moving), nb_position, idTexture, getHealthColor());
		Renderer.renderHuman((int)x, (int)y,Annimation.getHumanColumn(move, moving), Annimation.getHumanLine(orientation, moving), nb_position, armor.getEquipeRender().getid(), getHealthColor());
		if(TUNDER) {
			
			tunderBeam.bind();
			Renderer.renderObject((int)x-8, (int)y-16, tunderX, 0, tunderDimension, tunderTaille, semiWhite);
			tunderBeam.unbind();
		}
		}}
	
	public Caractere[] getWritableName(){
		return writableName;
	}
	public void setDirection(direction dir) {
		orientation = dir;
	}
	public void setMoving(boolean move) {
		moving = move;
	}
	protected void moving() {
		if(moving) move++;
		else move = 0;
	}
	private void ini() {
		level = Game.lvl;
		writableName = Renderer.getText(0, 0, name +" :", favorite_police, 0.3f);
		switch(classe) {
		case GUERRIER:
			shieldLife += 1;
			break;
		case MAGE:
			break;
		case ARCHER:
			break;
		case NINJA:
			shieldLife += 1;
			break;
		default:
		}
		tunderTaille[0]=taille[0]+16;
		tunderTaille[1]=taille[1]+32;


	}
	@Override
	public int getShield() {
		return armor.getArmor();
		}
	@Override
	public int getHealthBonus() {
		return weapon.getHealth()+armor.getHealth();
	}
	
	public int getDamage() {
		return weapon.getDamage()+armor.getDamage();
		}
	protected void renderDialogue(Caractere[] text) {
		Renderer.renderDialogue(text, writableName, texture.getid(), armor.getEquipeRender().getid());		
	}
	protected void iniAttaques(int[][] whoToReadAttack) {
		
		

		for(int a = 0; a < whoToReadAttack.length-2; a++) {
			attaques.add(new ArrayList<Attack>());
			for(int b = 0; b < whoToReadAttack[a+2].length; b++) {
				attaques.get(a).add(getAttack(whoToReadAttack[a+2][b], whoToReadAttack));
			}
			attacks[a] = new Attaque(attaques.get(a));
		}
	}
	protected void iniInventory(int[] inventory, int weap, int armo) {
		this.weapon=(Equipement)Game.getItem(weap);
		this.weapon.setOwner((Human)mouse.getEntity());
		this.weapon.equipe();
		
		this.armor=(Armor)Game.getItem(armo);
		this.armor.setOwner((Human)mouse.getEntity());
		this.armor.equipe();

		for(int i=0;i<inventorySize;i++) {
			inventaire[i]=Game.getItem(inventory[i]);
			inventaire[i].setLocation(i);
			inventaire[i].setOwner((Human)mouse.getEntity());
		}
	}
	public abstract float getAngle();
	
	
	
	public void moveX(float X) {
		if(canMove && !cantMove) {
		float xp = getX();
		float yp = getY();
		float xa = 0;
		float xHitBox1 = xp+1;
		float xHitBox2 = (xp+15);
		float yHitBox1 = (yp+17);
		float yHitBox2 = (yp+31);
		if(X<0) {
			xa = X;
			xHitBox1 = (xp+xa);
			xHitBox2 = xHitBox1;
			setDirection(direction.LEFT);
			setMoving(true);

			walking = true;
		}else {
			xa = X;
			xHitBox1 = (xp+16+xa);
			xHitBox2 = xHitBox1;
			setDirection(direction.RIGHT);
			setMoving(true);
			walking = true;
		}
		if(xHitBox1/16 >= Game.level.size[0] || xHitBox2/16 >= Game.level.size[0] ||
				xHitBox1 <= 0 || xHitBox2 <= 0) {
			
			xa = 0;
			setMoving(false);

		}else {
		
			
			 if(Game.level.getSolid((int)xHitBox1/16, (int)yHitBox1/16) || Game.level.getSolid((int)xHitBox2/16, (int)yHitBox2/16)){

				xa = 0;
				setMoving(false);


				
			}}
		setX(xp + xa);
	}}
	
	public void moveY(float Y) {
		if(canMove && !cantMove) {
		float xp = getX();
		float yp = getY();
		float ya = 0;
		float xHitBox1 = xp+1;
		float xHitBox2 = (xp+15);
		float yHitBox1 = (yp+17);
		float yHitBox2 = (yp+31);
		
		if(Y<0) {
			ya = Y;
			yHitBox1 = (yp+16+ya);
			yHitBox2 = yHitBox1;
			
			setDirection(direction.UP);;
			setMoving(true);
			walking = true;
		}else {
			ya = Y;
			yHitBox1 = (yp+32+ya);
			yHitBox2 = yHitBox1;
			setDirection(direction.DOWN);;
			setMoving(true);
			walking = true;	
		}
		if(yHitBox1/16 >= Game.level.size[1] || yHitBox2/16 >= Game.level.size[1] ||
				yHitBox1 <= 0 || yHitBox2 <= 0) {
			ya = 0;
			setMoving(false);

		}
		else if(Game.level.getSolid((int)xHitBox1/16, (int)yHitBox1/16) || Game.level.getSolid((int)xHitBox2/16, (int)yHitBox2/16)){ // ligne modifier si ca marche pas remplacer getSolid par "solidList" et remplacer les argument par des crochet pour acceder au tableau
			
			ya = 0;
			setMoving(false);



		}
		setY(yp + ya);

		
	}}
	
	
	
	
	@Override
	public boolean interact() {
		if(life && (interacting || Math.abs(Game.getPlayer().coord[0]-x) < 16 && Math.abs(Game.getPlayer().coord[1]-y) < 16)) {
			
			return interat();
		}
		return false;
	}
	@Override
	public void attackRender() {
		for(int a = 0; a < attacks.length; a++)	attacks[a].render();
	}
	@Override
	public void attackUpdate() {
		for(int a = 0; a < attacks.length; a++)	attacks[a].update();
	}
	public abstract void setMouse(Human joueur);
	
	
	public Attack getAttack(int attaque, int[][] whoToReadAttack) {
		switch(classe) {
		case NONE:
			return new Lock((Human)mouse.getEntity());
			
			
		case GUERRIER:
			switch(attaque) {
			case -1:
				return new Lock((Human)mouse.getEntity());
			case 0 :
				return new Lock((Human)mouse.getEntity());
			case 1 :
				return new Sword_Hit((Human)mouse.getEntity(), whoToReadAttack[1][0]);
			case 2 :
				return new Parade((Human)mouse.getEntity(), whoToReadAttack[1][1]);
				
			}
			
		case MAGE:
			switch(attaque) {
			case -1:
				return new Lock((Human)mouse.getEntity());
			case 0:
				return new Lock((Human)mouse.getEntity());
			case 1 :

				return new Fire_Ball((Human)mouse.getEntity(), whoToReadAttack[1][0]);
			case 2 :
				return new Heal((Human)mouse.getEntity(), whoToReadAttack[1][1]);
			default:
				return new Lock((Human)mouse.getEntity());
			}
			
			
		case ARCHER:
			switch(attaque) {
			case -1:
				return new Lock((Human)mouse.getEntity());
			case 0:
				return new Lock((Human)mouse.getEntity());
			case 1 :
				return new Fléche((Human)mouse.getEntity(), whoToReadAttack[1][0]);
			case 2 :
				return new Recul((Human)mouse.getEntity(), whoToReadAttack[1][1]);
			default:
				return new Lock((Human)mouse.getEntity());
			}
			
			
		case NINJA:
			switch(attaque) {
			case -1:
				return new Lock((Human)mouse.getEntity());
			case 0:
				return new Lock((Human)mouse.getEntity());
			case 1 :
				return new Knife_Hit((Human)mouse.getEntity(), whoToReadAttack[1][0]);
			case 2 :
				return new Dash((Human)mouse.getEntity(), whoToReadAttack[1][1]);
			default:
				return new Lock((Human)mouse.getEntity());
			}
			
			
		default:
			return new Lock((Human)mouse.getEntity());

		}
	}
	@Override
	public int getBottomY() {
		return (int)y + taille[1];
	}
	@Override
	public void start() {
		
	}
	
	

	public boolean giveItem(Item item) {
		if(item.itemName!=null && item.itemName!="No Item")renderGiver(str_getItem+item.itemName);
		for(int i=0; i<inventorySize; i++) {
			if (inventaire[i].getID()== 0) {
				inventaire[i]=item;
				item.setLocation(i);
				item.setOwner((Human)mouse.getEntity());
				return true;
			}
			
		}return false;
	}
	public void removeItem(int location) {
		inventaire[location]=null;
		for(int i =location;i<inventorySize-1;i++) {
			inventaire[i]=inventaire[i+1];
			inventaire[i].setLocation(i);
		}
		inventaire[inventorySize-1]=new NoItem();
	}
	public boolean unequipeArmor() {
		armor.unequipe();
		if(giveItem(armor)) {
			armor = new NoItem();
			actualiseHP();
			if(name==Main.save.name) {
				song = new Sound_lector("res/sound/sou/unequipeItem.wav", Main.save.soundVolume);
				song.start();
				}
			return true;
		}else {
			armor.equipe();
			return false;
		}
		
	}
	public boolean unequipeWeapon() {
		weapon.unequipe();
		if(giveItem(weapon)) {
			weapon = new NoItem();
			actualiseHP();
			if(name==Main.save.name) {
				song = new Sound_lector("res/sound/sou/unequipeItem.wav", Main.save.soundVolume);
				song.start();
				}
			return true;
		}else {
			weapon.equipe();
			return false;
		}
	}
	private boolean compatibility(Equipement stuff) {
		switch(classe) {
		case ARCHER:if(stuff.getType()==equipement.BOW)return true;break;
		case GUERRIER:if(stuff.getType()==equipement.SWORD)return true;break;
		case MAGE:if(stuff.getType()==equipement.WAND)return true;break;
		case NINJA:if(stuff.getType()==equipement.KNIFE)return true;break;
		default:break;			
		}return false;	
	}
	public void equipeItem(int location) {
		if(((Equipement) inventaire[location]).getType()==equipement.ARMOR) {
			int armorId=armor.getID();
			int newId=inventaire[location].getID();
			removeItem(location);
			armor = (Armor)Game.getItem(newId);
			armor.setOwner((Human)mouse.getEntity());
			armor.equipe();
			giveItem(Game.getItem(armorId));
			if(name==Main.save.name) {
				song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
				song.start();
				}

			}
		else if(((Equipement)inventaire[location]).getType()==equipement.ALL||compatibility((Equipement) inventaire[location])) {
			int weaponId=weapon.getID();
			int newId=inventaire[location].getID();
			removeItem(location);
			weapon = (Equipement)Game.getItem(newId);
			weapon.equipe();
			weapon.setOwner((Human)mouse.getEntity());
			giveItem(Game.getItem(weaponId));
			if(name==Main.save.name) {
				song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
				song.start();
				}
		}
	}
	public boolean equipeItem(Equipement item) {
		if(item.getID()==0)return false;
		if(item.getType()==equipement.ARMOR) {
			if (armor.getID()==0) {
				armor = (Armor)item;
				armor.equipe();
				armor.setOwner((Human)mouse.getEntity());
				if(name==Main.save.name) {
					song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
					song.start();
					}
				return true;
			}
			else {
				armor.unequipe();
				if(giveItem(armor)) {
				armor = (Armor)item;
				armor.equipe();
				armor.setOwner((Human)mouse.getEntity());
				if(name==Main.save.name) {
					song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
					song.start();
					}
				return true;
			}else armor.equipe();}
		}else {
			if(item.getType()==equipement.ALL||compatibility(item)) {
				if (weapon.getID()==0) {
					weapon = item;
					weapon.equipe();
					weapon.setOwner((Human)mouse.getEntity());
					if(name==Main.save.name) {
						song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
						song.start();
						}
					return true;
				}
				else {
					weapon.unequipe();
					if(giveItem(weapon)) {				
					weapon = item;
					weapon.equipe();
					weapon.setOwner((Human)mouse.getEntity());
					if(name==Main.save.name) {
					song = new Sound_lector("res/sound/sou/equipeItem.wav", Main.save.soundVolume);
					song.start();
					}

					return true;
				}else weapon.equipe();
				}
			}
		}
			
		return false;
	}
	
	public void addXP(int xp) {
		this.xp+=xp;
		renderGiver(str_getXP+xp+str_XP);
		while (this.xp>=50*2*(lvl+1)*(lvl+1)){
			lvl+=1;
			renderLevelUp();
			if(name==Main.save.name) {
			this.HP+=4;
			this.maxHP+=4;
			if(HP>maxHP)HP=maxHP;
			}
		}
	}
	public int getXP() {
		return (int)(xp-50*2*lvl*lvl);
	}
	public int getNextLvlXP() {
		return (int)(50*2*lvl*lvl);
	}
	public int xpgetAllXP() {
	return xp;
	}
	public int getLVL() {
		return lvl;
	}
	public int getGold() {
		return gold;
	}
	public void addgold(int bonus) {
		gold+=bonus;
		renderGiver(str_getGold+bonus+str_or);
	}

	public boolean removeGold(int remove) {
		if(remove<=gold) {
			gold-=remove;
			return true;
		}else return false;
	}
	protected abstract void renderGiver(String text);
	protected abstract void renderLevelUp();
	
	public void tunder(int distance) {
		for(Entity target : Game.level.mortalObjects.values()) {
			float D = (float)Math.sqrt(((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))*((x-(taille[0]/2))-(target.x-(target.taille[0]/2)))+((y+(taille[1]/2))-(target.y+(target.taille[1]/2)))*((y+(taille[1]/2))-(target.y+(target.taille[1]/2))));
			if(D < distance && target.team != team && target.mortalID != mortalID) {
				float[] selfCoord = getCoord();
				float[] targetCoord = target.getCoord();
				Game.level.addParticle(new Tunder((int)selfCoord[0],(int)selfCoord[1],(int)targetCoord[0],(int)targetCoord[1], (Human)mouse.getEntity()));
				loadTunder=0;
				song = new Sound_lector("res/sound/sou/attaques/other/tunder.wav", Main.save.soundVolume);
				song.start();

			}
		}
	}
}
