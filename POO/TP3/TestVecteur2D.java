class TestVecteur2D{
    public static void main(String[] args) {
      Vecteur2D v1 = new Vecteur2D(3,4);
      Vecteur2D v2 = new Vecteur2D(6,1);
      double d;
      System.out.println(v1);
      v1.multiplierK(3);
      System.out.println(v1);
      d=v1.multiplierV(v2);
      System.out.println(d);
      v2 = v1.transposer();
      System.out.println(v2);
      v2 = v1.ajouter(v2);
      System.out.println(v2);

    }
}
