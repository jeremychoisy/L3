import java.awt.Graphics;
import java.applet.Applet;

public class Code39Applet extends Applet {
  public void paint(Graphics g) {
    Code39 c = new Code39();
    Symbole s;
<<<<<<< HEAD
    IterateurSymbole i = new IterateurSymbole(c.getCode("JAVA 2012"));
=======
    Code39 c = new Code39();
    String chaine=c.getCode("JAVA 2012");
    IterateurSymbole i = new IterateurSymbole(chaine);
>>>>>>> 8109303852a88f915808436427565aa9b6469fd0
    int x = 5;

    while(!i.estTerminee())
    {
      s = i.suivant();
      s.print(g,x,15);
      x = x + s.getEpaisseur();
    }
  }
}
