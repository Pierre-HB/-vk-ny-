package fr.pierrehb.levels.levels;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.objects.Coffre_Vérouiller;
import fr.pierrehb.entities.objects.Grille;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Tile;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.items.others.Candy;
import fr.pierrehb.items.wands.BasicWand;
import fr.pierrehb.levels.Sous_Level;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.levels.sous_level.Sous_Level_2;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.ObjectsStates;

public class Mage_First_Room extends StandartLevel implements ObjectsStates {
	static Coffre_Vérouiller coffre;
	static int[][] level = {{13,13,3,1,1,1,3,3,3},
							{3,13,3,1,1,1,3,3,3},
							{3,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,13},
							{3,2,2,2,2,2,2,2,13},
							{3,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,3},
							{13,2,2,2,2,2,2,2,3},
							{13,13,3,1,1,1,3,3,3}};
	static Entity[] grilles = new Entity[6];
	static Entity[] grilles2 = new Entity[6];
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0},{2,1  ,2,2  ,2,3  ,2,13  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1}};
	private boolean logiclevel = false;
	public Sous_Level logicLVL;
	private boolean win = false;
	private int timer = 0;
	private int antTimer = 0;
	float[] test = {4, 5};
	Tile[] tiles = new Tile[6];
	Interface inter = new Interface();
	private OldMan oldMan = new OldMan(32, 112,direction.RIGHT);

	public Mage_First_Room() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		coffre = new Coffre_Vérouiller(64,64);
		objectList.put(1, coffre);
		spawner(coffre);
		solidList[4][5] = true;
		ini();

		
		

		}
	private void ini() {



humanSpawner(oldMan);
if(Main.save.avancement>=3) {
	coffre.setState(coffre_state.OPEN);

		}else {
			coffre.setState(coffre_state.CLOSE);
			grilles[0] = new Grille(48,0);
			grilles[1] = new Grille(64,0);
			grilles[2] = new Grille(80,0);
			grilles[3] = new Grille(48,16);
			grilles[4] = new Grille(64,16);
			grilles[5] = new Grille(80,16);

			spawner(grilles[0]);
			spawner(grilles[1]);
			spawner(grilles[2]);
			spawner(grilles[3]);
			spawner(grilles[4]);
			spawner(grilles[5]);

			
			solidList[3][1] = true;
			solidList[4][1] = true;
			solidList[5][1] = true;
		}
	}
	@Override
	public void interact() {
		if(!logiclevel) {
			standartInteract();
		}else {
			oldMan.explainLogicLevel();
		}

	}

	@Override
	public void trueUpdate() {
		if(logiclevel) {
			STANDART_UPDATE = false;
			logicLVL.update();
		}
		else {
			STANDART_UPDATE = true;
			if(win) {
				if(timer<31) {
					antTimer++;
				if(antTimer > 8) {
					timer++;
					antTimer = 0;
					for( Entity ent : grilles)ent.y-=1;
					for( Entity ent : grilles2)ent.y+=1;

				}}
				
				
			}
			
		if(player.getY()+32 > (size[1]*16-8)) {
			int[] newCoord = { 144, 48};
			nextLevel(4, newCoord);
		}
		if(player.getY() < 8) {
			int[] newCoord = { 128, 96};
			nextLevel(11, newCoord);
		}
		}
		
		
	}

	@Override
	public void trueRender() {
		if(logiclevel) {
			STANDART_RENDER = false;
			logicLVL.render();
			oldMan.renderLogicLevel();
		}
		else {
			STANDART_RENDER = true;
			Renderer.renderQuad(48, 0, 96, -32, BLACK);
		}
		
	}

	@Override
	public boolean getSolidMoveX(float xf, float yf) {
		return logicLVL.getSolidMoveX(xf, yf);
	}

	@Override
	public boolean getSolidMoveY(float xf, float yf) {
		return logicLVL.getSolidMoveY(xf, yf);
	}

	@Override
	public void speakToLevel(int i) {
		switch (i) {
		case 1:
			logiclevel = true;
			INTERFACE = false;
			translate_view = false;
			logicLVL = new Sous_Level_2();
			oldMan.explainLogicLevel();
			break;
		case 2:

			logiclevel = false;
			INTERFACE = true;
			translate_view = true;
			Sous_Level.dynamicSquareList.clear();
			Sous_Level.targetSquareList.clear();
			Sous_Level.squareList.clear();
			break;
		case 3:
			logiclevel = false;
			INTERFACE = true;
			translate_view = true;
			coffre.setState(coffre_state.OPEN);
			win = true;
			player.giveItem(new BasicWand());
			player.giveItem(new Candy());
			player.giveItem(new Candy());
			player.giveItem(new Candy());
			grilles2[0] = new Grille(48,112);
			grilles2[1] = new Grille(64,112);
			grilles2[2] = new Grille(80,112);
			grilles2[3] = new Grille(48,128);
			grilles2[4] = new Grille(64,128);
			grilles2[5] = new Grille(80,128);
			spawner(grilles2[0]);
			spawner(grilles2[1]);
			spawner(grilles2[2]);
			spawner(grilles2[3]);
			spawner(grilles2[4]);
			spawner(grilles2[5]);
			solidList[3][1] = false;
			solidList[4][1] = false;
			solidList[5][1] = false;
			solidList[3][9] = true;
			solidList[4][9] = true;
			solidList[5][9] = true;
			Main.save.classe = classes.MAGE;
			Main.save.playerAttacks[0][0] = 1;
			Main.save.playerAttacks[1][0] = 1;
			Main.save.playerAttacks[2][0] = 1;
			
	
		


			break;

		}
		
	}
	@Override
	protected void spreadStart(int epetitionNumber) {
		
	}

}
