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
	
	public Tile() {
		
	}
	public Tile(GamePanel gp, int x, int y, BufferedImage image, boolean collision) {
		this.x = x;
		this.y = y;
		this.gp = gp;
		tileSize = gp.tileSize;
		this.image = image;
		this.collision = collision;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + collision + ")";
	}
}
