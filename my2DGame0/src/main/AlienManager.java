package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Alien;
import entity.AlienDeath;

public class AlienManager {
	GamePanel gp;
	Alien alien;
	public ArrayList<Alien> aliens;
	public ArrayList<AlienDeath> ads;
	
	public AlienManager(GamePanel gp) {
		this.gp = gp;
		aliens = new ArrayList<Alien>();
		ads = new ArrayList<AlienDeath>();

	}
	
	public void updateStatus() {
		for(int i= 0; i < aliens.size(); i++) {
			if(aliens.get(i).dead) {
				ads.add(new AlienDeath(aliens.get(i).x, aliens.get(i).y, gp));
				aliens.remove(i);
				i--;
			}
		}

	}
	
	public void update() {
		jankSort(aliens);
		for(Alien alien : aliens) {
			alien.update();
		}
		for(int i= 0; i < ads.size(); i++) {
			if(ads.get(i).destroy) {
				ads.remove(i);
				i--;
			}
		}
	}

	
	public void draw(Graphics2D g2) {
		for(AlienDeath ad : ads) {
			ad.draw(g2);
		}
		for(Alien alien : aliens) {
			alien.draw(g2);
		}

	}
	

	public void jankSort(ArrayList<Alien> aliens){
		int size = aliens.size();
		ArrayList<Alien> copy = new ArrayList<Alien>();

		for(int i = 0; i < size; i++){
			int min = 0;
			int minIndex = 0;
			
			for(int j = 0; j < aliens.size(); j++) {
				int distance = (int) Math.sqrt(Math.pow((gp.player.hitbox.x + gp.player.hitbox.width -aliens.get(j).hitbox.x - aliens.get(j).hitbox.width), 2) + Math.pow((gp.player.hitbox.y + gp.player.hitbox.height - aliens.get(j).hitbox.y - aliens.get(j).hitbox.width), 2));
				if(min ==0) {
					min = distance;
					minIndex = j;
				} else if(distance < min){
					min = distance;
					minIndex = j;
				}
			}
			
			copy.add(aliens.get(minIndex));
			aliens.remove(minIndex);
		}
		this.aliens = copy;
	}
	
	public void reset() {
		aliens.clear();
		ads.clear();
	}
}
