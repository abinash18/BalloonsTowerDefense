package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;
import static other.DrawInFrame.WIDTH;
import static other.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

import other.LoadSettings;
import other.StateManager;
import other.StateManager.GameState;
import userInterface.ProgressBar;
import userInterface.UserInterface;

public class SplashScreenLoading {

	private Texture loadingBg, barTex, barBorder;
	private int loadingTime, tasks, tasksCompleted;
	private double barProgress, barWidth;
	private UserInterface UI;
	private ProgressBar prgLoading;
	private LoadSettings settings;
	private boolean isDone = false, isTexturePathLoadedcalled = false, isFileLoadedcalled = false,
			isTexturesLoadedcalled = false;
	public static String barText;

	public SplashScreenLoading() {
		this.tasks = 4;
		this.tasksCompleted = 0;
		this.loadingBg = LoadTexture("red_tint");
		this.loadingTime = 0;
		this.barWidth = 1350;
		this.barTex = LoadTexture("slider_bar");
		this.barBorder = LoadTexture("progressBar_border");
		this.UI = new UserInterface();
		// UI.addProgressBar("prgLoading", barTex, barBorder, loadingBg, 50, HEIGHT -
		// HEIGHT / 8 + 4, barProgress * 90, 16);
		LoadSettings.LoadFileXML();

	}

	public void tick() {

	//	loadingTime += Delta() * 100;
	//	System.out.println(loadingTime);
		//if (loadingTime >= 5) {
			loadingTime = 0;
			if (tasksCompleted == tasks) {
				StateManager.setState(GameState.MAINMENU);
			} else {

				barProgress = (float) (tasksCompleted * barWidth) / tasks;

				switch (tasksCompleted) {
				
				case 0: barText = tasksCompleted + "/" + tasks + " Loading..."; draw(); tasksCompleted++; break;
				
				case 1:
					if (LoadSettings.isFileLoaded && !isFileLoadedcalled) {
						tasksCompleted++;
						isFileLoadedcalled = true;
						barText = tasksCompleted + "/" + tasks + " Loading XML File...";
					} else if (!LoadSettings.isFileLoaded) {
						LoadSettings.LoadFileXML();
					}
					break;

				case 2:
					if (LoadSettings.isTexturePathsDone && !isTexturePathLoadedcalled) {
						tasksCompleted++;
						isTexturePathLoadedcalled = true;
						barText = tasksCompleted + "/" + tasks + " Loading Textures...";
					} else if (!LoadSettings.isTexturePathsDone) {
						LoadSettings.LoadTexturePathElementsIntoArray();
					}
					break;
				case 3:
					if (LoadSettings.isTexturesDone && !isTexturesLoadedcalled) {
						tasksCompleted++;
						isTexturesLoadedcalled = true;
						barText = tasksCompleted + "/" + tasks + " Creating Sprites...";
					} else if (!LoadSettings.isTexturesDone) {
						LoadSettings.LoadSpritesIntoArray();
					}
					break;
				}
			}
		//}
		barProgress = (float) (tasksCompleted * barWidth) / tasks;
		draw();

		System.out.println(tasksCompleted);

	}

	private void tickUI() {
		UI.drawString(50, 50, "progress: " + barProgress);
	}

	private void draw() {
		DrawQuadWithTexture(loadingBg, 0, 0, WIDTH, HEIGHT);
		DrawQuadWithTexture(barBorder, 50, HEIGHT - HEIGHT / 8, 1350, 25);
		DrawQuadWithTexture(barTex, 50, HEIGHT - HEIGHT / 8 + 4, (float) barProgress, 16);
		UI.drawString(50, 50, barText);
	}

}
