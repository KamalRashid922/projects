package eg.edu.alexu.csd.oop.draw;

import java.lang.Math;

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

//import eg.edu.alexu.csd.oop.draw.Rectangle;

public class frame extends JFrame {
	
	//LinkedList<IShape> shapes=new LinkedList<IShape>();
	//LinkedList<Integer> types = new LinkedList<Integer>();
	
	IDrawingEngine drawengine=new IDrawingEngine();
	Map<String, Double> map=new HashMap();

	double area;
	int x=0;//it's very important
	Graphics g;
	int x1=0;
	int y1=0;
	int x2=0;
	int y2=0;
	int x3;
	int y3;
	int counter;
	int posx=0;
	int posy=0;
	Color c1=Color.black;
	int selectedat=-1;
	//Color fillcolor1=Color.white;
	//int flag=0;
	
	public frame() {
		//getContentPane().setForeground(Color.RED);
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.BLACK);
		
		
		//setForeground(Color.RED);
		setTitle("Draw Shapes");
		setBounds(100, 100, 1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 0));
		panel.setBounds(0, 0, 1366, 100);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//panel_1
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(154, 0, 336, 100);
		panel.add(panel_1);
		panel_1.setBackground(new Color(204, 0, 0));
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_1.setLayout(null);
		
		//lblFormat 
		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(126, 78, 98, 14);
		panel_1.add(lblFormat);
		lblFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormat.setForeground(new Color(255, 255, 255));
		
		
		JColorChooser colorChooser = new JColorChooser();
		 
        colorChooser.setColor(0, 51, 255);
 
        //previewLabel
        JLabel previewLabel = new JLabel("Selected Color");
        previewLabel.setFont(new Font("Serif", Font.BOLD, 34));
        previewLabel.setSize(previewLabel.getPreferredSize());
 
        colorChooser.setPreviewPanel(previewLabel);
 
        //------------------------------------------------------------------
        ActionListener okActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            	c1=colorChooser.getColor();
            	if (counter==7) {
            		if (selectedat != -1) {
            			drawengine.undo.push(drawengine.shapes.get(selectedat));
            			drawengine.undoaction.push(11);
            			drawengine.undocolor.push(drawengine.shapes.get(selectedat).getColor());
            			drawengine.shapes.get(selectedat).setColor(c1);
            			repaint();
            		}
            	}
                
              
                	
            }
        };
 
        ActionListener cancelActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        };
 
        JDialog dialog = JColorChooser.createDialog(null,"Color Picker", false, colorChooser, okActionListener, cancelActionListener);
        
        //=----------------------------------------------------------
		
		//color
		JButton btnSetColor = new JButton("color");

		btnSetColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				
			}
		});

		btnSetColor.setBounds(70, 11, 60, 62);
		panel_1.add(btnSetColor);
		btnSetColor.setForeground(Color.RED);
		btnSetColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSetColor.setBackground(Color.LIGHT_GRAY);
	
		/*
		JButton btnFillColor = new JButton("fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				flag=2;
			}
		});
		btnFillColor.setForeground(Color.RED);
		btnFillColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnFillColor.setBackground(Color.LIGHT_GRAY);
		btnFillColor.setBounds(89, 45, 78, 28);
		panel_1.add(btnFillColor);
		*/
		
		//txtSelectedShape
		JTextField txtSelectedShape = new JTextField();
		txtSelectedShape.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSelectedShape.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelectedShape.setText("Selected Shape :");
		txtSelectedShape.setBackground(new Color(240, 230, 140));
		txtSelectedShape.setBounds(0, 100, 1366, 34);
		getContentPane().add(txtSelectedShape);
		txtSelectedShape.setColumns(10);
		
		//select
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(1, 11, 68, 62);
		panel_1.add(btnSelect);
		
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=7;//to stop drawing
				
			}
		});
		
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSelect.setForeground(Color.RED);
		btnSelect.setBackground(Color.LIGHT_GRAY);
		
		//delete
		JButton btnDelete = new JButton("delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBounds(132, 11, 65, 62);
		panel_1.add(btnDelete);
		
		//resize
		JButton btnResize = new JButton("resize");
		btnResize.setForeground(Color.RED);
		btnResize.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnResize.setBackground(Color.LIGHT_GRAY);
		btnResize.setBounds(198, 11, 71, 62);
		panel_1.add(btnResize);
		
		//resize
		JButton btnmove = new JButton("Move");
		btnmove.setForeground(Color.RED);
		btnmove.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnmove.setBackground(Color.LIGHT_GRAY);
		btnmove.setBounds(270, 11, 65, 62);
		panel_1.add(btnmove);				
		
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
				txtSelectedShape.setText("Selected Shape :");
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
				txtSelectedShape.setText("Selected Shape :");
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
				txtSelectedShape.setText("Selected Shape :");
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
				txtSelectedShape.setText("Selected Shape :");
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
				txtSelectedShape.setText("Selected Shape :");
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
				txtSelectedShape.setText("Selected Shape :");
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
		
		//Mouse                                     <===========Mouse Action===========>
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
							line.c=c1;
							//line.fillcolor=fillcolor1;
							line.map.put("type" ,(double)  1);
							area=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
							line.map.put("area" , area);
							map.clear();
							drawengine.addShape(line);
							drawengine.undo.push(line);
							drawengine.undoaction.push(1);
							break;
						
						case 2:
							x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
							IShape circle =new Circle(x1, y1, x2, x2);
							circle.c=c1;
							//circle.fillcolor=fillcolor1;
							circle.map.put("type" , (double) 2);
							area=Math.PI * Math.pow(x2/2, 2);
							circle.map.put("area", area);
							map.clear();
							drawengine.addShape(circle);
							drawengine.undo.push(circle);
							drawengine.undoaction.push(2);
							break;
						
						case 3:
							x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
							IShape ellipse =new Circle(x1, y1, x2, x2/2);
							ellipse.c=c1;
							//ellipse.fillcolor=fillcolor1;
							ellipse.map.put("type" , (double) 3);
							area=Math.PI * x2 *x2/2;
							ellipse.map.put("area", area);
							map.clear();
							drawengine.addShape(ellipse);
							drawengine.undo.push(ellipse);
							drawengine.undoaction.push(3);
							break;
						case 4:
							IShape rectangle=new Rectangle(x1, y1, x2-x1, y2-y1);
							rectangle.c=c1;
							//rectangle.fillcolor=fillcolor1;
							rectangle.map.put("type" , (double) 4);
							area=(x2-x1) * (y2-y1);
							rectangle.map.put("area", area);
							map.clear();
							drawengine.addShape(rectangle);
							drawengine.undo.push(rectangle);
							drawengine.undoaction.push(4);
							break;
						case 5:
							IShape square=new Rectangle(x1, y1, x2-x1, x2-x1);
							square.c=c1;
							//square.fillcolor=fillcolor1;
							square.map.put("type" , (double) 5);
							area=Math.pow(x2-x1, 2);
							square.map.put("area", area);
							map.clear();
							drawengine.addShape(square);
							drawengine.undo.push(square);
							drawengine.undoaction.push(5);
							break;
						case 6:
							IShape triangle=new Triangle (x1, y1, x1, y2, x2, y2);
							/*(x1, y1, x2, y2,
								x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))
								, y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);*/
							triangle.c=c1;
							//triangle.fillcolor=fillcolor1;
							triangle.map.put("type" , (double) 6);
							area=0.5 * (x2-x1) * (y2-y1);
							triangle.map.put("area", area);
							map.clear();
							drawengine.addShape(triangle);
							drawengine.undo.push(triangle);
							drawengine.undoaction.push(6);
							break;
						
						default:
							break;
						}
						if(x==1) {
							drawengine.undoaction.push(10);
							x=0;
						}
						
						super.mouseReleased(e);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						repaint();
						if (counter==7) {
							posx=e.getX();
							posy=e.getY();
							
							//boolean selected =false;
							int size=drawengine.shapes.size();
							for (int i=size-1; i>-1; i--) {
								switch ((int)Math.round(drawengine.shapes.get(i).map.get("type"))) {
								case 1:
									// txtSelectedShape.setText("Line at < "+shapes.get(i).p1.x+", "+shapes.get(i).p1.y+", "+shapes.get(i).p2.x+", "+shapes.get(i).p2.y+" >   & point selected < "+posx+", "+posy+" >");
	                                if ( Math.abs((drawengine.shapes.get(i).p2.y - drawengine.shapes.get(i).p1.y)*(drawengine.shapes.get(i).p2.x - posx)) >= Math.abs(0.95*(drawengine.shapes.get(i).p2.y - posy)* (drawengine.shapes.get(i).p2.x - drawengine.shapes.get(i).p1.x) ) && Math.abs((drawengine.shapes.get(i).p2.y - drawengine.shapes.get(i).p1.y)*(drawengine.shapes.get(i).p2.x - posx) ) <= Math.abs(1.05*(drawengine.shapes.get(i).p2.y - posy)* (drawengine.shapes.get(i).p2.x - drawengine.shapes.get(i).p1.x))) {
								    selectedat=i;
										//selected =true;
									txtSelectedShape.setText("Selected Shape : Line at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+" >");
											
									}
									
									break;
								case 2:
									int centerx=drawengine.shapes.get(i).p1.x + (drawengine.shapes.get(i).p2.x)/2;
									int centery=drawengine.shapes.get(i).p1.y + (drawengine.shapes.get(i).p2.y)/2;
									int r=(drawengine.shapes.get(i).p2.x)/2;
                                    if (Math.pow(posx-centerx,2)+ Math.pow(posy-centery,2)<=Math.pow(r, 2)) {
	  
                                    	selectedat=i;
										txtSelectedShape.setText("Selected Shape : circle at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+" >");
                                    }
                                    break;
								case 3:
									int centerx1=drawengine.shapes.get(i).p1.x + (drawengine.shapes.get(i).p2.x)/2;
									int centery1=drawengine.shapes.get(i).p1.y + (drawengine.shapes.get(i).p2.y)/2;
									int a=(drawengine.shapes.get(i).p2.x)/2;
									int b=(drawengine.shapes.get(i).p2.y)/2;
									if ( (Math.pow(posx-centerx1,2))/(Math.pow(a, 2)) + (Math.pow(posy-centery1,2))/(Math.pow(b, 2)) <=1) {
                                    	selectedat=i;
										txtSelectedShape.setText("Selected Shape : ellipse at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+" >");
                                    }
                                    break;
								case 4:
									if (posx >= drawengine.shapes.get(i).p1.x && posx <= drawengine.shapes.get(i).p1.x+drawengine.shapes.get(i).p2.x && posy >= drawengine.shapes.get(i).p1.y && posy <= drawengine.shapes.get(i).p1.y+drawengine.shapes.get(i).p2.y) {
										selectedat=i;
										txtSelectedShape.setText("Selected Shape : rectangle at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+" >");
   
									}
									break;
								case 5:
									if (posx >= drawengine.shapes.get(i).p1.x && posx <= drawengine.shapes.get(i).p1.x+drawengine.shapes.get(i).p2.x && posy >= drawengine.shapes.get(i).p1.y && posy <= drawengine.shapes.get(i).p1.y+drawengine.shapes.get(i).p2.y) {
										selectedat=i;
										txtSelectedShape.setText("Selected Shape : square at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+" >");
   
									}
									break;
								case 6:
									double slope = (drawengine.shapes.get(i).p3.y - drawengine.shapes.get(i).p1.y) / (drawengine.shapes.get(i).p3.x - drawengine.shapes.get(i).p1.x);
									if (posy >= drawengine.shapes.get(i).p1.y && posy <= drawengine.shapes.get(i).p3.y && posx >= drawengine.shapes.get(i).p1.x && posx <= (posy - drawengine.shapes.get(i).p1.y)/slope + drawengine.shapes.get(i).p1.x ) {
										selectedat=i;
										txtSelectedShape.setText("Selected Shape : triangle at < "+drawengine.shapes.get(i).p1.x+", "+drawengine.shapes.get(i).p1.y+", "+drawengine.shapes.get(i).p2.x+", "+drawengine.shapes.get(i).p2.y+", "+drawengine.shapes.get(i).p3.x+", "+drawengine.shapes.get(i).p3.y+" >");
   
									}
									//else {
									//	txtSelectedShape.setText("Selected Shape : ");
									//}
									break;
								default:
									txtSelectedShape.setText("Selected Shape : ");
									break;
								
								
								}	
							}
						}
						repaint();
					}
				});
				
		
				
				
				addMouseMotionListener(new MouseMotionListener() {
					@Override
					public void mouseDragged(MouseEvent e) {
						x2=e.getX(); 
						y2=e.getY();
						//this code for moving
						if(counter==8) {
							if(drawengine.shapes.get(selectedat).map.get("type")==1) {
								drawengine.shapes.get(selectedat).p2.x=e.getX()+drawengine.shapes.get(selectedat).p2.x - drawengine.shapes.get(selectedat).p1.x;
								drawengine.shapes.get(selectedat).p2.y=e.getY()+drawengine.shapes.get(selectedat).p2.y - drawengine.shapes.get(selectedat).p1.y;
							}
							else if(drawengine.shapes.get(selectedat).map.get("type")==6) {
								drawengine.shapes.get(selectedat).p2.x=e.getX();
								drawengine.shapes.get(selectedat).p2.y=e.getY()+drawengine.shapes.get(selectedat).p2.y - drawengine.shapes.get(selectedat).p1.y;

								drawengine.shapes.get(selectedat).p3.x=e.getX()+drawengine.shapes.get(selectedat).p3.x - drawengine.shapes.get(selectedat).p1.x;
								drawengine.shapes.get(selectedat).p3.y=e.getY()+drawengine.shapes.get(selectedat).p2.y - drawengine.shapes.get(selectedat).p1.y;
							}
							
							drawengine.shapes.get(selectedat).p1.x=e.getX();
							drawengine.shapes.get(selectedat).p1.y=e.getY();
							
								
						}
						repaint();
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
							
					}
					});
				
				
				//resize behavior
				btnResize.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selectedat != -1) {
							x=1;
							drawengine.undo.push(drawengine.shapes.get(selectedat));
							counter=10;
							switch ((int) Math.round(drawengine.shapes.get(selectedat).map.get("type"))) {
							
							case 1:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=1;
								break;
							case 2:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=2;
								break;
							case 3:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=3;
								break;
							case 4:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=4;
								break;
							case 5:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=5;
								break;
							case 6:
								drawengine.removeShape(drawengine.shapes.get(selectedat));
								counter=6;
								break;
							default:
								break;
							}
							txtSelectedShape.setText("Resize the shape");
						}
					}
				});	


				//delete behavior
				btnDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selectedat != -1) {
							counter=9;
							drawengine.undo.push(drawengine.shapes.get(selectedat));
							drawengine.undoaction.push(9);
							drawengine.removeShape(drawengine.shapes.get(selectedat));
							drawengine.refresh(g);
							txtSelectedShape.setText("Click the selected shape");
							repaint();
						}
						
					}
				
				});
				
				//move behavior
				btnmove.addActionListener(new ActionListener() {
							
					@Override
					public void actionPerformed(ActionEvent e) {
						counter=8;
						txtSelectedShape.setText("Move");
						//for saving
						IShape m;
						x1=drawengine.shapes.get(selectedat).p1.x;
						y1=drawengine.shapes.get(selectedat).p1.y;
						x2=drawengine.shapes.get(selectedat).p2.x;
						y2=drawengine.shapes.get(selectedat).p2.y;
						x3=drawengine.shapes.get(selectedat).p3.x;
						y3=drawengine.shapes.get(selectedat).p3.y;
						switch ((int) Math.round(drawengine.shapes.get(selectedat).map.get("type"))) {
						case 1:
							m=new Line(x1, y1, x2, y2);
							m.map.put("type",drawengine.shapes.get(selectedat).map.get("type") );
							m.map.put("area",drawengine.shapes.get(selectedat).map.get("area") );
							m.setColor(drawengine.shapes.get(selectedat).getColor());
							drawengine.undo.push(m);
							break;
						case 2:
						case 3:
							m=new Circle(x1, y1, x2, y2);
							m.map.put("type",drawengine.shapes.get(selectedat).map.get("type") );
							m.map.put("area",drawengine.shapes.get(selectedat).map.get("area") );
							m.setColor(drawengine.shapes.get(selectedat).getColor());
							drawengine.undo.push(m);
							break;
						
						case 4:
						case 5:
							m=new Rectangle(x1, y1, x2, y2);
							m.map.put("type",drawengine.shapes.get(selectedat).map.get("type") );
							m.map.put("area",drawengine.shapes.get(selectedat).map.get("area") );
							m.setColor(drawengine.shapes.get(selectedat).getColor());
							drawengine.undo.push(m);
							break;
						
						case 6:
							m=new Triangle(x1, y1, x2, y2,x3,y3);
							m.map.put("type",drawengine.shapes.get(selectedat).map.get("type") );
							m.map.put("area",drawengine.shapes.get(selectedat).map.get("area") );
							m.setColor(drawengine.shapes.get(selectedat).getColor());
							drawengine.undo.push(m);
							break;
						default:
							break;
						}
						
						
						
						drawengine.undoaction.push(8);
					}
				});
				
				//undo behavior
				btnUndo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						counter=11;
						drawengine.undo();
						txtSelectedShape.setText("Click the screen");
						repaint();
						
					}
				});
				
				//redo behavior
				btnRedo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						drawengine.redo();
						counter=12;
						repaint();
					}
				});	
				
				//save behavior
				btnSave.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	JFileChooser chooser = new JFileChooser();
		            	int returnedValue = chooser.showSaveDialog(null);
		            	if(returnedValue == JFileChooser.APPROVE_OPTION)
		                {
		                    File selectedFile = chooser.getSelectedFile();
		                    String filepath = selectedFile.getPath();
		                    drawengine.save(filepath);
		                }
		            	
		            }
		        });
				
				//load behavior
				btnLoad.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	JFileChooser chooser = new JFileChooser();
		            	FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files", "xml" );
		                chooser.setFileFilter(filter);
		            	int returnedValue = chooser.showOpenDialog(null);
		            	if(returnedValue == JFileChooser.APPROVE_OPTION)
		                {
		                    File selectedFile = chooser.getSelectedFile();
		                    String filepath = selectedFile.getPath();
		                    drawengine.load(filepath);
		                    repaint();
		                    
		                }
		            	
		            }
		        });
	
}
	
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawengine.refresh(g);
		
		//choose which to draw
		switch (counter) {
		case 1:
			g.setColor(c1);
			g.drawLine(x1, y1, x2, y2);
			break;
		
		case 2:
			x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
			g.setColor(c1);
			g.drawOval(x1, y1,x2,x2);
		//	g.setColor(fillcolor1);
		//	g.fillOval(x1, y1,x2,x2);
			break;
		
		case 3:
			x2=(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
			g.setColor(c1);
			g.drawOval(x1, y1,x2,x2/2);
			//g.setColor(fillcolor1);
			//g.fillOval(x1, y1,x2,x2/2);
			break;
		case 4:
			g.setColor(c1);
			g.drawRect(x1, y1, x2-x1, y2-y1);
			//g.setColor(fillcolor1);
			//g.fillRect(x1, y1, x2-x1, y2-y1);
			break;
		case 5:
			g.setColor(c1);
			g.drawRect(x1, y1, x2-x1, x2-x1);
		//	g.setColor(fillcolor1);
		//	g.fillRect(x1, y1, x2-x1, x2-x1);
			break;
		case 6:
			g.setColor(c1);
			g.drawLine(x1, y1, x1, y2);
			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x1, y2, x2, y2);
			
			/*g.drawLine(x1, y1,x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)),
					y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);
			g.drawLine(x2, y2, x1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))
			, y1+(int)Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))/3);*/
			break;
			
		default:
			break;
		}
		
		
	}


}
