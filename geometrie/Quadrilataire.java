package fr.pierrehb.geometrie;

public class Quadrilataire {
	public Point p1;
	public Point p2;
	public Point p3;
	public Point p4;
	public Segment s1_2;
	public Segment s2_3;
	public Segment s3_4;
	public Segment s4_1;
	public Segment[] list = new Segment[4];
	public Segment[] segmentList = new Segment[4];


	
	public Quadrilataire(Point P1, Point P2, Point P3, Point P4) {
		this.p1 = P1;
		this.p2 = P2;
		this.p3 = P3;
		this.p4 = P4;
		this.s1_2 = new Segment(P1, P2);
		this.s2_3 = new Segment(P2, P3);
		this.s3_4 = new Segment(P3, P4);
		this.s4_1 = new Segment(P4, P1);
		segmentList[0] = s1_2;
		segmentList[1] = s2_3;
		segmentList[2] = s3_4;
		segmentList[3] = s4_1;


	}
	public boolean isON(Point p) {
		int count = 0;
		if(p.x >= s1_2.p1.x && p.x <= s1_2.p2.x){list[count] = s1_2;count++;}
		if(p.x >= s2_3.p1.x && p.x <= s2_3.p2.x){list[count] = s2_3;count++;}
		if(p.x >= s3_4.p1.x && p.x <= s3_4.p2.x){list[count] = s3_4;count++;}
		if(p.x >= s4_1.p1.x && p.x <= s4_1.p2.x){list[count] = s4_1;count++;}
		
		switch (count) {
		case 0:
			return false;
		case 2:
			
			if(list[0].getY(p.x) != -2147483648 && list[1].getY(p.x) != -2147483648) {
			if(list[0].c.getY(p.x)<list[1].c.getY(p.x)) {
				if(list[0].c.getY(p.x)<=p.y && list[1].c.getY(p.x)>=p.y) {
					return true;
				}
				else return false;
			}else {
				if(list[1].c.getY(p.x)<=p.y && list[0].c.getY(p.x)>=p.y) {

					return true;
				}
				else return false;
			}
			}else return false;			
		case 4:
			sortY(p);
			if((list[0].c.getY(p.x)<=p.y && list[1].c.getY(p.x)>=p.y) || (list[2].c.getY(p.x)<=p.y && list[3].c.getY(p.x)>=p.y)) return true;
			else return false;
		default:
			return false;
		}
	}
	public boolean isON(Quadrilataire quadrilataire) {
		for(Segment segment1 : segmentList) {
			for(Segment segment2 : quadrilataire.segmentList) {
				if (isCross(segment1, segment2)) return true;
			}
		}
		
		
		
		
		return false;
	}
	private boolean isCross(Segment segment1, Segment segment2) {
		float[] interval = new float[2];
		if(!segment1.vertical && !segment2.vertical) {
			if(segment1.definitX(segment2.p1.x) && segment1.definitX(segment2.p2.x)) {interval[0] = segment2.p1.x; interval[1] = segment2.p2.x;}
			else if(segment2.definitX(segment1.p1.x) && segment2.definitX(segment1.p2.x)) {interval[0] = segment1.p1.x; interval[1] = segment1.p2.x;}
			else if(segment1.definitX(segment2.p1.x) && segment2.definitX(segment1.p2.x)) {interval[0] = segment2.p1.x; interval[1] = segment1.p2.x;}
			else if(segment2.definitX(segment1.p1.x) && segment1.definitX(segment2.p2.x)) {interval[0] = segment1.p1.x; interval[1] = segment2.p2.x;}
			else return false;
			if((segment1.getY(interval[0]) < segment2.getY(interval[0]) && segment1.getY(interval[1]) > segment2.getY(interval[1]) && segment1.getY(interval[0]) != -2147483648 && segment2.getY(interval[0]) != -2147483648) ||
				(segment1.getY(interval[0]) > segment2.getY(interval[0]) && segment1.getY(interval[1]) < segment2.getY(interval[1]) && segment1.getY(interval[0]) != -2147483648 && segment2.getY(interval[0]) != -2147483648)) {
				return true;
			}
			else return false;
		}
		if(segment1.vertical && segment2.vertical && segment1.p1.x == segment2.p1.x) {
			if((segment1.definitY(segment2.p1.y) || segment1.definitY(segment2.p2.y)) ||
			(segment2.definitY(segment1.p1.y) || segment2.definitY(segment1.p2.y))) {

				return true;
			}
			else return false;
		}
		if(segment1.vertical && !segment2.vertical && segment2.definitX(segment1.p1.x)) {
			if(segment1.definitY(segment2.getY(segment1.p1.x))) {

				return true;
			}
			else return false;
		}
		if(!segment1.vertical && segment2.vertical && segment1.definitX(segment2.p1.x)) {
			if(segment2.definitY(segment1.getY(segment2.p1.x))) {

				return true;
			}
			else return false;
		}
		return false;
	}
	private void sortY(Point p) { // on tri les segemnt du plus bas au plus haut en fonction d'une coordoner sur x
		Segment[] temporaire = list;
		int[] use = {0,0,0};
		
		for(int d = 0;d < temporaire.length; d++) {
			if(list[0].c.getY(p.x) > temporaire[d].c.getY(p.x)) {list[0] = temporaire[d];use[0] = d;}			
		}
		
		if(use[0] == 0) {list[1] = temporaire[1];use[1] = 1;}
		else {list[1] = temporaire[0];use[1] = 0;}
		for(int e = 0; e < temporaire.length; e++) {
			if(list[1].c.getY(p.x) > temporaire[e].c.getY(p.x) && e != use[0]) {list[1] = temporaire[e];use[1] = e;}
		}
		
		if(use[0] != 0 && use[1] != 0) {list[2] = temporaire[0];use[2] = 0;}
		else if(use[0] != 1 && use[1] != 1) {list[2] = temporaire[1];use[2] = 1;}
		else {list[2] = temporaire[2];use[2] = 2;}
		for(int f = 0; f < temporaire.length; f++) {
			if(list[1].c.getY(p.x) > temporaire[f].c.getY(p.x) && f != use[0] && f != use[1]) {list[2] = temporaire[f];use[2] = f;}
		}
		
		if(use[0] != 0 && use[1] != 0 && use[2] != 0) list[2] = temporaire[0];
		else if(use[0] != 1 && use[1] != 1 && use[2] != 1) list[2] = temporaire[1];
		else if(use[0] != 2 && use[1] != 2 && use[2] != 2) list[2] = temporaire[2];
		else list[3] = temporaire[3];

		
	}
	
	
}
