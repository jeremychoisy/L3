import java.util.*;

class TestRecherche{
    public static void main(String[] args) {
      int tab[] ={1,18,24,98,65,32,45,88};

      System.out.println(Recherche.recherche(tab,98));
      Arrays.sort(tab);
      System.out.println(Recherche.rechercheDico(tab,18,0,tab.length-1));


  }
}
