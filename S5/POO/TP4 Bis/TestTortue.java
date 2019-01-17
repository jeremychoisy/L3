import java.util.*;

class TestTortue{
  public static void main(String[] args) {
    Tortue t = new Tortue();
    t.avancer(50);
    System.out.println("+50 : " +t.getPosition());
    t.gauche(90);
    t.avancer(50);
    System.out.println(t.getPosition());
    t.gauche(90);
    t.avancer(50);
    System.out.println(t.getPosition());
    t.gauche(90);
    t.avancer(50);
    System.out.println(t.getPosition());
  }
}
