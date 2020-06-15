package fr.pierrehb.levels.levels;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.objects.Dummy;
import fr.pierrehb.entities.objects.Grille;
import fr.pierrehb.entities.villageois.OldMan;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;

public class Guerrier_Second_Room extends StandartLevel {

	static int[][] level =	{{3,3,3,3,3,3,3,3,13,3,3,3,3,3,3,3,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,5,6,6,6,7,13,3,3,5,6,6,6,7,2,3},
							{13,2,4,4,4,4,4,3,13,3,4,4,4,4,4,2,3},
							{13,2,4,4,4,4,4,4,4,4,4,4,4,4,4,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,13},
							{3,3,3,3,3,3,3,1,1,1,3,3,3,3,3,13,13}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture[] tileset = {loadtileset};
	static int[][] WhoToRead = {{1, 2},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0},{2,1  ,2,2  ,2,3  ,2,13  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1}};
	static Entity[] dummys = new Entity[5];
	static Entity[] grilles = new Entity[6];
	static int count = 0;
	static int antcount = 0;

	static boolean done = false;
	public Guerrier_Second_Room() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		humanSpawner(new OldMan(96,96,direction.RIGHT));
		dummys[0] = new Dummy(32, 0);
		dummys[1] = new Dummy(80, 0);
		dummys[2] = new Dummy(128, 0);
		dummys[3] = new Dummy(176, 0);
		dummys[4] = new Dummy(224, 0);
	
		if(Main.save.avancement>=3) {
			for(Entity dummy : dummys)dummy.HP = 0;
			done = true;
		}else {
		grilles[0] = new Grille(112,128);
		grilles[1] = new Grille(128,128);
		grilles[2] = new Grille(144,128);
		grilles[3] = new Grille(112,144);
		grilles[4] = new Grille(128,144);
		grilles[5] = new Grille(144,144);
		solidList[7][8] = true;
		solidList[8][8] = true;
		solidList[9][8] = true;
		for(Entity grille : grilles) spawner(grille);

	}
	
		for(Entity dummy : dummys) spawner(dummy);
		
		




	}
	@Override
	public void interact() {
		standartInteract();
	}
	@Override
	public void trueUpdate() {
		
		if(!done) {
			boolean onelife = false;
			for(Entity dummy : dummys) if(dummy.life) onelife = true;
			if(!onelife) {
				solidList[7][8] = false;
				solidList[8][8] = false;
				solidList[9][8] = false;
				done = true;
				Main.save.playerAttacks[0][2] = 1;
				Main.save.playerAttacks[1][1] = 1;
				Main.save.playerAttacks[3][0] = 2;
				

			}}else if(Main.save.avancement<3) {
				if(count < 32) {
					if(antcount < 8) {
						antcount++;
					}else {	
						antcount = 0;
						count++;
					for(Entity grille : grilles) grille.y--;
					if(count == 32) for(Entity grille : grilles) grille.remove = true;
				}}
			
			
		}
		
		if(player.getY()+32 > (size[1]*16-8)) {
			int[] newCoord = { 64, 32};
			if(Main.save.avancement<3)Main.save.avancement=3;
			nextLevel(5, newCoord);
		}
		
	}
	@Override
	public void trueRender() {
		
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
