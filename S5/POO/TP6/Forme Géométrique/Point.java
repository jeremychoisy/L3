class Point {
  private double x, y;
  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
// accesseurs et modificateurs de x et y
  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }

  public void setX(double x){
    this.x = x;
  }

  public void setY(double y){
    this.y =y;
  }

  public String toString(){
    return "(" + this.x + "," + this.y + ")";
  }
}
