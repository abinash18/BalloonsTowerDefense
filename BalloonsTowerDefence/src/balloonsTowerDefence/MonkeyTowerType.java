package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum MonkeyTowerType {

	DartMonkey(new Texture[] { LoadTexture("DartMonkey") }, DartType.NormalDart, 10, 1000, 1,
			50), NinjaMonkey(new Texture[] { LoadTexture("NinjaMonkey") }, DartType.NinjaStar, 10, 1000, 1, 200);

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