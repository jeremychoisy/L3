import java.awt.Graphics;
import java.awt.Color;

class Rectangle extends AFormeGeometrique{
  double largeur, hauteur;

  public Rectangle(Point p, double l, double h, Color cFond, Color cDessin){
    super(p, cFond, cDessin, Alignement.COINSUPGAUCHE);
    this.largeur = l;
    this.hauteur =h;
  }

  public Rectangle(Point p, double l, double h, Color cFond, Color cDessin, Alignement a){
    super(p, cFond, cDessin, a);
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
    Point p = new Point((int)this.pointAncrage.getX(),(int)this.pointAncrage.getY());
    switch(this.alignement){
      case COINSUPDROIT:
        p.setX(p.getX()-(int)this.largeur);
        break;
      case COININFGAUCHE:
        p.setY(p.getY()-(int)this.hauteur);
        break;
      case COININFDROIT:
        p.setX(p.getX()-(int)this.largeur);
        p.setY(p.getY()-(int)this.hauteur);
        break;
      case CENTRE:
        p.setX(p.getX()-(int)(this.largeur/2));
        p.setY(p.getY()-(int)(this.hauteur/2));
        break;
      default:
        break;
    }
    g.setColor(this.couleurFond);
    g.fillRect((int)p.getX(), (int)p.getY(), (int)this.largeur, (int)this.hauteur);
    g.setColor(this.couleurDessin);
    g.drawRect((int)p.getX(), (int)p.getY(), (int)this.largeur, (int) this.hauteur);
  }

  public String toString(){
    return "Rectangle : l =" + this.largeur + ", h=" + this.hauteur +", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
