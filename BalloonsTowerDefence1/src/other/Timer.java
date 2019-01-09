/**
 * Abinash Singh
 * Balloons Tower Defense Timer 
 */
package other;

import org.lwjgl.Sys;

public class Timer {

	private static boolean GamePaused = false; // is the game paused
	public static long lastGameFrame, totalTime;
	public static float delt = 0, timeMultiplier = 1; // Time multiplier is for to fast forward the game it multiplies
														// the delta and then that speeds up all the objects

	/**
	 * The current time since the game started is calculated
	 */
	public static long GrabTime() {
		long tempTime;
		// Gets time from Sys package
		tempTime = Sys.getTime() * 1000 / Sys.getTimerResolution(); // * 1000 becouse time is in miliseconds
		// returns the time
		return (tempTime);
	}

	/**
	 * Returns the delta to move everything pre: none post: If the game var isPaused
	 * is not true then the Delta is returned Otherwise delta is returned
	 */
	public static float Delta() {
		if (GamePaused) {
			return 0;
		} else {
			// the game will speed up when multiplied by the timeMultiplier
			return delt * timeMultiplier;
		}
	}

	/**
	 * Updates the delta pre: none post: The totaltime since the game started is
	 * calculated and delta is grabbed
	 */
	public static void Tick() {
		delt = grabDelta();
		totalTime += delt;
	}

	/**
	 * Calculates the difference in time pre: none. post: The delta is returned.
	 */
	public static float grabDelta() {
		long timeRightNow = GrabTime(); // the time now is gotten
		int delta = (int) (timeRightNow - lastGameFrame); // the difference is calculated
		lastGameFrame = GrabTime(); // the last game frame is updated to the current time
		// System.out.println(delta * 0.01f);
		if (delta * 0.001f > 0.05f) { // check to get the accurate value of delta in seconds
			return 0.05f;
		}
		// Returns the delta in seconds
		return delta * 0.001f;
	}

	/**
	 * Sets the timeMultiplier pre: none post: the timeMultiplier is changed to the
	 * one supplied
	 */
	public static void setTimeMultiplier(float changeInMult) {
		/*
		 * Checks if the change is less than 10 and not equal to zero or -1 and is less
		 * than that
		 */
		if ((timeMultiplier + changeInMult) < -1 && (timeMultiplier + changeInMult) > 10) {
			// Magic
		} else {
			timeMultiplier += changeInMult;
		}
	}

	/**
	 * Sets the game to a paused or un paused state pre: none post: paused is set to
	 * true if is false or vice versa
	 */
	public static void setGamePaused() {
		if (GamePaused) {
			GamePaused = false;
		} else {
			GamePaused = true;
		}
	}

	/**
	 * Getters for vars
	 */
	public static float grabTotalTime() {
		return (totalTime);
	}

	public static float grabTimeMultiplier() {
		return (timeMultiplier);
	}
}
