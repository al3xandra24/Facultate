package Operations;

import Domain.ComplexNumber;

public class Division implements Operation {
    @Override
    public ComplexNumber execute_operation(ComplexNumber number1, ComplexNumber number2){
        if (number2.getReal()==0 && number2.getImaginary()==0){
            throw new ArithmeticException("Division by zero!");
        }
        double real=(number1.getReal()*number2.getReal()+number1.getImaginary()*number2.getImaginary())/
            (number2.getReal()*number2.getReal()+number2.getImaginary()*number2.getImaginary());
        double imaginary=(number1.getImaginary()*number2.getReal()-number1.getReal()*number2.getImaginary())/
                (number2.getReal()*number2.getReal()+number2.getImaginary()*number2.getImaginary());
        return new ComplexNumber(real,imaginary);
    }
}
