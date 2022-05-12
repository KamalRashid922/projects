package eg.edu.alexu.csd.oop.game.myGame.controller.memento;

import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;

public class Memento {
	private CircusOfPlates state;
	
	public Memento(CircusOfPlates state) {
		this.state = state;
	}
	
	public CircusOfPlates getState() {
		return state;
	}
}
