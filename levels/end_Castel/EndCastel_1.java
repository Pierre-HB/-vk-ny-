package fr.pierrehb.levels.end_Castel;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Createur;
import fr.pierrehb.entities.objects.Door;
import fr.pierrehb.entities.villageois.RedGuard;
import fr.pierrehb.entities.villageois.RedKing;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class EndCastel_1  extends StandartLevel {

	static int[][] level =	{{1,6,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,1,1,1},
							{3,6,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,3,3,3},
							{3,6,3,4,3,6,3,4,3,6,3,4,3,6,3,4,3,6,3,4,3,6,3,10,1,11,3,3},
							{3,6,3,5,3,6,3,5,3,6,3,5,3,6,3,5,3,6,3,5,3,6,3,1,1,1,3,3},
							{3,6,2,2,2,6,2,2,2,6,2,2,2,6,2,2,2,6,2,2,2,6,3,1,1,1,3,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,7,7,7,7,7,2,2,2,2,2,2,3},
							{1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,2,2,2,2,2,2,3},
							{1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,2,2,2,2,2,2,3},
							{1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,7,7,7,7,7,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,12,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,2,2,3},
							{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,3,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/EndCastel/Sprit1.png");
	static Texture loadtileset1 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet1.png");
	static Texture loadtileset2 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet2.png");
	static Texture loadtileset3 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet3.png");
	static Texture[] tileset = {loadtileset1,loadtileset2,loadtileset3};
	static int[][] WhoToRead = {{1, 2, 7},{4, 3},{4,2,5,2},{2,1,  6,2,  7,0},{2,2  ,2,1  ,6,6  ,6,-1  ,7,7}};
	private Createur createur;
	private int introEvent = 0;
	public EndCastel_1() {
		super(tex, tileset, level, WhoToRead);

		standartIniTiles();
		if(Main.save.avancement != 0)humanSpawner(new Player());
		else {humanSpawner(new Player(250,250,false, false));
		createur = new Createur(112, 112, true,direction.RIGHT);
		humanSpawner(createur);
		
		humanSpawner(new RedKing(256, 112,direction.LEFT));
		humanSpawner(new RedGuard(80, 64,direction.DOWN));
		humanSpawner(new RedGuard(144, 64,direction.DOWN));
		humanSpawner(new RedGuard(208, 64,direction.DOWN));
		lector = new Sound_lector("res/sound/mus/cinematique/phase0.wav", Main.save.musicVolume);
		lector.start();
		music = true;
		}
		spawner(new Door(368, 32, false, WHITE));
		}

	@Override
	public void trueUpdate() {
		if(introEvent == 1)createur.moveX(1);
		if(introEvent == 6)createur.moveX(-1);
		if(!Team.isDuel) {
			if(player.getX()+15 > 368 && player.getY() < 56) {
				int[] newCoord = { 48, 96};
				nextLevel(-2, newCoord);
			}
		}
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
		introEvent = i;
		switch (i) {
		case 3:createur.interaction();break;
		case 4:createur.interaction();break;
		case 5:createur.interaction();break;
		}
		
	}

	@Override
	protected void spreadStart(int repetitionNumber) {
		
	}

	


}
