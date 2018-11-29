import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

public class AppletForme extends Applet {
    
    public void paint(Graphics g) {
        Point Pca = new Point(50, 50);
        Carre ca = new Carre(Pca, 50, Color.BLUE, Color.BLACK);

        Point Pr = new Point(150,50);
        Rectangle r = new Rectangle(Pr, 70, 35, Color.YELLOW, Color.BLACK);

        Point Pc = new Point(300,100);
        Cercle c = new Cercle(Pc, 40, Color.RED, Color.BLACK,Alignement.COINSUPGAUCHE);

        Point Pc2 = new Point(300,100);
        Cercle c2 = new Cercle(Pc2, 40, Color.RED, Color.BLACK, Alignement.COININFDROIT);

        Point Pe = new Point(500,100);
        Ellipse e = new Ellipse(Pe, 80, 50, Color.BLUE, Color.RED);

        ca.dessineToi(g);   
        c.dessineToi(g);
        c2.dessineToi(g);
        r.dessineToi(g);
        e.dessineToi(g);
    }
}