import static java.lang.Math.*;
import java.awt.Graphics;

class Cercle extends AFormeGeometrique{
  private double rayon;

  public Cercle(Point p, double r){
    super(p);
    this.rayon = r;
  }

  public double getAire(){
    return PI*pow(this.rayon,2);
  }

  public double getPerimetre(){
    return 2*PI*this.rayon;
  }
  
  public void dessineToi(Graphics g) {
    g.drawOval((int)this.pointAncrage.getX(), (int)this.pointAncrage.getY(), (int)this.rayon, (int)this.rayon);
  }

  public String toString(){
    return "Cercle : rayon =" + this.rayon + ", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
