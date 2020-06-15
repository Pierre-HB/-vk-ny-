package fr.pierrehb.attaques.attaque.mage;


import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.particles.Heal_Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;

public class Heal extends Attack implements Color{
	float x1,x2,x3,x4,y1,y2,y3,y4;
	int t = 0;
	private static Sound_lector song;


	public Heal(Human Joueur, int level) {
		super(Joueur, level);
		xo = 1.0f;
		yo = 1.0f;
		timeToDone = 10;
		reload = 6*50 + timeToDone;
		damageHeal = 1;
		
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
		joueur.getHeal(5);
		song = new Sound_lector("res/sound/sou/attaques/mage/atk2.wav", Main.save.soundVolume);
		song.start();
	}

	@Override
	public void updatePreparing() {
		float ang = joueur.getAngle();
		x1=(float)Math.cos(Math.PI/2+ang)*1;
		x2=(float)Math.cos(0.227706366+ang)*20.01388621f;
		x3=(float)Math.cos(-0.227706366+ang)*20.01388621f;
		x4=(float)Math.cos(-Math.PI/2+ang)*1;
		y1=(float)Math.sin(Math.PI/2+ang)*1;
		y2=(float)Math.sin(0.227706366+ang)*20.01388621f;
		y3=(float)Math.sin(-0.227706366+ang)*20.01388621f;
		y4=(float)Math.sin(-Math.PI/2+ang)*1;		
	}

	@Override
	public void trueUpdate() {
		 
		Game.level.addParticle(new Heal_Particle((int)joueur.x+(Human.taille[0]/2)-1, (int)joueur.y+(Human.taille[1]/2)-1, joueur.getAngle(), joueur, damageHeal));

	}

	@Override
	public void stopAttack() {
		joueur.cantMove = false;
		
		
	}

}
