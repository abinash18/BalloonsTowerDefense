package balloonsTowerDefence;

import java.io.Serializable;

public class HighScore implements Serializable {

	private String name, mapName;
	private int score, round;
	private float timeElasped;
	
	public HighScore(String name, String mapName, int round, float timeElasped){
		
		this.name = name;
		this.mapName = mapName;
		this.round = round;
		this.timeElasped = timeElasped;
		
		calculateScore();
		
		
	}
	
	private void calculateScore(){
		
		score = (int) (round * Math.floor(timeElasped));
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public float getTimeElasped() {
		return timeElasped;
	}

	public void setTimeElasped(float timeElasped) {
		this.timeElasped = timeElasped;
	}
	
}
