package entity;

import java.awt.Graphics2D;
import static main.CollisionChecker.checkTile;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		this.tiles = gp.tiles;

		setDefaultValues();
		getPlayerImage();
		initHitbox(9,3,29,36);
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 3;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up0 = ImageIO.read(getClass().getResourceAsStream("/player/back-1.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/back0.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
			down0 = ImageIO.read(getClass().getResourceAsStream("/player/front-1.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/front0.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/front1.png"));
			left0 = ImageIO.read(getClass().getResourceAsStream("/player/left-1.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			right0 = ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
		if(keyH.upPressed != true && keyH.downPressed!= true && keyH.leftPressed != true && keyH.rightPressed != true) {
			return;
		}
		
		int vx = 0, vy = 0;
		int count = 0;
		if(keyH.upPressed == true) {
			count++;
			direction = "up";
			if(checkTile(gp, hitbox.x, hitbox.y - speed, hitbox.width, hitbox.height)) {
				vy -= speed;
			}
		}
		if(keyH.downPressed == true) {
			count++;
			direction = "down";
			if(checkTile(gp, hitbox.x, hitbox.y + speed, hitbox.width, hitbox.height)) {
				vy += speed;
			}
		}

		if(keyH.leftPressed == true) {
			count++;
			direction = "left";
			if(checkTile(gp, hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height)) {
				vx -= speed;
			}
		}			
		if(keyH.rightPressed == true) {
			count++;
			direction = "right";
			if(checkTile(gp, hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height)) {
				vx += speed;
			}
		}
		
		if(count>1) {
			vx = vx * 2 / 3;
			vy = vy * 2 / 3;
		}
		
		x += vx;
		y += vy;
		
		updateHitbox();
				
		spriteCounter++;
		if(spriteCounter>12) {
			if(spriteNum ==1) {
				spriteNum = 2;
			}
			else if(spriteNum ==2) {
				spriteNum = 1;
			}
			spriteCounter =0;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true||keyH.rightPressed == true) {
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum==2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum==2) {
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum==2) {
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum==2) {
					image = right2;
				}
				break;
			}
		} else {
			switch(direction) {
			case "up":
				image = up0;	
				break;
			case "down":
				image = down0;
				break;
			case "left":
				image = left0;
				break;
			case "right":
				image = right0;
				break;
			}
		}
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);

	}
}
