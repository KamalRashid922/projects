package eg.edu.alexu.csd.oop.game.myGame.controller.startegy;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;

public class Moderate implements Difficulty {
	private static final Logger log = Logger.getLogger(Moderate.class);

	CircusOfPlates world;

	public Moderate(CircusOfPlates world) {
		log.info("Initiate Moderate strategy");
		this.world = world;
		setDifficulty();
	}

	@Override
	public void setDifficulty() {
		world.setSpeed(2);
	}
}