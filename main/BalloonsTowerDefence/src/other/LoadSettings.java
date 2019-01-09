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

	public LoadSettings(){
		texturePathElements = new ArrayList<Element>();
	}
	
	private void LoadFile() {

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
				}
			}
			
			Node gameSettings = document.getElementById("GameSettings");
			NodeList nList = document.getElementsByTagName("Setting");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				System.out.println(""); // Just a separator
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// Print each employee's detail
					Element eElement = (Element) node;
					texturePathElements.add(eElement);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
