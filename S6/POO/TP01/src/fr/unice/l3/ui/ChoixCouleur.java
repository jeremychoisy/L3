package fr.unice.l3.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class ChoixCouleur implements ActionListener {
	private Editor ed;
	
	public ChoixCouleur(Editor ed) {
		this.ed = ed;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color couleur = JColorChooser.showDialog(ed.getToolBox(), "Choix Couleur", Color.WHITE);
		ed.setCouleurCourante(couleur);
		JButton b = (JButton)e.getSource();
		b.setBackground(couleur);
		if(0.3*couleur.getRed() + 0.59*couleur.getGreen() +0.11*couleur.getBlue() >= 128){
			b.setForeground(Color.BLACK);
		}
		else
		{
			b.setForeground(Color.WHITE);
		}
	}
}

