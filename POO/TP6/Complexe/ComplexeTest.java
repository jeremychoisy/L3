class ComplexeTest{
    public static void main(String[] args) {
        IComplexe c = ComplexeUtil.creationComplexeCartesien(4,6);
        IComplexe c1 = ComplexeUtil.creationComplexeCartesien(2,0);
        IComplexe c3 = ComplexeUtil.conversionPolaire(c1);
        System.out.println(c3.reelle() +" "+ c3.imaginaire());
        IComplexe c2 = ComplexeUtil.addition(c,c1);
        System.out.println("Addition entre c1 et c2 = " + c2 );

        c2 = ComplexeUtil.division(c, c2);
        System.out.println("Division entre c1 et c2 = " + c2);

        c2 = ComplexeUtil.soustraction(c, c1);
        System.out.println("Soustraction entre c1 et c2 = " + c2);

        boolean b = ComplexeUtil.sontEgaux(c1, c3);
        System.out.println("Egalite entre c1 et c3 = " + b);

        c2 = ComplexeUtil.multiplication(c, c1);
        System.out.println("Multiplication entre c1 et c2 = " + c2);



    }
}