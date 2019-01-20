/**
 * Abinash Singh	
 * Balloons Tower Defense Dart 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.WIDTH;
import static other.DrawInFrame.isCollided;
import static other.Timer.Delta;

import org.newdawn.slick.opengl.Texture;

public abstract class Dart implements Entity {

	// Texture for the dart
	private Texture texture;
	// the entity interface requirements and this objects properties
	private float x, y, speed, xVelocity, yVelocity;
	public float angle;
	private int damage, width, height;
	// the balloon object this dart should be hitting and traveling too
	private Balloon target;
	// if this object is alive if the system should be updating or not
	private boolean alive, causeDamageCalled = false;
	// the default type of the dart
	private DartType dartType;

	/**
	 * constructor creates an instance of a dart to travel though the playing field
	 * with the dart type and target and x, y, width, height variable to determine
	 * the atributes of this object
	 */
	public Dart(DartType dartType, Balloon target, float x, float y) {
		this.dartType = dartType;
		this.target = target;
		this.x = x + GRID_SQUARE_SIZE / 2; // ensures that the dart is luanched from the center of the grids tile
		this.y = y + GRID_SQUARE_SIZE / 2;
		this.width = dartType.texture.getImageWidth();
		this.height = dartType.texture.getImageHeight();
		this.texture = dartType.texture;
		this.damage = dartType.damage;
		this.speed = dartType.speed;
		this.alive = true;
		this.xVelocity = 0f; // the x axis movement
		this.yVelocity = 0f; // the y axis movement
		this.angle = calculateAngle();

		// determines the direction the dart should be traveling
		findDirection();
	}

	/**
	 * Determines the direction the dart should be traveling by doing some
	 * trigonometry
	 */
	private void findDirection() {

		// cap for the total combined velocity
		float totalAllowedMovement = 1.0f;
		// determines the distance between the target and the dart
		float xDistanceFromTarget = Math.abs(target.getX() - x - GRID_SQUARE_SIZE / 4 + GRID_SQUARE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - GRID_SQUARE_SIZE / 4 + GRID_SQUARE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		// this works with y but i just followed a math tutorial online so...
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		// x velocity
		xVelocity = xPercentOfMovement;
		// this makes sure that the max directional movement cap
		// is what the sum of the Velocities is 1
		yVelocity = totalAllowedMovement - xPercentOfMovement;

		// if the target is behind the dart then set the velocity to be going backwards
		if (target.getX() < x) {
			xVelocity *= -1;
		}
		// If the target id above the dart then set the velocity to be going up
		if (target.getY() < y) {

			yVelocity *= -1;

		}

	}

	private float calculateAngle() {
		// Ineededtusewikipedia to get this i
		// did not learn this in math and needed
		// to use a couplle youtube tutorials to
		// find out how to do the math in java
		// aswell thank fully one had the exact
		// way of doing this
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);

		return (float) Math.toDegrees(angleTemp) + 90;
	}

	/**
	 * Causes damage to the target if hit and sets itself to not alive
	 */
	public void damage() {
		// if (!causeDamageCalled) { // ensure it is only called once
		target.damage(damage);
		alive = false;
		// causeDamageCalled = true;
		// }
	}

	public void tick() {

		// ensures that if the dart hasnt hit the target and leaves the frame it is not
		// updated

		if (alive) {
			if (x < 0 || x > WIDTH || y < 0 || y > HEIGHT) {
				alive = false;
				System.out.println("ads");
			}
			// findDirection();
			x += xVelocity * Delta() * speed;
			y += yVelocity * Delta() * speed;
			if (target.isAlive()) {
				if (isCollided(x, y, width, height, target.getX(), target.getY(), target.getWidth(),
						target.getHeight())) {
					damage();
				}
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
