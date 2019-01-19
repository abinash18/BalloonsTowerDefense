package userInterface;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import other.DrawInFrame;

public class Label {

	private TrueTypeFont font;
	private Font awtFont, temp;
	private int x, y, xOffset = 0, yOffset = 0;
	Texture bg = null;
	private String text, name;

	public Label(String name, String fontName, int fontsize, String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.name = name;
		InputStream in = ResourceLoader.getResourceAsStream("resources/fonts/" + fontName + ".ttf");
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, in);
			System.out.println("done");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		awtFont = temp.deriveFont(Font.BOLD, fontsize);
		this.font = new TrueTypeFont(awtFont, false);
	}

	public Label(String name, String font, int fontsize, String text, int x, int y, boolean bg) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.name = name;
		if (bg) {
			this.bg = DrawInFrame.LoadTexture("black");
		}

		InputStream in = ResourceLoader.getResourceAsStream("resources/fonts/" + font + ".ttf");
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, in);
			System.out.println("done");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		awtFont = temp.deriveFont(Font.BOLD, fontsize);
		this.font = new TrueTypeFont(awtFont, false);
	}
	
	public Label(String name, String font, int fontsize, String text, int x, int y, int xOffset, int yOffset, boolean bg) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.name = name;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		if (bg) {
			this.bg = DrawInFrame.LoadTexture("black");
		}

		InputStream in = ResourceLoader.getResourceAsStream("resources/fonts/" + font + ".ttf");
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, in);
			System.out.println("done");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		awtFont = temp.deriveFont(Font.BOLD, fontsize);
		this.font = new TrueTypeFont(awtFont, false);
	}

	public void tick() {
		draw();
	}

	public void draw() {
		if (bg != null) {
			DrawInFrame.DrawQuadWithTexture(bg, x + xOffset, y + yOffset, font.getWidth(text), font.getHeight());
		}
		font.drawString(x + xOffset, y + yOffset, text);
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrueTypeFont getFont() {
		return font;
	}

	public void setFont(TrueTypeFont font) {
		this.font = font;
	}

	public Font getAwtFont() {
		return awtFont;
	}

	public void setAwtFont(Font awtFont) {
		this.awtFont = awtFont;
	}

	public Font getTemp() {
		return temp;
	}

	public void setTemp(Font temp) {
		this.temp = temp;
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

	public Texture getBg() {
		return bg;
	}

	public void setBg(Texture bg) {
		this.bg = bg;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
