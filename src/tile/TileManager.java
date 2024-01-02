package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[100];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/island.txt");
	}
	
	public void getTileImage() {	
		
//			setup(0, "dirtBottomLeft", false);
//			setup(1, "dirtBottomRight", false);
//			setup(2, "dirtEndBottom", false);
//			setup(3, "dirtEndLeft", false);
//			setup(4, "dirtEndRight", false);
//			setup(5, "dirtEndTop", false);
//			setup(6, "dirtFull", false);
//			setup(7, "dirtFullBottom", false);
//			setup(8, "dirtFullLeft", false);
//			setup(9, "dirtFullLeftBottom", false);
//			setup(10, "dirtFullLeftTop", false);
//			setup(11, "dirtFullMidTop", false);
//			setup(12, "dirtFullRight", false);
//			setup(13, "dirtFullRightBottom", false);
//			setup(14, "dirtFullRightTop", false);
//			setup(15, "dirtHorizontal", false);
//			setup(16, "dirtLeftTop", false);
//			setup(17, "dirtRightTop", false);
//			setup(18, "dirtVertical", false);
//			setup(19, "fenceHorizontal", true);
//			setup(20, "fenceLeftBottom", true);
//			setup(21, "fenceLeftTop", true);
//			setup(22, "fenceRightBottom", true);
//			setup(23, "fenceRightTop", true);
//			setup(24, "fenceVertical", true);
//			setup(25, "grassTexture", false);
//			setup(26, "mainGrass", false);
//			setup(27, "mainWater", true);
//			setup(28, "mainWater_01", true);
//			setup(29, "rock", true);
//			setup(30, "tree", true);
//			setup(31, "waterBottomLeft", true);
//			setup(32, "waterBottomMid", true);
//			setup(33, "waterBottomRight", true);
//			setup(34, "waterCornerLeftBottom", true);
//			setup(35, "waterCornerRightBottom", true);
//			setup(36, "waterCornerRightTop", true);
//			setup(37, "waterCornerTopLeft", true);
//			setup(38, "waterMiddleLeft", true);
//			setup(39, "waterMidRight", true);
//			setup(40, "waterTopLeft", true);
//			setup(41, "waterTopMid", true);
//			setup(42, "waterTopRight", true);
		
		setup(0, "tile_01", false);
		setup(1, "tile_02", false);
		setup(2, "tile_03", false);
		setup(3, "tile_04", false);
		setup(4, "tile_05", false);
		setup(5, "tile_06", true);
		setup(6, "tile_07", true);
		setup(7, "tile_08", true);
		setup(8, "tile_09", true);
		setup(9, "tile_10", true);
		setup(10, "tile_11", false);
		setup(11, "tile_12", false);
		setup(12, "tile_13", false);
		setup(13, "tile_14", false);
		setup(14, "tile_15", false);
		setup(15, "tile_16", true);
		setup(16, "tile_17", true);
		setup(17, "tile_18", true);
		setup(18, "tile_19", true);
		setup(19, "tile_20", true);
		setup(20, "tile_21", false);
		setup(21, "tile_22", false);
		setup(22, "tile_23", false);
		setup(23, "tile_24", true);
		setup(24, "tile_25", true);
		setup(25, "tile_26", true);
		setup(26, "tile_27", true);
		setup(27, "tile_28", true);
		setup(28, "tile_29", true);
		setup(29, "tile_30", false);
		setup(30, "tile_31", false);
		setup(31, "tile_32", false);
		setup(32, "tile_33", false);
		setup(33, "tile_34", true);
		setup(34, "tile_35", true);
		setup(35, "tile_36", true);
		setup(36, "tile_37", false);
		setup(37, "tile_38", false);
		setup(38, "tile_39", false);
		setup(39, "tile_40", false);
		setup(40, "tile_41", false);
		setup(41, "tile_42", false);
		setup(42, "tile_43", false);
		setup(43, "tile_44", true);
		setup(44, "tile_45", true);
		setup(45, "tile_46", true);
		setup(46, "tile_47", false);
		setup(47, "tile_48", false);
		setup(48, "tile_49", false);
		setup(49, "tile_50", false);
		setup(50, "tile_51", true);
		setup(51, "tile_52", true);


		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/island/" + imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {

		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				
			}
			
			
			worldCol++;
			
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		
	}
	
}
