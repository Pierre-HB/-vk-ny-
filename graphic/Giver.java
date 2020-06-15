package fr.pierrehb.graphic;

import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;

public class Giver implements Color{
	private Caractere[] texte;
	private int x;
	private int y;
	private int timer=0;
	private float[] color = new float[4];
	private float[] backColor = new float[4];
	private float textLenght;
	private boolean visible;
	
	public Giver(String text, int x, int y, boolean visible) {
		this.texte=Renderer.getText(0, 0, text, Main.save.favorite_police, 0.2f);
		this.x=x;
		this.y=y;
		this.visible=visible;
		if (!visible) {
			this.timer=300;
		}
		color[0]=1f;
		color[1]=1f;
		color[2]=1f;
		color[3]=1f;
		backColor[0]=0f;
		backColor[1]=0f;
		backColor[2]=0f;
		backColor[3]=1f;
		for(Caractere chara : texte) textLenght = (chara.getCoord()[0]+chara.getOffset())*chara.getTaille();


	}
	public void update() {
		timer++;
		if(timer>300)visible=false;
		if(timer>150) {
			this.color[3]=1f-(timer-150)/150f;
			this.backColor[3]=1f-(timer-150)/150f;
		}
	}
	public void addY(int Y) {
		this.y+=Y;
	}
	public void render() {
		if(visible) {
		Renderer.renderInterface(x-1.5f, y-2.5f, textLenght+2.5f+x, y+6.5f, color);
		Renderer.renderInterface(x-1, y-2, textLenght+2+x, y+6, backColor);
		Renderer.renderInterfaceTexte(texte,color,x,y);
		}
	}

}
