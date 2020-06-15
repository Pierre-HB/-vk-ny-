package fr.pierrehb.entities.joueurs;

import fr.pierrehb.main.Main;

public class HackerStats {
	private static final int[] clearInventory= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private static final int[][] attaque1 = {{0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};


	public static int getWeapon() {
		if(Main.save.avancement<5) return 0;
		return 0;		
	}
	public static int getArmor() {
		if(Main.save.avancement<5) return 0;
		return 0;		
	}
	public static int[] getInventory() {
		return clearInventory;
	}
	public static int getLvl() {
		if(Main.save.avancement<5) return 0;
		return 0;	
	}
	public static int[][] getAttaque(){
		if(Main.save.avancement<5) return attaque1;
		return attaque1;	
	}

}
