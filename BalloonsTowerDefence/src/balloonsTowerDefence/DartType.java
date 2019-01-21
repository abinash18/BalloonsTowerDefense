/**
 * Abinash Singh
 * Balloons Tower Defense Dart Types
 */
package balloonsTowerDefence;

import static other.DrawInFrame.LoadTexture;
import static other.DrawInFrame.getTexture;

import org.newdawn.slick.opengl.Texture;

/**
 * This class contains default values for all dart types
 */
public enum DartType {
	NormalDart(getTexture("dart_monkey_dart"), 3, 1200, 0),
	NinjaStar(getTexture("ninja_throwing_star"), 3, 1200, 2),
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
