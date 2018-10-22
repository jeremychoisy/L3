class Vecteur{
  private double x,y,z,t;

  public Vecteur(int ... i){
    switch(i.length){
      case 1:
        System.out.println("Erreur : arguments manquants.");
        break;
      case 2:
        this.x = i[0];
        this.y = i[1];
        break;
      case 3:
        this.x = i[0];
        this.y = i[1];
        this.z = i[2];
        break;
      case 4:
        this.x = i[0];
        this.y = i[1];
        this.z = i[2];
        this.t = i[3];
      break;
      default:
        System.out.println("Erreur : trop d'arguments.");
      }
    }

  public Vecteur(int n, int ... i){
      if(i.length>n){
        System.out.println("Erreur : trop d'arguments pour la dimension.");
      }
      else if(i.length>n){
        switch(i.length){
          case 1:
          this.x = i[0];
          this.y = 0;
          this.z = 0;
          this.t = 0;
            break;
          case 2:
          this.x = i[0];
          this.y = i[1];
          this.z = 0;
          this.t = 0;
            break;
          case 3:
          this.x = i[0];
          this.y = i[1];
          this.z = i[2];
          this.t = 0;
            break;
          case 4:
          this.x = i[0];
          this.y = i[1];
          this.z = i[2];
          this.t = i[3];
          break;
          default:
            System.out.println("Erreur : trop d'arguments.");
          }
