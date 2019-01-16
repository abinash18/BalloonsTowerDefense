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
	//private Thread taskThread;

	public SplashScreenLoading() {
		this.tasks = 4;
		this.tasksCompleted = 0;
		this.loadingBg = LoadTexture("red_tint");
		this.loadingTime = 0;
		this.barWidth = 1350;
		this.barTex = LoadTexture("LoadingBarRedFill");
		this.barBorder = LoadTexture("LoadingBarDashedBorder");
		this.UI = new UserInterface();
		
		//UI.addLabel("name", "oztype", 25, "test", 90, 90, true);
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
		UI.drawOnScreen();
	//	loadingTime += Delta() * 100;
	//	System.out.println(loadingTime);
		//if (loadingTime >= 5) {
			loadingTime = 0;
			if (tasksCompleted == tasks) {
				StateManager.setState(GameState.TESTSCREEN);
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
						//taskThread = new Thread(new Runnable() {
						//	@Override
					//		public void run() {
								LoadSettings.LoadFileXML();
					//		}
					//	});
					//	taskThread.start();
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
					//	MakeFrame.setFps(Integer.parseInt(LoadSettings.getGameSetting("fps").getNodeValue()));
					} else if (!LoadSettings.isTexturesDone) {
//						taskThread = new Thread(new Runnable() {
//							@Override
//							public void run() {
								LoadSettings.LoadSpritesIntoArray();
//							}
//						});
//						taskThread.start();
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
		DrawQuadWithTexture(barBorder, 50, HEIGHT / 2, 1350, 25);
		DrawQuadWithTexture(barTex, 50, HEIGHT / 2 + 4, (float) barProgress, 16);
		UI.drawString(WIDTH / 2 - 125, HEIGHT / 2 + 4, barText);
	}

}
