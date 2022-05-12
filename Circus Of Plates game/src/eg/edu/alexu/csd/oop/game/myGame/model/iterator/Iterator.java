package eg.edu.alexu.csd.oop.game.myGame.model.iterator;

public interface Iterator<T> {
	void first();
	void next();
	boolean isDone();
	T currentItem();
}
