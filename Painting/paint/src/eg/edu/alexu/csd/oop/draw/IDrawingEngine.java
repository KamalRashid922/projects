package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IDrawingEngine implements DrawingEngine {
	LinkedList<IShape> shapes=new LinkedList<IShape>();
	Stack<IShape> undo=new Stack<IShape>();
	Stack<IShape> redo=new Stack<IShape>();
	Stack<Integer> undoaction=new Stack<Integer>();
	Stack<Integer> redoaction=new Stack<Integer>();
	Stack<Color> undocolor=new Stack<Color>();
	Stack<Color> redocolor=new Stack<Color>();
	
	
	
	@Override
	public void refresh(Graphics canvas) {
		for (IShape s : shapes) {
			s.draw(canvas);
		}

	}

	@Override
	public void addShape(Shape shape) {
		shapes.add((IShape) shape);
	}

	@Override
	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		for (IShape s : shapes) {
			if(s==oldShape) {
				s=(IShape) newShape;
			}
		}

	}

	@Override
	public Shape[] getShapes() {
		IShape[] sh =(IShape[]) shapes.toArray();
		return sh;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return null;
	}

	@Override
	public void undo() {
		if(undo.size()!=0) {
			switch (undoaction.peek()) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6: //draw
				redo.push(undo.pop());
				redoaction.push(undoaction.pop());
				shapes.removeLast();
				break;
			
			case 8: //move
				for (IShape s : shapes) {
					if(s.map.get("area")==undo.peek().map.get("area")) {
						redo.push(s);
						redoaction.push(undoaction.pop());
						shapes.remove(s);
						shapes.add(undo.pop());
						break;
					}
				}
				break;
			
			case 9: //delete
				shapes.add(undo.peek());
				redo.push(undo.pop());
				redoaction.push(undoaction.pop());
				break;
			
			case 10: //resize
				redo.push(shapes.getLast());
				redo.push(undo.pop());
				redoaction.push(undoaction.pop());
				shapes.removeLast();
				shapes.add(undo.pop());
				break;
			
			case 11: //color
				for (IShape s : shapes) {
					if(s.map.get("area")==undo.peek().map.get("area")) {
						redocolor.push(s.getColor());
						s.setColor(undocolor.pop());
						redo.push(undo.pop());
						redoaction.push(undoaction.pop());
						break;
					}
				}
				break;
			default:
				break;
			}
			
		}

	}

	@Override
	public void redo() {
		if(redo.size() != 0) {
			switch (redoaction.peek()) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6: //draw
				shapes.add(redo.peek());
				undo.push(redo.pop());
				undoaction.push(redoaction.pop());
				break;
			
			case 8: //move
				for (IShape s : shapes) {
					if(s.map.get("area")==redo.peek().map.get("area")) {
						undo.push(s);
						undoaction.push(redoaction.pop());
						shapes.remove(s);
						shapes.add(redo.pop());
						break;
					}
				}
				break;
			
			case 9: //delete
				shapes.remove(redo.peek());
				undo.push(redo.pop());
				undoaction.push(redoaction.pop());
				break;
			
			case 10: //resize
				redo.pop();
				undo.push(shapes.getLast());
				undoaction.push(redoaction.pop());
				shapes.removeLast();
				shapes.add(redo.pop());
				break;
			
			case 11: //color
				for (IShape s : shapes) {
					if(s.map.get("area")==redo.peek().map.get("area")) {
						undocolor.push(s.getColor());
						s.setColor(redocolor.pop());
						undo.push(redo.pop());
						undoaction.push(redoaction.pop());
						break;
					}
				}
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void save(String path) {
		if (path.contains(".xml")||path.contains(".XmL")||path.contains("Xml")) {
            try {
                XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
                xmlEncoder.writeObject(shapes);
                xmlEncoder.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
		}
	}

	@Override
	public void load(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			XMLDecoder decoder = new XMLDecoder(fis);
			shapes=(LinkedList<IShape>) decoder.readObject();
			decoder.close();
			fis.close();
			//System.out.println(shapes.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
