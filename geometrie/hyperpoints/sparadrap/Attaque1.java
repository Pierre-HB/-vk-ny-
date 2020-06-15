package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class Attaque1 extends StandartHyperPoint {

	public Attaque1() {
		super(actions.ATTAQUE1);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		distanceWithAdverse = 10;
		
	}

}