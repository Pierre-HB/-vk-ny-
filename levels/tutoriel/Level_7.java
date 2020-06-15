package fr.pierrehb.levels.tutoriel;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.objects.Door;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Level_7  extends StandartLevel {

	static int[][] level =	{{3,3,3,3,3,13,1,1,1,13,13,3,3,3,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,13},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,13}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0},{2,1  ,2,2  ,2,3  ,2,5  ,2,6  ,2,7  ,2,13  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1}};
	public Level_7() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		humanSpawner(new OldMan(80, 0,direction.RIGHT));
		if(Main.save.Sparadrap)humanSpawner(new Sparadrap((int)player.x, (int)player.y));
		spawner(new Door(96, -32, false, WHITE));
		lector = new Sound_lector("res/sound/mus/tutoriel/Puzzles.wav", Main.save.musicVolume);
		lector.start();
		music = true;




		}

	@Override
	public void trueUpdate() {
		if(!Team.isDuel) {
		if(player.getX()+15 > (size[0]*16-8)) {
			int[] newCoord = { 16, 48};
			nextLevel(15, newCoord);
		}
		if(player.getY()+15 < 8) {
			int[] newCoord = { 128, 192};
			nextLevel(17, newCoord);
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