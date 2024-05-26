package tile;

import java.awt.image.BufferedImage;

public class TileData {
	public BufferedImage image;
	public boolean collision;
	public boolean spawn;
	
	public TileData(BufferedImage image, boolean collision, boolean spawn) {
		this.image = image;
		this.collision = collision;
		this.spawn = spawn;
	}
}
