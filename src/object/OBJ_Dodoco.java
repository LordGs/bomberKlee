package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Dodoco extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Dodoco(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Oculus";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/dodoco.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
