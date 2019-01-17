import static java.lang.Math.*;
import java.awt.Graphics;
import java.awt.Color;

class Ellipse extends AFormeGeometrique{
  private double petitRayon;
  private double grandRayon;

  public Ellipse(Point p, double pR, double gR, Color cFond, Color cDessin){
    super(p, cFond, cDessin, Alignement.CENTRE);
    this.petitRayon = pR;
    this.grandRayon = gR;
  }

  public Ellipse(Point p, double pR, double gR, Color cFond, Color cDessin, Alignement a){
    super(p, cFond, cDessin, a);
    this.petitRayon = pR;
    this.grandRayon = gR;
  }

  public double getAire(){
    return PI*this.petitRayon*this.grandRayon;
  }

  public double getPerimetre(){
    return PI*sqrt(2*(pow(this.petitRayon,2)+pow(this.grandRayon,2)));
  }
  
  public void dessineToi(Graphics g) {
    Point p = new Point((int) this.pointAncrage.getX(), (int) this.pointAncrage.getY());
    switch (this.alignement) {
    case CENTRE:
      p.setX(p.getX() - (int) this.grandRayon);
      p.setY(p.getY() - (int) this.petitRayon);
      break;
    case COINSUPDROIT:
      p.setX(p.getX() - (int) (2 * this.grandRayon));
      break;
    case COININFGAUCHE:
      p.setY(p.getY() - (int) (2 * this.petitRayon));
      break;
    case COININFDROIT:
      p.setX(p.getX() - (int) (2 * this.grandRayon));
      p.setY(p.getY() - (int) (2 * this.petitRayon));
      break;
    default:
      break;
    }
    g.setColor(this.couleurFond);
    g.fillOval((int) p.getX(), (int) p.getY(), (int)(2*this.petitRayon), (int)(2*this.grandRayon));
    g.setColor(this.couleurDessin);
    g.drawOval((int) p.getX(), (int) p.getY(), (int)(2*this.petitRayon), (int)(2*this.grandRayon));
  }

  public String toString(){
    return "Ellipse : petit rayon =" + this.petitRayon + ", grand rayon=" + this.grandRayon +", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
