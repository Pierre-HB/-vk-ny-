package fr.pierrehb.attaques.attaque.archer;

import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;

public class Recul extends Attack implements Color{



	float x1,x2,x3,x4,y1,y2,y3,y4;
	float xi1,xi2,xi3,xi4,yi1,yi2,yi3,yi4;
	float xb1,xb2,xb3,xb4,yb1,yb2,yb3,yb4;
	float xc1,xc2,xc3,xc4,yc1,yc2,yc3,yc4;
	float xd1,xd2,xd3,xd4,yd1,yd2,yd3,yd4;
	Point p1, p2, p3, p4;
	Quadrilataire hitBox;
	float ang;
	float preparingAngle = 0.0831412319f;
	float bigDistance = 48;
	float smallDistance = 4;
	private static Sound_lector song;




	public Recul(Human Joueur, int level) {
		super(Joueur, level);
		xo = 1.0f;
		yo = 2.0f;
		timeToDone = 15;
		reload = 60*6 + timeToDone;
		damageHeal = 0;
		
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
		for(int a = 0; a < 50; a++)	Game.level.addParticle(new Smoke((int)joueur.x+(Human.taille[0]/2)-4, (int)joueur.y+(Human.taille[1]/2)-4));
		song = new Sound_lector("res/sound/sou/attaques/rafter/atk2.wav", Main.save.soundVolume);
		song.start();	
	}


	@Override
	public void updatePreparing() {
		ang = joueur.getAngle();
		x1=(float)-Math.cos(Math.PI/2+ang)*smallDistance;
		x2=(float)-Math.cos(preparingAngle+ang)*bigDistance;
		x3=(float)-Math.cos(-preparingAngle+ang)*bigDistance;
		x4=(float)-Math.cos(-Math.PI/2+ang)*smallDistance;
		y1=(float)-Math.sin(Math.PI/2+ang)*smallDistance;
		y2=(float)-Math.sin(preparingAngle+ang)*bigDistance;
		y3=(float)-Math.sin(-preparingAngle+ang)*bigDistance;
		y4=(float)-Math.sin(-Math.PI/2+ang)*smallDistance;
	}

	@Override
	public void trueUpdate() {
		for(int a = 0; a <(int)(bigDistance/timeToDone); a++) {
			joueur.moveX((float)-Math.cos(ang));
			joueur.moveY((float)Math.sin(ang));
		}		
	}

	@Override
	public void stopAttack() {

	}

}
