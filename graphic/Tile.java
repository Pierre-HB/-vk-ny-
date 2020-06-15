package fr.pierrehb.graphic;


import fr.pierrehb.game.Game;
import fr.pierrehb.main.Main;

public class Tile {
	
	public int x, y; // en pixel, PAS EN TUILE
	public int xo, yo; // la place de la texture dans le Sprit
	private boolean solid; // Si la tuite est solide ou pas
	private int nb_tile; // le nombre de tile differente pour faire une annimation
	int idTile;	//l'id de la tuile pour pouvoir la rendre
	int idTileSet;	//l'id des bord de la tuile pour pouvoir les afficher
	boolean hasTileSet;
	public int offset = 16; // la atille de la tuile
	public int halfOffset = offset / 2; // la taille des TilesSet
	int type; // sont type
	float[] color; 
	float[] nb_postion;
	int[] rez =  Main.save.size;
	int[][] tileSet = new int[4][2]; // l'emplacement des TileSet dans l'image
	boolean VNO = false;
	boolean VNE = false;
	boolean VSE = false;
	boolean VSO = false;
	boolean VUP = false;
	boolean VRIGHT = false;
	boolean VDOWN = false;
	boolean VLEFT = false;
	boolean annimated;
	boolean[] tileSetRender = new boolean[4];


	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.solid = false;
		this.type = -1;
		this.hasTileSet = false;
		this.annimated = false;

	}
	public Tile(int x, int y, int tipe, boolean solid, float[] nb_postion, int idTile, int idTileSet, boolean tileSet, boolean annime, int nb_tile) {
		this.x = x;
		this.y = y;
		this.nb_postion = nb_postion;
		this.solid = solid;
		this.type = tipe -1;
		this.xo = type%(int)nb_postion[0];
		this.yo = (type-xo)/(int)nb_postion[0];
		this.idTile = idTile;
		this.idTileSet = idTileSet;
		this.hasTileSet = tileSet;
		this.annimated = annime;
		this.nb_tile = nb_tile;

	}
	public Tile(int x, int y, int tipe, boolean solid, float[] nb_postion, int idTile, boolean tileSet, boolean annime, int nb_tile) {
		this.x = x;
		this.y = y;
		this.nb_postion = nb_postion;
		this.solid = solid;
		this.type = tipe -1;
		this.xo = type%(int)nb_postion[0];
		this.yo = (type-xo)/(int)nb_postion[0];
		this.idTile = idTile;
		this.hasTileSet = tileSet;
		this.annimated = annime;
		this.nb_tile = nb_tile;
	}
	public boolean getSolid() {
		return solid;
	}
	public void setTileSet(int UP, int RIGHT, int DOWN, int LEFT, int UPLE, int UPRI, int DORI, int DOLE, int[] same) {
		
		if(hasTileSet) {
		for(int meme : same) {
			if(UP == meme) 	VUP = true;
			if(RIGHT == meme) VRIGHT = true;
			if(DOWN == meme) VDOWN = true;
			if(LEFT == meme) VLEFT = true;
		}
		for(int meme2 : same) {
			if(UPLE == meme2 && VUP && VLEFT) VNO = true;
			if(UPRI == meme2 && VUP && VRIGHT) VNE = true;
			if(DORI == meme2 && VDOWN && VRIGHT) VSE = true;
			if(DOLE == meme2 && VDOWN && VLEFT) VSO = true;
			
			
		}


	//		if(!VNO && !VNE && !VSE && !VSO && !VUP && !VRIGHT && !VDOWN && !VLEFT) {

		if(VNO && VNE && VSE && VSO && VUP && VRIGHT && VDOWN && VLEFT) {
			hasTileSet = false;
		}
		if(hasTileSet) {
			tileSetRender[0] = tileSetRender[1] = tileSetRender[2] = tileSetRender[3] = true;
			
			
	
			
			
			
		
			if(!VNO) {
				if(VUP){
					tileSet[0][1] = 2;			
					tileSet[1][1] = 2;
					}else{
					tileSet[0][1] = 0;			
					tileSet[1][1] = 0;
					}
				
				if(VLEFT){
					tileSet[3][0] = 2;
					tileSet[0][0] = 2;			
					}else{
					tileSet[3][0] = 0;
					tileSet[0][0] = 0;			
					}
			}else {
				tileSetRender[0] = false;
				tileSet[0][0] = tileSet[0][1] = 0;
			}
			if(!VNE) {
				if(VUP) {
				tileSet[0][1] = 2;			
				tileSet[1][1] = 2;
				}else{
				tileSet[0][1] = 0;			
				tileSet[1][1] = 0;
				}
			if(VRIGHT){
				tileSet[1][0] = 3;
				tileSet[2][0] = 3;
				}else{
				tileSet[1][0] = 1;
				tileSet[2][0] = 1;
				}
			}else {
				tileSetRender[1] = false;
				tileSet[1][0] = tileSet[1][1] = 0;
			}
		
		if(!VSE) {
			if(VDOWN){
				tileSet[2][1] = 3;
				tileSet[3][1] = 3;
				}else{
				tileSet[2][1] = 1;
				tileSet[3][1] = 1;
				}
			if(VRIGHT){
				tileSet[1][0] = 3;
				tileSet[2][0] = 3;
				}else{
				tileSet[1][0] = 1;
				tileSet[2][0] = 1;
				}
		}else {
			tileSetRender[2] = false;
			tileSet[2][0] = tileSet[2][1] = 0;
		}
		
		if(!VSO) {
			if(VDOWN){
				tileSet[2][1] = 3;
				tileSet[3][1] = 3;
				}else{
				tileSet[2][1] = 1;
				tileSet[3][1] = 1;
				}
			if(VLEFT){
				tileSet[3][0] = 2;
				tileSet[0][0] = 2;			
				}else{
				tileSet[3][0] = 0;
				tileSet[0][0] = 0;			
				}
		}else {
			tileSetRender[3] = false;
			tileSet[3][0] = tileSet[3][1] = 0;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		} else {
			tileSet[0][0]=tileSet[0][1]=tileSet[1][0]=tileSet[0][1]=tileSet[2][0]=tileSet[2][1]=tileSet[3][0]=tileSet[3][1]=0;
			tileSetRender[0] = tileSetRender[1] = tileSetRender[2] = tileSetRender[3] = false;
		}
		}}



	
	public void render() {
		if(type != -1) {
			int tx= (int)Game.translateView[0];
			int ty = (int)Game.translateView[1];
			int x2 = x*rez[0]/16;
			int y2 = y*rez[1]/9;
			// On vériçfie que la Tile apparait bien sur l'écran, qu'elle n'est pas hor champ avant de l'afficher

			if(x2 < -tx-rez[0]/16 || y2 < -ty-rez[1]/9  || x2 > -tx+rez[0] || y2 > -ty+rez[1]) return;

		
			if(annimated) {
				if(hasTileSet) 	Renderer.renderTile(x, y, xo, (yo + Annimation.getTileLine(nb_tile)), nb_postion, idTile, idTileSet, tileSet, tileSetRender);
				else Renderer.renderTile(x, y, xo, (yo + Annimation.getTileLine(nb_tile)), nb_postion, idTile);
			}else {	
				if(hasTileSet) 	Renderer.renderTile(x, y, xo, yo, nb_postion, idTile, idTileSet, tileSet, tileSetRender);
				else Renderer.renderTile(x, y, xo, yo, nb_postion, idTile);
			}
		}
	}
}