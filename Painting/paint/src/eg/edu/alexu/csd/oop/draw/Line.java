package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

public class Line extends IShape {

	public Line(int x1, int y1 ,int x2 ,int y2 ) {
		super();
		p2.x = x2;
		p2.y = y2;
		p1.x=x1;
		p1.y=y1;
	}
	
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(c);
		canvas.drawLine(p1.x, p1.y, p2.x, p2.y);
		super.draw(canvas);
	}

}
