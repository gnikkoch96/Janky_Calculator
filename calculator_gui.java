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
	
	private static String stringNumber = "0";			//Will be used to display numbers in the numbers field
	private static boolean firstInput = true;			//Checks to see if the value entered is the first input from the user
	private static boolean firstArith = true;			//Checks to see if the arithmetic symbol was pushed more than once, if it does than it acts like an equal button plus its own feature
	private static JTextField numField;					//Switched to Static since we are going to be accessing it throughout the program
	
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
	
	//Set Methods
	public void setFirstInput(boolean fInput) {
		this.firstInput = fInput;
	}
	
	//Components
	public void addNumbersField() {
		//Display Numbers Field
		numField = new JTextField("0");
		numField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		numField.setEditable(false);
		this.getContentPane().add(numField, BorderLayout.NORTH);
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(!stringNumber.contentEquals("0")) {
			this.firstInput = true;
		}
		
		if(this.firstInput) {						//Set the TextField to include the value rather than keep parsing it'
			System.out.println("First Input: " + this.firstInput);
			this.setFirstInput(false);				//Checks that the first input has been inputted
			switch(command) {	
				//Num Pad
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
			
		}else {									//Not the first input
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
		
		if(this.firstArith) {
			arithmetic_methods.number1 = Double.parseDouble(numField.getText());				//Stores first input into the arithmetic_methods class
			switch(command) {
				case "+": arithmetic_methods.arithmeticNum = 1;
					break;		
				case "-": arithmetic_methods.arithmeticNum = 2;
					break;
				case "x": arithmetic_methods.arithmeticNum = 3;
					break;
				case "/": arithmetic_methods.arithmeticNum = 4;
					break;
			}
			this.setFirstInput(true);															//Starts the second input
			this.firstArith = false;
		}else {
			
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
