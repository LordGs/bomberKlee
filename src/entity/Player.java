package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	//will change to Oculus Soon
	public int numOfKeys = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 33;
		solidArea.y = 33;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 15; //29
		solidArea.height = 15; //46
		
		attackArea.width = 50;
		attackArea.height = 50;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		
	}
	
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 9;
		worldY = gp.tileSize * 12;
		speed = 5;
		direction = "down";
		
		//Player Status
		maxLife = 6;
		life = maxLife;
		
	}
	
	public void setDefaultPosition() {
		worldX = gp.tileSize * 9;
		worldY = gp.tileSize * 12;
		direction = "down";
	}
	
	
	public void restoreLife() {
		life = maxLife;
		invincible = false;
	}
	
	public void getPlayerImage() {
//		up1 = setup("/player/kleeBack1");
//		up2 = setup("/player/kleeBack2");
//		down1 = setup("/player/kleeFront1");
//		down2 = setup("/player/kleeFront2");
//		left1 = setup("/player/kleeLeft1");
//		left2 = setup("/player/kleeLeft2");
//		right1 = setup("/player/kleeRight1");
//		right2 = setup("/player/kleeRight2");
		
		up1 = setup("/kleePlayer/up1", gp.tileSize, gp.tileSize);
		up2 = setup("/kleePlayer/up2", gp.tileSize, gp.tileSize);
		down1 = setup("/kleePlayer/down1", gp.tileSize, gp.tileSize);
		down2 = setup("/kleePlayer/down2", gp.tileSize, gp.tileSize);
		left1 = setup("/kleePlayer/left1", gp.tileSize, gp.tileSize);
		left2 = setup("/kleePlayer/left2", gp.tileSize, gp.tileSize);
		right1 = setup("/kleePlayer/right1", gp.tileSize, gp.tileSize);
		right2 = setup("/kleePlayer/right2", gp.tileSize, gp.tileSize);
		
		tScreen = setup("/player/TitleScreen", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		attackUp1 = setup("/kleePlayer/atkUp1", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/kleePlayer/atkDown1", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/kleePlayer/atkLeft1", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/kleePlayer/atkRight1", gp.tileSize * 2, gp.tileSize);
		
		attackUp2 = setup("/kleePlayer/atkUp2", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/kleePlayer/atkDown2", gp.tileSize, gp.tileSize * 2);
		attackLeft2 = setup("/kleePlayer/atkLeft2", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/kleePlayer/atkRight2", gp.tileSize * 2, gp.tileSize);
		
		attackUp3 = setup("/kleePlayer/atkUp3", gp.tileSize, gp.tileSize * 2);
		attackDown3 = setup("/kleePlayer/atkDown3", gp.tileSize, gp.tileSize * 2);
		attackLeft3 = setup("/kleePlayer/atkLeft3", gp.tileSize * 2, gp.tileSize);
		attackRight3 = setup("/kleePlayer/atkRight3", gp.tileSize * 2, gp.tileSize);
		
		attackUp4 = setup("/kleePlayer/atkUp4", gp.tileSize, gp.tileSize * 2);
		attackDown4 = setup("/kleePlayer/atkDown4", gp.tileSize, gp.tileSize * 2);
		attackLeft4 = setup("/kleePlayer/atkLeft4", gp.tileSize * 2, gp.tileSize);
		attackRight4 = setup("/kleePlayer/atkRight4", gp.tileSize * 2, gp.tileSize);
	}
	
	public void update() {
		
		if(attacking == true) {
			attacking();
			
		}
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true || keyH.bombPressed == true){   
			
			if(keyH.upPressed == true) {
				direction = "up";	
				
			}
			else if(keyH.downPressed == true) {
				direction = "down";	
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check object collission
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//check npc collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			
			
			//CheckEvent
			gp.eHandler.checkEvent();
			
			if(collisionOn == false && keyH.enterPressed == false && keyH.bombPressed == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			gp.keyH.enterPressed = false;
			gp.keyH.bombPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 14) {
				if(spriteNum == 1) {
					spriteNum = 2;
					gp.playSE(3);
				}
				else if(spriteNum ==2) {
					spriteNum = 1;
					gp.playSE(4);
					
				}
				spriteCounter = 0;
			}
			
		}
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		//game over condition
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.playSE(13);
			gp.stopMusic();
			
		}

	}
	
	
	public void attacking(){
		spriteCounter++;
		
		if(spriteCounter <=6) {
			spriteNum = 1;
		}
		if(spriteCounter > 6 && spriteCounter <= 12) {
			spriteNum = 2;
			//save the current x world and y world solid area
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//adjust player's world XY for the attacks
			
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX+= attackArea.width; break;
			}
			
			//attack area becomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//check monster collision with the updated worldX and WordY and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			// restore the original daata
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > 12 && spriteCounter <= 18) {
			spriteNum = 3;
		}
		if(spriteCounter > 18 && spriteCounter <= 25) {
			spriteNum = 4;
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	//when you pick up object 
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				gp.playSE(6);
				numOfKeys++;
				gp.obj[i] = null;
				break;
			case "Door":
				
				if(numOfKeys > 1) {
					gp.playSE(2);
					//gp.playSE(5); //master jean is a bitch
					gp.obj[i] = null;
					numOfKeys--;
				}else {
					//gp.ui.showMessage("You need to Find 2 Keys");
				}
				break;
			case "Chest":
				if(numOfKeys >= 10) {
					gp.ui.gameFinished = true;
					numOfKeys = 0;
					gp.gameState = gp.victoryState;
					gp.stopMusic();
					gp.playSE(7);
					
				} else {
					//gp.ui.showMessage("You need Two Keys to Unlock");
						
				}
					
				break;
			}
		}
		
		
	}
	
	
	public void interactNPC(int i) {
		
		if(gp.keyH.enterPressed == true) {
			
			if(i != 999) {
				//What happens when you collided with an NPC
				System.out.print("You collided");

			} else {
					
			}
		}

		if(gp.keyH.bombPressed == true) {
			
			if(i != 999) {
				//What happens when you collided with an NPC
				

			} else {
					attacking = true;
			}
		}
		
	}
	
	public void contactMonster(int i) {
		if(i != 999) {
			
			//What happens when you collided with a monster
			
			
			if(invincible == false) {
				
				System.out.print("You collided with a monster ");
				life -= 1;
				gp.playSE(9);
				invincible = true;
				
			}
			
		}
		
	}
	
	
	//what happens when you hit a monster
	public void damageMonster(int i) {
		if(i != 999) {
			
			System.out.print("hit ");
			if(gp.monster[i].invincible == false) {
				gp.monster[i].life -= 1;
				gp.playSE(11);
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;;
					gp.playSE(12);
				}
			}
			
		}
		else {
			System.out.print("missed ");
		}
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
				if(spriteNum == 3) {image = attackUp3;}
				if(spriteNum == 4) {image = attackUp4;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
				if(spriteNum == 3) {image = attackDown3;}
				if(spriteNum == 4) {image = attackDown4;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
				if(spriteNum == 3) {image = attackLeft3;}
				if(spriteNum == 4) {image = attackLeft4;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
				if(spriteNum == 3) {image = attackRight3;}
				if(spriteNum == 4) {image = attackRight4;}
			}
			break;
		}
		
		if(invincible == true) {
			//make transparency half 
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		//reset transparency
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}

}
