package fr.pierrehb.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;

import fr.pierrehb.main.Main;
import fr.pierrehb.other.ObjectsStates.resolution;



public class Texture {
	
	
	int with;
	int height;
	int id;
	String path;
	static ByteBuffer buffer;
	private resolution rez;
	
	
	public Texture(int with, int height, int id, String path_) {
		this. with = with;
		this.height = height;
		this.id = id;
		this.path=path_;
		this.rez=Main.save.dimension;
	}
	
	public static Texture loadTexture(String path) {
		//on crée un buffer pour les pixels
		BufferedImage image = null;
		try {
			// on éssaye de lire le dossier contenant l'image
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// on définit les dimension de la texture en fonction du dossier source
		int w = image.getWidth();
		int h = image.getHeight();
		// on crée un tableau qui vas contenir tout les pixels de l'image
		int[] pixels = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);
		// on crée un buffer qui vas contenir TOUT les octets (4) de TOUT les pixels d l'image
		buffer = BufferUtils.createByteBuffer( h * w * 4 );
		
		// on parcour l'image pour prendre tout les pixel 1 ar 1
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int i = pixels[ x * h + y]; // sur la video il fait x + y* w
				// on place le Rouge, puis le Vert, puis le Bleu, puis l'Alpha de chaque pixel dans le buffer
				buffer.put((byte) ((i>>16) & 0xFF));
				buffer.put((byte) ((i>>8) & 0xFF));
				buffer.put((byte) ((i) & 0xFF));
				buffer.put((byte) ((i>>24) & 0xFF));

			}
		}
		// on fait dit au buffer qu'on arrete d'importer des information et on le passe ne mode "lecture"
		//buffer.flip();
		buffer = Fliper.flip(buffer);
		// on génére un id pour retrouver la texture dans LWJGL
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		// on redonne la texture, traiter pour s'afficher avec LWJGL
		return new Texture(w, h, id, path);
	}
	
	public int getWith() {
		actualise();
		return with;
	}
	public int getHeight() {
		actualise();
		return height;
	}
	public int getid() {
		actualise();
		return id;
	}
	public ByteBuffer getBuffer() {
		return buffer;
	}
	// on bind la texture pour savoir quel texture afficher avec le Renderer
	public void bind() {
		actualise();
		glBindTexture(GL_TEXTURE_2D, id);
	}
	// on désactive la texture pour liberer de la place en mémoir te pour pouvoir afficher une a&utre texture
	public void unbind() {
		actualise();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	private void actualise() {
		if(rez != Main.save.dimension) {
			Texture newTexture = loadTexture(path);
			this.height=newTexture.getHeight();
			this.with=newTexture.getWith();
			this.id=newTexture.getid();
			rez=Main.save.dimension;
		}
	}
}
