package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum DartType {
	NormalDart(LoadTexture("bullet"), 3, 1200, 0),
	NinjaStar(LoadTexture("ninjaMonkey_starNormal"), 3, 1200, 2);

	Texture texture;
	int damage, rotationRate;
	float speed;

	private DartType(Texture texture, int damage, float speed, int rotationRate) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}

