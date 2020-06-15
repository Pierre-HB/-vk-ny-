package fr.pierrehb.entities.villageois;


import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class BlueGuard extends Villageoi{
	private boolean MORTAL = false;
	private String text = "";
	private static Sound_lector sound;

	public BlueGuard(int x, int y,  boolean canmove, direction direction, boolean mortal_) {
		super(x, y, "Blue Guard", canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueGuardSprit.png"));
		MORTAL = mortal_;
		ini();
	}
	public BlueGuard(int x, int y,  boolean canmove, direction direction) {
		super(x, y, str_blueGuardName, canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueGuardSprit.png"));
		ini();
	}
	public BlueGuard(int x, int y, direction direction   ) {
		super(x, y, str_blueGuardName, true, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueGuardSprit.png"));
		ini();
	}
	public BlueGuard(int x, int y, direction direction, String text_  ) {
		super(x, y, str_blueGuardName, true, direction, Texture.loadTexture("res/textures/Human/Villageois/BlueGuardSprit.png"));
		this.text=text_;
		ini();
	}
	public BlueGuard(int x, int y ) {
		super(x, y, str_blueGuardName, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Villageois/BlueGuardSprit.png"));
		ini();
	}
	private void ini() {
		if(MORTAL) {
		mortal = true;
		mortalID = Main.getMortalID();
		}else  mortal = false;
		switch (level) {
		case 19:bigDialogue.add(Renderer.getText(0, 0, str_halte, polices.COMIC_SANS_MS, 0.4f));break;
		default:
			bigDialogue.add(Renderer.getText(0, 0, text, polices.COMIC_SANS_MS, 0.4f));break;

		}	
		HP = 20;
	}
	@Override
	protected boolean interat() {
		dialogue++;
			sayDialogue();		
return interacting;
}
	public void stopPlayer() {
		interat();
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
		if(dialogue != 0)	renderDialogue(bigDialogue.get(dialogue-1));
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