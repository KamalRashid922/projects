package eg.edu.alexu.csd.oop.game.myGame.model;

public interface Subject {
	public void addObserver(Observable obs);
	public void removeObserver(Observable obs);
	public void notifyObservers();
}

