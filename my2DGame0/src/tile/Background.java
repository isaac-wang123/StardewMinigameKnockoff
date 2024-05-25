package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {
	GamePanel gp;
	int count;
	int resetCount;
	int imageIndex;
	BufferedImage[] images;
	
	public Background(GamePanel gp){
		this.gp = gp;
		
		count = 0;
		resetCount = 10;
		
		getImage();
	}
	
	public void getImage() {
		images = new BufferedImage[27];
		
		try {
			images[0] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-0.png"));
			images[1] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-1.png"));
			images[2] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-2.png"));
			images[3] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-3.png"));
			images[4] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-4.png"));
			images[5] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-5.png"));
			images[6] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-6.png"));
			images[7] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-7.png"));
			images[8] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-8.png"));
			images[9] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-9.png"));
			images[10] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-10.png"));
			images[11] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-11.png"));
			images[12] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-12.png"));
			images[13] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-13.png"));
			images[14] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-14.png"));
			images[15] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-15.png"));
			images[16] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-16.png"));
			images[17] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-17.png"));
			images[18] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-18.png"));
			images[19] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-19.png"));
			images[20] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-20.png"));
			images[21] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-21.png"));
			images[22] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-22.png"));
			images[23] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-23.png"));
			images[24] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-24.png"));
			images[25] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-25.png"));
			images[26] = ImageIO.read(getClass().getResourceAsStream("/background/pixil-frame-26.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(count==resetCount-1) {
			if(imageIndex == images.length-1) {
				imageIndex = 0;
			} else {
				imageIndex++;
			}
			count = 0;
		} else {
			count++;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(images[imageIndex], 0, 0, gp.screenWidth,gp.screenHeight, null);
	}
	
}
