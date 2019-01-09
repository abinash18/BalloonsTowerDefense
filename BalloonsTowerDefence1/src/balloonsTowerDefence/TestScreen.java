package balloonsTowerDefence;

import static other.DrawInFrame.*;

import userInterface.Slider;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class TestScreen {

	private UserInterface UI;
	private Menu test;
	private Slider testSlider;
	
	public TestScreen() {
		this.UI = new UserInterface();
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
		DrawQuadWithColor(255, 100, 10, 0, 0, 2000, 2000);
		UI.drawString(0, 0, "Value: " + testSlider.getPointerPos());
		test.draw();
	}
	
}
