package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum DartType {
	NormalDart(getTexture("dart_monkey_dart"), 3, 1200, 0), NinjaStar(LoadTexture("ninjaMonkey_starNormal"), 3, 1200, 2),
	Lazer(getTexture("supermonkey_laser_vision_projectile"), 10, 1200, 0);

	Texture texture;
	int damage, rotationRate;
	float speed;

	private DartType(Texture texture, int damage, float speed, int rotationRate) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}
