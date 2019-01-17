import java.lang.Math.*;

class ComplexePolaire implements IComplexe {
    private double module, argument;

    public ComplexePolaire(double module, double argument){
        this.module = module;
        this.argument = argument;
    }

    public double reelle() {
        return module * Math.cos(argument);
    }

    public double imaginaire() {
        return module * Math.sin(argument);
    }
    
    public String toString(){
        return "Complexe Polaire : module = " + this.module + " | argument = " + this.argument; 
    } 
}