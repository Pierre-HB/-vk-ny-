package fr.pierrehb.entities.joueurs;

import fr.pierrehb.entities.Human;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.main.Main;



public class Createur extends Joueur {
	private static final int[] clearInventory= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private static final int[][] attaque = {{0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0},{-1,0,0,0,0}};
	private static final Caractere[][] introDialogues = new Caractere[4][];
	private int cunterIntro = 0;
	private static boolean saysIntro = false;


	public Createur(int x, int y) {
		super(x, y, str_createurName, classes.NONE, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Player/PlayerSprit.png"), polices.COMIC_SANS_MS);
		ini();
	}
	public Createur(int x, int y, boolean canmove, direction direction) {
		super(x, y, str_createurName, classes.NONE, canmove, direction, Texture.loadTexture("res/textures/Human/Player/PlayerSprit.png"), polices.COMIC_SANS_MS);
		ini();
	}
	private void ini() {
		HP = 30;
		maxHP = 30;
		classe = classes.NONE;
		mortal = true;
		mortalID = Main.getMortalID();
		this.RENDERCURSOR=false;
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		
		this.invent = clearInventory;
		this.arme=0;
		this.armure=-1;
		
		for(int a = 0; a < whoToReadAttack.length; a++) {
			for(int b = 0; b < whoToReadAttack[a].length; b++) {
				whoToReadAttack[a][b] = attaque[a][b];
			}
		}
		if(Main.save.avancement==0) {
			bigDialogue.add(Renderer.getText(0, 0, str_createur_1, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_createur_2, polices.COMIC_SANS_MS, 0.4f));
		}
		if(Main.save.avancement==1) {
			introDialogues[0] = Renderer.getText(0, 0, str_createur_3, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[1] = Renderer.getText(0, 0, str_createur_4, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[2] = Renderer.getText(0, 0, str_createur_5, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[3] = Renderer.getText(0, 0, str_createur_6, polices.COMIC_SANS_MS, 0.4f);

		}
	}

	@Override
	protected void joueurRender() {
		
	}

	@Override
	protected void resetmouse(int attaque) {
		
	}

	@Override
	protected void joueurDie() {
		
	}

	@Override
	protected void addActions() {
		
	}

	@Override
	public boolean askForFriend() {
		return false;
	}

	@Override
	public void PNJUpdate() {
		
	}

	@Override
	public boolean askDuel(Human demandeur) {
		return false;
	}

	@Override
	public float getAngle() {
		return 0;
	}

	@Override
	protected boolean interat() {
	dialogue++;
	sayDialogue();
	
	return interacting;
	}
	public boolean interaction() {
		return interat();
	}

	@Override
	public void dialogueRender() {
		if(dialogue != 0)	renderDialogue(bigDialogue.get(dialogue-1));
		if(saysIntro) renderDialogue(introDialogues[cunterIntro]);
		
	}
	public void sayIntro() {
		if(!saysIntro) {
			endDialogue();
			saysIntro = true;
			startDialogue(introDialogues[cunterIntro]);
		}
		else {
			cunterIntro++;
			saysIntro = false;
		}
	}
	@Override
	public Friend getFriend() {
		return null;
	}

}
