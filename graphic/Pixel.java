package fr.pierrehb.graphic;

import fr.pierrehb.geometrie.Point;

public class Pixel {
	public float x;
	public float y;
	public Point[] points = new Point[4];
	public float[] color;
	private boolean standart;
	
	public Pixel(float x, float y, float[] color) {
		this.x = x;
		this.y = y;
		this.color = color;
		standart = true;
	}
	public Pixel(Point P1, Point P2, Point P3, Point P4, float[] color) {
		this.points[0] = P1;
		this.points[1] = P2;
		this.points[2] = P3;
		this.points[3] = P4;

		this.color = color;
		standart = false;
	}
	public void increaseX(float X) {		
		if(standart) x+=X;	
		else for(Point point : points) point.x+=X;
	}
	public void increaseY(float Y) {
		if(standart) y+=Y;
		else for(Point point : points) point.y+=Y;

	}
	public void setX(float X) {
		if(standart) x = X;
	}
	public void setY(float Y) {
		if(standart) y = Y;
	}
	public void render() {
		if(standart) Renderer.renderPixel(x, y, color);
		else Renderer.renderPixel(points[0], points[1], points[2], points[3], color);
	}
}
