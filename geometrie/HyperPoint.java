package fr.pierrehb.geometrie;

import java.util.ArrayList;
import java.util.List;

import fr.pierrehb.other.Actions;

public abstract class HyperPoint implements Actions {

	// DIMENSIONS :
	public float percentOfLife = 100;
	public float percentOfLifeAdverse = 100;
	public float percentOfLifeAllie = 100;
	public float distanceWithAdverse = 100;
	public float distanceWithProjectile = 100;
	public float distanceWithAllie = 100;

	
	// AUTRE
	public List<float[]> hyperPoints = new ArrayList<float[]>();
	public float[] hyperCoord = new float[6];
	public actions action = actions.NONE;
	public HyperPoint(actions Action) {
		action = Action;
		ini();
		
	}
	protected abstract void ini();
	public void start() {
		hyperCoord[0] = percentOfLife;
		hyperCoord[1] = percentOfLifeAdverse;
		hyperCoord[2] = percentOfLifeAllie;
		hyperCoord[3] = distanceWithAdverse;
		hyperCoord[4] = distanceWithProjectile;
		hyperCoord[5] = distanceWithAllie;
		float[] HyperCoord = new float[6];
		HyperCoord[0] = percentOfLife;
		HyperCoord[1] = percentOfLifeAdverse;
		HyperCoord[2] = percentOfLifeAllie;
		HyperCoord[3] = distanceWithAdverse;
		HyperCoord[4] = distanceWithProjectile;
		HyperCoord[5] = distanceWithAllie;
		hyperPoints.add(HyperCoord);
				}
	
}
