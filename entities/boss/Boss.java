package fr.pierrehb.entities.boss;

import fr.pierrehb.attaques.attaque.Joueur_Mouse;
import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Human;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;

public abstract class Boss extends Entity{
	public int healthBar = 1;
	protected direction orientation;
	protected boolean moving = false;
	public boolean walking = false;
	protected sens vers;
	protected boolean wantMove = false;
	public Souris mouse;
	protected float speed = 1f;
	protected int[] movingHitBox;
	protected int move = 0;
	protected boolean MOUSE = false;

	public Boss(float x, float y, int[] taille, String name, boolean canmove, int healthBar_) {
		super(x, y, taille, name, canmove);
		team = -1;
		this.healthBar=healthBar_;
		this.mortal = true;
		this.mortalID = Main.getMortalID();
	}
	
	@Override
	public int getBottomY() {
		return (int)y + taille[1];
	}
	
	public void setMouse(Boss joueur) {

		mouse = new Joueur_Mouse(joueur);
		MOUSE = true;
		newMouse();

	}
	protected abstract void newMouse();
	public void getTarget(int distance) {
		
		float D;
		float littleD = distance +1;
		for(Human human : Game.level.humans) {
			D = (float)Math.sqrt((x-human.x)*(x-human.x)+(y-human.y)*(y-human.y));

			if(human.life && D < distance && D < littleD && human.team != team && human.team != -2) {
				littleD = D;
				hasTarget = true;
				target = human;
				mouse.setTarget(human);
			}
		}
	}
	public void setDirection(direction dir) {
		orientation = dir;
	}
	public void setMoving(boolean move) {
		moving = move;
	}
	public void moveX(float X) {
		if(canMove && !cantMove) {
		float xp = getX();
		float yp = getY();
		float xa = 0;
		float xHitBox1 = xp+movingHitBox[0];
		float xHitBox2 = (xp+movingHitBox[2]);
		float yHitBox1 = (yp+movingHitBox[1]);
		float yHitBox2 = (yp+movingHitBox[3]);
		if(X<0) {
			xa = X;
			xHitBox1 = (xp+movingHitBox[0]+xa);
			xHitBox2 = xHitBox1;
			setDirection(direction.LEFT);
			setMoving(true);

			walking = true;
		}else {
			xa = X;
			xHitBox1 = (xp+movingHitBox[2]+xa);
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
			boolean solid=false;
			for (int i = 0; (int)yHitBox1/16+i<=(int)yHitBox2/16; i++) {
				if(Game.level.getSolid((int)xHitBox1/16, (int)yHitBox1/16+i)) solid = true;
			}
		
			if(solid) {
			xa = 0;
			setMoving(false);
			}
			}
		setX(xp + xa);
	}}
	
	public void moveY(float Y) {
		if(canMove && !cantMove) {
		float xp = getX();
		float yp = getY();
		float ya = 0;
		float xHitBox1 = xp+movingHitBox[0];
		float xHitBox2 = (xp+movingHitBox[2]);
		float yHitBox1 = (yp+movingHitBox[1]);
		float yHitBox2 = (yp+movingHitBox[3]);
		
		if(Y<0) {
			ya = Y;
			yHitBox1 = (yp+movingHitBox[1]+ya);
			yHitBox2 = yHitBox1;
			
			setDirection(direction.UP);
			setMoving(true);
			walking = true;
		}else {
			ya = Y;
			yHitBox1 = (yp+movingHitBox[3]+ya);
			yHitBox2 = yHitBox1;
			setDirection(direction.DOWN);
			setMoving(true);
			walking = true;	
		}
		if(yHitBox1/16 >= Game.level.size[1] || yHitBox2/16 >= Game.level.size[1] ||
				yHitBox1 <= 0 || yHitBox2 <= 0) {
			ya = 0;
			setMoving(false);

		}
		else {
			boolean solid=false;
			for (int i = 0; (int)xHitBox1/16+i<=(int)xHitBox2/16; i++) {
				if(Game.level.getSolid((int)xHitBox1/16+i, (int)yHitBox1/16)) solid = true;
			}
		
			if(solid) {
			ya = 0;
			setMoving(false);
			}

			

		}
		setY(yp + ya);

		
	}}
	public void trueUpdate() {
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
		moving();
		bossUpdate();
	}
	
	protected abstract void bossUpdate();
	protected boolean isnear(int distance) {
		if(!hasTarget)return false;
		return Math.sqrt((target.x-x)*(target.x-x)+(target.y-y)*(target.y-y)) < distance;
	}
	protected boolean isfar(int distance) {
		if(!hasTarget)return false;
		return Math.sqrt((target.x-x)*(target.x-x)+(target.y-y)*(target.y-y)) > distance;
	}
	@Override
	protected int getDefense() {
		return 0;
	}
	protected void moving() {
		if(moving) move++;
		else move = 0;
	}


}
