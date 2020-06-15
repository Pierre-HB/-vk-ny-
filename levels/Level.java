package fr.pierrehb.levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Mortal_Particle;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.entities.Player;
import fr.pierrehb.entities.joueurs.Joueur;
import fr.pierrehb.entities.objects.Focus;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Interface;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Dialogue;
import fr.pierrehb.other.Polices;

public abstract class Level implements Color, Dialogue, Polices{

	protected int rez[] = Main.save.size;
	protected int[][] commands = Main.save.contrôles;
	public static Player player;
	public Map<Integer, Entity> objectList = new HashMap<>();
	public Map<Integer, Particle> particleList = new HashMap<>();
	public List<Integer> removeParticles = new ArrayList<Integer>();
	public Map<Integer, Entity> mortalObjects = new HashMap<>();
	public Map<Integer, Mortal_Particle> mortalParticles = new HashMap<>();
	public List<Human> humans = new ArrayList<Human>();
	public static boolean[][] solidList;
	public List<Entity> EntityForSummon = new ArrayList<Entity>();
	public List<Particle> ParticleForSummon = new ArrayList<Particle>();
	protected Entity target;
	public Focus focus;
	public boolean haveTarget = false;
	public boolean haveFocus = false;
	protected boolean STANDART_RENDER = true;
	protected boolean STANDART_UPDATE = true;
	


	public int[] size = new int[2];


	protected String playerName = Main.save.name;

	
	public Level() {
	}
	public void setTarget(Entity entity) {
		haveTarget = true;
		target = entity;
	}
	public void unSetTarget() {
		haveTarget = false;
	}
	public abstract void update();
	public abstract void render();
	public abstract float[] translateView();
	public void addParticle(Particle particle) {
		particleList.put(particle.id, particle);
		if(particle.mortal) mortalParticles.put(particle.id, (Mortal_Particle)particle);
	}
	public void removeParticle(int id) {
		particleList.remove(id);
	}
	public abstract boolean getSolid(int x, int y);
	public abstract boolean getSolidMoveX(float x, float y);// Pour les level énigmes;
	public abstract boolean getSolidMoveY(float x, float y);// Pour les level énigmes;
	public abstract void speakToLevel(int i);
	public abstract void interact();
	public abstract boolean giveDamageSquare(Quadrilataire hitBox, Entity attacker, int damage);
	public abstract boolean giveHeal(int[] hitBox, Entity attacker, int damage);
	public abstract void askForParty(Joueur demandeur);
	public abstract void nextLevel();
	public abstract Entity spawner(Entity entity);
	public abstract void startLevel();
	public void summonParticle(Particle particle) {
		ParticleForSummon.add(particle);
	}
	public void setFocus(Focus focus) {
		this.focus = focus;
	}
	public float[] getFocusCoord() {
		float[] coord = new float[2];
		if (haveTarget){
			
		}else if (haveFocus) {
			coord[0] = focus.x-Human.taille[0]/2;
			coord[1] = focus.y-Human.taille[1]/2;
			
		}else if(!STANDART_UPDATE){
			coord[0] = 240/2;
			coord[1] = 112/2;
		}else {
			coord[0] = player.x;
			coord[1] = player.y;
		}
		return coord;
	}
	protected abstract Interface getInter();
	public Interface getInterface() {
		return getInter();
		
	}
	public abstract void giveXpGold(int xp, int gold);
	public abstract void save();
	public abstract void loadingConsol();
	public abstract void stopMusic();


}
