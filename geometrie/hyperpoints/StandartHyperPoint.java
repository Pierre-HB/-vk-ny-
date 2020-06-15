package fr.pierrehb.geometrie.hyperpoints;

import fr.pierrehb.geometrie.HyperPoint;

public abstract  class StandartHyperPoint extends HyperPoint {

	public StandartHyperPoint(actions Action) {
		super(Action);
	}
	public StandartHyperPoint() {
		super(actions.NONE);
	}
	@Override // C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void ini() {
	}
	protected abstract void secondIni();
}
