class Complexe{
  double r,im;

  public Complexe(double reel, double imag){
    this.r = reel;
    this.im = imag;
  }

  public String toString(){
    return "" + r + " + (" + im + ")i";
  }
}
