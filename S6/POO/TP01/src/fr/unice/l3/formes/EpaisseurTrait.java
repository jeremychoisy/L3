package fr.unice.l3.formes;

public enum EpaisseurTrait {
	FIN(1f), MOYEN(2.0f), EPAIS(4.0f);
	private float epaisseur;
	
	private EpaisseurTrait(float epaisseur) {
		this.epaisseur = epaisseur;
	}
	
	public float getValue() {
		return epaisseur;
	}
}