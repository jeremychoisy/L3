package fr.unice.l3.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.unice.l3.formes.*;

public class CreationForme implements ActionListener, MouseInputListener {
	private Editor ed;
	private Rectangle r;
	private Point pSource;
	private String type;
	private Boolean actionStatus;
	
	public CreationForme(Editor ed) {
		this.ed = ed;
		this.type = "Rectangle";
		this.actionStatus = false;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		/*int x,y,width,height;
		JPanel drawingArea = this.ed.getDrawingArea();
		switch(ae.getActionCommand()) {
		case "Rectangle":
			x = (int) (Math.random()*drawingArea.getWidth()*0.8);
			y = (int) (Math.random()*drawingArea.getHeight()*0.8);
			width = (int) (Math.random()*drawingArea.getWidth()*0.2); 
			height = (int) (Math.random()*drawingArea.getHeight()*0.2);
			Rectangle r = new Rectangle(new Point(x,y),width,height);
			r.setBounds(0,0,350,350);
			r.setVertical(AlignementVertical.MILIEU);
			r.setCouleurTrait(ed.getCouleurCourante());
			drawingArea.add(r);
			drawingArea.revalidate();
			drawingArea.repaint();
			break;
		case "Ellipse":
			x = (int) (Math.random()*drawingArea.getWidth()*0.8);
			y = (int) (Math.random()*drawingArea.getHeight()*0.8);
			width = (int) (Math.random()*drawingArea.getWidth()*0.2); 
			height = (int) (Math.random()*drawingArea.getHeight()*0.2); 
			Ellipse el = new Ellipse(new Point(x,y),width,height);
			el.setBounds(0,0,350,350);
			el.setCouleurTrait(ed.getCouleurCourante());
			drawingArea.add(el);
			drawingArea.revalidate();
			drawingArea.repaint();
			break;
		default:
			break; 
		}*/
		type = ae.getActionCommand();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		actionStatus = true;
		pSource = new Point(e.getX(),e.getY());
		r = new Rectangle(pSource, 0,0);
		r.setBounds(0,0,350,350);
		r.setCouleurTrait(ed.getCouleurCourante());
		ed.getDrawingArea().add(r);
		ed.getDrawingArea().revalidate();
		ed.getDrawingArea().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(actionStatus) {
			if(type == "Ellipse") {
				
				Ellipse el = new Ellipse(pSource,r.getLargeur(),r.getHauteur());
				el.setCouleurTrait(ed.getCouleurCourante());
				el.setBounds(0,0,ed.getWidth(),ed.getHeight());
				el.setHorizontal(r.getHorizontal());
				el.setVertical(r.getVertical());
				
				ed.getDrawingArea().remove(r);
				ed.getDrawingArea().add(el);
				ed.getDrawingArea().revalidate();
				ed.getDrawingArea().repaint();		
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {
		if(actionStatus) {
			actionStatus = false;
			ed.getDrawingArea().remove(r);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(e.getX()<pSource.getX()){
			r.setHorizontal(AlignementHorizontal.DROITE);
		}
		else
		{
			r.setHorizontal(AlignementHorizontal.GAUCHE);
		}
		if(e.getY()<pSource.getY()){
			r.setVertical(AlignementVertical.BAS);
		}
		else
		{
			r.setVertical(AlignementVertical.HAUT);
		}
		
		r.setLargeur(Math.abs(e.getX()-pSource.getX()));
		r.setHauteur(Math.abs(e.getY() - pSource.getY()));
		ed.getDrawingArea().revalidate();
		ed.getDrawingArea().repaint();
	}
}
