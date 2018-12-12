package elementgenerator.generation;

import json.object.*;

/**
 * A Generation class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Generation {
	
	//Static Fields
	
	/**
	 * The Default value for the generation
	 */
	public static final boolean DEFAULT_GENERATE = false;
	
	/**
	 * The Default value for the lowest point of generation
	 */
	public static final int DEFAULT_LOW = 10;
	
	/**
	 * The Default value for the highest point of generation
	 */
	public static final int DEFAULT_HIGH = 100;
	
	/**
	 * The Default value for rarity of an ore across the world
	 */
	public static final int DEFAULT_WORLD_RARITY = 10;
	
	/**
	 * The Default value for rarity of an ore in a specific chunk
	 */
	public static final int DEFAULT_CHUNK_RARITY = 8;
	
	//Instance Fields
	private boolean generate;
	private int low;
	private int high;
	private int worldRarity;
	private int chunkRarity;
	
	/**
	 * Creates a new Generation object
	 * @param generate whether or not an ore generates in a dimension
	 * @param low the lowest point of generation in a dimension
	 * @param high the highest point of generation in a dimension
	 * @param worldRarity how rare an ore is across a world
	 * @param chunkRarity how rare an ore is in a specific chunk
	 */
	public Generation(boolean generate, int low, int high, int worldRarity, int chunkRarity) {
		this.generate = generate;
		this.low = low;
		this.high = high;
		this.worldRarity = worldRarity;
		this.chunkRarity = chunkRarity;
	}
	
	/**
	 * Creates a new Generation object
	 * @param generate whether or not an ore generates in a dimension
	 */
	public Generation(boolean generate) {
		this(generate, DEFAULT_LOW, DEFAULT_HIGH, DEFAULT_WORLD_RARITY, DEFAULT_CHUNK_RARITY);
	}
	
	/**
	 * Creates a new Generation object
	 */
	public Generation() {
		this(DEFAULT_GENERATE);
	}
	
	/**
	 * Sets whether or not an ore generates in a specific dimension
	 * @param generate boolean value of whether or not an ore generates in a dimension
	 */
	public void setGenerate(boolean generate) {
		this.generate = generate;
	}
	
	/**
	 * Sets the lowest point of generation in a dimension
	 * @param low the lowest point of generation
	 */
	public void setLow(int low) {
		this.low = low;
	}
	
	/**
	 * Sets the highest point of generation in a dimension
	 * @param high the highest point of generation
	 */
	public void setHigh(int high) {
		this.high = high;
	}
	
	/**
	 * Sets how often the ore generates across a dimension
	 * @param worldRarity how often an ore generates across a dimension
	 */
	public void setWorldRarity(int worldRarity) {
		this.worldRarity = worldRarity;
	}
	
	/**
	 * Sets how often an ore generates in a specific chunk
	 * @param chunkRarity how often an ore generates in a specific chunk
	 */
	public void setChunkRarity(int chunkRarity) {
		this.chunkRarity = chunkRarity;
	}
	
	/**
	 * Returns whether or not an ore generates in a dimension
	 * @return boolean - whether or not an ore generates in a dimension
	 */
	public boolean doesGenerate() {
		return generate;
	}
	
	/**
	 * Returns the lowest point of generation in a dimension
	 * @return int - the lowest point of generation
	 */
	public int getLow() {
		return low;
	}
	
	/**
	 * Returns the highest point of generation in a dimension
	 * @return int - the highest point of generation
	 */
	public int getHigh() {
		return high;
	}
	
	/**
	 * Returns how often an ore generates across a dimension
	 * @return int - how often an ore generates across a dimension
	 */
	public int getWorldRarity() {
		return worldRarity;
	}
	
	/**
	 * Returns how often an ore generates in a specific chunk
	 * @return int - how often an ore generates in a specific chunk
	 */
	public int getChunkRarity() {
		return chunkRarity;
	}
	
	/**
	 * Returns the Stringified version of the generation
	 * @return String
	 */
	public String toString() {
	
		return (
				getIndent(1) + "Generate: " + doesGenerate() + "\n" +
				getIndent(1) + "Low Point: " + getLow() + "\n" +
				getIndent(1) + "High Point: " + getHigh() + "\n" +
				getIndent(1) + "World Rarity: " + getWorldRarity() + "\n" +
				getIndent(1) + "Chunk Rarity: " + getChunkRarity() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the generation
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("generate", doesGenerate()),
				new JSONPair("low", getLow()),
				new JSONPair("high", getHigh()),
				new JSONPair("worldRarity", getWorldRarity()),
				new JSONPair("chunkRarity", getChunkRarity())
		);
		
	}
	
	/**
	 * Returns a String used for indenting; Standard Indent size is 4 spaces (' ')
	 * @param indentLevel the level of indent to add
	 * @return String - the indented string
	 */
	private String getIndent(int indentLevel) {
		
		String indentString = "";
		for(int i = 0; i < indentLevel; i++)
			indentString += "    ";
		return indentString;
		
	}
	
}
