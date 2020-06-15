package fr.pierrehb.levels;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Mortal_Particle;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.boss.Boss;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.game.Game;
import fr.pierrehb.game.Team;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Alpha_End_Interface;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Tile;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;



public abstract class StandartLevel extends Level{
	
	protected Texture[] tileSetTexture;
	protected int[] downLevel;
	protected int[] upLevel;
	protected int[][] tiles;
	protected boolean playing;
	protected List<Tile> tilesList = new ArrayList<Tile>();
	protected List<Entity> entities = new ArrayList<Entity>();



	protected int[][] whoToRead;

	protected float[] translateView = new float[2];
	protected boolean music = false;
	protected Sound_lector lector;
	protected Texture texture;
	protected boolean translate_view = true;
	protected boolean INTERFACE = true;
	
	protected Entity interactor;
	protected boolean interacting = false;
	public Interface inter = new Interface();
	protected Alpha_End_Interface otherInterface;
	protected boolean OTHERINTERFACE = false;
	protected boolean SPECIALDECONEXION = false;
	private int spreadStart=0;
	private int spreadStartEnd=0;
	private int spreadStartDelay=0;



	@Override
	public Interface getInter() {
		return inter;
	}
	@Override
	public void askForParty(Joueur demandeur) {
		
	}
	public StandartLevel(Texture texture, Texture[] tiletettex, int[][] level, int[][] whotoread) {
		super();
		this.texture = texture;
		this.size[0] = level[0].length;
		this.size[1] = level.length;
		this.tiles = level;
		this.tileSetTexture = tiletettex;
		/*	Who To Read:
		 * 
		 * 	whoToRead = {{ liste des "non-solide" Tile ( donner les type des non solide Tile)},
		 * 				{nb de texture par ligne PUIS colone ex; {10,5} = 10 tuile par ligne te 5 tuile par colone}
		 * 				{liste des Tile annime (lister: leur type, le nombre d'annimation)}
		 * 				{Liste des id des TileSet par type ex: { (le type) 3, 2 (id de la texture de sont TileSet) , (le type )4, 6(l'id de la texture de son TileSet)}
		 * 				{liste des tuile avec qui on NE FAIT PAS de TileSet ex: { (le type)2, 1,   2,2     2,3} le type 2 ne fera donc pas de TileSet avec le type 1, 2 et 3... Par contre il en fera avec les type -1, 4, 5 ...}}
		 *  
		 */
		this.whoToRead = whotoread;
		solidList = new boolean[size[0]][size[1]];
		inter = new Interface();

		
	}
	protected void spreadStartIni(int delay, int repetition) {
		this.spreadStartDelay=delay;
		this.spreadStartEnd=repetition;
		
	}
	protected abstract void spreadStart(int epetitionNumber);
	@Override
	public void startLevel() {
		for(Entity entity : entities) {
			entity.start();
		}
		for(Entity entity : EntityForSummon) {
			spawner(entity);
		}
	}
	@Override
	public Entity spawner(Entity entity) {
		entities.add(entity);
		if(entity.mortal) mortalObjects.put(entity.mortalID, entity);
		return entity;
	}
	public Human humanSpawner(Human human) {
		human.setMouse(human);
		humans.add(human);
		spawner(human);
		if(human.getClass().getName() == Player.class.getName()) {
			player = (Player)human;
			if(player.HP<=0)player.HP=5;

		}
		

		return human;
	}
	public Boss bossSpawner(Boss boss) {
		boss.setMouse(boss);
		spawner(boss);
		return boss;
	}
	@Override
	public boolean giveDamageSquare(Quadrilataire hitBox, Entity attacker, int damage) {
		boolean giveDamage = false;
		Point P1, P2, P3, P4;
		Quadrilataire hitBoxAdvers;
		

		for(Entity entity : mortalObjects.values() ) {
			if(entity.team != attacker.team) {
				P1 = new Point(entity.hitBox[0], entity.hitBox[1]);
				P2 = new Point(entity.hitBox[0], entity.hitBox[3]);
				P3 = new Point(entity.hitBox[2], entity.hitBox[3]);
				P4 = new Point(entity.hitBox[2], entity.hitBox[1]);
				hitBoxAdvers = new Quadrilataire(P1, P2, P3, P4);
				if(hitBox.isON(hitBoxAdvers) || hitBox.isON(P1) || hitBox.isON(P2) || hitBox.isON(P3) || hitBox.isON(P4) ||
						hitBoxAdvers.isON(hitBox.p1) || hitBoxAdvers.isON(hitBox.p2) || hitBoxAdvers.isON(hitBox.p3) || hitBoxAdvers.isON(hitBox.p4)) {
					entity.getDamage(damage, attacker);
					giveDamage = true;
				}
			
			}}
		for(Mortal_Particle particle : mortalParticles.values() ) {
			if(particle.joueur.team != attacker.team) {
			
				hitBoxAdvers = particle.square;

				if(hitBox.isON(hitBoxAdvers) || hitBox.isON(particle.p1) || hitBox.isON(particle.p2) || hitBox.isON(particle.p3) || hitBox.isON(particle.p4) ||
						hitBoxAdvers.isON(hitBox.p1) || hitBoxAdvers.isON(hitBox.p2) || hitBoxAdvers.isON(hitBox.p3) || hitBoxAdvers.isON(hitBox.p4)) {
					particle.getDamage(damage);
				}
			
			}}
		return giveDamage;
	}
	@Override
	public boolean giveHeal(int[] hitBox, Entity attacker, int heal) {
		boolean giveHeal = false;

		for(Entity entity : mortalObjects.values() ) {
			if(entity.team == attacker.team && entity.mortalID != attacker.mortalID) {
			
			if(((hitBox[0]<entity.hitBox[0] && hitBox[2]>entity.hitBox[0]) && (hitBox[1]<entity.hitBox[1] && hitBox[3]>entity.hitBox[1])) ||
				((hitBox[0]<entity.hitBox[2] && hitBox[2]>entity.hitBox[2]) && (hitBox[1]<entity.hitBox[1] && hitBox[3]>entity.hitBox[1])) ||
				((hitBox[0]<entity.hitBox[2] && hitBox[2]>entity.hitBox[2]) && (hitBox[1]<entity.hitBox[3] && hitBox[3]>entity.hitBox[3])) ||
				((hitBox[0]<entity.hitBox[0] && hitBox[2]>entity.hitBox[0]) && (hitBox[1]<entity.hitBox[3] && hitBox[3]>entity.hitBox[3])) ||
				((entity.hitBox[0]<hitBox[0] && entity.hitBox[2]>hitBox[0]) && (entity.hitBox[1]<hitBox[1] && entity.hitBox[3]>hitBox[1])) ||
				((entity.hitBox[0]<hitBox[2] && entity.hitBox[2]>hitBox[2]) && (entity.hitBox[1]<hitBox[1] && entity.hitBox[3]>hitBox[1])) ||
				((entity.hitBox[0]<hitBox[2] && entity.hitBox[2]>hitBox[2]) && (entity.hitBox[1]<hitBox[3] && entity.hitBox[3]>hitBox[3])) ||
				((entity.hitBox[0]<hitBox[0] && entity.hitBox[2]>hitBox[0]) && (entity.hitBox[1]<hitBox[3] && entity.hitBox[3]>hitBox[3]))) {
				if(entity.HP<entity.getMaxHP()) {
				entity.getHeal(heal);
				giveHeal = true;
			}}}}
		return giveHeal;
	}
	public void standartIniTiles() {
		for(int x = 0; x < size[0]; x ++) {
			for(int y = 0; y< size[1]; y++) {
				int type = tiles[y][x];
				boolean solid = true;
				boolean hastileset = false;
				boolean annimated = false;
				int idTileSet = 0;
				int nb_tile = 1;
				Tile tile;
				int UP = -1;
				int RIGHT = -1;
				int DOWN = -1;
				int LEFT = -1;
				int UPLE = -1;
				int UPRI = -1;
				int DORI = -1;
				int DOLE = -1;
				float[] nb_positions = {whoToRead[1][0], whoToRead[1][1]};
				if(type == 0) {
					tile = new Tile(x, y);
					tilesList.add(tile);
					solidList[x][y] = false;
				}else {
				for(int tipe : whoToRead[0]) if(tipe == type) solid = false;
	
				for(int s = 0; s < whoToRead[2].length; s +=2) {
					if(type == whoToRead[2][s]) {
						annimated = true;
						nb_tile = whoToRead[2][s+1];
					}}				
				for(int z = 0; z < whoToRead[3].length; z +=2) {
					if(whoToRead[3][z] == type) {
						idTileSet = tileSetTexture[whoToRead[3][z + 1]].getid();
						hastileset = true;
					}
				}
				
				
				if(hastileset) {
					int a = 0;
					for(int z = 0; z < whoToRead[4].length; z +=2) if(whoToRead[4][z] == type) a++;
					
					int[] same = new int[a+1];
					a = 0;
					for(int z = 0; z < whoToRead[4].length; z +=2) {
						if(whoToRead[4][z] == type) {
							same[a] = whoToRead[4][z + 1];
							a++;
						}}
					same[a] = 0;


					if( y - 1 >= 0) UP = tiles[y-1][x];
					if( (x + 1) < (size[0])) RIGHT = tiles[y][x+1];
					if( y + 1 < size[1]) DOWN = tiles[y+1][x];
					if( x - 1 >= 0) LEFT = tiles[y][x-1];
					
					if(x-1 >= 0 && y - 1 >= 0) UPLE = tiles[y-1][x-1];
					if(x+1 < size[0] && y - 1 >= 0) UPRI = tiles[y-1][x+1];
					if(x+1 < size[0] && y + 1 < size[1]) DORI = tiles[y+1][x+1];
					if(x-1 >= 0 && y + 1 < size[1]) DOLE = tiles[y+1][x-1];
					
					tile = new Tile(x, y, type, solid, nb_positions, texture.getid(), idTileSet, hastileset, annimated, nb_tile);
					tile.setTileSet(UP, RIGHT, DOWN, LEFT, UPLE, UPRI, DORI, DOLE, same);
				}
				else tile = new Tile(x, y, type, solid, nb_positions, texture.getid(), hastileset, annimated, nb_tile);
				tilesList.add(tile);
				solidList[x][y] = solid;
				}
			}
		}
	}
@Override
public void update() {
	if(spreadStartEnd>0) {
		spreadStart++;
		if(spreadStart>=spreadStartDelay) {
			spreadStart=0;
			spreadStart(spreadStartEnd);
			spreadStartEnd--;
			
		}
	}
	
	
if(STANDART_UPDATE) {

	Annimation.update();
	for(Entity entity : entities) entity.update();

	if(music) {
		if(lector.getState() == State.TERMINATED) {
		lector = new Sound_lector(lector.getSong(), Main.save.musicVolume);
		lector.start();
		}
	}
	if (haveFocus) {
		focus.update();
	}
	Team.update();
	if(INTERFACE)inter.update();
	if(OTHERINTERFACE)otherInterface.update();
}else player.standartPlayerActions();
trueUpdate();
for(Particle particle : particleList.values())particle.update();
if(!ParticleForSummon.isEmpty()) {
	for(Particle particle : ParticleForSummon) {
		addParticle(particle);
		
}
	ParticleForSummon.clear();
}
if(!removeParticles.isEmpty()) {
	for(Integer id : removeParticles) {
		try {
		if(particleList.get(id).mortal) {
			
			mortalParticles.remove(id);
			}				
			}catch (NullPointerException e) {
		}
		try {
		particleList.remove(id);
		}catch (NullPointerException e) {				
		}
	
	}
	removeParticles.clear();
}
}
@Override
public void render() {
if(STANDART_RENDER) {
	for(Tile tile : tilesList) tile.render();

	
	Entity[] entitys = new Entity[entities.size()];
	for(int f = 0; f < entities.size(); f++) {
		entitys[f] = entities.get(f);
	}
	Arrays.sort(entitys);
	for( Entity entit : Arrays.asList(entitys)) {
		entit.render();
	}

}
trueRender();
for(Particle particle : particleList.values())particle.render();
if(INTERFACE)inter.render();
if(OTHERINTERFACE)otherInterface.render();
for(Entity entit : entities) entit.dialogueRender();

}
public abstract void trueRender();
public abstract void trueUpdate();
	public void standartRender() {
		

		for(Tile tile : tilesList) tile.render();

		
		Entity[] entitys = new Entity[entities.size()];
		for(int f = 0; f < entities.size(); f++) {
			entitys[f] = entities.get(f);
		}
		Arrays.sort(entitys);
		for( Entity entit : Arrays.asList(entitys)) {
			entit.render();
		}

	if(INTERFACE)inter.render();
	for(Entity entit : entities) entit.dialogueRender();
	}
	@Override
	public float[] translateView() {
		if(translate_view) {
		

		if (haveFocus) {
			translateView[0] = -focus.getX()*rez[0]/256+rez[0]/2;
			translateView[1] = -focus.getY()*rez[1]/144+rez[1]/2;
		}else if(haveTarget) {
			float X = ((player.getX()+Human.taille[0]/2)+(target.getX()+target.taille[0]/2))/2;
			float Y = ((player.getY()+Human.taille[1]/2)+(target.getY()+target.taille[1]/2))/2;
			
			if(X-target.getX() > 128) X = target.getX()-128;
			if(target.getX()-(X+16) > 128) X = target.getX()+128+16;
			
			if(Y-target.getY() > 77) Y = target.getY()+77;
			if(target.getY()-(Y+16) > 77) Y = target.getY()-77-16;

			
			translateView[0] = -(float)(X*rez[0]/256) +rez[0]/2;
			translateView[1] = -(float)(Y*rez[1]/144) +rez[1]/2;
		}else {
		translateView[0] = -(float)(player.getX()+Human.taille[0]/2)*rez[0]/256 +rez[0]/2;
		translateView[1] = -(float)(player.getY()+Human.taille[1]/2)*rez[1]/144 +rez[1]/2;
		}
		return translateView;
		}else {
		float[] i = {0,0};
		return i;
	}
		}
	@Override
	public boolean getSolid(int x, int y) {	
		return solidList[x][y];
	}
	@Override
	@SuppressWarnings("deprecation")
	public void stopMusic() {
		if(music)lector.stop();
	}
	
