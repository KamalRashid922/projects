package eg.edu.alexu.csd.oop.draw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	public static frame f;
	
	public static void main(String[] args) {
		//set the frame
		/*frame=new Mainframe("Drawing Shapes");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setResizable(true);*/
		//frame.sheet.addMouseListener(new Mouse(frame.sheet));
		f = new frame ();
		f.setResizable(true);
		f.setVisible(true);
		
		
	}
	
	

}
