package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{

	public OBJ_Heart(GamePanel gp) {

		super(gp);
	
		name = "Heart";
		
		image = setupHeart("/objects/fullHeart");
		image2 = setupHeart("/objects/halfHeart");
		image3 = setupHeart("/objects/noHeart");
		
	}
}
