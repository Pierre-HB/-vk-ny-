package fr.pierrehb.game;

import org.lwjgl.opengl.GL11;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Friend;
import fr.pierrehb.entities.joueurs.HackerFriend;
import fr.pierrehb.entities.joueurs.SparadrapFriend;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.items.*;
import fr.pierrehb.items.armors.FinalArmor;
import fr.pierrehb.items.armors.NewBeggeningArmor;
import fr.pierrehb.items.bows.BasicBow;
import fr.pierrehb.items.knifes.BasicKnife;
import fr.pierrehb.items.others.Candy;
import fr.pierrehb.items.swords.BasicSword;
import fr.pierrehb.items.wands.BasicWand;
import fr.pierrehb.levels.Alpha_End;
import fr.pierrehb.levels.Level;
import fr.pierrehb.levels.Level_1;
import fr.pierrehb.levels.Level_2;
import fr.pierrehb.levels.Level_3;
import fr.pierrehb.levels.Level_4;
import fr.pierrehb.levels.ThirdMenu;
import fr.pierrehb.levels.end_Castel.EndCastel_1;
import fr.pierrehb.levels.end_Castel.EndCastel_2;
import fr.pierrehb.levels.first_castel.Castel_1;
import fr.pierrehb.levels.first_castel.Castel_2;
import fr.pierrehb.levels.levels.Archer_First_Room;
import fr.pierrehb.levels.levels.Archer_Second_Room;
import fr.pierrehb.levels.levels.Guerrier_First_Room;
import fr.pierrehb.levels.levels.Guerrier_Second_Room;
import fr.pierrehb.levels.levels.Mage_First_Room;
import fr.pierrehb.levels.levels.Mage_Second_Room;
import fr.pierrehb.levels.levels.Ninja_First_Room;
import fr.pierrehb.levels.levels.Ninja_Second_Room;
import fr.pierrehb.levels.tutoriel.*;
import fr.pierrehb.main.Main;

public class Game {
	public static Level level;
	public static float[] translateView;
	public static int lvl;
	public static boolean INTRO = false;

	public Game() {
	}
	public void render() {
		translateView = level.translateView();
		Renderer.renderUpdate();
		GL11.glTranslatef(translateView[0], translateView[1], 0);
		level.render();
		if(INTRO)Cinematique.render();
		
	}
	public void update() {
		level.update();
		Annimation.update();
		if(INTRO)Cinematique.update();

	}
	public void start() {
		level = new ThirdMenu();
		level.startLevel();
	}
	public void restart() {
		Team.clearPlayerTeam();
		level = new ThirdMenu();
		level.startLevel();
	}
	public static void nexLevel() {
		Team.clearPlayerTeam();
		
		level = getLevel(Main.save.room);
			
		level.startLevel();
		

	}
	public static void restartLevel() {
		Team.clearPlayerTeam();
		level.stopMusic();
		switch(Main.save.room) {
		case 17:
			int[] newCoordinates = {112,64};
			Main.save.coordinates = newCoordinates;
			level = getLevel(16);
			Main.save();
			break;
		default:
			level = getLevel(Main.save.room);
			break;
		}
		level.startLevel();
	}
	public static Level getLevel(int LVL) {
		lvl = LVL;
		Level level;
		switch (lvl) {
			case 1:level = new Level_1();break;
			case 2:level = new Level_2();break;
			case 3:level = new Level_3();break;
			case 4:level = new Level_4();break;
			case 5:level = new Guerrier_First_Room();break;
			case 6:level = new Mage_First_Room();break;
			case 7:level = new Archer_First_Room();break;
			case 8:level = new Ninja_First_Room();break;
			case 10:level = new Guerrier_Second_Room();break;
			case 11:level = new Mage_Second_Room();break;
			case 12:level = new Archer_Second_Room();break;
			case 13:level = new Ninja_Second_Room();break;
			case 14:level = new Level_5();break;
			case 15:level = new Level_6();break;
			case 16:level = new Level_7();break;
			case 17:level = new Boss_Room_1();break;
			case 18:level = new Castel_1();break;
			case 19:level = new Castel_2();break;
			case -1:level = new EndCastel_1();break;
			case -2:level = new EndCastel_2();break;
			case -42:level = new Alpha_End();break;
			default:level = new ThirdMenu();
		}
		return level;
	}
	public static Item getItem(int ID) {
		switch(ID) {
		case -42: return new Console();
		case -1: return new FinalArmor();
		case -2: return new NewBeggeningArmor();
		case 1:return new BasicSword();
		case 2:return new BasicWand();
		case 3:return new BasicBow();
		case 4:return new BasicKnife();
		case 5:return new Candy();
		default:return new NoItem();
		}
		
	}
	public static Friend getFriend(int friend) {
		switch (friend) {
		case 1:return new SparadrapFriend();
		case 2:return new HackerFriend();
		default:return null;
		}
	}
	public static float[] getPlayerCoord() {
		float[] coord = {(int)Level.player.x, (int)Level.player.y};
		return coord;
	}public static  Player getPlayer() {
		return Level.player;
	}
	public static float[] getFocusCoord() {
		return level.getFocusCoord();
	}
	
	
}
