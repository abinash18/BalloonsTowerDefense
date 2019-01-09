package balloonsTowerDefence;

import static other.DrawInFrame.*;

import org.newdawn.slick.opengl.Texture;

import userInterface.Slider;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class TestScreen {

	private UserInterface UI;
	private Menu test;
	private Slider testSlider;
	private Texture bg;
	
	public TestScreen() {
		this.UI = new UserInterface();
		this.bg = LoadTexture("red_tint");
		UI.createMenu("test", 0, 0, 2000, 500, 1, 0);
		test = UI.getMenu("test");
		
		test.addMenuSlider("slider", "slider_bar", "slider_pointer", 0, 100, 1, 0);
		
		testSlider = test.getSlider("slider");
		
		
	}
	
	public void tick() {
		draw();
	}
	
	private void updateTest() {
		
		
	}
	
	private void draw() {
		DrawQuadWithTexture(bg, 0, 0, WIDTH, HEIGHT);
		UI.drawString(0, 0, "Value: " + testSlider.getPointerValue());
		UI.drawString(50, 0, "Position x: " + testSlider.getPointerPos());
		test.draw();
	}
	
}
