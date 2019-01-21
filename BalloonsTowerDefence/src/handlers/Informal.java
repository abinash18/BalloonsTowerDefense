package handlers;

import javax.swing.JOptionPane;

public class Informal {

	public static void showInstructions() {

		try {
			JOptionPane.showMessageDialog(null, "Instructions", "alert", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void message(String text) {
		JOptionPane.showMessageDialog(null, text, "Informal", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
