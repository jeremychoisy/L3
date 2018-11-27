import java.awt.Graphics;

class Rectangle extends AFormeGeometrique{
  double largeur, hauteur;

  public Rectangle(Point p, double l, double h){
    super(p);
    this.largeur = l;
    this.hauteur =h;
  }

  public double getAire(){
    return this.largeur*this.hauteur;
  }

  public double getPerimetre(){
    return this.largeur+this.hauteur;
  }

  public void dessineToi(Graphics g) {
    g.drawRect((int) this.pointAncrage.getX(), (int) this.pointAncrage.getY(), (int) this.largeur,
        (int) this.hauteur);
  }

  public String toString(){
    return "Rectangle : l =" + this.largeur + ", h=" + this.hauteur +", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
