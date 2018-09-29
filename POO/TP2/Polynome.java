import java.lang.Math;

class Polynome{
    int a,b,c;

    public Polynome(int a, int b, int c){
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public double evalPoly(double x){
      return (a*Math.pow(x,2))+(b*x)+c;
    }

    public int getDet(){
      return (b*b)-(4*a*c);
    }

    public double racineReelle1(){
      return (-b-Math.sqrt(this.getDet()))/(2*a);
    }

    public double racineReelle2(){
      return (-b+Math.sqrt(this.getDet()))/(2*a);
    }

    public Complexe racine1(){
      return new Complexe(-b/(2*a),-Math.sqrt(Math.abs(getDet()))/(2*a));
    }

    public Complexe racine2(){
      return new Complexe(-b/(2*a), Math.sqrt(Math.abs(getDet()))/(2*a));
    }
}
