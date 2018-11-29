import java.awt.Graphics;
import java.awt.Color;

abstract class AFormeGeometrique{
  protected Point pointAncrage;
  protected Color couleurFond;
  protected Color couleurDessin;
  protected Alignement alignement;

  // CONSTRUCTEUR

  public AFormeGeometrique(Point p, Color couleurFond, Color couleurDessin, Alignement a){
    this.pointAncrage = p;
    this.couleurFond = couleurFond;
    this.couleurDessin = couleurDessin;
    this.alignement = a;
  }

  // GET
  public Alignement getAlignement(){
    return this.alignement;
  }
  public Color getCouleurFond(){
    return this.couleurFond;
  }

  public Color getCouleurDessin(){
    return this.couleurDessin;
  }

  public Point getPointAncrage(){
    return this.pointAncrage;
  }

  // SET

  public void setAlignement(Alignement a){
    this.alignement = a;
  }

  public void setCouleurFond(Color c){
    this.couleurFond = c;
  }

  public void setCouleurDessin(Color c){
    this.couleurDessin= c;
  }

  public void setPointAncrage(double x,double y){
    this.pointAncrage.setX(x);
    this.pointAncrage.setY(y);
  }

  // ABSTRACT

  public abstract double getAire();
  public abstract double getPerimetre();
  public abstract void dessineToi(Graphics g);
}
