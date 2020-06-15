package fr.pierrehb.main;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;




public class Main {
	
	static DisplayMode[] modes;
	static int particleID = -2147483648;
	static String version = "Alpha_0.22";
	
	

public static void main(String[] args) {


	
	Main main = new Main();
	main.start();
	
		
		

	}
	
	// INITIALISATION
	public static ObjectOutputStream oos;
	public static ObjectInputStream ois;
	public static Save save = null;
	public static boolean running = false;
	public static int[] size = new int[2];
	public static DisplayMode mode;
	public static String name = "Évkïnyù";
	public static final double tick_per_second = 60.00;
	public static int images = 0;
	public static int nb_images = 0;
	public static int ritmes = 0;
	public static int nb_ritmes = 0;
	public static int time = 0;
	public static Game game;
	public static boolean first_lunch = false;

	public static int mortalID = -2147483648;
	public static ByteBuffer icon16;
	public static ByteBuffer icon32;
	public static ByteBuffer icon64;
	public static ByteBuffer icon128;
	
	
	private static boolean changeClass = false;
	private static boolean changeRoom = false;
	private static boolean changeCoord = false;

	

public Main() {
	initialization();
}

private void programeurIni() {
	if(changeClass) {
		
	}
	if(changeRoom) {
		save.room = 16;
	}
	if(changeCoord) {
		save.coordinates[0] = 210;
		save.coordinates[1] = 48;
	}
	
	
	
}
	
public static void loop() {
	
	long timer = System.currentTimeMillis();
	int ticks = 0;
	int frames = 0;
	long start_time = System.nanoTime();
	double elapsed = 0;
	double nanosecond_per_tick = 1000000000.00/tick_per_second;
	long now_time;
	
	
	while(running) {
		if(Display.isCloseRequested()) stop();
		
		Display.update();
		
		now_time = System.nanoTime();
		elapsed = now_time - start_time;
		if(elapsed > nanosecond_per_tick) {
			start_time += nanosecond_per_tick;
			update();
			ticks++;
		}else {
			render();
			frames++;
		}
		
		if(System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			time++;
			//Display.setTitle(name + " I ticks :  " + ticks + "  Frame : " + frames + " | secondes : " + time + " | width " + Display.getWidth() + " Height " + Display.getHeight() + " | version "+version);
			images += frames;
			ritmes += ticks;
			nb_images++;
			nb_ritmes++;			
			frames = 0;
			ticks = 0;
		}

	}end();
}

public static void view2D(int width, int height) {
	

	
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	GLU.gluOrtho2D(0, width, height, 0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	
	glEnable(GL_TEXTURE_2D);
	
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

}	
public static void update() {
	game.update();
}
public static void render() {
	view2D(save.size[0], save.size[1]);
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(0.0f, 0.0f, 0.0f, 1);
	game.render();
}
public static void end() {
	save();
	System.out.println("==================================================\n");
	System.out.println(" 	-FPS moyen : " + (images / nb_images)+"\n");
	System.out.println(" 	-ritme moyen : " + (ritmes / nb_ritmes)+"\n");
	System.out.println(" 	-duré du programe : " + time + "s\n");
	System.out.println("==================================================");
	Display.destroy();
	System.exit(0);
}
public void start() {
	running = true;
	game.start();
	programeurIni();
	loop();
}

public static void initialization() {
	load();
	game = new Game();
	size = save.size;
	mode = new DisplayMode(size[0], size[1]);
	try {
		Display.setDisplayMode(mode);
		Display.setResizable(false);
		Display.setFullscreen(true);
		Display.setTitle(name);
		Display.create();
		view2D(size[0], size[1]);
		icon16 = Texture.loadTexture("res/textures/Logo/Évköwn-16.png").getBuffer();
		icon32 = Texture.loadTexture("res/textures/Logo/Évköwn-32.png").getBuffer();
		icon64 = Texture.loadTexture("res/textures/Logo/Évköwn-64.png").getBuffer();
		icon128 = Texture.loadTexture("res/textures/Logo/Évköwn-128.png").getBuffer();
		Display.setInitialBackground(0.0f, 0.0f, 0.0f);
		Display.setIcon(new ByteBuffer[] {icon16, icon32, icon64, icon128});

		
		
	} catch (LWJGLException e) {
		e.printStackTrace();
	}
}

public static void stop() {
	Game.level.save();
	running = false;
}
public static void save() {
	try {
		 // on crée un flux entre le jeu et le dossier "Save.txt"
		  oos = new ObjectOutputStream(
		          new BufferedOutputStream(
		            new FileOutputStream(
		              new File("Save.txt"))));
		  // on enregistre l'objet "save" dans le dossier "Save.txt"
		  oos.writeObject(save);
		  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// on éssaye de fermer le flux
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
public static void load() {
	try {
		try {
			 // on crée un flux entre le jeu et le dossier "Save.txt"
			ois = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(
			              new File("Save.txt"))));
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			// Si on ne trouve pas le dossier (lors de la premiére partie) on crée un nouvel objet "Save"
			System.out.println("not found, create a new save");
			save = new Save();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// Si on trouve le dossier, on charge l'objet "save"
			save = (Save)ois.readObject();
		} catch (InvalidClassException e) {
			// Si on ne trouve pas une classe qui corréspond a notre objet "save", on crée un nouvel objet "save"
						save = new Save();
		}
		catch (ClassNotFoundException | IOException e) {
			System.out.println("fichier corrompus, on le recréé");
			save = new Save();

		} catch (NullPointerException e) {
			e.printStackTrace();

		}
	}
	finally {
		try {
			// on éssaye de fermer le flux
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();

		}

    }

}
public static Save getSave() {
	return save;
}
public static void reloadDisplay() {

	Display.destroy();
	initialization();
	Renderer.reload();

}
public static int getParticleID() {
	particleID++;
	if(particleID == 2147483647)particleID = -2147483648;
	return particleID;
}


public static int getMortalID() {
	mortalID++;
	if(mortalID == 2147483646)mortalID = -2147483648;
	return mortalID;
}

public static boolean compareName(String name) {
	boolean same=true;
	if(save.name.length()==name.length()) {
		for (int i = 0; i<name.length();i++) {
			if(save.name.charAt(i)!=name.charAt(i))same=false;
		}
		return same;
	}return false;
	
}
}