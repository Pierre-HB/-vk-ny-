package fr.pierrehb.entities.boss.boss1;

import fr.pierrehb.entities.boss.Boss;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.BossDie;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.sound.Sound_lector;


public class Boss1 extends Boss implements BossDie, Dialogue{
	private static Texture body = Texture.loadTexture("res/textures/entity/boss1/Boss1.png");
	private static float[] dimension = {4,8};
	private static int[] taille = {32,32};
	private int[] movingHitBox_ = {1,17,31,31};
	private Hand leftHand;
	private Hand rightHand;
	private boolean angry = false;
	private int atk1 = 0;
	private int atk2 = 0;
	private int atk3 = 0;
	private int atk4 = 0;
	private boolean[] possibleAttaque = {false, false, false, false};
	private static final int atk1Doing = 30;
	private static final int atk2Doing = 30;
	private static final int atk3Doing = 90;
	private static final int atk4Doing = 120;
	private static final int[] minColor = {200,200,200};
	private static final int[] maxColor = {255,255,255};
	private int atk1Waiting = 300;
	private int atk2Waiting = 300;
	private int atk3Waiting = 500;
	private int atk4Waiting = 500;
	private int startAtk = -1;
	private enum attaques {ATTTAQUE1,ATTTAQUE2,ATTTAQUE3,ATTTAQUE4,NONE};
	private attaques currentAttaque = attaques.NONE;
	private boolean asleep = true;
	private static int dieXp=50;
	private static int dieGold=30;
	private static Sound_lector song;

	public Boss1(float x, float y) {
		super(x, y, taille, str_Boss1Name, true,2);
		ini();
	}
	public Boss1(float x, float y, boolean canmove) {
		super(x, y, taille, str_Boss1Name, canmove,2);
		ini();
	}
	
	private void ini() {
		orientation=direction.DOWN;
		this.HP=100;
		this.maxHP=100;
		speed = 0.6f;
		movingHitBox= movingHitBox_;
		this.VULNERABLE=false;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+31;
		hitBox[3] = (int)y+31;
	}
	public void awake() {
		startAtk++;
	}
	private void startAttaqueUpdate() {
		leftHand.angle+=0.1f;
		rightHand.angle+=0.1f;
		if(startAtk<=60) {
			leftHand.jetterSword(1);
			rightHand.jetterSword(1);
		}
		if(startAtk>=270) {
			leftHand.jetterSword(-2);
			rightHand.jetterSword(-2);
		}
		if(startAtk==300) {
			leftHand.putSword(0);
			rightHand.putSword(0);
		}
			
		
		
	}
	@Override
	public void start() {
		
	}

