class Vecteur{
  private int[] coordo;

  public Vecteur(int ... i){
    if(i.length==1)
    {
      System.out.println("Erreur : trop peu d'arguments.");
    }    else
    {
      coordo = new int[i.length];
      for(int j=0;j<i.length;j++)
      {
        coordo[j]=i[j];
      }
    }
  }

  public Vecteur(int n, int[] i){
    if(n==1)
    {
      System.out.println("Erreur : On ne peut pas créer un vecteur avec un seul argument.");
    }
    else if(i.length>n)
    {
      System.out.println("Erreur : le nombre d'arguments annoncé est incorrect.");
    }
    else
    {
      coordo = new int[n];
      for(int j=0;j<n;j++)
      {
        if(j>=i.length)
        {
          coordo[j]=0;
        }
        else
        {
        coordo[j]=i[j];
        }
      }
    }
  }

  public int[] getCord(){
    return this.coordo;
  }


  public void multiplierK(int k){
    for(int i=0;i<this.coordo.length;i++)
    {
      this.coordo[i]=this.coordo[i]*k;
    }
  }

  public double getProduitSca(Vecteur v){
    int somme = 0;
    if(this.coordo.length!=v.getCord().length)
    {
      System.out.println("Erreur getProduitSca : les deux vecteurs sont de dimensions differentes.");
    }
    else
    {
    for(int i=0;i<this.coordo.length;i++)
    {
      somme += this.coordo[i]*v.getCord()[i];
    }
    return somme;
    }
    return -1;
  }

  public Vecteur transposer(){
    int tab[]=new int[this.coordo.length];
    int j=0;
    for(int i=this.coordo.length-1;i>=0;i--)
    {
      tab[j]=this.coordo[i];
      j++;
    }
    return new Vecteur(tab.length,tab);
  }

  public Vecteur ajouter(Vecteur v){
    int tab[]=new int[this.coordo.length];
    if(this.coordo.length!=v.getCord().length)
    {
      System.out.println("Erreur ajouter : les deux vecteurs sont de dimensions differentes.");
      return null;
    }
    else
    {
    for(int i=0;i<this.coordo.length;i++)
    {
      tab[i]= this.coordo[i]+v.getCord()[i];
    }
    return new Vecteur(tab.length,tab);
    }
  }

  public String toString(){
    String chaine = "Vecteur : ";
    for(int i=0;i<this.coordo.length;i++)
    {
      chaine += "" + this.coordo[i] + " | ";
    }
    return chaine;
  }
}
