package fr.pierrehb.levels;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Square;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.TypeSquare;
import fr.pierrehb.sound.Sound_lector;

public abstract class Sous_Level extends Level implements TypeSquare{
	private static float[] translateView = {0,0};
	public static List<Square> squareList = new ArrayList<Square>();
	public static List<Square> dynamicSquareList = new ArrayList<Square>();
	public static List<Square> targetSquareList = new ArrayList<Square>();


	protected static boolean[][] solidList;
	protected static float xC = 128.0f;
	protected static float yC = 72.0f;
	protected static float bigOffset = 96.0f;
	protected static Square cadre;
	protected static boolean LEFT, RIGHT, BACK, ROTATE = false;
	private static int timer = 0;
	private static boolean moving, move;
	private static int leftkey = Main.save.contrôles[2][0];
	private static int leftmouse = Main.save.contrôles[2][1];
	private static int rightkey = Main.save.contrôles[3][0];
	private static int rightmouse = Main.save.contrôles[3][1];
	private Caractere[] back = Renderer.getText(5, 5, str_back, polices.COMIC_SANS_MS, 0.5f);
	private Caractere[] trigo = Renderer.getText(5, 125, str_trigo, polices.COMIC_SANS_MS, 0.7f);
	private Caractere[] direct = Renderer.getText(180, 125, str_direct, polices.COMIC_SANS_MS, 0.7f);
	private static Sound_lector song;

	
	public Sous_Level(int[][] level) {
		solidList = new boolean[level.length][level.length];
		start(level);
		ini();
	}
	@Override
	public void update() {
		
		
			
		if(testWin()) {
			squareList.clear();
			dynamicSquareList.clear();
			targetSquareList.clear();
			win();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if(!BACK) {
				back();
			}BACK = true;
		}else BACK = false;
		
		for(Square sqrt : dynamicSquareList) if(sqrt.moving) move = true;
		if(move)moving = true;
		else moving = false;
		move = false;
		
	if(ROTATE) {
		timer++;
		if(timer >= 30) {
			for(Square sq : squareList) sq.stopRotating();
			for(Square sqrt : dynamicSquareList) sqrt.stopRotating();
			for(Square sqrt : targetSquareList) sqrt.stopRotating();
			cadre.stopRotating();
			ROTATE = false;
		}
	}
	else {
		timer = 0;
		if(!moving) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || (leftkey != -1 && Keyboard.isKeyDown(leftkey)) || Mouse.isButtonDown(leftmouse)) {
			if(!LEFT) {
				if(!ROTATE) {
					song = new Sound_lector("res/sound/sou/logicLevel/rotate.wav", Main.save.soundVolume);
					song.start();
				}
				startRotateTrigo();
				cadre.startRotating(rotating.TRIGO, xC, yC);
				ROTATE = true;

			}
			LEFT = true;
		}else LEFT = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || (rightkey != -1 && Keyboard.isKeyDown(rightkey)) || Mouse.isButtonDown(rightmouse)) {
			if(!RIGHT) {
				if(!ROTATE) {
					song = new Sound_lector("res/sound/sou/logicLevel/rotate.wav", Main.save.soundVolume);
					song.start();
				}
				startRotateDirect();
				cadre.startRotating(rotating.DIRECT, xC, yC);
				ROTATE = true;
			}RIGHT = true;

		}else RIGHT = false;
	}}
		for(Square sq : squareList) sq.update();
		for(Square sqrt : dynamicSquareList) sqrt.update();
		for(Square sqrt : targetSquareList) sqrt.update();
		cadre.update();


	}
