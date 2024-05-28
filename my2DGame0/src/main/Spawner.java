package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import entity.Alien;
import tile.Tile;

public class Spawner {
	GamePanel gp;
	int time;
	ArrayList<Tile> spawnTiles;
	int[][] spawns;
	int spawnInterval;
	int maxRows;
	boolean infinite = true;
	int multiplier;
	int waveCooldown;
	int cooldownCounter;
	int wave;
	int spawnMax;
	int spawned;
	int probability;
	
	public Spawner(GamePanel gp) {
		this.gp = gp;
		time = 0;
		spawnTiles = new ArrayList<Tile>();
		loadSpawnLocations();
		maxRows = 120;
		spawns = new int[maxRows][spawnTiles.size()];
		System.out.println(spawns.length);
		spawnInterval = 60;

		if(infinite) {
			wave = 0;
			waveCooldown = 240;
			cooldownCounter = 0;
			spawned = 0;
			spawnMax = 0;
		} else {
			loadSpawnPattern();
		}
	
	}
	
	public void loadSpawnLocations() {
		for(Tile[] t : gp.tileManager.tiles) {
			for(Tile tile : t) {
				if(tile.spawn) {
					spawnTiles.add(tile);
				}
			}
		}
	}
	
	public void loadSpawnPattern() {
		try {
			InputStream is = getClass().getResourceAsStream("/spawnMap/spawnMap01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < spawnTiles.size() && row < maxRows) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				String numbers[] = line.split(" ");
				while(col < spawnTiles.size()) {
					int num = Integer.parseInt(numbers[col]);
					spawns[row][col] = num;
					col++;
				}
				if(col == spawnTiles.size()) {
					col = 0;
					row++;
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(infinite) {
			updateInfinite();
		} else {
			updateFromMap();
		}
	}
	
	public void updateInfinite() {
		if(spawned >= spawnMax) {
			wave++;
			spawned = 0;
			spawnMax = wave * 3;
			probability = 10 - wave;
			if(probability < 1) {
				probability = 1;
			}
			if(wave > 1) {
				cooldownCounter = waveCooldown;
			}
		}
	
		if(cooldownCounter == 0) {
			if(time % spawnInterval == 0) {
				System.out.println("spawn");
				for(Tile tile : spawnTiles) {
					if((int)(Math.random() * probability) == 0) {
						int tileX = tile.x;
						int tileY = tile.y;
						if(tileY == 0) {
							tileY -= gp.tileSize;
						} 
						if(tileY == (gp.maxScreenRow - 1) * gp.tileSize) {
							tileY += gp.tileSize;
						}
						if(tileX == 0) {
							tileX -= gp.tileSize;
						} 
						if(tileX == (gp.maxScreenCol - 1) * gp.tileSize) {
							tileX += gp.tileSize;
						}
						
						gp.alienManager.aliens.add(new Alien(gp, tileX, tileY));
						spawned++;
					}
				}
			}
		} else {
			if(cooldownCounter > 0) {
				cooldownCounter--;
			}
		}
		time++;
	}
	public void updateFromMap() {
		if(time % spawnInterval == 0 && time < spawnInterval * maxRows) {
			int[] currentRow = spawns[time / spawnInterval];
			for(int i = 0; i < currentRow.length; i++) {
				switch(currentRow[i]) {
				case 0:
					break;
				case 1:
					int tileX = spawnTiles.get(i).x;
					int tileY = spawnTiles.get(i).y;
					if(tileY == 0) {
						tileY -= gp.tileSize;
					} 
					if(tileY == (gp.maxScreenRow - 1) * gp.tileSize) {
						tileY += gp.tileSize;
					}
					if(tileX == 0) {
						tileX -= gp.tileSize;
					} 
					if(tileX == (gp.maxScreenCol - 1) * gp.tileSize) {
						tileX += gp.tileSize;
					}

					gp.alienManager.aliens.add(new Alien(gp, tileX, tileY));
				}
			}

		}
		time ++;
	}
	
	public void reset() {
		time = 0;
	}
}
