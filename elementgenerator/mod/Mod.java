package elementgenerator.mod;

import json.object.*;

import elementgenerator.element.Element;
import elementgenerator.util.ModUtils;

/**
 * A Mod class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Mod {
	
	//Static Fields
	
	public static final String DEFAULT_NAME = "";
	public static final Authors DEFAULT_AUTHOR = new Authors();
	public static final String DEFAULT_VERSION = "0.0.1";
	public static final MinecraftVersion DEFAULT_MC_VERSION = new MinecraftVersion(); 
	public static final String DEFAULT_CREDITS = "";
	public static final String DEFAULT_URL = "";
	public static final String DEFAULT_UPDATE_URL = "";
	public static final String DEFAULT_DESCRIPTION = "";
	
	//Instance Fields
	private String name;
	private Authors author;
	private String version;
	private MinecraftVersion minecraftVersion;
	private String credits;
	private String url;
	private String updateUrl;
	private String description;
	private int creativeTabIconIndex;
	
	private Element[] elements;
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 * @param description the description of the mod
	 * @param credits the credits given for the mod
	 * @param url the url for the mod
	 * @param updateUrl the update url for the mod
	 * @param elements an array of elements
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion, String description, String credits, String url, String updateUrl, Element... elements) {
		this.name = name;
		this.author = author;
		this.version = version;
		this.minecraftVersion = minecraftVersion;
		this.creativeTabIconIndex = 0;
		this.description = description;
		this.credits = credits;
		this.url = url;
		this.updateUrl = updateUrl;
		this.elements = elements;
	}
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 * @param description the description of the mod
	 * @param credits the credits given for the mod
	 * @param url the url for the mod
	 * @param updateUrl the update url for the mod
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion, String description, String credits, String url, String updateUrl) {
		this(name, author, version, minecraftVersion, description, credits, url, updateUrl, new Element[0]);
	}
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 * @param description the description of the mod
	 * @param credits the credits given for the mod
	 * @param url the url for the mod
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion, String description, String credits, String url) {
		this(name, author, version, minecraftVersion, description, credits, url, DEFAULT_UPDATE_URL);
	}
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 * @param description the description of the mod
	 * @param credits the credits given for the mod
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion, String description, String credits) {
		this(name, author, version, minecraftVersion, description, credits, DEFAULT_URL);
	}
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 * @param description the description of the mod
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion, String description) {
		this(name, author, version, minecraftVersion, description, DEFAULT_CREDITS);
	}
	
	/**
	 * Creates a new Mod object
	 * @param name the name of the mod
	 * @param author the author of the mod
	 * @param version the version of the mod
	 * @param minecraftVersion the minecraft version the mod runs on
	 */
	public Mod(String name, Authors author, String version, MinecraftVersion minecraftVersion) {
		this(name, author, version, minecraftVersion, DEFAULT_DESCRIPTION);
	}
	
	/**
	 * Creates a new Mod object
	 */
	public Mod() {
		this(DEFAULT_NAME, DEFAULT_AUTHOR, DEFAULT_VERSION, DEFAULT_MC_VERSION);
	}
	
	/**
	 * Sets the name of the mod
	 * @param name name of the mod
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the author of the mod
	 * @param author author of the mod
	 */
	public void setAuthor(Authors author) {
		this.author = author;
	}
	
	/**
	 * Sets the version of the mod
	 * @param version version of the mod
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Sets the minecraft version the mod runs on
	 * @param minecraftVersion minecraft version
	 */
	public void setMinecraftVersion(MinecraftVersion minecraftVersion) {
		this.minecraftVersion = minecraftVersion;
	}
	
	/**
	 * Sets the index of the creative tab icon for the mod
	 * @param creativeTabIconIndex the index for the icon
	 */
	public void setCreativeTabIconIndex(int creativeTabIconIndex) {
		this.creativeTabIconIndex = creativeTabIconIndex;
	}
	
	/**
	 * Sets the description of the mod
	 * @param description description of the mod
	 */
	public void setDescription(String description) {
		this.description = description.replace("~", "\n");
	}
	
	/**
	 * Sets the credits of the mod
	 * @param credits credits of the mod
	 */
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	/**
	 * Sets the URL of the mod
	 * @param url URL of the mod
	 */
	public void setURL(String url) {
		this.url = url;
	}
	
	/**
	 * Sets tge update URL of the mod
	 * @param updateUrl update URL of the mod
	 */
	public void setUpdateURL(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	
	/**
	 * Sets the elements to an existing Element array
	 * @param elements an array of Elements
	 */
	public void setElements(Element[] elements) {
		this.elements = elements;
	}
	
	/**
	 * Adds a new Element to the mod
	 * @param element the Element to add
	 */
	public void addElement(Element element) {
		Element[] newArray = new Element[elements.length + 1];
		for(int i = 0; i < elements.length; i++)
			newArray[i] = elements[i];
		newArray[newArray.length - 1] = element;
		elements = newArray.clone();
	}
	
	/**
	 * Adds an array of elements to the mod
	 * @param elements an array of Elements to add
	 */
	public void addElements(Element[] elements) {
		for(int i = 0; i < elements.length; i++)
			addElement(elements[i]);
	}
	
	/**
	 * Removes all Elements that equal the name given
	 * @param name the name of the Element(s) to remove
	 */
	public void removeElement(String name) {
		Element[] newArray = new Element[elements.length - 1];
		int subtract = 0;
		for(int i = 0; i < elements.length; i++) {
			if(elements[i].getName().equalsIgnoreCase(name))
				subtract += 1;
			else
				newArray[i - subtract] = elements[i];
		}
		elements = newArray.clone();
	}
	
	/**
	 * Returns the name of the mod
	 * @return String
	 */
	public String getName() {
		return ModUtils.titleCase(name);
	}
	
	/**
	 * Returns the ID version of the mod name
	 * @return String
	 */
	public String getModId() {
		return ModUtils.underscoreCase(name, false);
	}
	
	/**
	 * Returns the author of the mod
	 * @return String
	 */
	public Authors getAuthor() {
		return author;
	}
	
	/**
	 * Returns the ID version of the author's name
	 * @return String
	 */
	public String getAuthorId() {
		return ModUtils.underscoreCase(author.getAuthors()[0], false);
	}
	
	/**
	 * Returns the version of the mod
	 * @return String
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Returns the minecraft version of the mod
	 * @return MinecraftVersion
	 */
	public MinecraftVersion getMinecraftVersion() {
		return minecraftVersion;
	}
	
	/**
	 * Returns the creative tab icon index for the mod
	 * @return int
	 */
	public int getCreativeTabIconIndex() {
		return creativeTabIconIndex;
	}
	
	/**
	 * Returns the description of the mod
	 * @param newLineGone whether or not to remove the newLine characters ("\n")
	 * @return String
	 */
	public String getDescription(boolean newLineGone) {
		if(newLineGone)
			return description.replace("\n", "\\n");
		return description;
	}
	
	/**
	 * Returns the description of the mod
	 * @return String
	 */
	public String getDescription() {
		return getDescription(false);
	}
	
	/**
	 * Returns the credits given for the mod
	 * @return String
	 */
	public String getCredits() {
		return credits;
	}
	
	/**
	 * Returns the url of the mod
	 * @return String
	 */
	public String getURL() { 
		return url;
	}
	
	/**
	 * Returns the update url of the mod
	 * @return String
	 */
	public String getUpdateURL() {
		return updateUrl;
	}
	
	/**
	 * Returns an array of Elements contained in the mod
	 * @return Element[]
	 */
	public Element[] getElements() {
		return elements;
	}
	
	/**
	 * Returns the JSON version of the mod
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		JSONArray elements = new JSONArray();
		for(Element element: this.elements)
			elements.addItem(element.toJSON());
		
		JSONObject mod = new JSONObject(
				new JSONPair("name", getName()),
				new JSONPair("authors", getAuthor().toJSON()),
				new JSONPair("version", getVersion()),
				new JSONPair("minecraftVersion", getMinecraftVersion().toJSON()),
				new JSONPair("creativeTabIconIndex", getCreativeTabIconIndex()),
				new JSONPair("description", getDescription(true)),
				new JSONPair("credits", getCredits()),
				new JSONPair("url", getURL()),
				new JSONPair("updateUrl", getUpdateURL()),
				new JSONPair("elements", elements)
		);
		
		return mod;
		
	}

}
