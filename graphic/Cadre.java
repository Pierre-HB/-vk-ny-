package fr.pierrehb.graphic;

import fr.pierrehb.other.Polices.polices;

public class Cadre {
	private int xoffset;
	private int yoffset;
	private float[] background = new float[4];
	private float[] selecetedBackground = new float[4];
	private float[] color = new float[4];
	private float[] selectedColor = new float[4];
	private float[] nameColor = new float[4];
	private float[] selectedNameColor = new float[4];
	private float[] waitingColor = new float[4];
	private Caractere[] name;
	private boolean bigerSelected = false;
	private Texture charactere;
	private boolean control = false;
	private float[] taille = {7f,7f};
	private int xo;
	private int yo;
	private boolean waiting = false;

	public Cadre(int xoffset, int yoffset, String name, float[] background, float[] selectedBackground, float[] color, float[] selectedColor,float[] nameColor, float[] selectedNameColor, boolean biggerSelected) {
		for (int i = 0; i<3;i++) {
			this.background[i]=background[i];
			this.selecetedBackground[i]=selectedBackground[i];
			this.color[i]=color[i];
			this.selectedColor[i]=selectedColor[i];
			this.nameColor[i]=nameColor[i];
			this.selectedNameColor[i]=selectedNameColor[i];
		}
		this.xoffset=xoffset;
		this.yoffset=yoffset;
		
		this.name=Renderer.getText(0, 0, name, polices.COMIC_SANS_MS, 0.4f);
		this.bigerSelected=biggerSelected;	
	}
	public Cadre(int xoffset, int yoffset, Caractere[] name, float[] background, float[] selectedBackground, float[] color, float[] selectedColor,float[] nameColor, float[] selectedNameColor, boolean biggerSelected) {
		this.xoffset=xoffset;
		this.yoffset=yoffset;
		for (int i = 0; i<3;i++) {
			this.background[i]=background[i];
			this.selecetedBackground[i]=selectedBackground[i];
			this.color[i]=color[i];
			this.selectedColor[i]=selectedColor[i];
			this.nameColor[i]=nameColor[i];
			this.selectedNameColor[i]=selectedNameColor[i];
			
		}
		this.name=name;
		this.bigerSelected=biggerSelected;	
		}
	
	public Cadre(int xoffset, int yoffset, Caractere[] name,int xo_, int yo_, float[] background, float[] selectedBackground, float[] color, float[] selectedColor,float[] nameColor, float[] selectedNameColor, float[] waitingColor, Texture charactere_) {
		this.xoffset=xoffset;
		this.yoffset=yoffset;
		for (int i = 0; i<3;i++) {
			this.background[i]=background[i];
			this.selecetedBackground[i]=selectedBackground[i];
			this.color[i]=color[i];
			this.selectedColor[i]=selectedColor[i];
			this.nameColor[i]=nameColor[i];
			this.selectedNameColor[i]=selectedNameColor[i];
			this.waitingColor[i]=waitingColor[i];
		}
		this.name=name;
		this.bigerSelected=false;	
		control = true;
		this.charactere = charactere_;
		this.xo = xo_;
		this.yo = yo_;
		}
	
	public void render(int x, int y, boolean selected, float alpha) {
		if(control) {
			if (selected) {
				selecetedBackground[3]=alpha;
				selectedColor[3]=1f;
				selectedNameColor[3]=alpha;
				waitingColor[3]=alpha;

				
				if(waiting) {
					if(bigerSelected)Renderer.renderInterface(x-1, y-1, x+xoffset+1, y+yoffset+1, waitingColor);
					else Renderer.renderInterface(x, y, x+xoffset-yoffset-1, y+yoffset, waitingColor);
					Renderer.renderInterface(x+1, y+1, x+xoffset-1-yoffset-1, y+yoffset-1, selectedColor);
					Renderer.renderInterfaceTexte(name,waitingColor,(int)x+3,(int)y+yoffset-12);
					Renderer.renderTexture((float)x+xoffset-yoffset, (float)y, (float)x+xoffset, (float)y+yoffset, (float)xo, (float)yo, taille, charactere.getid(), waitingColor);
					
				}else {
				if(bigerSelected)Renderer.renderInterface(x-1, y-1, x+xoffset+1, y+yoffset+1, selecetedBackground);
				else Renderer.renderInterface(x, y, x+xoffset-yoffset-1, y+yoffset, selecetedBackground);
				Renderer.renderInterface(x+1, y+1, x+xoffset-1-yoffset-1, y+yoffset-1, selectedColor);
				Renderer.renderInterfaceTexte(name,selectedNameColor,(int)x+3,(int)y+yoffset-12);
				Renderer.renderTexture((float)x+xoffset-yoffset, (float)y, (float)x+xoffset, (float)y+yoffset, (float)xo, (float)yo, taille, charactere.getid(), selectedNameColor);
				}
			}else {
				background[3]=alpha;
				color[3]=1f;
				nameColor[3]=alpha;
				Renderer.renderInterface(x, y, x+xoffset-yoffset-1, y+yoffset, background);
				Renderer.renderInterface(x+1, y+1, x+xoffset-1-yoffset-1, y+yoffset-1, color);
				Renderer.renderInterfaceTexte(name,nameColor,(int)x+3,(int)y+yoffset-12);
				Renderer.renderTexture((float)x+xoffset-yoffset, (float)y, (float)x+xoffset, (float)y+yoffset, (float)xo, (float)yo, taille, charactere.getid(), nameColor);

			}
		}
		else {
		if (selected) {
			selecetedBackground[3]=alpha;
			if(bigerSelected)selectedColor[3]=alpha;
			else selectedColor[3]=1f;
			selectedNameColor[3]=alpha;
			
			if(bigerSelected)Renderer.renderInterface(x-1, y-1, x+xoffset+1, y+yoffset+1, selecetedBackground);
			else Renderer.renderInterface(x, y, x+xoffset, y+yoffset, selecetedBackground);
			Renderer.renderInterface(x+1, y+1, x+xoffset-1, y+yoffset-1, selectedColor);
			Renderer.renderInterfaceTexte(name,selectedNameColor,(int)x+3,(int)y+yoffset-12);
		}else {
			background[3]=alpha;
			if(bigerSelected)color[3]=alpha;
			else color[3]=1f;
			nameColor[3]=alpha;
			Renderer.renderInterface(x, y, x+xoffset, y+yoffset, background);
			Renderer.renderInterface(x+1, y+1, x+xoffset-1, y+yoffset-1, color);
			Renderer.renderInterfaceTexte(name,nameColor,(int)x+3,(int)y+yoffset-12);
		}
		}
		

		
	}
	public void waite() {
		waiting=true;
	}
	public void unWait() {
		waiting=false;
	}
	public void setLocation(int[] o) {
		this.xo=o[0];
		this.yo=o[1];
	}

}
