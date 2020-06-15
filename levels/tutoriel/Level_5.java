package fr.pierrehb.levels.tutoriel;


import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.objects.Button;
import fr.pierrehb.entities.objects.Herse;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.ObjectsStates.herse_state;
import fr.pierrehb.sound.Sound_lector;

public class Level_5  extends StandartLevel {

	static int[][] level =	{{3,3,13,13,13,3,3,3,3,3,3,3,1,1,13,13},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,13},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,13},
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{13,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{13,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0},{2,1  ,2,2  ,2,3  ,2,5  ,2,6  ,2,7  ,2,13  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1}};
	static Herse[] herses = new Herse[8];
	public Level_5() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		humanSpawner(new OldMan(144, 0));
		herses[0] = (Herse)spawner(new Herse(96, 0, herse_state.UP, RED));
		herses[1] = (Herse)spawner(new Herse(96, 16, herse_state.UP, RED));
		herses[2] = (Herse)spawner(new Herse(96, 32, herse_state.UP, RED));
		herses[3] = (Herse)spawner(new Herse(96, 48, herse_state.UP, WHITE));
		herses[4] = (Herse)spawner(new Herse(96, 64, herse_state.UP, RED));
		herses[5] = (Herse)spawner(new Herse(96, 80, herse_state.UP, WHITE));
		herses[6] = (Herse)spawner(new Herse(96, 96, herse_state.UP, WHITE));
		herses[7] = (Herse)spawner(new Herse(96, 112, herse_state.UP, WHITE));
		objectList.put(1,spawner(new Button(128, 0, 2)));
		if(Main.save.Sparadrap)humanSpawner(new Sparadrap((int)player.x, (int)player.y));
		
		lector = new Sound_lector("res/sound/mus/tutoriel/Puzzles.wav", Main.save.musicVolume);
		lector.start();
		music = true;


		}

	@Override
	public void trueUpdate() {
		if(!Team.isDuel) {
		if(player.getY()+16 < 8) {
			int[] newCoord = { 56, 112};
			nextLevel(3, newCoord);
		}
		
		if(player.getX() < 8) {
			int[] newCoord = { 226, 48};
			nextLevel(15, newCoord);
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
		if(i == 2) for(Herse herse : herses) herse.setState(herse_state.DOWN);
		
	}

	@Override
	protected void spreadStart(int epetitionNumber) {
		
	}

	


}
