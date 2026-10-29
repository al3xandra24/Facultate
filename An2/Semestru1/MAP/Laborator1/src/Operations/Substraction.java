package Operations;

import Domain.ComplexNumber;

public class Substraction implements Operation {
    @Override
    public ComplexNumber execute_operation(ComplexNumber number1, ComplexNumber number2) {
        double real=number1.getReal()-number2.getReal();
        double imaginary=number1.getImaginary()-number2.getImaginary();
        return new ComplexNumber(real,imaginary);
    }
}
