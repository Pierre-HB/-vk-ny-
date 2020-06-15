package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class CloseAllie extends StandartHyperPoint {

	public CloseAllie() {
		super(actions.CLOSEALLIE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		distanceWithAllie = 105;
		start();
		distanceWithAllie = 60;
		percentOfLifeAllie = 50;
		start();
		distanceWithAllie = 40;
		start();
		distanceWithAllie = 30;
		percentOfLifeAllie = 40;
		start();
		distanceWithAllie = 20;
		percentOfLifeAllie = 30;
		start();
	}

}