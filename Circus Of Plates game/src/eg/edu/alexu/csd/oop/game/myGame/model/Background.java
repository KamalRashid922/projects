package eg.edu.alexu.csd.oop.game.myGame.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Background implements GameObject{

	private int x;
	private int y;
	private boolean visible;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

	public Background(String imagePath) {
		setX(0);
		setY(0);
		this.visible = true;
		try {
			spriteImages[0] = ImageIO.read(this.getClass().getResourceAsStream("/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x=x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y=y;
	}

	@Override
	public int getWidth() {
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

}
