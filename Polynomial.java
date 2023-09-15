public class Polynomial {

    double[] coefficients;

    public Polynomial(){
        coefficients = new double[1];
    }

    public Polynomial(double[] list){
       
        coefficients = list;

    }

    public Polynomial add(Polynomial pnomial){

        int length1 = coefficients.length;
        int length2 = pnomial.coefficients.length;

        if (length1 > length2) {
            for (int i = 0; i<length2; i++){
                coefficients[i] = coefficients[i] + pnomial.coefficients[i];
            }

            return this;
        }

        else {
            
            for (int i = 0; i<length1; i++){
                pnomial.coefficients[i] = pnomial.coefficients[i] + coefficients[i];
            }
            
            return pnomial;
        }
        
    }

    public double evaluate(double x_value){

        int length = coefficients.length;

        double total = 0;

        for (int i = 0; i < length; i++){

            total = total + coefficients[i] * Math.pow(x_value, i);
        }
        
        return total;
    }

    

    public boolean hasRoot(double x_value){

        int length = coefficients.length;

        double total = 0;

        for (int i = 0; i < length; i++){

            total = total + coefficients[i] * Math.pow(x_value, i);
        }
        
        if (total == 0.0){
            return true;
        }

        return false;
    }

}