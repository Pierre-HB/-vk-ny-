package fr.pierrehb.levels;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Hacker;
import fr.pierrehb.graphic.Alpha_End_Interface;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;

public class Alpha_End extends StandartLevel {
	
	static int[][] level =	{{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1}};
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit1.png");
	static Texture[] tileset = {};	
	static int[][] WhoToRead = {{1,2},{4, 4},{},{},{}};
	Hacker hacker;
	int cunt = 0;
	private static final int[] newCoord = { 416, 112};



	public Alpha_End() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		player.canAttack=false;
		player.canAct=false;
		player.haveParty=false;
		hacker = (Hacker) humanSpawner( new Hacker(32, 32));
		INTERFACE=false;
		OTHERINTERFACE=true;
		SPECIALDECONEXION=true;
		otherInterface= new Alpha_End_Interface();
		
		Main.save.room = 18;
		Main.save.coordinates = newCoord;
		Main.save();
	}

	@Override
	protected void spreadStart(int epetitionNumber) {
		
	}

	@Override
	public void trueRender() {
		
	}

	@Override
	public void trueUpdate() {
		cunt++;
		if(player.x>14)player.x=8;
		if(player.x<2)player.x=8;
		if(player.y>94)player.y=94;
		if(player.y<-14)player.y=-14;
		player.canMove=true;
		if(cunt==18000) {
			player.canAct=true;
			hacker.interat();
		}
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
		standartInteract();
		
	}

}
