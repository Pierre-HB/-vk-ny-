package fr.pierrehb.attaques.attaque.mage;


import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.particles.Fire_Ball_Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;

public class Fire_Ball extends Attack implements Color{
	float x1,x2,x3,x4,y1,y2,y3,y4;
	private static Sound_lector song;


	public Fire_Ball(Human Joueur, int level) {
		super(Joueur, level);
		xo = 0.0f;
		yo = 1.0f;
		timeToDone = 15;
		reload = 40 + timeToDone;
		damageHeal = 3;
		
	}

	@Override
	public void render() {
		Renderer.renderSquare(x1+joueur.x+(Human.taille[0]/2), x2+joueur.x+(Human.taille[0]/2), x3+joueur.x+(Human.taille[0]/2), x4+joueur.x+(Human.taille[0]/2), -y1+joueur.y+(Human.taille[1]/2), -y2+joueur.y+(Human.taille[1]/2), -y3+joueur.y+(Human.taille[1]/2), -y4+joueur.y+(Human.taille[1]/2), SEMIWHITE);

	}

	

	@Override
	public void renderAttack() {
		
	}
	@Override
	public void startAttack() {
		joueur.cantMove = true;
		float[] speed = {(float)(2*Math.cos(joueur.getAngle())), (float)(-2*Math.sin(joueur.getAngle()))}; 
		Game.level.addParticle(new Fire_Ball_Particle((int)joueur.x+(Human.taille[0]/2)-4, (int)joueur.y+(Human.taille[1]/2)-4, speed, joueur, damageHeal+joueur.getDamage()));
		song = new Sound_lector("res/sound/sou/attaques/mage/atk1.wav", Main.save.soundVolume);
		song.start();

	}

	@Override
	public void updatePreparing() {
		float ang = joueur.getAngle();
		x1=(float)Math.cos(Math.PI/2+ang)*1;
		x2=(float)Math.cos(0.027706366+ang)*36.01388621f;
		x3=(float)Math.cos(-0.027706366+ang)*36.01388621f;
		x4=(float)Math.cos(-Math.PI/2+ang)*1;
		y1=(float)Math.sin(Math.PI/2+ang)*1;
		y2=(float)Math.sin(0.027706366+ang)*36.01388621f;
		y3=(float)Math.sin(-0.027706366+ang)*36.01388621f;
		y4=(float)Math.sin(-Math.PI/2+ang)*1;		
	}

	@Override
	public void trueUpdate() {
		
	}

	@Override
	public void stopAttack() {
		joueur.cantMove = false;
		
		
	}

}
