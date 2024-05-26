package entity;

import static main.CollisionChecker.checkTile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Alien extends Entity{
	GamePanel gp;
	public boolean dead = false;
	int probability;
	int randomCounterValue;
	int randomCooldownValue;
	int randomCounter = 0;
	int randomCooldown = 0;
	int vxRandom;
	int vyRandom;
	double angle;
	int protectedVx;
	int protectedVy;
	int protectedTime;
	int protectedCountdown;
	
	public Alien(GamePanel gp, int x, int y) {
		this.x = x;
		this.y = y;
		this.gp = gp;
		loadImage();
		initHitbox(9,0,33,45);
		randomCounterValue = 60;
		randomCooldownValue = 240;
		speed = 2;
		probability = 600;
		protectedTime = 90;
		protectedCountdown = protectedTime;
		if(x <= 0) {
			protectedVx = speed;
			protectedVy = 0;
		}
		if(x >= (gp.maxScreenCol - 1) * gp.tileSize) {
			protectedVx = -speed;
			protectedVy = 0;
		}
		if(y <= 0) {
			protectedVx = 0;
			protectedVy = speed;
		}
		if(y >= (gp.maxScreenRow - 1) * gp.tileSize) {
			protectedVx = 0;
			protectedVy = -speed;
		}
	}
	
	
	public void loadImage() {
		try {
			alienImage = ImageIO.read(getClass().getResourceAsStream("/enemies/alien.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Player player, ArrayList<Alien> aliens) {
		if(protectedCountdown > 0) {
			vx = protectedVx;
			vy = protectedVy;
			protectedCountdown--;
		} else {
			if(randomCooldown>0) {
				randomCooldown--;
			}
			if(randomCounter>0) {
				randomCounter--;
				vx = vxRandom;
				vy = vyRandom;
			} else {
				int angle = findDirection(player);
				setVelocity(angle);
			}
			if(randomCooldown==0) {
				if(gamble()) {
					int randomNum = (int) (Math.random()*4);
					switch(randomNum) {
						case 0:
							vxRandom = 0;
							vyRandom = speed;
							break;
						case 1:
							vxRandom = speed;
							vyRandom = 0;
							break;
	
						case 2:
							vxRandom = -speed;
							vyRandom = 0;
							break;
	
						case 3:
							vxRandom = 0;
							vyRandom = -speed;
							break;
	
					}
					vx = vxRandom;
					vy = vyRandom;
				}
			}
		}
		if(!checkTile(gp, hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, true)) {
			vy = vx;
			vx = 0;
		}
		if(!checkTile(gp, hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, true)) {
			vy = vx;
			vy = 0;
		}
		if(!checkCollision(hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, aliens)){
			vx = 0;
		} 
		if(!checkCollision(hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, aliens)){
			vy = 0;
		} 
		
		x += vx;
		y += vy;
		
		updateHitbox();
	}
	
	public void setVelocity(int angle) {
		switch(angle) {
		  case 0:
		    vx = speed;
		    vy =0;
		    break;
		  case 45:
			vx = speed * 2 / 3;
			vy = -speed * 2 / 3;
		    break;
		  case 90:
			vx = 0;
			vy = -speed;
		    break;
		  case 135:
			vx = -speed * 2 / 3;
			vy = -speed * 2 / 3;
		    break;
		  case 180:
			vx = -speed;
			vy = 0;
		    break;
		  case 225:
			vx = -speed * 2 / 3;
			vy = speed * 2 / 3;
		    break;
		  case 270:
			vx = 0;
			vy = speed;
		    break;
		  case 315:
			vx = speed * 2 / 3;
			vy = speed * 2 / 3;
		    break;
		}
	}
	
	public int findDirection(Player player) {
		double xDistance = (double) x - player.x;
		double yDistance = (double) - y + player.y;
		if(xDistance == 0) {
			xDistance = 1;
		}
		angle = Math.atan(yDistance/xDistance) * 180 / Math.PI;
		if(xDistance > 0) {
			angle = 180 + angle;
		} else if(yDistance > 0) {
			angle  = angle + 360;
		}
		angle += 22.5;
		angle = (int) angle /45 * 45;
		if(angle == 360) {
			angle = 0;
		}
		
		return (int) angle;
	}
	
	public boolean gamble() {
		if((int) (Math.random()*probability) == 0) {
			randomCounter = randomCounterValue; 
			randomCooldown = randomCounterValue + randomCooldownValue;
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(alienImage, x, y, gp.tileSize,gp.tileSize, null);
	}
	
	public String toString() {
		return ("(" + x + ", " + y + ")");
	}
}
