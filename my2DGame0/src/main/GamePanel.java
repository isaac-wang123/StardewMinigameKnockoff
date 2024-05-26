package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Alien;
import entity.Bullet;
import entity.Player;
import tile.Background;
import tile.Tile;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	BulletManager bulletManager = new BulletManager(this, keyH);
	TileManager tileManager = new TileManager(this);
	Background background = new Background(this);
	Player player = new Player(this,keyH);	
	AlienManager alienManager = new AlienManager(this);
	Spawner spawner = new Spawner(this,tileManager.getTiles());
	public Tile[][] tiles;
	public ArrayList<Bullet> bullets;
	public ArrayList<Alien> aliens;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		tiles = tileManager.getTiles();
		bullets = bulletManager.bullets;
		aliens = alienManager.aliens;
	}
	 
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
			
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) {			
			
			update();
			
			repaint();
			try {
				double remainingTime  = nextDrawTime - System.nanoTime();
				remainingTime/=1000000;
				
				if(remainingTime<0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			
			
		}
	}
	
	public void update() {
		background.update();
		player.update();
		spawner.update(alienManager);
		alienManager.update(player);
		bulletManager.update(player,alienManager.aliens);
		alienManager.updateStatus();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		background.draw(g2);
		bulletManager.draw(g2);
		player.draw(g2);
		tileManager.draw(g2);
		alienManager.draw(g2);
		
		g2.dispose();
	}
	
	
}
