package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;
import static other.DrawInFrame.WIDTH;
import static other.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

import other.LoadSettings;
import other.StateManager;
import other.StateManager.GameState;
import userInterface.Label;
import userInterface.ProgressBar;
import userInterface.UserInterface;

public class SplashScreenLoading {

	private Texture loadingBg, barTex, barBorder;
	private int loadingTime, tasks, tasksCompleted, randomSprite = 0;
	private double barProgress, barWidth;
	private long elaspedtime;
	private UserInterface UI;
	private Label progress;
	private ProgressBar prgLoading;
	private LoadSettings settings;
	private boolean isDone = false, isTexturePathLoadedcalled = false, isFileLoadedcalled = false,
			isTexturesLoadedcalled = false, first = true;
	public static String barText;
	// private Thread taskThread;

	public SplashScreenLoading() {
		this.tasks = 4;
		this.elaspedtime = 0;
		this.tasksCompleted = 0;
		this.loadingBg = LoadTexture("red_tint");
		// Just to show off all the cool textures i just loaded in
		this.loadingTime = 5000;
		this.barWidth = 1350;
		this.barTex = LoadTexture("LoadingBarRedFill");
		this.barBorder = LoadTexture("LoadingBarDashedBorder");
		this.UI = new UserInterface();

		UI.addLabel("prog", "oztype", 25, "", 50, HEIGHT - HEIGHT / 8 - 30, false);
		progress = UI.getLabel("prog");
		// UI.addProgressBar("prgLoading", barTex, barBorder, loadingBg, 50, HEIGHT -
		// HEIGHT / 8 + 4, barProgress * 90, 16);

//		taskThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
		LoadSettings.LoadFileXML();

//			}
//		});
//		
//		taskThread.start();

	}

	public void tick() {
		// UI.drawOnScreen();
		// loadingTime += Delta() * 100;
		// System.out.println(loadingTime);
		// if (loadingTime >= 5) {
		// loadingTime = 0;
		System.out.println(elaspedtime);
		if (tasksCompleted == tasks && elaspedtime >= loadingTime) {
			StateManager.setState(GameState.GAME);
		} else {

			barProgress = (float) (tasksCompleted * barWidth) / tasks;

			switch (tasksCompleted) {

			case 0:
				barText = tasksCompleted + "/" + tasks + " Loading...";
				draw();
				tasksCompleted++;
				break;

			case 1:
				if (LoadSettings.isFileLoaded && !isFileLoadedcalled) {
					tasksCompleted++;
					isFileLoadedcalled = true;
					barText = tasksCompleted + "/" + tasks + " Loading XML File...";
				} else if (!LoadSettings.isFileLoaded) {
					// taskThread = new Thread(new Runnable() {
					// @Override
					// public void run() {
					LoadSettings.LoadFileXML();
					// }
					// });
					// taskThread.start();
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
					// elaspedtime++;
//					if (!first) {
//						randomSprite = (int) (Math.random() * LoadSettings.sprites.size()) + 0;
//					}
					// MakeFrame.setFps(Integer.parseInt(LoadSettings.getGameSetting("fps").getNodeValue()));
				} else if (!LoadSettings.isTexturesDone) {
//						taskThread = new Thread(new Runnable() {
//							@Override
//							public void run() {
					LoadSettings.LoadSpritesIntoArray();
					first = false;
//							}
//						});
//						taskThread.start();
				}
				break;
			case 4:
				elaspedtime += Delta() * 1000;
				break;
			}
		}
		// }
		barProgress = (float) (tasksCompleted * barWidth) / tasks;
		draw();
		// first = false;
		System.out.println(tasksCompleted);

	}

	private void tickUI() {
		// UI.drawString(50, 50, "progress: " + barProgress);
		progress.setText(barText);
		UI.drawOnScreen();
	}

	private void draw() {

		DrawQuadWithTexture(loadingBg, 0, 0, WIDTH, HEIGHT);
		DrawQuadWithTexture(barBorder, 50, HEIGHT - HEIGHT / 8, 1350, 50);
		DrawQuadWithTexture(barTex, 50, HEIGHT - HEIGHT / 8 + 9, (float) barProgress, 32);
		if (!first) {
			DrawQuadWithTexture(LoadSettings.sprites.get(randomSprite).getTex(),
					progress.getFont().getWidth(progress.getText()) + 50, HEIGHT - HEIGHT / 8 - 75, GRID_SQUARE_SIZE,
					GRID_SQUARE_SIZE);
			randomSprite = (int) (Math.random() * LoadSettings.sprites.size()) + 0;
			// first = false;
		}
		// UI.drawString(WIDTH / 2 - 125, HEIGHT / 2 + 4, barText);
		tickUI();
	}

}
