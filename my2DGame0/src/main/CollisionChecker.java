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
		Tile[][] tiles = gp.tileManager.tiles;	
		int xIndex = x / gp.tileSize;
		int yIndex = y / gp.tileSize;
		if(xIndex < 0 || yIndex < 0 || xIndex >= gp.maxScreenCol || yIndex >= gp.maxScreenRow) {
			return false;
		}
		Tile tile = tiles[yIndex][xIndex];
		return tile.collision;
	}
	
	public static boolean radiusChecker(GamePanel gp, Rectangle hitbox) {
		Tile[][] tiles = gp.tileManager.tiles;	
		if(hitbox.x < 0 || hitbox.y < 0) {
			return false;
		}
		int col = hitbox.x / gp.tileSize;
		int row = hitbox.y / gp.tileSize;
		if(row >= 0 && row < gp.maxScreenCol && col >= 0 && col < gp.maxScreenRow) {
			if(tiles[col][row].collision) {
				return true;
			}
			if(row + 1 < gp.maxScreenCol) {
				if(tiles[col][row + 1].collision) {
					return true;
				}
			}
			if(col + 1 < gp.maxScreenRow) {
				if(tiles[col + 1][row].collision) {
					return true;
				}			
			}
			if(col + 1 < gp.maxScreenRow && row + 1 < gp.maxScreenCol) {
				if(tiles[col + 1][row + 1].collision) {
					return true;
				}			
			}
			if(row > 0) {
				if(tiles[col][row-1].collision) {
					return true;
				}
			}
			if(col > 0) {
				if(tiles[col-1][row].collision) {
					return true;
				}
			}
			if(row > 0 && col > 0) {
				if(tiles[col-1][row-1].collision) {
					return true;
				}
			}
			if(col > 0 && row + 1 < gp.maxScreenCol) {
				if(tiles[col - 1][row + 1].collision) {
					return true;
				}			
			}
			if(col + 1 < gp.maxScreenRow && row > 0) {
				if(tiles[col + 1][row - 1].collision) {
					return true;
				}			
			}
		}
		return false;
	}

}
