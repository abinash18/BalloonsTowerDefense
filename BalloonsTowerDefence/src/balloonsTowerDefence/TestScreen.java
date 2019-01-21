package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.DrawQuadWithTextureReflected;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.WIDTH;
import static other.DrawInFrame.getTexture;

import org.newdawn.slick.opengl.Texture;

import other.DrawInFrame;
import other.ImageTools;
import other.LevelToolKit;
import userInterface.Slider;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

/**
 * Test class 
 */
public class TestScreen {

	private UserInterface UI;
	private Menu test;
	private Slider testSlider;
	private Texture sprite, spriteBg;
	private FloorGrid grid;

	public TestScreen() {
//		this.UI = new UserInterface();
//		this.bg = LoadTexture("red_tint");
//		UI.createMenu("test", 0, 0, 2000, 500, 1, 0);
//		test = UI.getMenu("test");
//		
//		test.addMenuSlider("slider", "slider_bar", "slider_pointer", 0, 100, 1, 0);
//		
//		testSlider = test.getSlider("slider");

		// LoadSettings.LoadFileXML();
		grid = LevelToolKit.LoadMap("dfg");
		sprite = getTexture("grass");
		spriteBg = ImageTools.LoadSpriteTextureFromSpriteSheet("red_tint", "In_Game_Custom");
	}

	public void tick() {
		draw();
		//grid.drawGridOnScreenMapPreview(32);
	}

	private void updateTest() {

	}

	private void draw() {
		DrawQuadWithTexture(spriteBg, 0, 0, DrawInFrame.WIDTH, DrawInFrame.HEIGHT);
		DrawQuadWithTexture(sprite, HEIGHT / 2, WIDTH / 2, sprite.getImageWidth(), sprite.getImageHeight());

//		DrawQuadWithTexture(bg, 0, 0, WIDTH, HEIGHT);
//		UI.drawString(0, 0, "Value: " + testSlider.getPointerValue());
//		UI.drawString(50, 0, "Position x: " + testSlider.getPointerPos());
//		test.draw();

	}

}
