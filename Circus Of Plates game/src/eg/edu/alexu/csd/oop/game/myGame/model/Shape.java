package eg.edu.alexu.csd.oop.game.myGame.model;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.myGame.model.shapes.State;

public abstract class Shape implements GameObject, Cloneable {
	private int x;
	private int y;
	private boolean visible;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int type;
	private State state=null;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;

	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		if(state==null) {
			this.y=0;
		}
		else if (state.setY(y) != -1) {
			this.y = state.setY(y);
		}
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	protected void setSpriteImages(BufferedImage[] spriteImages) {
		this.spriteImages = spriteImages;
	}

	public int getType() {
		return type;
	}

	public Shape clone() {
		return null;
	}

	public void setState(State state) {
		this.state = state;
	}

}
