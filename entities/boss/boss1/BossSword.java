package fr.pierrehb.entities.boss.boss1;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.other.Color;

public class BossSword implements Color{
	private Texture texture = Texture.loadTexture("res/textures/entity/boss1/Boss1Sword.png");
	private float[] dimension = {1,1};
	public float angle = 0;
	public float [] origine = {0,0};
	public float distance = 0;
	public float[] center = new float[2];
	private static final float bigAngle = 0.2691674928f;
	private static final float smallAngle = (float)Math.PI-1.212025657f;
	private static final float smallSize = 8.544003745f;
	private static final float bigSize = 30.08321791f;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private Quadrilataire hitBox;
	
	public BossSword() {
		center[0] = origine[0]+distance*(float)Math.cos(angle);
		center[1] = origine[1]-distance*(float)Math.sin(angle);
		p1 = new Point(center[0]+smallSize*(float)Math.cos(angle+smallAngle),center[1]-smallSize*(float)Math.sin(angle+smallAngle));
		p2 = new Point(center[0]+bigSize*(float)Math.cos(angle+bigAngle),center[1]-bigSize*(float)Math.sin(angle+bigAngle));
		p3 = new Point(center[0]+smallSize*(float)Math.cos(angle-smallAngle),center[1]-smallSize*(float)Math.sin(angle-smallAngle));
		p4 = new Point(center[0]+bigSize*(float)Math.cos(angle-bigAngle),center[1]-bigSize*(float)Math.sin(angle-bigAngle));

		
	}
	public void update() {
		center[0] = origine[0]+distance*(float)Math.cos(angle);
		center[1] = origine[1]-distance*(float)Math.sin(angle);
		p1 = new Point(center[0]+smallSize*(float)Math.cos(angle+smallAngle),center[1]-smallSize*(float)Math.sin(angle+smallAngle));
		p2 = new Point(center[0]+bigSize*(float)Math.cos(angle+bigAngle),center[1]-bigSize*(float)Math.sin(angle+bigAngle));
		p3 = new Point(center[0]+smallSize*(float)Math.cos(angle-smallAngle),center[1]-smallSize*(float)Math.sin(angle-smallAngle));
		p4 = new Point(center[0]+bigSize*(float)Math.cos(angle-bigAngle),center[1]-bigSize*(float)Math.sin(angle-bigAngle));

	}
	public void setOrigine(float[] origine_) {
		this.origine=origine_;
	}
	public void setAngle(float angle_) {
		this.angle=angle_;
	}
	public void setDistance(float d) {
		this.distance=d;
	}
	public float[] getCenter() {
		return center;
	}
	public void render() {
		Renderer.renderPixel(p1, p2, p4, p3, texture, 0, 0, dimension, WHITE);
		
	}

	public void attaque(int damage, Entity boss) {
		hitBox = new Quadrilataire(p1, p2, p3, p4);

		Game.level.giveDamageSquare(hitBox, boss, damage);
		
	}
}
