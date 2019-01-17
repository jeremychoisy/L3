package fr.unice.l3.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.unice.l3.formes.*;

public class MyFrame {
	public static void main(String[] args) {
	JFrame frame = new JFrame("Formes Géométriques");
	JPanel panel = new JPanel();
	frame.setSize(350,350);
	
	Point p = new Point(100,100);
	Rectangle r = new Rectangle(p,50,50);
	panel.add(r);
	panel.setBackground(Color.GREEN);
	panel.setPreferredSize(new Dimension(300,300));
	
	frame.getContentPane().add(r);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
