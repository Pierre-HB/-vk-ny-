package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class Nothing extends StandartHyperPoint {

	public Nothing() {
		super(actions.NONE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on d�finit le point en fonction de ses coordon� dans les dimension a d�finir ainsi que sont action
	protected void secondIni() {
		distanceWithAllie = 0;
		
	}

}