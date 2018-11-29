class ComplexeUtil{

    public ComplexeCartesien creationComplexeCartesien(double reelle, double imaginaire){
        return new ComplexeCartesien(reelle,imaginaire);
    }

    public ComplexePolaire creationComplexePolaire(double module, double argument){
        return new ComplexePolaire(module,argument);
    }

    public void conversionPolaire(IComplexe c){
        
    }
}