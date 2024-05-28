package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed, escapePressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		if(code == KeyEvent.VK_UP) {
			upArrowPressed = true;
		}
		
		if(code == KeyEvent.VK_DOWN) {
			downArrowPressed = true;
		}
		
		if(code == KeyEvent.VK_LEFT) {
			leftArrowPressed = true;
		}
		
		if(code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = true;
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			escapePressed = true;
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			} else if (gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		 
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
			
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
			
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
			
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		} 
		
		if(code == KeyEvent.VK_UP) {
			upArrowPressed = false;
		}
		
		if(code == KeyEvent.VK_DOWN) {
			downArrowPressed = false;
		}
		
		if(code == KeyEvent.VK_LEFT) {
			leftArrowPressed = false;
		}
		
		if(code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = false;
		}
	}

}
