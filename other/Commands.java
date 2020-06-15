package fr.pierrehb.other;

import fr.pierrehb.main.Main;

public interface Commands {
	public static enum cardinalNS {UP,DOWN,NONE};
	public static enum cardinalEO {LEFT,RIGHT,NONE};
	public static enum click {L,R,RR,RL,RRR,RRL,RLL,RLR,NONE};
	public static int upkey = Main.save.contrôles[0][0];
	public static int upmouse = Main.save.contrôles[0][1];
	public static int downkey = Main.save.contrôles[1][0];
	public static int downmouse = Main.save.contrôles[1][1];
	public static int leftkey = Main.save.contrôles[2][0];
	public static int leftmouse = Main.save.contrôles[2][1];
	public static int rightkey = Main.save.contrôles[3][0];
	public static int rightmouse = Main.save.contrôles[3][1];
	public static int interactkey = Main.save.contrôles[4][0];
	public static int interactmouse = Main.save.contrôles[4][1];
	public static int inventairekey = Main.save.contrôles[5][0];
	public static int inventairemouse = Main.save.contrôles[5][1];
	public static int attaque1key = Main.save.contrôles[6][0];
	public static int attaque1mouse = Main.save.contrôles[6][1];
	public static int attaque2key = Main.save.contrôles[7][0];
	public static int attaque2mouse = Main.save.contrôles[7][1];
	public static int attaque1 = Main.save.contrôles[8][0];
	public static int attaque2 = Main.save.contrôles[9][0];
	public static int attaque3 = Main.save.contrôles[10][0];
	public static int attaque4 = Main.save.contrôles[11][0];
	public static int attaque5 = Main.save.contrôles[12][0];
	}
