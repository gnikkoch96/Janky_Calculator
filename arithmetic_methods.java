package Calculator;

public class arithmetic_methods {
	
	//This value will change depending on what calculation is being performed
	public static double results;
	
	//Conversion (Neg to Pos and Vice Versa)
	public static double negConv(double num1) {
		return num1 * (-1);
	}
		
	//Equals
	public void equals(double num1, double num2, int arithmeticNum) {
		switch(arithmeticNum) {
			case 1: //Adding
				results = add(num1, num2);
				break;
			case 2: //Subtracting
				results = subtract(num1, num2);
				break;
			case 3: //Multiplying
				results = multiply(num1, num2);
				break;
			case 4: //Dividing
				results = divide(num1, num2);
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
