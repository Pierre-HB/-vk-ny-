package fr.pierrehb.entities.joueurs;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;
import fr.pierrehb.main.Main;

public class HackerFriend extends Friend{

	public HackerFriend() {

		Equipement weapon = ((Equipement) Game.getItem(HackerStats.getWeapon()));
		Equipement armor = ((Equipement) Game.getItem(HackerStats.getArmor()));
		this.damage=weapon.getDamage()+armor.getDamage();
		this.lvl=HackerStats.getLvl();
		this.maxHeal=30+lvl*2+weapon.getHealth()+armor.getHealth();
		this.armor=armor.getArmor();
		this.name=Renderer.getText(0, 0, "Ha(k3r", Main.save.favorite_police, 0.5f);
		this.texture=Texture.loadTexture("res/textures/Human/Joueurs/Ha(k3r.png");
	}

	@Override
	public int getSignature() {
		return 0;
	}

	@Override
	public boolean inLife() {
		return Main.save.Hacker;
	}

	

}


