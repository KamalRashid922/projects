package eg.edu.alexu.csd.oop.game.myGame.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.myGame.controller.memento.Memento;
import eg.edu.alexu.csd.oop.game.myGame.controller.memento.Originator;
import eg.edu.alexu.csd.oop.game.myGame.model.iterator.Iterator;
import eg.edu.alexu.csd.oop.game.myGame.model.iterator.ShapesCollection;
import eg.edu.alexu.csd.oop.game.myGame.model.platesPool.ShapesPool;
import eg.edu.alexu.csd.oop.game.myGame.model.shapes.ControlState;
import eg.edu.alexu.csd.oop.game.myGame.model.shapes.MovingState;

public class CircusOfPlates implements World, Originator, Cloneable {
	private static final Logger log = Logger.getLogger(CircusOfPlates.class);
	private final int width;
	private final int height;
	private List<GameObject> constant = new LinkedList<GameObject>();
	private List<GameObject> movable = new LinkedList<GameObject>();
	private List<GameObject> controlable = new LinkedList<GameObject>();
	private Clown clown;
	private int speed = -5;
	private int controlSpeed = 15;
	private int score = 0;
	private int count = 0;
	private int maxCount = 10;
	private ShapesPool shapesPool;
	private ShapesCollection shapesCollection;
	private int j = 0;
	private CircusOfPlates state;

	public CircusOfPlates(int screenWidth, int screenHeight) {
		log.info("Initiating circus of plates");
		width = screenWidth;
		height = screenHeight;
		Background background = new Background("/background.png");
		constant.add(background);
		clown = new Clown((screenWidth / 2) - 75, (int) (screenHeight) - 155, "/player1.png");
		controlable.add(clown);
		Observable observer = new PlateObserver();
		clown.addObserver(observer);
		shapesPool = ShapesPool.getInstance(screenWidth);
		shapesCollection = new ShapesCollection();

	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return shapesCollection.getList();
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return controlable;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	private boolean intersect(GameObject obj1, GameObject obj2, int x) {
		if ((obj1.getY() == obj2.getY() + obj2.getHeight())
				&& (Math.abs((obj1.getX() + x - obj2.getX())) < obj2.getWidth())) {
			obj2.setY(obj1.getY() - obj2.getHeight());
			((Shape) obj2).setState(new ControlState());
			return true;
		}
		return false;
	}

	@Override
	public boolean refresh() {
		for (int h = 0; h < maxCount; h++) {
			if (clown.getLeftStack().size() == 10 || clown.getRightStack().size() == 10) {
				return false;
			}
			// count++;
			// if (count % maxCount != 0) {
			// return true;
			// }
			else {
				count = 0;
				if (j % 100 == 0) {
					Shape plate = shapesPool.acquire();
					plate.setState(new MovingState());
					shapesCollection.add(plate);
					movable.add(plate);
				}
				j++;
				Iterator<GameObject> iterator = shapesCollection.createIterator();
				while (!iterator.isDone()) {
					Shape o = (Shape) iterator.currentItem();
					o.setY(o.getY() + 1);
					if (intersect(clown.getTopLeft(), o, 0)) {
						o.setX(clown.getTopLeft().getX());
						controlable.add(o);
						shapesCollection.remove(o);
						clown.addToStack("lStack", (Shape) o);
					}
					int x;
					if (clown.getTopLeft().getX() == clown.getTopRight().getX()) {
						x = 100;
					} else {
						x = 0;
					}
					if (intersect(clown.getTopRight(), o, x)) {
						o.setX(clown.getTopRight().getX() + x);
						controlable.add(o);
						shapesCollection.remove(o);
						clown.addToStack("rStack", (Shape) o);
					}
					/*
					 * for (int i = 0; i < clown.getLeftStack().size(); i++) { Shape p =
					 * clown.getLeftStack().get(i); if (i == 0) { p.setY(clown.getY() -
					 * p.getHeight()); } else { p.setY(clown.getLeftStack().get(i - 1).getY() -
					 * p.getHeight()); } } for (int i = 0; i < clown.getRightStack().size(); i++) {
					 * Shape p = clown.getRightStack().get(i); if (i == 0) { p.setY(clown.getY() -
					 * p.getHeight()); } else { p.setY(clown.getRightStack().get(i - 1).getY() -
					 * p.getHeight()); } }
					 */
					int before = controlable.size();
					controlable.clear();
					controlable.add(clown);
					for (int i = 0; i < clown.getLeftStack().size(); i++) {
						clown.getLeftStack().get(i).setX(clown.getX());
						controlable.add(clown.getLeftStack().get(i));
					}
					for (int i = 0; i < clown.getRightStack().size(); i++) {
						clown.getRightStack().get(i).setX(clown.getX() + 100);
						controlable.add(clown.getRightStack().get(i));
					}
					score += (before - controlable.size()) / 3;
					if (o.getY() == height) {
						shapesPool.release(o);
					}
					iterator.next();
				}
			}
		}
		return true;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String getStatus() {
		return "Score : " + score;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.maxCount = speed;
	}

	@Override
	public int getControlSpeed() {
		return controlSpeed;
	}

	@Override
	public void setState(CircusOfPlates state) {
		this.state = state;
	}

	@Override
	public Memento save() {
		return new Memento(state);
	}

	@Override
	public void restore(Memento m) {
		state = m.getState().clone();
		constant = state.getConstantObjects();
		movable = state.getMovableObjects();
		controlable = state.getControlableObjects();
		shapesCollection.clear();
		for (GameObject o : movable) {
			shapesCollection.add((Shape) o);
		}
		speed = state.getSpeed();
		score = state.getScore();
		clown = state.clown;
	}

	@Override
	public CircusOfPlates clone() {
		log.info("Cloning circus of plates");
		CircusOfPlates circus = new CircusOfPlates(width, height);
		circus.clown = clown.clone();
		circus.score = score;
		circus.speed = speed;
		circus.shapesPool = shapesPool;
		for (GameObject obj : movable) {
			Shape shape = (Shape) obj;
			circus.movable.add(shape.clone());
		}
		for (GameObject obj : controlable) {
			if (!obj.getClass().isInstance(clown)) {
				circus.controlable.add(obj);
			}
		}
		circus.controlable.add(clown);
		return circus;
	}

}
