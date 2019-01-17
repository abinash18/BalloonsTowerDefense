package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;
import static other.DrawInFrame.*;

public enum MonkeyTowerType {

	DartMonkey(new Texture[] { LoadTexture("DartMonkey") }, DartType.NormalDart, 10, 271, 1, 50), NinjaMonkey(
			new Texture[] { LoadTexture("NinjaMonkey") }, DartType.NinjaStar, 10, 271, 1,
			200), SuperMonkey(new Texture[] { getTexture("SuperMonkey") }, DartType.NormalDart, 10, 271, 0.4f, 3000);

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