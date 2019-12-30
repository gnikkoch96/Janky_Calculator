package Calculator;

import java.util.ArrayList;

public class arithmetic_methods {
	
	//This value will change depending on what calculation is being performed
	public static double results;															//What will displayed after calculations are complete
	public static double inputOne;															//Stores input one (Note: inputOne will end up containing the result if an arithmetic symbol was pushed instead of the equals sign)
	public static double inputTwo;															//Stores input two
	public static String arithSymbol;														//Stores the Arithmetic Symbol

	//Conversion (Neg to Pos and Vice Versa)
	public static double negConv() {
		return inputOne * -1;
	}
		
	//Equals
	public void equals() {
		if(calculator_gui.secondInput) {													//Only calculate when the second input has been submitted
			switch(arithSymbol) {
				case "+":
					results += inputOne + inputTwo;
					break;
				case "-":
					results -= inputOne - inputTwo;
					break;
				case "x":
					results *= inputOne * inputTwo;
					break;
				case "/":
					results /= inputOne / inputTwo;
					break;
			}
		}
	}
	
	//Adding
	public double add(double num1, double num2) {
		return num1 + num2;
	}
	
	//Subtracting
	public double subtract(double num1, double num2) {
		return  num1 - num2;
	}
	
	
	//Multiplying
	public double multiply(double num1, double num2) {
		return num1 * num2;
	}
	
	//Dividing
	public double divide(double num1, double num2) {
		try {
			return num1/num2;
		}catch(ArithmeticException e) {					//Catches error if the denominator is 0
			//Generate Error Message
			return 0;
		}
	}
	
	
}
