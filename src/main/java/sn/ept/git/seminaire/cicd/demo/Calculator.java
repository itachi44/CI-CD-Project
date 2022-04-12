package sn.ept.git.seminaire.cicd.demo;

public class Calculator implements ICalculator {

    public static final String DIVIDE_BY_ZERO = "Can not divide by zero";

    @Override
    public double add(double a, double b) {
        return a+b;
    }

    @Override
    public double subtract(double a, double b) {
        return  a-b;
    }

    @Override
    public double multiply(double a, double b) {
        return  a*b;
    }

    @Override
    public double divide(double a, double b) throws ArithmeticException {
        if(b==0){
            throw new ArithmeticException(DIVIDE_BY_ZERO);
        }
        return a/b;
    }
}
