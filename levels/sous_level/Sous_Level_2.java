package fr.pierrehb.levels.sous_level;

import fr.pierrehb.entities.Square;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.levels.Sous_Level;

public class Sous_Level_2 extends Sous_Level{
	static int[][] level= {{1,1,1,1,1,1,1,1,1,1},
			{1,11,1,0,0,0,0,0,10,1},
			{1,0,1,0,0,0,8,1,1,1},
			{1,0,5,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,7,0,1},
			{1,1,1,6,0,0,0,1,0,1},
			{1,12,0,0,0,0,0,1,9,1},
			{1,1,1,1,1,1,1,1,1,1}};
	
	public Sous_Level_2() {
		super(level);
	}

	@Override
	public void ini() {
		
	}

	@Override
	public void startRotateTrigo() {
		for(Square sr : squareList) sr.startRotating(rotating.TRIGO,128, 72);	
		for(Square sr : dynamicSquareList) sr.startRotating(rotating.TRIGO,128, 72);
		for(Square sr : targetSquareList) sr.startRotating(rotating.TRIGO,128, 72);	

	}

	@Override
	public void startRotateDirect() {
		for(Square sr : squareList) sr.startRotating(rotating.DIRECT,128, 72);	
		for(Square sr : dynamicSquareList) sr.startRotating(rotating.DIRECT,128, 72);	
		for(Square sr : targetSquareList) sr.startRotating(rotating.DIRECT,128, 72);	

	}

	@Override
	public void win() {
		Game.level.speakToLevel(3);
	}

	@Override
	public void back() {
		Game.level.speakToLevel(2);
	}

	@Override
	public void speakToLevel(int i) {
		
	}

	@Override
	public void interact() {
		
	}
	@Override
	protected Interface getInter() {
		return null;
	}

	@Override
	public void giveXpGold(int xp, int gold) {
		
	}


	@Override
	public void save() {
		
	}

	@Override
	public void loadingConsol() {
		
	}

	@Override
	public void stopMusic() {
		
	}

}