package balloonsTowerDefence;

import org.w3c.dom.Element;
import org.w3c.dom.css.Counter;

import other.LevelToolKit;
import other.LoadSettings;

public class MapSelectScreen {

	FloorGrid[] grid = new FloorGrid[LoadSettings.maps.size()];

	public MapSelectScreen() {
		int counter = 0;
		for (Element e : LoadSettings.maps) {
			grid[counter] = LevelToolKit.LoadMap(e.getAttribute("name"));
			counter++;
		}
	}

	public void tick() {
		for (int i = 0; i < grid.length; i++) {
			grid[i].drawGridOnScreenMapPreview(25);
		}
		draw();
	}

	private void draw() {

	}

}
