package fr.pierrehb.geometrie;

public class Courbe_Affine {
	public float a;
	public float b;
	public float c;
	public boolean vertical = false;
	public boolean horizontal = false;
	
	public Courbe_Affine(float A, float B, float C) {
		this.a = A;
		this.b = B;
		this.c = C;
	}
	public Courbe_Affine(Point P1, Point P2) {
			this.a = (P2.y-P1.y);
			this.b = -(P2.x-P1.x);
			this.c = -a*P1.x-b*P1.y;
			if( a == 0) horizontal = true;
			if(b == 0) vertical = true;

	}
	
	public float getX(float y) {
		if( a != 0)return (-b*y-c)/a;
		else return -2147483648;
	}
	public float getY(float x) {
		if( b != 0)return (-a*x-c)/b;
		else return -2147483648;
	}
	
	
}
