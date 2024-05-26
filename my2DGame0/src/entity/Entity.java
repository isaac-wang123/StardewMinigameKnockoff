package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	 
	 public void bounce(Alien alien1, Alien alien2) {
		 
	 }
	 
	 public boolean checkCollision(int x, int y, int width, int height, ArrayList<Alien> entities) {
		 for(Alien entity : entities) {
			 if(entity == this) {
				 continue;
			 }
			 if(intersecting(entity.hitbox, new Rectangle(x, y, width, height))) {
				 return false;
			 }

		 }
		 return true;
	 }
	 
	 public boolean intersecting (Rectangle r1, Rectangle r2) {
		 return (inside(r1, r2.x, r2.y) || inside(r1, r2.x + r2.width, r2.y) || inside(r1, r2.x, r2.y + r2.height) || inside(r1, r2.x + r2.width, r2.y + r2.height));
	 }
	 
	 public boolean inside(Rectangle r, int x, int y) {
		 return(x >= r.x && x <= r.x + r.width && y >= r.y && y <= r.y + r.height);
	 }
}
