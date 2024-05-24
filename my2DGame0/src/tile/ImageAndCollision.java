package tile;

import java.awt.image.BufferedImage;

public class ImageAndCollision {
	public BufferedImage image;
	public boolean collision;
	
	public ImageAndCollision(BufferedImage image, boolean collision) {
		this.image = image;
		this.collision = collision;
	}
}
