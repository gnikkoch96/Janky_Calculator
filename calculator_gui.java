package Calculator;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class calculator_gui extends JFrame implements ActionListener {
	
	private static String stringNumber;			//Will be used to display numbers in the numbers field
	
	//Main Constructor
	public calculator_gui() {
		super("Nikko's Janky Calculator");
		this.getContentPane().setPreferredSize(new Dimension(300,400));
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
		//Display Numbers Field
		JTextField numField = new JTextField(50);
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
