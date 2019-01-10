package balloonsTowerDefence;

import java.util.ArrayList;

import userInterface.UserInterface;

import java.io.*;

public class Leaderboard implements Serializable {

	private ArrayList<HighScore> scores;
	private File dataFile;
	private int numberOfScores;
	private UserInterface leaderboardUI;

	public Leaderboard() {

		this.scores = new ArrayList<HighScore>();
		this.dataFile = new File("scores.txt");
		this.numberOfScores = 0;
		writeTestScore();
		loadScores();
		
		
		this.leaderboardUI = new UserInterface();
	}

	public void tick() {
		draw();
	}

	private void writeTestScore() {

		try {
			if (dataFile.exists()) {
				ArrayList<HighScore> temphs = new ArrayList<HighScore>();
//				FileInputStream in = new FileInputStream(dataFile);
//				ObjectInputStream readScore = new ObjectInputStream(in);
//				HighScore hs;
//				while ((hs = (HighScore) readScore.readObject()) != null) {
//					temphs.add(hs);
//				}
				
				FileOutputStream out = new FileOutputStream(dataFile, true);
				ObjectOutputStream outObject = new ObjectOutputStream(out);

				outObject.writeObject(new HighScore("132", "sdsa", 1, 123f));
				outObject.writeObject(new HighScore("w", "sdsa", 2, 123f));
				outObject.writeObject(new HighScore("f", "sdsa", 3, 123f));
				
				outObject.close();
				out.close();
			} else {
				dataFile.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadScores() {

		try {

			FileInputStream in = new FileInputStream(dataFile);
			ObjectInputStream readScore = new ObjectInputStream(in);
			HighScore hs;
			while ((hs = (HighScore) readScore.readObject()) != null) {
				numberOfScores += 1;
				scores.add(hs);
			}
			readScore.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void draw() {
		
		leaderboardUI.drawString(50, 100, "#: " + numberOfScores);

		for (HighScore hs : scores){
			System.out.println(numberOfScores);
			leaderboardUI.drawString(0 + numberOfScores * 2, 0, "HighScore: " + hs.getName() + " " + hs.getScore());
		}
		
	}

}
