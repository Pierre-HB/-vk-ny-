package fr.pierrehb.entities.objects;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Dummy extends Objects {
	static float[] dimension = {2,1};
	static int[] taille = {16,32};
	private static Sound_lector song;

		public Dummy(int x, int y, boolean canmove) {
			super(x, y, "Dummy", canmove, Texture.loadTexture("res/textures/entity/Dummy.png"), dimension, taille);
			ini();
	}
		public Dummy(int x, int y) {
			super(x, y, "Dummy", false, Texture.loadTexture("res/textures/entity/Dummy.png"), dimension, taille);
			ini();
			}

		@Override
		protected void die() {
			xo = 1;
			Game.level.giveXpGold(20, 2);
			song = new Sound_lector("res/sound/sou/objects/dummyDestroy.wav", Main.save.soundVolume);
			song.start();
		}

		@Override
		public void trueUpdate() {
			hitBox[0] = (int)x+1;
			hitBox[1] = (int)y+1;
			hitBox[2] = (int)x+15;
			hitBox[3] = (int)y+31;
		}
	
		public void ini() {
			HP = 5;
			xo = 0;
			yo = 0;
			mortal = true;
			mortalID = Main.getMortalID();

			hitBox[0] = (int)x+1;
			hitBox[1] = (int)y+1;
			hitBox[2] = (int)x+15;
			hitBox[3] = (int)y+31;
		}
		@Override
		protected boolean interat() {
			return false;
		}
		@Override
		public boolean interact() {
			return false;
		}
		@Override
		public void dialogueRender() {
			
		}
		@Override
		public int getBottomY() {
			return (int)y + taille[1];
		}
		@Override
		protected void otherRender() {
			
		}
		@Override
		public void start() {
			
		}
		

	}