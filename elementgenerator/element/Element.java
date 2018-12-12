package elementgenerator.element;

import json.object.*;

import elementgenerator.block.*;
import elementgenerator.generation.*;
import elementgenerator.tool.*;

/**
 * An Element class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Element {
	
	//Static Fields
	
	/**
	 * The ROCK value
	 */
	public static final int ROCK = 0;
	
	/**
	 * The INGOT value
	 */
	public static final int INGOT = 1;
	
	/**
	 * The NUGGET value
	 */
	public static final int NUGGET = 2;
	
	/**
	 * The DUST value
	 */
	public static final int DUST = 3;
	
	/**
	 * The GEM value
	 */
	public static final int GEM = 4;
	
	/**
	 * The CRYSTAL value
	 */
	public static final int CRYSTAL = 5;
	
	/**
	 * The SHARD value
	 */
	public static final int SHARD = 6;
	
	/**
	 * The PEBBLE value
	 */
	public static final int PEBBLE = 7;
	
	/**
	 * The STAR value
	 */
	public static final int STAR = 8;
	
	/**
	 * The AMETHYST value
	 */
	public static final int AMETHYST = 9;
	
	/**
	 * The Default value for the name of the element
	 */
	public static final String DEFAULT_NAME = "";
	
	/**
	 * The Default value for the gem of the element
	 */
	public static final int DEFAULT_GEM = ROCK;
	
	/**
	 * The Default value for the gem dropping from the ore
	 */
	public static final boolean DEFAULT_DROPS_GEM = false;
	
	/**
	 * The Default value for the minimum amount of gems that drop from the ore
	 */
	public static final int DEFAULT_LEAST_QUANTITY = 1;
	
	/**
	 * The Default value for the maximum amount of gems that drop from the ore
	 */
	public static final int DEFAULT_MOST_QUANTITY = 1;
	
	/**
	 * The Default value for the color data of the element
	 */
	public static final ColorData DEFAULT_COLOR_DATA = new ColorData();
	
	/**
	 * The Default value for the generation data of the element
	 */
	public static final GenerationData DEFAULT_GENERATION_DATA = new GenerationData();
	
	/**
	 * The Default value for the tool data of the element
	 */
	public static final ToolData DEFAULT_TOOL_DATA = new ToolData();
	
	/**
	 * The Default value for the armor data of the element
	 */
	public static final ArmorData DEFAULT_ARMOR_DATA = new ArmorData();
	
	/**
	 * The Default value for the ore data of the element
	 */
	public static final BlockData DEFAULT_ORE_DATA = new BlockData();
	
	/**
	 * The Default value for the block data of the element
	 */
	public static final BlockData DEFAULT_BLOCK_DATA = new BlockData();
	
	//Instance Fields
	private String name;
	private int gem;
	private boolean dropsGem;
	private int leastQuantity;
	private int mostQuantity;
	private ColorData colorData;
	private GenerationData generationData;
	private ToolData toolData;
	private ArmorData armorData;
	private BlockData oreData;
	private BlockData blockData;

	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param gem the gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 * @param dropsGem whether or not the gem of the element is dropped when the block is broken
	 * @param colorData the colors of the element
	 * @param generationData the generation of the element's ore
	 * @param toolData the tools of the element
	 * @param armorData the armor of the element
	 * @param oreData the element's ore info
	 * @param blockData the element's block info
	 */
	public Element(String name, int gem, boolean dropsGem, ColorData colorData, GenerationData generationData, ToolData toolData, ArmorData armorData, BlockData oreData, BlockData blockData) {
		this.name = name;
		this.gem = gem;
		this.dropsGem = dropsGem;
		this.leastQuantity = DEFAULT_LEAST_QUANTITY;
		this.mostQuantity = DEFAULT_MOST_QUANTITY;
		this.colorData = colorData;
		this.generationData = generationData;
		this.toolData = toolData;
		this.armorData = armorData;
		this.oreData = oreData;
		this.blockData = blockData;
	}
	
	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param gem the gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 * @param dropsGem whether or not the gem of the element is dropped when the block is broken
	 * @param colorData the colors of the element
	 * @param generationData the generation of the element's ore
	 * @param toolData the tools of the element
	 * @param armorData the armor of the element
	 */
	public Element(String name, int gem, boolean dropsGem, ColorData colorData, GenerationData generationData, ToolData toolData, ArmorData armorData) {
		this(name, gem, dropsGem, colorData, generationData, toolData, armorData, DEFAULT_ORE_DATA, DEFAULT_BLOCK_DATA);
	}
	
	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param gem the gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 * @param dropsGem whether or not the gem of the element is dropped when the block is broken
	 * @param colorData the colors of the element
	 * @param generationData the generation of the element's ore
	 */
	public Element(String name, int gem, boolean dropsGem, ColorData colorData, GenerationData generationData) {
		this(name, gem, dropsGem, colorData, generationData, DEFAULT_TOOL_DATA, DEFAULT_ARMOR_DATA);
	}
	
	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param gem the gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 * @param dropsGem whether or not the gem of the element is dropped when the block is broken
	 * @param colorData the colors of the element
	 */
	public Element(String name, int gem, boolean dropsGem, ColorData colorData) {
		this(name, gem, dropsGem, colorData, DEFAULT_GENERATION_DATA);
	}
	
	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param gem the gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 * @param colorData the colors of the element
	 */
	public Element(String name, int gem, ColorData colorData) {
		this(name, gem, DEFAULT_DROPS_GEM, colorData);
	}
	
	/**
	 * Creates a new Element object
	 * @param name the name of the element
	 * @param colorData the colors of the element
	 */
	public Element(String name, ColorData colorData) {
		this(name, DEFAULT_GEM, colorData);
	}
	
	/**
	 * Creates a new Element object
	 */
	public Element() {
		this(DEFAULT_NAME, DEFAULT_COLOR_DATA);
	}
	
	/**
	 * Sets the name of the element
	 * @param name the name of the element
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the gem type of the element
	 * @param gem gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 */
	public void setGem(int gem) {
		this.gem = gem;
	}
	
	/**
	 * Sets the gem type of the element
	 * @param gem gem type of the element {@code (ROCK, INGOT, NUGGET, DUST, GEM, CRYSTAL, SHARD)}
	 */
	public void setGem(String gem) {
		switch(gem.toLowerCase()) {
			case "rock":     this.gem = ROCK; 		break;
			case "ingot":    this.gem = INGOT; 		break;
			case "nugget":   this.gem = NUGGET; 		break;
			case "dust":     this.gem = DUST; 		break;
			case "gem":      this.gem = GEM; 		break;
			case "crystal":  this.gem = CRYSTAL; 	break;
			case "shard":    this.gem = SHARD;   	break;
			case "pebble":   this.gem = PEBBLE;      break;
			case "star":     this.gem = STAR;  		break;
			case "amethyst": this.gem = AMETHYST;	break;
			default: 		 this.gem = DEFAULT_GEM; break;
		}
	}
	
	/**
	 * Sets whether or not the gem drops from the ore
	 * @param dropsGem whether or not the gem drops from the ore
	 */
	public void setGemDrops(boolean dropsGem) {
		this.dropsGem = dropsGem;
	}
	
	/**
	 * Sets the minimum amount of gems that drop from the ore (only useful when {@code dropsGem = true})
	 * @param leastQuantity the minimum amount of gems that drop
	 */
	public void setLeastQuantity(int leastQuantity) {
		this.leastQuantity = leastQuantity;
	}
	
	/**
	 * Sets the maximum amount of gems that drop from the ore (only useful when {@code dropsGem = false})
	 * @param mostQuantity the maximum amount of gems that drop
	 */
	public void setMostQuantity(int mostQuantity) {
		this.mostQuantity = mostQuantity;
	}
	
	/**
	 * Sets the color data for the element
	 * @param colorData the color data of the element
	 */
	public void setColorData(ColorData colorData) {
		this.colorData = colorData;
	}
	
	/**
	 * Sets the generation data for the element
	 * @param generationData the generation data for the element
	 */
	public void setGenerationData(GenerationData generationData) {
		this.generationData = generationData;
	}
	
	/**
	 * Sets the tool data for the element
	 * @param toolData the tool data for the element
	 */
	public void setToolData(ToolData toolData) {
		this.toolData = toolData;
	}
	
	/**
	 * Sets the armor data for the element
	 * @param armorData the armor data for the element
	 */
	public void setArmorData(ArmorData armorData) {
		this.armorData = armorData;
	}
	
	/**
	 * Sets the ore data for the element
	 * @param oreData the ore data for the element
	 */
	public void setOreData(BlockData oreData) {
		this.oreData = oreData;
	}
	
	/**
	 * Sets the block data for the element
	 * @param blockData the block data for the element
	 */
	public void setBlockData(BlockData blockData) {
		this.blockData = blockData;
	}
	
	/**
	 * Returns the name of the element
	 * @return String - the name of the element
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the id of the gem
	 * @return int - gem in ID form
	 */
	public int getGemId() {
		return gem;
	}
	
	/**
	 * Returns the name of the gem
	 * @return String - the name of the gem
	 */
	public String getGem() {
		switch(gem) {
			case ROCK: 	   return "rock";
			case INGOT:    return "ingot";
			case NUGGET:   return "nugget";
			case DUST: 	   return "dust";
			case GEM:      return "gem";
			case CRYSTAL:  return "crystal";
			case SHARD:    return "shard";
			case PEBBLE:   return "pebble";
			case STAR:     return "star";
			case AMETHYST: return "amethyst";
			default: 	   return "rock";
		}
	}
	
	/**
	 * Returns whether or not the gem drops from the ore
	 * @return boolean - whether or not the gem drops
	 */
	public boolean doesGemDrop() {
		return dropsGem;
	}
	
	/**
	 * Returns the minimum amount of gems that drop from the ore
	 * @return int - the minimum amount of gems
	 */
	public int getLeastQuantity() {
		return leastQuantity;
	}
	
	/**
	 * Returns the maximum amount of gems that drop from the ore
	 * @return int - the maximum amount of gems
	 */
	public int getMostQuantity() {
		return mostQuantity;
	}
	
	/**
	 * Returns the ColorData of the element
	 * @return ColorData - the ColorData of the element
	 */
	public ColorData getColorData() {
		return colorData;
	}
	
	/**
	 * Returns the GenerationData of the element
	 * @return GenerationData - the GenerationData of the element
	 */
	public GenerationData getGenerationData() {
		return generationData;
	}
	
	/**
	 * Returns the ToolData of the element
	 * @return ToolData - the ToolData of the element
	 */
	public ToolData getToolData() {
		return toolData;
	}
	
	/**
	 * Returns the ArmorData of the element
	 * @return ArmorData - the ArmorData of the element
	 */
	public ArmorData getArmorData() {
		return armorData;
	}
	
	/**
	 * Returns the OreData of the element
	 * @return BlockData - the OreData of the element
	 */
	public BlockData getOreData() {
		return oreData;
	}
	
	/**
	 * Returns the BlockData of the element
	 * @return BlockData - the BlockData of the element
	 */
	public BlockData getBlockData() {
		return blockData;
	}
	
	/**
	 * Compares this Element object to a specified Element object
	 * @param element the Element object to compare to
	 * @return int
	 */
	public int compareTo(Element element) {
		
		// Compare by name
		return this.name.compareTo(element.getName());
	}
	
	/**
	 * Returns the Stringified version of the Element
	 * @return String
	 */
	public String toString() {
		
		return (
				getIndent(3) + "Name: " + getName() + "\n" +
				getIndent(3) + "Gem: " + getGem() + "\n" +
				getIndent(3) + "Drops Gem: " + doesGemDrop() + "\n" +
				getIndent(3) + "Least Quantity: " + getLeastQuantity() + "\n" +
				getIndent(3) + "Most Quantity: " + getMostQuantity() + "\n" +
				getIndent(3) + "Color Data\n" + getColorData().toString() + "\n" +
				getIndent(3) + "Generation Data\n" + getGenerationData().toString() + "\n" +
				getIndent(3) + "Tool Data\n" + getToolData().toString() + "\n" +
				getIndent(3) + "Armor Data\n" + getArmorData().toString() + "\n" +
				getIndent(3) + "Ore Data\n" + getOreData().toString() + "\n" +
				getIndent(3) + "Block Data\n" + getBlockData().toString() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the Element
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("name", getName()),
				new JSONPair("gem", getGemId()),
				new JSONPair("dropsGem", doesGemDrop()),
				new JSONPair("leastQuantity", getLeastQuantity()),
				new JSONPair("mostQuantity", getMostQuantity()),
				new JSONPair("colorData", getColorData().toJSON()),
				new JSONPair("generationData", getGenerationData().toJSON()),
				new JSONPair("toolData", getToolData().toJSON()),
				new JSONPair("armorData", getArmorData().toJSON()),
				new JSONPair("oreData", getOreData().toJSON()),
				new JSONPair("blockData", getBlockData().toJSON())
		);
		
	}
	
	/**
	 * Returns a String used for indenting; Standard indent size is 4 spaces (' ')
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
