package fr.pierrehb.geometrie;

import java.util.List;

public class HyperEspace {
	
	
	public static HyperPoint getNearestPoint(List<HyperPoint> standartCases, HyperPoint state) {
		HyperPoint nearestPoint = standartCases.get(0);
		float D = 0;
		float litleD = 0;
		float oneDirection = 0;
		
		for(int a = 0; a < standartCases.size(); a++) {
			for(int b = 0; b<standartCases.get(a).hyperPoints.size(); b++) {
				D = 0;
				for(int c = 0; c < standartCases.get(a).hyperCoord.length; c++) {
					oneDirection = (standartCases.get(a).hyperPoints.get(b)[c] - state.hyperCoord[c]);
					D += oneDirection*oneDirection;
				}
				if(a == 0) {
					litleD = D;
					nearestPoint = standartCases.get(a);
				}else if(D < litleD) {
					litleD = D;
					nearestPoint = standartCases.get(a);
				}

			}
		}
		
		return nearestPoint;
		
	}

}
