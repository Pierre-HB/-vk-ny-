package fr.pierrehb.geometrie.hyperpoints;

import fr.pierrehb.entities.joueurs.Joueur;

public class State  extends StandartHyperPoint {

	private Joueur joueur;
	
	public State(Joueur JOUEUR) {
		super(actions.STATE);
		this.joueur = JOUEUR;
		secondIni();
		start();
	}

	@Override// C'est ici que l'on définit le point en fonction de ses coordoné dans les dimension a définir ainsi que sont action
	protected void secondIni() {
		percentOfLife = joueur.HP*100/joueur.getMaxHP();
		if(joueur.haveAdverse)percentOfLifeAdverse = 100*joueur.targetAdverse.HP/joueur.targetAdverse.getMaxHP();
		if(joueur.haveAllie)percentOfLifeAllie = 100*joueur.targetAllie.HP/joueur.targetAllie.getMaxHP();
		if(joueur.haveAdverse) distanceWithAdverse = (float)Math.sqrt((joueur.getCoord()[0]-joueur.targetAdverse.getCoord()[0])*(joueur.getCoord()[0]-joueur.targetAdverse.getCoord()[0])+(joueur.getCoord()[1]-joueur.targetAdverse.getCoord()[1])*(joueur.getCoord()[1]-joueur.targetAdverse.getCoord()[1]))/1;
		if(joueur.haveParticle)distanceWithProjectile  = (float)Math.sqrt((joueur.getCoord()[0]-joueur.targetParticle.x)*(joueur.getCoord()[0]-joueur.targetParticle.x)+(joueur.getCoord()[1]-joueur.targetParticle.y)*(joueur.getCoord()[1]-joueur.targetParticle.y))/1;
		if(joueur.haveAllie)distanceWithAllie  = (float)Math.sqrt((joueur.getCoord()[0]-joueur.targetAllie.getCoord()[0])*(joueur.getCoord()[0]-joueur.targetAllie.getCoord()[0])+(joueur.getCoord()[1]-joueur.targetAllie.getCoord()[1])*(joueur.getCoord()[1]-joueur.targetAllie.getCoord()[1]))/1;
		
	}

}
