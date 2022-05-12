package eg.edu.alexu.csd.oop.game.myGame.model.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.controller.dynamicloader.Loadable;
import eg.edu.alexu.csd.oop.game.myGame.model.Shape;

public class ShapesFactory implements Loadable {
	private static ShapesFactory uniqueInstance = new ShapesFactory();
	private static int width;
	private static List<Class<?>> supportedShapes;
	private static final Logger log = Logger.getLogger(ShapesFactory.class);


	public static ShapesFactory getInstance(int backgroundWidth) {
		width = backgroundWidth;
		return uniqueInstance;
	}

	private ShapesFactory() {
		supportedShapes = new ArrayList<Class<?>>();
	}

	public Shape makeShape() {
		log.info("Making shape from factory");
		try {
			int size = supportedShapes.size();
			Constructor<?> co = supportedShapes.get((int) Math.floor(Math.random() * size)).getConstructor(int.class,
					int.class);
			Shape plate = (Shape) co.newInstance((int) Math.ceil(Math.random() * width), 0);
			return plate;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addSupportedClasses(Class<?> c) {
		supportedShapes.add(c);
	}

	@Override
	public List<Class<?>> getSupportedClasses() {
		return supportedShapes;
	}
}
