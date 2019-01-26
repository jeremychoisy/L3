package fr.unice.l3.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


import fr.unice.l3.formes.*;


public class Editor extends JFrame {
	private JPanel drawingArea, toolbox;
	private Color couleurCourante;
	
	public Editor(String title){
		super(title);
		this.setSize(350,350);
		this.setLayout(new BorderLayout());
		drawingArea = new JPanel(null);
		toolbox = new JPanel();
		couleurCourante = Color.BLACK;
		toolbox.setLayout(new BoxLayout(toolbox,BoxLayout.Y_AXIS));
		
		JButton rectangle = new JButton("Rectangle");
		JButton ellipse = new JButton("Ellipse");
		JButton couleur = new JButton("Couleur");
		
		rectangle.setMaximumSize(new Dimension(100,20));
		rectangle.setActionCommand("Rectangle");
		ellipse.setMaximumSize(new Dimension(100,20));
		ellipse.setActionCommand("Ellipse");
		couleur.setMaximumSize(new Dimension(100,20));
		
		CreationForme actionListener = new CreationForme(this);
		ChoixCouleur actionListenerColor = new ChoixCouleur(this);
		
		rectangle.addActionListener(actionListener);
		ellipse.addActionListener(actionListener);
		couleur.addActionListener(actionListenerColor);
		
		toolbox.add(rectangle);
		toolbox.add(ellipse);
		toolbox.add(couleur);
		toolbox.add(Box.createVerticalBox());
		toolbox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,Color.BLACK));
		

		this.add(toolbox,BorderLayout.WEST);
		this.add(drawingArea,BorderLayout.CENTER);

		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel getDrawingArea() {
		return this.drawingArea;
	}
	
	public JPanel getToolBox() {
		return this.toolbox;
	}
	
	public Color getCouleurCourante() {
		return this.couleurCourante;
	}
	
	public void setCouleurCourante(Color c) {
		this.couleurCourante = c;
	}
	
	public static void main(String[] args) {
		Editor e = new Editor("Graphic Editor");
	}
}
