class Carte
{
  private Couleur c;
  private Valeur v;

  public Carte(Couleur c, Valeur v){
    this.c = c;
    this.v = v;
  }

  public int getValeur(Couleur c){
    int tab[]={0,0,0,10,2,3,4,11};
    int tabAsset[]={0,0,14,10,20,3,4,11};
    if(this.c==c){
      return tabAsset[this.v.ordinal()];
    }else{
      return tab[this.v.ordinal()];
    }
  }
}
