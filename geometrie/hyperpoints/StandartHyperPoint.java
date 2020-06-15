package fr.pierrehb.geometrie.hyperpoints;

import fr.pierrehb.geometrie.HyperPoint;

public abstract  class StandartHyperPoint extends HyperPoint {

	public StandartHyperPoint(actions Action) {
		super(Action);
	}
	public StandartHyperPoint() {
		super(actions.NONE);
	}
	@Override // C'est ici que l'on d�finit le point en fonction de ses coordon� dans les dimension a d�finir ainsi que sont action
	protected void ini() {
	}
	protected abstract void secondIni();
}
