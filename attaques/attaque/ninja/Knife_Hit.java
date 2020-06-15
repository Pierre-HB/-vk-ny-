package fr.pierrehb.attaques.attaque.ninja;

import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.Human;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;


public class Knife_Hit  extends Attack implements Color{



		float x1,x2,x3,x4,y1,y2,y3,y4;
		Point p1, p2, p3, p4;
		Quadrilataire hitBox;
		float ang;
		float preparingAngle = 0.1973955598f;
		float attackingAngle = 0.0665681638f;
		float bigDistance = 15;
		float smallDistance = 3;
		float distance = 0;
		private static Sound_lector song;



		public Knife_Hit(Human Joueur, int level) {
			super(Joueur, level);
			xo = 0.0f;
			yo = 3.0f;
			timeToDone = 10;
			reload = 30 + timeToDone;
			damageHeal = 4;
			
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
			joueur.cantMove = true;
			ang = joueur.getAngle();
			x1=(float)Math.cos(Math.PI/2+ang)*smallDistance;
			x2=(float)Math.cos(preparingAngle+ang)*bigDistance;
			x3=(float)Math.cos(-preparingAngle+ang)*bigDistance;
			x4=(float)Math.cos(-Math.PI/2+ang)*smallDistance;
			y1=(float)Math.sin(Math.PI/2+ang)*smallDistance;
			y2=(float)Math.sin(preparingAngle+ang)*bigDistance;
			y3=(float)Math.sin(-preparingAngle+ang)*bigDistance;
			y4=(float)Math.sin(-Math.PI/2+ang)*smallDistance;
			p1 = new Point(x1+joueur.x+8, -y1+joueur.y+16);
			p2 = new Point(x2+joueur.x+8, -y2+joueur.y+16);
			p3 = new Point(x3+joueur.x+8, -y3+joueur.y+16);
			p4 = new Point(x4+joueur.x+8, -y4+joueur.y+16);
			hitBox = new Quadrilataire(p1, p2, p3, p4);

			Game.level.giveDamageSquare(hitBox, joueur, damageHeal+joueur.getDamage());
			
			
			attackingAngle = Souris.getAngle(distance, 1);
			x1=(float)Math.cos(Math.PI/2+ang)*1;
			x2=(float)Math.cos(attackingAngle+ang)*distance;
			x3=(float)Math.cos(-attackingAngle+ang)*distance;
			x4=(float)Math.cos(-Math.PI/2+ang)*1;
			y1=(float)Math.sin(Math.PI/2+ang)*1;
			y2=(float)Math.sin(attackingAngle+ang)*distance;
			y3=(float)Math.sin(-attackingAngle+ang)*distance;
			y4=(float)Math.sin(-Math.PI/2+ang)*1;
			
			song = new Sound_lector("res/sound/sou/attaques/ninja/atk1.wav", Main.save.soundVolume);
			song.start();
		}


		@Override
		public void updatePreparing() {
			ang = joueur.getAngle();
			x1=(float)Math.cos(Math.PI/2+ang)*smallDistance;
			x2=(float)Math.cos(preparingAngle+ang)*bigDistance;
			x3=(float)Math.cos(-preparingAngle+ang)*bigDistance;
			x4=(float)Math.cos(-Math.PI/2+ang)*smallDistance;
			y1=(float)Math.sin(Math.PI/2+ang)*smallDistance;
			y2=(float)Math.sin(preparingAngle+ang)*bigDistance;
			y3=(float)Math.sin(-preparingAngle+ang)*bigDistance;
			y4=(float)Math.sin(-Math.PI/2+ang)*smallDistance;
		}

		@Override
		public void trueUpdate() {
			distance += bigDistance/timeToDone;
			
			
			attackingAngle = Souris.getAngle(distance, 1);
			x1=(float)Math.cos(Math.PI/2+ang)*1;
			x2=(float)Math.cos(attackingAngle+ang)*distance;
			x3=(float)Math.cos(-attackingAngle+ang)*distance;
			x4=(float)Math.cos(-Math.PI/2+ang)*1;
			y1=(float)Math.sin(Math.PI/2+ang)*1;
			y2=(float)Math.sin(attackingAngle+ang)*distance;
			y3=(float)Math.sin(-attackingAngle+ang)*distance;
			y4=(float)Math.sin(-Math.PI/2+ang)*1;
		}

		@Override
		public void stopAttack() {
			distance = 0;
			joueur.cantMove = false;
			

			
						
		}

	}



