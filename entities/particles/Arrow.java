package fr.pierrehb.entities.particles;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Mortal_Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;

public class Arrow extends Mortal_Particle{
	
	private float angle;
	private float celerity = 1.0f;
	int damage = 1;
	private int longDistance = 9;
	private int smallDistance = 3;
	private float litleAngle = 0.3218505544f;
	private Texture arrow = Texture.loadTexture("res/textures/entity/Arrow.png");
	private float[] nb_texture = {1.0f, 1.0f};


	
	public Arrow(int X, int Y, float Angle, Entity joueur) {
		super(X, Y, 60, joueur, 1);
		this.angle = Angle;
		ini();
	}
	public Arrow(int X, int Y, float Angle, float Celerity, Entity joueur, int Damage) {
		super(X, Y, 60, joueur, 1);
		this.celerity = Celerity;
		this.angle = Angle;
		this.damage = Damage;
		ini();
	}
	private void ini() {
		speed[0] = (float)Math.cos(angle)*celerity;
		speed[1] = (float)-Math.sin(angle)*celerity;
		timeLife = 120;
		x1 = x;
		x2 = x+(float)Math.cos(angle)*longDistance;
		x3 = x+(float)(Math.cos(angle-litleAngle)*Math.sqrt(longDistance*longDistance+smallDistance*smallDistance));
		x4 = x+(float)Math.cos(angle-Math.PI/2)*smallDistance;
		y1 = y;
		y2 = y-(float)Math.sin(angle)*longDistance;
		y3 = y-(float)(Math.sin(angle-litleAngle)*Math.sqrt(longDistance*longDistance+smallDistance*smallDistance));
		y4 = y-(float)Math.sin(angle-Math.PI/2)*smallDistance;
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
		p3 = new Point(x3, y3);
		p4 = new Point(x4, y4);
		square = new Quadrilataire(p1, p2, p3, p4);




	}
	@Override
	protected void trueUpdate() {
	
		
		p1.x += speed[0];
		p2.x += speed[0];
		p3.x += speed[0];
		p4.x += speed[0];
		p1.y += speed[1];
		p2.y += speed[1];
		p3.y += speed[1];
		p4.y += speed[1];

		
		square = new Quadrilataire(p1, p2, p3, p4);

		if(Game.level.giveDamageSquare(square, joueur, damage))die();
		if(HP <= 0)die();
	}

	@Override
	protected void trueRender() {
	Renderer.renderPixel(p1, p2, p3, p4, arrow, 0, 0, nb_texture, WHITE);
	}
	@Override
	protected void mortalEffect() {
		
	}

	

}
