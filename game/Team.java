package fr.pierrehb.game;

import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Dialogue;

public class Team implements Color, Dialogue{
	public static int team = 42;
	private static String playerName = Main.save.name;
	
	public static int playerTeam = -42;
	private static int sparadrapTeam = -43;
	private static int hackerTeam = -44;
	private static int createurTeam = -45;
	public static boolean playerHaveTeam = false;
	private static boolean sparadrapHaveTeam = false;
	public static Human demandeur;
	public static Human accepteur;
	public static Human demandeur1;
	public static Human accepteur1;
	public static Human duelDemandeur;
	public static Human duelAccepteur;
	public static boolean askingForParty = false;
	public static List<Joueur> playerteam = new ArrayList<Joueur>();
	public static List<Joueur> playeradverse = new ArrayList<Joueur>();
	public static boolean PlayerHaveDuel=false;
	public static int[] duelTeam= {0,0};
	public static boolean duelToDeath=false;
	public static boolean isDuel=false;
	public static boolean askingForDuel=false;
	
	public static void update() {
		if(isDuel) {
			for (Human man : Game.level.humans) {
				if(man.team==duelTeam[0]) {
					if(duelToDeath && man.HP<=0) endDuel(duelTeam[1]);
					else if(!duelToDeath && man.HP<=man.getMaxHP()/2) endDuel(duelTeam[1]);
					}
				if(man.team==duelTeam[1]) {
					if(duelToDeath && man.HP<=0) endDuel(duelTeam[0]);
					else if(!duelToDeath && man.HP<=man.getMaxHP()/2) endDuel(duelTeam[0]);
					

				}
					
				}
			
		}
	}
	private static void iniPlayerDuel() {
		int adversTeam=0;
		if(playerTeam==duelTeam[0])adversTeam=duelTeam[1];
		else adversTeam=duelTeam[0];
		for (Human man : Game.level.humans) {
			if(man.team==adversTeam)playeradverse.add((Joueur)man);

		}
	}
	
	public static boolean askForDuel(Human Demandeur, Human Accepteur, boolean toDeath) {
		if(Demandeur.team!=Accepteur.team) {
			if(!askingForDuel)duelToDeath=toDeath;
		if(Accepteur.getClass() == Player.class) {
			return askDuelBetweenPlayer(Demandeur, Accepteur);
		}
		else {
			return askDuelBetweenPNJ(Demandeur, Accepteur);
		}
		}return false;
		}
	private static boolean askDuelBetweenPNJ(Human Demandeur, Human Accepteur) {
		if(Accepteur.askDuel(Demandeur)) {
		demandeur1 = Demandeur;
		accepteur1 = Accepteur;
		duelTeam[0]=demandeur1.team;
		duelTeam[1]=accepteur1.team;
		isDuel=true;
		startDuel();
		return true;	
		}return false;
	}
	private static void startDuel() {
		iniPlayerDuel();
		for (Human man : Game.level.humans) {
			if(man.team==duelTeam[0])man.setDuel(duelTeam[1]);
			if(man.team==duelTeam[1])man.setDuel(duelTeam[0]);
		}
	}
	private static void endDuel(int winner) {
		clearPlayerAdverse();
		isDuel=false;
		for (Human man : Game.level.humans) {
			if(man.team==duelTeam[0])man.endDuel(winner);
			if(man.team==duelTeam[1])man.endDuel(winner);
		}
	}
	
	
	private static boolean askDuelBetweenPlayer(Human Demandeur, Human Accepteur) {
		if(!askingForDuel) {
		duelDemandeur = Demandeur;
		duelAccepteur = Accepteur;
		askingForDuel = true;
		((Player)duelAccepteur).askDuel(duelDemandeur);
		return true;
	}else return false;
	}
	public static void answerForDuel(boolean response) {
		askingForDuel = false;
		if(response) {			
			duelTeam[0]=duelDemandeur.team;
			duelTeam[1]=duelAccepteur.team;
			isDuel=true;
			startDuel();			
		}
	}
	public static void clearPlayerAdverse() {
		playeradverse.clear();
	}
	
	
	
