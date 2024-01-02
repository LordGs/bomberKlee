package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_DodocoCollectible;
import object.OBJ_Dodoco;



public class UI {
	
	GamePanel gp;
	Font arial20, arial30, genshinFont, pixelFont;
	Graphics2D g2;
	
	BufferedImage keyImage, heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		
		try {
			//genshin font
			InputStream is = getClass().getResourceAsStream("/font/babyDoll.ttf");
			genshinFont = Font.createFont(Font.TRUETYPE_FONT, is);
			//pixel font
			is = getClass().getResourceAsStream("/font/PixelGameFont.ttf");
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
//		arial20 = new Font("Arial", Font.PLAIN, 20);
//		arial30 = new Font("Calibri", Font.BOLD, 40);
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
		
		//Create HUD Object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
		
	}
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(genshinFont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		//title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//play state
		if(gp.gameState == gp.playState) {
			//uhhh
			drawOculusNum();
			drawPlayerLife();
			
			
		// pause state
		}if(gp.gameState == gp.pauseState) {
			//ihh
			drawPauseScreen();
		}
		if(gp.gameState == gp.gameOverState) {
			//ihh
			drawGameOverScreen();
		}
		if(gp.gameState == gp.victoryState) {
			//ihh
			drawVictoryScreen();
		}
		
		
	}
	
	
	public void drawOculusNum() {
		OBJ_Dodoco oculus = new OBJ_Dodoco(gp);
		keyImage = oculus.image;
		g2.drawImage(keyImage, gp.tileSize * 12 , gp.tileSize / 3 - 3, gp.tileSize, gp.tileSize, gp);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		g2.setColor(new Color(255,255,255,255));
		g2.drawString("=" + gp.player.numOfKeys, gp.tileSize * 13 , gp.tileSize / 2 + 30);
	}
	
	
	public void drawPlayerLife() {
		
		//gp.player.life = 3;
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		//Draw max Heart
		while(i < gp.player.maxLife / 2) { //  divide by 2 if you want 3 hearts/ 2
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//reset heart
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		//draw current life
		while(i < gp.player.life) {
			g2.drawImage(heart_half,x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		
		
		//Image
//			x = gp.screenWidth / 5;
//			y += gp.tileSize * 1;
//			g2.drawImage(keyImage, x, y, gp.tileSize * 8, gp.tileSize * 8, null);
		
		
	}
	
	
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,170));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "Game Over";
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		g2.setColor(new Color(255,255,255));
		g2.drawString(text, x- 4, y - 4);
		
//		restart
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		text = "Restart";
		x = getXforCenteredText(text);
		y = gp.tileSize * 6;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x - 4, y - 4);
		
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
	
		
//		QUIT
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		text = "Exit";
		x = getXforCenteredText(text);
		y = gp.tileSize * 7;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x - 4, y - 4);
		
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	
	
	
	public void drawVictoryScreen() {
		
		g2.setColor(new Color(0,0,0,170));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "You found All Dodocos!";
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		g2.setColor(new Color(255,255,255));
		g2.drawString(text, x- 4, y - 4);
		
//		restart
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		text = "Restart";
		x = getXforCenteredText(text);
		y = gp.tileSize * 6;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x - 4, y - 4);
		
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
	
		
//		QUIT
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		text = "Exit";
		x = getXforCenteredText(text);
		y = gp.tileSize * 7;
		g2.setColor(new Color(0,0,0,100));
		g2.drawString(text, x, y);
		
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x - 4, y - 4);
		
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	
	
	
	//Full Title Screen
	public void drawTitleScreen() {
		
		//background
		g2.setColor(new Color(155,212,195));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "BomberKlee";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;
		
		//shadow
		g2.setColor(new Color(0,0,0,90));
		g2.drawString(text, x, y+10);
		//stroke
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x, y+5);
		g2.drawString(text, x+5, y);
		g2.drawString(text, x, y-5);
		g2.drawString(text, x-5, y);
		//main
		g2.setColor(new Color(192,212,112));
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
		String text2 = "Adventures";
		int x2 = getXforCenteredText(text2);
		int y2 = gp.tileSize * 3 + 30;
		
		//shadow
		g2.setColor(new Color(0,0,0,90));
		g2.drawString(text2, x2, y2+6);
		//stroke
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text2, x2, y2+2);
		g2.drawString(text2, x2+2, y2);
		g2.drawString(text2, x2, y2-2);
		g2.drawString(text2, x2-2, y2);
		
		//main
		g2.setColor(new Color(155,212,195,255));
		g2.drawString(text2, x2, y2);
		
		
		//Menu Choices
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		//PLAY
		text = "PLAY";
		x = getXforCenteredText(text);
		y = gp.tileSize * 6;
		
		//PLAY shadow
		g2.setColor(new Color(0,0,0,50));
		g2.drawString(text, x, y+4);
		//PLAY main
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		//ABOUT
		text = "ABOUT";
		x = getXforCenteredText(text);
		y = gp.tileSize * 7;
						
		//ABOUT shadow
		g2.setColor(new Color(0,0,0,50));
		g2.drawString(text, x, y+4);
		//ABOUT main
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x, y);
		
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		//EXIT
		text = "EXIT";
		x = getXforCenteredText(text);
		y = gp.tileSize * 8;
				
		//EXIT shadow
		g2.setColor(new Color(0,0,0,50));
		g2.drawString(text, x, y+4);
		//EXIT main
		g2.setColor(new Color(255,255,255,255));
		g2.drawString(text, x, y);
		
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		

		
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Paused";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}




