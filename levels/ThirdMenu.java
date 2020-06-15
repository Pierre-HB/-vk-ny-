package fr.pierrehb.levels;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

import org.lwjgl.input.Keyboard;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.game.Cinematique;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Cadre;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.graphic.Liste;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.main.Save;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.sound.Sound_lector;

public class ThirdMenu extends Level implements Dialogue, Color{
	private static boolean UP = false;
	private static boolean up = false;
	private static boolean DOWN = false;
	private static boolean down = false;
	private static boolean LEFT = false;
	private static boolean releaseLeft = false;
	private static boolean left = false;
	private static boolean RIGHT = false;
	private static boolean right = false;
	private static boolean releaseRight = false;
	private static boolean ENTER = false;
	private static boolean enter = false;
	private static boolean DEL = false;
	private static boolean del = false;
	private float[] translateView = {0,0};
	private static enum menu {MAIN, OPTION, REZOLUTION, SOUND, CONTROL, EREASED, NAME};
	private static menu currentMenu = menu.MAIN;
	private static int mainI = 0;
	private static int optionI = 0;
	private static int rezolutionI = 0;
	private static int soundI = 0;
	private static int controlI = 0;
	private static int ereasedI = 0;
	private static int goI = 0;
	private static boolean GO = false;
	private static int goTimer = 0;
	private static boolean readingKeyboard = false;
	private static boolean readingControls = false;
	private static final int[][] contrôlesOrigineaux = {	{Keyboard.KEY_Z, -1},{Keyboard.KEY_S, -1},{Keyboard.KEY_Q, -1},{Keyboard.KEY_D, -1},
			{Keyboard.KEY_F, -1},{Keyboard.KEY_E, -1},{-1, 0},{-1, 1},{Keyboard.KEY_1, -1},{Keyboard.KEY_2, -1},{Keyboard.KEY_3, -1},{Keyboard.KEY_4, -1},{Keyboard.KEY_5, -1}};

	
	private static final float letterSize = 0.6f;
	private static final Caractere[] helloWorld = Renderer.getText(120, 30, str_menu_helloWorld, Main.save.favorite_police, 1.2f);
	private static final Caractere[] play = Renderer.getText(0, 0, str_menu_play, Main.save.favorite_police, letterSize);
	private static final Caractere[] newGame = Renderer.getText(0, 0, str_menu_newGame, Main.save.favorite_police, letterSize);
	private static final Caractere[] options = Renderer.getText(0, 0, str_menu_options, Main.save.favorite_police, letterSize);
	private static final Caractere[] quitter = Renderer.getText(0, 0, str_menu_quit, Main.save.favorite_police, letterSize);

	private static final Caractere[] rezolutions = Renderer.getText(0, 0, str_menu_rezolution, Main.save.favorite_police, letterSize);
	private static final Caractere[] controls = Renderer.getText(0, 0, str_menu_control, Main.save.favorite_police, letterSize);
	private static final Caractere[] volume = Renderer.getText(0, 0, str_menu_volume, Main.save.favorite_police, letterSize);
	private static final Caractere[] back = Renderer.getText(0, 0, str_menu_back, Main.save.favorite_police, letterSize);

	private static final Caractere[] music = Renderer.getText(200, 15, str_menu_music, Main.save.favorite_police, letterSize);
	private static final Caractere[] sound = Renderer.getText(200, 55, str_menu_sound, Main.save.favorite_police, letterSize);
	

