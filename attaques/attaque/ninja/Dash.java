package fr.pierrehb.attaques.attaque.ninja;

import fr.pierrehb.attaques.attaque.Attack;
import fr.pierrehb.entities.Human;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.sound.Sound_lector;


public class Dash  extends Attack implements Color{



		float x1,x2,x3,x4,y1,y2,y3,y4;
		float xi1,xi2,xi3,xi4,yi1,yi2,yi3,yi4;
		float xb1,xb2,xb3,xb4,yb1,yb2,yb3,yb4;
		float xc1,xc2,xc3,xc4,yc1,yc2,yc3,yc4;
		float xd1,xd2,xd3,xd4,yd1,yd2,yd3,yd4;
		Point p1, p2, p3, p4;
		Quadrilataire hitBox;
		float ang;
		float preparingAngle = 0.1243549945f;
		float attackingAngle = 0.0302937599f;
		float bigAttackingAngle = 0.0587558227f;
		float bigDistance = 64;
		float smallDistance = 8;
		float bigDistanceAttack = 16;
		float smallDistanceAttack = 8.5f;
		private static Sound_lector song;




		public Dash(Human Joueur, int level) {
			super(Joueur, level);
			xo = 1.0f;
			yo = 3.0f;
			timeToDone = 15;
			reload = 60*2 + timeToDone;
			damageHeal = 2;
			
		}

		@Override
		public void render() {
			Renderer.renderSquare(x1+joueur.x+(Human.taille[0]/2), x2+joueur.x+(Human.taille[0]/2), x3+joueur.x+(Human.taille[0]/2), x4+joueur.x+(Human.taille[0]/2), -y1+joueur.y+(Human.taille[1]/2), -y2+joueur.y+(Human.taille[1]/2), -y3+joueur.y+(Human.taille[1]/2), -y4+joueur.y+(Human.taille[1]/2), SEMIWHITE);

		}

		

