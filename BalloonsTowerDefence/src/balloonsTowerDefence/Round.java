package balloonsTowerDefence;

import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.Timer.Delta;

import java.util.concurrent.CopyOnWriteArrayList;

public class Round {

	private float timeSinceLastSpawn, spawnTime;
	private Balloon[] balloonTypes;
	private CopyOnWriteArrayList<Balloon> balloonsList;
	private int balloonsPerWave, balloonsSpawned;
	private float speedForBalloon;
	private boolean roundCompleted;

	public Round(Balloon[] balloonTypes, float spawnTime, int balloonsPerWave) {
		this.balloonTypes = balloonTypes;
		this.spawnTime = spawnTime;
		this.balloonsPerWave = balloonsPerWave;
		//this.speedForBalloon = balloonTypes.getSpeed();
		this.balloonsList = new CopyOnWriteArrayList<Balloon>();
		this.balloonsSpawned = 0;
		this.timeSinceLastSpawn = 0;
		this.roundCompleted = false;
		spawn();
	}

	public void tick() {
		boolean allBalloonsPopped = true; // set by default to true every time it is called
		if (balloonsSpawned < balloonsPerWave) {
			timeSinceLastSpawn += Delta();
			if (timeSinceLastSpawn >= spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}
		for (Balloon balloon : balloonsList) {
			if (balloon.isAlive()) {
				allBalloonsPopped = false; // even if one enemy is alive then is set to false and if none are alivve it
										// stays true
				balloon.tick();
				balloon.draw();
			} else {
				balloonsList.remove(balloon);
			}
		}
		if (allBalloonsPopped) {
			roundCompleted = true; // becouse it loops over every update and sets the allEnemys Dead value to true
									// if it is left true which means all of them are dead then it sets
									// wavecompleted to true
		}
	}

	private void spawn() {
		
		int randomBalloonIndex;
		
		randomBalloonIndex = (int)(Game.MAX_BALLOON_TYPES * Math.random() + 0);
		
		// Apocalypse mode setting just for testing
		balloonsList
				.add(new BalloonRed(balloonTypes[randomBalloonIndex].getInstanceTexture(), balloonTypes[randomBalloonIndex].getStartTile(), balloonTypes[1].getGrid(),
						GRID_SQUARE_SIZE, GRID_SQUARE_SIZE, balloonTypes[randomBalloonIndex].getSpeed(), balloonTypes[randomBalloonIndex].getHealth()));
		balloonsSpawned++;
	}

	public float getTimeSinceLastSpawn() {
		return timeSinceLastSpawn;
	}

	public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
		this.timeSinceLastSpawn = timeSinceLastSpawn;
	}

	public float getSpawnTime() {
		return spawnTime;
	}

	public void setSpawnTime(float spawnTime) {
		this.spawnTime = spawnTime;
	}

	public Balloon[] getBalloonTypes() {
		return balloonTypes;
	}

	public void setBalloonTypes(Balloon[] balloonTypes) {
		this.balloonTypes = balloonTypes;
	}

	public CopyOnWriteArrayList<Balloon> getBalloonsList() {
		return balloonsList;
	}

	public void setBalloonsList(CopyOnWriteArrayList<Balloon> balloonsList) {
		this.balloonsList = balloonsList;
	}

	public int getBalloonsPerWave() {
		return balloonsPerWave;
	}

	public void setBalloonsPerWave(int balloonsPerWave) {
		this.balloonsPerWave = balloonsPerWave;
	}

	public int getBalloonsSpawned() {
		return balloonsSpawned;
	}

	public void setBalloonsSpawned(int balloonsSpawned) {
		this.balloonsSpawned = balloonsSpawned;
	}

	public float getSpeedForBalloon() {
		return speedForBalloon;
	}

	public void setSpeedForBalloon(float speedForBalloon) {
		this.speedForBalloon = speedForBalloon;
	}

	public boolean isRoundCompleted() {
		return roundCompleted;
	}

	public void setRoundCompleted(boolean roundCompleted) {
		this.roundCompleted = roundCompleted;
	}

}
