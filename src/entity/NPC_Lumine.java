package entity;
import java.util.Random;

import main.GamePanel;

public class NPC_Lumine extends Entity{

	public NPC_Lumine(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea.x = 10;
		solidArea.y = 10;
		solidArea.width = 30;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	
	
	public void getImage() {

		up1 = setup("/player/kleeBack1" , gp.tileSize, gp.tileSize);
		up2 = setup("/player/kleeBack2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/KleeFront1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/KleeFront2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/KleeLeft1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/KleeLeft2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/KleeRight1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/KleeRight2", gp.tileSize, gp.tileSize);
		
//		up1 = setup("/monster/slime1");
//		up2 = setup("/monster/slime2");
//		down1 = setup("/monster/slime1");
//		down2 = setup("/monster/slime2");
//		left1 = setup("/monster/slime1");
//		left2 = setup("/monster/slime2");
//		right1 = setup("/monster/slime1");
//		right2 = setup("/monster/slime2");
		
		
	}
	
	public void setAction() {
		
		Random random = new Random();
		int i = random.nextInt(100) + 1; //pick up a number randomly from 1 - 100
		
		actionLockCounter++;
		
		if(actionLockCounter == 70) {
			
			if(i <=25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
			
		}
		
		
		
	}
	
	
}
