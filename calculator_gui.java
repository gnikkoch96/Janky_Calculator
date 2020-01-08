package Calculator;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class calculator_gui extends JFrame implements ActionListener {
	/*
	 *  1. Can't hold to a large amount of number (non-scientific)
	 */
	
	private static String stringResult = "0";			//Will be used to display numbers in the numbers field
	private static JTextField numField;					//Switched to Static since we are going to be accessing it throughout the program
	private static JTextField historyNumField;			//Display which numbers have been pressed along with their arithmetic symbol. (Note: Resets after equal)
	
	private static ArrayList<String> symbolsList;		//Stores each symbol pressed, this is used to display the previous symbol (if necessary)
	private static boolean pressedArith = false;		//Checks to see if an arithmetic symbol was pressed (might not be necessary)
	private static boolean pressedEqual = false;		//For User Test: Presssing Equals makes inputOne = result and inputTwo = same
	private static boolean initialNum = false;			//Checks to see if Number is the first number that is pressed so that the 0 can change to the number
	private static boolean placedDot = false;			//Checks to see if the "." was pressed (Note: it can only be pressed once)
	private static boolean convertNeg = false;			//False = didn't convert, true = convert
	private static boolean firstInput = false;			//Checks to see if the first input was made (registered after pressing arithmetic symbol)
	public static boolean secondInput = false;			//Checks to see if the second input was made (registered after clicking on another arithmetic symbol which includes "=")

	
	//Main Constructor
	public calculator_gui() {
		super("Nikko's Janky Calculator");
		this.getContentPane().setPreferredSize(new Dimension(300,400));
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		//Adding Components
		this.addNumbersField();
		this.addButtons();
		
		//Updates
		this.revalidate();
		this.repaint();
		this.pack();
		
		//Closing Operations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		symbolsList = new ArrayList<String>();
	}	

	//Components
	public void addNumbersField() {
		JPanel fields = new JPanel();
		fields.setLayout(new BorderLayout());
		
		historyNumField = new JTextField();
		historyNumField.setHorizontalAlignment(SwingConstants.RIGHT);
		historyNumField.setEditable(false);
		
		//Display Numbers Field
		numField = new JTextField("0");
		numField.setHorizontalAlignment(SwingConstants.RIGHT);
		numField.setEditable(false);
		
		fields.add(historyNumField,BorderLayout.NORTH);
		fields.add(numField, BorderLayout.CENTER);
		
		this.getContentPane().add(fields, BorderLayout.NORTH);
		
	}
	
	public void addButtons() {
		JButton one, two, three, four, five, six, seven, eight, nine, zero, add, sub, mult, div, equals, neg, dot, del, clear;
		JPanel numContainer, arithContainer, numPad, arithPad;

		//Numbers	
		numContainer = new JPanel();
		numContainer.setLayout(new BorderLayout());
		
		numPad = new JPanel();							//Contains the Numbers
		numPad.setLayout(new GridLayout(4,3));			//4x3 Matrix
		
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		neg = new JButton("+/-");
		zero = new JButton("0");
		dot = new JButton(".");
		clear = new JButton("Clear");					//Appears in the Numbers side

		
		//Adding ActionListeners
		clear.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		
		neg.addActionListener(this);
		zero.addActionListener(this);
		dot.addActionListener(this);
		
		//7, 8 , 9
		numPad.add(seven);
		numPad.add(eight);
		numPad.add(nine);
		
		//4, 5, 6
		numPad.add(four);
		numPad.add(five);
		numPad.add(six);
		
		//1, 2, 3
		numPad.add(one);
		numPad.add(two);
		numPad.add(three);
		
		//+/-, 0, .
		numPad.add(neg);
		numPad.add(zero);
		numPad.add(dot);
		
		numContainer.add(clear, BorderLayout.NORTH);
		numContainer.add(numPad, BorderLayout.CENTER);
		
		//Arithmetic Symbols
		arithContainer = new JPanel();
		arithContainer.setLayout(new BorderLayout());
		
		arithPad = new JPanel();						//Contains Arithmetic Symbols
		arithPad.setLayout(new GridLayout(5,1));		//4x1 Matrix
		
		add = new JButton("+");
		mult = new JButton("*");
		sub = new JButton("-");
		div = new JButton("/");
		equals = new JButton("=");
		del = new JButton("<X");						//Appear in the Arithmetic Side
		
		add.addActionListener(this);
		mult.addActionListener(this);
		sub.addActionListener(this);
		div.addActionListener(this);
		equals.addActionListener(this);
		del.addActionListener(this);
		
		arithPad.add(add);
		arithPad.add(sub);
		arithPad.add(mult);
		arithPad.add(div);
		arithPad.add(equals);
		
		arithContainer.add(del, BorderLayout.NORTH);
		arithContainer.add(arithPad, BorderLayout.CENTER);
		
		this.getContentPane().add(numContainer,BorderLayout.WEST);
		this.getContentPane().add(arithContainer, BorderLayout.CENTER);
	}
	
	//Checks
	public boolean checkArithmetic(String command) {	//Checks if any of the arithmetic buttons were pressed
		//Result: First Input is Solidified
		if(command.contentEquals("+") || command.contentEquals("-") || command.contentEquals("*") || command.contentEquals("/"))
			return true;
		else
			return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//Checks if the current value of numField is "0"
		if(numField.getText().contentEquals("0")) {
			initialNum = false;
		}
		
		//Displaying the Number (for inputOne and inputTwo)
		if(!initialNum) {																					//0 is the only number present
			firstInput = true;																				//Checks that the first input has been stored
			switch(command) {	
				case "1": numField.setText("1");
					break;
				case "2": numField.setText("2");
					break;
				case "3": numField.setText("3");
					break;
				case "4": numField.setText("4");
					break;
				case "5": numField.setText("5");
					break;
				case "6": numField.setText("6");
					break;
				case "7": numField.setText("7");
					break;
				case "8": numField.setText("8");
					break;
				case "9": numField.setText("9");
					break;	
				case ".": 
					if(!placedDot) {
						numField.setText("0.");
						placedDot = true;
					}
					break;
			}		
			initialNum = true;
		}else {	
			//User entered a number, so it must append to current string to obtain the actual value			
			switch(command) {	
			case "1": numField.setText(numField.getText() + "1");
				break;
			case "2": numField.setText(numField.getText() + "2");
				break;
			case "3": numField.setText(numField.getText() + "3");
				break;
			case "4": numField.setText(numField.getText() + "4");
				break;
			case "5": numField.setText(numField.getText() + "5");
				break;
			case "6": numField.setText(numField.getText() + "6");
				break;
			case "7": numField.setText(numField.getText() + "7");
				break;
			case "8": numField.setText(numField.getText() + "8");
				break;
			case "9": numField.setText(numField.getText() + "9");
				break;
			case "0": numField.setText(numField.getText() + "0");
				break;
			case ".": 
				if(!placedDot) {
					numField.setText(numField.getText() + ".");
					placedDot = true;
				}
				break;
				
			}
		}
		
		//Equals User Tests
		if(command.contentEquals("=") && (!firstInput || !secondInput)) {									
			//User Test: Pressing "=" with no values for inputOne and inputTwo
			if(!firstInput) {
				arithmetic_methods.inputOne = 0;
				arithmetic_methods.inputTwo = 0;
				historyNumField.setText("0 + 0 =");				
			}else if (firstInput && !secondInput) {
			//User Test: Pressing input One and then "=" will make input Two "0"
				arithmetic_methods.inputOne = Double.parseDouble(numField.getText());
				arithmetic_methods.inputTwo = arithmetic_methods.inputOne;						
				historyNumField.setText(arithmetic_methods.inputOne + " " + arithmetic_methods.arithSymbol + " " + arithmetic_methods.inputOne + " =");
				
				//Calculate
				stringResult = arithmetic_methods.equals();
				
				//Display
				numField.setText(stringResult);
			}	
		//Delete 
		}else if(command.contentEquals("<X")){			
			//The value of numField remains 0 if the user tries to delete values when it is 0
			if(!numField.getText().contentEquals("0")) {
				String currentNum = numField.getText();
				//-2 since -1 to adjust and another -1 to take away one
				currentNum = currentNum.substring(0, currentNum.length() - 1);		
				numField.setText(currentNum);
		
				if(currentNum.length() == 0)	
					//When the last number is a single digit, then it reverts to 0 if it is deleted
					numField.setText("0");
			}
			
		//Clear Field
		}else if(command.contentEquals("Clear")) {
			//Resets everything
			pressedArith = false;
			initialNum = false;
			firstInput = false;
			secondInput = false;
			placedDot = false;
			convertNeg = false;
			
			historyNumField.setText("");
			numField.setText("0");
			
			arithmetic_methods.inputOne = 0;
			arithmetic_methods.inputTwo = 0;
		
			
		//Inverse Sign (as long as the value doesn't equal to 0)
		}else if(command.contentEquals("+/-") && !numField.getText().contentEquals("0")) {
			//Store +/-
			arithmetic_methods.arithSymbol = command;
			arithmetic_methods.inputOne = Double.parseDouble(numField.getText());
			
			//Execute and Display
			if(!convertNeg) {
				//Adds "-"
				numField.setText("-" + numField.getText());
				convertNeg = true;
			}else {
				//Removes "-"
				numField.setText(numField.getText().substring(1, numField.getText().length()));
				convertNeg = false;
			}
		
		}else if(checkArithmetic(command) && !secondInput) {												//Input one is stored after pressing an arithmetic symbol										
			convertNeg = false;
			//Store Input One
			arithmetic_methods.inputOne = Double.parseDouble(numField.getText());
			historyNumField.setText(numField.getText() + " " + command);									//Stores the number pressed when an arithmetic symbol is pressed

			//Store Arithmetic Symbol
			arithmetic_methods.arithSymbol = command;
			symbolsList.add(command);
			
			//If Input Two hasn't been placed, then the symbols can still be replaced
			if(!secondInput) {
				arithmetic_methods.arithSymbol = command;
			}
			pressedArith = true;
			
			//Second Input is in the process of being stored		
			initialNum = false;
		}else if(!checkArithmetic(command) && firstInput && pressedArith && !secondInput) {					//Arithmetic symbol is stored after pressing a number after stage one		
			secondInput = true;
			
			//Entering number for the second input
			if(!initialNum) {								
				switch(command) {	
					case "1": numField.setText("1");
						break;
					case "2": numField.setText("2");
						break;
					case "3": numField.setText("3");
						break;
					case "4": numField.setText("4");
						break;
					case "5": numField.setText("5");
						break;
					case "6": numField.setText("6");
						break;
					case "7": numField.setText("7");
						break;
					case "8": numField.setText("8");
						break;
					case "9": numField.setText("9");
						break;
					case ".":
						if(!placedDot) {
							numField.setText("0.");
							placedDot = true;
						}
						break;
						
						
				}		
				initialNum = true;				
			}			
		}else if((checkArithmetic(command) || command.contentEquals("=")) && firstInput && secondInput) {
			if(!pressedEqual) {
				//Storing Input Two
				arithmetic_methods.inputTwo = Double.parseDouble(numField.getText());
			}
			
			if(checkArithmetic(command)) {
				//Pressing Arithmetic Symbol
				historyNumField.setText(historyNumField.getText() +  " " + numField.getText() + " " + command);	
				
				//Stores Symbol
				symbolsList.add(command);
				
			}else {	
				//Pressing Equal
				if(!pressedEqual)
					historyNumField.setText(historyNumField.getText() + " " + numField.getText() + " =");
				else
					//Displays with original Arithmetic Symbol
					historyNumField.setText(arithmetic_methods.inputOne + " " + symbolsList.get(symbolsList.size() - 1) + " " + arithmetic_methods.inputTwo + " =");
				
			}
			
			if(!command.contentEquals("=")) {
				//Store updated Symbol
				arithmetic_methods.arithSymbol = command;
			}
			
			//Execute Calculation and Store Results
			stringResult = arithmetic_methods.equals();
			
			//Display Results
			numField.setText(stringResult);
			
			//Input One = Results
			arithmetic_methods.inputOne = Double.parseDouble(stringResult);
			System.out.println("Input One: " + arithmetic_methods.inputOne);
			System.out.println("Input Two: " + arithmetic_methods.inputTwo);
			
			if(!command.contentEquals("=")) {											//If the User presses "=" again -> inputOne = results and inputTwo = stays the same
				historyNumField.setText(arithmetic_methods.inputOne + " " + command);
				
				//Preparing to continue after calculation
				secondInput = false;	
				initialNum = false;
				
				pressedEqual = false;
			}else {																		//Pressed Equal
				pressedEqual = true;
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	//Execution
	public static void executeCalculator() {
		calculator_gui obj = new calculator_gui();
		obj.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				executeCalculator();
			}
		});
	}
	

}
