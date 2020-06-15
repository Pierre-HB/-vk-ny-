package fr.pierrehb.entities.ennemis;

import fr.pierrehb.attaques.attaque.Souris;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.PNJ;
import fr.pierrehb.entities.particles.Smoke;
import fr.pierrehb.game.Game;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;
import fr.pierrehb.graphic.Texture;

public abstract class Ennemy extends PNJ{
	private int[] minColor1 = {120, 120, 120};
	private int[] maxColor1 = {155, 155, 155};
	private int[] minColor2 = {150, 50, 50};
	private int[] maxColor2 = {255, 60, 60};
	protected int dieXp=0;
	protected int dieGold=0;
	private static Sound_lector sound;

	public Ennemy(int x, int y, String name, classes Classe, boolean canmove, direction direction, Texture tex) {
		super(x, y, name, Classe, canmove, direction, tex);
	}
	
	@Override
	protected void die() {
		Game.level.giveXpGold(dieXp, dieGold);
		remove = true;
		for(int a = 0; a < 50; a++) {
			Game.level.addParticle(new Smoke((int)x+8, (int)y+16, random(40, 80), (Human)mouse.getEntity(), minColor1, maxColor1, minColor1, maxColor1));
		}
		for(int a = 0; a < 25; a++) {
			Game.level.addParticle(new Smoke((int)x+8, (int)y+16, random(40, 80), (Human)mouse.getEntity(), minColor2, maxColor2, minColor2, maxColor2));
		}
		sound = new Sound_lector("res/sound/sou/ennemyDie.wav", Main.save.soundVolume);
		sound.start();
		
	}
	
	@Override
	public void dialogueRender() {
		
	}
	
	@Override
	protected boolean interat() {return false;}
	@Override
	public void setMouse(Souris Mouse) {}
	public void getTarget(int distance) {
		
		float D;
		float littleD = distance +1;
		for(Human human : Game.level.humans) {
			D = (float)Math.sqrt((x-human.x)*(x-human.x)+(y-human.y)*(y-human.y));

			if(human.life && D < distance && D < littleD && human.team != team && human.team != -2) {
				littleD = D;
				hasTarget = true;
				target = human;
				mouse.setTarget(human);
			}
		}
	}
	@Override
	public void setParty(Human demandeur) {
		Team.answerForParty(false);
	}

}