		@Override
		public void renderAttack() {
			Renderer.renderSquare(x1+joueur.x+(Human.taille[0]/2), x2+joueur.x+(Human.taille[0]/2), x3+joueur.x+(Human.taille[0]/2), x4+joueur.x+(Human.taille[0]/2), -y1+joueur.y+(Human.taille[1]/2), -y2+joueur.y+(Human.taille[1]/2), -y3+joueur.y+(Human.taille[1]/2), -y4+joueur.y+(Human.taille[1]/2), BLACK);
			Renderer.renderSquare(xi1+joueur.x+(Human.taille[0]/2), xi2+joueur.x+(Human.taille[0]/2), xi3+joueur.x+(Human.taille[0]/2), xi4+joueur.x+(Human.taille[0]/2), -yi1+joueur.y+(Human.taille[1]/2), -yi2+joueur.y+(Human.taille[1]/2), -yi3+joueur.y+(Human.taille[1]/2), -yi4+joueur.y+(Human.taille[1]/2), BLACK);
			Renderer.renderSquare(xb1+joueur.x+(Human.taille[0]/2), xb2+joueur.x+(Human.taille[0]/2), xb3+joueur.x+(Human.taille[0]/2), xb4+joueur.x+(Human.taille[0]/2), -yb1+joueur.y+(Human.taille[1]/2), -yb2+joueur.y+(Human.taille[1]/2), -yb3+joueur.y+(Human.taille[1]/2), -yb4+joueur.y+(Human.taille[1]/2), BLACK);
			Renderer.renderSquare(xc1+joueur.x+(Human.taille[0]/2), xc2+joueur.x+(Human.taille[0]/2), xc3+joueur.x+(Human.taille[0]/2), xc4+joueur.x+(Human.taille[0]/2), -yc1+joueur.y+(Human.taille[1]/2), -yc2+joueur.y+(Human.taille[1]/2), -yc3+joueur.y+(Human.taille[1]/2), -yc4+joueur.y+(Human.taille[1]/2), BLACK);
			Renderer.renderSquare(xd1+joueur.x+(Human.taille[0]/2), xd2+joueur.x+(Human.taille[0]/2), xd3+joueur.x+(Human.taille[0]/2), xd4+joueur.x+(Human.taille[0]/2), -yd1+joueur.y+(Human.taille[1]/2), -yd2+joueur.y+(Human.taille[1]/2), -yd3+joueur.y+(Human.taille[1]/2), -yd4+joueur.y+(Human.taille[1]/2), BLACK);

			Renderer.renderInterface4P(xi1+128, -yi1+72, xi2+128, -yi2+72, xi3+128, -yi3+72, xi4+128, -yi4+72, BLACK);
			Renderer.renderInterface4P(xb1+128, -yb1+72, xb2+128, -yb2+72, xb3+128, -yb3+72, xb4+128, -yb4+72, BLACK);
			Renderer.renderInterface4P(xc1+128, -yc1+72, xc2+128, -yc2+72, xc3+128, -yc3+72, xc4+128, -yc4+72, BLACK);
			Renderer.renderInterface4P(xd1+128, -yd1+72, xd2+128, -yd2+72, xd3+128, -yd3+72, xd4+128, -yd4+72, BLACK);


		}
		@Override
		public void startAttack() {
			p1 = new Point(x1+joueur.x+8, -y1+joueur.y+16);
			p2 = new Point(x2+joueur.x+8, -y2+joueur.y+16);
			p3 = new Point(x3+joueur.x+8, -y3+joueur.y+16);
			p4 = new Point(x4+joueur.x+8, -y4+joueur.y+16);
			joueur.shieldLife += 10;
			song = new Sound_lector("res/sound/sou/attaques/ninja/atk2.wav", Main.save.soundVolume);
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
			for(int a = 0; a <(int)(bigDistance/timeToDone); a++) {
				joueur.moveX((float)Math.cos(ang));
				joueur.moveY((float)-Math.sin(ang));

			}
			x1=(float)-Math.cos(bigAttackingAngle+ang)*smallDistanceAttack;
			x2=(float)-Math.cos(attackingAngle+ang)*bigDistanceAttack;
			x3=(float)-Math.cos(-attackingAngle+ang)*bigDistanceAttack;
			x4=(float)-Math.cos(-bigAttackingAngle+ang)*smallDistanceAttack;
			y1=(float)-Math.sin(bigAttackingAngle+ang)*smallDistanceAttack;
			y2=(float)-Math.sin(attackingAngle+ang)*bigDistanceAttack;
			y3=(float)-Math.sin(-attackingAngle+ang)*bigDistanceAttack;
			y4=(float)-Math.sin(-bigAttackingAngle+ang)*smallDistanceAttack;
			
			xi1=(float)-Math.cos(bigAttackingAngle+ang+0.3f)*smallDistanceAttack;
			xi2=(float)-Math.cos(attackingAngle+ang+0.3f)*bigDistanceAttack;
			xi3=(float)-Math.cos(-attackingAngle+ang+0.3f)*bigDistanceAttack;
			xi4=(float)-Math.cos(-bigAttackingAngle+ang+0.3f)*smallDistanceAttack;
			yi1=(float)-Math.sin(bigAttackingAngle+ang+0.3f)*smallDistanceAttack;
			yi2=(float)-Math.sin(attackingAngle+ang+0.3f)*bigDistanceAttack;
			yi3=(float)-Math.sin(-attackingAngle+ang+0.3f)*bigDistanceAttack;
			yi4=(float)-Math.sin(-bigAttackingAngle+ang+0.3f)*smallDistanceAttack;
			
			xb1=(float)-Math.cos(bigAttackingAngle+ang+0.6f)*smallDistanceAttack;
			xb2=(float)-Math.cos(attackingAngle+ang+0.6f)*bigDistanceAttack;
			xb3=(float)-Math.cos(-attackingAngle+ang+0.6f)*bigDistanceAttack;
			xb4=(float)-Math.cos(-bigAttackingAngle+ang+0.6f)*smallDistanceAttack;
			yb1=(float)-Math.sin(bigAttackingAngle+ang+0.6f)*smallDistanceAttack;
			yb2=(float)-Math.sin(attackingAngle+ang+0.6f)*bigDistanceAttack;
			yb3=(float)-Math.sin(-attackingAngle+ang+0.6f)*bigDistanceAttack;
			yb4=(float)-Math.sin(-bigAttackingAngle+ang+0.6f)*smallDistanceAttack;
			
			xc1=(float)-Math.cos(bigAttackingAngle+ang-0.3f)*smallDistanceAttack;
			xc2=(float)-Math.cos(attackingAngle+ang-0.3f)*bigDistanceAttack;
			xc3=(float)-Math.cos(-attackingAngle+ang-0.3f)*bigDistanceAttack;
			xc4=(float)-Math.cos(-bigAttackingAngle+ang-0.3f)*smallDistanceAttack;
			yc1=(float)-Math.sin(bigAttackingAngle+ang-0.3f)*smallDistanceAttack;
			yc2=(float)-Math.sin(attackingAngle+ang-0.3f)*bigDistanceAttack;
			yc3=(float)-Math.sin(-attackingAngle+ang-0.3f)*bigDistanceAttack;
			yc4=(float)-Math.sin(-bigAttackingAngle+ang-0.3f)*smallDistanceAttack;
			
			xd1=(float)-Math.cos(bigAttackingAngle+ang-0.6f)*smallDistanceAttack;
			xd2=(float)-Math.cos(attackingAngle+ang-0.6f)*bigDistanceAttack;
			xd3=(float)-Math.cos(-attackingAngle+ang-0.6f)*bigDistanceAttack;
			xd4=(float)-Math.cos(-bigAttackingAngle+ang-0.6f)*smallDistanceAttack;
			yd1=(float)-Math.sin(bigAttackingAngle+ang-0.6f)*smallDistanceAttack;
			yd2=(float)-Math.sin(attackingAngle+ang-0.6f)*bigDistanceAttack;
			yd3=(float)-Math.sin(-attackingAngle+ang-0.6f)*bigDistanceAttack;
			yd4=(float)-Math.sin(-bigAttackingAngle+ang-0.6f)*smallDistanceAttack;
			
			
		}

		@Override
		public void stopAttack() {
			
			hitBox = new Quadrilataire(p1, p2, p3, p4);

			Game.level.giveDamageSquare(hitBox, joueur, damageHeal);
			joueur.shieldLife -= 10;
			


		}

	}


