package sn.ept.git.seminaire.cicd.demo;

public interface ICalculator {

    double add (double a,double b);
    double subtract(double a, double b);
    double multiply (double a,double b);
    double divide (double a,double b) throws ArithmeticException;

}
