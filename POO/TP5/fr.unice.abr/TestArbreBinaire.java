import java.util.*;

class TestArbreBinaire{
  public static void main(String[] args) {
    int r;
    ArbreBinaire a = new ArbreBinaire(50);

    for(int i=0; i<50; i++){
      r = new Random().nextInt(100+1);
      a.ajouterElement(r);
    }

    a.ajouterElement(45);
    a.parcoursInfixe();
    System.out.println(a.toString());
    System.out.println(a.estPresent(45));
  }
}
