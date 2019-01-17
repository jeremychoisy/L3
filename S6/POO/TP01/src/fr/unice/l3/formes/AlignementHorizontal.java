package fr.unice.l3.formes;

public enum AlignementHorizontal {
	GAUCHE, MILIEU, DROITE;
	
	int adapt(int x, int largeur) {
		switch(this) {
		case GAUCHE: return x;
		case MILIEU: return x - (largeur>>1);
		case DROITE: return x - largeur;
		}
		return x; // should not happen;
	}
}
