package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	public Tile[][] tiles;
	GamePanel gp;
	TileData[] images;
	boolean drawPath = false;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
		images = new TileData[10];
		getImage();
		loadMap();
	}
	
	public void getImage() {
		try {
			images[0] = new TileData(ImageIO.read(getClass().getResourceAsStream("/tiles/transparent.png")),false, false);
			images[1] = new TileData(ImageIO.read(getClass().getResourceAsStream("/tiles/asteroid.png")),true, false);
			images[2] = new TileData(ImageIO.read(getClass().getResourceAsStream("/tiles/transparent.png")),false, true);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map02.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				while(col < gp.maxScreenCol) {
					
					int num = Integer.parseInt(numbers[col]);
					tiles[row][col] = new Tile(gp, col*gp.tileSize, row*gp.tileSize, images[num].image, images[num].collision, images[num].spawn);
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		for(Tile[] rows: tiles) {
			for(Tile tile : rows) {
				tile.draw(g2);
			}
		}

		if(drawPath) {
			g2.setColor(Color.red);
			for(int i = 0 ; i < gp.pFinder.pathList.size(); i++) {
				int x = gp.pFinder.pathList.get(i).col * gp.tileSize;
				int y = gp.pFinder.pathList.get(i).row * gp.tileSize;
				g2.drawRect(x,  y,  gp.tileSize,  gp.tileSize);
			}

		}
	}
}
