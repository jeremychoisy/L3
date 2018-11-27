import java.awt.Graphics;

abstract class AFormeGeometrique{
  protected Point pointAncrage;

  public AFormeGeometrique(Point p){
    this.pointAncrage = p;
  }

  public Point getPointAncrage(){
    return this.pointAncrage;
  }

  public void setPointAncrage(double x,double y){
    this.pointAncrage.setX(x);
    this.pointAncrage.setY(y);
  }

  public abstract double getAire();
  public abstract double getPerimetre();
  public abstract void dessineToi(Graphics g);
}
