class Vecteur2D{
  private double x;
  private double y;

  public Vecteur2D(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double getX(){
    return this.x;
  }

  public double getY(){
    return this.y;
  }

  public void multiplierK(double k){
    this.x = this.x*k;
    this.y = this.y*k;
  }

  public double multiplierV(Vecteur2D v){
    return ((this.x*v.getX())+(this.y*v.getY()));
  }

  public Vecteur2D transposer(){
    return new Vecteur2D(this.y,this.x);
  }

  public Vecteur2D ajouter(Vecteur2D v){
    return new Vecteur2D(this.x+v.getX(),this.y+v.getY());
  }

  public String toString(){
    return "Vecteur2D x = "+ this.x + " | y = " + this.y;
  }
}
