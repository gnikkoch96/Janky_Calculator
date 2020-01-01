package Calculator;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class calculator_gui extends JFrame implements ActionListener {
	/*
	 *  1. Can't hold to a large amount of number (non-scientific)
	 */
	
	private static String stringResult = "0";			//Will be used to display numbers in the numbers field
	private static JTextField numField;					//Switched to Static since we are going to be accessing it throughout the program
	private static JTextField historyNumField;			//Display which numbers have been pressed along with their arithmetic symbol. (Note: Resets after equal)
	
	private static boolean pressedArith = false;		//Checks to see if an arithmetic symbol was pressed (might not be necessary)
	private static boolean initialNum = false;			//Checks to see if Number is the first number that is pressed so that the 0 can change to the number
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
	}	

	//Components
	public void addNumbersField() {
		JPanel fields = new JPanel();
		fields.setLayout(new BorderLayout());
		
		historyNumField = new JTextField();
		historyNumField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		historyNumField.setEditable(false);
		
		//Display Numbers Field
		numField = new JTextField("0");
		numField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
		mult = new JButton("x");
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
		if(command.contentEquals("+") || command.contentEquals("-") || command.contentEquals("x") || command.contentEquals("/"))
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
		if(!initialNum) {								//0 is the only number present
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
			}
		}
		
		//Storing the Arithmetic Symbol
		if(checkArithmetic(command) && !secondInput) {														//User pressed an arithmetic symbol
			//Store Input One
			firstInput = true;																				//Checks that the first input has been stored
			arithmetic_methods.inputOne = Double.parseDouble(numField.getText());
			historyNumField.setText(historyNumField.getText() + command + numField.getText());				//Stores the number pressed when an arithmetic symbol is pressed

			//Store Arithmetic Symbol
			arithmetic_methods.arithSymbol = command;
			
			//If Input Two hasn't been placed, then the symbols can still be replaced
			if(!secondInput) {
				arithmetic_methods.arithSymbol = command;
			}
			pressedArith = true;
		}else if(!checkArithmetic(command) && firstInput && pressedArith) {									//Checks that firstInput was stored and that button pressed was a number
			//Second Input is in the process of being stored
			secondInput = true;
		}else if(checkArithmetic(command) && firstInput && secondInput) {
			//Storing Input Two
			arithmetic_methods.inputTwo = Double.parseDouble(numField.getText());
			historyNumField.setText(historyNumField.getText() + command + numField.getText());	
			
			//Execute Calculation and Store Results
			stringResult = arithmetic_methods.equals();
			
			//Display Results
			numField.setText(stringResult);
			
			//Input One = Results
			arithmetic_methods.inputOne = Double.parseDouble(stringResult);
			secondInput = false;		
		}else if(command.contentEquals("=")) {
			//User Test: Pressing "=" with no values for inputOne and inputTwo
			if(!firstInput) {
				arithmetic_methods.inputOne = 0;
				arithmetic_methods.inputTwo = 0;
				historyNumField.setText("0 + 0 =");				
			}else if (firstInput && !secondInput) {
			//User Test: Pressing input One and then "=" will make input Two "0"
				arithmetic_methods.inputOne = Double.parseDouble(numField.getText());
				arithmetic_methods.inputTwo = 0;						
			}
			stringResult = arithmetic_methods.equals();	
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
