import java.util.*;

class TestNoeud{
  public static void main(String[] args) {
    Noeud n = new Noeud(10);
    Noeud n1 = new Noeud(5);

    n.setNoeudGauche(n1);

    System.out.println(n.toString());
  }
}
