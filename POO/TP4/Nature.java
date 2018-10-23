import java.awt.*;

enum Nature{
  BARRE(Color.BLACK),ESPACE(Color.WHITE);
  private Color c;

  private Nature(Color c){
    this.c = c;
  }

  public void definirCouleur(Graphics e){
    e.setColor(this.c);
  }

}
