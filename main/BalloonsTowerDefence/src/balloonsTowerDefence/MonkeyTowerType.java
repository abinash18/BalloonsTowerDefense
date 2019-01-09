package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum MonkeyTowerType {

	DartMonkey(new Texture[] { LoadTexture("tower_base"), LoadTexture("tower_gun") }, DartType.NormalDart, 10, 1000, 0.3f, 50);

	Texture[] textures;
	DartType dartType;
	int damage, range, cost;
	float firingSpeed;

	MonkeyTowerType(Texture[] textures, DartType dartType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.dartType = dartType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}

}
