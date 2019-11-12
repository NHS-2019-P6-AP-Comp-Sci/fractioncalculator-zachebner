/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	public static String first;
	public static String operator;
	public static String second;
	public static String expression;
	public static int whole1;
	public static int denom1;
	public static int numer1;
	public static int whole2;
	public static int denom2;
	public static int numer2;
	public static String finalExpression;
	public static void main(String[] args) {
		System.out.println("Welcome to the fraction calculator. ");
		while (!expression.equals("quit")) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your expression. (enter \"quit\" to exit.)");
			String expressionfake = input.nextLine();
			String expression = expressionfake.toLowerCase();

			System.out.println(produceAnswer(expression));
		}
		System.out.println("Goodbye!");
		// TODO: Read the input from the user and call produceAnswer with an equation

	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		first = input.substring(0, input.indexOf(" "));
		operator = input.substring(input.indexOf(" ") + 1, input.indexOf(" ") + 2);
		second = input.substring(input.indexOf(" ") + 3);
		fractionParsing(first, second);
		String string = "whole:" + whole2 + " numerator:" + numer2 + " denominator:" + denom2;

	//		
		return string;
	}

	

	public static void fractionParsing(String input1, String input2) {
		if (!(input1.indexOf("/") == -1)) {
			if (!(input1.indexOf("_") == -1)) {
				whole1 = Integer.parseInt(input1.substring(0, input1.indexOf("_")));
				numer1 = Integer.parseInt(input1.substring((input1.indexOf("_") + 1), input1.indexOf("/")));
				denom1 = Integer.parseInt(input1.substring(input1.indexOf("/") + 1));
			} else {
				whole1 = 0;
				numer1 = Integer.parseInt(input1.substring(0, input1.indexOf("/")));
				denom1 = Integer.parseInt(input1.substring(input1.indexOf("/") + 1));
			}
		} else {
			whole1 = Integer.parseInt(input1);
			numer1 = 0;
			denom1 = 1;
		}
		if (!(input2.indexOf("/") == -1)) {
			if (!(input2.indexOf("_") == -1)) {
				whole2 = Integer.parseInt(input2.substring(0, input2.indexOf("_")));
				numer2 = Integer.parseInt(input2.substring((input2.indexOf("_") + 1), input2.indexOf("/")));
				denom2 = Integer.parseInt(input2.substring(input2.indexOf("/") + 1));
			} else {
				whole2 = 0;
				numer2 = Integer.parseInt(input2.substring(0, input2.indexOf("/")));
				denom2 = Integer.parseInt(input2.substring(input2.indexOf("/") + 1));
			}
		} else {
			whole2 = Integer.parseInt(input2);
			numer2 = 0;
			denom2 = 1;
		}
//		numer1 += whole1 * denom1;
//		numer2 += whole2 * denom2;
//		doMath();

	}
//	public static void doMath() {
//		double finalDouble = 0;
//		String finalExpression = "";
//		if (operator.equals("+")) {
//			add();
//		} else if (operator.equals("-")) {
//			subtract();
//		} else if (operator.equals("*")) {
//			 multiply();
//		} else if (operator.equals("/")) {
//			divide();
//		}
//	
//	}
//	public static void add() {
//		int newn = (numer1 * denom2) + (numer2 * denom1);
//		int newd = denom2 * denom1;
//		reduce(newn, newd);
//	}
//	public static void subtract() {
//		
//	}
//	public static void multiply() {
//		
//	}
//	public static void divide() {
//		
//	}
//	public static void reduce(int newn, int newd) {
//		int n = Math.abs(newn);
//		int d = Math.abs(newd);
//		int w = 0;
//		if (!(n % d == 0)) {
//			while (n > d) {
//				w += 1;
//				n -= d;
//			}
//		} else {
//			w = n / d;
//		}
//		for (int i = 0; i < d; i++) {
//		
//		}
//	}
//	// TODO: Fill in the space below with any helper methods that you think you will
//	// need
//
}
