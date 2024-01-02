package main;
import monster.MON_Slime;
import object.OBJ_Chest;
import object.OBJ_DodocoCollectible;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_DodocoCollectible(gp);
		gp.obj[0].worldX = 12 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
		
		gp.obj[1] = new OBJ_DodocoCollectible(gp);
		gp.obj[1].worldX = 26 * gp.tileSize;
		gp.obj[1].worldY = 10 * gp.tileSize;

		gp.obj[2] = new OBJ_DodocoCollectible(gp);
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 11 * gp.tileSize;
		
		gp.obj[3] = new OBJ_DodocoCollectible(gp);
		gp.obj[3].worldX = 14 * gp.tileSize;
		gp.obj[3].worldY = 17 * gp.tileSize;

		gp.obj[4] = new OBJ_DodocoCollectible(gp);
		gp.obj[4].worldX = 17 * gp.tileSize;
		gp.obj[4].worldY = 23 * gp.tileSize;
		
		gp.obj[5] = new OBJ_DodocoCollectible(gp);
		gp.obj[5].worldX = 21 * gp.tileSize;
		gp.obj[5].worldY = 38 * gp.tileSize;
		
		gp.obj[6] = new OBJ_DodocoCollectible(gp);
		gp.obj[6].worldX = 29 * gp.tileSize;
		gp.obj[6].worldY = 23 * gp.tileSize;
		
		gp.obj[7] = new OBJ_DodocoCollectible(gp);
		gp.obj[7].worldX = 31 * gp.tileSize;
		gp.obj[7].worldY = 26 * gp.tileSize;
		
		gp.obj[8] = new OBJ_DodocoCollectible(gp);
		gp.obj[8].worldX = 39 * gp.tileSize;
		gp.obj[8].worldY = 36 * gp.tileSize;
		
		gp.obj[9] = new OBJ_DodocoCollectible(gp);
		gp.obj[9].worldX = 39 * gp.tileSize;
		gp.obj[9].worldY = 38 * gp.tileSize;
		
		
		//chest
		
		gp.obj[10] = new OBJ_Chest(gp);
		gp.obj[10].worldX = 10 * gp.tileSize;
		gp.obj[10].worldY = 9 * gp.tileSize;
	}

	public void setNPC() {
		
//		gp.npc[0] = new NPC_Lumine(gp);
//		gp.npc[0].worldX = gp.tileSize*8;
//		gp.npc[0].worldY = gp.tileSize*11;
		
//		gp.npc[1] = new NPC_Lumine(gp);
//		gp.npc[1].worldX = gp.tileSize*37;
//		gp.npc[1].worldY = gp.tileSize*30;
//		
//		gp.npc[2] = new NPC_Lumine(gp);
//		gp.npc[2].worldX = gp.tileSize*23;
//		gp.npc[2].worldY = gp.tileSize*14;
		
	}
	
	
	public void setMonster() {
		
		gp.monster[0] = new MON_Slime(gp);
		gp.monster[0].worldX = gp.tileSize*20;
		gp.monster[0].worldY = gp.tileSize*16;
		
		
		gp.monster[1] = new MON_Slime(gp);
		gp.monster[1].worldX = gp.tileSize*22;
		gp.monster[1].worldY = gp.tileSize*17;
		
		gp.monster[2] = new MON_Slime(gp);
		gp.monster[2].worldX = gp.tileSize*24;
		gp.monster[2].worldY = gp.tileSize*15;
		
		gp.monster[3] = new MON_Slime(gp);
		gp.monster[3].worldX = gp.tileSize*13;
		gp.monster[3].worldY = gp.tileSize*38;
		
		gp.monster[4] = new MON_Slime(gp);
		gp.monster[4].worldX = gp.tileSize*20;
		gp.monster[4].worldY = gp.tileSize*38;
		
		gp.monster[5] = new MON_Slime(gp);
		gp.monster[5].worldX = gp.tileSize*13;
		gp.monster[5].worldY = gp.tileSize*32;
		
		gp.monster[6] = new MON_Slime(gp);
		gp.monster[6].worldX = gp.tileSize*37;
		gp.monster[6].worldY = gp.tileSize*13;
		
		gp.monster[7] = new MON_Slime(gp);
		gp.monster[7].worldX = gp.tileSize*37;
		gp.monster[7].worldY = gp.tileSize*27;
		
		gp.monster[8] = new MON_Slime(gp);
		gp.monster[8].worldX = gp.tileSize*35;
		gp.monster[8].worldY = gp.tileSize*37;
		
		gp.monster[9] = new MON_Slime(gp);
		gp.monster[9].worldX = gp.tileSize*35;
		gp.monster[9].worldY = gp.tileSize*38;
		
		gp.monster[10] = new MON_Slime(gp);
		gp.monster[10].worldX = gp.tileSize*36;
		gp.monster[10].worldY = gp.tileSize*38;
	}
}






