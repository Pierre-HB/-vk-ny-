package fr.pierrehb.entities.joueurs;

import fr.pierrehb.entities.Human;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.items.Console;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;


public class Hacker extends Joueur {
	private static final Caractere[][] introDialogues = new Caractere[5][];
	private int cunterIntro = 0;
	private static boolean saysIntro = false;
	private boolean MORTAL = false;

	public Hacker(int x, int y) {
		super(x, y, str_hackerName, classes.NONE, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Joueurs/Ha(k3r.png"), polices.COMIC_SANS_MS);
		ini();
	}
	public Hacker(int x, int y, boolean canmove, direction direction) {
		super(x, y, str_hackerName, classes.NONE, canmove, direction, Texture.loadTexture("res/textures/Human/Joueurs/Ha(k3r.png"), polices.COMIC_SANS_MS);
		ini();
	}
	public Hacker(int x, int y, boolean canmove, direction direction, boolean mortal_) {
		super(x, y, str_hackerName, classes.NONE, canmove, direction, Texture.loadTexture("res/textures/Human/Joueurs/Ha(k3r.png"), polices.COMIC_SANS_MS);
		MORTAL = mortal_;
		ini();
	}
	private void ini() {
		HP = 300;
		maxHP = 300;
		classe = classes.NONE;
		mortal = MORTAL;
		if(MORTAL)mortalID = Main.getMortalID();
		this.RENDERCURSOR=false;
		
		hitBox[0] = (int)x+1;
		hitBox[1] = (int)y+1;
		hitBox[2] = (int)x+15;
		hitBox[3] = (int)y+31;
		
		this.invent = HackerStats.getInventory();
		this.arme=HackerStats.getWeapon();
		this.armure=HackerStats.getArmor();
		
		for(int a = 0; a < whoToReadAttack.length; a++) {
			for(int b = 0; b < whoToReadAttack[a].length; b++) {
				whoToReadAttack[a][b] = HackerStats.getAttaque()[a][b];
			}
		}
		if(Main.save.avancement==1) {
			introDialogues[0] = Renderer.getText(0, 0, str_hacker_1, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[1] = Renderer.getText(0, 0, str_hacker_2, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[2] = Renderer.getText(0, 0, str_hacker_3, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[3] = Renderer.getText(0, 0, str_hacker_4, polices.COMIC_SANS_MS, 0.4f);
			introDialogues[4] = Renderer.getText(0, 0, str_hacker_5, polices.COMIC_SANS_MS, 0.4f);

		}
		if(Main.save.room==-42) {
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker1, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker2, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker3, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker4, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker5, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker6, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker7, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker8, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker9, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker10, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker11, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker12, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker13, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker14, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker15, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker16, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker17, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker18, polices.COMIC_SANS_MS, 0.4f));
			bigDialogue.add(Renderer.getText(0, 0, str_alpha_end_Hacker19, polices.COMIC_SANS_MS, 0.4f));


			
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
	public Friend getFriend() {
		return new HackerFriend();
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
	public boolean interat() {
		if(Main.save.avancement==2) {
		this.VISIBLE=false;
		Game.getPlayer().giveItem(new Console());
		return false;
		}else {
			dialogue++;
			sayDialogue();
		}
		return interacting;
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

}
