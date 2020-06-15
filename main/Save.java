package fr.pierrehb.main;

import java.io.Serializable;
import org.lwjgl.input.Keyboard;
import fr.pierrehb.other.ObjectsStates;
import fr.pierrehb.other.Polices.polices;

public class Save implements Serializable, ObjectsStates {

	private static final long serialVersionUID = 1L;
	public  String name="premierJeu";
	public  int room;
	public  int[] coordinates = new int[2];
	public  int[] size = {1024, 576};
	public resolution dimension = resolution.R1024;
	public classes classe = classes.NONE;
	public polices favorite_police = polices.COMIC_SANS_MS;
	public int playerHP = 30;
	public int[][] contrôles = {	{Keyboard.KEY_Z, -1},{Keyboard.KEY_S, -1},{Keyboard.KEY_Q, -1},{Keyboard.KEY_D, -1},
									{Keyboard.KEY_F, -1},{Keyboard.KEY_E, -1},{-1, 0},{-1, 1},{Keyboard.KEY_1, -1},{Keyboard.KEY_2, -1},{Keyboard.KEY_3, -1},{Keyboard.KEY_4, -1},{Keyboard.KEY_5, -1}};
	// UP / DOWN / LEFT / RIGHT / INTERACT / INVENTORY / ATTAQUE1 / ATTAQUE2
	public  boolean Sparadrap = true;
	public boolean Hacker = true;
	public float musicVolume = 1.0f;
	public float soundVolume = 1.0f;
	public int[][] playerAttacks = {{0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};
	// Sloat débloquer ( 0 = lock, 1 = unlock / level des attaques ( 0 = lock, 1 = level 1, 2 = level 2...) / combinaison des attaques
	//({-1} = lock sinon {2,5,9} = attaque 2 puis 5 puis 9)
	public int[] playerInventory = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public int playerWeapon = 0;
	public int playerArmor = 0;
	public int playerXP=0;
	public int playergold=0;
	public int[] friends= {0,0};
	public int avancement=-1;

	public Save() {}
	
	public void reSet() {
		classe = classes.NONE;
		room = 2;
		coordinates[0] = 7;
		coordinates[1] = 5;
		playerHP = 30;
		playerXP=0;
		playergold=0;
		avancement=0;
		for(int a = 0; a < playerAttacks.length; a++) {
			for(int b = 0; b < playerAttacks[a].length; b++) {
				playerAttacks[a][b] = 0;
			}}
		playerAttacks[2][0] = playerAttacks[3][0] = playerAttacks[4][0] = playerAttacks[5][0] = playerAttacks[6][0] = -1;
		for(int i = 0;i<16;i++) {
			playerInventory[i]=0;
		}
		for(int i=0; i<friends.length;i++) {
			friends[i]=0;
		}
		playerWeapon=0;
		playerArmor=0;
	}
}
