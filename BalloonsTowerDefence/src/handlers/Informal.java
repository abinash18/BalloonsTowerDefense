package handlers;

import javax.swing.JOptionPane;

public class Informal {

	public static void showInstructions(){
		
		try {
			JOptionPane.showMessageDialog(null, "Instructions", "alert", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
