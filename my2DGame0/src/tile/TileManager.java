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
	GamePanel gp;
	ImageAndCollision[] images;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
		images = new ImageAndCollision[10];
		getImage();
		loadMap();
	}
	
	public void getImage() {
		try {
			images[1] = new ImageAndCollision(ImageIO.read(getClass().getResourceAsStream("/tiles/testTileRed.png")),true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				while(col < gp.maxScreenCol) {
					
					int num = Integer.parseInt(numbers[col]);
					
					if(num ==0) {
						col++;
						continue;
					}
					
					tiles[row][col] = new Tile(gp, row*gp.tileSize, col*gp.tileSize,images[num].image, images[num].collision);
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		for(Tile[] rows: tiles) {
			for(Tile tile : rows) {
				if(tile!=null) {
					tile.draw(g2);
				}
			}
		}
	}
	
}
