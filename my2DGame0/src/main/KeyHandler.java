package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
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
