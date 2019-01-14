package balloonsTowerDefence;

import static other.DrawInFrame.*;

import org.newdawn.slick.opengl.Texture;

import other.DrawInFrame;
import other.ImageTools;
import other.LoadSettings;
import userInterface.Slider;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class TestScreen {

	
	private UserInterface UI;
	private Menu test;
	private Slider testSlider;
	private Texture sprite, spriteBg;
	
	public TestScreen() {
//		this.UI = new UserInterface();
//		this.bg = LoadTexture("red_tint");
//		UI.createMenu("test", 0, 0, 2000, 500, 1, 0);
//		test = UI.getMenu("test");
//		
//		test.addMenuSlider("slider", "slider_bar", "slider_pointer", 0, 100, 1, 0);
//		
//		testSlider = test.getSlider("slider");
		
	//	LoadSettings.LoadFileXML();
		sprite = getTexture("apprentice_avatar_dragon_breath_small");
		spriteBg = ImageTools.LoadSpriteTextureFromSpriteSheet("red_tint", "In_Game_Custom");
	}

	public void tick() {
		draw();
		
	}

	private void updateTest() {

	}

	private void draw() {
		DrawQuadWithTexture(spriteBg, 0, 0, DrawInFrame.WIDTH, DrawInFrame.HEIGHT);
		DrawQuadWithTexture(sprite, 0, 0, sprite.getImageWidth(), sprite.getImageHeight());
		
//		DrawQuadWithTexture(bg, 0, 0, WIDTH, HEIGHT);
//		UI.drawString(0, 0, "Value: " + testSlider.getPointerValue());
//		UI.drawString(50, 0, "Position x: " + testSlider.getPointerPos());
//		test.draw();

	}

}
