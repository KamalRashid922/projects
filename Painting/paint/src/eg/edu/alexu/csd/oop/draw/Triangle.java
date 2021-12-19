package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

public class Triangle extends IShape{
	//int x3;
	//int y3;
	
	public Triangle(int x1,int y1,int x2,int y2,int x3, int y3) {
		super();
		p2.x = x2;
		p2.y = y2;
		p1.x=x1;
		p1.y=y1;
		p3.x=x3;
		p3.y=y3;
		//this.x3 = x3;
		//this.y3 = y3;
	}

/*	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}
	*/
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(c);
		canvas.drawLine(p1.x, p1.y, p2.x, p2.y);
		canvas.drawLine(p1.x, p1.y, p3.x, p3.y);
		canvas.drawLine(p2.x, p2.y, p3.x, p3.y);
		super.draw(canvas);
	}

}
