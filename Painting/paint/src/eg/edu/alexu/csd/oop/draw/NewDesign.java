package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class NewDesign extends JFrame {

	 JFrame frame;
	LinkedList<IShape> shapes=new LinkedList<IShape>();
	int x1=0;
	int y1=0;
	int x2=0;
	int y2=0;
	int counter;
	Color c1=Color.black;
	Color fillcolor1;
	private JTextField txtSelectedShape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDesign window = new NewDesign();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
			}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewDesign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.RED);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.RED);
		frame.setTitle("Draw Shapes");
		frame.setBounds(100, 100, 1366, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 0));
		panel.setBounds(0, 0, 1366, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(154, 0, 336, 100);
		panel.add(panel_1);
		panel_1.setBackground(new Color(204, 0, 0));
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_1.setLayout(null);
		
		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(126, 78, 98, 14);
		panel_1.add(lblFormat);
		lblFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormat.setForeground(new Color(255, 255, 255));
		
		//
		
		JColorChooser colorChooser = new JColorChooser();
		 
        colorChooser.setColor(0, 51, 255);
 
        JLabel previewLabel = new JLabel("Selected Color");
        previewLabel.setFont(new Font("Serif", Font.BOLD, 34));
        previewLabel.setSize(previewLabel.getPreferredSize());
 
        colorChooser.setPreviewPanel(previewLabel);
 
        ActionListener okActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                c1=colorChooser.getColor();
            }
        };
 
        ActionListener cancelActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        };
 
        JDialog dialog = JColorChooser.createDialog(frame,"Color Picker", false, colorChooser, okActionListener, cancelActionListener);
        
        //
		
		JButton btnSetColor = new JButton("set color");
		btnSetColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		btnSetColor.setBounds(89, 11, 78, 28);
		panel_1.add(btnSetColor);
		btnSetColor.setForeground(Color.RED);
		btnSetColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSetColor.setBackground(Color.LIGHT_GRAY);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(10, 11, 69, 62);
		panel_1.add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSelect.setForeground(Color.RED);
		btnSelect.setBackground(Color.LIGHT_GRAY);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBounds(177, 11, 69, 62);
		panel_1.add(btnDelete);
		
		JButton btnResize = new JButton("resize");
		btnResize.setForeground(Color.RED);
		btnResize.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnResize.setBackground(Color.LIGHT_GRAY);
		btnResize.setBounds(256, 11, 69, 62);
		panel_1.add(btnResize);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(489, 0, 629, 100);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_2.setBackground(new Color(204, 0, 0));
		
		JLabel lblShapes = new JLabel("Shapes");
		lblShapes.setHorizontalAlignment(SwingConstants.CENTER);
		lblShapes.setForeground(Color.WHITE);
		lblShapes.setBounds(269, 78, 98, 14);
		panel_2.add(lblShapes);
		
		JButton square = new JButton("");
		square.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=5;
			}
		});
		square.setToolTipText("Square");
		Image img =new ImageIcon(this.getClass().getResource("/playstation-square-icon.png")).getImage();
		square.setIcon(new ImageIcon(img));
		
		square.setForeground(Color.RED);
		square.setFont(new Font("Tahoma", Font.BOLD, 10));
		square.setBackground(Color.LIGHT_GRAY);
		square.setBounds(31, 11, 69, 62);
		panel_2.add(square);
		
		JButton rectangle = new JButton("");
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=4;
			}
		});
		rectangle.setToolTipText("Rectangle");
		Image img1 =new ImageIcon(this.getClass().getResource("/rectangle-icon.png")).getImage();
		rectangle.setIcon(new ImageIcon(img1));
		rectangle.setForeground(Color.RED);
		rectangle.setFont(new Font("Tahoma", Font.BOLD, 10));
		rectangle.setBackground(Color.LIGHT_GRAY);
		rectangle.setBounds(128, 11, 69, 62);
		panel_2.add(rectangle);
		
		JButton triangle = new JButton("");
		triangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=6;
			}
		});
		triangle.setToolTipText("Triangle");
		Image img2 =new ImageIcon(this.getClass().getResource("/playstation-triangle-icon.png")).getImage();
		triangle.setIcon(new ImageIcon(img2));
		triangle.setForeground(Color.RED);
		triangle.setFont(new Font("Tahoma", Font.BOLD, 10));
		triangle.setBackground(Color.LIGHT_GRAY);
		triangle.setBounds(233, 11, 69, 62);
		panel_2.add(triangle);
		
		JButton line = new JButton("");
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				counter=1;
			}
		});
				
		line.setToolTipText("Line");
		Image img3 =new ImageIcon(this.getClass().getResource("/Line-icon.png")).getImage();
		line.setIcon(new ImageIcon(img3));
		line.setForeground(Color.RED);
		line.setFont(new Font("Tahoma", Font.BOLD, 10));
		line.setBackground(Color.LIGHT_GRAY);
		line.setBounds(334, 11, 69, 62);
		panel_2.add(line);
		
		JButton ellipse = new JButton("");
		ellipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=3;
			}
		});
		ellipse.setToolTipText("Ellipse");
		Image img4 =new ImageIcon(this.getClass().getResource("/Ellipse-tool-icon.png")).getImage();
		ellipse.setIcon(new ImageIcon(img4));
		ellipse.setForeground(Color.RED);
		ellipse.setFont(new Font("Tahoma", Font.BOLD, 10));
		ellipse.setBackground(Color.LIGHT_GRAY);
		ellipse.setBounds(435, 11, 69, 62);
		panel_2.add(ellipse);
		
		JButton circle = new JButton("");
		circle.setToolTipText("Circle");
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=2;
			}
		});
		Image img5 =new ImageIcon(this.getClass().getResource("/playstation-circle-icon.png")).getImage();
		circle.setIcon(new ImageIcon(img5));
		circle.setForeground(Color.RED);
		circle.setFont(new Font("Tahoma", Font.BOLD, 10));
		circle.setBackground(Color.LIGHT_GRAY);
		circle.setBounds(532, 11, 69, 62);
		panel_2.add(circle);
		
		JButton btnSave = new JButton("save");
		btnSave.setBounds(1142, 11, 78, 62);
		panel.add(btnSave);
		btnSave.setForeground(Color.RED);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSave.setBackground(Color.LIGHT_GRAY);
		
		JButton btnLoad = new JButton("load");
		btnLoad.setForeground(Color.RED);
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLoad.setBackground(Color.LIGHT_GRAY);
		btnLoad.setBounds(1252, 11, 78, 62);
		panel.add(btnLoad);
		
		JButton btnUndo = new JButton("undo");
		btnUndo.setForeground(Color.RED);
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnUndo.setBackground(Color.LIGHT_GRAY);
		btnUndo.setBounds(10, 11, 67, 62);
		panel.add(btnUndo);
		
		JButton btnRedo = new JButton("redo");
		btnRedo.setForeground(Color.RED);
		btnRedo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRedo.setBackground(Color.LIGHT_GRAY);
		btnRedo.setBounds(87, 11, 57, 62);
		panel.add(btnRedo);
		
		txtSelectedShape = new JTextField();
		txtSelectedShape.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSelectedShape.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelectedShape.setText("Selected Shape :");
		txtSelectedShape.setBackground(new Color(240, 230, 140));
		txtSelectedShape.setBounds(0, 100, 1350, 34);
		frame.getContentPane().add(txtSelectedShape);
		txtSelectedShape.setColumns(10);
		
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
