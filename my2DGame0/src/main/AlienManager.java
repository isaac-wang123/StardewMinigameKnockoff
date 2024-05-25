package main;

import java.awt.Graphics2D;

import entity.Alien;

public class AlienManager {
	GamePanel gp;
	Alien alien;
	public AlienManager(GamePanel gp) {
		this.gp = gp;
		alien = new Alien (gp, 200,200);
	}
	
	public void draw(Graphics2D g2) {
		alien.draw(g2);
	}
}
