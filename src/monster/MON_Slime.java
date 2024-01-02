package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Slime extends Entity{
	
	GamePanel gp;

	public MON_Slime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		// TODO Auto-generated constructor stub
		type = 2;
		name = "Hydro Slime";
		speed = 1;
		
		maxLife = 6; //life of the slime
		life = maxLife;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		
		up1 = setup("/monster/slimeUp1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/slimeUp2", gp.tileSize, gp.tileSize);
		
		down1 = setup("/monster/slimeLeft1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/slimeLeft2", gp.tileSize, gp.tileSize);
		
		left1 = setup("/monster/slimeLeft1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/slimeLeft2", gp.tileSize, gp.tileSize);
		
		right1 = setup("/monster/slimeRight1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/slimeRight2", gp.tileSize, gp.tileSize);
		
		
		
		
	}
	
	
	public void setAction() {
		Random random = new Random();
		int i = random.nextInt(100) + 1; //pick up a number randomly from 1 - 100
		
		actionLockCounter++;
		
		if(actionLockCounter == 100) {
			
			if(i <=25) {
				direction = "up";
				speed = 1;
			}
			if(i > 25 && i <= 50) {
				direction = "down";
				speed = 1;
			}
			if(i > 50 && i <= 75) {
				direction = "left";
				speed = 1;
			}
			if(i > 75 && i <= 100) {
				direction = "right";
				speed = 1;
				
			}
			
			actionLockCounter = 0;
			
		}
		
		
	}
	
	//this is the simple AI for monster to follow you when you attack it
	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gp.player.direction;
		
		if(gp.player.direction == "up") {
			direction = "down";
			speed = 1;
		}
		if(gp.player.direction == "down") {
			direction = "up";
			speed = 1;
		}
		if(gp.player.direction == "left") {
			direction = "right";
			speed = 1;
		}
		if(gp.player.direction == "right") {
			direction = "left";
			speed = 1;
		}
	}

}




