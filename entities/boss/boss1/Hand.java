package fr.pierrehb.entities.boss.boss1;

import fr.pierrehb.entities.boss.Boss;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Color;

public class Hand implements Color{
	private Texture texture = Texture.loadTexture("res/textures/entity/boss1/Boss1Hand.png");
	private float[] dimension = {1,1};
	private Boss owner;
	private float[] origine;
	private int d = 14;
	private float size = 3;
	private float[] center = new float[2];
	public float angle = 0;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private BossSword sword;
	private boolean haveSword = false;
	
	public Hand(Boss owner_) {
		this.owner=owner_;
		
	}
	public void update() {
		
		origine = owner.getCoord();
		center[0] = origine[0]+(float)Math.cos(angle)*d;
		center[1] = origine[1]-(float)Math.sin(angle)*d;
		if(haveSword) {
			sword.setOrigine(center);
			sword.setAngle(angle);
		}
		
		p1 = new Point(center[0]+size*(float)Math.cos(angle+Math.PI/4),center[1]+size*(float)Math.sin(angle+Math.PI/4));
		p2 = new Point(center[0]+size*(float)Math.cos(angle+3*Math.PI/4),center[1]+size*(float)Math.sin(angle+3*Math.PI/4));
		p3 = new Point(center[0]+size*(float)Math.cos(angle-Math.PI/4),center[1]+size*(float)Math.sin(angle-Math.PI/4));
		p4 = new Point(center[0]+size*(float)Math.cos(angle-3*Math.PI/4),center[1]+size*(float)Math.sin(angle-3*Math.PI/4));
		
		if(haveSword)sword.update();
	}
	public void render() {
		if(haveSword) {
			Renderer.renderLine(sword.getCenter()[0], sword.getCenter()[1], center[0], center[1], GREY);
			sword.render();
		}
		Renderer.renderPixel(p1, p2, p4, p3, texture, 0, 0, dimension, WHITE);
		
	}
	public void giveSword() {
		haveSword=true;
		sword = new BossSword();
		
	}
	public void attaque(int damage) {
		if(haveSword)sword.attaque(damage, owner);
	}
	public void putSword(float d) {
		if(haveSword)sword.setDistance(d);
	}
	public void jetterSword(float d) {
		if(haveSword) {
			sword.distance+=d;
		}
	}

}
