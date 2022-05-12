package eg.edu.alexu.csd.oop.game.myGame.model;

import java.util.Stack;
import org.apache.log4j.Logger;

public class PlateObserver implements Observable {

	private Clown clown;
	private Stack<Shape> rStack;
	private Stack<Shape> lStack;
	private static final Logger log = Logger.getLogger(PlateObserver.class);
	
	@Override
	public void update(Subject clown) {
		this.clown = (Clown) clown;
		rStack = this.clown.getRightStack();
		lStack = this.clown.getLeftStack();
		if(lStack.size() > 2) {
			log.info("updating left stack");
			if((lStack.get(lStack.size() - 1).getType() == lStack.get(lStack.size() - 2).getType())
					&& (lStack.get(lStack.size() - 2).getType() == lStack.get(lStack.size() - 3).getType())) {
				lStack.pop();
				lStack.pop();
				lStack.pop();
			}
		}
		if(rStack.size() > 2) {
			log.info("updating right stack");
			if((rStack.get(rStack.size()-1).getType() == rStack.get(rStack.size() - 2).getType())
					&& (rStack.get(rStack.size()-2).getType() == rStack.get(rStack.size()-3).getType())) {
				rStack.pop();
				rStack.pop();
				rStack.pop();
			}
		}
	}

}
