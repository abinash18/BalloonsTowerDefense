/**
 * Abinash Singh  
 * Balloons Tower Defense Round Manager
 */
package balloonsTowerDefence;

/**
 * Handles all rounds and balloons in those rounds
 */
public class RoundManager {

	private float timeBetweenRounds, spawnTime;
	private int currentRoundNumber, balloonsPerRound, balloonsThisRound;
	private Balloon[] balloonTypes;
	// The current round the player is on
	private Round currentRound;

	/**
	 * constructor Creates a round manager object with the types of balloons that
	 * can be in the round and the initial balloon amount and spawn time
	 */
	public RoundManager(Balloon[] balloonTypes, int balloonsPerRound, float spawnTime) {
		this.balloonTypes = balloonTypes;
		this.balloonsPerRound = balloonsPerRound;
		this.timeBetweenRounds = 0;
		this.currentRoundNumber = 0;
		this.spawnTime = spawnTime;
		this.currentRound = null;

		this.balloonsThisRound = currentRoundNumber * balloonsPerRound * 50;

		// newRound();

	}

	/**
	 * Creates a new round
	 */
	public void beginRound() {
		newRound();
	}

	/**
	 * Updates the current round every time the game loops
	 */
	public void tick() {

		// if (!currentRound.isRoundCompleted()) {
		if (currentRound != null) {
			currentRound.tick();
		}
		// else {
		// newRound(); // Right when the last wave ends the new wave is created
		// }

	}

	/**
	 * Creates a new round inrements the round counter and also calculates the
	 * amount of balloons this specific round
	 */
	private void newRound() {
		balloonsThisRound = currentRoundNumber * balloonsPerRound * 5;
		currentRound = new Round(balloonTypes, 0.3f, balloonsThisRound);
		currentRoundNumber++;

		System.out.println("Begining Round: " + currentRoundNumber);
	}

	/**
	 * Generated Getters and Setters
	 */

	public float getTimeBetweenRounds() {
		return timeBetweenRounds;
	}

	public void setTimeBetweenRounds(float timeBetweenRounds) {
		this.timeBetweenRounds = timeBetweenRounds;
	}

	public int getBalloonsThisRound() {
		return balloonsThisRound;
	}

	public void setBalloonsThisRound(int balloonsThisRound) {
		this.balloonsThisRound = balloonsThisRound;
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
