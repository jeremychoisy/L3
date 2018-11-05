import java.awt.Graphics;
import java.applet.Applet;

public class Code39Applet extends Applet {
  public void paint(Graphics g) {
    Code39 c = new Code39();
    Symbole s;
    IterateurSymbole i = new IterateurSymbole(c.getCode("JAVA 2012"));
    int x = 5;

    while(!i.estTerminee())
    {
      s = i.suivant();
      s.print(g,x,15);
      x = x + s.getEpaisseur();
    }
  }
}
