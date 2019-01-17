import java.util.Random;
import java.lang.Math;

class GenerateurAleatoire{
  public static double random1(){
    return new Random().nextInt(50+1);
  }

  public static double random1Math(){
    return (int)(Math.random()*(50+1));
  }

  public static double random2(){
    return new Random().nextInt((50+1)-(-20+1))-(20-1);
  }

  public static double random2Math(){
    return (int)((Math.random()*(50+1-(-20+1)))-(20-1));
  }
}
