package fr.pierrehb.entities;

import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.other.Color;
import fr.pierrehb.other.Résolution;
import fr.pierrehb.other.TypeSquare;

public class Square implements TypeSquare, Résolution ,Color{
	private float x;
	private float y;
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float x3;
	private float y3;
	private float x4;
	private float y4;
	private float xi1;
	private float yi1;
	private float xi2;
	private float yi2;
	private float xi3;
	private float yi3;
	private float xi4;
	private float yi4;
	private float xb1;
	private float yb1;
	private float xb2;
	private float yb2;
	private float xb3;
	private float yb3;
	private float xb4;
	private float yb4;
	private float xCentre;
	private float yCentre;
	private float D;
	private float D1;
	private float D2;
	private float D3;
	private float D4;
	private float Di1;
	private float Di2;
	private float Di3;
	private float Di4;
	private float Db1;
	private float Db2;
	private float Db3;
	private float Db4;
	private float X,Y;
	private float X1,X2,X3,X4,Y1,Y2,Y3,Y4;
	private float Xi1,Xi2,Xi3,Xi4,Yi1,Yi2,Yi3,Yi4;
	private float Xb1,Xb2,Xb3,Xb4,Yb1,Yb2,Yb3,Yb4;
	private float[] Angles;
	private float[] Ds;
	private float angle_per_tick = (float)(Math.PI/60.0);
	public type_square type;
	private boolean solid;
	private boolean rotatingTrigo = false;
	private boolean rotatingDirect = false;
	private float offset;
	private boolean move;
	public boolean moving;


