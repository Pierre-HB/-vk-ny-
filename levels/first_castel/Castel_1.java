package fr.pierrehb.levels.first_castel;

import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Createur;
import fr.pierrehb.entities.joueurs.Hacker;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.objects.Door;
import fr.pierrehb.entities.villageois.BlueGuard;
import fr.pierrehb.entities.villageois.BlueKing;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.levels.StandartLevel;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Castel_1 extends StandartLevel {

	static int[][] level =	{{1,1,1,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,6,1,1,1,6,1},
							{3,3,3,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,6,3,3,3,6,3},
							{3,3,10,1,11,3,6,3,4,3,6,3,4,3,6,3,4,3,6,3,4,3,6,3,4,3,6,3},
							{3,3,1,1,1,3,6,3,5,3,6,3,5,3,6,3,5,3,6,3,5,3,6,3,5,3,6,3},
							{3,3,1,1,1,3,6,2,2,2,6,2,2,2,6,2,2,2,6,2,2,2,6,2,2,2,6,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,7,7,7,7,7,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1},
							{3,2,2,2,2,2,2,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1},
							{3,2,2,2,2,2,2,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1},
							{3,2,2,2,2,2,2,7,7,7,7,7,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3},
							{3,2,2,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,12,2,2,2,12,3},
							{3,3,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}};
							
	
	static Texture tex = Texture.loadTexture("res/textures/Tile/StartCastel/Sprit1.png");
	static Texture loadtileset1 = Texture.loadTexture("res/textures/Tile/StartCastel/TileSet1.png");
	static Texture loadtileset2 = Texture.loadTexture("res/textures/Tile/StartCastel/TileSet2.png");
	static Texture loadtileset3 = Texture.loadTexture("res/textures/Tile/StartCastel/TileSet3.png");
	static Texture[] tileset = {loadtileset1,loadtileset2,loadtileset3};
	static int[][] WhoToRead = {{1, 2, 7},{4, 3},{4,2,5,2},{2,1,  6,2,  7,0},{2,2  ,2,1  ,6,6  ,6,-1  ,7,7}};
	private Createur createur;
	private Hacker hacker;
	private Human[] guards = new Human[5];
	private int eventIntro = 0;
	public Castel_1() {
		super(tex, tileset, level, WhoToRead);
		

		standartIniTiles();
		if(Main.save.avancement == 1) {
			humanSpawner(new Player(272,0,false, false));
			guards[0] = new BlueKing(182, 112, true, direction.RIGHT, true);
			guards[1] = new BlueGuard(240, 80, true,direction.DOWN, true);
			guards[2] = new BlueGuard(214, 80, true,direction.DOWN, true);
			guards[3] = new BlueGuard(240, 144, true,direction.UP, true);
			guards[4] = new BlueGuard(214, 144, true,direction.UP, true);
			for (Human guard : guards) humanSpawner(guard);

			
			createur = new Createur(400, 112, true,direction.LEFT);
			humanSpawner(createur);
			
			hacker = new Hacker(20, 112, true,direction.LEFT,true);
			humanSpawner(hacker);
			lector = new Sound_lector("res/sound/mus/cinematique/phase1.wav", Main.save.musicVolume);
			lector.start();
			music = true;
		}else {
		humanSpawner(new Player());
		if(Main.save.Sparadrap)humanSpawner(new Sparadrap((int)player.x, (int)player.y));
		humanSpawner(new BlueKing(48, 112));
		humanSpawner(new BlueGuard(16, 128,direction.RIGHT,str_beggeningCastel_guard1));
		humanSpawner(new BlueGuard(80, 128,direction.LEFT,str_beggeningCastel_guard2));
		humanSpawner(new BlueGuard(16, 176,direction.RIGHT,str_beggeningCastel_guard3));
		humanSpawner(new BlueGuard(80, 176,direction.LEFT,str_beggeningCastel_guard4));
		lector = new Sound_lector("res/sound/mus/kïnblû/Castel.wav", Main.save.musicVolume);
		lector.start();
		music = true;
		}
		spawner(new Door(32, 32, false, WHITE));	
	}

	@Override
	public void trueUpdate() {
		if(eventIntro==1)createur.moveX(-1);
		if(eventIntro == 3) {
			guards[0].x-=4;
			guards[1].y-=4;
			guards[2].y-=4;
			guards[3].y+=4;
			guards[4].y+=4;
		}
		if(eventIntro==4)hacker.moveX(1);
		if(eventIntro==11)createur.moveX(1);
		if(eventIntro==12)createur.x-=4;
		if(!Team.isDuel) {
			if(player.getY()+31 > (size[1]*16-8)) {
				int[] newCoord = { 128, 0};
				nextLevel(17, newCoord);
			}
			if(player.getX()+15 < 80 && player.getY() < 56) {
				int[] newCoord = { 48, 112};
				nextLevel(19, newCoord);
			}
			if(player.getX()+15 > (size[0]*16-8)) {
				int[] newCoord = { 8, -24};
				nextLevel(-42, newCoord);
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
		eventIntro=i;
		if(i==6)hacker.sayIntro();
		if(i>6 && i!=11 && i!= 12 && i != 13 && i != 17) {
			createur.sayIntro();
			hacker.sayIntro();
		}
		if(i==11) {
			createur.canMove=true;
		}
		if(i == 12) {
			createur.moveX(-1);
			hacker.sayIntro();
			hacker.sayIntro();
		}
		if(i == 14) {
			createur.writableName = Renderer.getText(0, 0, str_new_name +" :", Main.save.favorite_police, 0.3f);
		}
		if(i == 17)createur.sayIntro();
		
	}

	@Override
	protected void spreadStart(int repetitionNumber) {
		
	}

}
