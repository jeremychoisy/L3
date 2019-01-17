import java.util.*;

class TestVecteur{
    public static void main(String[] args) {
      Vecteur v1 = new Vecteur(12,45,98);
      Vecteur v2 = new Vecteur(3,new int[]{2,3});
      Vecteur v3 = new Vecteur(3,new int[]{3,4,8});
      Vecteur v4 = v3.transposer();
      Vecteur v5 = v3.ajouter(v2);
      double produitSca = v3.getProduitSca(v2);

      System.out.println(v3);
      v3.multiplierK(3);
      System.out.println("x3 : " + v3);
      System.out.println("transpose : " + v4);
      System.out.println("v2 + v3 : " + v5);
      System.out.println("Produit scalaire de v2 et v3 : " + produitSca);


  }
}
