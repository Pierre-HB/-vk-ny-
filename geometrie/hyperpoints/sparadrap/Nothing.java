package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class Nothing extends StandartHyperPoint {

	public Nothing() {
		super(actions.NONE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		distanceWithAllie = 0;
		
	}

}