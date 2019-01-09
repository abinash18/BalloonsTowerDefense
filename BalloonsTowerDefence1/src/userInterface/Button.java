package userInterface;

import org.newdawn.slick.opengl.Texture;

public class Button {
	
	private Texture texture;
	private String name;
	private int x, y, width, height; // The Width and Height is taken from the texture it self
	
	/**
	 * Constructor
	 * pre: none
	 * post: This constructore takes a width and height just 
	 * incase if the game ressolution is changed and the textures
	 * are to big and need to resized
	 * The texture is set along with the name of the button and x and y positions
	 * The x and y are relative to the top left corner of the texture
	 */
	public Button(Texture texture, String name, int x, int y, int width, int height) {
		this.texture = texture;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructor
	 * pre: none
	 * post: This constructore dose not take a width and height 
	 * they are accuired straight from the teexture using methods provided in the slick-util liberary
	 * The texture is set along with the name of the button and x and y positions
	 * The x and y are relative to the top left corner of the texture
	 */
	public Button(Texture texture, String name, int x, int y) {
		super();
		this.texture = texture;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
	}

	/**
	 * Generated Getters and Setters 
	 * For all Variables 
	 * Im just taking advantage of the great tools provided by Eclipse,
	 * Not being Lazy 
	 */	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
