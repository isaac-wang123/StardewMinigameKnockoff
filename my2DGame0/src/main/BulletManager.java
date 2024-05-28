package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Alien;
import entity.Bullet;
import entity.Entity;
import entity.Player;

public class BulletManager {
	public ArrayList<Bullet> bullets;
	int counter;
	int fireInterval;
	
	GamePanel gp;
	
	
	public BulletManager(GamePanel gp) {
		this.gp = gp;
		bullets = new ArrayList<Bullet>();
		fireInterval = 20;
		counter = 0;
	}
	
	public void update() {
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(gp.alienManager.aliens);
			if(bullets.get(i).selfDestruct == true) {
				bullets.remove(i);
				i--;
			}
		}
		
		if(gp.keyH.downArrowPressed==true||gp.keyH.upArrowPressed==true||gp.keyH.leftArrowPressed==true||gp.keyH.rightArrowPressed==true){
			if(counter == 0) {			
				bullets.add(new Bullet(gp, gp.player.x, gp.player.y));
				counter += fireInterval + 1;
			}
		}	
		
		if(counter > 0){
			counter--;
		}

	}
	
	public void draw(Graphics2D g2) {
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g2);
		}
	}
	
}
