/**
 * 
 */
package fr.pierrehb.levels;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Hacker;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;


public class Level_1 extends StandartLevel {

	static int[][] level =	{{3,3,3,3,3,3,1,1,1,3,3,3,3,3,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit1.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 4},{},{2,0,  3,0},{2,2  ,2,3  ,2,1  ,3,3  ,3,-1  ,3,1}};
	Sparadrap sparadrap;
	public Level_1() {
		super(tex, tileset, level, WhoToRead);

		standartIniTiles();
		humanSpawner(new Player());
		

		


		if(Main.save.avancement==2 && Main.compareName("PierreHB")) humanSpawner( new Hacker(112, 64));
		
		if(Main.save.Sparadrap && Main.save.avancement>=3) {
			sparadrap = new Sparadrap(64, 64);
			sparadrap.interacting = true;
			sparadrap.interact();
			humanSpawner(sparadrap);
		}else humanSpawner(new OldMan(208, 64,direction.RIGHT));
		
		lector = new Sound_lector("res/sound/mus/tutoriel/Puzzles.wav", Main.save.musicVolume);
		lector.start();
		music = true;
		}


	@Override
	public void trueUpdate() {
		if(!Team.isDuel) {
		if(player.getX()+15 > (size[0]*16-8)) {
			int[] newCoord = { 16, 64};
			nextLevel(2, newCoord);
		}
		if(player.getX() < 8) {
			int[] newCoord = { 1000, 64};
			nextLevel(3, newCoord);
		}
		if(player.getY()+15 < 8) {
			int[] newCoord = { 184, 184};
			nextLevel(4, newCoord);
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
		
	}

	@Override
	protected void spreadStart(int epetitionNumber) {
		
	}

	


}
