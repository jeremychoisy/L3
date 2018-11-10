import java.util.*;
import java.lang.Math;

class Tortue{
  private int x;
  private int y;
  private double orientation;
  private boolean positionStylo;

  public static double orientationDefault = 0;
  public static boolean positionStyloDefault = true;

  public Tortue(){
    this.x = 0;
    this.y =0;
    this.orientation = Tortue.orientationDefault;
    this.positionStylo = Tortue.positionStyloDefault;
  }

  public void modifierPositionStylo(){
    this.positionStylo = !(this.positionStylo);
  }

  public void gauche(double angle){
    this.orientation += (angle*Math.PI)/180;
  }

  public void droite(double angle){
    this.orientation -=  (angle*Math.PI)/180;
  }

  public void avancer(double d){
    this.x += d*Math.cos(this.orientation);
    this.y += d*Math.sin(this.orientation);
  }

  public void reculer(double d){
    this.x -= d * Math.cos(this.orientation);
    this.y -= d * Math.sin(this.orientation);
  }
  public String getPosition(){
    return "position : (" + this.x + "," + this.y + ")";
  }
}
