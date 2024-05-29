package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class AlienDeath {
	public int x;
	public int y;
	GamePanel gp;
	public int count;
	BufferedImage[] images;
	public int imageIndex;
	public int frameRate;
	public boolean destroy;
	
	public AlienDeath(int x, int y, GamePanel gp) {
		frameRate = 3;
		this.gp = gp;
		this.x = x;
		this.y = y;
		count = 0;
		images = new BufferedImage[8];
		loadImage();
	}
	
	public void loadImage() {
		try {
			images[0] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-0.png"));
			images[1] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-1.png"));
			images[2] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-2.png"));
			images[3] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-3.png"));
			images[4] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-4.png"));
			images[5] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-5.png"));
			images[6] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-6.png"));
			images[7] = ImageIO.read(getClass().getResourceAsStream("/alienDeath/pixil-frame-7.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		if(count % frameRate == 0 && count >0) {
			imageIndex++;
		}
		if(imageIndex == 8) {
			destroy = true;
			return;
		}
		
		g2.drawImage(images[imageIndex], x, y, gp.tileSize, gp.tileSize, null);		
		count++;
	}
}
