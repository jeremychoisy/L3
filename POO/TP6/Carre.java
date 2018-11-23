class Carre extends AFormeGeometrique{
  private double longueurCote;

  public Carre(Point p, double l){
    super(p);
    this.longueurCote = l;
  }
  public double getAire(){
    return this.longueurCote*this.longueurCote;
  }

  public double getPerimetre(){
    return this.longueurCote*2;
  }

  public String toString(){
    return "Carre : l =" + this.longueurCote + ", point d'ancrage ="+this.pointAncrage.toString() + "\n" +
    "Aire : " + this.getAire() + " / Perimetre : " + this.getPerimetre();
  }
}
