package fr.pierrehb.levels.end_Castel;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.objects.ArmorStand;
import fr.pierrehb.entities.villageois.RedGuard;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.items.armors.FinalArmor;
import fr.pierrehb.levels.StandartLevel;

public class EndCastel_2 extends StandartLevel {

	static int[][] level =	{{1,6,1,6,1,6,1},
							{3,6,4,6,4,6,3},
							{3,6,5,6,5,6,3},
							{3,6,2,6,2,6,3},
							{3,2,2,2,2,2,3},
							{3,2,2,2,2,2,3},
							{3,2,2,2,2,2,3},
							{3,2,2,2,2,2,3},
							{3,2,2,2,2,2,3},
							{3,3,1,1,1,3,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/EndCastel/Sprit1.png");
	static Texture loadtileset1 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet1.png");
	static Texture loadtileset2 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet2.png");
	static Texture loadtileset3 = Texture.loadTexture("res/textures/Tile/EndCastel/TileSet3.png");
	static Texture[] tileset = {loadtileset1,loadtileset2,loadtileset3};
	static int[][] WhoToRead = {{1, 2, 7},{4, 3},{4,2,5,2},{2,1,  6,2,  7,0},{2,2  ,2,1  ,6,6  ,6,-1  ,7,7}};
	public EndCastel_2() {
		super(tex, tileset, level, WhoToRead);
		

		standartIniTiles();
		humanSpawner(new Player());
		spawner(new ArmorStand(48,48, new FinalArmor()));
		humanSpawner(new RedGuard(80, 64,direction.LEFT));
		humanSpawner(new RedGuard(16, 64,direction.RIGHT));
		
	}

	@Override
	public void trueUpdate() {
		if(!Team.isDuel) {
			if(player.getY()+31 > (size[1]*16-8)) {
				int[] newCoord = { 386, 64};
				nextLevel(-1, newCoord);
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
	protected void spreadStart(int repetitionNumber) {
		
	}

}