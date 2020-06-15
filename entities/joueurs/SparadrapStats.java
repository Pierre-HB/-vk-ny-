package fr.pierrehb.entities.joueurs;

import fr.pierrehb.main.Main;

public class SparadrapStats {
	private static final int[] clearInventory= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private static final int[][] attaque1 = {{1,1,0,0,0},{1,1,0,0,0,0,0,0,0,0},{1,0,0,0,0},{2,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};


	public static int getWeapon() {
		if(Main.save.avancement<5) return 2;
		return 0;		
	}
	public static int getArmor() {
		if(Main.save.avancement<5) return 0;
		return 0;		
	}
	public static int[] getInventory() {
		if(Main.save.avancement<5) return clearInventory;
		return clearInventory;
	}
	public static int getLvl() {
		if(Main.save.avancement<4) return 3;
		if(Main.save.avancement==4) return 4;
		return 0;	
	}
	public static int[][] getAttaque(){
		if(Main.save.avancement<5) return attaque1;
		return attaque1;	
	}

}
