package fr.pierrehb.entities.particles;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Pixel;

public class Heal_Particle extends Particle{
	private Pixel[] pixels = new Pixel[5];
	private float angle;
	int heal = 1;


	
	public Heal_Particle(int X, int Y, float Angle, Entity joueur) {
		super(X, Y, 60, joueur);
		this.angle = Angle;
		ini();
	}
	public Heal_Particle(int X, int Y, float Angle, Entity joueur, int Heal) {
		super(X, Y, 60, joueur);
		this.angle = Angle;
		this.heal = Heal;
		ini();
	}
	private void ini() {
		iniPixel();
		speed[0] = (float)((Math.cos(angle+(Math.random()-0.5)/2))*Math.random()*2);
		speed[1] = (float)(-(Math.sin(angle+(Math.random()-0.5)/2))*Math.random()*2);
		timeLife = 30;
		hitBox[0] = (int)x;
		hitBox[1] = (int)y;
		hitBox[2] = (int)x+2;
		hitBox[3] = (int)y+2;
	}
	@Override
	protected void trueUpdate() {
		for(Pixel pixel : pixels) {
			pixel.increaseX(speed[0]);
			pixel.increaseY(speed[1]);
		}
		hitBox[0] = (int)x;
		hitBox[1] = (int)y;
		hitBox[2] = (int)x+2;
		hitBox[3] = (int)y+2;
		if(Game.level.giveHeal(hitBox, joueur, heal))die();
	}

	@Override
	protected void trueRender() {
		for(Pixel pixel : pixels)pixel.render();
	}

	private void iniPixel() {
		
		pixels[0] = new Pixel(1+x,0+y,LIGHTGREEN);
		pixels[1] = new Pixel(0+x,1+y,LIGHTGREEN);
		pixels[2] = new Pixel(1+x,1+y,DARKGREEN);
		pixels[3] = new Pixel(2+x,1+y,LIGHTGREEN);
		pixels[4] = new Pixel(1+x,2+y,LIGHTGREEN);
		
		

	}
	@Override
	protected void mortalEffect() {
		
	}

}