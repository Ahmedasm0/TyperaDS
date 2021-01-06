package com.company.client;
import java.util.Scanner;

public class TimeCount {




    public static void main(String[] args){
        // write your code here
        {


            System.out.println("Enter: I want this");
            long start = System.nanoTime();
            Scanner input = new Scanner(System.in);
            String phrase = input.next();
            long elapsedTime = System.nanoTime() - start;


            String test = "I want this";







             System.out.println("time it took: " + elapsedTime);
             double seconds = (double)elapsedTime / 1_000_000_000.0;
             System.out.println("which is " + seconds + " seconds");

         }



    }

}
