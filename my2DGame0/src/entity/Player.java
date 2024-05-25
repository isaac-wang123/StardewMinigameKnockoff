package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		
		solidArea = new Rectangle();
		solidArea.x = 9;
		solidArea.y = 6;
		solidArea.height = 36;
		solidArea.width = 30;
		setDefaultValues();
		getPlayerImage();
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
		
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		if(keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}

		if(keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}			
		if(keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
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
