package eg.edu.alexu.csd.oop.game.myGame.controller.dynamicloader;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.model.Shape;
import eg.edu.alexu.csd.oop.game.myGame.view.TestMain;

public class PlatesLoader implements Loader {
	private Loadable loadable;
	private static final Logger log = Logger.getLogger(TestMain.class);
	public PlatesLoader(Loadable loadable) {
		this.loadable = loadable;
	}

	@Override
	public void loadPlugin(String path) {
		try(JarFile jarFile = new JarFile(path);) {
			log.info("Trying to load Shape Classes");
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + path +"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue;
			    }
			    // -6 because of .class
			    String className = je.getName().substring(0,je.getName().length() - 6);
			    className = className.replace('/', '.');
			    Class<?> c = cl.loadClass(className);
			    Constructor <?> co = c.getConstructor(int.class, int.class);
			    if (co.newInstance(10, 10) instanceof Shape) {
			    	loadable.addSupportedClasses(c);
			    }

				}
			} catch(Exception e) {
				log.info("failed to load classes");
				e.printStackTrace();
			}
	}

}
