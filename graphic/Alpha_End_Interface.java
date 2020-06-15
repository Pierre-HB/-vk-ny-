package fr.pierrehb.graphic;

import fr.pierrehb.game.Game;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Commands;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.other.Polices;

public class Alpha_End_Interface implements Color, Polices, Dialogue, Commands{
	private static final Caractere[] end = Renderer.getText(105, 5, str_alpha_end_end, Main.save.favorite_police, 2f);
	private static final Caractere[] comingSoon = Renderer.getText(10, 60, str_alpha_end_comingSoon, Main.save.favorite_police, 0.8f);
	private static final Caractere[] created = Renderer.getText(50, 150, str_alpha_end_created, Main.save.favorite_police, 0.8f);


	public Alpha_End_Interface() {
		
	}
	public void render() {
		Renderer.renderInterfaceTexte(end,WHITE,0,(int)-Game.getPlayer().y);
		Renderer.renderInterfaceTexte(comingSoon,WHITE,0,(int)-Game.getPlayer().y);
		Renderer.renderInterfaceTexte(created,WHITE,0,(int)-Game.getPlayer().y);
		
	}
	public void update() {
		
	}

}
