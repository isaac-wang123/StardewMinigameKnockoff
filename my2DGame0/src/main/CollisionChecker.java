package main;

import java.awt.Rectangle;

import tile.Tile;

public class CollisionChecker {
	
	public static boolean checkTile(GamePanel gp, int x, int y, int width, int height, boolean alien) {	
		if(!isSolid(x, y, gp, alien)) {
			if(!isSolid(x + width, y, gp, alien)) {
				if(!isSolid(x, y + height, gp, alien)) {
					if(!isSolid(x + width, y + height, gp, alien)) {
						return true;
					}
				}
			}
		}
		return false;
	} 
	
	private static boolean isSolid(int x, int y, GamePanel gp, boolean alien) {
		Tile[][] tiles = gp.tiles;
		if(!alien && (x < 0 || x >= gp.screenWidth || y < 0 || y >= gp.screenHeight)) {
			return true;
		}
		
		int xIndex = x / gp.tileSize;
		int yIndex = y / gp.tileSize;
		if(xIndex < 0 || yIndex < 0 || xIndex >= gp.maxScreenCol || yIndex >= gp.maxScreenRow) {
			return false;
		}
		Tile tile = tiles[yIndex][xIndex];
		return tile.collision;
	}
}
