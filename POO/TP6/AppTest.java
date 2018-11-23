import java.util.*;
class AppTest{
    public static void main(String[] args) {
      Point p = new Point(4,6);

      Carre ca = new Carre(p,5);
      Rectangle r = new Rectangle(p,10,4);
      Cercle c = new Cercle(p,6);
      Ellipse e = new Ellipse(p,4,8);

      System.out.println(ca);
      System.out.println(r);
      System.out.println(c);
      System.out.println(e);
    }

}
