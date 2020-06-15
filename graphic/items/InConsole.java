package fr.pierrehb.items;

import org.lwjgl.input.Keyboard;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.main.Main;

public class InConsole {
	public static String oldKey=null;
	public static String key=null;
	public static String command="";
	private static boolean ENTER = true;
	


	
	public static void update() {
		key=getKeyNamePressed();
		if(key!=oldKey) {
			if(key!=null) {
				command+=key;
			}oldKey=key;
		}
		if(command.length()>20) {
			command="";
			ENTER=true;
			Interface.INCONSOLE=false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER) {
				action();
				System.out.println("DONE "+command);
				Interface.INCONSOLE=false;
				command="";
			}ENTER=true;
		}else ENTER=false;
	}
	public static String getKeyNamePressed() {
		String k = null;
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) k = "a";			
		if(Keyboard.isKeyDown(Keyboard.KEY_B)) k = "b";
		if(Keyboard.isKeyDown(Keyboard.KEY_C)) k = "c";
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) k = "d";
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) k = "e";
		if(Keyboard.isKeyDown(Keyboard.KEY_F)) k = "f";
		if(Keyboard.isKeyDown(Keyboard.KEY_G)) k = "g";
		if(Keyboard.isKeyDown(Keyboard.KEY_H)) k = "h";
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) k = "i";
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) k = "j";
		if(Keyboard.isKeyDown(Keyboard.KEY_K)) k = "k";
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) k = "l";
		if(Keyboard.isKeyDown(Keyboard.KEY_M)) k = "m";
		if(Keyboard.isKeyDown(Keyboard.KEY_N)) k = "n";
		if(Keyboard.isKeyDown(Keyboard.KEY_O)) k = "o";
		if(Keyboard.isKeyDown(Keyboard.KEY_P)) k = "p";
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) k = "q";
		if(Keyboard.isKeyDown(Keyboard.KEY_R)) k = "r";
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) k = "s";
		if(Keyboard.isKeyDown(Keyboard.KEY_T)) k = "t";
		if(Keyboard.isKeyDown(Keyboard.KEY_U)) k = "u";
		if(Keyboard.isKeyDown(Keyboard.KEY_V)) k = "v";
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) k = "w";
		if(Keyboard.isKeyDown(Keyboard.KEY_X)) k = "x";
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)) k = "y";
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)) k = "z";
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) k = " ";
		if(Keyboard.isKeyDown(Keyboard.KEY_1)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) k = "1";
		if(Keyboard.isKeyDown(Keyboard.KEY_2)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) k = "2";
		if(Keyboard.isKeyDown(Keyboard.KEY_3)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) k = "3";
		if(Keyboard.isKeyDown(Keyboard.KEY_4)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) k = "4";
		if(Keyboard.isKeyDown(Keyboard.KEY_5)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) k = "5";
		if(Keyboard.isKeyDown(Keyboard.KEY_6) && (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))) k = "6";
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) k = "6";
		if(Keyboard.isKeyDown(Keyboard.KEY_6) && !(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))) k = "-";
		if(Keyboard.isKeyDown(Keyboard.KEY_7)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) k = "7";
		if(Keyboard.isKeyDown(Keyboard.KEY_8)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) k = "8";
		if(Keyboard.isKeyDown(Keyboard.KEY_9)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) k = "9";
		if(Keyboard.isKeyDown(Keyboard.KEY_0)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)) k = "0";
		return k;
	}

	private static String extract(String text, int start) {
		String result="";
		for (int i = start;i<text.length();i++) result+=text.charAt(i);
		return result;
	}
	private static int getInt(String chiffre) {
		if (chiffre.length()==0) return -2147483648;
		int result = 0;
		int start = 0;
		if(chiffre.charAt(0)=='-')start=1;
		int l = chiffre.length();
		for (int i =start; i<chiffre.length();i++) {
			switch (chiffre.charAt(i)) {
			case '0':break;
			case '1':result+=Math.pow(10, l-i-1);break;
			case '2':result+=2*Math.pow(10, l-i-1);break;
			case '3':result+=3*Math.pow(10, l-i-1);break;
			case '4':result+=4*Math.pow(10, l-i-1);break;
			case '5':result+=5*Math.pow(10, l-i-1);break;
			case '6':result+=6*Math.pow(10, l-i-1);break;
			case '7':result+=7*Math.pow(10, l-i-1);break;
			case '8':result+=8*Math.pow(10, l-i-1);break;
			case '9':result+=9*Math.pow(10, l-i-1);break;
			default: return -2147483648;
			}
		}
		if (start==1)return -result;
		return result;
	}
	
	private static boolean compare(String commande) {
		boolean result = true;
		if(command.length()<commande.length())result=false;
		else for(int i = 0;i<commande.length();i++)if(command.charAt(i)!=commande.charAt(i))result=false;
		return result;
	}
	
	private static void action() {
		if(command=="")return;
		if(compare("load")) Game.level.loadingConsol();
		if(compare("room"))if(getInt(extract(command,5))!=-2147483648)Main.save.room=getInt(extract(command,5));
		if(compare("progres"))if(getInt(extract(command,8))!=-2147483648)Main.save.avancement=getInt(extract(command,8));
		if(compare("give"))if(getInt(extract(command,5))!=-2147483648)Game.getPlayer().giveItem(Game.getItem(getInt(extract(command,5))));
		if(compare("x"))if(getInt(extract(command,2))!=-2147483648)Game.getPlayer().x=getInt(extract(command,2));
		if(compare("y"))if(getInt(extract(command,2))!=-2147483648)Game.getPlayer().y=getInt(extract(command,2));
		if(compare("hp"))if(getInt(extract(command,3))!=-2147483648)Game.getPlayer().HP=getInt(extract(command,3));
		if(compare("sparadrap true"))Main.save.Sparadrap=true;
		if(compare("sparadrap false"))Main.save.Sparadrap=false;
		
		
		
	}
}
