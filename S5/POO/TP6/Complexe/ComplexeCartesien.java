class ComplexeCartesien implements IComplexe {
    private double reelle;
    private double imaginaire;

    public ComplexeCartesien(double reelle, double imaginaire){
        this.reelle = reelle;
        this.imaginaire = imaginaire;
    }

    public double reelle() {
        return reelle;
    }

    public double imaginaire() {
        return imaginaire;
    }
    
    public String toString() {
        return "Complexe Cartesien : reelle = " + this.reelle + " | imaginaire = " + this.imaginaire;
    }
}
