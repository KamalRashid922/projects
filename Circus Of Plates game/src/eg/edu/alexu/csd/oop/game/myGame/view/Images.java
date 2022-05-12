package eg.edu.alexu.csd.oop.game.myGame.view;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class Images {
	private static final Logger log = Logger.getLogger(Images.class);
	private BufferedImage redPlate;
	private BufferedImage bluePlate;
	private BufferedImage greenPlate;
	private BufferedImage redBall;
	private BufferedImage blueBall;
	private BufferedImage greenBall;
	private static Images unique = new Images();
	
	private Images() {
	}
	
	public static Images getInstance(){
		unique.loadImages();
		return unique;
	}
	
	public void loadImages() {
		try {
			log.info("Loading Images");
			bluePlate=ImageIO.read(this.getClass().getResourceAsStream("/images1.png"));
			redPlate=ImageIO.read(this.getClass().getResourceAsStream("/images2.png"));
			greenPlate=ImageIO.read(this.getClass().getResourceAsStream("/images3.png"));
			blueBall=ImageIO.read(this.getClass().getResourceAsStream("/blueBall.png"));
			redBall=ImageIO.read(this.getClass().getResourceAsStream("/redBall.png"));
			greenBall=ImageIO.read(this.getClass().getResourceAsStream("/greenBall.png"));
		}catch(Exception e) {
			log.info("Failed to load images from files");
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(String type) {
		if(type.equals("redBall")) {
			return redBall;
		}
		if(type.equals("blueBall")) {
			return blueBall;
		}
		if(type.equals("greenBall")) {
			return greenBall;
		}
		if(type.equals("redPlate")) {
			return redPlate;
		}
		if(type.equals("bluePlate")) {
			return bluePlate;
		}
		if(type.equals("greenPlate")) {
			return greenPlate;
		}
		return null;
	}
}
