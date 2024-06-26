package entity;

import static main.CollisionChecker.checkTile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bullet extends Entity{
	
	GamePanel gp;
	public boolean selfDestruct = false;

	
	public Bullet(GamePanel gp, int x, int y) {
		this.gp = gp;
		
		getBulletImage();
		
		setValues(x,y);
		initHitbox(21,21,6,6);
	}
	
	public void getBulletImage() {
		try {
			bulletImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bulletv5.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setValues(int x, int y) {
		
		speed = 10;
		
		this.x = x;
		this.y = y;
		
		if(gp.keyH.upArrowPressed==true) {
			vy = -speed;
		} else if(gp.keyH.downArrowPressed==true) {
			vy = speed;
		}
		
		if(gp.keyH.rightArrowPressed==true) {
			vx = speed;
		} else if(gp.keyH.leftArrowPressed==true) {
			vx = -speed;
		}
		
		if(vx != 0 && vy != 0) {
			vx = vx * 2 / 3;
			vy = vy * 2 / 3;
		}
	}
	

	public void update(ArrayList<Alien> aliens) {
		x += vx;
		y += vy;
		updateHitbox();

		for(Alien alien : aliens) {
			if(alien.hitbox.contains(hitbox.x,hitbox.y)||alien.hitbox.contains(hitbox.x+hitbox.width,hitbox.y)||alien.hitbox.contains(hitbox.x,hitbox.y+hitbox.height)||alien.hitbox.contains(hitbox.x+hitbox.width,hitbox.y+hitbox.height)) {
				selfDestruct = true;
				alien.dead = true;
				return;
			}
		}
		
		if(!checkTile(gp, hitbox.x, hitbox.y, hitbox.width, hitbox.height, false)) {
			selfDestruct = true;
			return;
		}

	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = bulletImage;
		
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
}
