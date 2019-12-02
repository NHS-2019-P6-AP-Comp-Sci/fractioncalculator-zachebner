/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	public static String expression = "";
	public static String finalExpression;
	public static int currentnumer;
	public static int currentdenom;
	public static int currentoperator;
	public static boolean adding = false;
	public static boolean subtracting = false;
	public static boolean multiplying = false;
	public static boolean dividing = false;

	public static void main(String[] args) {
		System.out.println("Welcome to the fraction calculator. ");
		boolean running = true;
		while (running) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your expression. (enter \"quit\" to exit.)");
			String expressionfake = input.nextLine();
			String expression = expressionfake.toLowerCase();
			if (!expression.contentEquals("quit")) {
				System.out.println(produceAnswer(expression));
			} else {
				System.out.println("You have chosen to quit the program. ");
				running = false;
				input.close();
			}
		}
		System.out.println("Goodbye!");
	}

	public static String produceAnswer(String input) {
		int spaces = input.length() - input.replace(" ", "").length();
		if (!(spaces == 0)) {
			inputParsing(input, spaces);
		} else {
			finalExpression = "Invalid expression.";
		}
		return finalExpression;
	}

//takes the input and divides it up into whole, numerators, denominators, and operators
	public static void inputParsing(String input, int spaces) {
		int count = 0;
		int whole;
		int numer;
		int denom;
		String newinput = " ";
		boolean last = false;
		// if a space, knows it is either an expression following or an operator
		// following
		for (int i = 0; i < input.length(); i++) {
			String testinput = input.substring(i, i + 1);
			// checks if an operator and tells the program which operation will be performed
			if (testinput.contentEquals("+")) {
				adding = true;
				subtracting = false;
				multiplying = false;
				dividing = false;
			} else if (testinput.contentEquals("-")) {
				adding = false;
				subtracting = true;
				multiplying = false;
				dividing = false;
			} else if (testinput.contentEquals("*")) {
				adding = false;
				subtracting = false;
				multiplying = true;
				dividing = false;
			} else if (testinput.contentEquals("/")) {
				adding = false;
				subtracting = false;
				multiplying = false;
				dividing = true;
			}
			// if a number follows, parses it and turns it into a whole, numerator, and
			// denominator
			if (testinput.contentEquals(" ") || i == 0) {

				if (!(spaces % 2 == 0) || i == 0) {

					if (!(i == 0)) {
						String substringtester = input.substring(i + 1);
						if (substringtester.indexOf(" ") >= 0) {
							substringtester = substringtester.substring(0, substringtester.indexOf(" "));
						}
						newinput = substringtester;
					} else {
						newinput = input.substring(0, input.indexOf(" "));
					}
					if (newinput.indexOf(" ") == -1) {
						last = true;
					}
					if (!(newinput.indexOf("/") == -1)) {
						if (!(newinput.indexOf("_") == -1)) {
							whole = Integer.parseInt(newinput.substring(0, newinput.indexOf("_")));
							numer = Integer.parseInt(newinput.substring((newinput.indexOf("_") + 1), newinput.indexOf("/")));
							if (!last) {
								denom = Integer.parseInt(newinput.substring(newinput.indexOf("/") + 1, newinput.indexOf(" ")));
							} else {
								denom = Integer.parseInt(newinput.substring(newinput.indexOf("/") + 1));
							}
						} else {
							whole = 0;
							numer = Integer.parseInt(newinput.substring(0, newinput.indexOf("/")));
							if (!last) {
								denom = Integer.parseInt(newinput.substring(newinput.indexOf("/") + 1, newinput.indexOf(" ")));
							} else {
								denom = Integer.parseInt(newinput.substring(newinput.indexOf("/") + 1));
							}
						}
					} else {
						if (newinput.indexOf(" ") == -1) {
							whole = Integer.parseInt(newinput.substring(0));
							numer = 0;
							denom = 1;
						} else {
							whole = Integer.parseInt(newinput.substring(0, newinput.indexOf(" ")));
							numer = 0;
							denom = 1;
						}
					}
					if (count == 0) {
						singleUpdate(whole, numer, denom);
					} else {
						readyToMath(whole, numer, denom);
					}
					count++;

				}
				if (!(i == 0)) {
					spaces--;
				}
			}
		}
		reduce();
	}

// keeps a running total of the numerators and denominators by setting the first numerator and denominators to public variables
	public static void singleUpdate(int whole, int numer, int denom) {
		if (whole >= 0) {
			currentnumer = ((whole) * denom) + numer;
		} else {
			currentnumer = ((whole) * denom) - numer;
		}
		currentdenom = denom;
	}

// sorts out the numerators and denominators and decides what math to do with them
	public static void readyToMath(int whole, int numer, int denom) {
		int tempnum = 0;
		if (whole >= 0) {
			tempnum = ((whole) * denom) + numer;
		} else {
			tempnum = ((whole) * denom) - numer;
		}
		int tempden = denom;
		if (adding) {
			add(tempnum, tempden);
		} else if (subtracting) {
			subtract(tempnum, tempden);
		} else if (multiplying) {
			multiply(tempnum, tempden);
		} else if (dividing) {
			division(tempnum, tempden);
		} else {
			finalExpression = "ERROR: Invalid operator.";
		}
	}

// different operation methods, turn results into public variables of the running total of numerator and denominators
	public static void add(int n, int d) {
		currentnumer = (currentnumer * d) + (n * currentdenom);
		currentdenom = d * currentdenom;
	}

	public static void subtract(int n, int d) {
		currentnumer = (currentnumer * d) - (n * currentdenom);
		currentdenom = d * currentdenom;
	}

	public static void multiply(int n, int d) {
		currentnumer = currentnumer * n;
		currentdenom = currentdenom * d;

	}

	public static void division(int n, int d) {
		currentnumer = currentnumer * d;
		currentdenom = currentdenom * n;

	}

// reduces the numerator and denominator into a mixed/whole number, 
// and tests for some edge cases such as division by 0 and negative handling
	public static void reduce() {
		int n = Math.abs(currentnumer);
		int d = Math.abs(currentdenom);
		boolean nneg = false;
		boolean dneg = false;
		if (currentnumer < 0) {
			nneg = true;
		}
		if (currentdenom < 0) {
			dneg = true;
		}
		int w = 0;
		boolean underline = false;
		boolean slash = false;
		if (!((n % d) == 0)) {
			while (n > d) {
				w += 1;
				n -= d;
			}
			slash = true;
		} else {
			w = n / d;
		}
		for (int i = d; i > 0; i--) {
			if ((n % i) == 0 && (d % i) == 0) {
				n /= i;
				d /= i;
			}
		}
		if (w > 0) {
			underline = true;
		}
		if (slash) {
			if (underline) {
				finalExpression = w + "_" + n + "/" + d;
			} else {
				finalExpression = n + "/" + d;
			}
		} else {
			finalExpression = Integer.toString(w);
		}
		if (nneg ^ dneg) {
			finalExpression = "-" + finalExpression;
		}
		if (finalExpression.contentEquals("-0")) {
			finalExpression = "0";
		}
	}
}
