package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class CloseEnnemie extends StandartHyperPoint {

	public CloseEnnemie() {
		super(actions.CLOSEENNEMIE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on d�finit le point en fonction de ses coordon� dans les dimension a d�finir ainsi que sont action
	protected void secondIni() {
		distanceWithAdverse = 96;
	}

}