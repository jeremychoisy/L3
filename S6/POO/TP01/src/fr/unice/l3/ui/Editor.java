package fr.unice.l3.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


import fr.unice.l3.formes.*;


public class Editor extends JFrame {
	private JPanel drawingArea, toolbox;
	
	public Editor(String title){
		super(title);
		this.setSize(350,350);
		this.setLayout(new BorderLayout());
		
		drawingArea = new JPanel(null);
		toolbox = new JPanel();
		toolbox.setLayout(new BoxLayout(toolbox,BoxLayout.Y_AXIS));
		
		JButton rectangle = new JButton("Rectangle");
		JButton ellipse = new JButton("Ellipse");
		
		rectangle.setMaximumSize(new Dimension(100,20));
		rectangle.setActionCommand("Rectangle");
		ellipse.setMaximumSize(new Dimension(100,20));
		ellipse.setActionCommand("Ellipse");
		
		CreationForme actionListener = new CreationForme(this);
		
		rectangle.addActionListener(actionListener);
		ellipse.addActionListener(actionListener);
		
		toolbox.add(rectangle);
		toolbox.add(ellipse);
		toolbox.add(Box.createVerticalBox());
		toolbox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,Color.BLACK));
		
		Point p = new Point(0,0);
		Rectangle r = new Rectangle(p,50,50);
		r.setBounds(0,0,350,350);
		drawingArea.add(r);

		this.add(toolbox,BorderLayout.WEST);
		this.add(drawingArea,BorderLayout.CENTER);

		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(this.getComponents());
	}
	
	public JPanel getDrawingArea() {
		return this.drawingArea;
	}
	
	public static void main(String[] args) {
		Editor e = new Editor("Graphic Editor");
	}
}
