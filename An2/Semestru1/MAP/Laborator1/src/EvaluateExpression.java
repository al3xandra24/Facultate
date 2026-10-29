import Domain.ComplexNumber;
import Operations.Addition;
import Operations.Division;
import Operations.Multiplication;
import Operations.Substraction;
import Operations.Operation;

public class EvaluateExpression {
    public static ComplexNumber evaluate(String expression[]){
            ComplexNumber[] values= new ComplexNumber[expression.length];
            int topComplexNumbers=-1, topOperators=-1;
            char[] operators=new char[expression.length];
            for (int i=0; i<expression.length;i++){
                if (i%2==0){
                    ComplexNumber current_number=ComplexNumber.parseComplexNumber(expression[i]);
                    topComplexNumbers++;
                    values[topComplexNumbers]=current_number;
                }
                else{
                    char current_operator=expression[i].charAt(0);
                    while (topOperators>=0 && get_priority(operators[topOperators])>= get_priority(current_operator)){
                        char operator=operators[topOperators];
                        topOperators--;
                        ComplexNumber number1=values[topComplexNumbers];
                        topComplexNumbers--;
                        ComplexNumber number2=values[topComplexNumbers];
                        topComplexNumbers--;
                        ComplexNumber result=operation(number2, number1, operator);
                        topComplexNumbers++;
                        values[topComplexNumbers] = result;
                    }
                    topOperators++;
                    operators[topOperators]=current_operator;
                }
            }
            while (topOperators>=0){
                char operator=operators[topOperators];
                topOperators--;
                ComplexNumber number1=values[topComplexNumbers];
                topComplexNumbers--;
                ComplexNumber number2=values[topComplexNumbers];
                topComplexNumbers--;
                ComplexNumber result=operation(number2, number1, operator);
                topComplexNumbers++;
                values[topComplexNumbers]=result;
            }
            return values[0];
    }

    private static int get_priority(char operator)
    {
        if (operator=='+' || operator=='-')
            return 1;
        else return 2;
    }

    private static Operation getOperation(char operator) {
        if (operator == '+') return new Addition();
        if (operator == '-') return new Substraction();
        if (operator == '*') return new Multiplication();
        return new Division();
    }

    private static ComplexNumber operation(ComplexNumber number1, ComplexNumber number2, char operator){
        Operation op = getOperation(operator);
        return op.execute_operation(number1, number2);
    }
}
