package fr.pierrehb.attaques;

import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.attaques.attaque.Attack;

public class Attaque {
	public List<Attack> attaques = new ArrayList<Attack>();
	private int attaque = -1;
	public boolean done = false;
	public boolean doing = false;
	public boolean preparing = false;
	public boolean lock = true;
	public int reload = 1;
	public int load = 0;
	public float charge = 0.0f;
	public float xo = 0;
	public float yo = 4;
	
	
	public Attaque(List<Attack> Attaques) {
		this.attaques = Attaques;
		ini();
	}
	public void render() {
		if(preparing)attaques.get(0).render();
		if(doing)attaques.get(attaque).renderAttack();
	}
	public void update() {
		charge = (float)load/(float)reload;
			if(load >= reload) {
				load = reload;
				lock = false;
			}else load++;
			
		
		if(preparing)attaques.get(0).updatePreparing();
		if(doing) {
			if(attaque == -1) {
				attaques.get(0).startAttack();
				attaque = 0;
			}else {

				if(attaques.get(attaque).done) {
					attaques.get(attaque).stopAttack();
					attaques.get(attaque).done = false;
					if(attaque < attaques.size()-1) {
						attaque++;

						attaques.get(attaque).startAttack();
					}else {

						attaque = -1;
						doing = false;
						done = true;
					}}
			}

			if(doing)attaques.get(attaque).update();
			}
		}
	
	public void startAttaque() {
		preparing = false;
		if(!lock) {
		doing = true;
		lock = true;
		load = 0;
		}
	}
	public void prepareAttaque() {
		preparing = true;
	}
	public void ini() {
		for(Attack attack : attaques) {
			reload += attack.reload;
		}
		reload = (int)(3*reload/4);
		if(reload <=0)reload = 1;
		xo = attaques.get(0).xo;
		yo = attaques.get(0).yo;
	}
	public void reset() {
		for(Attack attack : attaques) attack.done = false;
		done = false;
	}
	public float getXo(int atk) {
		return attaques.get(atk).xo;
	}
	public float getYo(int atk) {
		return attaques.get(atk).yo;
	}
	

}
