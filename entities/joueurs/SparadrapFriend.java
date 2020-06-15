package fr.pierrehb.entities.joueurs;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Equipement;
import fr.pierrehb.main.Main;

public class SparadrapFriend extends Friend{

	public SparadrapFriend() {
		Equipement weapon = ((Equipement) Game.getItem(SparadrapStats.getWeapon()));
		Equipement armor = ((Equipement) Game.getItem(SparadrapStats.getArmor()));
		this.damage=weapon.getDamage()+armor.getDamage();
		this.lvl=SparadrapStats.getLvl();
		this.maxHeal=30+lvl*2+weapon.getHealth()+armor.getHealth();
		this.armor=armor.getArmor();
		this.name=Renderer.getText(0, 0, "Sparadrap", Main.save.favorite_police, 0.5f);
		this.texture=Texture.loadTexture("res/textures/Human/Joueurs/SparadrapSprit.png");
	}

	@Override
	public int getSignature() {
		return 1;
	}
	@Override
	public boolean inLife() {
		return Main.save.Sparadrap;
	}

	

}
