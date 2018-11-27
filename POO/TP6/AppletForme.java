import java.awt.Graphics;
import java.applet.Applet;

public class AppletForme extends Applet {
    
    public void paint(Graphics g) {
        Point p = new Point(50, 50);
        Carre ca = new Carre(p, 50);
        Rectangle r = new Rectangle(p, 70, 35);
        Cercle c = new Cercle(p, 40);
        Ellipse e = new Ellipse(p, 80, 50);

        ca.dessineToi(g);   
        c.dessineToi(g);
        r.dessineToi(g);
        e.dessineToi(g);
    }
}