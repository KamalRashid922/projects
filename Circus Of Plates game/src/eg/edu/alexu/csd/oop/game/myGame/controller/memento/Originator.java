package eg.edu.alexu.csd.oop.game.myGame.controller.memento;

import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;

public interface Originator {
	public void setState(CircusOfPlates state);
	public Memento save();
	public void restore(Memento m);
}
