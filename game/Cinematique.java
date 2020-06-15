package fr.pierrehb.game;

import org.lwjgl.input.Keyboard;

import fr.pierrehb.entities.objects.Focus;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.levels.Level;
import fr.pierrehb.main.Main;

public class Cinematique {
	public static Level room;
	public static Focus focus;
	public static int timer = 0;
	public static int phase = 0;
	private static float[] screenColor = {0f,0f,0f,0f};
	
	public static void startIntro() {
		Game.INTRO = true;
		room = Game.getLevel(-1);
		room.startLevel();
		Game.level=room;
		focus = new Focus(200, 100);
		room.setFocus(focus);
		room.haveFocus=true;
		timer = 0;
		phase = 0;
		
	}
	public static void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && Main.compareName("PierreHB")) {
			timer=2100;
			phase=1;
		}
		timer++;
		if(phase == 0) {
			if(timer==320)room.speakToLevel(1);
			if(timer==430)room.speakToLevel(2);
			if(timer==700)room.speakToLevel(3);
			if(timer==900)room.speakToLevel(4);
			if(timer==1100)room.speakToLevel(5);
			if(timer==1150)room.speakToLevel(6);
			if(timer > 1250) {
				screenColor[3] = ((float)timer-1250f)/150f;
			}
			if(timer == 1400) {
				Main.save.avancement=1;
				screenColor[3]=0f;
				timer = 0;
				phase = 1;
				room.stopMusic();
				room = Game.getLevel(18);
				room.startLevel();
				Game.level=room;
				focus = new Focus(250, 116);
				room.setFocus(focus);
				room.haveFocus=true;
		}
		}else if(phase == 1) {
			if(timer==1)room.speakToLevel(1);
			if(timer==180)room.speakToLevel(2);
			if(timer==300)room.speakToLevel(3);
			if(timer==308)room.speakToLevel(4);
			if(timer==485)room.speakToLevel(5);
			if(timer==500)room.speakToLevel(6);
			if(timer==700)room.speakToLevel(7);
			if(timer==750)room.speakToLevel(8);
			if(timer==1000)room.speakToLevel(9);
			if(timer==1250)room.speakToLevel(10);
			
			
			if(timer==1300)room.speakToLevel(11);
			if(timer==1450)room.speakToLevel(12);
			if(timer==1470)room.speakToLevel(13);
			if(timer==1750)room.speakToLevel(14);
			if(timer==1850)room.speakToLevel(15);
			
			if(timer==2050) {
				room.speakToLevel(16);
				screenColor[0] = 1f;
				screenColor[1] = 1f;
				screenColor[2] = 1f;
			}
			if(timer > 2100) {
				screenColor[3] = ((float)timer-2100)/50;
			}
			if(timer == 2150) {
				room.speakToLevel(17);
				Game.INTRO=false;
				Main.save.avancement=2;
				room.stopMusic();
				screenColor[0] = 0f;
				screenColor[1] = 0f;
				screenColor[2] = 0f;
				screenColor[3] = 0f;		
				Game.nexLevel();
			}
		}
	}
	public static void render() {
		Renderer.renderInterface(0, 0, 256, 144, screenColor);
		
	}

}
