package fr.unice.l3.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.unice.l3.formes.*;

public class CreationForme implements ActionListener {
	private Editor ed;
	
	public CreationForme(Editor ed) {
		this.ed = ed;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int x,y,width,height;
		JPanel drawingArea = this.ed.getDrawingArea();
		switch(e.getActionCommand()) {
		case "Rectangle":
			x = (int) (Math.random()*drawingArea.getWidth()*0.8);
			y = (int) (Math.random()*drawingArea.getHeight()*0.8);
			width = (int) (Math.random()*drawingArea.getWidth()*0.2); 
			height = (int) (Math.random()*drawingArea.getHeight()*0.2);
			Rectangle r = new Rectangle(new Point(x,y),width,height);
			r.setBounds(0,0,350,350);
			drawingArea.add(r);
			break;
		case "Ellipse":
			x = (int) (Math.random()*drawingArea.getWidth()*0.8);
			y = (int) (Math.random()*drawingArea.getHeight()*0.8);
			width = (int) (Math.random()*drawingArea.getWidth()*0.2); 
			height = (int) (Math.random()*drawingArea.getHeight()*0.2); 
			drawingArea.getGraphics().drawOval(x, y, width, height);
			break;
		default:
			break; 
		}
	}
}
