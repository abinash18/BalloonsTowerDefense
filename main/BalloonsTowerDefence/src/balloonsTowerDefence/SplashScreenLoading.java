package balloonsTowerDefence;

import static other.DrawInFrame.*;
import static other.Timer.*;

import org.newdawn.slick.opengl.Texture;

import other.StateManager;
import other.StateManager.GameState;
import userInterface.UserInterface;

public class SplashScreenLoading {

	private Texture loadingBg, barTex, barBorder;
	private int loadingTime;
	private float barProgress;
	private UserInterface UI;
	
	public SplashScreenLoading() {
		this.loadingBg = LoadTexture("red_tint");
		this.loadingTime = 15;
		this.barTex = LoadTexture("slider_bar");
		this.barBorder = LoadTexture("progressBar_border");
		this.UI = new UserInterface();
		UI.addProgressBar(barTex, barBorder, loadingBg, 50, HEIGHT - HEIGHT / 8 + 4, barProgress * 90, 16);
	}

	public void tick() {

		barProgress += Delta();

		if (barProgress >= loadingTime) {
			StateManager.setState(GameState.MAINMENU);
		} else {
			draw();
		}
	}

	private void tickUI(){
		UI.
		UI.drawString(50, 50, "progress: " + barProgress);
	}
	
	private void draw() {
		
		
		
		DrawQuadWithTexture(loadingBg, 0, 0, WIDTH, HEIGHT);
		DrawQuadWithTexture(barBorder, 50, HEIGHT - HEIGHT / 8, 1350, 25);
		DrawQuadWithTexture(barTex, 50, HEIGHT - HEIGHT / 8 + 4, barProgress * 90, 16);
		UI.drawString(50, 50, "progress: " + barProgress);
	}

}
