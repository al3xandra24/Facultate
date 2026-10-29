package Operations;

import Domain.ComplexNumber;

public interface Operation {
    ComplexNumber execute_operation(ComplexNumber number1, ComplexNumber number2);
}

