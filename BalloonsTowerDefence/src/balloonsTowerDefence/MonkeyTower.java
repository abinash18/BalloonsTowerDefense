package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithRotatedTexture;
import static other.DrawInFrame.DrawQuadWithTexture;
import static other.Timer.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public abstract class MonkeyTower implements Entity {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, damage, range;
	public Balloon Target;
	private Texture[] textures;
	private CopyOnWriteArrayList<Balloon> balloons;
	public ArrayList<Dart> darts;
	public MonkeyTowerType type;
	private boolean targeted;

	public MonkeyTower(MonkeyTowerType type, Floor startingFloor, CopyOnWriteArrayList<Balloon> balloons) {
		this.x = startingFloor.getxPos();
		this.y = startingFloor.getyPos();
		this.width = startingFloor.getWidth();
		this.height = startingFloor.getHeight();
		// this.target = target;
		this.timeSinceLastShot = 0f;
		this.darts = new ArrayList<Dart>();
		this.damage = type.damage;
		this.textures = type.textures;
		this.balloons = balloons;
		this.targeted = false;
		this.range = type.range;
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;
		this.type = type;
	}

	public void tick() {

		if (!targeted) {
			Target = acquireTarget();
		} else if (timeSinceLastShot > firingSpeed) {
			angle = calculateAngle();
			shoot(Target);
			timeSinceLastShot = 0;
		}
		if (Target == null || Target.isAlive() == false) {
			targeted = false;
		}

		timeSinceLastShot += Delta();

		for (Dart dart : darts) {
			dart.tick();
		}

		draw();
	}

	public void draw() {
		DrawQuadWithTexture(textures[0], x, y, width, height);
//		if (textures.length > 1) {
//			for (int i = 1; i < textures.length; i++) {
		DrawQuadWithRotatedTexture(textures[1], x, y, width, height, angle);
//			}
//		}
//		
	}

	private float findDistance(Balloon balloons) {
		float xDistance = Math.abs(balloons.getX() - x);
		float yDistance = Math.abs(balloons.getY() - y);
		return xDistance + yDistance;
	}

	public Balloon acquireTarget() {

		Balloon closest = null;
		float closestDistance = 1000;
		
		for (Balloon balloons : balloons) {
			if (isInRange(balloons) && findDistance(balloons) < closestDistance && balloons.getHiddenHealth() > 0) {
				closestDistance = findDistance(balloons);
				closest = balloons;
			}
		}
		if (closest != null) {
			targeted = true;
		}
		
		return closest;

	}

	private boolean isInRange(Balloon balloon) {
		float xDistance = Math.abs(balloon.getX() - x);
		float yDistance = Math.abs(balloon.getY() - y);

		if (xDistance < range && yDistance < range) {
			return true;
		}
		return false;
	}

	public abstract void shoot(Balloon target);

	public void reUpdateTargetList(CopyOnWriteArrayList<Balloon> newList) {
		balloons = newList;
	}

	private float calculateAngle() {

		double angleTemp = Math.atan2(Target.getY() - y, Target.getX() - x); // I needed to use wikipedia to get this i
																				// did not learn this in math and needed
																				// to use a couplle youtube tutorials to
																				// find out how to do the math in java
																				// aswell thank fully one had the exact
																				// way of doing this
		return (float) Math.toDegrees(angleTemp) - 180;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getTimeSinceLastShot() {
		return timeSinceLastShot;
	}

	public void setTimeSinceLastShot(float timeSinceLastShot) {
		this.timeSinceLastShot = timeSinceLastShot;
	}

	public float getFiringSpeed() {
		return firingSpeed;
	}

	public void setFiringSpeed(float firingSpeed) {
		this.firingSpeed = firingSpeed;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Balloon getTarget() {
		return Target;
	}

	public void setTarget(Balloon target) {
		Target = target;
	}

	public Texture[] getTextures() {
		return textures;
	}

	public void setTextures(Texture[] textures) {
		this.textures = textures;
	}

	public CopyOnWriteArrayList<Balloon> getBalloons() {
		return balloons;
	}

	public void setBalloons(CopyOnWriteArrayList<Balloon> balloons) {
		this.balloons = balloons;
	}

	public ArrayList<Dart> getDarts() {
		return darts;
	}

	public void setDarts(ArrayList<Dart> darts) {
		this.darts = darts;
	}

	public MonkeyTowerType getType() {
		return type;
	}

	public void setType(MonkeyTowerType type) {
		this.type = type;
	}

	public boolean isTargeted() {
		return targeted;
	}

	public void setTargeted(boolean targeted) {
		this.targeted = targeted;
	}
}
