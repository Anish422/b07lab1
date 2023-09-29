import java.util.*;
import java.io.File;  
import java.util.Scanner;

import javax.naming.event.ObjectChangeListener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;




public class Polynomial {

    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        coefficients = new double[1];
        exponents = new int[1];
    }

    public Polynomial(double[] list, int[] list2){
       
        coefficients = list;
        exponents = list2;

    }

    public Polynomial(File text){

        String lineOne = "";
        
        try{
        BufferedReader  reader = new BufferedReader(new FileReader(text));
        lineOne = reader.readLine();
        reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int numOfTerms = 1;

        for (int i = 0; i < lineOne.length(); i++){
            if (lineOne.charAt(i) == '+' || lineOne.charAt(i) == '-'){
                numOfTerms = numOfTerms +1;
            }
        }

        if (lineOne.startsWith("-")){
            lineOne = "c"+lineOne.substring(1);
            numOfTerms = numOfTerms -1;
        }

        String[] terms = new String[numOfTerms];
        terms = splitTerms(numOfTerms, lineOne);

        // for (String term : terms) {
        //     System.out.println(term);
        // }

        coefficients = new double[numOfTerms];
        exponents = new int[numOfTerms];
        
        int count = 0;
        for (String term : terms){
            String[] oneTerm = new String[2];
            
            if (term.startsWith("c")){
                term = "-"+term.substring(1);
            }
            
            oneTerm = getCoandEx(term);
            coefficients[count] = Double.parseDouble(oneTerm[0]);
            exponents[count] =  Integer.parseInt(oneTerm[1]);
            count++;
        }
    }

    public static String[] getCoandEx(String term){
        String[] output = new String[2];

        if (!term.contains("x")){
            output[1] = "0";
            output[0] = term;
        }
        
        else if (term.charAt(term.length()-1) == 'x'){
            output[1] = "1";
            output[0] = term.substring(0, term.length()-1);
        }

        else {
            output = term.split("x");
        }

        return output;
    }

    public static String[] splitTerms(int numOfTerms, String input){

        String[] parts = input.split("\\+");
        String[] terms = new String[numOfTerms];
        int count =0;

        for (int i =0; i<parts.length; i++) {

            String[] temp;
            temp = parts[i].split("\\-");

            if (temp.length>1){
                terms[count] = temp[0];
                count++;
                for (int j =1; j<temp.length; j++){
                    terms[count] = "-" + temp[j];
                    count++;
                    //System.out.println(temp[j]);
                }
            }
            
            else{
              terms[count] = temp[0];
              count++;
              //System.out.println(temp[0]);
            }
          }

        return terms;
    }

    public static String[] str_append(String[] arr, String element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public static int indexOf(int expo, int[] expo_list)
    {
        for (int i=0; i<expo_list.length; i++)
        {
            if (expo_list[i] == expo) 
            {return i;}
        }

        return -1;
    }

    public static double[] doub_append(double[] arr, double element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public static int[] int_append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public Polynomial add(Polynomial pnomial){

        int length1 = this.coefficients.length;
        int length2 = pnomial.coefficients.length;
        int j;

        if (length1 > length2) {
            for (int i = 0; i<length2; i++){
                
                j = indexOf(pnomial.exponents[i], this.exponents);

                if (j != -1){
                    this.coefficients[j] = this.coefficients[j] + pnomial.coefficients[i];
                }

                else{
                    this.coefficients = doub_append(this.coefficients, pnomial.coefficients[i]);
                    this.exponents = int_append(this.exponents, pnomial.exponents[i]);
                }
            }

            return this;
        }

        else {
            
            for (int i = 0; i<length1; i++){

                j = indexOf(this.exponents[i], pnomial.exponents);

                if (j != -1){
                    pnomial.coefficients[j] = pnomial.coefficients[j] + coefficients[i];
                }

                else{
                    pnomial.coefficients = doub_append(pnomial.coefficients, this.coefficients[i]);
                    pnomial.exponents = int_append(pnomial.exponents, this.exponents[i]);
                }
            }
            
            return pnomial;
        }
        
    }

    public double evaluate(double x_value){

        int length = this.coefficients.length;

        double total = 0;

        for (int i = 0; i < length; i++){

            total = total + coefficients[i] * Math.pow(x_value, this.exponents[i]);
        }
        
        return total;
    }

    

    public boolean hasRoot(double x_value){

        double total = evaluate(x_value);

        if (total == 0.0){
            return true;
        }

        return false;
    }


    public Polynomial multiply(Polynomial pnomial){

        int length1 = this.coefficients.length;
        int length2 = pnomial.coefficients.length;

        Polynomial temp1 = new Polynomial();

        for (int i =0; i<length1; i++){

            double[] new_coeff = new double[length2];
            int[] new_expo = new int[length2];

            for (int j=0; j<length2; j++){

                new_coeff[j] = this.coefficients[i]*pnomial.coefficients[j];
                new_expo[j] = this.exponents[i]+pnomial.exponents[j];

            }

            Polynomial temp = new Polynomial();

            temp.coefficients = new_coeff;
            temp.exponents = new_expo;

            temp1 = temp1.add(temp);

        }

        return temp1;
    }

    public void saveToFile(String fileName){
        String line = "";
        for (int i =0; i <this.coefficients.length; i++){

            if (this.exponents[i] != 0 && this.coefficients[i]>0){
                line = line + "+" + Double.toString(this.coefficients[i])+ "x" + Integer.toString(this.exponents[i]);
            }
            else if (this.exponents[i] != 0 && this.coefficients[i]<0){
                line = line + Double.toString(this.coefficients[i])+ "x" + Integer.toString(this.exponents[i]);
            }
            else{
                if (this.coefficients[i]<0){
                    line = line + Double.toString(this.coefficients[i]);
                }
                else{
                    line = line + "+" + Double.toString(this.coefficients[i]);
                }
            }
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        writer.write(line);
        writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}

