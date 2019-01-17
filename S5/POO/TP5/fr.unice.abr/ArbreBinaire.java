class ArbreBinaire{
  Noeud noeudRacine;
  ArbreBinaire filsGauche;
  ArbreBinaire filsDroit;

  public ArbreBinaire( int valeur){
    this.noeudRacine = new Noeud(valeur);
  }

  public void ajouterElement(int valeur){
    if(valeur < this.noeudRacine.getValeur()){
      if(this.filsGauche == null){
        this.filsGauche = new ArbreBinaire(valeur);
      }
      else
      {
        this.filsGauche.ajouterElement(valeur);
      }
    }
    else if(valeur > this.noeudRacine.getValeur())
    {
      if(this.filsDroit == null){
        this.filsDroit = new ArbreBinaire(valeur);
      }
      else
      {
        this.filsDroit.ajouterElement(valeur);
      }
    }
  }


  public boolean estPresent(int valeur){
    if(this.filsGauche != null){
      if(this.filsDroit != null){
        return (this.noeudRacine.getValeur() == valeur || this.filsGauche.estPresent(valeur) || this.filsDroit.estPresent(valeur));
      }
      else
      {
        return (this.noeudRacine.getValeur() == valeur || this.filsGauche.estPresent(valeur));
      }
    }
    else
    {
      if(this.filsDroit != null){
        return (this.noeudRacine.getValeur() == valeur || this.filsDroit.estPresent(valeur));
      }
      else
      {
        return (this.noeudRacine.getValeur() == valeur);
      }
    }
  }

  public void parcoursInfixe() {
   if (this.filsGauche != null)
     this.filsGauche.parcoursInfixe();
   System.out.println(this.noeudRacine.getValeur());
   if (this.filsDroit != null)
     this.filsDroit.parcoursInfixe();
  }



  public String toString() {
    return toString("\t");
  }

  public String toString(String s) {
    if (this.filsGauche != null) {
      if (this.filsDroit != null){
        return(s+this.noeudRacine.toString()+"\n"+this.filsGauche.toString(s+"\t")+this.filsDroit.toString(s+"\t"));
      }
      else
      {
        return(s+this.noeudRacine.toString()+"\n"+this.filsGauche.toString(s+"\t")+"\n");
      }
    }
    else
    {
      if (this.filsDroit != null){
        return(s+this.noeudRacine.toString()+"\n\n"+this.filsDroit.toString(s+"\t"));
      }
      else
      {
        return(s+this.noeudRacine.toString()+"\n");
      }
    }
  }
}
