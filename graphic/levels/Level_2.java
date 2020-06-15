package fr.pierrehb.levels;


import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Level_2 extends StandartLevel {

		static int[][] level =	{{3,3,3,12,3,3,12,12,3,3,3,12,3,12,3},
								{3,3,3,12,3,3,12,12,3,3,3,12,3,12,3},
								{3,4,4,4,4,4,4,4,4,4,4,4,4,4,3},
								{3,4,4,4,4,4,4,4,2,2,4,4,4,4,3},
								{1,2,2,2,2,2,2,2,2,2,2,4,4,4,3},
								{1,2,2,2,2,2,2,2,2,2,2,4,4,4,3},
								{3,5,6,6,6,6,7,3,2,2,3,4,4,4,3},
								{3,4,4,4,4,4,4,3,3,3,3,4,4,4,3},
								{3,4,4,4,4,4,4,4,3,3,4,4,4,4,3},
								{3,4,4,4,4,4,4,4,4,4,4,4,4,4,3}};
		
		static int dialogue = 0;

		static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit1.png");
		static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
		static Texture[] tileset = {loadtileset};
		static int[][] WhoToRead = {{1, 2},{4, 4},{12,2, 4,2,  5,2,  6,2,  7,2},{2,0,  3,0},{2,2  ,2,3  ,2,1   ,2,5   ,2,6   ,2,7  ,3,12 ,3,3  ,3,-1  ,3,1}};
		public Level_2() {
			super(tex, tileset, level, WhoToRead);
			standartIniTiles();
			Main.getSave();
			humanSpawner(new OldMan(64, 64,direction.RIGHT));
			humanSpawner(new Player());
			lector = new Sound_lector("res/sound/mus/tutoriel/Waterfall.wav", Main.save.musicVolume);
			lector.start();
			music = true;
		}

		
		@Override
		public void trueUpdate() {
			if(!Team.isDuel) {
			if(player.getX() < 8) {
				int[] newCoord = { 208, 64};
				nextLevel(1, newCoord);
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
