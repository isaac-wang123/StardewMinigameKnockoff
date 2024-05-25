package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Alien extends Entity{
	GamePanel gp;
	
	public Alien(GamePanel gp, int x, int y) {
		this.x = x;
		this.y = y;
		this.gp = gp;
		loadImage();
	}
	
	public void loadImage() {
		try {
			alienImage = ImageIO.read(getClass().getResourceAsStream("/enemies/alien.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(alienImage, x, y, gp.tileSize,gp.tileSize, null);
	}
}
