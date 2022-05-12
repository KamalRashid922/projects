package eg.edu.alexu.csd.oop.game.myGame.model.platesPool;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.model.Shape;
import eg.edu.alexu.csd.oop.game.myGame.model.factory.ShapesFactory;

public class ShapesPool extends ObjectPool<Shape> {
	private static final Logger log = Logger.getLogger(ShapesPool.class);
	private static ShapesFactory factory;
	private static int width;
	private static ShapesPool uniqueInstance;
	
	public static ShapesPool getInstance(int width) {
		ShapesPool.width = width;
		if (uniqueInstance != null)
			return uniqueInstance;
		else {
			uniqueInstance = new ShapesPool();
			return uniqueInstance;
		}
	}
	private ShapesPool() {
		super();
		factory = ShapesFactory.getInstance(width);
	}

	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		ShapesPool.width = width;
	}
	@Override
	protected Shape create() {
		log.info("pool creating a new shape");
		return factory.makeShape();
	}
	
	@Override
	public boolean validate(Shape object) {
		return object.isVisible();
	}
	@Override
	public void expire(Shape object) {
		log.info("pool expiring a shape");
		object.setVisible(false);
	}

}
