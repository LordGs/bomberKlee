package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_DodocoCollectible;

public class Entity {
	
	GamePanel gp;
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tScreen;
	
	//this is for the atack BufferedImages
	public BufferedImage attackUp1, attackDown1, attackLeft1, attackRight1;
	public BufferedImage attackUp2, attackDown2, attackLeft2, attackRight2;
	public BufferedImage attackUp3, attackDown3, attackLeft3, attackRight3;
	public BufferedImage attackUp4, attackDown4, attackLeft4, attackRight4;
	
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0,0, 48, 48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false;
	
	boolean hpBarOn = false;
	
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	int dyingCounter = 0;
	int hpBarCounter = 0;
	
	
	
	public int type; // 0 player 1 npc 2 monster
	
	
	//Character Status
	public int maxLife;
	public int life;
	
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void damageReaction() {}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				//we can give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
				gp.playSE(9);
			}
		}
		
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		
		spriteCounter++;
		if(spriteCounter > 14) {
			if(spriteNum == 1) {
				spriteNum = 2;
				//gp.playSE(3); this is the footsteps
			}
			else if(spriteNum ==2) {
				spriteNum = 1;
				//gp.playSE(4); this it the footsteps
				
			}
			spriteCounter = 0;
		}
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			switch(direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				break;
			}
			
			//monster HP Bar
			if(type == 2  && hpBarOn == true){
				
				double oneScale = (double)gp.tileSize / maxLife;
				double hpBarValue = oneScale * life;
				
				g2.setColor(new Color(158, 46, 71));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 9);
				g2.setColor(new Color(199, 84, 110));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 7);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
				
			}
			
			
			if(invincible == true) {
				//make transparency half for monster
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			
			if(dying == true) {
				dyingAnimation(g2);
			}
			
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			changeAlpha(g2, 1F);
			
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) { changeAlpha(g2, 0f); }
		if(dyingCounter > i && dyingCounter <=i * 2) { changeAlpha(g2, 1f); }
		if(dyingCounter > i * 2 && dyingCounter <= i * 3) { changeAlpha(g2, 0f); }
		if(dyingCounter > i * 3 && dyingCounter <= i * 4) { changeAlpha(g2, 1f); }
		if(dyingCounter > i * 4 && dyingCounter <= i * 5) { changeAlpha(g2, 0f); }
		if(dyingCounter > i * 5 && dyingCounter <= i * 6) { changeAlpha(g2, 1f); }
		if(dyingCounter > i * 6 && dyingCounter <= i * 7) { changeAlpha(g2, 0f); }
		if(dyingCounter > i * 7 && dyingCounter <= i * 8) { changeAlpha(g2, 1f); }
		if(dyingCounter > i * 8) {
			dying = false;
			alive = false;
		}
		
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	//this is the scaling of the heart ont he screen
		public BufferedImage setupHeart(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize / 2 + 15, gp.tileSize / 2 + 15);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	

}
