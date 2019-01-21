package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Element;
import org.w3c.dom.css.Counter;

import static other.DrawInFrame.*;
import other.LevelToolKit;
import other.LoadSettings;
import userInterface.UserInterface;

public class MapSelectScreen {

	FloorGrid[] grid = new FloorGrid[LoadSettings.maps.size()];
	private int gm = 0, map = 0;
	Texture bg = getTexture("red_tint"), button = getTexture("button_notClicked");
	UserInterface UI;
	
	public MapSelectScreen() {
		UI = new UserInterface();
		UI.addButton("Normal", button, 50, 50, button.getImageWidth(), button.getImageHeight());
		int counter = 0;
		for (Element e : LoadSettings.maps) {
			grid[counter] = LevelToolKit.LoadMap(e.getAttribute("name"));
			counter++;
		}
	}

	public void tick() {
		
		draw();
	}

	private void draw() {
		DrawQuadWithTexture(bg, 0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < grid.length; i++) {
			grid[i].drawGridOnScreenMapPreview(25, 100 * i * 5, 100);
		}
		UI.drawOnScreen();
		
	}

}
