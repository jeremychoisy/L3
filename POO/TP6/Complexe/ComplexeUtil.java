import java.lang.Math.*;

class ComplexeUtil{

    static public ComplexeCartesien creationComplexeCartesien(double reelle, double imaginaire){
        return new ComplexeCartesien(reelle,imaginaire);
    }

    static public ComplexePolaire creationComplexePolaire(double module, double argument){
        return new ComplexePolaire(module,argument);
    }

    static public ComplexePolaire conversionPolaire(IComplexe c){
        double module = Math.sqrt((c.reelle()*c.reelle())+(c.imaginaire()*c.imaginaire()));
        double argument = Math.atan(c.imaginaire()/c.reelle());
        return new ComplexePolaire(module,argument);
    }

    static public ComplexeCartesien conversionCartesienne(IComplexe c){
        return new ComplexeCartesien(c.reelle(),c.imaginaire());
    }

    static public boolean sontEgaux(IComplexe c1, IComplexe c2){
        return ((c1.reelle() == c2.reelle()) && (c1.imaginaire() == c2.imaginaire()));
    }

    static public IComplexe addition(IComplexe c1, IComplexe c2){
        ComplexeCartesien c = new ComplexeCartesien(c1.reelle()+c2.reelle(),c1.imaginaire()+c2.imaginaire());
        if(c1 instanceof ComplexePolaire && c2 instanceof ComplexePolaire){
            return conversionPolaire(c);
        }
        else{
            return c;
        }
    }

    static public IComplexe soustraction(IComplexe c1, IComplexe c2) {
        ComplexeCartesien c = new ComplexeCartesien(c1.reelle() - c2.reelle(), c1.imaginaire() - c2.imaginaire());
        if (c1 instanceof ComplexePolaire && c2 instanceof ComplexePolaire) {
            return conversionPolaire(c);
        } else {
            return c;
        }

    }

    static public IComplexe multiplication(IComplexe c1, IComplexe c2) {
        double reelle = (c1.reelle()*c2.reelle()) - (c1.imaginaire()*c2.imaginaire());
        double imaginaire = (c1.reelle() * c2.imaginaire()) + (c2.reelle() * c1.imaginaire());
        ComplexeCartesien c = new ComplexeCartesien(reelle, imaginaire);
        if (c1 instanceof ComplexePolaire && c2 instanceof ComplexePolaire) {
            return conversionPolaire(c);
        } else {
            return c;
        }
    }

    static public IComplexe division(IComplexe c1, IComplexe c2) {
        double reelle = ((c1.reelle()*c2.reelle()) + (c1.imaginaire()*c2.imaginaire()))/((c2.reelle()*c2.reelle())*(c2.imaginaire()*c2.imaginaire()));
        double imaginaire = ((c2.reelle() * c1.imaginaire())-(c1.reelle() * c2.imaginaire()))/((c2.reelle()*c2.reelle())*(c2.imaginaire()*c2.imaginaire()));
        ComplexeCartesien c = new ComplexeCartesien(reelle, imaginaire);
        if (c1 instanceof ComplexePolaire && c2 instanceof ComplexePolaire) {
            return conversionPolaire(c);
        } else {
            return c;
        }
    }
}