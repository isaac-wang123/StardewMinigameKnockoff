package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, maruMonica;
	public boolean gameFinished = false; 
	BufferedImage title, mario;
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp  = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadImage();
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void loadImage() {
		try {
			title = ImageIO.read(getClass().getResourceAsStream("/screens/titlev5.png"));
			mario = ImageIO.read(getClass().getResourceAsStream("/player/front-1.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		switch(gp.gameState) {
			case 1: 
				break;
			case 2:
				drawPauseScreen();
				break;
			case 0:
				drawTitleScreen();
				break;
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "Paused";
		int x = getCenterX(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	
	public void drawTitleScreen(){
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		String text = "Cool Space Game";
		int x = getCenterX(text);
		int y = gp.tileSize * 3;		
		
		g2.drawImage(title, 0, 0, gp.screenWidth, gp.screenHeight, null);
		
		g2.setColor(Color.black);
		g2.drawString(text,  x + 5,  y + 5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		x = (gp.maxScreenCol/2 - 1)* gp.tileSize;
		y += gp.tileSize * 1.5;
		g2.drawImage(mario,  x,  y, gp.tileSize * 2,  gp.tileSize * 2, null);
		
		text = "Start";
		x = getCenterX(text);
		y += 3.5*gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum ==0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "Quit";
		x = getCenterX(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		

	}
	public int getCenterX(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
