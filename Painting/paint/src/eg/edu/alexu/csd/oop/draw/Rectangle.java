package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

	public class Rectangle extends IShape {
		public Rectangle(int x1, int y1 ,int x2 ,int y2 ) {
			super();
			p2.x = x2;
			p2.y = y2;
			p1.x=x1;
			p1.y=y1;
		}
		
		@Override
		public void draw(Graphics canvas) {
			canvas.setColor(c);
			canvas.drawRect(p1.x, p1.y, p2.x, p2.y);
			//canvas.setColor(fillcolor);
			//canvas.fillRect(p1.x+2, p1.y+2, p2.x, p2.y);
			super.draw(canvas);
		}

}
