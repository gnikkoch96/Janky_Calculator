package Calculator;

import java.util.ArrayList;

public class arithmetic_methods {
	
	//This value will change depending on what calculation is being performed
	private static double results;															//What will displayed after calculations are complete
	public static String stringResults;														//What will be displayed in the calculator_gui
	public static double inputOne;															//Stores input one (Note: inputOne will end up containing the result if an arithmetic symbol was pushed instead of the equals sign)
	public static double inputTwo;															//Stores input two
	public static String arithSymbol;														//Stores the Arithmetic Symbol


	//Arithmetic Method
	public String equals() {
		if(calculator_gui.secondInput) {													//Only calculate when the second input has been submitted
			switch(arithSymbol) {
				case "+":	//Adding
					results += inputOne + inputTwo;
					break;
				case "-":	//Subtracting
					results -= inputOne - inputTwo;
					break;
				case "x":	//Multiplying
					results *= inputOne * inputTwo;
					break;
				case "/":	//Dividing
					try {
						results /= inputOne / inputTwo;
					}catch(ArithmeticException e) {
						e.printStackTrace();
					}
					break;
				case "+/-":	//Conversing Neg to Pos 
					results = inputOne * -1;
					break;			
			}		
		}
		return Double.toString(results);
	}
}