	private static final Caractere[] control1 = Renderer.getText(0, 0, str_menu_avancer, Main.save.favorite_police, letterSize);
	private static final Caractere[] control2 = Renderer.getText(0, 0, str_menu_reculer, Main.save.favorite_police, letterSize);
	private static final Caractere[] control3 = Renderer.getText(0, 0, str_menu_gauche, Main.save.favorite_police, letterSize);
	private static final Caractere[] control4 = Renderer.getText(0, 0, str_menu_droite, Main.save.favorite_police, letterSize);
	private static final Caractere[] control5 = Renderer.getText(0, 0, str_menu_interagir, Main.save.favorite_police, letterSize);
	private static final Caractere[] control6 = Renderer.getText(0, 0, str_menu_inventaire, Main.save.favorite_police, letterSize);
	private static final Caractere[] control7 = Renderer.getText(0, 0, str_menu_attaque1, Main.save.favorite_police, letterSize);
	private static final Caractere[] control8 = Renderer.getText(0, 0, str_menu_attaque2, Main.save.favorite_police, letterSize);
	private static final Caractere[] control9 = Renderer.getText(0, 0, str_menu_attaque3, Main.save.favorite_police, letterSize);
	private static final Caractere[] control10 = Renderer.getText(0, 0, str_menu_attaque4, Main.save.favorite_police, letterSize);
	private static final Caractere[] control11 = Renderer.getText(0, 0, str_menu_attaque5, Main.save.favorite_police, letterSize);
	private static final Caractere[] control12 = Renderer.getText(0, 0, str_menu_reset, Main.save.favorite_police, letterSize);

	
	private static Texture commands = Texture.loadTexture("res/textures/Tile/Menu/Commands.png");
	private static Cadre avancer = new Cadre(75,15,control1,0,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre reculer = new Cadre(75,15,control2,1,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre gauche = new Cadre(75,15,control3,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre droite = new Cadre(75,15,control4,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre interagir = new Cadre(75,15,control5,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre inventaire = new Cadre(75,15,control6,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre attaque1 = new Cadre(75,15,control7,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre attaque2 = new Cadre(75,15,control8,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre attaque3 = new Cadre(75,15,control9,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre attaque4 = new Cadre(75,15,control10,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre attaque5 = new Cadre(75,15,control11,2,0,WHITE,GREEN, BLACK, BLACK, WHITE, GREEN, GOLD, commands);
	private static Cadre reset = new Cadre(75,15,control12,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
	private static Cadre retour = new Cadre(75,15,back,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
	private static Cadre[] controlsSettings = {avancer,reculer,gauche,droite,interagir,inventaire,attaque1,attaque2,attaque3,attaque4,attaque5,reset,retour};
	
	private static Liste controlsList = new Liste(controlsSettings,175,64);
	private static Liste rezolutionList;
	
	private static String playerName = Main.save.name;
	private static Caractere[] name = Renderer.getText(160, 25, "--------", Main.save.favorite_police, 0.8f);
	private static Caractere[] nomJoueur = Renderer.getText(130, 105, playerName, Main.save.favorite_police, 1.2f);
	private static String lastCharactere = null;
	
	private static final Caractere[] accepter = Renderer.getText(0, 0, str_menu_accepter, Main.save.favorite_police, letterSize);
	private static final Caractere[] askName = Renderer.getText(110, 5, str_menu_choose, Main.save.favorite_police, letterSize);
	private static float[] backColor = {0f,0f,0f,0f};
	private static float[] frontColor = {1f,1f,1f,0f};
	private static Caractere[] finalName = Renderer.getText(100, 70, playerName, Main.save.favorite_police, 0.8f);
	private static final Caractere[] itSGo = Renderer.getText(30, 60, str_menu_go, Main.save.favorite_police, 1.7f);
	private static final Caractere[] ereased = Renderer.getText(80, 45, str_menu_warning, Main.save.favorite_police, 0.5f);
	private static final Caractere[] oui = Renderer.getText(0, 0, str_menu_oui, Main.save.favorite_police, 0.5f);
	private static final Caractere[] non = Renderer.getText(0, 0, str_menu_non, Main.save.favorite_police, 0.5f);
	private Sound_lector song;
	
	
	
	@Override
	public void update() {
		if(!GO) {
		keyboardUpdate();
		controlsList.update();
		if(currentMenu==menu.SOUND) {
			if(soundI==0) {
				if(releaseLeft || releaseRight) {
					song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.musicVolume);
					song.start();
				}			
			}
			if(soundI==1) {
				if(releaseLeft || releaseRight) {
					song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
					song.start();
				}			
			}
		}
		if(currentMenu==menu.REZOLUTION)rezolutionList.update();
		if(currentMenu==menu.EREASED) {
			if(left || right)next();
		}
		if(!readingControls) {
			if(!readingKeyboard) {
				if(up)previous();
				if(down)next();
			}
			
			if(enter)select();
		}else readControls();
		if(currentMenu == menu.SOUND) {
			if(soundI == 0) {
				if(RIGHT) {
					if(Main.save.musicVolume==0)Main.save.musicVolume=0.9f;
					Main.save.musicVolume+=0.1f;
				}
				if(Main.save.musicVolume>4.9f)Main.save.musicVolume=4.9f;
				
				if(LEFT)Main.save.musicVolume-=0.1f;
				if(Main.save.musicVolume<0.9f)Main.save.musicVolume=0f;
			}if(soundI==1) {
				if(RIGHT) {
					if(Main.save.soundVolume==0)Main.save.soundVolume=0.9f;
					Main.save.soundVolume+=0.1f;
				}
				if(Main.save.soundVolume>4.9f)Main.save.soundVolume=4.9f;
				
				if(LEFT)Main.save.soundVolume-=0.1f;
				if(Main.save.soundVolume<0.9f)Main.save.soundVolume=0f;
			}
			
		}
		if(currentMenu==menu.NAME)updateName();
		avancer.setLocation(getLetter(Main.save.contrôles[0][0]));
		reculer.setLocation(getLetter(Main.save.contrôles[1][0]));
		gauche.setLocation(getLetter(Main.save.contrôles[2][0]));
		droite.setLocation(getLetter(Main.save.contrôles[3][0]));
		interagir.setLocation(getLetter(Main.save.contrôles[4][0]));
		inventaire.setLocation(getLetter(Main.save.contrôles[5][0]));
		attaque1.setLocation(getLetter(Main.save.contrôles[8][0]));
		attaque2.setLocation(getLetter(Main.save.contrôles[9][0]));
		attaque3.setLocation(getLetter(Main.save.contrôles[10][0]));
		attaque4.setLocation(getLetter(Main.save.contrôles[11][0]));
		attaque5.setLocation(getLetter(Main.save.contrôles[12][0]));
		}
		else {
			goTimer++;
			backColor[3] = goTimer/300f;
			frontColor[3] = goTimer/300f;
			if(goTimer==300)go();
		}
	}
	private void reset() {
		currentMenu = menu.MAIN;
		mainI = 0;
		optionI = 0;
		rezolutionI = 0;
		soundI = 0;
		controlI = 0;
		ereasedI = 0;
		goI = 0;

	}
	
	private void keyboardUpdate() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER)enter = true;
			else enter = false;
			ENTER = true;		
		}else {
			ENTER = false;
			enter = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
			if(!DEL)del = true;
			else del = false;
			DEL = true;		
		}else {
			DEL = false;
			del = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if(!UP)up = true;
			else up = false;
			UP = true;		
		}else {
			UP = false;
			up = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			if(!DOWN)down = true;
			else down = false;
			DOWN = true;		
		}else {
			DOWN = false;
			down = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			if(!LEFT)left = true;
			else left = false;
			LEFT = true;
			releaseLeft=false;
		}else {
			if(LEFT)releaseLeft=true;
			else releaseLeft=false;
			LEFT = false;
			left = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			if(!RIGHT)right = true;
			else right = false;
			RIGHT = true;
			releaseRight=false;
		}else {
			if(RIGHT)releaseRight=true;
			else releaseRight=false;
			RIGHT = false;
			right = false;
		}
	}

	@Override
	public void render() {
		glBindTexture(GL_TEXTURE_2D, 0);
		if(currentMenu == menu.MAIN)Renderer.renderInterfaceTexte(helloWorld, GREEN, 0, 0);
		if(Main.save.avancement!=-1) {
			renderChoice(0,menu.MAIN,17,10,play);
			if(currentMenu==menu.MAIN)Renderer.renderInterfaceTexte(nomJoueur, GREEN, 0, 0);
		}
		renderChoice(1,menu.MAIN,17,43,newGame);
		renderChoice(2,menu.MAIN,17,81,options);
		renderChoice(3,menu.MAIN,17,114,quitter);
		
		if(currentMenu == menu.OPTION || currentMenu == menu.SOUND || currentMenu == menu.CONTROL || currentMenu==menu.REZOLUTION) {
			renderChoice(0,menu.OPTION,96,20,rezolutions);
			renderChoice(1,menu.OPTION,96,48,controls);
			renderChoice(2,menu.OPTION,96,76,volume);
			renderChoice(3,menu.OPTION,96,104,back);
		}
		
		if(currentMenu == menu.SOUND) {
			float volumeSound=(Main.save.soundVolume-0.9f)/4*68+181;
			float volumeMusic=(Main.save.musicVolume-0.9f)/4*68+181;
			if(Main.save.soundVolume==0)volumeSound=181f;
			if(Main.save.musicVolume==0)volumeMusic=181f;
			Renderer.renderSquare(180.0f,250.0f,30.0f,38.0f,WHITE);
			Renderer.renderSquare(180.0f,250.0f,70.0f,78.0f,WHITE);
			Renderer.renderInterfaceTexte(music);
			Renderer.renderInterfaceTexte(sound);
			renderChoice(2,menu.SOUND,180,96,back);
			Renderer.renderSquare(181.0f,volumeMusic,31.0f,37.0f,LIGHTGREY);
			Renderer.renderSquare(181.0f,volumeSound,71.0f,77.0f,LIGHTGREY);
		
		if(soundI==0) {
			
			Renderer.renderSquare(181.0f,volumeMusic,31.0f,37.0f,GREEN);
			Renderer.renderSquare(volumeMusic-2.0f,volumeMusic+2.0f,25.0f,43.0f,GREEN);
			

		}
		if(soundI==1) {
			
			Renderer.renderSquare(181.0f,volumeSound,71.0f,77.0f,GREEN);
			
			Renderer.renderSquare(volumeSound-2.0f,volumeSound+2.0f,65.0f,83.0f,GREEN);

		}}
		if(currentMenu == menu.CONTROL)controlsList.render();
		if(currentMenu==menu.REZOLUTION)rezolutionList.render();
		if(currentMenu==menu.NAME) {
			Renderer.renderSquare(140.0f,230.0f,20.0f,40.0f,WHITE);
			Renderer.renderSquare(141.0f,229.0f,21.0f,39.0f,BLACK);
			Renderer.renderInterfaceTexte(name);
			Renderer.renderInterfaceTexte(askName);
			renderChoice(0,menu.NAME,150,70,back);
			renderChoice(1,menu.NAME,150,100,accepter);
		}
		if(currentMenu==menu.EREASED) {
			Renderer.renderQuad(4.0f/20.0f, 4.0f/20.0f, 16.0f/20.0f, 16.0f/20.0f, WHITE);
			Renderer.renderQuad(5.0f/20.0f, 5.0f/20.0f, 15.0f/20.0f, 15.0f/20.0f, BLACK);
			Renderer.renderInterfaceTexte(ereased);
			renderLittleChoice(0,menu.EREASED, 80, 80, non);
			renderLittleChoice(1,menu.EREASED, 135, 80, oui);
		}
		if(GO) {
			Renderer.renderSquare(0f,256f,0f,144f,backColor);
			Renderer.renderInterfaceTexte(itSGo,frontColor ,0 ,0);
			Renderer.renderInterfaceTexte(finalName);
			;
		}

		
	}

	private float[] getColor(int rang, menu liste) {
		if( liste == currentMenu) {
			switch(liste) {
			case CONTROL:if(rang==controlI)return GREEN;
				return WHITE;
			case EREASED:if(rang==ereasedI)return GREEN;
				return WHITE;
			case MAIN:if(rang==mainI)return GREEN;
				return WHITE;
			case NAME:
				if(rang==1 && playerName.length()==0)return GREY;
				if(rang==goI)return GREEN;
				return WHITE;
			case OPTION:if(rang==optionI)return GREEN;
				return WHITE;
			case REZOLUTION:if(rang==rezolutionI)return GREEN;
				return WHITE;
			case SOUND:if(rang==soundI)return GREEN;
				return WHITE;
			default:
				break;			
			}
		}return WHITE;
	}
	
	private void renderChoice(int rang, menu liste, int x, int y, Caractere[] text) {
		float[] color = getColor(rang, liste);
		Renderer.renderInterface(x,y,x+65, y+17, color);
		Renderer.renderInterface(x+1,y+1,x+64,y+16, BLACK);
		int lenght = (int)(64-(text[text.length-1].getCoord()[0]+text[text.length-1].getOffset())*letterSize)/2;
		Renderer.renderInterfaceTexte(text, color, x+lenght, y+4);
	}
	private void renderLittleChoice(int rang, menu liste, int x, int y, Caractere[] text) {
		float[] color = getColor(rang, liste);
		Renderer.renderInterface(x,y,x+35, y+17, color);
		Renderer.renderInterface(x+1,y+1,x+34,y+16, BLACK);
		int lenght = (int)(34-(text[text.length-1].getCoord()[0]+text[text.length-1].getOffset())*letterSize)/2;
		Renderer.renderInterfaceTexte(text, color, x+lenght, y+4);
	}
	
	private void next() {
		boolean startSound = true;
		switch(currentMenu) {
		case CONTROL:controlsList.next();break;
		case EREASED:ereasedI=1-ereasedI;break;
		case MAIN:
			if(Main.save.avancement!=-1) {
				mainI++;if(mainI==4)mainI=0;break;
			}else {
				mainI++;if(mainI==4)mainI=1;break;
			}
		case NAME:if(playerName.length()!=0)goI= 1-goI;break;
		case OPTION:optionI++;if(optionI==4)optionI=0;break;
		case REZOLUTION:rezolutionList.next();break;
		case SOUND:soundI++;if(soundI==3)soundI=0;break;
		default:
			startSound=false;
			break;		
		}
		if(startSound) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
		}
	}
	private void previous() {
		boolean startSound = true;
		switch(currentMenu) {
		case CONTROL:controlsList.previous();break;
		case EREASED:ereasedI=1-ereasedI;break;
		case MAIN:if(Main.save.avancement!=-1) {
			mainI--;if(mainI==-1)mainI=3;break;
		}else {
			mainI--;if(mainI==0)mainI=3;break;
		}
		case NAME:if(playerName.length()!=0)goI= 1-goI;break;
		case OPTION:optionI--;if(optionI==-1)optionI=3;break;
		case REZOLUTION:rezolutionList.previous();break;
		case SOUND:soundI--;if(soundI==-1)soundI=2;break;
		default:
			startSound=false;
			break;		
		}
		if(startSound) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
		}
	}
	private void select() {
		boolean startSound = true;
		switch(currentMenu) {
		case CONTROL:
			if(controlsSettings.length-3>=controlsList.select()) {
			controlsSettings[controlsList.select()].waite();readingControls=true;break;
			}else if(controlsSettings.length-2==controlsList.select()) {
				resetControl();
				break;
			}else {
				currentMenu=menu.OPTION;
				controlsList.reset();
				break;
			}
		case EREASED:if(ereasedI==0)currentMenu=menu.MAIN;
		else {
			currentMenu=menu.NAME;
			playerName = "";
			loadPlayerName();
		}
		ereasedI=0;
			break;
		case MAIN:
			if(mainI==0) {
				if(Main.save.avancement>1) {
					Game.nexLevel();
					ENTER=true;
				}
				else {
					Main.save.reSet();
					Main.save.room=2;
					int[] c = {136, 72};
					Main.save.coordinates = c;
					Main.save();
					Game.INTRO = true;
					Cinematique.startIntro();
					ENTER=true;
				}
			}
			if(mainI==2)currentMenu=menu.OPTION;
			if(mainI==3)Main.end();
			if(mainI==1) {
				if(Main.save.avancement==-1) {
					currentMenu=menu.NAME;
					playerName = "";
					loadPlayerName();
				}else currentMenu=menu.EREASED;
			}
			break;
		case NAME:if(goI==0)currentMenu=menu.MAIN;
		else {
			finalName = Renderer.getText(160, 25, playerName, Main.save.favorite_police, 0.8f);
			GO=true;
		}
			break;
		case OPTION:if(optionI==3)currentMenu=menu.MAIN;if(optionI==2)currentMenu=menu.SOUND;if(optionI==1)currentMenu=menu.CONTROL;if(optionI==0) {currentMenu=menu.REZOLUTION;createRezolutionList(0);}break;
		case REZOLUTION:
			if(rezolutionList.length-1==rezolutionList.select())currentMenu=menu.OPTION;
			else setRezolution();
			break;
		case SOUND:if(soundI==2)currentMenu=menu.OPTION;soundI=0;break;
		default:
			startSound=false;
			break;		
		}
		if(startSound) {
			song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
			song.start();
		}
	}
	private static void loadPlayerName() {
		String nom = playerName;
		for (int x = playerName.length(); x < 8; x++) {
			nom+="-";
		}
		name = Renderer.getText(160, 25, nom, Main.save.favorite_police, 0.8f);
		
	}
	private static void setRezolution() {
		switch(rezolutionList.select()) {
		case 0:	int[] s1 = {1920, 1080};Main.save.size=s1; Main.save.dimension = Save.resolution.R1920;break;
		case 1:	int[] s2 = {1776, 1000};Main.save.size=s2; Main.save.dimension = Save.resolution.R1776;break;
		case 2: int[] s3 = {1600, 900};	Main.save.size=s3; Main.save.dimension = Save.resolution.R1600;break;
		case 3:	int[] s4 = {1280, 720};	Main.save.size=s4; Main.save.dimension = Save.resolution.R1280;break;
		case 4:	int[] s5 = {1024, 576}; Main.save.size=s5; Main.save.dimension = Save.resolution.R1024;break;
		case 5:	int[] s6 = {640, 360};	Main.save.size =s6; Main.save.dimension = Save.resolution.R640;break;
		
		}
	Main.save();
	Main.reloadDisplay();
	reload();
		
	}
	private static void createRezolutionList(int start) {
		Cadre rez1 = new Cadre(75,15,str_menu_1920,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		Cadre rez2 = new Cadre(75,15,str_menu_1776,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		Cadre rez3 = new Cadre(75,15,str_menu_1600,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		Cadre rez4 = new Cadre(75,15,str_menu_1280,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		Cadre rez5 = new Cadre(75,15,str_menu_1024,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		Cadre rez6 = new Cadre(75,15,str_menu_640,WHITE,GREEN,BLACK,BLACK,WHITE,GREEN,false);
		rez1.toString();
		switch(Main.save.dimension) {
		case R1920:rez1 = new Cadre(75,15,str_menu_1920,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		case R1776:rez2 = new Cadre(75,15,str_menu_1776,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		case R1600:rez3 = new Cadre(75,15,str_menu_1600,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		case R1280:rez4 = new Cadre(75,15,str_menu_1280,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		case R1024:rez5 = new Cadre(75,15,str_menu_1024,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		case R640:rez6 = new Cadre(75,15,str_menu_640,LIGHTBLUE,GREEN,BLACK,BLACK,LIGHTBLUE,GREEN,false);break;
		}
		Cadre[] rezs = {rez1, rez2, rez3, rez4, rez5, rez6, retour}; 
		rezolutionList= new Liste(rezs,175,64, start);
	}
	private static void reload() {
		createRezolutionList(rezolutionList.select());
	}
	
	private void readControls() {
		int key = getKeyPressed();
		if(enter) {
			readingControls=false;
			controlsSettings[controlsList.select()].unWait();
			return;
		}
		if(key==-1 || keyIsUse(key))return;
		
		switch(controlsList.select()) {
			case 0:Main.save.contrôles[0][0]=key;break;
			case 1:Main.save.contrôles[1][0]=key;break;
			case 2:Main.save.contrôles[2][0]=key;break;
			case 3:Main.save.contrôles[3][0]=key;break;
			case 4:Main.save.contrôles[4][0]=key;break;
			case 5:Main.save.contrôles[5][0]=key;break;
			case 6:Main.save.contrôles[8][0]=key;break;
			case 7:Main.save.contrôles[9][0]=key;break;
			case 8:Main.save.contrôles[10][0]=key;break;
			case 9:Main.save.contrôles[11][0]=key;break;
			case 10:Main.save.contrôles[12][0]=key;break;
			
		}
		readingControls=false;
		controlsSettings[controlsList.select()].unWait();
	}
	private boolean keyIsUse(int key) {
		boolean use = false;
		for(int[] cont : Main.save.contrôles) {
			if(key == cont[0]) use = true;
		}
		return use;
	}
	private void resetControl() {
		for (int x = 0; x < Main.save.contrôles.length; x++) {
			for (int y = 0; y< Main.save.contrôles[x].length; y++) {
				Main.save.contrôles[x][y] = contrôlesOrigineaux[x][y];
			}
		}
	}
	
	private void updateName() {
		String key = getKeyNamePressed();
		if(key != null && playerName.length()<8 && key != lastCharactere) {
			playerName+=key;
			loadPlayerName();
		}
		if(del) {
			playerName=removeCharactereName();
			loadPlayerName();
			if(playerName.length()==0)goI=0;
		}
		lastCharactere=key;
	}
	private String removeCharactereName() {
		if(playerName.length()==0)return "";
		String temp ="";
		for (int i = 0; i<playerName.length()-1; i++) {
			temp+=playerName.charAt(i);
		}return temp;
	}
	public void go() {
		GO=false;
		goTimer=0;
		mainI=0;
		currentMenu=menu.MAIN;
		backColor[3]=0f;
		frontColor[3]=0f;
		Main.save.reSet();
		Main.save.name = playerName;
		Main.save.room=2;
		int[] c = {136, 72};
		Main.save.coordinates = c;
		Main.save();
		Game.INTRO = true;
		ENTER=true;
		Cinematique.startIntro();
	}
	public int getKeyPressed() {
		int k = -1;
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) k = Keyboard.KEY_A;			
		if(Keyboard.isKeyDown(Keyboard.KEY_B)) k = Keyboard.KEY_B;
		if(Keyboard.isKeyDown(Keyboard.KEY_C)) k = Keyboard.KEY_C;
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) k = Keyboard.KEY_D;
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) k = Keyboard.KEY_E;
		if(Keyboard.isKeyDown(Keyboard.KEY_F)) k = Keyboard.KEY_F;
		if(Keyboard.isKeyDown(Keyboard.KEY_G)) k = Keyboard.KEY_G;
		if(Keyboard.isKeyDown(Keyboard.KEY_H)) k = Keyboard.KEY_H;
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) k = Keyboard.KEY_I;
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) k = Keyboard.KEY_J;
		if(Keyboard.isKeyDown(Keyboard.KEY_K)) k = Keyboard.KEY_K;
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) k = Keyboard.KEY_L;
		if(Keyboard.isKeyDown(Keyboard.KEY_M)) k = Keyboard.KEY_M;
		if(Keyboard.isKeyDown(Keyboard.KEY_N)) k = Keyboard.KEY_N;
		if(Keyboard.isKeyDown(Keyboard.KEY_O)) k = Keyboard.KEY_O;
		if(Keyboard.isKeyDown(Keyboard.KEY_P)) k = Keyboard.KEY_P;
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) k = Keyboard.KEY_Q;
		if(Keyboard.isKeyDown(Keyboard.KEY_R)) k = Keyboard.KEY_R;
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) k = Keyboard.KEY_S;
		if(Keyboard.isKeyDown(Keyboard.KEY_T)) k = Keyboard.KEY_T;
		if(Keyboard.isKeyDown(Keyboard.KEY_U)) k = Keyboard.KEY_U;
		if(Keyboard.isKeyDown(Keyboard.KEY_V)) k = Keyboard.KEY_V;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) k = Keyboard.KEY_W;
		if(Keyboard.isKeyDown(Keyboard.KEY_X)) k = Keyboard.KEY_X;
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)) k = Keyboard.KEY_Y;
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)) k = Keyboard.KEY_Z;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) k = Keyboard.KEY_UP;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) k = Keyboard.KEY_DOWN;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) k = Keyboard.KEY_LEFT;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) k = Keyboard.KEY_RIGHT;
		if(Keyboard.isKeyDown(Keyboard.KEY_1)) k = Keyboard.KEY_1;
		if(Keyboard.isKeyDown(Keyboard.KEY_2)) k = Keyboard.KEY_2;
		if(Keyboard.isKeyDown(Keyboard.KEY_3)) k = Keyboard.KEY_3;
		if(Keyboard.isKeyDown(Keyboard.KEY_4)) k = Keyboard.KEY_4;
		if(Keyboard.isKeyDown(Keyboard.KEY_5)) k = Keyboard.KEY_5;
		if(Keyboard.isKeyDown(Keyboard.KEY_6)) k = Keyboard.KEY_6;
		if(Keyboard.isKeyDown(Keyboard.KEY_7)) k = Keyboard.KEY_7;
		if(Keyboard.isKeyDown(Keyboard.KEY_8)) k = Keyboard.KEY_8;
		if(Keyboard.isKeyDown(Keyboard.KEY_9)) k = Keyboard.KEY_9;
		if(Keyboard.isKeyDown(Keyboard.KEY_0)) k = Keyboard.KEY_0;
		return k;
	}
	public String getKeyNamePressed() {
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
		if(k != null && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)||Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)))k=upperCharactere(k);
		if(Keyboard.isKeyDown(Keyboard.KEY_1)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) k = "1";
		if(Keyboard.isKeyDown(Keyboard.KEY_2)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) k = "2";
		if(Keyboard.isKeyDown(Keyboard.KEY_3)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) k = "3";
		if(Keyboard.isKeyDown(Keyboard.KEY_4)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) k = "4";
		if(Keyboard.isKeyDown(Keyboard.KEY_5)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) k = "5";
		if(Keyboard.isKeyDown(Keyboard.KEY_6)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) k = "6";
		if(Keyboard.isKeyDown(Keyboard.KEY_7)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) k = "7";
		if(Keyboard.isKeyDown(Keyboard.KEY_8)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) k = "8";
		if(Keyboard.isKeyDown(Keyboard.KEY_9)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) k = "9";
		if(Keyboard.isKeyDown(Keyboard.KEY_0)||Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)) k = "0";
		return k;
	}
	private String upperCharactere(String chara) {
		switch(chara) {
		case"a":return"A";
		case"b":return"B";
		case"c":return"C";
		case"d":return"D";
		case"e":return"E";
		case"f":return"F";
		case"g":return"G";
		case"h":return"H";
		case"i":return"I";
		case"j":return"J";
		case"k":return"K";
		case"l":return"L";
		case"m":return"M";
		case"n":return"N";
		case"o":return"O";
		case"p":return"P";
		case"q":return"Q";
		case"r":return"R";
		case"s":return"S";
		case"t":return"T";
		case"u":return"U";
		case"v":return"V";
		case"w":return"W";
		case"x":return"X";
		case"y":return"Y";
		case"z":return"Z";
		default:return "";
		}
	}
	private int[] getLetter(int key) {
		int[] answer = {0,0};
		switch (key) {
			case Keyboard.KEY_A:answer[0] = 0;	answer[1] = 0;	break;
			case Keyboard.KEY_B:answer[0] = 1;	answer[1] = 0;	break;
			case Keyboard.KEY_C:answer[0] = 2;	answer[1] = 0;	break;
			case Keyboard.KEY_D:answer[0] = 3;	answer[1] = 0;	break;
			case Keyboard.KEY_E:answer[0] = 4;	answer[1] = 0;	break;
			case Keyboard.KEY_F:answer[0] = 5;	answer[1] = 0;	break;
			case Keyboard.KEY_G:answer[0] = 6;	answer[1] = 0;	break;
			case Keyboard.KEY_H:answer[0] = 0;	answer[1] = 1;	break;
			case Keyboard.KEY_I:answer[0] = 1;	answer[1] = 1;	break;
			case Keyboard.KEY_J:answer[0] = 2;	answer[1] = 1;	break;
			case Keyboard.KEY_K:answer[0] = 3;	answer[1] = 1;	break;
			case Keyboard.KEY_L:answer[0] = 4;	answer[1] = 1;	break;
			case Keyboard.KEY_M:answer[0] = 5;	answer[1] = 1;	break;
			case Keyboard.KEY_N:answer[0] = 6;	answer[1] = 1;	break;
			case Keyboard.KEY_O:answer[0] = 0;	answer[1] = 2;	break;
			case Keyboard.KEY_P:answer[0] = 1;	answer[1] = 2;	break;
			case Keyboard.KEY_Q:answer[0] = 2;	answer[1] = 2;	break;
			case Keyboard.KEY_R:answer[0] = 3;	answer[1] = 2;	break;
			case Keyboard.KEY_S:answer[0] = 4;	answer[1] = 2;	break;
			case Keyboard.KEY_T:answer[0] = 5;	answer[1] = 2;	break;
			case Keyboard.KEY_U:answer[0] = 6;	answer[1] = 2;	break;
			case Keyboard.KEY_V:answer[0] = 0;	answer[1] = 3;	break;
			case Keyboard.KEY_W:answer[0] = 1;	answer[1] = 3;	break;
			case Keyboard.KEY_X:answer[0] = 2;	answer[1] = 3;	break;
			case Keyboard.KEY_Y:answer[0] = 3;	answer[1] = 3;	break;
			case Keyboard.KEY_Z:answer[0] = 4;	answer[1] = 3;	break;
			case Keyboard.KEY_UP:answer[0] = 5;answer[1] = 3;	break;
			case Keyboard.KEY_DOWN:answer[0] = 6;answer[1] = 3;break;
			case Keyboard.KEY_LEFT:answer[0] = 0;answer[1] = 4;break;
			case Keyboard.KEY_RIGHT:answer[0] = 1;answer[1] = 4;break;
			case Keyboard.KEY_1:answer[0] = 4;answer[1] = 4;break;
			case Keyboard.KEY_2:answer[0] = 5;answer[1] = 4;break;
			case Keyboard.KEY_3:answer[0] = 6;answer[1] = 4;break;
			case Keyboard.KEY_4:answer[0] = 0;answer[1] = 5;break;
			case Keyboard.KEY_5:answer[0] = 1;answer[1] = 5;break;
			case Keyboard.KEY_6:answer[0] = 2;answer[1] = 5;break;
			case Keyboard.KEY_7:answer[0] = 3;answer[1] = 5;break;
			case Keyboard.KEY_8:answer[0] = 4;answer[1] = 5;break;
			case Keyboard.KEY_9:answer[0] = 5;answer[1] = 5;break;
			case Keyboard.KEY_0:answer[0] = 6;answer[1] = 5;break;
			
			}
			return answer;		
	}
	@Override
	public float[] translateView() {
		return translateView;
	}

	@Override
	public boolean getSolid(int x, int y) {
		return false;
	}

	@Override
	public boolean getSolidMoveX(float x, float y) {
		return false;
	}

	@Override
	public boolean getSolidMoveY(float x, float y) {
		return false;
	}

	@Override
	public void speakToLevel(int i) {
		
	}

	@Override
	public void interact() {
		
	}

	@Override
	public boolean giveDamageSquare(Quadrilataire hitBox, Entity attacker, int damage) {
		return false;
	}

	@Override
	public boolean giveHeal(int[] hitBox, Entity attacker, int damage) {
		return false;
	}

	@Override
	public void askForParty(Joueur demandeur) {
		
	}

	@Override
	public void nextLevel() {
		
	}

	@Override
	public Entity spawner(Entity entity) {
		return null;
	}

	@Override
	public void startLevel() {
		reset();
		if(Main.save.avancement==-1)mainI=1;
		
	}

	@Override
	protected Interface getInter() {
		return null;
	}

	@Override
	public void giveXpGold(int xp, int gold) {
		
	}

	@Override
	public void save() {
		
	}

	@Override
	public void loadingConsol() {
		
	}
	@Override
	public void stopMusic() {
		
	}
	
	

}
