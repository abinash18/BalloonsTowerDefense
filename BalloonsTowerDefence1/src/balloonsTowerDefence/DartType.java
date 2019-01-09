package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum DartType {
	NormalDart(LoadTexture("bullet"), 3, 1200);

	Texture texture;
	int damage;
	float speed;

	private DartType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}
