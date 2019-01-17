import static java.lang.Math.*;
import java.awt.Graphics;
import java.awt.Color;

class Cercle extends AFormeGeometrique{
  private double rayon;

  public Cercle(Point p, double r, Color cFond, Color cDessin){
    super(p, cFond, cDessin, Alignement.CENTRE);
    this.rayon = r;
  }

  public Cercle(Point p, double r, Color cFond, Color cDessin, Alignement a){
    super(p, cFond, cDessin, a);
    this.rayon = r;
  }

  public double getAire(){
    return PI*pow(this.rayon,2);
  }

  public double getPerimetre(){
    return 2*PI*this.rayon;
  }
  
  public void dessineToi(Graphics g) {
    Point p = new Point((int)this.pointAncrage.getX(),(int)this.pointAncrage.getY());
    switch(this.alignement){
      case CENTRE:
        p.setX(p.getX()-(int)this.rayon);
        p.setY(p.getY()-(int)this.rayon);  
        break;
      case COINSUPDROIT:
        p.setX(p.getX()-(int)(2*this.rayon));
        break;
      case COININFGAUCHE:
        p.setY(p.getY()-(int)(2*this.rayon));
        break;
      case COININFDROIT:
        p.setX(p.getX()-(int)(2*this.rayon));
        p.setY(p.getY()-(int)(2*this.rayon));
        break;
      default:
        break;
    }
    g.setColor(this.couleurFond);
    g.fillOval((int)p.getX(), (int)p.getY(), (int)(2*this.rayon), (int)(2*this.rayon));
    g.setColor(this.couleurDessin);
    g.drawOval((int)p.getX(), (int)p.getY(), (int)(2*this.rayon), (int)(2*this.rayon));

  }

  public String toString(){
    return "Cercle : rayon =" + this.rayon + ", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
