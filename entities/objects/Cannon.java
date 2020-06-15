package fr.pierrehb.entities.objects;

import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.particles.Bullet;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.Level;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Cannon extends Objects {
	static float[] dimension = {1,1};
	static int[] taille = {32,16};
	public float[] center = new float[2];
	private float x1, x2, x3, x4, y1, y2, y3, y4;
	private Point p1, p2, p3, p4;
	private float smallD = 11.3137085f;
	private float bigD = 25.29822128f;
	private float smallA = 0.3217505544f;
	private float bigA = (float)Math.PI*3/4;
	private int[] minColor1 = {0, 0, 0};
	private int[] maxColor1 = {50, 50, 50};
	private int[] minColor2 = {50, 50, 50};
	private int[] maxColor2 = {60, 60, 60};
	private static final int[] minColor = {200,200,200};
	private static final int[] maxColor = {255,255,255};
	private int load = 0;
	private int reload = 240;
	public boolean charging = false;
	private static Sound_lector sound;


	//explosion
	public Target target;
	boolean ready = true;
		
		public Cannon(int x, int y, int xTarget, int yTarget) {
			super(x, y, "Cannon", false, Texture.loadTexture("res/textures/entity/Cannon.png"), dimension, taille);
			center[0] = x+24;
			center[1] = y+8;
			target = new Target(xTarget, yTarget, center);
			ini();
			}
		public Cannon(int x, int y, int xTarget, int yTarget, float[] Color) {
			super(x, y, "Cannon", false, Texture.loadTexture("res/textures/entity/Cannon.png"), dimension, taille);
			center[0] = x+24;
			center[1] = y+8;
			target = new Target(xTarget, yTarget, center);
			
			this.color = Color;
			ini();
			}
		@Override
		public void start() {
			Game.level.EntityForSummon.add(target);
		}
		@Override
		protected void die() {
			
		}

		@Override
		public void trueUpdate() {
			x2 = center[0]+(float)(Math.cos(bigA+getAngle())*smallD);
			x1 = center[0]+(float)(Math.cos(smallA+getAngle())*bigD);
			x4 = center[0]+(float)(Math.cos(-smallA+getAngle())*bigD);
			x3 = center[0]+(float)(Math.cos(-bigA+getAngle())*smallD);
			y2 = center[1]+(float)(Math.sin(bigA+getAngle())*smallD);
			y1 = center[1]+(float)(Math.sin(smallA+getAngle())*bigD);
			y4 = center[1]+(float)(Math.sin(-smallA+getAngle())*bigD);
			y3 = center[1]+(float)(Math.sin(-bigA+getAngle())*smallD);
			p1 = new Point(x1, y1);
			p2 = new Point(x2, y2);
			p3 = new Point(x3, y3);
			p4 = new Point(x4, y4);
			if(!ready) {
				load++;
				Game.level.addParticle(new Smoke((int)center[0], (int)center[1], 35, Game.getPlayer(), minColor1, maxColor1, minColor2, maxColor2));	
				if(load > reload) {
					load = 0;
					ready = true;
				}
			}


			
		}
		private float getAngle(){
			return Souris.getAngle(target.x+8-center[0], target.y+8-center[1]);
		}
		
		public void ini() {
			TRUERENDER = false;
			HP = 200;
			xo = 0;
			yo = 0;
			Level.solidList[(int)((x+16)/16)][(int)((y)/16)] = true;
			p1 = new Point(0, 0);
			p2 = new Point(0, 0);
			p3 = new Point(0, 0);
			p4 = new Point(0, 0);

		}
		private int getLifeTime() {
			return (int)(Math.sqrt((center[0]-target.x-8)*(center[0]-target.x-8)+(center[1]-target.y-8)*(center[1]-target.y-8))/3);
		}
		@Override
		protected boolean interat() {
			if(!charging) {
				charging = true;
				target.targeted();
				Game.getPlayer().cantMove = true;
				Game.level.setTarget(target);
				Game.getPlayer().canAttack = false;
				return true;
			}else {
				
				charging = false;
				target.unTargeted();
				Game.getPlayer().cantMove = false;
				Game.getPlayer().canAttack = true;

				Game.level.unSetTarget();
				if(ready) {
					fire();
					return false;

			}else return true;}
		}
		@Override
		public boolean interact() {
			if(!remove) {
			if(Math.abs(Game.getPlayer().getCoord()[0]-(x+8+8)) <= 24 &&  Math.abs((Game.getPlayer().getCoord()[1]-(y))) <= 22) {
				interat();
				return true;
			}
			else if(charging) {
				interat();
				return true;
			}return false;
		}
			return false;}
		@Override
		public void dialogueRender() {
			
		}
		@Override
		public int getBottomY() {
			return (int)y + taille[1];
		}
		@Override
		protected void otherRender() {
			Renderer.renderPixel(p1, p2, p3, p4, texture, 0, 0, dimension, color);
			
		}
		public void fire() {
			if(ready && !remove) {
				sound = new Sound_lector("res/sound/sou/objects/explosion1.wav", Main.save.soundVolume);
				sound.start();
			Game.level.addParticle(new Bullet((int)center[0], (int)center[1], getLifeTime(), getAngle(), Game.getPlayer()));
			ready = false;
			}
		}
		public void destroy() {
			remove = true;
			Level.solidList[(int)((x+16)/16)][(int)((y)/16)] = false;
			for (int i = 0; i<50; i++)Game.level.summonParticle(new Smoke((int)x+24, (int)y+8, random(40, 100),random(20, 100)/200.0f, Game.getPlayer(), minColor, maxColor, minColor, maxColor));

		}
		

	}