	@Override
	protected void die() {
		life = false;
		remove = true;
		BossDie.bossDie(x, y);
		Game.level.giveXpGold(dieXp, dieGold);
		Game.level.speakToLevel(4);
		song = new Sound_lector("res/sound/sou/attaques/other/boss1/bossDie.wav", Main.save.soundVolume);
		song.start();
		
	}
	private void attaque1Preparation() {
		leftHand.angle=mouse.getAngle()-2*(float)Math.PI/3;
		if(atk1==-1)leftHand.angle=mouse.getAngle()-(float)Math.PI/3;
		
	}
	private void attaque2Preparation() {
		rightHand.angle=mouse.getAngle()+2*(float)Math.PI/3;
		if(atk2==-1)leftHand.angle=mouse.getAngle()+(float)Math.PI/3;	
	}
	private void attaque3Preparation() {
		leftHand.angle=mouse.getAngle()-(float)Math.PI/2;
		rightHand.angle=mouse.getAngle()+(float)Math.PI/2;
		if(atk3==-1) {
			leftHand.angle=mouse.getAngle()-(float)Math.PI;
			rightHand.angle=mouse.getAngle();
		}
	}
	private void attaque4Preparation() {
		leftHand.angle=mouse.getAngle()-(float)Math.PI;
		rightHand.angle=mouse.getAngle()-(float)Math.PI;
		if(atk4==-1) {
			leftHand.angle=mouse.getAngle()-(float)Math.random()/5;
			rightHand.angle=mouse.getAngle()-(float)Math.random()/5;
		}
		
	}
	private void attaque1Start() {
		
		currentAttaque=attaques.ATTTAQUE1;
		atk1=-30;
		cantMove=true;
		song = new Sound_lector("res/sound/sou/attaques/other/boss1/atk1.wav", Main.save.soundVolume);
		song.start();
		
		
		
	}
	private void attaque2Start() {
		currentAttaque=attaques.ATTTAQUE2;
		atk2=-30;
		possibleAttaque[1]=false;
		cantMove=true;
		song = new Sound_lector("res/sound/sou/attaques/other/boss1/atk2.wav", Main.save.soundVolume);
		song.start();
			
	}
	private void attaque3Start() {
		currentAttaque=attaques.ATTTAQUE3;
		atk3=-30;
		possibleAttaque[2]=false;
		song = new Sound_lector("res/sound/sou/attaques/other/boss1/atk3.wav", Main.save.soundVolume);
		song.start();
		
	
	}
	private void attaque4Start() {
		currentAttaque=attaques.ATTTAQUE4;
		atk4=-30;
		possibleAttaque[3]=false;
		cantMove=true;
		song = new Sound_lector("res/sound/sou/attaques/other/boss1/atk4.wav", Main.save.soundVolume);
		song.start();
		
	
	}
	private void attaque1Update() {
		if(atk1>=atk1Doing) {
			currentAttaque=attaques.NONE;
			cantMove=false;
		}
		if(atk1<0) {
			attaque1Preparation();
		}else {
		leftHand.angle+=(2*(float)Math.PI/3)/atk1Doing;
		if(atk1%6==0)leftHand.attaque(2);
		}
		
	}
	private void attaque2Update() {
		if(atk2>=atk2Doing) {
			currentAttaque=attaques.NONE;
			cantMove=false;
		}
		if(atk2<0) {
			attaque2Preparation();
		}else {
		rightHand.angle-=(2*(float)Math.PI/3)/atk1Doing;
		if(atk2%6==0)rightHand.attaque(2);
		}
	}
	private void attaque3Update() {
		if(atk3>=atk3Doing)currentAttaque=attaques.NONE;
		if(atk3<0) {
			attaque3Preparation();
		}else {
		
		leftHand.angle+=(4*(float)Math.PI)/atk3Doing;
		if(atk3%6==1)leftHand.attaque(2);
		rightHand.angle+=(4*(float)Math.PI)/atk3Doing;
		if(atk3%6==1)leftHand.attaque(2);
		}
	
	}
	private void attaque4Update() {
		if(atk4>=atk4Doing) {
			currentAttaque=attaques.NONE;
			leftHand.putSword(0);
			rightHand.putSword(0);
			cantMove=false;
		}
		else {
			if(atk4<0) {
				attaque4Preparation();
			}else if(atk4*5/4<atk4Doing) {
				leftHand.jetterSword(2);
				rightHand.jetterSword(2);
				if(atk4%6==1)leftHand.attaque(2);
				if(atk4%6==1)rightHand.attaque(2);
			}else {
				leftHand.jetterSword(-8);
				rightHand.jetterSword(-8);
			}
			
		}
	
	}
	@Override
	protected void newMouse() {
		leftHand = new Hand((Boss)mouse.getEntity());
		rightHand = new Hand((Boss)mouse.getEntity());
		leftHand.giveSword();
		rightHand.angle=(float)Math.PI;
	}
	private void startAttaque() {
		if(possibleAttaque[3] && isfar(48)) {
			attaque4Start();
			return;
			}
		if(isnear(32)) {
			if(possibleAttaque[0] && random(0,3)==0) {
				attaque1Start();
				return;
				}
			if(possibleAttaque[1] && random(0,3)==0) {
				attaque2Start();
				return;
				}
			if(possibleAttaque[2] && random(0,3)==0) {
				attaque3Start();
				return;
				}
		}		
	}

	@Override
	public void bossUpdate() {
		leftHand.update();
		rightHand.update();
		
		if(asleep) {
			
			if(startAtk !=-1) {
				startAtk++;
				startAttaqueUpdate();
				if(startAtk>300) {
					asleep = false;
					mortal = true;
					this.VULNERABLE=true;
				}
			}
			
		}else {
		if(!angry && HP<maxHP/2) {
			angry = true;
			rightHand.giveSword();
			for (int i = 0; i<100; i++)Game.level.summonParticle(new Smoke((int)x, (int)y, random(40, 100),random(20, 100)/200.0f, mouse.getEntity(), minColor, maxColor, minColor, maxColor));

		}
		if(atk1<atk1Waiting)atk1++;
		else possibleAttaque[0]=true;
		if(atk2<atk2Waiting)atk2++;
		else possibleAttaque[1]=true;
		if(atk3<atk3Waiting)atk3++;
		else possibleAttaque[2]=true;
		if(atk4<atk4Waiting)atk4++;
		else possibleAttaque[3]=true;
		if(!angry)possibleAttaque[1]=false;
		switch(currentAttaque) {
		case ATTTAQUE1:
			attaque1Update();
			break;
		case ATTTAQUE2:
			attaque2Update();
			break;
		case ATTTAQUE3:
			attaque3Update();
			break;
		case ATTTAQUE4:
			attaque4Update();
			break;
		case NONE:
			if(hasTarget)startAttaque();
			break;
		
		}
		
		
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+31;
		hitBox[3] = (int)y+31;
		if(!hasTarget) {
			switch(random(0,3)) {
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
			}
		}
	}

	@Override
	protected void attackRender() {
	}
	@Override
	protected void attackUpdate() {
	}
	@Override
	public void trueRender() {

		body.bind();
		Renderer.renderObject((int)x, (int)y,Annimation.getHumanColumn(move, moving), Annimation.getHumanLine(orientation, moving), dimension, taille, getHealthColor());
		body.unbind();
		leftHand.render();
		rightHand.render();
		
	}

	@Override
	protected int getShield() {
		return 0;
	}

	@Override
	public int getHealthBonus() {
		return 0;
	}

	@Override
	public boolean interact() {
		return false;
	}

	@Override
	protected boolean interat() {
		return false;
	}

	@Override
	public void dialogueRender() {
	}
	

}
