package other;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadSettings {

	private static final String SETTINGS_FILE_NAME = "src/xml/settings.xml";
	private static ArrayList<Element> texturePathElements;
	private static ArrayList<Element> towerElements;
	private static ArrayList<Element> settings;
	private boolean isTexturesDone = false, isTowersDone = false, isGameSettingsDone = false, isDone = false,
			isFileLoaded = false;

	public LoadSettings() {
		texturePathElements = new ArrayList<Element>();
		towerElements = new ArrayList<Element>();
		settings = new ArrayList<Element>();
	}

	public void LoadFile() {

		try {
			// Load File
			File settingsFile = new File(SETTINGS_FILE_NAME);

			// Get Document Builder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Build Document
			Document document = builder.parse(settingsFile);

			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			isFileLoaded = true;
			// the root node
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			// Get all nodes for the settings
			Node main = document.getElementById("main");
			Node textures = document.getElementById("textures");
			NodeList nList = document.getElementsByTagName("TexturePath");

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				System.out.println(""); // Just a separator
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// Print each employee's detail
					Element eElement = (Element) node;
					texturePathElements.add(eElement);
					System.out.println(eElement.getAttribute("name"));
				}
			}
			isTexturesDone = true;
			// Node tower = document.getElementById("Towers");
			NodeList towers = document.getElementsByTagName("Tower");

			for (int i = 0; i < towers.getLength(); i++) {
				Node node = towers.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					towerElements.add(eElement);
				}
			}
			isTowersDone = true;
			Node gameSettings = document.getElementById("GameSettings");
			NodeList settingsNodeList = document.getElementsByTagName("Setting");

			for (int i = 0; i < settingsNodeList.getLength(); i++) {
				Node node = settingsNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					settings.add(eElement);
				}
			}
			isGameSettingsDone = true;
			isDone = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Element> getTexturePathElements() {
		return texturePathElements;
	}

	public static ArrayList<Element> getTowerElements() {
		return towerElements;
	}

	public static ArrayList<Element> getSettings() {
		return settings;
	}

	public boolean isTexturesDone() {
		return isTexturesDone;
	}

	public boolean isTowersDone() {
		return isTowersDone;
	}

	public boolean isGameSettingsDone() {
		return isGameSettingsDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public boolean isFileLoaded() {
		return isFileLoaded;
	}

}