	public static boolean askForParty(Human Demandeur, Human Accepteur) {
		if(Accepteur.getClass() == Player.class) return askBetweenPlayer(Demandeur, Accepteur);
		else return askBetweenPNJ(Demandeur, Accepteur);
		}
	private static boolean askBetweenPNJ(Human Demandeur, Human Accepteur) {
		demandeur = Demandeur;
		accepteur = Accepteur;
		accepteur.setParty(demandeur);
		return true;
	
	}
	private static boolean askBetweenPlayer(Human Demandeur, Human Accepteur) {
		if(!askingForParty) {demandeur = Demandeur;
		accepteur = Accepteur;
		askingForParty = true;
		accepteur.setParty(demandeur);
		return true;
	}else return false;
	}
	public static void answerForParty(boolean response) {
		askingForParty = false;
		if(response) {
			System.out.println(demandeur.getName());
			if(demandeur.haveParty) {
				accepteur.haveParty = true;
				accepteur.team = demandeur.team;
				setTeam(accepteur, demandeur.team);
				inPlayerTeam(demandeur);
				inPlayerTeam(accepteur);
			}else {
				accepteur.team = demandeur.team = getTeam();
				accepteur.haveParty = demandeur.haveParty = true;
				setTeam(demandeur, demandeur.team);
				setTeam(accepteur, demandeur.team);
				inPlayerTeam(demandeur);
				inPlayerTeam(accepteur);


			}
		}
	}
	private static void inPlayerTeam(Human joueur) {
		if(joueur.team == playerTeam && joueur.getClass() != Player.class && !playerteam.contains(joueur)) playerteam.add((Joueur)joueur);

	}
	public static void clearPlayerTeam() {
		playerteam.clear();
	}
	public static void iniTeam(Human perso) {
		switch (perso.getName()) {
		case str_sparadrapName:
			perso.team = sparadrapTeam;
			perso.haveParty = sparadrapHaveTeam;
			inPlayerTeam(perso);
			return;
		case str_createurName:
			perso.team = createurTeam;
			return;
		case str_hackerName:
			perso.team = hackerTeam;;
			default:
				return;
		}
	}
	public static void playerDie(Joueur dead) {
		if(playerteam.contains(dead)) playerteam.remove(dead);
	}
	public static void leaveTeam(Human joueur) {
		if(joueur.haveParty) {
			joueur.haveParty=false;
			if(joueur.getName() == playerName) {
				playerTeam=-42;
				playerHaveTeam=false;
				joueur.team=-42;
				clearPlayerTeam();
				
			}else {
			switch (joueur.getName()) {
			case str_sparadrapName:
				sparadrapTeam = -43;
				joueur.team=-43;
				sparadrapHaveTeam=false;
				break;
			}}
			if(playerteam.contains(joueur)) playerteam.remove(joueur);
		}
	}
	public static int getTeam() {
		team++;
		return team;
	}
	public static float[] getTeamColor(int Team) {
		if(Team == playerTeam) return GREEN;
		switch (Team%4) {
		case 0:
			return RED;
		case 1:
			return BLUE;
		case 2:
			return GOLD;
		case 3:
			return BROWN;
		default:
			return WHITE;
		
		}
	}
	
	public static void setTeam(Human human, int newTeam) {
		if(human.getName() == Main.save.name) {playerTeam = newTeam; playerHaveTeam = true; return;}
		switch (human.getName()) {
		case str_sparadrapName:
			sparadrapTeam = newTeam;
			sparadrapHaveTeam = true;
			break;
		default:
			break;
		}
		
	}
	public static void createTeam(Human human) {
		int t = getTeam();
		human.team=t;
		human.haveParty=true;
		setTeam(human, t);
	}
}
