package main;

import java.awt.Rectangle;

import tile.Tile;

public class CollisionChecker {
	
	public static boolean checkTile(GamePanel gp, int x, int y, int width, int height, Tile[][] tiles) {	
		if(!isSolid(x, y, tiles, gp)) {
			if(!isSolid(x + width, y, tiles, gp)) {
				if(!isSolid(x, y + height, tiles, gp)) {
					if(!isSolid(x + width, y + height, tiles, gp)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	private static boolean isSolid(int x, int y, Tile[][] tiles, GamePanel gp) {
		
		if(x < 0 || x >= gp.screenWidth || y < 0 || y >= gp.screenHeight) {
			return true;
		}
		
		int xIndex = x / gp.tileSize;
		int yIndex = y / gp.tileSize;
		
		Tile tile = tiles[yIndex][xIndex];
		System.out.println(tile);
		System.out.println(!tile.collision);
		return tile.collision;
	}
}
