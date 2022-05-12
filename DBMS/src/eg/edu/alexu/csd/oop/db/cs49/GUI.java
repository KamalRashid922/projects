package eg.edu.alexu.csd.oop.db.cs49;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame {
	JTextArea command;
	JTextArea consol;
	JButton btn;
	JLabel label;
	public GUI(String title) {
		super();
		
		//set Layout Manager
		setLayout(null);
		
		//set the components
		command=new JTextArea();
			command.setBounds(1,5,585,90);
			command.setBorder(BorderFactory.createTitledBorder("Command"));
			Font f=new Font("Command", 2, 15);
			command.setFont(f);
			
		consol=new JTextArea();
			consol.setBounds(1, 161, 585, 400);
			consol.setBorder(BorderFactory.createTitledBorder("Consol"));
			consol.setBackground(Color.lightGray);
			consol.setFont(f);
		
		btn=new JButton("Enter");
			btn.setBounds(80 , 110 ,70 ,40);
		
		label=new JLabel("do something!");
			label.setBounds(250 , 100, 200,50);
			label.setFont(f);
		
		//add the components
		Container c=getContentPane();
		
		c.add(command);
		c.add(btn);
		c.add(consol);
		c.add(label);
		
		
		
		

	}
}
