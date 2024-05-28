package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import ai.PathFinder;
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
	
	public KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public BulletManager bulletManager = new BulletManager(this);
	public TileManager tileManager = new TileManager(this);
	public Background background = new Background(this);
	public Player player = new Player(this);	
	public AlienManager alienManager = new AlienManager(this);
	public UI ui = new UI(this);
	Spawner spawner = new Spawner(this);
	public PathFinder pFinder = new PathFinder(this);
	public Tile[][] tiles;
	public ArrayList<Bullet> bullets;
	public ArrayList<Alien> aliens;
	public boolean reset = false;
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int deathState = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		gameState = titleState;
	}
	 
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
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
		System.out.println(gameState);
		if(gameState == titleState) {
			if(!reset) {
				alienManager.reset();
				player.reset();
				bulletManager.reset();
				spawner.reset();
				keyH.reset();
			}
			reset = true;
		}
		if(gameState == playState) {
			background.update();
			player.update();
			spawner.update();
			alienManager.update();
			bulletManager.update();
			alienManager.updateStatus();
			reset = false;
		}
		
		if(gameState == pauseState) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == titleState) {

		} else {
			background.draw(g2);
			bulletManager.draw(g2);
			player.draw(g2);
			tileManager.draw(g2);
			alienManager.draw(g2);
		}
		
		ui.draw(g2);
		g2.dispose();
	}
	
	
}
