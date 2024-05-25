package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import tile.Tile;

public class Entity {
	 public int x, y;
	 public int speed;
	 public int vx, vy;
	 public int xOffset, yOffset;
	 public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2, bulletImage, alienImage;
	 public String direction;
	 public boolean left, right, up, down;
	 public int spriteCounter = 0;
	 public int spriteNum = 1;
	 public Rectangle hitbox;
	 public boolean collisionOn = false;
	 public Tile[][] tiles;
	 
	 public void initHitbox(int xOffset, int yOffset, int width, int height) {
		 this.xOffset = xOffset;
		 this.yOffset = yOffset;
		 hitbox = new Rectangle(x + xOffset, y + yOffset, width, height);
	 }
	 
	 public void updateHitbox() {
		 hitbox.x = x + xOffset;
		 hitbox.y = y + yOffset;
	 }
	 
	 public void drawHitbox(Graphics2D g2) {
		 g2.setColor(Color.red);
		 g2.drawRect(hitbox.x,hitbox.y,hitbox.width ,hitbox.height);
	 }

}
