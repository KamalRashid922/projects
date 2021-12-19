package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.*;

public class Mainframe extends JFrame {
	int x1=0;
	int y1=0;
	int x2=0;
	int y2=0;
	int counter;
	Control panel;
	JPanel sheet;
	JMenuBar menubar;
	JMenu draw;
	JMenu color;
	JMenu move;
	JMenu delete;
	JMenu resize;
	JMenu undo;
	JMenu redo;
	JMenu select;
	JMenuItem line;
	JMenuItem circle;
	JMenuItem ellipse;
	JMenuItem triangle;
	JMenuItem rectangle;
	JMenuItem squre;
	LinkedList<IShape> shapes=new LinkedList<IShape>();
	
	public Mainframe(String title) {
		super(title);
		
		//set layout manager
		setLayout(new BorderLayout());
		
		//set components
		panel=new Control();
		sheet=new JPanel();  //it's the frame work we will draw in
			//sheet.setBorder(BorderFactory.createEtchedBorder());
			Dimension size=getPreferredSize();
			size.height=700;
			sheet.setBackground(Color.white);
			sheet.setPreferredSize(size);
		
		menubar=new JMenuBar();
		setJMenuBar(menubar);
		
		draw=new JMenu("Draw");
		color=new JMenu("Color");
		move=new JMenu("Move");
		delete=new JMenu("Delete");
		resize=new JMenu("Resize");
		undo=new JMenu("Undo");
		redo=new JMenu("Redo");
		select=new JMenu("Select");
		
		line=new JMenuItem("Line");
		circle=new JMenuItem("Circle");
		ellipse=new JMenuItem("Ellipse");
		triangle=new JMenuItem("Triangle");
		rectangle=new JMenuItem("Rectangle");
		squre=new JMenuItem("Square");
		
		
		//add the components
		Container c=getContentPane();
		
		c.add(panel,BorderLayout.NORTH);
		c.add(sheet ,BorderLayout.SOUTH);
		
		menubar.add(draw);
		menubar.add(color);
		menubar.add(move);
		menubar.add(delete);
		menubar.add(resize);
		menubar.add(undo);
		menubar.add(redo);
		menubar.add(select);
		
		draw.add(line);
		draw.add(circle);
		draw.add(ellipse);
		draw.add(triangle);
		draw.add(rectangle);
		draw.add(squre);
		
		
		//Mouse
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x1=x2=e.getX(); 
				y1=y2=e.getY();
				repaint();
				super.mousePressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				x2=e.getX(); 
				y2=e.getY();
				//Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
				//saving shapes
				switch (counter) {
				case 1:
					IShape line =new Line(x1, y1, x2, y2);
					shapes.add(line);
					break;
				
				case 2:
					x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
					IShape circle =new Circle(x1, y1, x2, x2);
					shapes.add(circle);
					break;
				
				case 3:
					x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
					IShape ellipse =new Circle(x1, y1, x2, x2/2);
					shapes.add(ellipse);
					break;
				case 4:
					IShape rectangle=new Rectangle(x1, y1, x2-x1, y2-y1);
					shapes.add(rectangle);
					break;
				case 5:
					IShape square=new Rectangle(x1, y1, x2-x1, x2-x1);
					shapes.add(square);
					break;
				case 6:
					IShape triangle=new Triangle(x1, y1, x2, y2,
						x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))
						, y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);
					shapes.add(triangle);
					break;
				default:
					break;
				}
				
				
				super.mouseReleased(e);
			}
		});
		
		
		addMouseMotionListener(new MouseMotionListener() {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			x2=e.getX(); 
			y2=e.getY();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
				
		}
		});
		
		
		//line behavior
		line.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.label.setText("Line");
				counter=1;
			}
		});	
		
		//circle behavior
		circle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.label.setText("Circle");
				counter=2;
				
			}
		});
		
		//ellipse behavior
		ellipse.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
			panel.label.setText("Ellipse");
			counter=3;
						
			}
		});	
		
		//rectangle behavior
		rectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.label.setText("Rectangle");
				counter=4;	
			}
		});
		
		//rectangle behavior
		squre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.label.setText("Square");
				counter=5;	
			}
		});
		
		//triangle behavior
		triangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.label.setText("Triangle");
				counter=6;
			}
		});
}
			
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (IShape s : shapes) {
			s.draw(g);
		}
		
		//choose which to draw
		switch (counter) {
		case 1:
			g.drawLine(x1, y1, x2, y2);
			break;
		
		case 2:
			x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
			g.drawOval(x1, y1,x2,x2);
			break;
		
		case 3:
			x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
			g.drawOval(x1, y1,x2,x2/2);
			break;
		case 4:
			g.drawRect(x1, y1, x2-x1, y2-y1);
			break;
		case 5:
			g.drawRect(x1, y1, x2-x1, x2-x1);
			break;
		case 6:
			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x1, y1,x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)),
					y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);
			g.drawLine(x2, y2, x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))
			, y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);
			break;
			
		default:
			break;
		}
		
		
	}
	
}
