package fr.pierrehb.graphic;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Caracteres;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Polices;


public class Renderer implements Color, Caracteres, Polices {
	static int[] size = Main.save.size;
	static float[] tileProportion = {size[0] / 16 , size[1] / 9};
	private static float[] headSprite = {5.0f, 16.0f};
	private static int[] headTaille = {32, 32};
	private static int CORX = 8;// Corréction a apportet pour que l'image soit centrer sur le centre du player
	private static int CORY = 16;
	private static float[] playerCoord = {0,0};
	
	public static void renderUpdate() {
		int rez[] = Main.save.size;
		playerCoord[0] = -((Game.translateView[0]-(rez[0]/2))*256/rez[0]+8);
		playerCoord[1] = -((Game.translateView[1]-(rez[1]/2))*144/rez[1]+16);

	}
	public static void reload() {
		size = Main.save.size;
		tileProportion[0] = size[0]/16;
		tileProportion[1] = size[1]/9;
	}
	public static void renderQuad(int x, int y, int xo, int yo, int offsetX, int offsetY, float[] size, float[] color) {
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f( (0 + xo) / size[0], (0 + yo ) / size[1]); glVertex2f(x, y);
		glTexCoord2f( (1 + xo) / size[0], (0 + yo ) / size[1]); glVertex2f(x + offsetX, y);
		glTexCoord2f( (1 + xo) / size[0], (1 + yo ) / size[1]); glVertex2f(x + offsetX, y + offsetY);
		glTexCoord2f( (0 + xo) / size[0], (1 + yo ) / size[1]); glVertex2f(x, y + offsetY);	
		glEnd();
	}
	public static void renderHuman(int x, int y, int xo, int yo, float[] nb_texture,int id ,  float[] color) {
		glBindTexture(GL_TEXTURE_2D, id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]); glVertex2f(x  * size[0]/256, (y) * size[1]/144);
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]); glVertex2f((x + 16) * size[0]/256, (y) * size[1]/144);
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]); glVertex2f((x + 16) * size[0]/256, (y + 32) * size[1]/144);
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]); glVertex2f(x  * size[0]/256, (y + 32 )* size[1]/144);	
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);

		
	}
	public static void renderCursor(float x, float y, int id,  float[] color) {
		glBindTexture(GL_TEXTURE_2D, id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f( 0, 0); glVertex2f(x  * size[0]/256, (y - 8) * size[1]/144);
		glTexCoord2f( 1, 0); glVertex2f((x + 16) * size[0]/256, (y - 8) * size[1]/144);
		glTexCoord2f( 1, 1); glVertex2f((x + 16) * size[0]/256, (y) * size[1]/144);
		glTexCoord2f( 0, 1); glVertex2f(x  * size[0]/256, (y)* size[1]/144);	
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);

		
	}
	public static void renderObject(int x, int y, int xo, int yo, float[] dimension, int[] taille, float[] color) {

		 
		  glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f( (0 + xo) / dimension[0], (0 + yo ) / dimension[1]); glVertex2f(x  * size[0]/256, y * size[1]/144);
		glTexCoord2f( (1 + xo) / dimension[0], (0 + yo ) / dimension[1]); glVertex2f((x + taille[0]) * size[0]/256, y * size[1]/144);
		glTexCoord2f( (1 + xo) / dimension[0], (1 + yo ) / dimension[1]); glVertex2f((x + taille[0]) * size[0]/256, (y + taille[1]) * size[1]/144);
		glTexCoord2f( (0 + xo) / dimension[0], (1 + yo ) / dimension[1]); glVertex2f(x  * size[0]/256, (y + taille[1] )* size[1]/144);	
		glEnd();
		  
		 
	}
	public static void renderQuad(int x, int y, float[] color) {
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x * tileProportion[0], y * tileProportion[1]);		
		glVertex2f((x + 1) * tileProportion[0], y * tileProportion[1]);		
		glVertex2f((x + 1) * tileProportion[0], (y + 1) * tileProportion[1]);		
		glVertex2f(x * tileProportion[0], (y + 1) * tileProportion[1]);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}
	public static void renderPixel(float x, float y, float xo, float yo) {
		glBegin(GL_QUADS);
		glVertex2f(x * (float)size[0]/256.0f, y * (float)size[1]/144f);		
		glVertex2f((xo) * (float)size[0]/256.0f, y * (float)size[1]/144f);		
		glVertex2f((xo) * (float)size[0]/256.0f, (yo) * (float)size[1]/144f);		
		glVertex2f(x * (float)size[0]/256.0f, (yo) * (float)size[1]/144f);
		glEnd();
	}
	public static void renderPixel(float x, float y, float[] color) {
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x * (float)size[0]/256.0f, y * (float)size[1]/144f);		
		glVertex2f((x+1) * (float)size[0]/256.0f, y * (float)size[1]/144f);		
		glVertex2f((x+1) * (float)size[0]/256.0f, (y+1) * (float)size[1]/144f);		
		glVertex2f(x * (float)size[0]/256.0f, (y+1) * (float)size[1]/144f);
		glEnd();
	}
	public static void renderPixel(Point p1, Point p2, Point p3, Point p4, float[] color) {
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(p1.x * (float)size[0]/256.0f, p1.y * (float)size[1]/144f);		
		glVertex2f(p2.x * (float)size[0]/256.0f, p2.y * (float)size[1]/144f);		
		glVertex2f(p3.x * (float)size[0]/256.0f, p3.y * (float)size[1]/144f);		
		glVertex2f(p4.x * (float)size[0]/256.0f, p4.y * (float)size[1]/144f);
		glEnd();
	}
	public static void renderPixel(Point p1, Point p2, Point p3, Point p4, Texture texture, int xo, int yo, float[] nb_texture, float[] color) {
		glBindTexture(GL_TEXTURE_2D, texture.id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(p1.x * (float)size[0]/256.0f, p1.y * (float)size[1]/144f);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(p2.x * (float)size[0]/256.0f, p2.y * (float)size[1]/144f);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(p3.x * (float)size[0]/256.0f, p3.y * (float)size[1]/144f);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(p4.x * (float)size[0]/256.0f, p4.y * (float)size[1]/144f);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	public static void renderPixel2(float x, float y, float xo, float yo) {
		glBegin(GL_QUADS);
		glVertex2f(x, y);		
		glVertex2f(xo, y);		
		glVertex2f(xo, yo);		
		glVertex2f(x, yo);
		glEnd();
	}
	public static void renderTile(int x, int y, int xo, int yo, float[] nb_texture, float[] color) {
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], (y + 1) * tileProportion[1]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], (y + 1) * tileProportion[1]);
		glEnd();
	}
	public static void renderTile(int x, int y, int xo, int yo, float[] nb_texture, int idTile) {
		glBindTexture(GL_TEXTURE_2D, idTile);
		glBegin(GL_QUADS);
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], (y + 1) * tileProportion[1]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], (y + 1) * tileProportion[1]);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	public static void renderHead(int x, int y, int xo, int yo, float[] nb_texture, int[] taille, int idTile) {
		glBindTexture(GL_TEXTURE_2D, idTile);
		glBegin(GL_QUADS);

		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(x * size[0]/256, y * size[1]/144);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f((x + taille[0]) * size[0]/256, y * size[1]/144);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f((x + taille[0]) * size[0]/256, (y + taille[1]) * size[1]/144);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(x * size[0]/256, (y + taille[1]) * size[1]/144);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	public static void renderTile(int x, int y, int xo, int yo, float[] nb_texture, int idTile, int idTileSet, int[][] tileset, boolean[] tileSetRender) {
		glBindTexture(GL_TEXTURE_2D, idTile);
		glBegin(GL_QUADS);
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (0 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f((x + 1) * tileProportion[0], (y + 1) * tileProportion[1]);		
		glTexCoord2f( (0 + xo) / nb_texture[0], (1 + yo ) / nb_texture[1]);glVertex2f(x * tileProportion[0], (y + 1) * tileProportion[1]);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		glBindTexture(GL_TEXTURE_2D, idTileSet);
		
		if(tileSetRender[0]) {
		glBegin(GL_QUADS);

		glTexCoord2f( (0 + tileset[0][0]) / 4.0f, (0 + tileset[0][1] ) / 4.0f);glVertex2f(x * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[0][0]) / 4.0f, (0 + tileset[0][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[0][0]) / 4.0f, (1 + tileset[0][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (0 + tileset[0][0]) / 4.0f, (1 + tileset[0][1] ) / 4.0f);glVertex2f(x * tileProportion[0], (y + 0.5f) * tileProportion[1]);
		glEnd();
		}
		
		if(tileSetRender[1]) {
		glBegin(GL_QUADS);

		glTexCoord2f( (0 + tileset[1][0]) / 4.0f, (0 + tileset[1][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[1][0]) / 4.0f, (0 + tileset[1][1] ) / 4.0f);glVertex2f((x + 1.0f) * tileProportion[0], y * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[1][0]) / 4.0f, (1 + tileset[1][1] ) / 4.0f);glVertex2f((x + 1.0f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (0 + tileset[1][0]) / 4.0f, (1 + tileset[1][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);
		glEnd();
		}
		
		if(tileSetRender[2]) {
		glBegin(GL_QUADS);

		glTexCoord2f( (0 + tileset[2][0]) / 4.0f, (0 + tileset[2][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[2][0]) / 4.0f, (0 + tileset[2][1] ) / 4.0f);glVertex2f((x + 1.0f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[2][0]) / 4.0f, (1 + tileset[2][1] ) / 4.0f);glVertex2f((x + 1.0f) * tileProportion[0], (y + 1.0f) * tileProportion[1]);		
		glTexCoord2f( (0 + tileset[2][0]) / 4.0f, (1 + tileset[2][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 1.0f) * tileProportion[1]);
		glEnd();
		}
		
		if(tileSetRender[3]) {
		glBegin(GL_QUADS);

		glTexCoord2f( (0 + tileset[3][0]) / 4.0f, (0 + tileset[3][1] ) / 4.0f);glVertex2f(x * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[3][0]) / 4.0f, (0 + tileset[3][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 0.5f) * tileProportion[1]);		
		glTexCoord2f( (1 + tileset[3][0]) / 4.0f, (1 + tileset[3][1] ) / 4.0f);glVertex2f((x + 0.5f) * tileProportion[0], (y + 1.0f) * tileProportion[1]);		
		glTexCoord2f( (0 + tileset[3][0]) / 4.0f, (1 + tileset[3][1] ) / 4.0f);glVertex2f(x * tileProportion[0], (y + 1.0f) * tileProportion[1]);
		glEnd();
		}
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	public static void renderQuad(float x1, float y1, float x2, float y2, float[] color) { // TEST
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x1 * size[0], y1 * size[1]);		
		glVertex2f(x2 * size[0], y1 * size[1]);		
		glVertex2f(x2 * size[0], y2 * size[1]);		
		glVertex2f(x1 * size[0], y2 * size[1]);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}
	public static void renderSquare(float x1, float x2, float x3, float x4, float y1, float y2, float y3, float y4, float[] color) { // TEST
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x1 * size[0]/256, y1 * size[1]/144);		
		glVertex2f(x2 * size[0]/256, y2 * size[1]/144);		
		glVertex2f(x3 * size[0]/256, y3 * size[1]/144);		
		glVertex2f(x4 * size[0]/256, y4 * size[1]/144);
		glEnd();
	}public static void renderSquare(float x1, float x2, float y1, float y2, float[] color) { // TEST
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x1 * size[0]/256, y1 * size[1]/144);		
		glVertex2f(x2 * size[0]/256, y1 * size[1]/144);		
		glVertex2f(x2 * size[0]/256, y2 * size[1]/144);		
		glVertex2f(x1 * size[0]/256, y2 * size[1]/144);
		glEnd();
	}
	public static void renderQuad2(float x1, float y1, float x2, float y2, float[] color) { // TEST
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x1, y1);		
		glVertex2f(x2, y1);		
		glVertex2f(x2, y2);		
		glVertex2f(x1, y2);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);


	}public static void renderQuad(int x1, int y1, int x2, int y2, float[] color) { 
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f(x1*size[0]/256, y1*size[1]/144);		
		glVertex2f(x2*size[0]/256, y1*size[1]/144);		
		glVertex2f(x2*size[0]/256, y2*size[1]/144);		
		glVertex2f(x1*size[0]/256, y2*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}
	public static void renderInterface(float x1, float y1, float x2, float y2, float[] color) { 
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}
	
	public static void renderInterface4P(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float[] color) { 
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x3+playerCoord[0]-128+CORX)*size[0]/256, (y3+playerCoord[1]-72+CORY)*size[1]/144);		
		glVertex2f((x4+playerCoord[0]-128+CORX)*size[0]/256, (y4+playerCoord[1]-72+CORY)*size[1]/144);

		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}
	public static void renderTexture(float x1, float y1, float x2, float y2, float xo, float yo, float[] taille, int id, float[] color) { 
		glBindTexture(GL_TEXTURE_2D, id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glTexCoord2f( (0 + xo) / taille[0], (0 + yo ) / taille[1]);glVertex2f(x1*size[0]/256, (y1)*size[1]/144);		
		glTexCoord2f( (1 + xo) / taille[0], (0 + yo ) / taille[1]);glVertex2f(x2*size[0]/256, (y1)*size[1]/144);		
		glTexCoord2f( (1 + xo) / taille[0], (1 + yo ) / taille[1]);glVertex2f(x2*size[0]/256, (y2)*size[1]/144);		
		glTexCoord2f( (0 + xo) / taille[0], (1 + yo ) / taille[1]);glVertex2f(x1*size[0]/256, (y2)*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);
		glBindTexture(GL_TEXTURE_2D, 0);

	}
	public static void renderInterfaceTexture(float x1, float y1, float x2, float y2, float xo, float yo, float[] taille, int id, float[] color) { 
		glBindTexture(GL_TEXTURE_2D, id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glTexCoord2f( (0 + xo) / taille[0], (0 + yo ) / taille[1]);glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (1 + xo) / taille[0], (0 + yo ) / taille[1]);glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (1 + xo) / taille[0], (1 + yo ) / taille[1]);glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (0 + xo) / taille[0], (1 + yo ) / taille[1]);glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);
		glBindTexture(GL_TEXTURE_2D, 0);

	}public static void renderInterfaceTexture(float x1, float y1, float x2, float y2, float xo1, float yo1,float xo2, float yo2, float[] taille, int id, float[] color) { 
		glBindTexture(GL_TEXTURE_2D, id);
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glTexCoord2f( (xo1) / taille[0], (yo1) / taille[1]);glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (xo2) / taille[0], (yo1) / taille[1]);glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y1+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (xo2) / taille[0], (yo2) / taille[1]);glVertex2f((x2+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);		
		glTexCoord2f( (xo1) / taille[0], (yo2) / taille[1]);glVertex2f((x1+playerCoord[0]-128+CORX)*size[0]/256, (y2+playerCoord[1]-72+CORY)*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);
		glBindTexture(GL_TEXTURE_2D, 0);

	}
	
	public static void renderLine(float x1, float y1, float x2, float y2, float[] color) { 
		glBegin(GL_QUADS);
		glColor4f(color[0], color[1], color[2], color[3]);		
		glVertex2f((x1-1)*size[0]/256, (y1)*size[1]/144);		
		glVertex2f((x1)*size[0]/256, (y1-1)*size[1]/144);		
		glVertex2f((x2+1)*size[0]/256, (y2)*size[1]/144);		
		glVertex2f((x2)*size[0]/256, (y2+1)*size[1]/144);
		glEnd();
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);

	}

	
	public static Caractere[] getText(int xs, int ys, String texte, polices type, float taille) {
	
		int x = 0;
		int y = 0;
	int n = texte.length();
	char[] list = new char[n];
	for(int i = 0; i < n; i++) {
	list[i] = texte.charAt(i);
	}
	Caractere[] text = new Caractere[n];

	
	switch (type) {
	case COMIC_SANS_MS:
		for(int j = 0; j < n; j++) {
			String charactere = "" + list[j];
			switch (charactere) {
			case "A":editTxt(text, x, y, taille, j, caractere.A);break;
			case "B":editTxt(text, x, y, taille, j, caractere.B);break;
			case "C":editTxt(text, x, y, taille, j, caractere.C);break;
			case "D":editTxt(text, x, y, taille, j, caractere.D);break;
			case "E":editTxt(text, x, y, taille, j, caractere.E);break;
			case "F":editTxt(text, x, y, taille, j, caractere.F);break;
			case "G":editTxt(text, x, y, taille, j, caractere.G);break;
			case "H":editTxt(text, x, y, taille, j, caractere.H);break;
			case "I":editTxt(text, x, y, taille, j, caractere.I);break;
			case "J":editTxt(text, x, y, taille, j, caractere.J);break;
			case "K":editTxt(text, x, y, taille, j, caractere.K);break;
			case "L":editTxt(text, x, y, taille, j, caractere.L);break;
			case "M":editTxt(text, x, y, taille, j, caractere.M);break;
			case "N":editTxt(text, x, y, taille, j, caractere.N);break;
			case "O":editTxt(text, x, y, taille, j, caractere.O);break;
			case "P":editTxt(text, x, y, taille, j, caractere.P);break;
			case "Q":editTxt(text, x, y, taille, j, caractere.Q);break;
			case "R":editTxt(text, x, y, taille, j, caractere.R);break;
			case "S":editTxt(text, x, y, taille, j, caractere.S);break;
			case "T":editTxt(text, x, y, taille, j, caractere.T);break;
			case "U":editTxt(text, x, y, taille, j, caractere.U);break;
			case "V":editTxt(text, x, y, taille, j, caractere.V);break;
			case "W":editTxt(text, x, y, taille, j, caractere.W);break;
			case "X":editTxt(text, x, y, taille, j, caractere.X);break;
			case "Y":editTxt(text, x, y, taille, j, caractere.Y);break;
			case "Z":editTxt(text, x, y, taille, j, caractere.Z);break;
			case "a":editTxt(text, x, y, taille, j, caractere.a);break;
			case "b":editTxt(text, x, y, taille, j, caractere.b);break;
			case "c":editTxt(text, x, y, taille, j, caractere.c);break;
			case "d":editTxt(text, x, y, taille, j, caractere.d);break;
			case "e":editTxt(text, x, y, taille, j, caractere.e);break;
			case "f":editTxt(text, x, y, taille, j, caractere.f);break;
			case "g":editTxt(text, x, y, taille, j, caractere.g);break;
			case "h":editTxt(text, x, y, taille, j, caractere.h);break;
			case "i":editTxt(text, x, y, taille, j, caractere.i);break;
			case "j":editTxt(text, x, y, taille, j, caractere.j);break;
			case "k":editTxt(text, x, y, taille, j, caractere.k);break;
			case "l":editTxt(text, x, y, taille, j, caractere.l);break;
			case "m":editTxt(text, x, y, taille, j, caractere.m);break;
			case "n":editTxt(text, x, y, taille, j, caractere.n);break;
			case "o":editTxt(text, x, y, taille, j, caractere.o);break;
			case "p":editTxt(text, x, y, taille, j, caractere.p);break;
			case "q":editTxt(text, x, y, taille, j, caractere.q);break;
			case "r":editTxt(text, x, y, taille, j, caractere.r);break;
			case "s":editTxt(text, x, y, taille, j, caractere.s);break;
			case "t":editTxt(text, x, y, taille, j, caractere.t);break;
			case "u":editTxt(text, x, y, taille, j, caractere.u);break;
			case "v":editTxt(text, x, y, taille, j, caractere.v);break;
			case "w":editTxt(text, x, y, taille, j, caractere.w);break;
			case "x":editTxt(text, x, y, taille, j, caractere.x);break;
			case "y":editTxt(text, x, y, taille, j, caractere.y);break;
			case "z":editTxt(text, x, y, taille, j, caractere.z);break;
			case "1":editTxt(text, x, y, taille, j, caractere.n1);break;
			case "2":editTxt(text, x, y, taille, j, caractere.n2);break;
			case "3":editTxt(text, x, y, taille, j, caractere.n3);break;
			case "4":editTxt(text, x, y, taille, j, caractere.n4);break;
			case "5":editTxt(text, x, y, taille, j, caractere.n5);break;
			case "6":editTxt(text, x, y, taille, j, caractere.n6);break;
			case "7":editTxt(text, x, y, taille, j, caractere.n7);break;
			case "8":editTxt(text, x, y, taille, j, caractere.n8);break;
			case "9":editTxt(text, x, y, taille, j, caractere.n9);break;
			case "0":editTxt(text, x, y, taille, j, caractere.n0);break;
			case "Ê":editTxt(text, x, y, taille, j, caractere.Ê);break;
			case "É":editTxt(text, x, y, taille, j, caractere.É);break;
			case "À":editTxt(text, x, y, taille, j, caractere.À);break;
			case "Ï":editTxt(text, x, y, taille, j, caractere.Ï);break;
			case "Î":editTxt(text, x, y, taille, j, caractere.Î);break;
			case "Ù":editTxt(text, x, y, taille, j, caractere.Ù);break;
			case "Ô":editTxt(text, x, y, taille, j, caractere.Ô);break;
			case "Ç":editTxt(text, x, y, taille, j, caractere.Ç);break;
			case "ê":editTxt(text, x, y, taille, j, caractere.ê);break;
			case "é":editTxt(text, x, y, taille, j, caractere.é);break;
			case "è":editTxt(text, x, y, taille, j, caractere.è);break;
			case "à":editTxt(text, x, y, taille, j, caractere.à);break;
			case "ï":editTxt(text, x, y, taille, j, caractere.ï);break;
			case "î":editTxt(text, x, y, taille, j, caractere.î);break;
			case"ù":editTxt(text, x, y, taille, j, caractere.ù);break;
			case"û":editTxt(text, x, y, taille, j, caractere.û);break;
			case "ô":editTxt(text, x, y, taille, j, caractere.ô);break;
			case "ç":editTxt(text, x, y, taille, j, caractere.ç);break;
			case "È":editTxt(text, x, y, taille, j, caractere.È);break;
			case "Â":editTxt(text, x, y, taille, j, caractere.Â);break;
			case "Û":editTxt(text, x, y, taille, j, caractere.Û);break;
			case "Ò":editTxt(text, x, y, taille, j, caractere.Ò);break;
			case "ò":editTxt(text, x, y, taille, j, caractere.ò);break;
			case "ë":editTxt(text, x, y, taille, j, caractere.ë);break;
			case "Ë":editTxt(text, x, y, taille, j, caractere.Ë);break;
			case "ä":editTxt(text, x, y, taille, j, caractere.ä);break;
			case "Ä":editTxt(text, x, y, taille, j, caractere.Ä);break;
			case "ö":editTxt(text, x, y, taille, j, caractere.ö);break;
			case "Ö":editTxt(text, x, y, taille, j, caractere.Ö);break;
			case "ÿ":editTxt(text, x, y, taille, j, caractere.ÿ);break;
			case "â":editTxt(text, x, y, taille, j, caractere.â);break;
			case "ì":editTxt(text, x, y, taille, j, caractere.ì);break;
			case "ü":editTxt(text, x, y, taille, j, caractere.ü);break;
			case "&":editTxt(text, x, y, taille, j, caractere.AND);break;
			case ",":editTxt(text, x, y, taille, j, caractere.VIRG);break;
			case ";":editTxt(text, x, y, taille, j, caractere.PVIRG);break;
			case ".":editTxt(text, x, y, taille, j, caractere.POINT);break;
			case "!":editTxt(text, x, y, taille, j, caractere.EXC);break;
			case "?":editTxt(text, x, y, taille, j, caractere.INTER);break;
			case "'":editTxt(text, x, y, taille, j, caractere.APOST);break;
			case "\"":editTxt(text, x, y, taille, j, caractere.GUIE);break;
			case "-":editTxt(text, x, y, taille, j, caractere.TIRER);break;
			case ":":editTxt(text, x, y, taille, j, caractere.DPOINT);break;
			case "<":editTxt(text, x, y, taille, j, caractere.INF);break;
			case ">":editTxt(text, x, y, taille, j, caractere.SUP);break;
			case "/":editTxt(text, x, y, taille, j, caractere.SLASH);break;
			case "*":editTxt(text, x, y, taille, j, caractere.STAR);break;
			case "+":editTxt(text, x, y, taille, j, caractere.PLUS);break;
			case "=":editTxt(text, x, y, taille, j, caractere.EGAL);break;
			case "%":editTxt(text, x, y, taille, j, caractere.POURCENT);break;
			case "(":editTxt(text, x, y, taille, j, caractere.PARG);break;
			case ")":editTxt(text, x, y, taille, j, caractere.PARD);break;
			case "[":editTxt(text, x, y, taille, j, caractere.CROG);break;
			case "]":editTxt(text, x, y, taille, j, caractere.CROD);break;
			case "{":editTxt(text, x, y, taille, j, caractere.ACOG);break;
			case "}":editTxt(text, x, y, taille, j, caractere.ACOD);break;
			case " ":editTxt(text, x, y, taille, j, caractere.SPACE);break;
			case "\n":editTxt(text, x, y, taille, j, caractere.LINE);break;
			case "$":editTxt(text, x, y, taille, j, caractere.RED);break;
			case "£":editTxt(text, x, y, taille, j, caractere.BLUE);break;
			case "€":editTxt(text, x, y, taille, j, caractere.GREEN);break;
			case "¤":editTxt(text, x, y, taille, j, caractere.GRAY);break;
			case "@":editTxt(text, x, y, taille, j, caractere.WHITE);break;
			case "µ":editTxt(text, x, y, taille, j, caractere.BLACK);break;
			case "^":editTxt(text, x, y, taille, j, caractere.GOLD);break;
			case "°":editTxt(text, x, y, taille, j, caractere.LIGHTBLUE);break;
			case "|":editTxt(text, x, y, taille, j, caractere.ROTATETRIGO);break;
			case "#":editTxt(text, x, y, taille, j, caractere.ROTATEDIRECT);break;
			default:editTxt(text, x, y, taille, j, caractere.UNKNOWN);
			}
		text[j].setCoord(xs, ys);	
		}
	default:
		break;}
	
	return text;}
	
	private static void editTxt(Caractere[] text,int x, int y, float taille, int j, caractere chara) {
		if(j!=0)text[j] = new Comic_Sans_MS(((text[j-1].getCoord()[0])+text[j-1].getOffset()), text[j-1].getCoord()[1], taille, chara);
		else text[j] = new Comic_Sans_MS(x, y, taille, chara);
		
	}
	public static void renderTexte(int[][] tab, float[] color, Texture tex, float taille) {
	tex.bind();
	float[] k = {10.0f, 10.0f};
	for( int w = 0; w < tab.length; w++) {
	renderQuad(tab[w][0], tab[w][1] , tab[w][2], tab[w][3], (int)(16.0f*size[0]*taille/640.0f), (int)(16.0f*size[1]*taille/360.0f), k, color);
	}
	tex.unbind();
	}

	public static void renderTexte(Caractere[] chara) { 
	glColor4f(1.0f,1.0f,1.0f,1.0f);
	for( int w = 0; w < chara.length; w++) {
		if(chara[w].getColor()) glColor4f(chara[w].getLetter()[0]/255.0f, chara[w].getLetter()[1]/255.0f, chara[w].getLetter()[2]/255.0f, 1.0f);
		else{int[] letter = chara[w].getLetter();
		int x = chara[w].coord[0];
		int y = chara[w].coord[1];
		int xi = chara[w].xIni;
		int yi = chara[w].yIni;
		float taille = chara[w].getTaille();
		for(int v = 0; v < letter.length; v+=4) {
			renderPixel(((letter[v]+x)*taille+xi), ((letter[v+1]+y)*taille+yi), ((letter[v+2]+x)*taille+xi), ((letter[v+3]+y)*taille+yi));
		}}

	}
	}public static void renderInterfaceTexte(Caractere[] chara) {
		glColor4f(1.0f,1.0f,1.0f,1.0f);
		for( int w = 0; w < chara.length; w++) {
			if(chara[w].getColor()) glColor4f(chara[w].getLetter()[0]/255.0f, chara[w].getLetter()[1]/255.0f, chara[w].getLetter()[2]/255.0f, 1.0f);
			else{int[] letter = chara[w].getLetter();
			int x = chara[w].coord[0];
			int y = chara[w].coord[1];
			int xi = chara[w].xIni;
			int yi = chara[w].yIni;
			float taille = chara[w].getTaille();
			for(int v = 0; v < letter.length; v+=4) {
				renderPixel(((letter[v]+x)*taille+xi)-128+playerCoord[0]+CORX, ((letter[v+1]+y)*taille+yi)-72+playerCoord[1]+CORY, ((letter[v+2]+x)*taille+xi)-128+playerCoord[0]+CORX, ((letter[v+3]+y)*taille+yi)-72+playerCoord[1]+CORY);
			}}			
		}
		}
	public static void renderInterfaceTexte(Caractere[] chara, float alpha, int xoffset, int yoffset) {
			glColor4f(1.0f,1.0f,1.0f,alpha);
			for( int w = 0; w < chara.length; w++) {
				if(chara[w].getColor()) glColor4f(chara[w].getLetter()[0]/255.0f, chara[w].getLetter()[1]/255.0f, chara[w].getLetter()[2]/255.0f, alpha);
				else{int[] letter = chara[w].getLetter();
				int x = chara[w].coord[0];
				int y = chara[w].coord[1];
				int xi = chara[w].xIni;
				int yi = chara[w].yIni;

				float taille = chara[w].getTaille();
				for(int v = 0; v < letter.length; v+=4) {
					renderPixel(((letter[v]+x)*taille+xi)-128+playerCoord[0]+CORX+xoffset, ((letter[v+1]+y)*taille+yi)-72+playerCoord[1]+CORY+yoffset, ((letter[v+2]+x)*taille+xi)-128+playerCoord[0]+CORX+xoffset, ((letter[v+3]+y)*taille+yi)-72+playerCoord[1]+CORY+yoffset);

				}}			
			}glColor4f(1.0f,1.0f,1.0f,1.0f);
			}
	public static void renderInterfaceTexte(Caractere[] chara, float[] color, int xoffset, int yoffset) {
			glColor4f(color[0],color[1],color[2],color[3]);
			for( int w = 0; w < chara.length; w++) {
				if(chara[w].getColor()) glColor4f(chara[w].getLetter()[0]/255.0f, chara[w].getLetter()[1]/255.0f, chara[w].getLetter()[2]/255.0f, color[3]);
				else{int[] letter = chara[w].getLetter();
				int x = chara[w].coord[0];
				int y = chara[w].coord[1];
				int xi = chara[w].xIni;
				int yi = chara[w].yIni;

				float taille = chara[w].getTaille();
				for(int v = 0; v < letter.length; v+=4) {
					renderPixel(((letter[v]+x)*taille+xi)-128+playerCoord[0]+CORX+xoffset, ((letter[v+1]+y)*taille+yi)-72+playerCoord[1]+CORY+yoffset, ((letter[v+2]+x)*taille+xi)-128+playerCoord[0]+CORX+xoffset, ((letter[v+3]+y)*taille+yi)-72+playerCoord[1]+CORY+yoffset);

				}}			
			}glColor4f(1.0f,1.0f,1.0f,1.0f);
			}
	public static void renderTexte(Caractere chara, int[]coordPlayer) {
		if(chara.getColor()) glColor4f(chara.getLetter()[0]/255.0f, chara.getLetter()[1]/255.0f, chara.getLetter()[2]/255.0f, 1.0f);
		else{int[] letter = chara.getLetter();
		int x = chara.coord[0];
		int y = chara.coord[1];
		float taille = chara.getTaille();
		for(int v = 0; v < letter.length; v+=4) {
			renderPixel((((letter[v]+x)*taille)+(-80+coordPlayer[0]+CORX)), (((letter[v+1]+y)*taille)+(45+coordPlayer[1]+CORY)), ((letter[v+2]+x)*taille)+(-80+coordPlayer[0]+CORX), ((letter[v+3]+y)*taille)+(45+coordPlayer[1]+CORY));
		}
	}
	}
	public static void renderTextInterface00(Caractere[] text, float x, float y) {
		for( int w = 0; w < text.length; w++) {
			if(text[w].getColor()) glColor4f(text[w].getLetter()[0]/255.0f, text[w].getLetter()[1]/255.0f, text[w].getLetter()[2]/255.0f, 1.0f);
			else{int[] letter = text[w].getLetter();
			int x1 = text[w].coord[0];
			int y1 = text[w].coord[1];
			float taille = text[w].getTaille();
			for(int v = 0; v < letter.length; v+=4) {
				renderPixel((((letter[v]+x1)*taille)+(-128+x+playerCoord[0]+CORX)), (((letter[v+1]+y1)*taille)+(-72+y+playerCoord[1]+CORY)), ((letter[v+2]+x1)*taille)+(-128+x+playerCoord[0]+CORX), ((letter[v+3]+y1)*taille)+(-72+y+playerCoord[1]+CORY));
			}
			}}
	}
	public static void renderName(Caractere[] name,int[] coordPlayer, float[] color) {

		for( int w = 0; w < name.length; w++) {
			if(name[w].getColor()) glColor4f(name[w].getLetter()[0]/255.0f, name[w].getLetter()[1]/255.0f, name[w].getLetter()[2]/255.0f, 1.0f);
			else{int[] letter = name[w].getLetter();
			int x = name[w].coord[0];
			int y = name[w].coord[1];
			float taille = name[w].getTaille();
			for(int v = 0; v < letter.length; v+=4) {
				renderPixel((((letter[v]+x)*taille)+(-90+coordPlayer[0]+CORX)), (((letter[v+1]+y)*taille)+(39+coordPlayer[1]+CORY)), ((letter[v+2]+x)*taille)+(-90+coordPlayer[0]+CORX), ((letter[v+3]+y)*taille)+(39+coordPlayer[1]+CORY));
			}
			}}
		}
	public static void renderDialogue(Caractere[] tab,int[] coordPlayer, float speed) {
	for( int w = 0; w < tab.length; w++) {
		if(w <= Annimation.getLetter(speed))renderTexte(tab[w], coordPlayer);
	}}
	public static void renderDialogue(Caractere[] tab, Caractere[] name, float speed, int headID) {
		int[] coordPlayer = {(int)Game.getPlayerCoord()[0], (int)Game.getPlayerCoord()[1]};
	renderQuad2(((coordPlayer[0]+2+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-2+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-2+CORY)*size[1]/144) + (size[1]/2), WHITE);
	renderQuad2(((coordPlayer[0]+3+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+1+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-3+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-3+CORY)*size[1]/144) + (size[1]/2), BLACK);
	glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);	
	renderName(name, coordPlayer,  WHITE);
	renderDialogue(tab,coordPlayer, speed);	
	renderHead((int)((coordPlayer[0]+CORX) - (16*8-4)), coordPlayer[1]+CORY + (16*2+4), 4, 0, headSprite,headTaille, headID);
}	
	public static void renderDialogue(Caractere[] tab, Caractere[] name, int headID) {
	int[] coordPlayer = {(int)Game.getFocusCoord()[0], (int)Game.getFocusCoord()[1]};
	renderQuad2(((coordPlayer[0]+2+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-2+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-2+CORY)*size[1]/144) + (size[1]/2), WHITE);
	renderQuad2(((coordPlayer[0]+3+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+1+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-3+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-3+CORY)*size[1]/144) + (size[1]/2), BLACK);
	glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);	
	renderName(name, coordPlayer,  WHITE);
	renderHead((int)((coordPlayer[0]+CORX) - (16*8-4)), coordPlayer[1]+CORY + (16*2+4), 4, 0, headSprite,headTaille, headID);

	renderDialogue(tab,coordPlayer, 1.0f);	
}	
	
	public static void renderDialogue(Caractere[] tab, Caractere[] name, int headID, int armorID) {
		int[] coordPlayer = {(int)Game.getFocusCoord()[0], (int)Game.getFocusCoord()[1]};
		renderQuad2(((coordPlayer[0]+2+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-2+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-2+CORY)*size[1]/144) + (size[1]/2), WHITE);
		renderQuad2(((coordPlayer[0]+3+CORX)*size[0]/256) - (size[0]/2), ((coordPlayer[1]+1+CORY)*size[1]/144) + (size[1]/4), ((coordPlayer[0]-3+CORX)*size[0]/256) + (size[0]/2), ((coordPlayer[1]-3+CORY)*size[1]/144) + (size[1]/2), BLACK);
		glColor4f(WHITE[0], WHITE[1], WHITE[2], WHITE[3]);	
		renderName(name, coordPlayer,  WHITE);
		renderHead((int)((coordPlayer[0]+CORX) - (16*8-4)), coordPlayer[1]+CORY + (16*2+4), 4, 0, headSprite,headTaille, headID);
		renderHead((int)((coordPlayer[0]+CORX) - (16*8-4)), coordPlayer[1]+CORY + (16*2+4), 4, 0, headSprite,headTaille, armorID);

		renderDialogue(tab,coordPlayer, 1.0f);	
	}	
	
}
