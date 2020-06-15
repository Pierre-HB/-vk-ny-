package fr.pierrehb.entities.villageois;


import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class BlueKing  extends Villageoi{
	private boolean MORTAL = false;
	private static Sound_lector sound;

	public BlueKing(int x, int y,  boolean canmove, direction direction, boolean mortal_) {
		super(x, y, "Blue King", canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueKingSprit.png"));
		MORTAL = mortal_;
		ini();
	}
		public BlueKing(int x, int y,  boolean canmove, direction direction) {
			super(x, y, str_BlueKingName, canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueKingSprit.png"));
			ini();
		}
		public BlueKing(int x, int y, direction direction   ) {
			super(x, y, str_BlueKingName, true, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueKingSprit.png"));
			ini();
		}
		public BlueKing(int x, int y ) {
			super(x, y, str_BlueKingName, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Villageois/BlueKingSprit.png"));
			ini();
		}
		private void ini() {
			if(MORTAL) {
				mortal = true;
				mortalID = Main.getMortalID();
				}else  mortal = false;
			switch (level) {

			case 18:
				bigDialogue.add(Renderer.getText(0, 0, str_beggeningCastel_king1, polices.COMIC_SANS_MS, 0.4f));
				bigDialogue.add(Renderer.getText(0, 0, str_beggeningCastel_king2, polices.COMIC_SANS_MS, 0.4f));
				bigDialogue.add(Renderer.getText(0, 0, str_beggeningCastel_king3, polices.COMIC_SANS_MS, 0.4f));
				bigDialogue.add(Renderer.getText(0, 0, str_beggeningCastel_king4, polices.COMIC_SANS_MS, 0.4f));

				
				break;

				
			}	
			HP = 20;

		}
		@Override
		protected boolean interat() {
			dialogue++;
			switch (level) {
			default:
				sayDialogue();
			}
	return interacting;
	}



		
		@Override
		public void trueRender() {
			humanRender();

		}
		@Override
		protected void die() {
			remove = true;
			sound = new Sound_lector("res/sound/sou/allyDie.wav", Main.save.soundVolume);
			sound.start();
		}
		@Override
		public void dialogueRender() {
			switch (level) {
			default:
				if(dialogue != 0)	renderDialogue(bigDialogue.get(dialogue-1));

				}
		}
		@Override
		public void PNJUpdate() {
			switch(level) {
			case 2:
				if (event == 1) {
					wantMove = true;
					vers = sens.O;
				}break;
			case 1:
				if (x < 160 && x > 126) {
					wantMove = true;
					vers = sens.NO;
				}else if( x < 160 ) {
					wantMove = true;
					vers = sens.N;
				}else {
					wantMove = true;
					vers = sens.O;
				}break;

				
					
			}
			
			
		}
		@Override
		public float getAngle() {
			return mouse.getAngle();
		}
		@Override
		public void setDuel(int dueleur) {
			
		}
		@Override
		public void endDuel(int winner) {
			
		}
		@Override
		public boolean askDuel(Human demandeur) {
			return false;
		}
		
		
	}