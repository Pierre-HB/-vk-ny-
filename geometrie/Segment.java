package fr.pierrehb.geometrie;

public class Segment {
	public Point p1;
	public Point p2;
	public Courbe_Affine c;
	public boolean vertical = false;
	public boolean horizontal = false;
	
	public Segment(Point P1, Point P2) {
		if(P1.x<P2.x) {
			this.p1 = P1;
			this.p2 = P2;	
		}else {
			this.p1 = P2;
			this.p2 = P1;
		}
		this.c = new Courbe_Affine(p1, p2);
		vertical = c.vertical;
		horizontal = c.horizontal;
	}
	
	public boolean definitX(float X) {
		if(p1.x <= X && p2.x >= X) return true;
		else return false;
	}
	public boolean definitY(float Y) {
		if(p1.y<p2.y) {
			if(p1.y <= Y && p2.y >= Y) return true;
			else return false;
		}else {
			if(p1.y >= Y && p2.y <= Y) return true;
			else return false;
	}}
	public float getX(float Y) {
		if(!definitY(Y)) return -2147483648;
		else return c.getX(Y);
	}
	public float getY(float X) {
		if(!definitX(X)) {
			return -2147483648;
		}
		else return c.getY(X);
	}
}
