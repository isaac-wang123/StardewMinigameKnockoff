package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	 public int x, y;
	 public int speed;
	 public int vx, vy;
	 public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2, bulletImage;
	 public String direction;
	 public boolean left, right, up, down;
	 public int spriteCounter = 0;
	 public int spriteNum = 1;
	 public Rectangle solidArea;
	 public boolean collisionOn = false;
}
