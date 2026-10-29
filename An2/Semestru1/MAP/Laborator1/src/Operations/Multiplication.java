package Operations;

import Domain.ComplexNumber;

public class Multiplication implements Operation{
    @Override
    public ComplexNumber execute_operation(ComplexNumber number1, ComplexNumber number2) {
        double real=number1.getReal()*number2.getReal()-number1.getImaginary()*number2.getImaginary();
        double imaginary=number1.getReal()*number2.getImaginary()+number1.getImaginary()*number2.getReal();
        return new ComplexNumber(real,imaginary);
    }
}
