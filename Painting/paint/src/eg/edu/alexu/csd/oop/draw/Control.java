package eg.edu.alexu.csd.oop.draw;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Control extends JPanel {
	JLabel label;
	JTextField textfield;
	public Control() {
		//properties
		Dimension size=getPreferredSize();
		size.height=75;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("control"));
		
		//set components
		label=new JLabel();
		textfield=new JTextField();
			Dimension s=getPreferredSize();
			s.height=30;
			s.width=70;
			textfield.setPreferredSize(s);
		
		//add components
		this.add(label);
		//this.add(textfield);
		
	}
}
