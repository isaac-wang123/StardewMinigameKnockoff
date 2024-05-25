package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Bullet;
import entity.Player;

public class BulletManager {
	ArrayList<Bullet> bullets;
	int counter;
	int fireInterval;
	
	KeyHandler keyH;
	GamePanel gp;
	Player player;
	
	
	public BulletManager(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		bullets = new ArrayList<Bullet>();
		
		fireInterval = 15;
		counter = 0;
	}
	
	public void update(Player player) {
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			if(bullets.get(i).selfDestruct == true) {
				bullets.remove(i);
				i--;
			}
		}
		
		if(keyH.downArrowPressed==true||keyH.upArrowPressed==true||keyH.leftArrowPressed==true||keyH.rightArrowPressed==true){
			if(counter == 0) {			
				bullets.add(new Bullet(gp, keyH, player.x, player.y));
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
