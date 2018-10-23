import java.awt.Graphics;

public enum Epaisseur{
  LARGE(3), ETROIT(1);
  private int epaisseur;
  private final int hauteur = 50;


  private Epaisseur(int epaisseur){
    this.epaisseur = epaisseur;
  }

  public int getValue(){
    return this.epaisseur;
  }

  public void print(Graphics e, int x, int y) {
        e.fillRect(x, y, this.epaisseur, this.hauteur);
  }
}
