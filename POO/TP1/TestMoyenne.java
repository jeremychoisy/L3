import java.util.*;

public class TestMoyenne {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Moyenne m = new Moyenne();

    int note = 0;
    double coeff = 0;
    String exit = "";

    System.out.println("Calcul de Moyenne :");
    do
    {
      System.out.println("Entrez une note :");
      note = s.nextInt();
      System.out.println("Entrez coefficient :");
      coeff = s.nextDouble();
      m.ajouteNote(note,coeff);
      System.out.println("Voulez-vous entrez une autre note ? o/n");
      exit = s.next();
    }while(exit.charAt(0)=='o');
    System.out.println("Nombre de presences ?");
    m.ajoutePresence(s.nextInt());
    System.out.println("Moyenne : " + m.calculMoyenne());
    System.out.println("Meilleur note : " + m.getMeilleurNote());
  }
}
