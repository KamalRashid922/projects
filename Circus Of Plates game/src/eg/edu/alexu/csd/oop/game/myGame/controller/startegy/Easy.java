package eg.edu.alexu.csd.oop.game.myGame.controller.startegy;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;
public class Easy implements Difficulty {
	private static final Logger log = Logger.getLogger(Easy.class);

	CircusOfPlates world;

	public Easy(CircusOfPlates world) {
		log.info("Initiating easy strategy");
		this.world = world;
		setDifficulty();
	}

	@Override
	public void setDifficulty() {
		world.setSpeed(1);
	}
}
