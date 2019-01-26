package fr.unice.l3.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

abstract class AFormeGeometrique extends JComponent {
	protected Point ancrage;
	protected Color couleurTrait = Color.black;
	protected EpaisseurTrait epaisseur = EpaisseurTrait.FIN;
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
	final public int getLargeur() { return this.largeur; }
	final public int getHauteur() { return this.hauteur; }
	
	final public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	final public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	final Point getCoinSuperieurGauche() {
		int x = horizontal.adapt(ancrage.getX(), getLargeur());
		int y = vertical.adapt(ancrage.getY(), getHauteur());
		
		return new Point(x, y);
	}
	final public void setEpaisseurTrait(EpaisseurTrait epaisseur) {
		this.epaisseur = epaisseur;
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
		Point coin = getCoinSuperieurGauche() ;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(this.epaisseur.getValue()));
		g.setColor(couleurTrait);

		int translateX = coin.x - getAncrage().x;
		int translateY = coin.y - getAncrage().y;
		g2.translate(translateX, translateY);
		getAncrage().paintComponent(g);
		g2.translate(-translateX, -translateY);

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
