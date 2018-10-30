import java.awt.Graphics;
import java.applet.Applet;

public class Code39Applet extends Applet {
  public void paint(Graphics g) {
    Symbole s;
    Code39 c = new Code39();
    String chaine=c.getCode("JAVA 2012");
    IterateurSymbole i = new IterateurSymbole(chaine);
    int x = 5;

    while(!i.estTerminee())
    {
      s = i.suivant();
      s.print(g,x,15);
      x = x + s.getEpaisseur();
    }
  }
}
