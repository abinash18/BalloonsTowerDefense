package balloonsTowerDefence;

import static other.DrawInFrame.*;
import static other.DrawInFrame.WIDTH;

import userInterface.UserInterface;

public class Instructions {

	private int x, y, width, height;
	private UserInterface UI;

	public Instructions() {
		this.x = 0;
		this.y = 0;
		this.width = WIDTH;
		this.height = HEIGHT;
		this.UI = new UserInterface();
	}

	public void tick() {
		draw();
	}
	
	private void draw() {
		
	}
	
}
