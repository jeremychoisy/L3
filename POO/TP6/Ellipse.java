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
    g.setColor(this.couleurFond);
    g.fillOval((int) this.pointAncrage.getX(), (int) this.pointAncrage.getY(), (int)(2*this.petitRayon), (int)(2*this.grandRayon));
    g.setColor(this.couleurDessin);
    g.drawOval((int) this.pointAncrage.getX(), (int) this.pointAncrage.getY(), (int)(2*this.petitRayon), (int)(2*this.grandRayon));
  }

  public String toString(){
    return "Ellipse : petit rayon =" + this.petitRayon + ", grand rayon=" + this.grandRayon +", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