public abstract void startRotateTrigo();
public abstract void startRotateDirect();

	@Override
	public void render() {

		Renderer.renderSquare(xC-bigOffset/2, xC+bigOffset/2, yC-bigOffset/2-20, yC-bigOffset/2-4, RED);
		Renderer.renderSquare(xC-bigOffset/2, xC+bigOffset/2, yC+bigOffset/2+4, yC+bigOffset/2+20, GOLD);
		Renderer.renderSquare(xC-bigOffset/2-20, xC-bigOffset/2-4, yC-bigOffset/2, yC+bigOffset/2, BLUE);
		Renderer.renderSquare(xC+bigOffset/2+4, xC+bigOffset/2+20, yC-bigOffset/2, yC+bigOffset/2, GREEN);
		Renderer.renderTexte(back);
		Renderer.renderTexte(trigo);
		Renderer.renderTexte(direct);

		cadre.render();



		for(Square sq : squareList) sq.render();
		for(Square sqrt : dynamicSquareList) sqrt.render();
		for(Square sqrt : targetSquareList) sqrt.render();

	}

	@Override
	public float[] translateView() {
		return translateView;
	}
	public abstract void ini();
	
	public void start(int[][] level) {
		cadre = new Square(xC, yC, bigOffset/2, type_square.STANDART);
		for(int y = 0; y < level.length; y++) {
			for(int x = 0; x < level.length; x ++) {
				switch (level[y][x]) {
				case 1:
					squareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.STANDART));
				break;
				case 5:
					dynamicSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.RED));
				break;
				case 6:
					dynamicSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.BLUE));
				break;
				case 7:
					dynamicSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.YELLOW));
				break;
				case 8:
					dynamicSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.GREEN));
				break;
				case 9:
					targetSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.RED_TARGET));
				break;
				case 10:
					targetSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.BLUE_TARGET));
				break;
				case 11:
					targetSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.YELLOW_TARGET));
				break;
				case 12:
					targetSquareList.add(new Square(xC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)x+0.5f),yC-(bigOffset/2)+(bigOffset/(float)level.length)*((float)y+0.5f), (bigOffset/((float)level.length*2.0f)), type_square.GREEN_TARGET));
				break;
				
				}
			}
		}
		reloadSolid();
		
	}
	public void reloadSolid() {
		for(int x = 0; x < solidList.length; x++) {
			for(int y = 0; y < solidList.length; y++) {
				solidList[x][y] = false;
			}}
		for(Square sqr : squareList) {
		
			
			int xb =(int)((sqr.getX()-(solidList.length/(2*bigOffset))-(xC-(bigOffset/2)))/solidList.length);			
			int yb =(int)((sqr.getY()-(solidList.length/(2*bigOffset))-(yC-(bigOffset/2)))/solidList.length);
			solidList[xb][yb] = sqr.getSolid();
		}}
	@Override
	public boolean getSolid(int x, int y) {		
		return false;
	}
	
	@Override
	public boolean getSolidMoveX(float x, float y) { // moins précis sur le'axe Y
		int nb = 0;
		if(x*1.003f >xC+(bigOffset/2)-(bigOffset/(2*solidList.length)) || x*0.9999f < xC-(bigOffset/2)+(bigOffset/(2*solidList.length)) || y*1.005f > yC+(bigOffset/2)-(bigOffset/(2*solidList.length)) || y*0.95f < yC-(bigOffset/2)+(bigOffset/(2*solidList.length)))return true;
		else {
			for(Square sqrt : dynamicSquareList) {
			if(Math.abs(x-sqrt.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(y-sqrt.getY())<(bigOffset/(solidList.length))*0.95f) nb++;									
			}
			for(Square sqr : squareList) {
				if(Math.abs(x-sqr.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(y-sqr.getY())<(bigOffset/(solidList.length))*0.95f) if(sqr.getSolid())nb++;									
			}
			if(nb >1) return true;
			}
		return false;
	}
	@Override
	public boolean getSolidMoveY(float x, float y) {// moins précis sur le'axe X
		int nb = 0;
		if(x*1.005f >xC+(bigOffset/2)-(bigOffset/(2*solidList.length)) || x*0.95 < xC-(bigOffset/2)+(bigOffset/(2*solidList.length)) || y*1.003f > yC+(bigOffset/2)-(bigOffset/(2*solidList.length)) || y*0.9999f < yC-(bigOffset/2)+(bigOffset/(2*solidList.length)))return true;
		else {
			for(Square sqrt : dynamicSquareList) {
			if(Math.abs(x-sqrt.getX())<(bigOffset/(solidList.length))*0.95f && Math.abs(y-sqrt.getY())<(bigOffset/(solidList.length))*0.9999f) nb++;									
			}

			for(Square sqr : squareList) {
				if(Math.abs(x-sqr.getX())<(bigOffset/(solidList.length))*0.95f && Math.abs(y-sqr.getY())<(bigOffset/(solidList.length))*0.9999f) /*if(sqr.getSolid())*/nb++;									
			}

			
			if(nb >1) return true;
			}
		return false;
	}
	public boolean testWin() {
		boolean win = false;
		for(Square target : targetSquareList) {
			for(Square dynamic : dynamicSquareList) {
			switch (target.type) {
			case RED_TARGET:
				if(dynamic.type == type_square.RED && Math.abs(target.getX()-dynamic.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(target.getY()-dynamic.getY())<(bigOffset/(solidList.length))*0.9999f)win = true;
				break;
			case BLUE_TARGET:
				if(dynamic.type == type_square.BLUE && Math.abs(target.getX()-dynamic.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(target.getY()-dynamic.getY())<(bigOffset/(solidList.length))*0.9999f)win = true;

				break;
			case GREEN_TARGET:
				if(dynamic.type == type_square.GREEN && Math.abs(target.getX()-dynamic.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(target.getY()-dynamic.getY())<(bigOffset/(solidList.length))*0.9999f)win = true;

				break;
			case YELLOW_TARGET:
				if(dynamic.type == type_square.YELLOW && Math.abs(target.getX()-dynamic.getX())<(bigOffset/(solidList.length))*0.9999f && Math.abs(target.getY()-dynamic.getY())<(bigOffset/(solidList.length))*0.9999f)win = true;

				break;
			default:
					
			}
		}if(!win) return false;

		}
		song = new Sound_lector("res/sound/sou/logicLevel/win.wav", Main.save.soundVolume);
		song.start();
		return true;
	}
	
	public abstract void win();
	public abstract void back();
	
	@Override
	public boolean giveHeal(int[] hitBox, Entity joueur, int damage) {
		return false;
	}
	
	@Override
	public boolean giveDamageSquare(Quadrilataire hitBox, Entity joueur, int damage) {
		return false;
	}
	@Override
	public void askForParty(Joueur demandeur) {
		
	}
	@Override
	public void nextLevel() {
		
	}
	@Override
	public Entity spawner(Entity entity) {
		return entity;
	}
	@Override
	public void startLevel() {
		
	}
}
