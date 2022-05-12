package eg.edu.alexu.csd.oop.game.myGame.controller.startegy;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.myGame.model.CircusOfPlates;

public class Hard implements Difficulty {
	private static final Logger log = Logger.getLogger(Hard.class);

	CircusOfPlates world;
	
	public Hard(CircusOfPlates world) {
		log.info("Initiate hard strategy");
		this.world = world;
		setDifficulty();
	}

	@Override
	public void setDifficulty() {
		world.setSpeed(4);
	}
}