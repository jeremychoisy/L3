import java.awt.Graphics;
import java.awt.Color;

class Carre extends AFormeGeometrique{
  private double longueurCote;

  public Carre(Point p, double l, Color cFond, Color cDessin){
    super(p, cFond, cDessin,Alignement.COINSUPGAUCHE);
    this.longueurCote = l;
  }

  public Carre(Point p, double l, Color cFond, Color cDessin, Alignement a){
    super(p, cFond, cDessin, a);
    this.longueurCote = l;
  }
  
  
  public double getAire(){
    return this.longueurCote*this.longueurCote;
  }

  public double getPerimetre(){
    return this.longueurCote*2;
  }

  public void dessineToi(Graphics g){
    Point p = new Point((int)this.pointAncrage.getX(),(int)this.pointAncrage.getY());
    switch(this.alignement){
      case COINSUPDROIT:
        p.setX(p.getX()-(int)this.longueurCote);
        break;
      case COININFGAUCHE:
        p.setY(p.getY()-(int)this.longueurCote);
        break;
      case COININFDROIT:
        p.setX(p.getX()-(int)this.longueurCote);
        p.setY(p.getY()-(int)this.longueurCote);
        break;
      case CENTRE:
        p.setX(p.getX()-(int)(this.longueurCote/2));
        p.setY(p.getY()-(int)(this.longueurCote/2));
        break;
      default:
        break;
    }
    g.setColor(this.couleurFond);
    g.fillRect((int)p.getX(), (int)p.getY(), (int)this.longueurCote, (int)this.longueurCote);
    g.setColor(this.couleurDessin);
    g.drawRect((int)p.getX(), (int)p.getY(), (int)this.longueurCote, (int)this.longueurCote);
    

  }

  public String toString(){
    return "Carre : l =" + this.longueurCote + ", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
