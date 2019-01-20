/**
 * Abinash Singh
 * Balloons Tower Defense Entity Interface 
 */
package balloonsTowerDefence;

/**
 * This interface contains needed methods for a object to move on the screen
 */
public interface Entity {
	public float getX();

	public float getY();

	public int getHeight();

	public int getWidth();

	public void setX(float x);

	public void setY(float y);

	public void setHeight(int height);

	public void setWidth(int width);

	public void tick();

	public void draw();
}
