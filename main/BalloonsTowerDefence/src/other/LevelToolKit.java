/**
 * Abinash Singh 
 * Balloons Tower Defense LevelToolKit 
 */
package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import balloonsTowerDefence.Floor;
import balloonsTowerDefence.FloorGrid;
import balloonsTowerDefence.FloorType;

/**
 * Tool kit for Saving, Creating and Loading maps into the Floor Grid
 */
public class LevelToolKit {

	public static void saveMap(String mapName, FloorGrid grid) {
		String map = "";

		for (int y = 0; y < grid.getSquaresWide(); y++) {
			for (int x = 0; x < grid.getSquaresHigh(); x++) {
				map += getFloorID(grid.getFloor(y, x));
			}
		}
		try {
			File f = new File(mapName + ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(map);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FloorGrid LoadMap(String mapName) {

		FloorGrid grid = new FloorGrid();

		if (new File("test.txt").exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(mapName + ".txt"));
				String data = br.readLine();
				for (int y = 0; y < grid.getSquaresWide(); y++) {
					for (int x = 0; x < grid.getSquaresHigh(); x++) {
						grid.setFloor(y, x, getFloorType(
								data.substring(y * grid.getSquaresHigh() + x, y * grid.getSquaresHigh() + x + 1)));
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				new File("test.txt").createNewFile();
			} catch (IOException e) {
				LoadMap("test.txt");
				e.printStackTrace();
			}
		}

		return grid;

	}

	public static FloorType getFloorType(String ID) {

		FloorType type = FloorType.Null;
		switch (ID) {
		case "0":
			type = FloorType.Grass;
			break;
		case "1":
			type = FloorType.Path;
			break;
		case "2":
			type = FloorType.Water;
			break;
		case "3":
			type = FloorType.Null;
			break;
		}

		return type;

	}

	public static String getFloorID(Floor f) {

		String ID = "E";
		switch (f.getFloorType()) {
		case Grass:
			ID = "0";
			break;
		case Path:
			ID = "1";
			break;
		case Water:
			ID = "2";
			break;
		case Null:
			ID = "2";
			break;
		}
		return ID;
	}

}
