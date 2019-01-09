package balloonsTowerDefence;

public class RoundManager {

	private float timeBetweenRounds, spawnTime;
	private int currentRoundNumber, balloonsPerRound;
	private Balloon[] balloonTypes;
	private Round currentRound;

	public RoundManager(Balloon[] balloonTypes, int balloonsPerRound, float spawnTime) {
		this.balloonTypes = balloonTypes;
		this.balloonsPerRound = balloonsPerRound;
		this.timeBetweenRounds = 0;
		this.currentRoundNumber = 0;
		this.spawnTime = spawnTime;
		this.currentRound = null;

		newRound();

	}

	public void tick() {

		if (!currentRound.isRoundCompleted()) {
			currentRound.tick();
		} else {
			newRound(); // Right when the last wave ends the new wave is created
		}

	}

	private void newRound() {
		currentRound = new Round(balloonTypes, balloonsPerRound * 0.3f, balloonsPerRound * currentRoundNumber);
		currentRoundNumber++;
		System.out.println("Begining Round: " + currentRoundNumber);
	}

	public float getTimeBetweenRounds() {
		return timeBetweenRounds;
	}

	public void setTimeBetweenRounds(float timeBetweenRounds) {
		this.timeBetweenRounds = timeBetweenRounds;
	}

	public float getSpawnTime() {
		return spawnTime;
	}

	public void setSpawnTime(float spawnTime) {
		this.spawnTime = spawnTime;
	}

	public int getCurrentRoundNumber() {
		return currentRoundNumber;
	}

	public void setCurrentRoundNumber(int currentRoundNumber) {
		this.currentRoundNumber = currentRoundNumber;
	}

	public int getBalloonsPerRound() {
		return balloonsPerRound;
	}

	public void setBalloonsPerRound(int balloonsPerRound) {
		this.balloonsPerRound = balloonsPerRound;
	}

	public Balloon[] getBalloonTypes() {
		return balloonTypes;
	}

	public void setBalloonTypes(Balloon[] balloonTypes) {
		this.balloonTypes = balloonTypes;
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

}
