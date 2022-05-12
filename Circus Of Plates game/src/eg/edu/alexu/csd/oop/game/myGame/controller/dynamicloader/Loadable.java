package eg.edu.alexu.csd.oop.game.myGame.controller.dynamicloader;

import java.util.List;

public interface Loadable {
	public void addSupportedClasses(Class<?> c);
	public List<Class<?>> getSupportedClasses();
}
