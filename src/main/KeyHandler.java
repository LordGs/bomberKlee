package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean enterPressed, bombPressed;
	
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TitleState
		
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.playSE(1);
				gp.ui.commandNum--;
				
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				
			} 
			if(code == KeyEvent.VK_S) {
				gp.playSE(1);
				gp.ui.commandNum++;
				
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}

			} 
			
			
			if(code == KeyEvent.VK_ENTER) {
				
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playSE(2);
					gp.stopMusic();
					gp.playMusic(0);
				}
				
				if(gp.ui.commandNum == 1) {
					// later
				}
				
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}				
				
			}
			
		}
		
		
		
		
		//PlayState
		if(gp.gameState == gp.playState) {
			
			
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
			}if(code == KeyEvent.VK_ENTER) {	
				enterPressed = true;
			}if(code == KeyEvent.VK_B) {	
				bombPressed = true;
				gp.playSE(10);
			}

		}
		
		
		//game over state
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		
		//game over state
		else if(gp.gameState == gp.victoryState) {
			victoryState(code);
		}
		
		
		
		if(code == KeyEvent.VK_ESCAPE) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			} else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
		
		
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(1);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(1);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.restart();
			}
		}
	}
	
	public void victoryState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(1);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(1);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.restart();
			}
		}
	}

	@Override
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
		
		
	}
	

}
