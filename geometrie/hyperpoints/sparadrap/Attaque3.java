package fr.pierrehb.geometrie.hyperpoints.sparadrap;

import fr.pierrehb.geometrie.hyperpoints.StandartHyperPoint;

public class Attaque3 extends StandartHyperPoint {

	public Attaque3() {
		super(actions.ATTAQUE3);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on d�finit le point en fonction de ses coordon� dans les dimension a d�finir ainsi que sont action
	protected void secondIni() {
		
	}

}