import java.awt.Graphics;
import java.applet.Applet;

public class Code39Applet extends Applet {
  public void paint(Graphics g) {
    Symbole s;
    IterateurSymbole i = new IterateurSymbole("010010100");
    int x = 5;

    while(!i.estTerminee())
    {
      s = i.suivant();
      s.print(g,x,15);
      x = x + s.getEpaisseur();
    }
  }
}
