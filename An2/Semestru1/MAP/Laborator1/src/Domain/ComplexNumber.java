package Domain;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal(){return real;}

    public double getImaginary(){return imaginary;}

    public static ComplexNumber parseComplexNumber(String complexnumber){
        int middle_index=-1;
        for (int i=1; i<complexnumber.length(); i++){
            if (complexnumber.charAt(i)=='+' || complexnumber.charAt(i)=='-'){
                middle_index=i;
                break;
            }
        }
        int real_part, imaginary_part;
        String real_str=complexnumber.substring(0, middle_index);
        String imaginary_str=complexnumber.substring(middle_index);
        if (imaginary_str.length()==2) {
            if (imaginary_str.charAt(0) == '+') imaginary_part = 1;
            else imaginary_part = -1;
        }
        else {
            imaginary_str=imaginary_str.substring(0, imaginary_str.length()-2);
            imaginary_part = Integer.parseInt(imaginary_str);
        }
        real_part=Integer.parseInt(real_str);
        return new ComplexNumber(real_part,imaginary_part);
    }

    public String toString(){
        if (imaginary==1) return (int)real+"+i";
        if (imaginary==-1) return (int)real+"-i";
        if (imaginary<0) return (int)real+""+(int)imaginary+"*i";
        return (int)real+"+"+(int)imaginary+"*i";
    }
}
