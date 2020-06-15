package fr.pierrehb.levels;

import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.ennemis.Rafter;
import fr.pierrehb.entities.ennemis.Warrior;
import fr.pierrehb.entities.joueurs.Sparadrap;
import fr.pierrehb.entities.objects.Grille;
import fr.pierrehb.game.Team;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Level_3 extends StandartLevel {

static final int[][] level ={{3,12,3,12,12,12,3,3,12,3,3,3,12,12,3,3,3,3,12,3,3,3,3,3,12,3,3,3,3,12,12,3,3,3,3,3,12,3,3,3,12,12,12,3,3,12,3,3,3,3,12,12,3,3,3,12,3,12,3,12,12,3,3,3},
							{3,12,3,12,12,12,3,3,12,3,3,3,12,12,3,3,3,3,12,3,3,3,3,3,12,3,3,3,3,12,12,3,3,3,3,3,12,3,3,3,12,12,12,3,3,12,3,3,3,3,12,12,3,3,3,12,3,12,3,12,12,3,3,3},
							{3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3},
							{3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3},
							{3,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
							{3,4,4,2,2,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,5,6,6,6,6,7,3,3,3,3},
							{3,4,4,2,2,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,4,4,4,4,4,4,3,3,3,3},
							{3,4,4,2,2,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3},
							{3,4,4,1,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3}};



static final Texture tex = Texture.loadTexture("res/textures/Tile/Underground/Sprit1.png");
static final Texture loadtileset = Texture.loadTexture("res/textures/Tile/Underground/TileSet1.png");
static final Texture[] tileset = {loadtileset};
static final int[][] WhoToRead = {{1, 2},{4, 4},{12,2, 4,2,  5,2,  6,2,  7,2},{1,0,  2,0,  3,0},{1,-1,  1,1, 1,2, 1,3, 2,2  ,2,3  ,2,1   ,2,5   ,2,6   ,2,7  ,3,12 ,3,3  ,3,-1  ,3,1}};
static final int[][] ennemys = {{0,900,48},{1,870,64},{1,870,64},{0,700,56},{1,670,64},{1,670,48},{0,500,64},{0,500,48},{1,470,56},{0,300,64},{0,300,48},{0,270,56},{1,100,56},{1,70,64},{1,70,48}};
static final int repetition = ennemys.length;

public Level_3() {
super(tex, tileset, level, WhoToRead);
standartIniTiles();



humanSpawner(new Player());
if(Main.save.Sparadrap && Main.save.avancement>2)Team.askForParty(humanSpawner(new Sparadrap((int)player.x, (int)player.y)), player);

if(Main.save.avancement<3) {
	spawner(new Grille(48,144));
	spawner(new Grille(64,144));
	solidList[3][9] = true;
	solidList[4][9] = true;

}





humanSpawner(new Warrior(700, 56));
humanSpawner(new Rafter(670, 64));
humanSpawner(new Rafter(670, 48));


humanSpawner(new Warrior(500, 64));
humanSpawner(new Warrior(500, 48));
humanSpawner(new Rafter(470, 56));


humanSpawner(new Warrior(300, 64));
humanSpawner(new Warrior(300, 48));
humanSpawner(new Warrior(270, 56));


humanSpawner(new Rafter(100, 56));
humanSpawner(new Rafter(70, 64));
humanSpawner(new Rafter(70, 48));












lector = new Sound_lector("res/sound/mus/tutoriel/Coridor.wav", Main.save.musicVolume);
lector.start();
music = true;
}


@Override
public void trueUpdate() {
	if(!Team.isDuel) {
if(player.getX()+16 > size[0]*16-8) {
int[] newCoord = { 16, 64};
nextLevel(1, newCoord);
}
if(player.getY()+32 > (size[1]*16-8)) {
	int[] newCoord = { 200, 0};
	nextLevel(14, newCoord);
}}
}

@Override
public void trueRender() {
}


@Override
public void interact() {standartInteract();

	
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
	int rang = repetition-repetitionNumber;
	if(ennemys[rang][0]==0) humanSpawner(new Warrior(ennemys[rang][1], ennemys[rang][2]));
	else humanSpawner(new Rafter(ennemys[rang][1], ennemys[rang][2]));

}





}