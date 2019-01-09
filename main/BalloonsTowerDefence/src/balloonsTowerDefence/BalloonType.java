package balloonsTowerDefence;

public enum BalloonType {

	// The null value for type next is so that the game knows to pop the balloon
	// instead of spawning one in its place thats next
	RedBalloon("balloon_red", 1, 200, 0, null), 
	// After this balloon is the red balloon
	BlueBalloon("chocolateCookie", 1, 200, 0, BalloonType.RedBalloon);

	String textureName;
	int health, speed, regenRate;
	BalloonType nextBalloonType;

	BalloonType(String texture, int health, int speed, int regenRate, BalloonType typeNext) {

		this.textureName = texture;
		this.health = health;
		this.speed = speed;
		this.regenRate = regenRate;
		this.nextBalloonType = typeNext;

	}

}
