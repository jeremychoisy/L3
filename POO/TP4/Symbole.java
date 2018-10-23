import java.util.*;
import java.awt.*;

class Symbole{
  private Epaisseur e;
  private Nature n;

  public Symbole(Epaisseur e,Nature n){
    this.e = e;
    this.n = n;
  }

  public int getEpaisseur(){
    return e.getValue();
  }

  public void print(Graphics g,int x,int y){
    n.definirCouleur(g);
    e.print(g,x,y);
  }
}
