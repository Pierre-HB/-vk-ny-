package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class Attaque2 extends StandartHyperPoint {

	public Attaque2() {
		super(actions.ATTAQUE2);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		percentOfLifeAllie = 60;
		distanceWithAllie = 10;
		start();
		percentOfLifeAllie = 40;
		start();
		percentOfLifeAllie = 20;
		start();
		percentOfLifeAllie = 0;
		start();
		distanceWithAllie = 0;
		start();
		percentOfLifeAllie = 100;
		distanceWithAllie = 100;
		percentOfLife = 60;
		start();
		percentOfLife = 40;
		start();
		percentOfLife = 20;
		start();
		percentOfLife = 0;
		start();
	}

}
