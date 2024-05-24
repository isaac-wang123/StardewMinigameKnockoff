package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	Tile[][] tiles;
	int[][] tileId;
	GamePanel gp;
	BufferedImage[] images;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
		tileId = new int[gp.maxScreenCol][gp.maxScreenRow];
		images = new BufferedImage[10];
		getImage();
		loadMap();
	}
	
	public void getImage() {
		try {
			images[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/testTileRed.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		for(int i= 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length;j++) {
				InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				tiles[i][j] = new Tile(gp, i*gp.tileSize, j*gp.tileSize,images[0]);
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		for(int i= 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length;j++) {
				tiles[i][j].draw(g2);
			}
		}
		
	}
}
