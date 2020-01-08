package Calculator;

import java.util.ArrayList;

public class arithmetic_methods {
	
	//This value will change depending on what calculation is being performed
	private static double results;															//What will displayed after calculations are complete
	public static double inputOne;															//Stores input one (Note: inputOne will end up containing the result if an arithmetic symbol was pushed instead of the equals sign)
	public static double inputTwo;															//Stores input two
	public static String arithSymbol;														//Stores the Arithmetic Symbol


	//Arithmetic Method
	public static String equals() {		 
			switch(arithSymbol) {
				case "+":	//Adding
					results = inputOne + inputTwo;
					break;
				case "-":	//Subtracting
					results = inputOne - inputTwo;
					break;
				case "*":	//Multiplying
					results = inputOne * inputTwo;
					break;
				case "/":	//Dividing
					try {
						results = inputOne / inputTwo;
					}catch(ArithmeticException e) {
						e.printStackTrace();
					}
					break;	
			}		
		
		return Double.toString(results);
	}
}
