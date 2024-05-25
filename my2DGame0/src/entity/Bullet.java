package entity;

import static main.CollisionChecker.checkTile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bullet extends Entity{
	
	KeyHandler keyH;
	GamePanel gp;
	public boolean selfDestruct = false;
	
	public Bullet(GamePanel gp, KeyHandler keyH, int x, int y) {
		
		System.out.println("y!");
		this.gp = gp;
		this.keyH = keyH;
		
		getBulletImage();
		
		setValues(x,y);
		initHitbox(21,21,6,6);
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
		
		
		if(keyH.upArrowPressed==true) {
			vy = -speed;
		} else if(keyH.downArrowPressed==true) {
			vy = speed;
		}
		
		if (keyH.rightArrowPressed==true) {
			vx = speed;
		} else if(keyH.leftArrowPressed==true) {
			vx = -speed;
		}
		
		if(vx != 0 && vy != 0) {
			vx = vx * 2 / 3;
			vy = vy * 2 / 3;
		}
	}
	
	public void update() {
		if(!checkTile(gp, hitbox.x + vx, hitbox.y + vy, hitbox.width, hitbox.height)) {
			selfDestruct = true;
		} else {
			x += vx;
			y += vy;
		}
		updateHitbox();
		

		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = bulletImage;
		
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
}
