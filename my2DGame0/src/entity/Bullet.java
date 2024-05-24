package entity;

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
			bullet = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setValues(int x, int y) {
		speed = 5;
		
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
			vx = speed;
		}
	}
	
	public void update() {
		x += vx;
		y += vy;
		
		if(x < 0 || x > GamePanel.screenWidth||y < 0 || y > screenHeight) {
			selfDestruct = true;
		}
			
	}
}