	public void nextLevel(int room, int[] newCoordinates) {
		if(music) {
			stopMusic();
		}
		Main.save.playerHP = player.HP;
		Main.save.room = room;
		Main.save.coordinates = newCoordinates;
		Main.save.playerInventory=player.getInventory();
		Main.save.playerArmor=player.getArmor();
		Main.save.playerWeapon=player.getWeapon();
		Main.save.playergold=player.getGold();
		Main.save.playerXP=player.xpgetAllXP();
		Main.save();
		Game.nexLevel();
	}

	@Override
	public void save() {
		Main.save.playerHP = player.HP;
		int[] position = {(int)player.getX(), (int)player.getY()};

		if(!SPECIALDECONEXION)Main.save.coordinates = position;
		Main.save.playerInventory=player.getInventory();
		Main.save.playerArmor=player.getArmor();
		Main.save.playerWeapon=player.getWeapon();
		Main.save.playergold=player.getGold();
		Main.save.playerXP=player.xpgetAllXP();
		Main.save();
		
	}
	@Override
	public void loadingConsol() {
		if(music) stopMusic();
		Main.save.playerInventory=player.getInventory();
		Main.save.playerArmor=player.getArmor();
		Main.save.playerWeapon=player.getWeapon();
		Main.save.playergold=player.getGold();
		Main.save.playerXP=player.xpgetAllXP();
		Game.nexLevel();
	}
	public void nextLevel() {
		if(music) stopMusic();
		if(Main.save.playerHP <= 0) Main.save.playerHP = 5;
		Main.save();
		Game.nexLevel();


	}
	protected void standartInteract() {
		if (interacting) {
			interacting = interactor.interact();
		}else {
		for( Entity ent : entities) {
			if (ent.getName() != Main.getSave().name && ent.interact()) {
				interactor = ent;
				interacting = true;
				return;}
		}}
	}
	@Override
	public void giveXpGold(int xp, int gold) {
		for (Human homme : humans) {
			homme.addgold(gold);
			homme.addXP(xp);
		}
	}
}
