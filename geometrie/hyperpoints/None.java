package fr.pierrehb.geometrie.hyperpoints;


public class None  extends StandartHyperPoint {

	public None() {
		super(actions.NONE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on d�finit le point en fonction de ses coordon� dans les dimension a d�finir ainsi que sont action
	protected void secondIni() {
		
	}

}
