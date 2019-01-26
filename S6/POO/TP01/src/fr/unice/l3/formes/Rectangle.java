package fr.unice.l3.formes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends AFormeGeometrique {
	public Rectangle(Point coin, int largeur, int hauteur) {
		super(coin, largeur, hauteur);
		
	}
	
	@Override
	double surface() {
		return getLargeur()*getHauteur();
	}

	@Override
	double perimetre() {
		return 2*(getLargeur() + getHauteur());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point coin = getCoinSuperieurGauche() ;
		g.drawRect(coin.getX(),coin.getY(), getLargeur(), getHauteur());
		}
	
	@Override
	public String toString() {
		return "Rectangle"+super.toString();
	}
}
