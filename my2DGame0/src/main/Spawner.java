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
	Tile[][] tiles;
	ArrayList<Tile> spawnTiles;
	int[][] spawns;
	int spawnInterval;
	int maxRows;
	
	public Spawner(GamePanel gp, Tile[][] tiles) {
		this.gp = gp;
		this.tiles = tiles;
		time = 0;
		spawnInterval = 60;
		maxRows = 120;
		spawnTiles = new ArrayList<Tile>();
		loadSpawnLocations();
		spawns = new int[maxRows][spawnTiles.size()];
		loadSpawnPattern();
		for(int[] j : spawns) {
			for(int i : j) {
				System.out.print(i);
			}
			System.out.println("");
		}
	}
	
	public void loadSpawnLocations() {
		for(Tile[] t : tiles) {
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
	
	public void update(AlienManager am) {
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

					am.add(new Alien(gp, tileX, tileY));
				}
			}

		}
		time ++;
		
	}
}
