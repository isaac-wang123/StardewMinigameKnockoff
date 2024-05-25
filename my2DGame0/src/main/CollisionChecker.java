package main;

import java.awt.Rectangle;

import tile.Tile;

public class CollisionChecker {
	
	public static boolean checkTile(GamePanel gp, int x, int y, int width, int height) {	
		if(!isSolid(x, y, gp)) {
			if(!isSolid(x + width, y, gp)) {
				if(!isSolid(x, y + height, gp)) {
					if(!isSolid(x + width, y + height, gp)) {
						return true;
					}
				}
			}
		}
		return false;
	} 
	
	private static boolean isSolid(int x, int y, GamePanel gp) {
		Tile[][] tiles = gp.tiles;
		if(x < 0 || x >= gp.screenWidth || y < 0 || y >= gp.screenHeight) {
			return true;
		}
		
		int xIndex = x / gp.tileSize;
		int yIndex = y / gp.tileSize;
		
		Tile tile = tiles[yIndex][xIndex];
		return tile.collision;
	}
}
