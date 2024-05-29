package entity;

import static main.CollisionChecker.checkTile;
import static main.CollisionChecker.radiusChecker;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Alien extends Entity{
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
	double xDistance;
	double yDistance;
	
	public Alien(GamePanel gp, int x, int y) {
		this.x = x;
		this.y = y;
		super.setGp(gp);
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
	
	public void update() {
		boolean checked = false;

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
				
				if(!checkTile(gp, hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, true)||!checkTile(gp, hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, true)) {
					vx = 0;
					vy = 0;
				}
				
			} else {
				if(radiusChecker(gp, hitbox)){
					int playerCol = (gp.player.hitbox.x) / gp.tileSize;
					int playerRow = (gp.player.hitbox.y) / gp.tileSize; 
					if(x > 0 && y > 0) {
						searchPath(playerRow, playerCol);
					}
				} else {
					int angle = findDirection();
					setVelocity(angle);
					
					if(!checkTile(gp, hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, true)) {
						vy = 0;
					}
					
					if(!checkTile(gp, hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, true)) {
						vx = 0;
					}
					checked = true;
				}
			}
		}
	
		if(!checkCollision(hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, gp.alienManager.aliens)){
			vx = 0;
		} 
		if(!checkCollision(hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, gp.alienManager.aliens)){
			vy = 0;
		} 
		
		if(vx == 0 && vy == 0 && !checked) {
			int angle = findDirection();
			setVelocity(angle);
			if(!checkTile(gp, hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, true)) {
				vy = 0;
			}
			if(!checkTile(gp, hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, true)) {
				vx = 0;
			}
		}
		
		if(!checkCollision(hitbox.x + vx, hitbox.y, hitbox.width, hitbox.height, gp.alienManager.aliens)){
			vx = 0;
		} 
		if(!checkCollision(hitbox.x, hitbox.y + vy, hitbox.width, hitbox.height, gp.alienManager.aliens)){
			vy = 0;
		} 
		
		x += vx;
		y += vy;
		updateHitbox();
		
		if(intersecting(hitbox, gp.player.hitbox)){
			gp.gameState = gp.deathState;
		}
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
	
	public int findDirection() {
		xDistance = (double) x - gp.player.x;
		yDistance = (double) - y + gp.player.y;
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
		angle = (int) angle / 45 * 45;
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
