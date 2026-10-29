import Domain.ComplexNumber;

 public class Validator {
    public static boolean isExpressionValid(String[] expression) {
        if (expression==null || expression.length<1 || expression.length%2==0)
            return false;
        for (int i=0; i<expression.length; i++) {
            if (i%2==0){
                if (!isComplexNumberValid(expression[i])) {
                    System.out.println(expression[i] + "is not a valid complex number");
                    return false;
                }
            }
            else {
                if (!isOperatorValid(expression[i])){
                    System.out.println(expression[i] + "is not a valid operator");
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isOperatorValid(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }

    private static boolean isComplexNumberValid(String complexnumber) {
        if (complexnumber==null || complexnumber.isEmpty())
            return false;
        if (complexnumber.charAt(0) != '+' && complexnumber.charAt(0) != '-' && !Character.isDigit(complexnumber.charAt(0)))
            return false;
        int poz;
        if (complexnumber.charAt(0) == '+' || complexnumber.charAt(0) == '-')
            poz = 1;
        else
            poz = 0;
        if (poz>=complexnumber.length() || !Character.isDigit(complexnumber.charAt(poz)))
            return false;
        while (poz<complexnumber.length() && Character.isDigit(complexnumber.charAt(poz))) {
            poz++;
        }
        if (poz>=complexnumber.length() ||complexnumber.charAt(poz) != '+' && complexnumber.charAt(poz) != '-')
            return false;
        poz++;
        if (poz>=complexnumber.length())
            return false;
        if (complexnumber.charAt(poz)=='i' && complexnumber.length()==poz+1)
            return true;
        else if (Character.isDigit(complexnumber.charAt(poz))) {
            while (poz < complexnumber.length() && Character.isDigit(complexnumber.charAt(poz))) {
                poz++;
            }
            if (poz >= complexnumber.length() || complexnumber.charAt(poz) != '*')
                return false;
            poz++;
            if (poz >= complexnumber.length() || complexnumber.charAt(poz) != 'i')
                return false;
            poz++;
        }
        else
            return false;
        return poz == complexnumber.length();
    }
}
