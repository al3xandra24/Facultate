import Domain.ComplexNumber;

public class Main {

    public static void main(String[] args) {
        if (!Validator.isExpressionValid(args)) {
            System.out.println("Invalid expression");
        } else {
            ComplexNumber result = EvaluateExpression.evaluate(args);
            System.out.println(result.toString());
        }
    }
}
