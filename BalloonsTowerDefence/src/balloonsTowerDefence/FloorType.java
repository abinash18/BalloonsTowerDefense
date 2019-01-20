/**
 * Abinash Singh
 * Balloons Tower Defense FloorType
 */
package balloonsTowerDefence;

/**
 * Floor type contains the default values for all the floor types
 */
public enum FloorType {

	Grass("grass", true), Path("path", false), Water("water", true), Null("water", false);

	String textureName;
	boolean builds;

	FloorType(String texName, boolean builds) {

		this.textureName = texName;
		this.builds = builds;

	}

}
