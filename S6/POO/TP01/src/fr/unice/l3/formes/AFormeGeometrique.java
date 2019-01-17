package fr.unice.l3.formes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

abstract class AFormeGeometrique extends JComponent {
	protected Point ancrage;
	protected Color couleurTrait = Color.black;
	protected AlignementHorizontal horizontal = AlignementHorizontal.GAUCHE;
	protected AlignementVertical vertical = AlignementVertical.HAUT;
	protected int largeur, hauteur;
	
	protected AFormeGeometrique(Point ancrage, int largeur, int hauteur) {
		super();
		this.ancrage = new Point(ancrage);
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	abstract double surface();
	abstract double perimetre();
	final int getLargeur() { return this.largeur; }
	final int getHauteur() { return this.hauteur; }
	
	final Point getCoinSuperieurGauche() {
		int x = horizontal.adapt(ancrage.getX(), getLargeur());
		int y = vertical.adapt(ancrage.getY(), getHauteur());
		
		return new Point(x, y);
	}
	final public void setHorizontal(AlignementHorizontal horizontal) {
		this.horizontal = horizontal;
	}
	final public AlignementHorizontal getHorizontal() {
		return horizontal;
	}
	final public void setVertical(AlignementVertical vertical) {
		this.vertical = vertical;
	}
	final public AlignementVertical getVertical() {
		return vertical;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(couleurTrait);
	}
	
	final public Color getCouleurTrait() {
		return couleurTrait;
	}
	final public void setCouleurTrait(Color couleurTrait) {
		this.couleurTrait = couleurTrait;
	}
	
	final protected Point getAncrage() {
		return ancrage;
	}
	
	@Override
	public String toString() {
		return "["+getAncrage()+"; "+getLargeur()+"x"+getHauteur()+"]";
	}
}
