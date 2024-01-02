package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_DodocoCollectible extends Entity{
	
	
	public OBJ_DodocoCollectible(GamePanel gp) {
		
		super(gp);
		
		name = "Key";
		down1 = setup("/objects/dodoco", gp.tileSize, gp.tileSize);

	}
	
}
