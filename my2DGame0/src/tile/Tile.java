package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Tile {
	int x, y;
	GamePanel gp;
	int tileSize;
	
	public BufferedImage image;
	
	public boolean collision = false;

	public Tile(GamePanel gp, int x, int y, BufferedImage image) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		tileSize = gp.tileSize;
		this.image = image;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
}
