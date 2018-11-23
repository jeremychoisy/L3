import static java.lang.Math.*;

class Ellipse extends AFormeGeometrique{
  private double petitRayon;
  private double grandRayon;

  public Ellipse(Point p, double pR, double gR){
    super(p);
    this.petitRayon = pR;
    this.grandRayon = gR;
  }

  public double getAire(){
    return PI*this.petitRayon*this.grandRayon;
  }

  public double getPerimetre(){
    return PI*sqrt(2*(pow(this.petitRayon,2)+pow(this.grandRayon,2)));
  }

  public String toString(){
    return "Ellipse : petit rayon =" + this.petitRayon + ", grand rayon=" + this.grandRayon +", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
