import java.io.File;


public class Driver {
    public static void main(String [] args) {
    // Polynomial p = new Polynomial();
    // System.out.println(p.evaluate(3));
    double [] c1 = {6,7,5};
    int [] e1 = {1,2,3};
    Polynomial p1 = new Polynomial(c1, e1);
    double [] c2 = {2,3,4,5};
    int [] e2 = {1,2,3,4};
    Polynomial p2 = new Polynomial(c2, e2);
    
    Polynomial s = p1.add(p2);
    printPolynomial(s);
    
    System.out.println("s(-1.231) = " + s.evaluate(-1.231));
    if(s.hasRoot(0))
    System.out.println("0 is a root of s");
    else
    System.out.println("0 is not a root of s");

    Polynomial m = p1.multiply(p2);
    printPolynomial(m);
        
    File file = new File("/Users/anish/Desktop/test.txt");
    Polynomial p5 = new Polynomial(file);

    printPolynomial(p5);

    p5.saveToFile("test2.txt");
    

    }
        public static void printPolynomial(Polynomial s){
        for (int i=0; i<s.coefficients.length; i++){
            System.out.print(s.coefficients[i] + " | ");
        }
            System.out.println("");

        for (int i=0; i<s.exponents.length; i++){
            System.out.print(s.exponents[i] + " | ");
        }
            System.out.println("");
        }
    }