	public Square(float x, float y, float offset, type_square type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.offset = offset;
		
		switch (type) {
		case NONE:this.solid = true;break;
		case STANDART:this.solid = true;break;
		case RED:this.solid = true;break;
		case BLUE:this.solid = true;break;
		case GREEN:this.solid = true;break;
		case YELLOW:this.solid = true;break;
		case RED_TARGET:this.solid = false;break;
		case BLUE_TARGET:this.solid = false;break;
		case GREEN_TARGET:this.solid = false;break;
		case YELLOW_TARGET:this.solid = false;break;		
		}
		
	}
	public boolean getSolid() {
		return solid;
	}

	
	

	
	public void update() {
		if(rotatingTrigo) {
			for(int k = 0; k < Angles.length; k++) {
				Angles[k] += angle_per_tick;
			}
		}
		if(rotatingDirect) {
			for(int k = 0; k < Angles.length; k++) {
				Angles[k] -= angle_per_tick;
			}
		}
		if(rotatingTrigo||rotatingDirect) {

			switch (type) {
		case STANDART:
		x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
		x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
		x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
		x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
		x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
		xi1 = xCentre+(float)(Ds[5]*Math.cos(Angles[5]));
		xi2 = xCentre+(float)(Ds[6]*Math.cos(Angles[6]));
		xi3 = xCentre+(float)(Ds[7]*Math.cos(Angles[7]));
		xi4 = xCentre+(float)(Ds[8]*Math.cos(Angles[8]));
		y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
		y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
		y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
		y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
		y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
		yi1 = yCentre+(float)(Ds[5]*Math.sin(Angles[5]));
		yi2 = yCentre+(float)(Ds[6]*Math.sin(Angles[6]));
		yi3 = yCentre+(float)(Ds[7]*Math.sin(Angles[7]));
		yi4 = yCentre+(float)(Ds[8]*Math.sin(Angles[8]));
	
		break;
		case BLUE:				
			
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			break;
		case BLUE_TARGET:
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			xi1 = xCentre+(float)(Ds[5]*Math.cos(Angles[5]));
			xi2 = xCentre+(float)(Ds[6]*Math.cos(Angles[6]));
			xi3 = xCentre+(float)(Ds[7]*Math.cos(Angles[7]));
			xi4 = xCentre+(float)(Ds[8]*Math.cos(Angles[8]));
			xb1 = xCentre+(float)(Ds[9]*Math.cos(Angles[9]));
			xb2 = xCentre+(float)(Ds[10]*Math.cos(Angles[10]));
			xb3 = xCentre+(float)(Ds[11]*Math.cos(Angles[11]));
			xb4 = xCentre+(float)(Ds[12]*Math.cos(Angles[12]));
			
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			yi1 = yCentre+(float)(Ds[5]*Math.sin(Angles[5]));
			yi2 = yCentre+(float)(Ds[6]*Math.sin(Angles[6]));
			yi3 = yCentre+(float)(Ds[7]*Math.sin(Angles[7]));
			yi4 = yCentre+(float)(Ds[8]*Math.sin(Angles[8]));
			yb1 = yCentre+(float)(Ds[9]*Math.sin(Angles[9]));
			yb2 = yCentre+(float)(Ds[10]*Math.sin(Angles[10]));
			yb3 = yCentre+(float)(Ds[11]*Math.sin(Angles[11]));
			yb4 = yCentre+(float)(Ds[12]*Math.sin(Angles[12]));
			
			
			
			
			break;
		case GREEN:
			

			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			break;
		case GREEN_TARGET:
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			xi1 = xCentre+(float)(Ds[5]*Math.cos(Angles[5]));
			xi2 = xCentre+(float)(Ds[6]*Math.cos(Angles[6]));
			xi3 = xCentre+(float)(Ds[7]*Math.cos(Angles[7]));
			xi4 = xCentre+(float)(Ds[8]*Math.cos(Angles[8]));
			xb1 = xCentre+(float)(Ds[9]*Math.cos(Angles[9]));
			xb2 = xCentre+(float)(Ds[10]*Math.cos(Angles[10]));
			xb3 = xCentre+(float)(Ds[11]*Math.cos(Angles[11]));
			xb4 = xCentre+(float)(Ds[12]*Math.cos(Angles[12]));
			
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			yi1 = yCentre+(float)(Ds[5]*Math.sin(Angles[5]));
			yi2 = yCentre+(float)(Ds[6]*Math.sin(Angles[6]));
			yi3 = yCentre+(float)(Ds[7]*Math.sin(Angles[7]));
			yi4 = yCentre+(float)(Ds[8]*Math.sin(Angles[8]));
			yb1 = yCentre+(float)(Ds[9]*Math.sin(Angles[9]));
			yb2 = yCentre+(float)(Ds[10]*Math.sin(Angles[10]));
			yb3 = yCentre+(float)(Ds[11]*Math.sin(Angles[11]));
			yb4 = yCentre+(float)(Ds[12]*Math.sin(Angles[12]));
			break;
		case NONE:
			break;
		case RED:
			
			
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			break;
		case RED_TARGET:
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			xi1 = xCentre+(float)(Ds[5]*Math.cos(Angles[5]));
			xi2 = xCentre+(float)(Ds[6]*Math.cos(Angles[6]));
			xi3 = xCentre+(float)(Ds[7]*Math.cos(Angles[7]));
			xi4 = xCentre+(float)(Ds[8]*Math.cos(Angles[8]));
			xb1 = xCentre+(float)(Ds[9]*Math.cos(Angles[9]));
			xb2 = xCentre+(float)(Ds[10]*Math.cos(Angles[10]));
			xb3 = xCentre+(float)(Ds[11]*Math.cos(Angles[11]));
			xb4 = xCentre+(float)(Ds[12]*Math.cos(Angles[12]));
			
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			yi1 = yCentre+(float)(Ds[5]*Math.sin(Angles[5]));
			yi2 = yCentre+(float)(Ds[6]*Math.sin(Angles[6]));
			yi3 = yCentre+(float)(Ds[7]*Math.sin(Angles[7]));
			yi4 = yCentre+(float)(Ds[8]*Math.sin(Angles[8]));
			yb1 = yCentre+(float)(Ds[9]*Math.sin(Angles[9]));
			yb2 = yCentre+(float)(Ds[10]*Math.sin(Angles[10]));
			yb3 = yCentre+(float)(Ds[11]*Math.sin(Angles[11]));
			yb4 = yCentre+(float)(Ds[12]*Math.sin(Angles[12]));
			break;
		case YELLOW:

			

			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			break;
		case YELLOW_TARGET:
			x = xCentre+(float)(Ds[0]*Math.cos(Angles[0]));
			x1 = xCentre+(float)(Ds[1]*Math.cos(Angles[1]));
			x2 = xCentre+(float)(Ds[2]*Math.cos(Angles[2]));
			x3 = xCentre+(float)(Ds[3]*Math.cos(Angles[3]));
			x4 = xCentre+(float)(Ds[4]*Math.cos(Angles[4]));
			xi1 = xCentre+(float)(Ds[5]*Math.cos(Angles[5]));
			xi2 = xCentre+(float)(Ds[6]*Math.cos(Angles[6]));
			xi3 = xCentre+(float)(Ds[7]*Math.cos(Angles[7]));
			xi4 = xCentre+(float)(Ds[8]*Math.cos(Angles[8]));
			xb1 = xCentre+(float)(Ds[9]*Math.cos(Angles[9]));
			xb2 = xCentre+(float)(Ds[10]*Math.cos(Angles[10]));
			xb3 = xCentre+(float)(Ds[11]*Math.cos(Angles[11]));
			xb4 = xCentre+(float)(Ds[12]*Math.cos(Angles[12]));			
			y = yCentre+(float)(Ds[0]*Math.sin(Angles[0]));
			y1 = yCentre+(float)(Ds[1]*Math.sin(Angles[1]));
			y2 = yCentre+(float)(Ds[2]*Math.sin(Angles[2]));
			y3 = yCentre+(float)(Ds[3]*Math.sin(Angles[3]));
			y4 = yCentre+(float)(Ds[4]*Math.sin(Angles[4]));
			yi1 = yCentre+(float)(Ds[5]*Math.sin(Angles[5]));
			yi2 = yCentre+(float)(Ds[6]*Math.sin(Angles[6]));
			yi3 = yCentre+(float)(Ds[7]*Math.sin(Angles[7]));
			yi4 = yCentre+(float)(Ds[8]*Math.sin(Angles[8]));
			yb1 = yCentre+(float)(Ds[9]*Math.sin(Angles[9]));
			yb2 = yCentre+(float)(Ds[10]*Math.sin(Angles[10]));
			yb3 = yCentre+(float)(Ds[11]*Math.sin(Angles[11]));
			yb4 = yCentre+(float)(Ds[12]*Math.sin(Angles[12]));
			break;
		default:
			break;
	}
		}
		
		else {
			switch (type) {
			
			case STANDART:
			x1 = (x-offset);
			x2 = (x+offset);
			x3 = (x+offset);
			x4 = (x-offset);
			y1 = (y-offset);
			y2 = (y-offset);
			y3 = (y+offset);
			y4 = (y+offset);
			xi1 = (x-offset+1);
			xi2 = (x+offset-1);
			xi3 = (x+offset-1);
			xi4 = (x-offset+1);
			yi1 = (y-offset+1);
			yi2 = (y-offset+1);
			yi3 = (y+offset-1);
			yi4 = (y+offset-1);
			break;
			case BLUE:				
				for(int v = 0; v < 10; v++) {
					if(!Game.level.getSolidMoveX(x-0.1f, y)) {
						x-=0.1f;
						move = true;
					}
					}
				x1 = (x-offset+1);
				x2 = (x+offset-1);
				x3 = (x+offset-1);
				x4 = (x-offset+1);
				y1 = (y-offset+1);
				y2 = (y-offset+1);
				y3 = (y+offset-1);
				y4 = (y+offset-1);
				break;
			case BLUE_TARGET:
				x1 = (x-offset*0.7f);
				x2 = (x+offset*0.7f);
				x3 = (x+offset*0.7f);
				x4 = (x-offset*0.7f);
				y1 = (y-offset*0.7f);
				y2 = (y-offset*0.7f);
				y3 = (y+offset*0.7f);
				y4 = (y+offset*0.7f);
				xi1 = (x-offset*0.6f);
				xi2 = (x+offset*0.6f);
				xi3 = (x+offset*0.6f);
				xi4 = (x-offset*0.6f);
				yi1 = (y-offset*0.6f);
				yi2 = (y-offset*0.6f);
				yi3 = (y+offset*0.6f);
				yi4 = (y+offset*0.6f);
				xb1 = (x-offset*0.3f);
				xb2 = (x+offset*0.3f);
				xb3 = (x+offset*0.3f);
				xb4 = (x-offset*0.3f);
				yb1 = (y-offset*0.3f);
				yb2 = (y-offset*0.3f);
				yb3 = (y+offset*0.3f);
				yb4 = (y+offset*0.3f);
				break;
			case GREEN:
				for(int v = 0; v < 10; v++) {
					if(!Game.level.getSolidMoveX(x+0.1f, y)) {
						move = true;
						x+=0.1f;
					}
					}

				x1 = (x-offset+1);
				x2 = (x+offset-1);
				x3 = (x+offset-1);
				x4 = (x-offset+1);
				y1 = (y-offset+1);
				y2 = (y-offset+1);
				y3 = (y+offset-1);
				y4 = (y+offset-1);
				break;
			case GREEN_TARGET:
				x1 = (x-offset*0.7f);
				x2 = (x+offset*0.7f);
				x3 = (x+offset*0.7f);
				x4 = (x-offset*0.7f);
				y1 = (y-offset*0.7f);
				y2 = (y-offset*0.7f);
				y3 = (y+offset*0.7f);
				y4 = (y+offset*0.7f);
				xi1 = (x-offset*0.6f);
				xi2 = (x+offset*0.6f);
				xi3 = (x+offset*0.6f);
				xi4 = (x-offset*0.6f);
				yi1 = (y-offset*0.6f);
				yi2 = (y-offset*0.6f);
				yi3 = (y+offset*0.6f);
				yi4 = (y+offset*0.6f);
				xb1 = (x-offset*0.3f);
				xb2 = (x+offset*0.3f);
				xb3 = (x+offset*0.3f);
				xb4 = (x-offset*0.3f);
				yb1 = (y-offset*0.3f);
				yb2 = (y-offset*0.3f);
				yb3 = (y+offset*0.3f);
				yb4 = (y+offset*0.3f);
				break;
			case NONE:
				break;
			case RED:
				
				for(int v = 0; v < 10; v++) {
					if(!Game.level.getSolidMoveY(x, y-0.1f)) {
						move = true;
						y-=0.1f;
					}
					}
				x1 = (x-offset+1);
				x2 = (x+offset-1);
				x3 = (x+offset-1);
				x4 = (x-offset+1);
				y1 = (y-offset+1);
				y2 = (y-offset+1);
				y3 = (y+offset-1);
				y4 = (y+offset-1);
				break;
			case RED_TARGET:
				x1 = (x-offset*0.7f);
				x2 = (x+offset*0.7f);
				x3 = (x+offset*0.7f);
				x4 = (x-offset*0.7f);
				y1 = (y-offset*0.7f);
				y2 = (y-offset*0.7f);
				y3 = (y+offset*0.7f);
				y4 = (y+offset*0.7f);
				xi1 = (x-offset*0.6f);
				xi2 = (x+offset*0.6f);
				xi3 = (x+offset*0.6f);
				xi4 = (x-offset*0.6f);
				yi1 = (y-offset*0.6f);
				yi2 = (y-offset*0.6f);
				yi3 = (y+offset*0.6f);
				yi4 = (y+offset*0.6f);
				xb1 = (x-offset*0.3f);
				xb2 = (x+offset*0.3f);
				xb3 = (x+offset*0.3f);
				xb4 = (x-offset*0.3f);
				yb1 = (y-offset*0.3f);
				yb2 = (y-offset*0.3f);
				yb3 = (y+offset*0.3f);
				yb4 = (y+offset*0.3f);
				break;
			case YELLOW:

				for(int v = 0; v < 10; v++) {
					if(!Game.level.getSolidMoveY(x, y+0.1f)) {
						move = true;
						y+=0.1f;
					}
				}

				x1 = (x-offset+1);
				x2 = (x+offset-1);
				x3 = (x+offset-1);
				x4 = (x-offset+1);
				y1 = (y-offset+1);
				y2 = (y-offset+1);
				y3 = (y+offset-1);
				y4 = (y+offset-1);
				break;
			case YELLOW_TARGET:
				x1 = (x-offset*0.7f);
				x2 = (x+offset*0.7f);
				x3 = (x+offset*0.7f);
				x4 = (x-offset*0.7f);
				y1 = (y-offset*0.7f);
				y2 = (y-offset*0.7f);
				y3 = (y+offset*0.7f);
				y4 = (y+offset*0.7f);
				xi1 = (x-offset*0.6f);
				xi2 = (x+offset*0.6f);
				xi3 = (x+offset*0.6f);
				xi4 = (x-offset*0.6f);
				yi1 = (y-offset*0.6f);
				yi2 = (y-offset*0.6f);
				yi3 = (y+offset*0.6f);
				yi4 = (y+offset*0.6f);
				xb1 = (x-offset*0.3f);
				xb2 = (x+offset*0.3f);
				xb3 = (x+offset*0.3f);
				xb4 = (x-offset*0.3f);
				yb1 = (y-offset*0.3f);
				yb2 = (y-offset*0.3f);
				yb3 = (y+offset*0.3f);
				yb4 = (y+offset*0.3f);
				break;
			default:
				break;
		}
		}
		if(move)moving = true;
		else moving = false;
		move = false;
	}

	
	public void render() {
		
		switch (type) {
		case NONE:break;
		case STANDART:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, WHITE);
			Renderer.renderSquare(xi1, xi2, xi3, xi4, yi1, yi2, yi3, yi4, BLACK);
			
		break;
		case RED:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, RED);

