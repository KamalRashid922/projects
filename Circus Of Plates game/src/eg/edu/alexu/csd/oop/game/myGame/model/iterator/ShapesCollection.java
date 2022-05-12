package eg.edu.alexu.csd.oop.game.myGame.model.iterator;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.myGame.model.Shape;

public class ShapesCollection implements Iterable<GameObject> {
	private List<GameObject> list;
	public ShapesCollection() {
		list = new ArrayList<GameObject>();
	}
	
	public void add(Shape newPlate) {
		list.add(newPlate);
	}
	
	public void remove(Shape plate) {
		list.remove(plate);
	}
	
	public List<GameObject> getList() {
		return list;
	}
	
	public void clear() {
		list.clear();
	}

	@Override
	public Iterator<GameObject> createIterator() {
		return new ShapesIterator(this);
	}

}
