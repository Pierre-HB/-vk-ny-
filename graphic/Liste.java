package fr.pierrehb.graphic;

import fr.pierrehb.other.Color;

public class Liste implements Color{
	private int i=0;
	private Cadre[] liste;
	private float x,y;
	private float transition = 0f;
	private int direction = 1;
	private boolean moving = false;
	private boolean speedMoving = false;
	private float pas = 0.1f;
	private float bigPas = 0.6f;
	private float alpha = 1f;
	public int length=0;
	

	public Liste(Cadre[] liste, float x, float y) {
		this.liste = liste;
		this.x = x;
		this.y = y;
		this.length=liste.length;
	}
	public Liste(Cadre[] liste, float x, float y, int start) {
		this.liste = liste;
		this.x = x;
		this.y = y;
		this.length=liste.length;
		if (start<0)start=0;
		if(start>=length)start=length-1;
		this.i=start;
		this.transition=start;
		
	}

	
	public void render() {
		for (int j =0;j<liste.length;j++) {
			if(Math.abs(j-i)<=3||speedMoving) {
			float yoffset = (j-transition)*21+y;
			if(j==i) {
				liste[j].render((int)x, (int)(yoffset), true, 1.0f);

			}else {
				if(Math.abs(j-i)>=2) {
					if(Math.abs(j-i)==2) {
						if(j-i>0 && direction >0)alpha=(Math.abs(transition-i+direction*1.1f))/1.4f;
						else if(j-i<0 && direction <0)alpha=(Math.abs(transition-i+direction*1.1f))/1.4f;
						else alpha=(Math.abs(transition-i)+1.1f)/1.4f;
					}
					else {
					if(j-i>0 && direction >0)alpha=0.1f-(Math.abs(transition-i+direction*0.1f))/1.4f;
					else if(j-i<0 && direction <0)alpha=0.1f-(Math.abs(transition-i+direction*0.1f))/1.4f;
					else alpha=(Math.abs(transition-i)+0.1f)/1.4f;
					}
				
				}else {
					alpha = 1.0f;
	
				}
				liste[j].render((int)x, (int)(yoffset), false, alpha);

			}
			
		}}
		
	}
	public void update() {
		if(moving) {
			transition+=direction*pas;
			if((direction > 0 && transition > i)||(direction < 0 && transition < i)) {
				moving = false;
				transition = (float)i;
			}
		}else if(speedMoving) {
			transition+=direction*bigPas;
			if((direction > 0 && transition > i)||(direction < 0 && transition < i)) {
				speedMoving = false;
				transition = (float)i;
		}}
		
	}
	public void next() {
		if(i<liste.length-1) {
			moving=true;
			i++;
			direction = 1;
		}else {
			i=0;
			speedMoving=true;
			direction = -1;
		}
		
		
	}
	public void previous() {
		if(i>0) {
			moving = true;
			i--;
			direction = -1;
		}else {
			i = length-1;
			speedMoving=true;
			direction = 1;
		}
		
	}
	public int select() {
		return i;
	}
	public void reset() {
		this.i=0;
		this.transition=0;
	}

}
