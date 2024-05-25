package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bullet extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public boolean selfDestruct = false;
	
	public Bullet(GamePanel gp, KeyHandler keyH, int x, int y) {

		this.gp = gp;
		this.keyH = keyH;
		
		getBulletImage();
		
		setValues(x,y);
		
	}
	
	public void getBulletImage() {
		try {
			
			bulletImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bulletv3.png"));
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void setValues(int x, int y) {
		
		speed = 10;
		
		this.x = x;
		this.y = y;
		
		int count = 0;
		
		if(keyH.upArrowPressed==true) {
			count ++;
			vy = -speed;
		} else if(keyH.downArrowPressed==true) {
			count++;
			vy = speed;
		}
		
		if (keyH.rightArrowPressed==true) {
			count++;
			vx = speed;
		} else if(keyH.leftArrowPressed==true) {
			count++;
			vx = -speed;
		}
		
		if(count>1) {
			vx/=2;
			vy/=2;
		}
	}
	
	public void update() {
		
		x += vx;
		y += vy;
		
		if(x < -gp.tileSize || x > gp.screenWidth||y < -gp.tileSize || y > gp.screenHeight) {
			selfDestruct = true;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = bulletImage;
		
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
}
