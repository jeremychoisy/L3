import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

public class AppletForme extends Applet {
    
    public void paint(Graphics g) {
        Point Pca = new Point(50, 50);
        Carre ca = new Carre(Pca, 50, Color.BLUE, Color.BLACK);
        Carre ca2 = new Carre(Pca, 50, Color.BLUE, Color.BLACK, Alignement.CENTRE);

        Point Pr = new Point(150,500);
        Rectangle r = new Rectangle(Pr, 70, 35, Color.YELLOW, Color.BLACK);
        Rectangle r2 = new Rectangle(Pr, 70, 35, Color.YELLOW, Color.BLACK, Alignement.COININFDROIT);


        Point Pc = new Point(300,100);
        Cercle c = new Cercle(Pc, 40, Color.WHITE, Color.BLACK);
        Cercle c2 = new Cercle(Pc, 40, Color.RED, Color.BLACK, Alignement.COININFDROIT);

        Point Pe = new Point(500,100);
        Ellipse e = new Ellipse(Pe, 80, 50, Color.BLUE, Color.RED);
        Ellipse e2 = new Ellipse(Pe, 80, 50, Color.GREEN, Color.RED, Alignement.COINSUPGAUCHE);

        ca.dessineToi(g);
        ca2.dessineToi(g);   
        c.dessineToi(g);
        c2.dessineToi(g);
        r.dessineToi(g);
        r2.dessineToi(g);
        e.dessineToi(g);
        e2.dessineToi(g);
    }
}