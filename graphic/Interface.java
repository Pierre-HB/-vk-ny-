package fr.pierrehb.graphic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.boss.Boss;
import fr.pierrehb.entities.joueurs.Friend;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.game.Game;
import fr.pierrehb.game.Team;
import fr.pierrehb.items.InConsole;
import fr.pierrehb.items.Item;
import fr.pierrehb.items.NoItem;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Commands;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.other.Polices;
import fr.pierrehb.sound.Sound_lector;

public class Interface implements Color, Polices, Dialogue, Commands{
	private static Texture attaques = Texture.loadTexture("res/textures/Human/Player/Attaques.png");
	private static int id = attaques.getid();
	private static float xh;
	private static float[] nb_attaques = {9.0f, 5.0f};
	private static Player player = (Player)Game.getPlayer();
	private static Caractere[] name = Renderer.getText(3, 5, Main.save.name, Main.save.favorite_police, 0.4f);
	private static float nameLenght;
	private static float A1, A2, A3, A4, A5;
	private static Caractere[] error = Renderer.getText(75, 40, str_dead, Main.save.favorite_police, 0.8f);
	private static Caractere[] close = Renderer.getText(112, 88, str_close, Main.save.favorite_police, 0.8f);
	private static Caractere[] askeur = Renderer.getText(110, 49, str_sparadrapName, polices.COMIC_SANS_MS, 0.4f);
	private static int i = 0;
	private static int m = 0;
	private static enum menuChoice {OBJET,EQUIPEMENT,ATTAQUE,SOSCIAL,RETOUR,DECONNEXION,ATTAQUE1,ATTAQUE2,ATTAQUE3,ATTAQUE4,ATTAQUE5,ATTAQUE_PRECEDANT,INVITER_PARTY,QUITTER_PARTY,CREE_PARTY,AMIS,DEMANDER_AMIS,DEFIS,SOSCIAL_RETOUR,LISTE_OBJET,PERSO,DEMANDEPARTY,DEMANDEAMI,DEMANDEDUEL,LISTEAMIS};
	private static boolean askParty = false;
	public static boolean askDuel = false;
	private static boolean inMenu = false;
	private static boolean MENU=false;
	private static boolean UP = false;
	private static boolean DOWN = false;
	private static enum agree {ACCEPTER,REFUSER};
	private static final Caractere[] yes = Renderer.getText(90, 80, str_agree, Main.save.favorite_police, 0.4f);
	private static final Caractere[] dont_agree = Renderer.getText(140, 80, str_dont_agree, Main.save.favorite_police, 0.4f);
	private static final Caractere[] askingParty = Renderer.getText(85, 60, str_asking_party, Main.save.favorite_police, 0.4f);
	private static final Caractere[] askingDuel = Renderer.getText(85, 60, str_asking_duel, Main.save.favorite_police, 0.4f);
	private static final Caractere[] askingDeathDuel = Renderer.getText(85, 60, str_asking_deathDuel, Main.save.favorite_police, 0.4f);
	
	
	private static final Caractere[] objet = Renderer.getText(30, 13, str_objet, Main.save.favorite_police, 0.5f);
	private static final Caractere[] equipement = Renderer.getText(22, 34, str_equipement, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaque = Renderer.getText(26, 55, str_attaques, Main.save.favorite_police, 0.5f);
	private static final Caractere[] soscial = Renderer.getText(30, 76, str_social, Main.save.favorite_police, 0.5f);
	private static final Caractere[] retourJeu = Renderer.getText(30, 97, str_retourAuJeu, Main.save.favorite_police, 0.5f);
	private static final Caractere[] deconnexion = Renderer.getText(20, 118, str_deconnexion, Main.save.favorite_police, 0.5f);

	private static final Caractere[] attaque1 = Renderer.getText(108, 13, str_attaque1, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaque2 = Renderer.getText(107, 34, str_attaque2, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaque3 = Renderer.getText(104, 55, str_attaque3, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaque4 = Renderer.getText(105, 76, str_attaque4, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaque5 = Renderer.getText(108, 97, str_attaque5, Main.save.favorite_police, 0.5f);
	private static final Caractere[] attaquePrecedant = Renderer.getText(108, 118, str_precedant, Main.save.favorite_police, 0.5f);

	private static final Caractere[] creeParty = Renderer.getText(106, 34, str_soscialCreeParty, Main.save.favorite_police, 0.5f);
	private static final Caractere[] inviterParty = Renderer.getText(102, 13, str_soscialInviterParty, Main.save.favorite_police, 0.5f);
	private static final Caractere[] quitterParty = Renderer.getText(100, 34, str_soscialQuitterParty, Main.save.favorite_police, 0.5f);
	private static final Caractere[] amis = Renderer.getText(117, 55, str_soscialAmis, Main.save.favorite_police, 0.5f);
	private static final Caractere[] demanderAmis = Renderer.getText(100, 76, str_soscialDemanderAmi, Main.save.favorite_police, 0.5f);
	private static final Caractere[] defis = Renderer.getText(116, 97, str_soscialDefis, Main.save.favorite_police, 0.5f);
	private static final Caractere[] soscialPrecedant = Renderer.getText(108, 118, str_precedant, Main.save.favorite_police, 0.5f);

	private static final Caractere[]  objetListeBack = Renderer.getText(0, 0, str_precedant, Main.save.favorite_police, 0.5f);
	
	private static final Caractere[]  goldPoint = Renderer.getText(110, 55, str_gold, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  lvlPoint = Renderer.getText(110, 65, str_lvl, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  attaquePoint = Renderer.getText(110, 85, str_attaque, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  healPoint = Renderer.getText(110, 45, str_heal, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  xpPoint = Renderer.getText(110, 75, str_xp, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  defensePoint = Renderer.getText(110, 95, str_defense, Main.save.favorite_police, 0.5f);

	
	private static final Caractere[]  armeTxt = Renderer.getText(0, 0, str_arme, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  armureTxt = Renderer.getText(0, 0, str_armure, Main.save.favorite_police, 0.5f);

	private static final Caractere[]  G = Renderer.getText(200, 10, str_G, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  DDD = Renderer.getText(200, 10, str_DDD, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  DGD = Renderer.getText(200, 10, str_DGD, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  DDG = Renderer.getText(200, 10, str_DDG, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  DGG = Renderer.getText(200, 10, str_DGG, Main.save.favorite_police, 0.5f);
	private static final Caractere[][] mouseControl = new Caractere[5][];
	
	private static final Caractere[] confirmerInviteParty = Renderer.getText(52, 35, str_confirmerInviteParty, Main.save.favorite_police, 0.5f);
	private static final Caractere[] confirmerdemandeAmi = Renderer.getText(52, 35, str_confirmerDemandeAmi, Main.save.favorite_police, 0.5f);
	private static final Caractere[] oui = Renderer.getText(135, 90, str_interfaceOui, Main.save.favorite_police, 0.6f);
	private static final Caractere[] non = Renderer.getText(50, 90, str_interfaceNon, Main.save.favorite_police, 0.6f);

	private static final Caractere[] amisPrecedant = Renderer.getText(130, 90, str_precedant, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  amisLlvlPoint = Renderer.getText(105, 35, str_lvl, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  amisAttaquePoint = Renderer.getText(105, 55, str_attaque, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  amisHealPoint = Renderer.getText(105, 45, str_heal, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  amisDefensePoint = Renderer.getText(105, 65, str_defense, Main.save.favorite_police, 0.5f);
	
	private static final Caractere[]  loseDuel = Renderer.getText(20, 40, str_lose_duel, Main.save.favorite_police, 1.5f);
	private static final Caractere[]  winDuel = Renderer.getText(20, 40, str_win_duel, Main.save.favorite_police, 1.5f);
	private static final Caractere[]  startDuel = Renderer.getText(20, 40, str_start_duel, Main.save.favorite_police, 1.5f);
	public static int endDuelDelay=0;
	private static boolean win=false;
	private static float[] endDuelColor = {1f,1f,1f,1f};
	private static final Caractere[] levelUp = Renderer.getText(20, 40, str_levelUp, Main.save.favorite_police, 1.2f);
	private static boolean lvlUp = false;
	
	private static final Caractere[]  defier = Renderer.getText(70, 25, str_confimrDuel, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  defieSimple = Renderer.getText(85, 60, str_askDuel, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  defieMortel = Renderer.getText(85, 85, str_askMortalDuel, Main.save.favorite_police, 0.5f);
	private static final Caractere[]  defiePrecedant = Renderer.getText(85, 110, str_duelPrecedant, Main.save.favorite_police, 0.5f);
	private static Caractere[] adverse;
	
	
	private static final Caractere[]  yesLeave = Renderer.getText(140, 80, str_yesLeave, Main.save.favorite_police, 0.4f);
	private static final Caractere[]  noLeave = Renderer.getText(90, 80, str_noLeave, Main.save.favorite_police, 0.4f);
	private static final Caractere[]  leave = Renderer.getText(100, 52, str_leave, Main.save.favorite_police, 0.4f);


	
	private static Caractere[] healNum;
	private static Caractere[] goldNum;
	private static Caractere[] attaqueNum;
	private static Caractere[] lvlNum;
	private static Caractere[] defenseNum;
	private static Caractere[] xpNum;
	private static Caractere[] joueurName;
	
	private static Caractere[] friendHeal;
	private static Caractere[] friendLvl;
	private static Caractere[] friendDamage;
	private static Caractere[] friendArmor;
	
	private static boolean ENTER = false;
	private static boolean LEFT = false;
	private static boolean RIGHT = false;
	private static float[] helths = new float[6];// si probleme, passer de 6 a 5 joueur max... j'en ai rajouter un pour pouvoir mettre touts les joueur enssemble d'un coup... pas forcement une bonne idée
	private static float[] helthsAdverse = new float[6];
	
	private static Liste inventory;
	private static Liste joueurs;
	private static Liste friendListe;
	private static Friend[] friends;
	private static Joueur[] pnjJoueurs;
	private static Item[] inventaire;
	private static boolean obj=false;
	private static boolean readObj=false;
	private static int objreading =0;
	private static int objI=0;
	private static int persoI=0;
	private static int joueurI=0;
	private static final float[] oneTile = {1f,1f};
	private float[] nb_position = {5.0f, 8.0f};
	private boolean inStuff=false;
	private boolean demanderJoueur=false;
	private int selectedJoueur = 0;
	private boolean confirmeSelectedJoueur=false;
	private boolean readFriend=false;
	private int friendReading=0;
	private int duelI=0;
	private boolean confirmeDuel=false;
	private boolean askToLeave=false;
	public static boolean INCONSOLE = false;
	private static int bossCunt = 0;
	private static boolean fightingBoss = false;
	private static Caractere[] bossName;
	private static Caractere[] lifeBossName;
	private static Caractere[] bossDescription;
	private static Boss boss;
	private static boolean bossFight = false;
	private static int lenghtBossName = 0;
	private static float[] bossHealth;
	private static Sound_lector sound;
	
	static private Giver[] givers = {new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false),new Giver("",0,0,false)};
	private Sound_lector song;
	
	public Interface() {
		A1 = 0.2f;
		A2 = 0.4f;
		A3 = 0.6f;
		A4 = 0.8f;
		A5 = 1.0f;
		mouseControl[0]=G;
		mouseControl[1]=DDD;
		mouseControl[2]=DDG;
		mouseControl[3]=DGD;
		mouseControl[4]=DGG;
		nameLenght = 0.0f;
		for(Caractere chara : name) nameLenght = (chara.getCoord()[0]+chara.getOffset())*chara.getTaille();
	}
	public void render() {
		
		if(Main.save.avancement>1) {
		// on affiche le nom
		Renderer.renderInterface(1.5f, 2.5f, nameLenght+4.5f, 12.5f, WHITE);
		Renderer.renderInterface(2, 3, nameLenght+4, 12, BLACK);
		Renderer.renderInterfaceTexte(name);
		}
		// on affiche les membre de la party:
		for(int a = 0; a < Team.playerteam.size(); a++) {
			Renderer.renderInterface(1.5f, 22+13*a+2.5f, Team.playerteam.get(a).partyNameLenght+4.5f, 21+13*a+9.5f, WHITE);
			Renderer.renderInterface(2, 22+13*a+3, Team.playerteam.get(a).partyNameLenght+4, 21+13*a+9, BLACK);
			Renderer.renderTextInterface00(Team.playerteam.get(a).partyName, 3, 26+13*a);
			// le vie:
			
			Renderer.renderInterface(2.5f, 31.5f+13*a, 41.5f, 35.5f+13*a, WHITE);
			Renderer.renderInterface(2.8f, 31.8f+13*a, 41.2f, 35.2f+13*a, BLACK);
			Renderer.renderInterface(3.25f, 32.25f+13*a, 40.75f, 34.75f+13*a, GREY);
			Renderer.renderInterface(3.25f, 32.25f+13*a, helths[a], 34.75f+13*a, getHealthColor((float)Team.playerteam.get(a).HP/(float)(Team.playerteam.get(a).getMaxHP())));
		}
		
		if(Team.isDuel) {
		for(int a = 0; a < Team.playeradverse.size(); a++) {
			Renderer.renderInterface(213.5f, 5+13*a+2.5f, 256-Team.playeradverse.get(a).partyNameLenght-3.5f, 4+13*a+9.5f, WHITE);
			Renderer.renderInterface(214, 5+13*a+3, 256-Team.playeradverse.get(a).partyNameLenght-4, 4+13*a+9, BLACK);
			Renderer.renderTextInterface00(Team.playeradverse.get(a).partyName, 215, 9+13*a);
			// le vie:
			
			Renderer.renderInterface(214.5f, 14.5f+13*a, 253.5f, 18.5f+13*a, WHITE);
			Renderer.renderInterface(214.8f, 14.8f+13*a, 253.2f, 18.2f+13*a, BLACK);
			Renderer.renderInterface(215.25f, 15.25f+13*a, 252.75f, 17.75f+13*a, GREY);
			Renderer.renderInterface(helthsAdverse[a], 15.25f+13*a, 252.75f , 17.75f+13*a, getHealthColor((float)Team.playeradverse.get(a).HP/(float)(Team.playeradverse.get(a).getMaxHP())));
		}}
		if(bossFight) {
			
			Renderer.renderInterface(213.5f, 5+2.5f, 213.5f+lenghtBossName+3.5f, 4+11.5f, WHITE);
			Renderer.renderInterface(214, 5+3, 214+lenghtBossName+2.5f, 4+11, BLACK);
			Renderer.renderTextInterface00(lifeBossName, 215, 9);
			
			for(int a = 0; a<boss.healthBar;a++) {
				Renderer.renderInterface(214.5f, 17.5f+7*a, 253.5f, 21.5f+7*a, WHITE);
				Renderer.renderInterface(214.8f, 17.8f+7*a, 253.2f, 21.2f+7*a, BLACK);
				Renderer.renderInterface(215.25f, 18.25f+7*a, 252.75f, 20.75f+7*a, GREY);
				Renderer.renderInterface(215.25f, 18.25f+7*a, bossHealth[a] , 20.75f+7*a, getHealthColor((float)boss.HP/(float)(boss.getMaxHP())));

			}
			
		}
		
		if(Main.save.avancement>1) {
		// on affiche la barre de vie
		Renderer.renderInterface(2.5f, 14.5f, 50.5f, 19.5f, WHITE);
		Renderer.renderInterface(2.8f, 14.8f, 50.2f, 19.2f, BLACK);
		Renderer.renderInterface(3.25f, 15.25f, 49.75f, 18.75f, GREY);
		Renderer.renderInterface(3.25f, 15.25f, xh, 18.75f, getHealthColor((float)player.HP/(float)(player.getMaxHP())));
		}
		if(Main.save.playerAttacks[0][0] !=0 && !Game.level.haveTarget) {
			
			Renderer.renderInterface(119, 123, 137, 141, BLACK);
			Renderer.renderInterface(95, 123, 113, 141, BLACK);
			Renderer.renderInterface(71, 123, 89, 141, BLACK);
			Renderer.renderInterface(143, 123, 161, 141, BLACK);
			Renderer.renderInterface(167, 123, 185, 141, BLACK);	
			
		Renderer.renderInterface(119.5f, 123.5f, 136.5f, 140.5f, getAttackColor(A3));
		Renderer.renderInterface(95.5f, 123.5f, 112.5f, 140.5f, getAttackColor(A2));
		Renderer.renderInterface(71.5f, 123.5f, 88.5f, 140.5f, getAttackColor(A1));
		Renderer.renderInterface(143.5f, 123.5f, 160.5f, 140.5f, getAttackColor(A4));
		Renderer.renderInterface(167.5f, 123.5f, 184.5f, 140.5f, getAttackColor(A5));
			
		Renderer.renderInterfaceTexture(72, 124, 88, 140, player.attacks[0].xo, player.attacks[0].yo, nb_attaques, id, GREY);
		Renderer.renderInterfaceTexture(96, 124, 112, 140, player.attacks[1].xo, player.attacks[1].yo, nb_attaques, id, GREY);
		Renderer.renderInterfaceTexture(120, 124, 136, 140, player.attacks[2].xo, player.attacks[2].yo, nb_attaques, id, GREY);
		Renderer.renderInterfaceTexture(144, 124, 160, 140, player.attacks[3].xo, player.attacks[3].yo, nb_attaques, id, GREY);
		Renderer.renderInterfaceTexture(168, 124, 184, 140, player.attacks[4].xo, player.attacks[4].yo, nb_attaques, id, GREY);
		
		Renderer.renderInterfaceTexture(72, 124, 88, 124+(16*A1), player.attacks[0].xo, player.attacks[0].yo, player.attacks[0].xo+1, player.attacks[0].yo+A1, nb_attaques, id, WHITE);
		Renderer.renderInterfaceTexture(96, 124, 112, 124+(16*A2), player.attacks[1].xo, player.attacks[1].yo, player.attacks[1].xo+1, player.attacks[1].yo+A2, nb_attaques, id, WHITE);
		Renderer.renderInterfaceTexture(120, 124, 136, 124+(16*A3), player.attacks[2].xo, player.attacks[2].yo, player.attacks[2].xo+1, player.attacks[2].yo+A3, nb_attaques, id, WHITE);
		Renderer.renderInterfaceTexture(144, 124, 160, 124+(16*A4), player.attacks[3].xo, player.attacks[3].yo, player.attacks[3].xo+1, player.attacks[3].yo+A4, nb_attaques, id, WHITE);
		Renderer.renderInterfaceTexture(168, 124, 184, 124+(16*A5), player.attacks[4].xo, player.attacks[4].yo, player.attacks[4].xo+1, player.attacks[4].yo+A5, nb_attaques, id, WHITE);
		}
		for (Giver giver : givers) giver.render();
		if(!Game.getPlayer().life) {
			Renderer.renderInterface(0.0f, 0.0f, 256.0f, 144.0f, LIGHTGREY);
			Renderer.renderInterface(65.0f, 30.0f, 191.0f, 115.0f, WHITE);
			Renderer.renderInterface(70.0f, 35.0f, 186.0f, 109.0f, BLACK);
			Renderer.renderInterfaceTexte(error);
			Renderer.renderInterface(105.0f, 83.0f, 151.0f, 103.0f, GREEN);
			Renderer.renderInterface(108.0f, 86.0f, 148.0f, 100.0f, BLACK);
			Renderer.renderInterfaceTexte(close);



		}
		//Si le joueur est dans l'inventaire
		if (inMenu) {
			Renderer.renderInterface(0.0f, 0.0f, 256.0f, 144.0f, LIGHTGREY);

			renderChoice(15.0f, 10.0f, 75.0f, 25.0f, objet, menuChoice.OBJET);
			renderChoice(15.0f, 31.0f, 75.0f, 46.0f, equipement, menuChoice.EQUIPEMENT);
			renderChoice(15.0f, 52.0f, 75.0f, 67.0f, attaque, menuChoice.ATTAQUE);
			renderChoice(15.0f, 73.0f, 75.0f, 88.0f, soscial, menuChoice.SOSCIAL);
			renderChoice(15.0f, 94.0f, 75.0f, 109.0f, retourJeu, menuChoice.RETOUR);
			renderChoice(15.0f, 115.0f, 75.0f, 130.0f, deconnexion, menuChoice.DECONNEXION);
			
			//Attaque:
			if ( m >= 6 && m <= 11 ) {
		
			renderChoice(98.0f, 10.0f, 158.0f, 25.0f, attaque1, menuChoice.ATTAQUE1);
			renderChoice(98.0f, 31.0f, 158.0f, 46.0f, attaque2, menuChoice.ATTAQUE2);
			renderChoice(98.0f, 52.0f, 158.0f, 67.0f, attaque3, menuChoice.ATTAQUE3);
			renderChoice(98.0f, 73.0f, 158.0f, 88.0f, attaque4, menuChoice.ATTAQUE4);
			renderChoice(98.0f, 94.0f, 158.0f, 109.0f, attaque5, menuChoice.ATTAQUE5);
			renderChoice(98.0f, 115.0f, 158.0f, 130.0f, attaquePrecedant, menuChoice.ATTAQUE_PRECEDANT);
			
			if(m!=11) {
				Renderer.renderInterface(199, 25, 221, 47, GOLD);
				Renderer.renderInterface(199, 49, 221, 71, GOLD);
				Renderer.renderInterface(199, 73, 221, 95, GOLD);
				Renderer.renderInterface(199, 97, 221, 119, GOLD);
				Renderer.renderInterface(199, 121, 221, 143, GOLD);
				
				Renderer.renderInterface(195, 7, 225, 20, WHITE);
				Renderer.renderInterface(196, 8, 224, 19, BLACK);
				Renderer.renderInterfaceTexte(mouseControl[m-6]);
				
				Renderer.renderInterfaceTexture(200, 26, 220, 46, player.attacks[m-6].getXo(0), player.attacks[m-6].getYo(0), nb_attaques, id, WHITE);
				Renderer.renderInterfaceTexture(200, 50, 220, 70, player.attacks[m-6].getXo(1), player.attacks[m-6].getYo(1), nb_attaques, id, WHITE);
				Renderer.renderInterfaceTexture(200, 74, 220, 94, player.attacks[m-6].getXo(2), player.attacks[m-6].getYo(2), nb_attaques, id, WHITE);
				Renderer.renderInterfaceTexture(200, 98, 220, 118, player.attacks[m-6].getXo(3), player.attacks[m-6].getYo(3), nb_attaques, id, WHITE);
				Renderer.renderInterfaceTexture(200, 122, 220, 142, player.attacks[m-6].getXo(4), player.attacks[m-6].getYo(4), nb_attaques, id, WHITE);

			}
		}
			if(confirmeSelectedJoueur) {
				renderPlayerSelection(pnjJoueurs[selectedJoueur].getListeName());
			}
			if(readFriend) {
				renderFriend();
			}
			if(confirmeDuel){
				renderDuel();
			}
			if((( m>=12 && m<=18)||m>=21) && !confirmeSelectedJoueur && !readFriend && !confirmeDuel) {
			if(!player.haveParty) {

				renderChoice(98.0f, 31.0f, 158.0f, 46.0f, creeParty, menuChoice.CREE_PARTY);

			}
			if (player.haveParty) {
				
				renderChoice(98.0f, 10.0f, 158.0f, 25.0f, inviterParty, menuChoice.INVITER_PARTY);
				renderChoice(98.0f, 31.0f, 158.0f, 46.0f, quitterParty, menuChoice.QUITTER_PARTY);

			}
			//Soscial
			
					
			renderChoice(98.0f, 52.0f, 158.0f, 67.0f, amis, menuChoice.AMIS);
			renderChoice(98.0f, 73.0f, 158.0f, 88.0f, demanderAmis, menuChoice.DEMANDER_AMIS);
			renderChoice(98.0f, 94.0f, 158.0f, 109.0f, defis, menuChoice.DEFIS);
			renderChoice(98.0f, 115.0f, 158.0f, 130.0f, soscialPrecedant, menuChoice.SOSCIAL_RETOUR);

			if(demanderJoueur) {				
				 joueurs.render();
			}
			
			
			}
			if(obj) {
				inventory.render();
				if(readObj) {
					renderItem(inventaire[objreading]);
					}
			}
			if(inStuff) {				
				if(readObj) {
					if(persoI==1)renderItem2Choice(player.weapon);
					else renderItem2Choice(player.armor);
				}else {
				renderPlayer(player);
				if(player.weapon.getID()==0) Renderer.renderInterfaceTexte(armeTxt,WHITE,107,118);
				else Renderer.renderInterfaceTexte(player.weapon.getListeName(),WHITE,107,118);
					
				if(player.armor.getID()==0) Renderer.renderInterfaceTexte(armureTxt,WHITE,160,118);
				else Renderer.renderInterfaceTexte(player.armor.getListeName(),WHITE,160,118);
				
				Renderer.renderInterfaceTexte(objetListeBack,WHITE,55,118);
			}}
			if(m==24 && !readFriend) {
				friendListe.render();
			}
			

			
		}
		// si demande de parti:
		if(askParty||askDuel||askToLeave) {
		Renderer.renderInterface(74.0f, 44.0f, 179.0f, 70.0f, BLACK);
		Renderer.renderInterface(75.0f, 45.0f, 178.0f, 69.0f, BROWN);

		
		Renderer.renderInterface(74.0f, 44.0f, 78.0f, 100.0f, BLACK);
		Renderer.renderInterface(74.0f, 44.0f, 180.0f, 48.0f, BLACK);
		Renderer.renderInterface(176.0f, 44.0f, 180.0f, 100.0f, BLACK);
		Renderer.renderInterface(74.0f, 96.0f, 180.0f, 100.0f, BLACK);
		Renderer.renderInterface(75.0f, 45.0f, 77.0f, 99.0f, BROWN);
		Renderer.renderInterface(75.0f, 45.0f, 179.0f, 47.0f, BROWN);
		Renderer.renderInterface(177.0f, 45.0f, 179.0f, 99.0f, BROWN);
		Renderer.renderInterface(75.0f, 97.0f, 179.0f, 99.0f, BROWN);
		
		
		if(i == 0) {
			Renderer.renderInterface(87.0f, 77.0f, 122.0f, 90.0f, BLACK);
			Renderer.renderInterface(137.0f, 78.0f, 172.0f, 89.0f, BLACK);

		}else if(i == 1) {
			Renderer.renderInterface(88.0f, 78.0f, 121.0f, 89.0f, BLACK);
			Renderer.renderInterface(136.0f, 77.0f, 173.0f, 90.0f, BLACK);


		}else {
			Renderer.renderInterface(88.0f, 78.0f, 121.0f, 89.0f, BLACK);
			Renderer.renderInterface(137.0f, 78.0f, 172.0f, 89.0f, BLACK);

		}
		Renderer.renderInterface(89.0f, 79.0f, 120.0f, 88.0f, getPartyColor(agree.ACCEPTER));
		
		Renderer.renderInterface(138.0f, 79.0f, 171.0f, 88.0f, getPartyColor(agree.REFUSER));

		
		if(askParty) {
		Renderer.renderInterfaceTexte(askeur);
		Renderer.renderInterfaceTexte(askingParty);
		Renderer.renderInterfaceTexte(dont_agree);
		Renderer.renderInterfaceTexte(yes);

		}
		else if(askDuel) {
			Renderer.renderInterfaceTexte(askeur);
			if(Team.duelToDeath)Renderer.renderInterfaceTexte(askingDeathDuel);
			else Renderer.renderInterfaceTexte(askingDuel);
			Renderer.renderInterfaceTexte(dont_agree);
			Renderer.renderInterfaceTexte(yes);
			
		}
		else if(askToLeave) {
			
			Renderer.renderInterfaceTexte(yesLeave);
			Renderer.renderInterfaceTexte(noLeave);
			Renderer.renderInterfaceTexte(leave);
		}
		}
	
		
		if(endDuelDelay!=0) {
			if(Team.isDuel) {
				Renderer.renderInterfaceTexte(startDuel,endDuelColor,0,0);
			}else if(!lvlUp) {
			if(win)Renderer.renderInterfaceTexte(winDuel,endDuelColor,0,0);
			else Renderer.renderInterfaceTexte(loseDuel,endDuelColor,0,0);
			} else Renderer.renderInterfaceTexte(levelUp,endDuelColor,0,0);
				
			
			
		}
		if(fightingBoss) {
			Renderer.renderInterfaceTexte(bossName);
			Renderer.renderInterfaceTexte(bossDescription);
		}
	}
	private void renderChoice(float x1,float y1,float x2,float y2,Caractere[] text,menuChoice type) {
		if (m==choice(type)) {
			Renderer.renderInterface(x1-1, y1-1, x2+1, y2+1, BLACK);
			Renderer.renderInterface(x1+1, y1+1, x2-1, y2-1, DARKBROWN);
			Renderer.renderInterfaceTexte(text);
		}else {
			Renderer.renderInterface(x1, y1, x2, y2, BLACK);
			Renderer.renderInterface(x1+1, y1+1, x2-1, y2-1, BROWN);
			Renderer.renderInterfaceTexte(text);
			
		}
		
	}
	private float[] getAttackColor(float xo) {
		if (xo == 1)return GOLD;
		return DARKGOLD;
	}
	public void update() {
		if(INCONSOLE)InConsole.update();
		player = Game.getPlayer();
		xh = ((float)player.HP/(float)(player.getMaxHP()))*(49.75f-3.25f)+3.25f;
		for(int b = 0; b < Team.playerteam.size(); b++) helths[b] = ((float)Team.playerteam.get(b).HP/(float)(Team.playerteam.get(b).maxHP+Team.playerteam.get(b).getHealthBonus()))*(40.75f-3.25f)+3.25f;		
		for(int b = 0; b < Team.playeradverse.size(); b++) helthsAdverse[b] = 256f-(((float)Team.playeradverse.get(b).HP/(float)(Team.playeradverse.get(b).getMaxHP()))*(40.75f-3.25f)+3.25f);		
		if(bossFight) {
			if(boss.HP<=0 || player.HP<=0)bossFight=false;
			float HPperBar = (float)boss.maxHP/boss.healthBar;
			float bossLife = (float)boss.HP/(float)boss.maxHP;
			for(int i = 0; i< boss.healthBar;i++) {
				if(bossLife>(i+1)/(float)boss.healthBar)bossHealth[i]=252.75f;
				else if(bossLife<(i)/(float)boss.healthBar)bossHealth[i]=215.25f;
				else bossHealth[i]=((boss.HP-HPperBar*i)/HPperBar)*37.5f+215.25f;
			}
		}
		A1 = player.attacks[0].charge;
		A2 = player.attacks[1].charge;
		A3 = player.attacks[2].charge;
		A4 = player.attacks[3].charge;
		A5 = player.attacks[4].charge;
		if(!Game.getPlayer().life) {
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {// ==============================================Le respawn apres la mort se declanche ici!!
				Game.restartLevel();
			}
		}
		
		for (Giver giver : givers) giver.update();
		if(askParty) party();
		if(askDuel) duel();
		else if(inMenu && !askToLeave) menu();
		if(obj)inventory.update();
		if(demanderJoueur)joueurs.update();
		if(m==24) friendListe.update();
		if(askToLeave)deconexion();
		
		if(endDuelDelay!=0) {
			endDuelDelay++;
			endDuelColor[3]=1f-(endDuelDelay/300f);
		}
		if(endDuelDelay>300) {
			lvlUp=false;
			endDuelDelay=0;
		}
		if(fightingBoss) {
			bossCunt++;
			if(bossCunt>=300) {
				bossCunt=0;
				fightingBoss=false;
			}
		}
		
		
	}
	public void startFinghtingBoss(String bossNom, String bossToString, Boss boss_) {
		fightingBoss=true;
		bossName = Renderer.getText(20, 30, bossNom, Main.save.favorite_police, 1.7f);
		bossDescription = Renderer.getText(40, 80, bossToString, Main.save.favorite_police, 0.8f);
		lifeBossName=Renderer.getText(0, 0, bossNom, Main.save.favorite_police, 0.3f);
		boss = boss_;
		bossFight=true;
		lenghtBossName = (int)((bossName[bossName.length-1].coord[0]+bossName[bossName.length-1].offset)*0.3);
		bossHealth=new float[boss.healthBar];
	}

	private void party() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || ( leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
			if(i!=0) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
				}
				i=0;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || ( rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
			if(i!=1) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
				}
				i=1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER) {
				song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
				song.start();
			switch(i) {
			case 0:
				askParty = false;
				Team.answerForParty(true);
				Game.getPlayer().haveParty = true;
				if (!inMenu) {
					Game.getPlayer().cantMove = false;
					Game.getPlayer().canAct = true;
				}
				break;
			case 1:
				askParty = false;
				Team.answerForParty(false);
				if (!inMenu) {
					Game.getPlayer().cantMove = false;
					Game.getPlayer().canAct = true;
				}
				i=0;
				break;
			default:
				break;
			}}
		}else ENTER = false;
	}
	private void duel() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || ( leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
			if(i!=0) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
				}
				i=0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || ( rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
			if(i!=1) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
				}
				i=1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER) {
				song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
				song.start();
			switch(i) {
			case 0:
				askDuel = false;
				Team.answerForDuel(true);
				if (!inMenu) {
					Game.getPlayer().cantMove = false;
					Game.getPlayer().canAct = true;
				}
				break;
			case 1:
				askDuel = false;
				Team.answerForDuel(false);
				if (!inMenu) {
					Game.getPlayer().cantMove = false;
					Game.getPlayer().canAct = true;
				}
				i=0;
				break;
			default:
				break;
			}}
		}else ENTER = false;
	}
	private void deconexion() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || ( leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
			if(i!=0) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
			}
			i = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || ( rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
			if(i!=1) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
			}
			i = 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER) {
				song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
				song.start();
			if(i==0)askToLeave=false;
			else {
				close();
				Game.level.save();
				Game.level.stopMusic();
				Main.game.restart();
				return;
			}		
			}ENTER=true;
		}else {
			ENTER=false;
		}
	}
	private void menu() {
		if((inventairekey != -1 && Keyboard.isKeyDown(inventairekey)) || Mouse.isButtonDown(inventairemouse)) {
			if (!MENU) {
				song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
				song.start();
				close();
				
			}
			MENU=true;
		}else MENU=false;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)||(upkey != -1 && Keyboard.isKeyDown(upkey)) || Mouse.isButtonDown(upmouse)) {
			if (!UP && !readObj && !askToLeave && !inStuff) {
				if(confirmeDuel)decreaseDuel();
				else decreaseMenu();
			}
			UP = true;
		}else UP = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)||(downkey != -1 && Keyboard.isKeyDown(downkey)) || Mouse.isButtonDown(downmouse)) {
			if (!DOWN && !readObj && !askToLeave&& !inStuff) {
				if(confirmeDuel)increaseDuel();
				else increaseMenu();
			}
			DOWN = true;
		}else DOWN = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(!ENTER) {
				song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
				song.start();
				if(inStuff) {
					if(readObj)selectePersoObj();
					else selectPerso();
				}
				else if(readObj)selecteObj();
				else if (confirmeSelectedJoueur)selectSelectedJoueur();
				else if(readFriend)readFriend=false;
				else if(confirmeDuel)selectDuel();
				else selectMenu();
			}
			ENTER=true;		
		}else ENTER = false;
		if(readObj||inStuff||confirmeSelectedJoueur) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)||(leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
				if (!LEFT) {
					if(inStuff) {
						if(readObj)objI=0;
						else decreasePerso();
						song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
						song.start();
					}
					else if(readObj)decreaseObj();
					else if (confirmeSelectedJoueur)decreaseSelecetdJoueur();
					
				}
				LEFT = true;
			}else LEFT = false;
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)||(rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
				if (!RIGHT) {
					if(inStuff) {
						if(readObj)objI=1;
						else increasePerso();
						song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
						song.start();
					}
					else if(readObj)increaseObj();
					else if(confirmeDuel)increaseDuel();
					else if (confirmeSelectedJoueur) {
						increaseSelecetdJoueur();
					}
				}
				RIGHT = true;
			}else RIGHT = false;
		}
		if(askDuel)Team.update();
			
		
	}
	private void increaseMenu() {
		switch(m) {
		case 5:m=0;break;
		case 11:m=6;break;
		case 12:m=15;break;
		case 18:
			if(player.haveParty)m=13;
			else m=12;
			break;
		case 19:inventory.next();break;
		
		case 21:
		case 23:
		case 22:joueurs.next();break;
		
		case 24:friendListe.next();break;
		default:
			m++;
			break;
		}
		song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
		song.start();
	}
	private void decreaseMenu() {
		switch(m) {
		case 0:m=5;break;
		case 6:m=11;break;
		case 12:m=18;break;
		case 13:m=18;break;
		case 15:
			if(player.haveParty) m--;
			else m=12;
			break;
		case 19:inventory.previous();break;
		case 21:
		case 23:
		case 22:joueurs.previous();break;
		case 24:friendListe.previous();break;
		default:
			m--;
			break;
				
		}
		song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
		song.start();
		
		
		
	}
	private void selectMenu() {
		boolean lunchSound = true;
		switch(m) {
		case 0:
			obj=true;
			inventaire=player.getInventaire();
			inventory = new Liste(createListeInventaire(inventaire),180f,65f);m=19;break;
		case 1:inStuff=true;iniPlayerStat(player);break;
		case 2:m=6;break;
		case 3:
			if (player.haveParty)m=13;
			else m = 12;
			break;
		case 4:
			m=0;
			closeMenu();
			break;
		case 5:askToLeave=true;break;
		case 11:m=2;break;
		case 12:Team.createTeam(player);m=13;break;
		case 15:friendListe= new Liste(createFriendListe(),180f,65f);m=24;break;
		case 13:
		case 16:
		case 17:
			joueurs=new Liste(startListeJoueur(),180f,65f);
			demanderJoueur=true;
			if(m==13)m=21;
			else if(m==16)m = 22;
			else m=23;
			break;
		case 14:Team.leaveTeam(player);m=12;break;
		case 18:m=3;break;
		case 19:
			objreading = inventory.select();
			if(objreading==inventory.length-1) {
				m=0;
				obj=false;
				break;
			}
			readObj=true;
			break;
		case 20:break;
		case 21:
		case 22:
		case 23:
			selectedJoueur=joueurs.select();
			if(selectedJoueur==joueurs.length-1) {
				if(m==21)m=13;
				else if(m==22) m = 16;
				else m = 17;
				demanderJoueur=false;
				break;
			}else {
				if(m==23) {
					confirmeDuel=true;
					adverse=Renderer.getText(105, 35, pnjJoueurs[selectedJoueur].getName(), Main.save.favorite_police, 0.6f);


				}
				else confirmeSelectedJoueur=true;
				
				break;
			}
		case 24:
			
			friendReading=friendListe.select();
			if(friendReading==friendListe.length-1) {
				m=15;
			}else if(friends[friendReading].inLife()) {
				readFriend=true;
				iniFriendStat();
			}
			break;
		default:
			lunchSound=false;
		}
		if(lunchSound) {
			song = new Sound_lector("res/sound/sou/menu/lunch.wav", Main.save.soundVolume);
			song.start();
		}
		
		
	}
	private Cadre[] createListeInventaire(Listable[] items) {
		int nbItem = 0;
		for(int a = 0; a<items.length;a++) {
			if(items[a].getClass()!=NoItem.class) {
				nbItem++;
			}else break;
		}
		Cadre[] liste = new Cadre[+1+nbItem];
		for (int a = 0; a<nbItem;a++) {
				liste[a] = new Cadre(60,15,items[a].getListeName(),BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);
			
		}
		liste[nbItem] = new Cadre(60,15,objetListeBack,BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);

		return liste;
	}
	private Cadre[]  createFriendListe() {
		int nbFriend=0;
		for(int friend : Main.save.friends) {
			if(friend!=0)nbFriend++;
		}
		Cadre[] cadres = new Cadre[nbFriend+1];
		Friend[] amis = new Friend[nbFriend];
		int a =0;
		for(int friend : Main.save.friends) {
			if(friend!=0) {
				amis[a]=Game.getFriend(friend);
				if(amis[a].inLife())cadres[a]=new Cadre(60,15,amis[a].getListeName(),BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);
				else cadres[a]=new Cadre(60,15,amis[a].getListeName(),BLACK,BLACK,GREY,GREY,WHITE,WHITE,true);

				a++;
			}
		}
		cadres[nbFriend] = new Cadre(60,15,objetListeBack,BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);
		friends=amis;
		return cadres;
	}
	private Cadre[] startListeJoueur() {
		int nbJoueurs=0;
		for (Human homme : Game.level.humans) {
			if(homme.getClass().getSuperclass().getName()==Joueur.class.getName() && homme.life) {
				if(((m!=13 && m!=17)|| homme.team!=player.team)&& (m!=16 || Main.save.friends[((Joueur)homme).getFriend().getSignature()]==0))nbJoueurs++;
			}
		}
		Joueur[] joueurs = new Joueur[nbJoueurs];
		int a = 0;
		for (Human man : Game.level.humans) {
			if(man.getClass().getSuperclass().getName()==Joueur.class.getName() && man.life) {
				if(((m!=13 && m!=17)|| man.team!=player.team)&& (m!=16 || Main.save.friends[((Joueur)man).getFriend().getSignature()]==0)) {
					joueurs[a]=(Joueur)man;
					System.out.println(m);
					a++;
				}
				
			}
		}
		pnjJoueurs = joueurs;
		return createListeJoueur(pnjJoueurs);
	}
	private Cadre[] createListeJoueur(Listable[] joueurs) {
		Cadre[] pnj = new Cadre[joueurs.length+1];
		for (int a = 0; a<joueurs.length;a++) {
			pnj[a] = new Cadre(60,15,joueurs[a].getListeName(),BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);
		}
		pnj[joueurs.length] = new Cadre(60,15,objetListeBack,BLACK,BLACK,BROWN,DARKBROWN,WHITE,WHITE,true);

		return pnj;
	}
	private int choice(menuChoice choice) {
		switch (choice) {
		case AMIS:return 15;
		case ATTAQUE:return 2;
		case ATTAQUE1:return 6;
		case ATTAQUE2:return 7;
		case ATTAQUE3:return 8;
		case ATTAQUE4:return 9;
		case ATTAQUE5:return 10;
		case ATTAQUE_PRECEDANT:return 11;
		case CREE_PARTY:return 12;
		case DECONNEXION:return 5;
		case DEFIS:return 17;
		case DEMANDER_AMIS:return 16;
		case EQUIPEMENT:return 1;
		case INVITER_PARTY:return 13;
		case OBJET:return 0;
		case QUITTER_PARTY:return 14;
		case RETOUR:return 4;
		case SOSCIAL:return 3;
		case SOSCIAL_RETOUR:return 18;
		case LISTE_OBJET:return 19;
		case PERSO:return 20;
		case DEMANDEPARTY:return 21;
		case DEMANDEAMI:return 22;
		case DEMANDEDUEL:return 23;
		case LISTEAMIS:return 24;
		default:return -1;		
		}
	}
	public static void startAskParty(Joueur Demandeur) {
		if(Game.getPlayer().team != Demandeur.team) {
		askeur = Renderer.getText(110, 49, Demandeur.getName(), Demandeur.favorite_police, 0.4f);
		askParty = true;
		ENTER = true;
		Game.getPlayer().cantMove = true;
		Game.getPlayer().canAct = false;

		}

	}
	public static void startAskDuel(Joueur Demandeur) {
		if(Game.getPlayer().team != Demandeur.team) {
		askeur = Renderer.getText(110, 49, Demandeur.getName(), Demandeur.favorite_police, 0.4f);
		askDuel = true;
		ENTER = true;
		Game.getPlayer().cantMove = true;
		Game.getPlayer().canAct = false;

		}

	}
	public static void onpenMenu() {
		inMenu = true;
		Game.getPlayer().canAct=false;
		Game.getPlayer().canMove=false;
		MENU=true;
		sound = new Sound_lector("res/sound/sou/menu/open.wav", Main.save.soundVolume);
		sound.start();
	}
	public static void closeMenu() {
		inMenu = false;
		Game.getPlayer().canAct=true;
		Game.getPlayer().canMove=true;
		MENU=false;
		sound = new Sound_lector("res/sound/sou/menu/close.wav", Main.save.soundVolume);
		sound.start();
	}
	public float[] getPartyColor(agree button) {
		switch (button) {
		case ACCEPTER:
			if(i == 0) return DARKBROWN;
			else return BROWN;
		case REFUSER:
			if(i == 1) return DARKBROWN;
			else return BROWN;
		default:
			break;
		
		}
		return BROWN;
	}
	private void renderItem(Item item) {
		Renderer.renderInterface(49, 14, 207, 111, BLACK);		
		Renderer.renderInterface(51, 16, 205, 109, BROWN);	
		Renderer.renderInterface(50, 48, 206, 50, BLACK);	
		
		
		
		if(objI==0) {
			Renderer.renderInterface(49, 112, 101, 132, BLACK);	
			Renderer.renderInterface(103, 113, 153, 131, BLACK);	
			Renderer.renderInterface(156, 113, 206, 131, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, DARKBROWN);	
			Renderer.renderInterface(104, 114, 152, 130, BROWN);	
			Renderer.renderInterface(157, 114, 205, 130, BROWN);
		}else if(objI==1) {
			Renderer.renderInterface(50, 113, 100, 131, BLACK);	
			Renderer.renderInterface(102, 112, 154, 132, BLACK);	
			Renderer.renderInterface(156, 113, 206, 131, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, BROWN);	
			Renderer.renderInterface(104, 114, 152, 130, DARKBROWN);	
			Renderer.renderInterface(157, 114, 205, 130, BROWN);
		}else if(objI==2) {
			Renderer.renderInterface(50, 113, 100, 131, BLACK);	
			Renderer.renderInterface(103, 113, 153, 131, BLACK);	
			Renderer.renderInterface(155, 112, 207, 132, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, BROWN);	
			Renderer.renderInterface(104, 114, 152, 130, BROWN);	
			Renderer.renderInterface(157, 114, 205, 130, DARKBROWN);
		}
		
		Renderer.renderInterfaceTexte(item.getName());
		Renderer.renderInterfaceTexte(item.getDescription());
		Renderer.renderInterfaceTexte(item.getActionName());
		Renderer.renderInterfaceTexte(item.getBack());
		Renderer.renderInterfaceTexte(item.getThrowingName());
		
		Renderer.renderInterface(54, 19, 81, 46, DARKGOLD);
		Renderer.renderInterface(55, 20, 80, 45, BROWN);
		Renderer.renderInterfaceTexture(55f,20f,80f,45f,0f,0f,oneTile,item.getTexture().id,WHITE);
	}
	private void renderItem2Choice(Item item) {
		Renderer.renderInterface(49, 14, 207, 111, BLACK);		
		Renderer.renderInterface(51, 16, 205, 109, BROWN);	
		Renderer.renderInterface(50, 48, 206, 50, BLACK);		
		
		if(objI==0) {
			Renderer.renderInterface(49, 112, 127, 132, BLACK);	
			Renderer.renderInterface(129, 113, 206, 131, BLACK);	
			
			Renderer.renderInterface(51, 114, 125, 130, DARKBROWN);	
			Renderer.renderInterface(130, 114, 205, 130, BROWN);	
		}else if(objI==1) {
			Renderer.renderInterface(50, 113, 126, 131, BLACK);	
			Renderer.renderInterface(128, 112, 207, 132, BLACK);	
			
			Renderer.renderInterface(51, 114, 125, 130, BROWN);	
			Renderer.renderInterface(130, 114, 205, 130, DARKBROWN);	
		}
	
		
		Renderer.renderInterfaceTexte(item.getName());
		Renderer.renderInterfaceTexte(item.getDescription());
		Renderer.renderInterfaceTexte(item.getActionName(),WHITE,25,0);
		Renderer.renderInterfaceTexte(item.getBack());
		
		Renderer.renderInterface(54, 19, 81, 46, DARKGOLD);
		Renderer.renderInterface(55, 20, 80, 45, BROWN);
		Renderer.renderInterfaceTexture(55f,20f,80f,45f,0f,0f,oneTile,item.getTexture().id,WHITE);
	}
	private void renderPlayer(Human joueur) {
		Renderer.renderInterface(49, 14, 207, 111, BLACK);		
		Renderer.renderInterface(51, 16, 205, 109, BROWN);	
		Renderer.renderInterface(100, 15, 102, 110, BLACK);
		Renderer.renderInterfaceTexture(55,20f,95,100,0f,0f,nb_position,player.idTexture,WHITE);
		
		Renderer.renderInterfaceTexte(goldPoint);
		Renderer.renderInterfaceTexte(attaquePoint);
		Renderer.renderInterfaceTexte(defensePoint);
		Renderer.renderInterfaceTexte(healPoint);
		Renderer.renderInterfaceTexte(lvlPoint);
		Renderer.renderInterfaceTexte(xpPoint);
		
		Renderer.renderInterfaceTexte(joueurName);
		Renderer.renderInterfaceTexte(healNum);
		Renderer.renderInterfaceTexte(goldNum);
		Renderer.renderInterfaceTexte(attaqueNum);
		Renderer.renderInterfaceTexte(lvlNum);
		Renderer.renderInterfaceTexte(defenseNum);
		Renderer.renderInterfaceTexte(xpNum);
		
		if(persoI==0) {
			Renderer.renderInterface(49, 112, 101, 132, BLACK);	
			Renderer.renderInterface(103, 113, 153, 131, BLACK);	
			Renderer.renderInterface(156, 113, 206, 131, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, DARKBROWN);	
			Renderer.renderInterface(104, 114, 152, 130, BROWN);	
			Renderer.renderInterface(157, 114, 205, 130, BROWN);
		}else if(persoI==1) {
			Renderer.renderInterface(50, 113, 100, 131, BLACK);	
			Renderer.renderInterface(102, 112, 154, 132, BLACK);	
			Renderer.renderInterface(156, 113, 206, 131, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, BROWN);	
			Renderer.renderInterface(104, 114, 152, 130, DARKBROWN);	
			Renderer.renderInterface(157, 114, 205, 130, BROWN);
		}else if(persoI==2) {
			Renderer.renderInterface(50, 113, 100, 131, BLACK);	
			Renderer.renderInterface(103, 113, 153, 131, BLACK);	
			Renderer.renderInterface(155, 112, 207, 132, BLACK);
			
			Renderer.renderInterface(51, 114, 99, 130, BROWN);	
			Renderer.renderInterface(104, 114, 152, 130, BROWN);	
			Renderer.renderInterface(157, 114, 205, 130, DARKBROWN);
		}
	}
	private void renderPlayerSelection(Caractere[] playerName) {

		
		Renderer.renderInterface(48, 30, 208, 80, BLACK);
		Renderer.renderInterface(49, 31, 207, 79, BROWN);
		if(m==21)Renderer.renderInterfaceTexte(confirmerInviteParty);
		else Renderer.renderInterfaceTexte(confirmerdemandeAmi);
		Renderer.renderInterfaceTexte(playerName,WHITE,100,60);
		
		
		
		if(joueurI==0) {
			Renderer.renderInterface(47, 83, 127, 105, BLACK);
			Renderer.renderInterface(130, 84, 208, 104, BLACK);
			Renderer.renderInterface(49, 85, 125, 103, DARKBROWN);
			Renderer.renderInterface(131, 85, 207, 103, BROWN);
			
		}else {
			Renderer.renderInterface(48, 84, 126, 104, BLACK);
			Renderer.renderInterface(129, 83, 209, 105, BLACK);
			Renderer.renderInterface(49, 85, 125, 103, BROWN);
			Renderer.renderInterface(131, 85, 207, 103, DARKBROWN);			
			
		}
		Renderer.renderInterfaceTexte(non);
		Renderer.renderInterfaceTexte(oui);
		
	}
	private void renderFriend(){
		Renderer.renderInterface(49, 14, 102, 111, BLACK);		
		Renderer.renderInterface(51, 16, 100, 109, BROWN);
		Renderer.renderInterface(100, 14, 207, 79, BLACK);
		Renderer.renderInterface(102, 16, 205, 77, BROWN);
		Renderer.renderInterface(112, 84, 195, 104, BLACK);
		Renderer.renderInterface(114, 86, 193, 102, DARKBROWN);
		
		
		Renderer.renderInterfaceTexte(amisPrecedant);
		Renderer.renderInterfaceTexte(amisLlvlPoint);
		Renderer.renderInterfaceTexte(amisAttaquePoint);
		Renderer.renderInterfaceTexte(amisHealPoint);
		Renderer.renderInterfaceTexte(amisDefensePoint);
		Renderer.renderInterfaceTexte(friendLvl);
		Renderer.renderInterfaceTexte(friendDamage);
		Renderer.renderInterfaceTexte(friendHeal);
		Renderer.renderInterfaceTexte(friendArmor);
		Renderer.renderInterfaceTexture(55,20f,95,100,0f,0f,nb_position,friends[friendReading].getTexture().id,WHITE);
		Renderer.renderInterfaceTexte(friends[friendReading].getName(),WHITE,130,20);
	}
	private void iniPlayerStat(Human joueur) {
		
		joueurName=Renderer.getText(130, 20, joueur.getName(), Main.save.favorite_police, 0.8f);
		healNum=Renderer.getText(130, 45, (String)(joueur.HP+" / "+(joueur.maxHP+joueur.getHealthBonus())), Main.save.favorite_police, 0.5f);
		goldNum=Renderer.getText(130, 55, (String)(joueur.getGold()+""), Main.save.favorite_police, 0.5f);
		attaqueNum=Renderer.getText(150, 85, (String)(joueur.getDamage()+""), Main.save.favorite_police, 0.5f);
		lvlNum=Renderer.getText(137, 65, (String)(joueur.getLVL()+""), Main.save.favorite_police, 0.5f);
		defenseNum=Renderer.getText(150, 95, (String)(joueur.getShield()+""), Main.save.favorite_police, 0.5f);
		xpNum=Renderer.getText(130, 75, (String)(joueur.getXP()+" / "+joueur.getNextLvlXP()), Main.save.favorite_police, 0.5f);
	}
	private void iniFriendStat() {
		friendLvl=Renderer.getText(145, 35, (String)(friends[friendReading].getLvl()+""), Main.save.favorite_police, 0.5f);
		friendDamage=Renderer.getText(145, 55, (String)(friends[friendReading].getDamage()+""), Main.save.favorite_police, 0.5f);
		friendHeal=Renderer.getText(145, 45, (String)(friends[friendReading].getMaxHeal()+""), Main.save.favorite_police, 0.5f);
		friendArmor=Renderer.getText(145, 65, (String)(friends[friendReading].getArmor()+""), Main.save.favorite_police, 0.5f);


	}
	private void renderDuel() {
		Renderer.renderInterface(58, 20, 198, 50, BLACK);
		Renderer.renderInterface(59, 21, 197, 49, BROWN);
		if(duelI==0) {
			Renderer.renderInterface(77, 54, 179, 76, BLACK);
			Renderer.renderInterface(78, 80, 178, 100, BLACK);
			Renderer.renderInterface(78, 105, 178, 125, BLACK);
			
			Renderer.renderInterface(79, 56, 177, 74, DARKBROWN);
			Renderer.renderInterface(79, 81, 177, 99, BROWN);
			Renderer.renderInterface(79, 106, 177, 124, BROWN);
		}else if(duelI==1) {
			Renderer.renderInterface(78, 55, 178, 75, BLACK);
			Renderer.renderInterface(77, 79, 179, 101, BLACK);
			Renderer.renderInterface(78, 105, 178, 125, BLACK);
			
			Renderer.renderInterface(79, 56, 177, 74, BROWN);
			Renderer.renderInterface(79, 81, 177, 99, DARKBROWN);
			Renderer.renderInterface(79, 106, 177, 124, BROWN);
		}else {
			Renderer.renderInterface(78, 55, 178, 75, BLACK);
			Renderer.renderInterface(78, 80, 178, 100, BLACK);
			Renderer.renderInterface(77, 104, 179, 126, BLACK);
			
			Renderer.renderInterface(79, 56, 177, 74, BROWN);
			Renderer.renderInterface(79, 81, 177, 99, BROWN);
			Renderer.renderInterface(79, 106, 177, 124, DARKBROWN);
		}
		Renderer.renderInterfaceTexte(defier);
		Renderer.renderInterfaceTexte(defieMortel);
		Renderer.renderInterfaceTexte(defieSimple);
		Renderer.renderInterfaceTexte(defiePrecedant);
		Renderer.renderInterfaceTexte(adverse);
		

	

	}
	private void selecteObj() {
		switch(objI) {
		case 0:
			readObj=false;
			break;
		case 1:
			inventaire[objreading].action();
			objI=0;
			readObj=false;
			inventaire=player.getInventaire();
			inventory = new Liste(createListeInventaire(inventaire),180f,65f);m=19;break;

		case 2:
			inventaire[objreading].removeSelf();
			objI=0;
			readObj=false;
			inventaire=player.getInventaire();
			inventory = new Liste(createListeInventaire(inventaire),180f,65f);m=19;break;

			
		}
	}
	private void selectePersoObj() {
		switch(objI) {
		case 0:
			readObj=false;
			objI=0;
			persoI=0;
			break;
		default:
			if(persoI==1)player.weapon.action();
			else player.armor.action();
			readObj=false;
			objI=0;
			persoI=0;
			iniPlayerStat(player);
			inventaire=player.getInventaire();
			inventory = new Liste(createListeInventaire(inventaire),180f,65f);
			break;

		

			
		}
	}
	private void increaseObj() {
		if (objI<2) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
			objI++;
		}
	}
	private void decreaseObj() {
		if (objI>0) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
			objI--;
		}
	}
	private void increaseDuel() {
		if (duelI<2) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
			duelI++;
		}
	}
	private void decreaseDuel() {
		if (duelI>0) {
			song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
			song.start();
			duelI--;
		}
	}
	private void selectDuel() {
		switch(duelI) {
		case 0:
			duelI=0;
			confirmeDuel=false;
			Team.askForDuel(player, pnjJoueurs[selectedJoueur], false);
			close();
			break;
		case 1:
			duelI=0;
			confirmeDuel=false;
			Team.askForDuel(player, pnjJoueurs[selectedJoueur], true);
			close();
			break;
		case 2:	
			duelI=0;
			confirmeDuel=false;
			break;
		}
	}
	private void increaseSelecetdJoueur() {
		joueurI=1;
		song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
		song.start();
	}
	private void decreaseSelecetdJoueur() {
		joueurI=0;
		song = new Sound_lector("res/sound/sou/menu/bip.wav", Main.save.soundVolume);
		song.start();
	}
	private void selectSelectedJoueur() {
		if (joueurI==0) {
			confirmeSelectedJoueur=false;
			
		}else {
			if(m==21) {
				m=13;
				demanderJoueur=false;
				Team.askForParty(player,pnjJoueurs[selectedJoueur]);
			}
			if(m==22) {
				if(pnjJoueurs[selectedJoueur].askForFriend()) {
					int friend=pnjJoueurs[selectedJoueur].getFriend().getSignature();
					Main.save.friends[friend]=friend;
				}
			}
			confirmeSelectedJoueur=false;
			joueurI=0;
			if(m==22) {
				m=16;
				demanderJoueur=false;				
			}
		}
	}
	private void selectPerso() {
		switch(persoI) {
		case 0:
			inStuff=false;
			m=1;
			break;
		case 1:{
			readObj=true;
			break;
		}
		case 2:{
			readObj=true;
			break;
		}
			
		}
	}
	private void increasePerso() {
		if(persoI==0 && player.weapon.getID()!=0)persoI=1;
		else if(player.armor.getID()!=0)persoI=2;
	}
	private void decreasePerso() {
		if(persoI==2 && player.weapon.getID()!=0)persoI=1;
		else persoI=0;
	}
	private float[] getHealthColor(float percentOfLife) {
		if(percentOfLife >0.5f) return GREEN;
		else if(percentOfLife > 0.2f) return GOLD;
		else return RED;
	}
	public void renderEndDuel(boolean youWinn) {
		win=youWinn;
		endDuelDelay=1;
	}
	private void close() {
		i=0;
		m=0;
		objI=0;
		persoI=0;
		duelI=0;
		obj=false;
		askDuel=false;
		readObj=false;
		inStuff=false;
		readFriend=false;
		demanderJoueur=false;
		confirmeDuel=false;
		confirmeSelectedJoueur=false;
		closeMenu();
		
	}
	public static void addGivers(String text) {
		for (int i = 8; i>=0;i--) {
			givers[i+1]=givers[i];
			givers[i+1].addY(-10);
		}
		givers[0]=new Giver(text,200,130,true);
	}
	public void renderLevelUp() {
		lvlUp=true;
		endDuelDelay++;
	}
}
