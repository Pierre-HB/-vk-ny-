package fr.pierrehb.geometrie.hyperpoints;


public class None  extends StandartHyperPoint {

	public None() {
		super(actions.NONE);
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		
	}

}
