class TestCarte{

  public static void main(String[] args) {
    Couleur c = Couleur.COEUR;
    Valeur v = Valeur.VALET;
    Carte ca = new Carte(c,v);
    System.out.println(ca.getValeur(Couleur.COEUR));
  }
}
