package fr.pierrehb.levels.tutoriel;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.boss.boss1.Boss1;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.objects.Button;
import fr.pierrehb.entities.objects.Cannon;
import fr.pierrehb.entities.objects.Cristal;
import fr.pierrehb.entities.objects.Door;
import fr.pierrehb.entities.objects.Grille;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Boss_Room_1 extends StandartLevel {

	static int[][] level =	{{0,3,3,3,3,3,3,1,1,1,3,3,3,3,3,3,0},
							{0,3,2,2,2,2,2,19,19,19,2,2,2,2,2,3,0},
							{0,3,2,2,2,2,2,19,2,19,2,2,2,2,2,3,0},
							{0,3,2,2,19,2,2,19,19,19,2,2,19,2,2,3,0},
							{0,3,2,2,2,2,2,19,19,19,2,2,2,2,2,3,0},
							{3,3,2,2,2,2,19,19,19,19,19,2,2,2,2,3,3},
							{1,1,19,19,19,19,19,19,19,19,19,19,19,19,19,1,1},
							{1,1,19,2,19,19,19,19,19,19,19,19,19,2,19,1,1},
							{1,1,19,19,19,19,19,19,19,19,19,19,19,19,19,1,1},
							{3,3,2,2,2,2,19,19,19,19,19,2,2,2,2,3,3},
							{0,3,2,2,2,2,2,19,19,19,2,2,2,2,2,3,0},
							{0,3,2,2,19,2,2,19,19,19,2,2,19,2,2,3,0},
							{0,3,2,2,2,2,2,19,2,19,2,2,2,2,2,3,0},
							{0,3,2,2,2,2,2,19,19,19,2,2,2,2,2,3,0},
							{0,3,3,3,3,3,3,1,1,1,3,3,3,3,3,3,0}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit2.png");
	static Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
	static Texture loadtileset2 = Texture.loadTexture("res/textures/Tile/Underground/TileSet2.png");
	static Texture[] tileset = {loadtileset, loadtileset2};
	static int[][] WhoToRead = {{1, 2, 19},{4, 5},{4,2, 12,2 ,5,2 ,6,2 ,7,2},{2,0,  3,0,  13,0,  19,1},{2,1  ,2,2  ,2,3  ,2,5  ,2,6  ,2,7  ,2,13  ,3,3  ,3,-1  ,3,1  ,3,12  ,3,13  ,3,14  ,3,15  ,3,16  ,3,17  ,3,18  ,13,3  ,13,12  ,13,13  ,13,14  ,13,15  ,13,16  ,13,17  ,13,18  ,13,-1  ,13,1  ,19,1  ,19,19}};
	static Cannon[] cannons = new Cannon[4];
	static Button[] buttons = new Button[4];
	Boss1 boss = new Boss1(120, 104);
	int cunt = -1; 
	private Door door;
	public Boss_Room_1() {
		super(tex, tileset, level, WhoToRead);
		standartIniTiles();
		humanSpawner(new Player());
		if(Main.save.Sparadrap)humanSpawner(new Sparadrap((int)player.x, (int)player.y));

		door = (Door)spawner(new Door(112, -32, true, WHITE));
		
		if(Main.save.avancement<4) {
			int[] newCoord = { 112, 0};
		Main.save.coordinates=newCoord;
		Main.save.room = 16;
		Main.getSave();
		SPECIALDECONEXION=true;
		spawner(new Cristal(112, 96, SEMIWHITE));
		bossSpawner(boss);
		cannons[0] = (Cannon)spawner(new Cannon(176, 48, 128, 112));
		cannons[1] = (Cannon)spawner(new Cannon(48, 48, 128, 112));
		cannons[2] = (Cannon)spawner(new Cannon(176, 176, 128, 112));
		cannons[3] = (Cannon)spawner(new Cannon(48, 176, 128, 112));
		lector = new Sound_lector("res/sound/mus/tutoriel/Boss.wav", Main.save.musicVolume);
		lector.start();
		music = true;
		}else {
			door.Unlock();
			lector = new Sound_lector("res/sound/mus/tutoriel/NoBoss.wav", Main.save.musicVolume);
			lector.start();
			music = true;
		}
		buttons[0] = (Button)spawner(new Button(128, 32, 2, true));
		buttons[1] = (Button)spawner(new Button(128, 192, 2, true));
		buttons[2] = (Button)spawner(new Button(48, 112, 2, true));
		buttons[3] = (Button)spawner(new Button(208, 112, 2, true));
		spawner(new Grille(0, 96));
		spawner(new Grille(0, 112));
		spawner(new Grille(0, 128));
		spawner(new Grille(16, 96));
		spawner(new Grille(16, 112));
		spawner(new Grille(16, 128));

		spawner(new Grille(240, 96));
		spawner(new Grille(240, 112));
		spawner(new Grille(240, 128));
		spawner(new Grille(256, 96));
		spawner(new Grille(256, 112));
		spawner(new Grille(256, 128));
		
		solidList[1][6] = true;
		solidList[1][7] = true;
		solidList[1][8] = true;
		solidList[15][6] = true;
		solidList[15][7] = true;
		solidList[15][8] = true;




		}
	private void stroyCannons() {
		for(Cannon cannon : cannons) cannon.destroy();
	}

	@Override
	public void trueUpdate() {
		if(cunt!=-1)cunt++;
		if(cunt==250)stroyCannons();
		if(!Team.isDuel) {
			if(player.getY()+15 < 8) {
				int[] newCoord = { 48, 176};
				nextLevel(18, newCoord);
			}
		if(player.getY()+31 > (size[1]*16-8)) {
			int[] newCoord = { 112, 0};
			nextLevel(16, newCoord);
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
		if(i == 2 && Main.save.avancement<4) for(Cannon cannon : cannons) cannon.fire();
		if(i==3) {
			cunt++;
			boss.awake();
			inter.startFinghtingBoss(str_Boss1Name, str_Boss1Description, boss);
			solidList[7][14] = true;
			solidList[8][14] = true;
			solidList[9][14] = true;
		}
		if(i==4) {
			door.Unlock();
			if(Main.save.avancement<4)Main.save.avancement=4;
			solidList[7][14] = false;
			solidList[8][14] = false;
			solidList[9][14] = false;
		}
	}

	@Override
	protected void spreadStart(int epetitionNumber) {
		
	}

	


}