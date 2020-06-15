package fr.pierrehb.entities.particles;


import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;

public class Tunder extends Particle {
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private int dx;
	private int dy;
	private Human luncher;
	private static final int lifeTime = 30;
	private static final int angles = 5;
	private int[][] tronc = new int[angles][2];
	private int[][][] branche = new int[angles][2][2];
	private float[][] colors = new float[angles][4];
	private static int[] troncSize = {10,10};
	private static int[] brancheSize = {5,5};
	private static final int[] minColor = {200,150,0};
	private static final int[] maxColor = {255,200,50};
	private static final int deadParticles = 50;
	private static final int damageHeal = 20;
	

	public Tunder(int xStart_, int yStart_, int xEnd_, int yEnd_, Human luncher_) {
		super(xStart_,yStart_,lifeTime,Game.getPlayer());
		this.xStart=xStart_;
		this.xEnd=xEnd_;
		this.yEnd=yEnd_;
		this.yStart=yStart_;
		this.luncher=luncher_;
		this.dx = xEnd-xStart;
		this.dy=yEnd-yStart;
		for (int i = 0;i<tronc.length;i++) {
			int[] point = randomPoint(i);
			tronc[i][0] = point[0];
			tronc[i][1] = point[1];
			int[] b1 = randomPoint(point);
			int[] b2 = randomPoint(point);
			branche[i][0][0] = b1[0];
			branche[i][0][1] = b1[1];
			branche[i][1][0] = b2[0];
			branche[i][1][1] = b2[1];
			colors[i][0] = random(200,255)/255f;
			colors[i][1] = random(150,200)/255f;
			colors[i][2] = random(0,50)/255f;
			colors[i][3] = 1.0f;
		}
	}




	@Override
	public void trueUpdate() {
		
	}
	private int[] randomPoint(int d){
		float distance = d/(float)angles;
		int[] root = {(int)(dx*distance), (int)(dy*distance)};
		int px = (int)((Math.random()-0.5)*2*troncSize[0]);
		int py = (int)((Math.random()-0.5)*2*troncSize[1]);
		int[] response = {px+root[0]+xStart,py+root[1]+yStart};
		return response;
	}
	private int[] randomPoint(int[] p){
		int px = (int)((Math.random())*brancheSize[0]);
		int py = (int)((Math.random())*brancheSize[1]);
		int[] response = {px+p[0],py+p[1]};
		return response;
	}
	




	@Override
	public void trueRender() {
		int distance = 2*angles*timeCounter/lifeTime;
		if(distance> angles)distance=angles;
		Renderer.renderLine(xStart,yStart,tronc[0][0],tronc[0][1],colors[angles-1]);
		for (int i = 0; i<distance-1;i++) {
			Renderer.renderLine(tronc[i][0],tronc[i][1],tronc[i+1][0],tronc[i+1][1],colors[i]);
			Renderer.renderLine(tronc[i][0],tronc[i][1],branche[i][0][0],branche[i][0][1],colors[i]);
			Renderer.renderLine(tronc[i][0],tronc[i][1],branche[i][1][0],branche[i][1][1],colors[i]);
		}
		if(distance==angles)Renderer.renderLine(tronc[angles-1][0],tronc[angles-1][1],xEnd,yEnd,colors[angles-1]);
		
	}









	@Override
	protected void mortalEffect() {
		Point p1 = new Point(xEnd-16, yEnd-16);
		Point p2 = new Point(xEnd-16, yEnd+16);
		Point p3 = new Point(xEnd+16, yEnd+16);
		Point p4 = new Point(xEnd+16, yEnd-16);
		Quadrilataire hitBox = new Quadrilataire(p1, p2, p3, p4);

		Game.level.giveDamageSquare(hitBox, luncher, damageHeal+luncher.getDamage());
		
		
		for (int i = 0; i<deadParticles; i++)Game.level.summonParticle(new Smoke((int)xEnd, (int)yEnd, random(40, 100),random(20, 100)/200.0f, luncher, minColor, maxColor, minColor, maxColor));
		
	}

}
