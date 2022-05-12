package eg.edu.alexu.csd.oop.game.myGame.view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.myGame.controller.startegy.Hard;
import eg.edu.alexu.csd.oop.game.myGame.controller.dynamicloader.Loader;
import eg.edu.alexu.csd.oop.game.myGame.controller.dynamicloader.PlatesLoader;
import eg.edu.alexu.csd.oop.game.myGame.controller.memento.Memento;
import eg.edu.alexu.csd.oop.game.myGame.controller.startegy.Easy;
import eg.edu.alexu.csd.oop.game.myGame.controller.startegy.Moderate;
import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;
import eg.edu.alexu.csd.oop.game.myGame.model.Sound;
import eg.edu.alexu.csd.oop.game.myGame.model.factory.ShapesFactory;

public class TestMain {
	private static CircusOfPlates circus, newCircus;
	private static GameController gameController;
	private static Memento memento;
	private static JMenuBar menuBar;
	
	
	public static void main(String[] args) {
		Thread sound = new Sound();
		sound.start();
		circus = new CircusOfPlates(1500, 800);
		Loader platesLoader = new PlatesLoader(ShapesFactory.getInstance(1500));
		File file = new File("./");
		File[] files = file.listFiles();
		for (File jarFile : files) {
			if (jarFile.getPath().substring(jarFile.getPath().length() - 4, jarFile.getPath().length()).equalsIgnoreCase(".jar")) {
				platesLoader.loadPlugin(jarFile.getPath());
			}
		}
		new Hard(circus);
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenu difficultySubmenu = new JMenu("Choose difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem moderate = new JMenuItem("Moderate");
		JMenuItem hard = new JMenuItem("Hard");
		difficultySubmenu.add(easy);
		difficultySubmenu.add(moderate);
		difficultySubmenu.add(hard);
		menu.addSeparator();
		menu.add(saveMenuItem);
		menu.add(loadMenuItem);
		menu.add(difficultySubmenu);
		menuBar.add(menu);
		gameController = GameEngine.start("test", circus, menuBar, Color.BLACK);
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Easy(circus);
			}
		});
		moderate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Moderate(circus);
				System.out.println(circus.getSpeed());
			}
		});
		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Hard(circus);
			}
		});
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newCircus = circus.clone();
				circus.setState(newCircus);
				memento = circus.save();
			}
		});
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				circus.restore(memento);
				circus.setState(newCircus);
				memento = circus.save();
				gameController.changeWorld(circus);
			}
		});
	}
	
}
