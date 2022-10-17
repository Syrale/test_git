package javafundementals1K; 

import java.io.*;

public class ScannerName {
    
    public static void main (String[] args)throws IOException {
        
    BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        System.out.println ("What is my name?");
        String name = reader.readLine();
        System.out.println ("What is your age?");
        int age = Integer.parseInt(reader.readLine());
        System.out.println ("My name is: " +name);
        System.out.println ("My age is: " +age);
        
    }
}
