package balloonsTowerDefence;

import static other.DrawInFrame.*;
import static other.Timer.*;

import org.newdawn.slick.opengl.Texture;

import other.LoadSettings;
import other.StateManager;
import other.StateManager.GameState;
import userInterface.ProgressBar;
import userInterface.UserInterface;

public class SplashScreenLoading {

	private Texture loadingBg, barTex, barBorder;
	private int loadingTime, tasks;
	private double barProgress, tasksCompleted, barWidth;
	private UserInterface UI;
	private ProgressBar prgLoading;
	private LoadSettings settings;
	private boolean isDone = false;

	public SplashScreenLoading() {
		this.tasks = 4;
		this.tasksCompleted = 0;
		this.loadingBg = LoadTexture("red_tint");
		this.loadingTime = 15;
		this.barWidth = 1350;
		this.barTex = LoadTexture("slider_bar");
		this.barBorder = LoadTexture("progressBar_border");
		this.UI = new UserInterface();
		// UI.addProgressBar("prgLoading", barTex, barBorder, loadingBg, 50, HEIGHT -
		// HEIGHT / 8 + 4, barProgress * 90, 16);
		this.settings = new LoadSettings();
		settings.LoadFile();

	}

	public void tick() {
		if (tasksCompleted == tasks) {
			//StateManager.setState(GameState.MAINMENU);
		} else {
			if (settings.isDone()) {
				if (settings.isFileLoaded()) {
					tasksCompleted++;
				}
				if (settings.isTexturesDone()) {
					tasksCompleted++;
				}
				if (settings.isTowersDone()) {
					tasksCompleted++;
				}
				if (settings.isGameSettingsDone()) {
					tasksCompleted++;
				}
				barProgress = (float)(tasksCompleted * barWidth) / tasks;
				draw();
				
			}
			
		}
	}

	private void tickUI() {
		UI.drawString(50, 50, "progress: " + barProgress);
	}

	private void draw() {
		DrawQuadWithTexture(loadingBg, 0, 0, WIDTH, HEIGHT);
		DrawQuadWithTexture(barBorder, 50, HEIGHT - HEIGHT / 8, 1350, 25);
		DrawQuadWithTexture(barTex, 50, HEIGHT - HEIGHT / 8 + 4, (float) barProgress, 16);
		UI.drawString(50, 50, "progress: " + barProgress);
	}

}
