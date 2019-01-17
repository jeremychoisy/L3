package fr.unice.l3.formes;

public enum AlignementVertical {
	HAUT, MILIEU, BAS;
	
	int adapt(int y, int hauteur) {
		switch(this) {
		case HAUT: return y;
		case MILIEU: return y - (hauteur>>1);
		case BAS: return y - hauteur;
		}
		return y;
	}
}