		break;
		case BLUE:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, BLUE);

		break;
		case GREEN:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, GREEN);

		break;
		case YELLOW:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, GOLD);

		break;
		case RED_TARGET:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, RED);
			Renderer.renderSquare(xi1, xi2, xi3, xi4, yi1, yi2, yi3, yi4, BLACK);
			Renderer.renderSquare(xb1, xb2, xb3, xb4, yb1, yb2, yb3, yb4, RED);

		break;
		case BLUE_TARGET:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, BLUE);
			Renderer.renderSquare(xi1, xi2, xi3, xi4, yi1, yi2, yi3, yi4, BLACK);
			Renderer.renderSquare(xb1, xb2, xb3, xb4, yb1, yb2, yb3, yb4, BLUE);
		
		break;
		case GREEN_TARGET:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, GREEN);
			Renderer.renderSquare(xi1, xi2, xi3, xi4, yi1, yi2, yi3, yi4, BLACK);
			Renderer.renderSquare(xb1, xb2, xb3, xb4, yb1, yb2, yb3, yb4, GREEN);
		
		break;
		case YELLOW_TARGET:
			Renderer.renderSquare(x1, x2, x3, x4, y1, y2, y3, y4, GOLD);
			Renderer.renderSquare(xi1, xi2, xi3, xi4, yi1, yi2, yi3, yi4, BLACK);
			Renderer.renderSquare(xb1, xb2, xb3, xb4, yb1, yb2, yb3, yb4, GOLD);
		
		break;
		}
				
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float X) {
		x = X;
	}
	public void setY(float Y) {
		y = Y;
	}
	public void startRotating(rotating rotat, float xc, float yc) {
		switch (rotat) {
		case TRIGO:
			rotatingTrigo = true;
			rotatingDirect = false;

		break;
		case DIRECT:
		rotatingDirect = true;
		rotatingTrigo = false;

		}
		xCentre = xc;
		yCentre = yc;
		switch (type) {
		case NONE:break;
		case STANDART:
			Ds = new float[9];
			Angles = new float[9];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Xi1 = xi1-xCentre;
			Xi2 = xi2-xCentre;
			Xi3 = xi3-xCentre;
			Xi4 = xi4-xCentre;
			Yi1 = yi1-yCentre;
			Yi2 = yi2-yCentre;
			Yi3 = yi3-yCentre;
			Yi4 = yi4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Ds[5] = Di1 = (float)Math.sqrt((Xi1*Xi1)+(Yi1*Yi1)); 
			Ds[6] = Di2 = (float)Math.sqrt((Xi2*Xi2)+(Yi2*Yi2)); 
			Ds[7] = Di3 = (float)Math.sqrt((Xi3*Xi3)+(Yi3*Yi3)); 
			Ds[8] = Di4 = (float)Math.sqrt((Xi4*Xi4)+(Yi4*Yi4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
			Angles[5] = getAngle(Xi1, Yi1, Di1);
			Angles[6] = getAngle(Xi2, Yi2, Di2);
			Angles[7] = getAngle(Xi3, Yi3, Di3);
			Angles[8] = getAngle(Xi4, Yi4, Di4);
		
		break;
		case RED:
			Ds = new float[5];
			Angles = new float[5];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4)); 
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
		break;
		case BLUE:
			Ds = new float[5];
			Angles = new float[5];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
		break;
		case GREEN:
			Ds = new float[5];
			Angles = new float[5];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
		break;
		case YELLOW:
			Ds = new float[5];
			Angles = new float[5];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
		break;
		case RED_TARGET:
			Ds = new float[13];
			Angles = new float[13];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Xi1 = xi1-xCentre;
			Xi2 = xi2-xCentre;
			Xi3 = xi3-xCentre;
			Xi4 = xi4-xCentre;
			Yi1 = yi1-yCentre;
			Yi2 = yi2-yCentre;
			Yi3 = yi3-yCentre;
			Yi4 = yi4-yCentre;
			Xb1 = xb1-xCentre;
			Xb2 = xb2-xCentre;
			Xb3 = xb3-xCentre;
			Xb4 = xb4-xCentre;
			Yb1 = yb1-yCentre;
			Yb2 = yb2-yCentre;
			Yb3 = yb3-yCentre;
			Yb4 = yb4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Ds[5] = Di1 = (float)Math.sqrt((Xi1*Xi1)+(Yi1*Yi1)); 
			Ds[6] = Di2 = (float)Math.sqrt((Xi2*Xi2)+(Yi2*Yi2)); 
			Ds[7] = Di3 = (float)Math.sqrt((Xi3*Xi3)+(Yi3*Yi3)); 
			Ds[8] = Di4 = (float)Math.sqrt((Xi4*Xi4)+(Yi4*Yi4));
			Ds[9] = Db1 = (float)Math.sqrt((Xb1*Xb1)+(Yb1*Yb1)); 
			Ds[10] = Db2 = (float)Math.sqrt((Xb2*Xb2)+(Yb2*Yb2)); 
			Ds[11] = Db3 = (float)Math.sqrt((Xb3*Xb3)+(Yb3*Yb3)); 
			Ds[12] = Db4 = (float)Math.sqrt((Xb4*Xb4)+(Yb4*Yb4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
			Angles[5] = getAngle(Xi1, Yi1, Di1);
			Angles[6] = getAngle(Xi2, Yi2, Di2);
			Angles[7] = getAngle(Xi3, Yi3, Di3);
			Angles[8] = getAngle(Xi4, Yi4, Di4);
			Angles[9] = getAngle(Xb1, Yb1, Db1);
			Angles[10] = getAngle(Xb2, Yb2, Db2);
			Angles[11] = getAngle(Xb3, Yb3, Db3);
			Angles[12] = getAngle(Xb4, Yb4, Db4);

		break;
		case BLUE_TARGET:
			Ds = new float[13];
			Angles = new float[13];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Xi1 = xi1-xCentre;
			Xi2 = xi2-xCentre;
			Xi3 = xi3-xCentre;
			Xi4 = xi4-xCentre;
			Yi1 = yi1-yCentre;
			Yi2 = yi2-yCentre;
			Yi3 = yi3-yCentre;
			Yi4 = yi4-yCentre;
			Xb1 = xb1-xCentre;
			Xb2 = xb2-xCentre;
			Xb3 = xb3-xCentre;
			Xb4 = xb4-xCentre;
			Yb1 = yb1-yCentre;
			Yb2 = yb2-yCentre;
			Yb3 = yb3-yCentre;
			Yb4 = yb4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Ds[5] = Di1 = (float)Math.sqrt((Xi1*Xi1)+(Yi1*Yi1)); 
			Ds[6] = Di2 = (float)Math.sqrt((Xi2*Xi2)+(Yi2*Yi2)); 
			Ds[7] = Di3 = (float)Math.sqrt((Xi3*Xi3)+(Yi3*Yi3)); 
			Ds[8] = Di4 = (float)Math.sqrt((Xi4*Xi4)+(Yi4*Yi4));
			Ds[9]  = Db1 = (float)Math.sqrt((Xb1*Xb1)+(Yb1*Yb1)); 
			Ds[10] = Db2 = (float)Math.sqrt((Xb2*Xb2)+(Yb2*Yb2)); 
			Ds[11] = Db3 = (float)Math.sqrt((Xb3*Xb3)+(Yb3*Yb3)); 
			Ds[12] = Db4 = (float)Math.sqrt((Xb4*Xb4)+(Yb4*Yb4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
			Angles[5] = getAngle(Xi1, Yi1, Di1);
			Angles[6] = getAngle(Xi2, Yi2, Di2);
			Angles[7] = getAngle(Xi3, Yi3, Di3);
			Angles[8] = getAngle(Xi4, Yi4, Di4);
			Angles[9]  = getAngle(Xb1, Yb1, Db1);
			Angles[10] = getAngle(Xb2, Yb2, Db2);
			Angles[11] = getAngle(Xb3, Yb3, Db3);
			Angles[12] = getAngle(Xb4, Yb4, Db4);
		
		break;
		case GREEN_TARGET:
			Ds = new float[13];
			Angles = new float[13];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Xi1 = xi1-xCentre;
			Xi2 = xi2-xCentre;
			Xi3 = xi3-xCentre;
			Xi4 = xi4-xCentre;
			Yi1 = yi1-yCentre;
			Yi2 = yi2-yCentre;
			Yi3 = yi3-yCentre;
			Yi4 = yi4-yCentre;
			Xb1 = xb1-xCentre;
			Xb2 = xb2-xCentre;
			Xb3 = xb3-xCentre;
			Xb4 = xb4-xCentre;
			Yb1 = yb1-yCentre;
			Yb2 = yb2-yCentre;
			Yb3 = yb3-yCentre;
			Yb4 = yb4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Ds[5] = Di1 = (float)Math.sqrt((Xi1*Xi1)+(Yi1*Yi1)); 
			Ds[6] = Di2 = (float)Math.sqrt((Xi2*Xi2)+(Yi2*Yi2)); 
			Ds[7] = Di3 = (float)Math.sqrt((Xi3*Xi3)+(Yi3*Yi3)); 
			Ds[8] = Di4 = (float)Math.sqrt((Xi4*Xi4)+(Yi4*Yi4));
			Ds[9] = Db1 = (float)Math.sqrt((Xb1*Xb1)+(Yb1*Yb1)); 
			Ds[10] = Db2 = (float)Math.sqrt((Xb2*Xb2)+(Yb2*Yb2)); 
			Ds[11] = Db3 = (float)Math.sqrt((Xb3*Xb3)+(Yb3*Yb3)); 
			Ds[12] = Db4 = (float)Math.sqrt((Xb4*Xb4)+(Yb4*Yb4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
			Angles[5] = getAngle(Xi1, Yi1, Di1);
			Angles[6] = getAngle(Xi2, Yi2, Di2);
			Angles[7] = getAngle(Xi3, Yi3, Di3);
			Angles[8] = getAngle(Xi4, Yi4, Di4);
			Angles[9] = getAngle(Xb1, Yb1, Db1);
			Angles[10] = getAngle(Xb2, Yb2, Db2);
			Angles[11] = getAngle(Xb3, Yb3, Db3);
			Angles[12] = getAngle(Xb4, Yb4, Db4);
		
		break;
		case YELLOW_TARGET:
			Ds = new float[13];
			Angles = new float[13];
			X = x-xCentre;
			Y = y-yCentre;
			X1 = x1-xCentre;
			X2 = x2-xCentre;
			X3 = x3-xCentre;
			X4 = x4-xCentre;
			Y1 = y1-yCentre;
			Y2 = y2-yCentre;
			Y3 = y3-yCentre;
			Y4 = y4-yCentre;
			Xi1 = xi1-xCentre;
			Xi2 = xi2-xCentre;
			Xi3 = xi3-xCentre;
			Xi4 = xi4-xCentre;
			Yi1 = yi1-yCentre;
			Yi2 = yi2-yCentre;
			Yi3 = yi3-yCentre;
			Yi4 = yi4-yCentre;
			Xb1 = xb1-xCentre;
			Xb2 = xb2-xCentre;
			Xb3 = xb3-xCentre;
			Xb4 = xb4-xCentre;
			Yb1 = yb1-yCentre;
			Yb2 = yb2-yCentre;
			Yb3 = yb3-yCentre;
			Yb4 = yb4-yCentre;
			Ds[0] = D = (float)Math.sqrt((X*X)+(Y*Y));
			Ds[1] = D1 = (float)Math.sqrt((X1*X1)+(Y1*Y1)); 
			Ds[2] = D2 = (float)Math.sqrt((X2*X2)+(Y2*Y2)); 
			Ds[3] = D3 = (float)Math.sqrt((X3*X3)+(Y3*Y3)); 
			Ds[4] = D4 = (float)Math.sqrt((X4*X4)+(Y4*Y4));
			Ds[5] = Di1 = (float)Math.sqrt((Xi1*Xi1)+(Yi1*Yi1)); 
			Ds[6] = Di2 = (float)Math.sqrt((Xi2*Xi2)+(Yi2*Yi2)); 
			Ds[7] = Di3 = (float)Math.sqrt((Xi3*Xi3)+(Yi3*Yi3)); 
			Ds[8] = Di4 = (float)Math.sqrt((Xi4*Xi4)+(Yi4*Yi4));
			Ds[9] = Db1 = (float)Math.sqrt((Xb1*Xb1)+(Yb1*Yb1)); 
			Ds[10] = Db2 = (float)Math.sqrt((Xb2*Xb2)+(Yb2*Yb2)); 
			Ds[11] = Db3 = (float)Math.sqrt((Xb3*Xb3)+(Yb3*Yb3)); 
			Ds[12] = Db4 = (float)Math.sqrt((Xb4*Xb4)+(Yb4*Yb4));
			Angles[0] = getAngle(X, Y, D);
			Angles[1] = getAngle(X1, Y1, D1);
			Angles[2] = getAngle(X2, Y2, D2);
			Angles[3] = getAngle(X3, Y3, D3);
			Angles[4] = getAngle(X4, Y4, D4);
			Angles[5] = getAngle(Xi1, Yi1, Di1);
			Angles[6] = getAngle(Xi2, Yi2, Di2);
			Angles[7] = getAngle(Xi3, Yi3, Di3);
			Angles[8] = getAngle(Xi4, Yi4, Di4);
			Angles[9] = getAngle(Xb1, Yb1, Db1);
			Angles[10] = getAngle(Xb2, Yb2, Db2);
			Angles[11] = getAngle(Xb3, Yb3, Db3);
			Angles[12] = getAngle(Xb4, Yb4, Db4);
		break;
		}
		
		
	}
	public void stopRotating() {
		rotatingTrigo = false;
		rotatingDirect = false;
	}
	private float getAngle(float X, float Y, float D) {
		if(D==0)return 0.0f;
		float Angle = (float)Math.acos(Math.abs(X)/D);
		if(X<0) Angle=(float)(Math.PI-Angle);

		if(Y<0) Angle = -Angle;
		return Angle;
	}
	

}
