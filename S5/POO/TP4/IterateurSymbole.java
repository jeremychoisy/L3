import java.util.*;

class IterateurSymbole{
  private String code;
  private int indice = 0;
  private int nature = 1;

  public IterateurSymbole(String code){
    this.code = code;
  }

  public boolean estTerminee(){
    if(this.indice==this.code.length())
      return true;
    return false;
  }

  public Symbole suivant(){
    Epaisseur e;
    Nature n;
    if ((this.code.charAt(indice)) == '1')
      e = Epaisseur.LARGE;
    else
      e = Epaisseur.ETROIT;
    if (nature==1)
    {
      n = Nature.BARRE;
      nature = 0;
    }
    else
    {
      n = Nature.ESPACE;
      nature = 1;
    }
    indice++;
    return new Symbole(e,n);
  }
}
