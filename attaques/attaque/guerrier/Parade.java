package fr.pierrehb.attaques.attaque.guerrier;


import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;

public class Parade extends Attack implements Color{
	float x1,x2,x3,x4,y1,y2,y3,y4;
	int t = 0;
	float ang = joueur.getAngle();
	float smallAngle = 0.5585993153f;
	float bigAngle = 0.620249486f;
	private static Sound_lector song;



	public Parade(Human Joueur, int level) {
		super(Joueur, level);
		xo = 1.0f;
		yo = 0.0f;
		timeToDone = 180;
		reload = 3*60 + timeToDone;
		
	}

	@Override
	public void render() {
		Renderer.renderSquare(x1+joueur.x+(Human.taille[0]/2), x2+joueur.x+(Human.taille[0]/2), x3+joueur.x+(Human.taille[0]/2), x4+joueur.x+(Human.taille[0]/2), -y1+joueur.y+(Human.taille[1]/2), -y2+joueur.y+(Human.taille[1]/2), -y3+joueur.y+(Human.taille[1]/2), -y4+joueur.y+(Human.taille[1]/2), SEMIWHITE);

	}

	

	@Override
	public void renderAttack() {
		Renderer.renderSquare(x1+joueur.x+(Human.taille[0]/2), x2+joueur.x+(Human.taille[0]/2), x3+joueur.x+(Human.taille[0]/2), x4+joueur.x+(Human.taille[0]/2), -y1+joueur.y+(Human.taille[1]/2), -y2+joueur.y+(Human.taille[1]/2), -y3+joueur.y+(Human.taille[1]/2), -y4+joueur.y+(Human.taille[1]/2), GREY);


	}
	@Override
	public void startAttack() {
		joueur.shieldLife += 2;
		song = new Sound_lector("res/sound/sou/attaques/warior/atk2.wav", Main.save.soundVolume);
		song.start();
	}

	@Override
	public void updatePreparing() {
		ang = joueur.getAngle();
		x1=(float)Math.cos(bigAngle+ang)*14;
		x2=(float)Math.cos(smallAngle+ang)*16;
		x3=(float)Math.cos(-smallAngle+ang)*16;
		x4=(float)Math.cos(-bigAngle+ang)*14;
		y1=(float)Math.sin(bigAngle+ang)*14;
		y2=(float)Math.sin(smallAngle+ang)*16;
		y3=(float)Math.sin(-smallAngle+ang)*16;
		y4=(float)Math.sin(-bigAngle+ang)*14;		
	}

	@Override
	public void trueUpdate() {
		ang = joueur.getAngle();
		x1=(float)Math.cos(bigAngle+ang)*14;
		x2=(float)Math.cos(smallAngle+ang)*16;
		x3=(float)Math.cos(-smallAngle+ang)*16;
		x4=(float)Math.cos(-bigAngle+ang)*14;
		y1=(float)Math.sin(bigAngle+ang)*14;
		y2=(float)Math.sin(smallAngle+ang)*16;
		y3=(float)Math.sin(-smallAngle+ang)*16;
		y4=(float)Math.sin(-bigAngle+ang)*14;

	}

	@Override
	public void stopAttack() {
		joueur.shieldLife -= 2;
		

	}

}
