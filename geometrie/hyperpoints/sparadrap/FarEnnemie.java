package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class FarEnnemie extends StandartHyperPoint {

	public FarEnnemie() {
		super(actions.FARENNEMIE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		distanceWithAdverse = 0;
	}

}