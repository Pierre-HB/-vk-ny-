package fr.pierrehb.levels;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Level_4 extends StandartLevel {

	static int[][] level =	{{3,12,13,13,14,3,12,3,3,17,13,13,3,3,18,3,3,12,3,15,3,13,3,3},
							{3,12,13,13,1,3,12,3,3,1,3,3,13,3,1,3,3,12,3,1,3,13,3,13},
							{3,12,13,1,1,1,12,3,1,1,1,3,13,1,1,1,3,12,1,1,1,13,13,13},
							{3,12,3,1,1,1,12,3,1,1,1,13,13,1,1,1,3,12,1,1,1,3,13,3},
							{3,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,3},
							{3,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,3},
							{3,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,2,2,2,4,4,3},
							{3,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,4,13},
							{13,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,4,13},
							{13,4,4,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,4,4,3},
							{13,4,4,3,5,6,7,3,3,2,2,2,2,2,2,3,3,5,6,7,3,4,4,3},
							{3,4,4,3,4,4,4,3,3,2,2,2,2,2,2,3,3,4,4,4,3,4,4,3},
							{3,4,4,4,4,4,4,4,4,2,2,2,2,2,2,4,4,4,4,4,4,4,4,3},
							{3,3,3,13,13,13,3,3,3,3,3,1,1,3,3,13,3,13,13,13,3,3,3,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0},{2,1  ,2,2  ,2,3  ,2,5  ,2,6  ,2,7  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1}};
	public Level_4() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		humanSpawner(new OldMan(160, 176,direction.RIGHT));
		lector = new Sound_lector("res/sound/mus/tutoriel/Selection.wav", Main.save.musicVolume);
		lector.start();
		music = true;

		}


	@Override
	public void trueUpdate() {

		if(!Team.isDuel) {
		if(player.getY()+32 > (size[1]*16-8)) {
			int[] newCoord = { 112, 0};
			nextLevel(1, newCoord);
		}
		if(player.getY()-16 < (16*2-8) && player.getX() >= 48 && player.getX() < 96) {
			int[] newCoord = { 64, 112};
			nextLevel(5, newCoord);
		}
		if(player.getY()-16 < (16*2-8) && player.getX() >= 128 && player.getX() < 176) {
			int[] newCoord = { 64, 112};
			nextLevel(6, newCoord);
		}
		if(player.getY()-16 < (16*2-8) && player.getX() >= 208 && player.getX() < 256) {
			int[] newCoord = { 64, 112};
			nextLevel(7, newCoord);
		}
		if(player.getY()-16 < (16*2-8) && player.getX() >= 288 && player.getX() < 336) {
			int[] newCoord = { 64, 112};
			nextLevel(8, newCoord);
		}}
		

	}

	@Override
	public void trueRender() {

		

	}

	@Override
	public void interact() {
		standartInteract();		
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
	protected void spreadStart(int epetitionNumber) {
		
	}

	


}