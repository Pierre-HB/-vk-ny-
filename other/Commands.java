package fr.pierrehb.other;

import fr.pierrehb.main.Main;

public interface Commands {
	public static enum cardinalNS {UP,DOWN,NONE};
	public static enum cardinalEO {LEFT,RIGHT,NONE};
	public static enum click {L,R,RR,RL,RRR,RRL,RLL,RLR,NONE};
	public static int upkey = Main.save.contr�les[0][0];
	public static int upmouse = Main.save.contr�les[0][1];
	public static int downkey = Main.save.contr�les[1][0];
	public static int downmouse = Main.save.contr�les[1][1];
	public static int leftkey = Main.save.contr�les[2][0];
	public static int leftmouse = Main.save.contr�les[2][1];
	public static int rightkey = Main.save.contr�les[3][0];
	public static int rightmouse = Main.save.contr�les[3][1];
	public static int interactkey = Main.save.contr�les[4][0];
	public static int interactmouse = Main.save.contr�les[4][1];
	public static int inventairekey = Main.save.contr�les[5][0];
	public static int inventairemouse = Main.save.contr�les[5][1];
	public static int attaque1key = Main.save.contr�les[6][0];
	public static int attaque1mouse = Main.save.contr�les[6][1];
	public static int attaque2key = Main.save.contr�les[7][0];
	public static int attaque2mouse = Main.save.contr�les[7][1];
	public static int attaque1 = Main.save.contr�les[8][0];
	public static int attaque2 = Main.save.contr�les[9][0];
	public static int attaque3 = Main.save.contr�les[10][0];
	public static int attaque4 = Main.save.contr�les[11][0];
	public static int attaque5 = Main.save.contr�les[12][0];
	}
