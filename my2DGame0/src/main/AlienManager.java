package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Alien;
import entity.Player;

public class AlienManager {
	GamePanel gp;
	Player player;
	Alien alien;
	public ArrayList<Alien> aliens;
	
	public AlienManager(GamePanel gp) {
		this.gp = gp;
		aliens = new ArrayList<Alien>();
	}
	
	public void updateStatus() {
		for(int i= 0; i < aliens.size(); i++) {
			if(aliens.get(i).dead) {
				aliens.remove(i);
				i--;
			}
		}
	}
	
	public void update(Player player) {
		this.player = player;
		jankSort(aliens);
		for(Alien alien : aliens) {
			alien.update(player, aliens);
		}
	}

	
	public void draw(Graphics2D g2) {
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
				int distance = (int) Math.sqrt(Math.pow((player.hitbox.x + player.hitbox.width -aliens.get(j).hitbox.x - aliens.get(j).hitbox.width), 2) + Math.pow((player.hitbox.y + player.hitbox.height - aliens.get(j).hitbox.y - aliens.get(j).hitbox.width), 2));
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
	
	public void add(Alien alien) {
		aliens.add(alien);
	}
}
