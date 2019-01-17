import java.util.*;

class Somme{
    public static void main(String[] args) {
      String tab[] = args;
      int somme=0;

      for(int i=0;i<tab.length;i++){
        somme = somme + Integer.parseInt(tab[i]);
      }

      System.out.println("La somme est : " + somme + ".");
    }
  }
