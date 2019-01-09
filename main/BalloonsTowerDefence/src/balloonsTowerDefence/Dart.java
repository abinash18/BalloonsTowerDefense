/**
 * Abinash Singh	
 * Balloons Tower Defense Dart 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.isCollided;
import static other.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

public abstract class Dart implements Entity {

	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Balloon target;
	private boolean alive;
	private DartType dartType;

	public Dart(DartType dartType, Balloon target, float x, float y, int width, int height) {
		this.dartType = dartType;
		this.target = target;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = dartType.texture;
		this.damage = dartType.damage;
		this.speed = dartType.speed;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		findDirection();
	}

	private void findDirection() {

		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x - GRID_SQUARE_SIZE / 4 + GRID_SQUARE_SIZE / 2); // gets
																												// //
																												// math
		float yDistanceFromTarget = Math.abs(target.getY() - y - GRID_SQUARE_SIZE / 4 + GRID_SQUARE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement; // this makes sure that the max directional movement cap
																// is what the sum of the Velocities is 1
		if (target.getX() < x) {
			xVelocity *= -1;
		}

		if (target.getY() < y) {

			yVelocity *= -1;

		}

	}

	public void damage() {
		target.damage(damage);
		alive = false;
	}

	public void tick() {

		if (alive) {
			findDirection();
			x += xVelocity * Delta() * speed;
			y += yVelocity * Delta() * speed;
			if (isCollided(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
				damage();
			}
			draw();
		}

	}

	public void draw() {
		DrawQuadWithTexture(texture, x, y, width, height);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public float getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	public Balloon getTarget() {
		return target;
	}

	public void setTarget(Balloon target) {
		this.target = target;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public DartType getDartType() {
		return dartType;
	}

	public void setDartType(DartType dartType) {
		this.dartType = dartType;
	}

}
