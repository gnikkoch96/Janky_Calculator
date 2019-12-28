package Calculator;

public class arithmetic_methods {
	
	//This value will change depending on what calculation is being performed
	public static double results;						//What will displayed after calcuations are complete
	public static double number1;						//Variable 1	
	public static double number2;						//Variable 2
	public static int arithmeticNum;					//Determines which arithemetic to execute
	
	//Conversion (Neg to Pos and Vice Versa)
	public static double negConv() {
		return number1 * (-1);
	}
		
	//Equals
	public void equals(int arithmeticNum) {
		switch(arithmeticNum) {
			case 1: //Adding
				results = add(number1, number2);
				break;
			case 2: //Subtracting
				results = subtract(number1, number2);
				break;
			case 3: //Multiplying
				results = multiply(number1, number2);
				break;
			case 4: //Dividing
				results = divide(number1, number2);
				break;		
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
