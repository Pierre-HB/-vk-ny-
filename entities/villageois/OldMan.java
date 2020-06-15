package fr.pierrehb.entities.villageois;




import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.objects.Focus;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Annimation;
import fr.pierrehb.graphic.Annimation.direction;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;
import fr.pierrehb.graphic.Caractere;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;

public class OldMan  extends Villageoi{
private Caractere[] oldManHello1;
private Caractere[] oldManHello2;
private Caractere[] oldManHello3;
private Caractere[] OldManPresentation1;
private Caractere[] OldManPresentation2;
private Caractere[] OldManPresentation3;
private Caractere[] OldManPresentation4;
private Caractere[] OldManPresentation5;
private Caractere[] OldManExplain1;
private Caractere[] OldManExplain2;
private Caractere[] OldManShowChest;
private Caractere[] OldManTraining;
private Focus focus;
private int dialogue2 = 0;
private static Sound_lector sound;

	public OldMan(int x, int y,  boolean canmove, direction direction) {
		super(x, y, str_oldManName, canmove, direction, Texture.loadTexture("res/textures/Human/Villageois/OldManSprit.png"));
		ini();
	}
	public OldMan(int x, int y, direction direction   ) {
		super(x, y, str_oldManName, true, direction, Texture.loadTexture("res/textures/Human/Villageois/OldManSprit.png"));
		ini();
	}
	public OldMan(int x, int y ) {
		super(x, y, str_oldManName, true, direction.DOWN, Texture.loadTexture("res/textures/Human/Villageois/OldManSprit.png"));
		ini();
	}
	private void ini() {
		switch (level) {
		case 2:
			oldManHello1 = Renderer.getText(0, 0, str_OldManHello1, polices.COMIC_SANS_MS, 0.4f);
			oldManHello2 = Renderer.getText(0, 0, str_OldManHello2, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 1:
			oldManHello3 = Renderer.getText(0, 0, str_OldManHello3, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 4:
			OldManPresentation1 = Renderer.getText(0, 0, str_OldManPresentation1, polices.COMIC_SANS_MS, 0.4f);
			OldManPresentation2 = Renderer.getText(0, 0, str_OldManPresentation2, polices.COMIC_SANS_MS, 0.4f);
			OldManPresentation3 = Renderer.getText(0, 0, str_OldManPresentation3, polices.COMIC_SANS_MS, 0.4f);
			OldManPresentation4 = Renderer.getText(0, 0, str_OldManPresentation4, polices.COMIC_SANS_MS, 0.4f);
			OldManPresentation5 = Renderer.getText(0, 0, str_OldManPresentation5, polices.COMIC_SANS_MS, 0.4f);

			focus = new Focus(72,8);
			break;
		case 5:
			OldManExplain1 = Renderer.getText(0, 0, str_OldManExplain1, polices.COMIC_SANS_MS, 0.4f);
			OldManExplain2 = Renderer.getText(0, 0, str_OldManExplain2, polices.COMIC_SANS_MS, 0.4f);
			OldManShowChest = Renderer.getText(0, 0, str_OldManShowChest, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 6:
			OldManExplain1 = Renderer.getText(0, 0, str_OldManExplain1, polices.COMIC_SANS_MS, 0.4f);
			OldManExplain2 = Renderer.getText(0, 0, str_OldManExplain2, polices.COMIC_SANS_MS, 0.4f);
			OldManShowChest = Renderer.getText(0, 0, str_OldManShowChest, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 7:
			OldManExplain1 = Renderer.getText(0, 0, str_OldManExplain1, polices.COMIC_SANS_MS, 0.4f);
			OldManExplain2 = Renderer.getText(0, 0, str_OldManExplain2, polices.COMIC_SANS_MS, 0.4f);
			OldManShowChest = Renderer.getText(0, 0, str_OldManShowChest, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 8:
			OldManExplain1 = Renderer.getText(0, 0, str_OldManExplain1, polices.COMIC_SANS_MS, 0.4f);
			OldManExplain2 = Renderer.getText(0, 0, str_OldManExplain2, polices.COMIC_SANS_MS, 0.4f);
			OldManShowChest = Renderer.getText(0, 0, str_OldManShowChest, polices.COMIC_SANS_MS, 0.4f);
			break;
		case 10: OldManTraining = Renderer.getText(0, 0, str_OldManTraining, polices.COMIC_SANS_MS, 0.4f); break;
		case 11: OldManTraining = Renderer.getText(0, 0, str_OldManTraining, polices.COMIC_SANS_MS, 0.4f); break;
		case 12: OldManTraining = Renderer.getText(0, 0, str_OldManTraining, polices.COMIC_SANS_MS, 0.4f); break;
		case 13: OldManTraining = Renderer.getText(0, 0, str_OldManTraining, polices.COMIC_SANS_MS, 0.4f); break;

		case 14:
			bigDialogue.add(Renderer.getText(0, 0, str_OldManButton, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 15:
			bigDialogue.add(Renderer.getText(0, 0, str_OldManCannon, polices.COMIC_SANS_MS, 0.4f));
			break;
		case 16:
			bigDialogue.add(Renderer.getText(0, 0, str_OldManPostFirstBoss, polices.COMIC_SANS_MS, 0.4f));
			break;

			
		}	
		HP = 20;

	}
	@Override
	protected boolean interat() {
		dialogue++;
		switch (level) {
		case 2: 
			interacting = true;
			if(dialogue == 1) {
				Annimation.startDialogue(oldManHello1.length);
				Game.getPlayer().canMove = false;
	
			}else if(dialogue == 2){
				nextDialogue(oldManHello2);
			}else {
				if(Annimation.stopDialogue()) {
					Game.getPlayer().canMove = true;
					dialogue = 0;
					interacting = false;
					event = 1;
				}else dialogue = 2;
				
			}
			break;
		case 1:
			interacting = true;
			if(dialogue == 1) {
				Annimation.startDialogue(oldManHello3.length);
				Game.getPlayer().canMove = false;
			}else {
				if(Annimation.stopDialogue()) {
					Game.getPlayer().canMove = true;
					dialogue = 0;
					interacting = false;
				}else dialogue = 1;
			}
			break;
		case 4:
			if(dialogue == 1) {
				focus = new Focus((int)Game.getPlayerCoord()[0],(int)Game.getPlayerCoord()[1]);
				Game.level.setFocus( focus);
				Annimation.startDialogue(OldManPresentation1.length);
				Game.getPlayer().canMove = false;
			}else if(dialogue == 2){				
				if (nextDialogue(OldManPresentation2)) {
					
					Game.level.haveFocus = true;
					focus.go(72, 48, 2);
				}
			}else if(dialogue == 3){
				if (nextDialogue(OldManPresentation3)) focus.go(152, 48, 2);
			}else if(dialogue == 4){
				if (nextDialogue(OldManPresentation4)) focus.go(232, 48, 2);
			}else if(dialogue == 5){
				if (nextDialogue(OldManPresentation5)) focus.go(312, 48, 2);
			}else {
				if(Annimation.stopDialogue()) {
					Game.getPlayer().canMove = true;
					dialogue = 0;
					interacting = false;
					Game.level.haveFocus = false;
				}else dialogue = 5;
			}
			break;
		case 5: sayExplainChest(); break;
		case 6: sayExplainChest(); break;
		case 7:
			sayExplainChest();
			break;
		case 8: sayExplainChest(); break;
		case 10: saysTraining(); break;
		case 11: saysTraining(); break;
		case 12:
			saysTraining();
			break;
		case 13: saysTraining(); break;
			
			
			
		default:
			sayDialogue();
		}
return interacting;
}
	private void sayExplainChest() {
		if(dialogue == 1) {
			Annimation.startDialogue(OldManShowChest.length);
			Game.getPlayer().canMove = false;
		}else {
			if(Annimation.stopDialogue()) {
				Game.getPlayer().canMove = true;
				dialogue = 0;
			}else dialogue = 1;
		}
	}
	private void saysTraining() {
		if(dialogue == 1) {
			Annimation.startDialogue(OldManTraining.length);
			Game.getPlayer().canMove = false;
		}else {
			if(Annimation.stopDialogue()) {
				Game.getPlayer().canMove = true;
				dialogue = 0;
			}else dialogue = 1;
		}
	}
	public void explainLogicLevel() {
		dialogue2++;
		
		if(dialogue2 == 1) {
			Annimation.startDialogue(OldManExplain1.length);

		}else if(dialogue2 == 2){
			if(Annimation.stopDialogue()) {
				Annimation.startDialogue(OldManExplain2.length);
			}else dialogue2 = 1;
		}else {
			if(Annimation.stopDialogue()) {
				dialogue2 = 0;
			}else dialogue2 = 2;
		}
	}
	public void renderLogicLevel() {
		if (dialogue2 == 1)	renderDialogue(OldManExplain1);	
		if (dialogue2 == 2)	renderDialogue(OldManExplain2);
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
			case 2:
				if(dialogue == 1)	renderDialogue(oldManHello1);	
				if(dialogue == 2)	renderDialogue(oldManHello2);
				break;
			case 1:
				if(dialogue == 1)	renderDialogue(oldManHello3);
				break;
			case 4:
				if(dialogue == 1)	renderDialogue(OldManPresentation1);	
				if(dialogue == 2)	renderDialogue(OldManPresentation2);
				if(dialogue == 3)	renderDialogue(OldManPresentation3);	
				if(dialogue == 4)	renderDialogue(OldManPresentation4);
				if(dialogue == 5)	renderDialogue(OldManPresentation5);
				break;
			case 5:	if(dialogue == 1)	renderDialogue(OldManShowChest); break;
			case 6:	if(dialogue == 1)	renderDialogue(OldManShowChest); break;
			case 7:	if(dialogue == 1)	renderDialogue(OldManShowChest); break;
			case 8:	if(dialogue == 1)	renderDialogue(OldManShowChest); break;
			case 10: if (dialogue == 1)	renderDialogue(OldManTraining); break;
			case 11: if (dialogue == 1)	renderDialogue(OldManTraining); break;
			case 12: if (dialogue == 1)	renderDialogue(OldManTraining); break;
			case 13: if (dialogue == 1)	renderDialogue(OldManTraining); break;

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