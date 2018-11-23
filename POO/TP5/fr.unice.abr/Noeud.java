class Noeud{
  private int valeur;
  private Noeud noeudGauche;
  private Noeud noeudDroit;

  public Noeud( int valeur){
    this.valeur = valeur;
  }

  public Noeud(int value, Noeud noeudGauche, Noeud noeudDroit){
    this.valeur = value;
    this.noeudGauche = noeudGauche;
    this.noeudDroit = noeudDroit;
  }

  public int getValeur(){
    return this.valeur;
  }

  public Noeud getNoeudGauche(){
    return this.noeudGauche;
  }

  public Noeud getNoeudDroit(){
    return this.noeudDroit;
  }

  public void setNoeudGauche( Noeud n){
    this.noeudGauche = n;
  }

  public void setNoeudDroit( Noeud n){
    this.noeudDroit = n;
  }

  public String toString(){
    if(this.noeudGauche != null){
      if(this.noeudDroit != null){
        return "Noeud : "  +  this.valeur + " | fils gauche : " + this.noeudGauche.getValeur() + " | fils droit : " + this.noeudDroit.getValeur() + "\n";
      }
      else
      {
        return "Noeud : "  +  this.valeur + " | fils gauche : " + this.noeudGauche.getValeur() + "\n";
      }
    }
    else
    {
      if(this.noeudDroit != null){
        return "Noeud : "  +  this.valeur + " | fils droit : " + this.noeudDroit.getValeur() + "\n";
      }
      else
      {
        return "Noeud : "  +  this.valeur + "\n";
      }
    }
  }
}
