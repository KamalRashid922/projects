package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.lang.CloneNotSupportedException;


import javax.swing.JFrame;

public class IShape  implements Cloneable, Shape   {
	Point p1=new Point();
	Point p2=new Point();
	Point p3=new Point();
	
	Map<String, Double> map=new HashMap();
	Color c=Color.black;
	Color fillcolor=Color.white;
	
	@Override
	public void setPosition(Point position) {
		p1=position;
	}

	@Override
	public Point getPosition() {
		return p1;
	}
	
	
	public void setPoint2(Point position) {
		p2=position;
	}

	
	public Point getPoint2() {
		return p2;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		map=properties;
		
	}

	@Override
	public Map<String, Double> getProperties() {
		return map;
	}

	@Override
	public void setColor(Color color) {
		c=color;
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public void setFillColor(Color color) {
		fillcolor=color;
	}

	@Override
	public Color getFillColor() {
		return fillcolor;
	}

	@Override
	public void draw(Graphics canvas) {	
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}

}
