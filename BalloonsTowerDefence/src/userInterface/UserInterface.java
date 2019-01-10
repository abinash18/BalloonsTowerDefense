/**
 * Abinash Singh 
 * Balloons Tower Defence UserInterface 
 */
package userInterface;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class UserInterface {
	// Stores the instance of all buttons in the current interface
	private ArrayList<Button> ListOfButtons;
	private ArrayList<ProgressBar> ListOfProgressBars;
	private ArrayList<Menu> ListOfMenus;
	private TrueTypeFont font;
	private Font awtFont;

	/**
	 * Constructor pre: none post: ListOfButtons is initialized with an empty
	 * ArrayList
	 */
	public UserInterface() {
		ListOfButtons = new ArrayList<Button>();
		ListOfMenus = new ArrayList<Menu>();
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}

	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}

	/**
	 * AddButton adds a button object to the button array list pre: none post: A
	 * button object is Created and added to the arrayList with the texture loaded
	 * using LoadTexture
	 */
	public void addButton(String nameOfButton, String buttonTexture, int xPosition, int yPosition) {
		ListOfButtons.add(new Button(LoadTexture(buttonTexture), nameOfButton, xPosition, yPosition));
	}

	/**
	 * AddButton adds a button object to the button array list pre: none post: A
	 * button object is Created and added to the arrayList with the texture loaded
	 * using LoadTexture
	 */
	public void addButton(String nameOfButton, Texture buttonTexture, int xPosition, int yPosition, int width,
			int height) {
		ListOfButtons.add(new Button(buttonTexture, nameOfButton, xPosition, yPosition, width, height));
	}

	public void addProgressBar(String name, Texture prgTex, Texture prgBorederTex, Texture prgBgTex, int x, int y, float width, float height){
		ListOfProgressBars.add(new ProgressBar(name, prgTex, prgBorederTex, prgBgTex, x, y, width, height));
	}
	
	public ProgressBar getProgressBar(String nameOfPrg) {

		// Loops through the Array list and trys to match the nameOfbutton with button
		// name
		for (ProgressBar prg : ListOfProgressBars) {
			// If the name matches this returns the button
			if (prg.getName().equals(nameOfPrg)) {
				return prg;
			}
		}
		// Returns Null
		return null;
	}
	
	/**
	 * Checks if the button has been clicked by the user by tracking the mouse pre:
	 * none; post: true is returned if the button has been clicked Otherwise false
	 * is returned
	 */
	public boolean isButtonClicked(String nameOfButton) {

		Button button = getButton(nameOfButton);
		float mouseY = HEIGHT - Mouse.getY() - 1; // Gets the y position of the mouse in the window subtracts 1 due to
													// it giving the 1 greater value

		// If the mouse x pos is greater than or to the right of the buttons x
		if (Mouse.getX() > button.getX()
				// If the X pos of mouse is less than the max of the buttonX plus width
				&& Mouse.getX() < button.getX() + button.getWidth()
				// If the mouse y pos is grater than the button y pos
				&& mouseY > button.getY()
				// If the mouse pos y is less than the buttons y pos plus its height which is
				// its Max
				&& mouseY < button.getY() + button.getHeight()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the button object that has the name in parameter pre: none post: The
	 * button Object is Returned With the name Matching one provided otherwise null
	 * has been returned
	 */
	public Button getButton(String nameOfButton) {

		// Loops through the Array list and trys to match the nameOfbutton with button
		// name
		for (Button button : ListOfButtons) {
			// If the name matches this returns the button
			if (button.getName().equals(nameOfButton)) {
				return button;
			}
		}
		// Returns Null
		return null;
	}

	public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
		ListOfMenus.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight));
	}

	public Menu getMenu(String name) {
		for (Menu m : ListOfMenus) {
			if (name.equals(m.getNameOfMenu())) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Draws the buttons on screen using the DrawInFrame class pre: none post: the
	 * button has been drawn on the screen
	 */
	public void drawOnScreen() {

		// Loops through ListOfButtons and draws each one
		for (Button button : ListOfButtons) {
			DrawQuadWithTexture(button.getTexture(), button.getX(), button.getY(), button.getWidth(),
					button.getHeight());
		}

		for (Menu menu : ListOfMenus) {
			menu.draw();
		}
		
//		for (ProgressBar prg : ListOfProgressBars) {
//			prg.tick();
//		}

	}

	public class Menu {

		private ArrayList<Button> ListOfButtonsInMenu;
		private ArrayList<Slider> ListOfSlidersInMenu;
		private int x, y, elementsWidth, elementsHeight, width, height, buttonAmount, padding, topPadding;
		String nameOfMenu;

		public Menu(String nameOfMenu, int x, int y, int width, int height, int buttonsWidth, int buttonsHeight) {
			this.ListOfButtonsInMenu = new ArrayList<Button>();
			this.ListOfSlidersInMenu = new ArrayList<Slider>();
			this.x = x;
			this.y = y;
			this.nameOfMenu = nameOfMenu;
			this.padding = (width - (buttonsWidth * GRID_SQUARE_SIZE)) / (buttonsWidth + 1);
			this.topPadding = 20;
			this.elementsWidth = buttonsWidth;
			this.elementsHeight = buttonsHeight;
			this.width = width;
			this.height = height;
			this.buttonAmount = 0;
		}

		public void addMenuButton(Button b) {
			setButton(b);
		}

		public void addMenuButton(String buttonName, String texName) {
			Button b = new Button(LoadTexture(texName), buttonName, 0, 0);
			setButton(b);
		}

		private Button getButton(String name) {

			for (Button b : ListOfButtonsInMenu) {

				if (b.getName().equals(name)) {
					return b;
				}
			}
			return null;
		}
		
		private void setButton(Button b) {
			if (elementsWidth != 0) {
				b.setY(y + (buttonAmount / elementsWidth) * GRID_SQUARE_SIZE);
			}
			b.setX(x + (buttonAmount % 2) * (padding + GRID_SQUARE_SIZE) + padding);
			buttonAmount++;
			ListOfButtonsInMenu.add(b);
		}

		public boolean isButtonClicked(String buttonName) {

			Button b = getButton(buttonName);
			float mouseY = HEIGHT - Mouse.getY() - 1;

			if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY()
					&& mouseY < b.getY() + b.getHeight()) {

				return true;

			}
			return false;
		}

		public void addMenuSlider(String sliderName, String barTexture, String pointerTexture, int minVal, int maxVal,
				int step, float pointerPos) {
			Slider s = new Slider(LoadTexture(barTexture), LoadTexture(pointerTexture), sliderName, minVal, maxVal,
					step, 0, 0);
			setSlider(s);
		}

		private void setSlider(Slider s) {
			if (elementsWidth != 0) {
				s.setY(y + (buttonAmount / elementsWidth) * GRID_SQUARE_SIZE);
			}
			s.setX(x + (buttonAmount % 2) * (padding + GRID_SQUARE_SIZE) + padding);
			buttonAmount++;
			ListOfSlidersInMenu.add(s);
		}

		public Slider getSlider(String name) {

			for (Slider s : ListOfSlidersInMenu) {

				if (s.getSliderName().equals(name)) {
					return s;
				}
			}
			return null;
		}

		public void draw() {
			for (Button b : ListOfButtonsInMenu) {
				DrawQuadWithTexture(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}

			for (Slider s : ListOfSlidersInMenu) {
				s.tick();
			}

		}

		public ArrayList<Button> getListOfButtonsInMenu() {
			return ListOfButtonsInMenu;
		}

		public void setListOfButtonsInMenu(ArrayList<Button> listOfButtonsInMenu) {
			ListOfButtonsInMenu = listOfButtonsInMenu;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
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

		public int getButtonAmount() {
			return buttonAmount;
		}

		public void setButtonAmount(int buttonAmount) {
			this.buttonAmount = buttonAmount;
		}

		public String getNameOfMenu() {
			return nameOfMenu;
		}

		public void setNameOfMenu(String nameOfMenu) {
			this.nameOfMenu = nameOfMenu;
		}

	}
}
