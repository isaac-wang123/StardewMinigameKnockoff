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
	public int count;
	int deathIndex;
	BufferedImage[] death;
	int deathFrameRate = 4;
	boolean newHigh;
	
	public UI(GamePanel gp) {
		this.gp  = gp;
		death = new BufferedImage[16];
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
		count = 0;
		deathIndex = 0;
	}
	
	public void loadImage() {
		try {
			title = ImageIO.read(getClass().getResourceAsStream("/screens/titlev5.png"));
			mario = ImageIO.read(getClass().getResourceAsStream("/player/front-1.png"));
			death[0] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-0.png"));
			death[1] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-1.png"));
			death[2] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-2.png"));
			death[3] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-3.png"));
			death[4] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-4.png"));
			death[5] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-5.png"));
			death[6] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-6.png"));
			death[7] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-7.png"));
			death[8] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-8.png"));
			death[9] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-9.png"));
			death[10] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-10.png"));
			death[11] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-11.png"));
			death[12] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-12.png"));
			death[13] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-13.png"));
			death[14] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-14.png"));
			death[15] = ImageIO.read(getClass().getResourceAsStream("/death/pixil-frame-15.png"));

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
				count = 0;
				reset();
				drawPlayScreen();
				break;
			case 2:
				drawPauseScreen();
				break;
			case 0:
				drawTitleScreen();
				break;
			case 3:
				drawDeathScreen();
				break;
		}
		
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
		
		String text = "Wave " + gp.spawner.wave;
		int x = (int) (gp.tileSize + 20);
		int y = (int) (gp.tileSize + 40);
		g2.drawString(text, x, y);
		
		text = "Paused";
		x = getCenterX(text);
		y = gp.screenHeight/2;
		g2.drawString(text, x, y);
		
	}
	
	public void drawDeathScreen() {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		if(gp.spawner.wave > gp.maxWave) {
			gp.maxWave = gp.spawner.wave;
			gp.sd.save();
			
			newHigh = true;
		}
		

		if(count < 60) {
			gp.player.draw(g2);
			count++;
		}
		if(count > 39) {
			if(count % deathFrameRate == 0 && deathIndex < death.length - 1) {
				deathIndex++;
			}
			if(count < 39 + (death.length - 1) * deathFrameRate) {
				g2.drawImage(death[deathIndex], gp.player.x, gp.player.y, gp.tileSize, gp.tileSize, null);
			}
			count++;
		}
		
		if(count > 80 + (death.length - 1) * deathFrameRate) {
			if(newHigh) {
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
				g2.setColor(Color.red);
				String text = "New High!";
				int x = getCenterX(text);
				int y = gp.tileSize *6;
				g2.drawString(text, x, y);
			}
			
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
			String text = "Game Over";
			int x = getCenterX(text);
			int y = gp.tileSize * 3;		
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

			text = "Wave: " + gp.spawner.wave;
			x = getCenterX(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);

			text = "Max Wave: " + gp.maxWave;
			x = getCenterX(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);

			
			
			text = "Retry";
			x = getCenterX(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Main Menu";
			x = getCenterX(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Quit";
			x = getCenterX(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
		

		
	}
	
	public void drawPlayScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
		String text = "Wave " + gp.spawner.wave;
		int x = (int) (gp.tileSize + 20);
		int y = (int) (gp.tileSize + 40);
		g2.drawString(text, x, y);
		
		text = "Time: " + gp.spawner.timeElapsed;
		y += gp.tileSize * 2 / 3;
//		g2.drawString(text, x, y);

		

	}
	
	public void drawTitleScreen(){
		g2.drawImage(title, 0, 0, gp.screenWidth, gp.screenHeight, null);

		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		String text = "Cool Space Game";
		int x = getCenterX(text);
		int y = gp.tileSize * 3;		
		
		
		g2.setColor(Color.black);
		g2.drawString(text,  x + 5,  y + 5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
		
		x = (int) (gp.screenWidth / 2 - 0.5 * gp.tileSize);
		y += gp.tileSize;
		g2.drawImage(mario,  x,  y, gp.tileSize,  gp.tileSize, null);
		
		text = "Start";
		x = getCenterX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if(commandNum ==0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "Quit";
		x = getCenterX(text);
		y += gp.tileSize;
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
	
	public void reset() {
		count = 0;
		deathIndex = 0;
		commandNum = 0;
		newHigh = false;
	}
}
