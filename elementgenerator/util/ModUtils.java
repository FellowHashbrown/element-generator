package elementgenerator.util;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import elementgenerator.block.BlockData;
import elementgenerator.element.*;
import elementgenerator.generation.*;
import elementgenerator.gui.ElementGenerator;
import elementgenerator.gui.MainPanel;
import elementgenerator.gui.customcomponents.Message;
import elementgenerator.mod.*;
import elementgenerator.tool.*;
import json.exception.*;
import json.object.*;
import json.parser.JSON;

/**
 * A class that holds all the methods needed to save the mod into a file or generate it 
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ModUtils {
	
	// Static Fields
	
	public static final Color[] TEMPLATE_COLORS = new Color[] {
			new Color(255, 0, 0, 255),   // RED
			new Color(255, 255, 0, 255), // YELLOW
			new Color(0, 255, 0, 255),   // GREEN
			new Color(0, 255, 255, 255), // AQUA
			new Color(0, 0, 255, 255),   // BLUE   
			new Color(255, 0, 255, 255)  // MAGENTA
	};
	
	// Current Directory
	public static final String CURRENT_DIRECTORY = System.getProperty("user.dir") + "/";

	// Mod Saves and element saves
	public static final String MOD_SAVE_DIRECTORY = CURRENT_DIRECTORY + "Saved Mods/";
	public static final String ELEMENT_SAVE_DIRECTORY = CURRENT_DIRECTORY + "Saved Elements/";
	
	// Templates
	public static final String RESOURCE_TEMPLATE_DIRECTORY = "/resources/templates/";
	// - Classes
	public static final String ORE_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/Ore.txt";
	public static final String BLOCK_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/Block.txt";
	public static final String GEM_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/Gem.txt";
	public static final String TOOL_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/%s.txt";
	public static final String ARMOR_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/Armor.txt";
	// - JSON
	public static final String MC_MOD_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/mcmod.info";
	public static final String PACK_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/pack.mcmeta";
	public static final String BUILD_GRADLE_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/build.gradle";
	public static final String MANIFEST = RESOURCE_TEMPLATE_DIRECTORY + "json/manifest.mf";
	public static final String BLOCK_STATE_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/block_state.json";
	public static final String BLOCK_MODEL_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/block_model.json";
	public static final String ITEM_MODEL_BLOCK_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/item_model_block.json";
	public static final String ITEM_MODEL_ITEM_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/item_model_item.json";
	public static final String STANDARD_ITEM = RESOURCE_TEMPLATE_DIRECTORY + "json/standard_item.json";
	public static final String BLOCK = RESOURCE_TEMPLATE_DIRECTORY + "json/block.json";
	public static final String CUBE = RESOURCE_TEMPLATE_DIRECTORY + "json/cube.json";
	public static final String CUBE_ALL = RESOURCE_TEMPLATE_DIRECTORY + "json/cube_all.json";
	public static final String RECIPE_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "json/%s_recipe.json";
	// - Mod
	public static final String CLASS_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/Class.txt";
	public static final String CLIENT_PROXY_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/ClientProxy.txt";
	public static final String COMMON_PROXY_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/CommonProxy.txt";
	public static final String GLOBAL_EVENTS_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/GlobalEvents.txt";
	public static final String VAR_LIST_TEMPLATE = RESOURCE_TEMPLATE_DIRECTORY + "classes/%s/VarList.txt";
	// - Textures
	public static final String NETHER_ORE_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/ores/nether/%s_nether_ore.png";
	public static final String SURFACE_ORE_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/ores/surface/%s_surface_ore.png";
	public static final String END_ORE_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/ores/end/%s_end_ore.png";
	public static final String BLOCK_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/blocks/%s_block.png";
	public static final String GEM_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/gems/%s.png";
	public static final String TOOL_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/tools/%s.png";
	public static final String ARMOR_MODEL_TEXTURE = RESOURCE_TEMPLATE_DIRECTORY + "textures/armor/layer_%s.png";
	
	// Output
	public static final String OUTPUT_DIRECTORY = CURRENT_DIRECTORY + "Generated Mods/";
			
	public static final String MOD_DIRECTORY = OUTPUT_DIRECTORY + "%s/%s/";
			
	// - Java Directory
	public static final String JAVA_DIRECTORY = MOD_DIRECTORY + "src/main/java/com/%s/%s/";
	// - Resources Directory
	public static final String RESOURCES_DIRECTORY = MOD_DIRECTORY + "src/main/resources/";
	public static final String META_INF_DIRECTORY = RESOURCES_DIRECTORY + "META-INF/";
	public static final String BLOCK_STATES_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/blockstates/";
	public static final String BLOCK_MODELS_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/models/block/";
	public static final String ITEM_MODELS_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/models/item/";
	public static final String BLOCK_TEXTURES_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/textures/blocks/";
	public static final String ITEM_TEXTURES_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/textures/items/";
	public static final String ARMOR_TEXTURES_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/textures/models/armor/";
	public static final String LANG_DIRECTORY = RESOURCES_DIRECTORY + "assets/%s/lang/";
	
	/**
	 * Loads an element from a JSONObject
	 * @param jsonElement the JSONObject to load from
	 * @param showMessage whether or not to show the JOptionPane message
	 * @return Element
	 */
	public static Element loadElement(JSONObject jsonElement, boolean showMessage) {
		
		// Attempt to load Element
		Element element = new Element();
		
		try {
			
			ElementGenerator.logger.log("ModUtils", "attempting to load element from JSONObject", Logger.ATTEMPT);
		
			// Basic Element Info
			element.setName(((JSONPrimitive)jsonElement.getPair("name").getValue()).getAsString(false));
			element.setGem(((JSONPrimitive)jsonElement.getPair("gem").getValue()).getAsInt());
	
			// Drops Gem
			try {
				element.setGemDrops(((JSONPrimitive)jsonElement.getPair("dropsGem").getValue()).getAsBoolean());
			} catch (Exception e) {
				element.setGemDrops(false);
			}
	
			// Least Quantity
			try {
				element.setLeastQuantity(((JSONPrimitive)jsonElement.getPair("leastQuantity").getValue()).getAsInt());
			} catch (Exception e) {
				element.setLeastQuantity(1);
			}
			
			// Most Quantity
			try {
				element.setMostQuantity(((JSONPrimitive)jsonElement.getPair("mostQuantity").getValue()).getAsInt());
			} catch (Exception e) {
				element.setMostQuantity(1);
			}
			
			// ColorData
			JSONObject jsonColorData = ((JSONObject)jsonElement.getPair("colorData").getValue());
			JSONObject jsonDarkestColor = ((JSONObject)jsonColorData.getPair("darkestColor").getValue());
			JSONObject jsonLightestColor = ((JSONObject)jsonColorData.getPair("lightestColor").getValue());
			Color darkestColor = new Color(
					((JSONPrimitive)jsonDarkestColor.getPair("red").getValue()).getAsInt(),
					((JSONPrimitive)jsonDarkestColor.getPair("green").getValue()).getAsInt(),
					((JSONPrimitive)jsonDarkestColor.getPair("blue").getValue()).getAsInt()
			);
			Color lightestColor = new Color(
					((JSONPrimitive)jsonLightestColor.getPair("red").getValue()).getAsInt(),
					((JSONPrimitive)jsonLightestColor.getPair("green").getValue()).getAsInt(),
					((JSONPrimitive)jsonLightestColor.getPair("blue").getValue()).getAsInt()
			);
			element.setColorData(new ColorData(darkestColor, lightestColor));
			
			// GenerationData
			try {
				JSONObject jsonGenerationData = ((JSONObject)jsonElement.getPair("generationData").getValue());
				GenerationData generationData = new GenerationData();
				
				// Nether
				try {
					JSONObject jsonNether = ((JSONObject)jsonGenerationData.getPair("nether").getValue());
					Generation nether = new Generation(
							((JSONPrimitive)jsonNether.getPair("generate").getValue()).getAsBoolean(),
							((JSONPrimitive)jsonNether.getPair("low").getValue()).getAsInt(),
							((JSONPrimitive)jsonNether.getPair("high").getValue()).getAsInt(),
							((JSONPrimitive)jsonNether.getPair("worldRarity").getValue()).getAsInt(),
							((JSONPrimitive)jsonNether.getPair("chunkRarity").getValue()).getAsInt()
					);
					generationData.setNether(nether);
				} catch (Exception e) {
					generationData.setNether(new Generation());
				}
				
				// Surface
				try {
					JSONObject jsonSurface = ((JSONObject)jsonGenerationData.getPair("surface").getValue());
					Generation surface = new Generation(
							((JSONPrimitive)jsonSurface.getPair("generate").getValue()).getAsBoolean(),
							((JSONPrimitive)jsonSurface.getPair("low").getValue()).getAsInt(),
							((JSONPrimitive)jsonSurface.getPair("high").getValue()).getAsInt(),
							((JSONPrimitive)jsonSurface.getPair("worldRarity").getValue()).getAsInt(),
							((JSONPrimitive)jsonSurface.getPair("chunkRarity").getValue()).getAsInt()
					);
					generationData.setSurface(surface);
				} catch (Exception e) {
					generationData.setSurface(new Generation(true));
				}
				
				// End
				try {
					JSONObject jsonEnd = ((JSONObject)jsonGenerationData.getPair("end").getValue());
					Generation end = new Generation(
							((JSONPrimitive)jsonEnd.getPair("generate").getValue()).getAsBoolean(),
							((JSONPrimitive)jsonEnd.getPair("low").getValue()).getAsInt(),
							((JSONPrimitive)jsonEnd.getPair("high").getValue()).getAsInt(),
							((JSONPrimitive)jsonEnd.getPair("worldRarity").getValue()).getAsInt(),
							((JSONPrimitive)jsonEnd.getPair("chunkRarity").getValue()).getAsInt()
					);
					generationData.setEnd(end);
				} catch (Exception e) {
					generationData.setEnd(new Generation());
				}
				
				element.setGenerationData(generationData);
			}
			catch (Exception e) {
				element.setGenerationData(new GenerationData());
			}
			
			// ToolData
			JSONObject jsonToolData = ((JSONObject)jsonElement.getPair("toolData").getValue());
			JSONObject jsonPickaxe = ((JSONObject)jsonToolData.getPair("pickaxe").getValue());
			JSONObject jsonAxe = ((JSONObject)jsonToolData.getPair("axe").getValue());
			JSONObject jsonHoe = ((JSONObject)jsonToolData.getPair("hoe").getValue());
			JSONObject jsonShovel = ((JSONObject)jsonToolData.getPair("shovel").getValue());
			JSONObject jsonSword = ((JSONObject)jsonToolData.getPair("sword").getValue());
			Tool pickaxe = new Tool(
					Tool.PICKAXE,
					((JSONPrimitive)jsonPickaxe.getPair("harvestLevel").getValue()).getAsInt(),
					((JSONPrimitive)jsonPickaxe.getPair("efficiency").getValue()).getAsInt(),
					Tool.DEFAULT_ENCHANTABILITY,
					((JSONPrimitive)jsonPickaxe.getPair("attackDamage").getValue()).getAsInt(),
					((JSONPrimitive)jsonPickaxe.getPair("uses").getValue()).getAsInt()
			);
			Tool axe = new Tool(
					Tool.AXE,
					((JSONPrimitive)jsonAxe.getPair("harvestLevel").getValue()).getAsInt(),
					((JSONPrimitive)jsonAxe.getPair("efficiency").getValue()).getAsInt(),
					Tool.DEFAULT_ENCHANTABILITY,
					((JSONPrimitive)jsonAxe.getPair("attackDamage").getValue()).getAsInt(),
					((JSONPrimitive)jsonAxe.getPair("uses").getValue()).getAsInt()
			);
			Tool hoe = new Tool(
					Tool.HOE,
					((JSONPrimitive)jsonHoe.getPair("harvestLevel").getValue()).getAsInt(),
					((JSONPrimitive)jsonHoe.getPair("efficiency").getValue()).getAsInt(),
					Tool.DEFAULT_ENCHANTABILITY,
					((JSONPrimitive)jsonHoe.getPair("attackDamage").getValue()).getAsInt(),
					((JSONPrimitive)jsonHoe.getPair("uses").getValue()).getAsInt()
			);
			Tool shovel = new Tool(
					Tool.SHOVEL,
					((JSONPrimitive)jsonShovel.getPair("harvestLevel").getValue()).getAsInt(),
					((JSONPrimitive)jsonShovel.getPair("efficiency").getValue()).getAsInt(),
					Tool.DEFAULT_ENCHANTABILITY,
					((JSONPrimitive)jsonShovel.getPair("attackDamage").getValue()).getAsInt(),
					((JSONPrimitive)jsonShovel.getPair("uses").getValue()).getAsInt()
			);
			Tool sword = new Tool(
					Tool.SWORD,
					((JSONPrimitive)jsonSword.getPair("harvestLevel").getValue()).getAsInt(),
					((JSONPrimitive)jsonSword.getPair("efficiency").getValue()).getAsInt(),
					Tool.DEFAULT_ENCHANTABILITY,
					((JSONPrimitive)jsonSword.getPair("attackDamage").getValue()).getAsInt(),
					((JSONPrimitive)jsonSword.getPair("uses").getValue()).getAsInt()
			);
			ToolData toolData = new ToolData(pickaxe, axe, hoe, shovel, sword);
			element.setToolData(toolData);
			
			// ArmorData
			JSONObject jsonArmorData = ((JSONObject)jsonElement.getPair("armorData").getValue());
			JSONObject jsonHelmet = ((JSONObject)jsonArmorData.getPair("helmet").getValue());
			JSONObject jsonChestplate = ((JSONObject)jsonArmorData.getPair("chestplate").getValue());
			JSONObject jsonLeggings = ((JSONObject)jsonArmorData.getPair("leggings").getValue());
			JSONObject jsonBoots = ((JSONObject)jsonArmorData.getPair("boots").getValue());
			
			int maxDamage = ((JSONPrimitive)jsonArmorData.getPair("maxDamage").getValue()).getAsInt();
			double toughness = ((JSONPrimitive)jsonArmorData.getPair("toughness").getValue()).getAsDouble();
			
			Armor helmet = new Armor(
					Armor.HELMET,
					((JSONPrimitive)jsonHelmet.getPair("health").getValue()).getAsInt(),
					((JSONPrimitive)jsonHelmet.getPair("effect").getValue()).getAsString(false),
					((JSONPrimitive)jsonHelmet.getPair("level").getValue()).getAsInt(),
					((JSONPrimitive)jsonHelmet.getPair("duration").getValue()).getAsInt()
			);
			Armor chestplate = new Armor(
					Armor.CHESTPLATE,
					((JSONPrimitive)jsonChestplate.getPair("health").getValue()).getAsInt(),
					((JSONPrimitive)jsonChestplate.getPair("effect").getValue()).getAsString(false),
					((JSONPrimitive)jsonChestplate.getPair("level").getValue()).getAsInt(),
					((JSONPrimitive)jsonChestplate.getPair("duration").getValue()).getAsInt()
			);
			Armor leggings = new Armor(
					Armor.LEGGINGS,
					((JSONPrimitive)jsonLeggings.getPair("health").getValue()).getAsInt(),
					((JSONPrimitive)jsonLeggings.getPair("effect").getValue()).getAsString(false),
					((JSONPrimitive)jsonLeggings.getPair("level").getValue()).getAsInt(),
					((JSONPrimitive)jsonLeggings.getPair("duration").getValue()).getAsInt()
			);
			Armor boots = new Armor(
					Armor.BOOTS,
					((JSONPrimitive)jsonBoots.getPair("health").getValue()).getAsInt(),
					((JSONPrimitive)jsonBoots.getPair("effect").getValue()).getAsString(false),
					((JSONPrimitive)jsonBoots.getPair("level").getValue()).getAsInt(),
					((JSONPrimitive)jsonBoots.getPair("duration").getValue()).getAsInt()
			);
			ArmorData armorData = new ArmorData(maxDamage, ArmorData.DEFAULT_ENCHANTABILITY, toughness, helmet, chestplate, leggings, boots);
			element.setArmorData(armorData);
			
			// OreData & BlockData
			JSONObject jsonOreData = ((JSONObject)jsonElement.getPair("oreData").getValue());
			JSONObject jsonBlockData = ((JSONObject)jsonElement.getPair("blockData").getValue());
			BlockData oreData = new BlockData(
					((JSONPrimitive)jsonOreData.getPair("hardness").getValue()).getAsDouble(),
					((JSONPrimitive)jsonOreData.getPair("resistance").getValue()).getAsDouble(),
					((JSONPrimitive)jsonOreData.getPair("lightLevel").getValue()).getAsDouble(),
					((JSONPrimitive)jsonOreData.getPair("lightOpacity").getValue()).getAsInt(),
					((JSONPrimitive)jsonOreData.getPair("harvestLevel").getValue()).getAsInt()
			);
			BlockData blockData = new BlockData(
					((JSONPrimitive)jsonBlockData.getPair("hardness").getValue()).getAsDouble(),
					((JSONPrimitive)jsonBlockData.getPair("resistance").getValue()).getAsDouble(),
					((JSONPrimitive)jsonBlockData.getPair("lightLevel").getValue()).getAsDouble(),
					((JSONPrimitive)jsonBlockData.getPair("lightOpacity").getValue()).getAsInt(),
					((JSONPrimitive)jsonBlockData.getPair("harvestLevel").getValue()).getAsInt()
			);
			element.setOreData(oreData);
			element.setBlockData(blockData);
			
			if(showMessage)
				new Message("Successfully loaded " + element.getName()).display("Success");
			
			ElementGenerator.logger.log("ModUtils", "Successfully loaded element \"" + element.getName() + "\"", Logger.SUCCESS);
			
			return element;
		} catch (Exception e) {
			
			ElementGenerator.logger.log("ModUtils", "Failed to load element.\n    Error: " + e.getMessage(), Logger.SUCCESS);
			
			// Only show a message if showMessage is true
			if(showMessage) {
				
				// If JSON file loaded was a mod file
				if(jsonElement.has("elements")) {
					// Pair was loaded, not an element file
					new Message(JOptionPane.ERROR_MESSAGE, 
							"Failed to load element.", 
							"    File chosen is not an element file.",
							e.getMessage()
					).display("Failed");
				}
				
				// JSON file loaded was an element file, other error
				else {
					// Pair was not loaded, unknown reason
					if(e instanceof JSONUnbalancedException) {
						new Message(JOptionPane.ERROR_MESSAGE, 
								"Failed to load element.", 
								"    Braces were unbalanced in the file.",
								e.getMessage()
						).display("Failed");
					}
					else if(e instanceof JSONPairDoesNotExistException) {
						new Message(JOptionPane.ERROR_MESSAGE, 
								"Failed to load element.", 
								"    There was a missing required tag inside the element data.",
								e.getMessage()
						).display("Failed");
					}
					else {
						new Message(JOptionPane.ERROR_MESSAGE, 
								"Failed to load element.", 
								"    Unknown Error.",
								e.getMessage()
						).display("Failed");
					}
				}
				
			}
			
			return null;
		}

	}
	
	/**
	 * Returns an element loaded from a JSONObject
	 * @param jsonElement the JSONObject to load an element from
	 * @return Element
	 */
	public static Element loadElement(JSONObject jsonElement) {
		return loadElement(jsonElement, false);
	}
	
	/**
	 * Returns an element loaded from a JSON file
	 * @param fileName the file to load an element from
	 * @param showMessage whether or not to show the JOptionPane message
	 * @return Element
	 */
	public static Element loadElement(String fileName, boolean showMessage) {
		JSONObject jsonElement = (JSONObject) JSON.read(fileName);
		
		return loadElement(jsonElement, showMessage);
	}
	
	/**
	 * Returns an element loaded from a JSON file
	 * @param fileName the file to load an element from
	 * @return Element
	 */
	public static Element loadElement(String fileName) {
		return loadElement(fileName); 
	}
	
	/**
	 * Saves the mod to the MOD_SAVE_DIRECTORY
	 * @param mod the mod to save
	 * @param showMessage whether or not to show a success message
	 */
	public static void saveMod(Mod mod, boolean showMessage) {
		
		ElementGenerator.logger.log("ModUtils", "Saving mod \"" + mod.getName() + "\"");
		
		// Create "Saved Mods" path
		File modPath = new File(MOD_SAVE_DIRECTORY);
		modPath.mkdirs();
		
		// Create "Saved Elements" path
		File elementPath = new File(ELEMENT_SAVE_DIRECTORY);
		elementPath.mkdirs();
		
		// Save Mod
		String modFileName = MOD_SAVE_DIRECTORY + underscoreCase(mod.getName()) + "." + ElementGenerator.ELEMENT_FILE;
		JSON.write(modFileName, mod.toJSON());
		
		// Save all elements in a json file
		for(Element element: mod.getElements()) {
			
			ElementGenerator.logger.log("ModUtils", "Saving element \"" + element.getName() + "\"");
			
			String elementFileName = ELEMENT_SAVE_DIRECTORY + underscoreCase(element.getName()) + "." + ElementGenerator.ELEMENT_FILE;
			JSON.write(elementFileName, element.toJSON());
		}
		
		if(showMessage) {
			new Message(
					mod.getName() + " was successfully saved.",
					"Elements in the mod were successfully saved to the \"Saved Elements\" folder."
			).display("Success");
		}
		
	}
	
	/**
	 * Saves the mod to the MOD_SAVE_DIRECTORY
	 * @param mod the mod to save
	 */
	public static void saveMod(Mod mod) {
		saveMod(mod, true);
	}
	
	/**
	 * Loads a mod from a JSONObject
	 * @param jsonMod the JSONObject to load from
	 * @return Mod
	 */
	public static Mod loadMod(JSONObject jsonMod) {
		
		// Attempt to load mod
		Mod mod = new Mod();
		
		// Try to load each aspect of the mod
		try {
			
			ElementGenerator.logger.log("ModUtils", "Attempting to load mod", Logger.ATTEMPT);
			
			// Basic Mod Info
			mod.setName(((JSONPrimitive)jsonMod.getPair("name").getValue()).getAsString(false));
			mod.setAuthor(new Authors(((JSONArray)jsonMod.getPair("authors").getValue())));
			mod.setVersion(((JSONPrimitive)jsonMod.getPair("version").getValue()).getAsString(false));
			
			// Minecraft Version
			JSONObject jsonMinecraftVersion = ((JSONObject)jsonMod.getPair("minecraftVersion").getValue());
			MinecraftVersion minecraftVersion = new MinecraftVersion(
					((JSONPrimitive)jsonMinecraftVersion.getPair("version").getValue()).getAsString(false),
					((JSONPrimitive)jsonMinecraftVersion.getPair("build").getValue()).getAsString(false),
					((JSONPrimitive)jsonMinecraftVersion.getPair("java").getValue()).getAsString(false),
					((JSONPrimitive)jsonMinecraftVersion.getPair("snapshot").getValue()).getAsString(false),
					((JSONPrimitive)jsonMinecraftVersion.getPair("forgeSnapshot").getValue()).getAsString(false)
			);
			mod.setMinecraftVersion(minecraftVersion);
			
			// Creative Tab Icon
			try {
				mod.setCreativeTabIconIndex(((JSONPrimitive)jsonMod.getPair("creativeTabIconIndex").getValue()).getAsInt());
			} catch (Exception e) {
				mod.setCreativeTabIconIndex(0);
			}
	
			// Credits
			try {
				mod.setCredits(((JSONPrimitive)jsonMod.getPair("credits").getValue()).getAsString(false));
			} catch (Exception e) {
				mod.setCredits("");
			}
			
			// Description
			try {
				mod.setDescription(((JSONPrimitive)jsonMod.getPair("description").getValue()).getAsString(false));
			} catch (Exception e) {
				mod.setDescription("");
			}

			// URL
			try {
				mod.setURL(((JSONPrimitive)jsonMod.getPair("url").getValue()).getAsString(false));
			} catch (Exception e) {
				mod.setURL("");
			}
			 
			// Update URL
			try {
				mod.setUpdateURL(((JSONPrimitive)jsonMod.getPair("updateUrl").getValue()).getAsString(false));
			} catch (Exception e) {
				mod.setUpdateURL("");
			}
			
			// Elements
			JSONArray elements = (JSONArray)jsonMod.getPair("elements").getValue();
			for(JSONElement value: elements.getArray()) {
				
				Element element = loadElement((JSONObject)value);
				
				// Add element to the mod
				mod.addElement(element);
				
			}
			
			// Mod was successfully loaded, show message
			ElementGenerator.logger.log("ModUtils", "Successfully loaded \"" + mod.getName() + "\"", Logger.SUCCESS);
			
			new Message(
					mod.getName() + " was successfully loaded."
			).display("Success");
			
			// Return the mod
			return mod;
			
		} catch (Exception e) {
			
			ElementGenerator.logger.log("ModUtils", "Failed to load mod.\n    Error: " + e.getMessage(), Logger.FAILED);
			
			// Mod wasn't loaded, show message
			if(e instanceof JSONUnbalancedException) {
				new Message(JOptionPane.ERROR_MESSAGE, 
						"Failed to load mod.", 
						"    Braces were unbalanced in the file.",
						e.getMessage()
				).display("Failed");
			} 
			else if(e instanceof JSONPairDoesNotExistException) {
				new Message(JOptionPane.ERROR_MESSAGE, 
						"Failed to load mod.", 
						"    There was a missing required tag inside the mod data.",
						e.getMessage()
				).display("Failed");
			}
			else {
				new Message(JOptionPane.ERROR_MESSAGE, 
						"Failed to load mod.", 
						"    Unknown Error.",
						e.getMessage()
				).display("Failed");
			}
			
			e.printStackTrace();
	
			// Return a null object
			return null;
		}
				
	}
	
	/**
	 * Opens a mod from a json file
	 * @param fileName the file to load a Mod from
	 * @return Mod - the mod object loaded from the json file
	 */
	public static Mod loadMod(String fileName) {
		
		// Try to open the file into a JSONObject
		JSONObject jsonMod = (JSONObject) JSON.read(fileName);
		
		return loadMod(jsonMod);
		
	}
	
	/**
	 * Generates the mod and all the files needed for it
	 * @param mod the Mod object to generate
	 */
	public static void generateMod(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating mod: " + mod.getName());
		
		generateClasses(mod);
		generateJSONS(mod);
		generateTextures(mod);
		generateModFiles(mod);
		generateLang(mod);
		
		// Success message for file generation
		new Message(
				mod.getName() + " was successfully generated and saved."
		).display("Success");
		
	}
	
	/**
	 * Generates all the class files needed for the elements
	 * @param mod the Mod object to generate from
	 */
	public static void generateClasses(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating class files for mod: " + mod.getName());
		
		// Create the java directory
		File javaDirectory = new File(
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				)
		);
		javaDirectory.mkdirs();
		
		// Get the array of elements
		Element[] elements = mod.getElements();
		
		// Set up the Main Class File declarations, fuel, registers, etc..
		String declarations = "";
		String fuels = "";
		String generateNethers = "";
		String generateSurfaces = "";
		String generateEnds = "";
		String loads = "";
		String serverLoads = "";
		String instances = "";
		String preInits = "";
		String renderers = "";
		
		// Iterate through elements
		for(int i = 0; i < elements.length; i++) {
			
			ElementGenerator.logger.log("ModUtils", "");
			ElementGenerator.logger.log("ModUtils", "****************************************************************************************************");
			ElementGenerator.logger.log("ModUtils", "Generating class files for element: " + elements[i].getName());
			
			// Generate Ores
			// - Only generate Nether Ore if ore generates in Nether
			if(elements[i].getGenerationData().getNether().doesGenerate()) {
				
				ElementGenerator.logger.log("ModUtils", "Generating Nether Ore class file for element: " + elements[i].getName());
				
				declarations += String.format(		
						"    %1$sNetherOre %2$s_nether_ore = new %1$sNetherOre();\n",
						titleCase(elements[i].getName(), false), underscoreCase(elements[i].getName())
				);
				generateNethers += String.format(
						"            %s_nether_ore.generateNether(world, random, chunkX, chunkZ);\n",
						underscoreCase(elements[i].getName())
				);
				loads += String.format(
						"        %s_nether_ore.load(event);\n",
						underscoreCase(elements[i].getName())
				);
				serverLoads += String.format(
						"        %s_nether_ore.serverLoad(event);\n",
						underscoreCase(elements[i].getName())
				);
				instances += String.format(
						"        %s_nether_ore.instance = this.instance;\n",
						underscoreCase(elements[i].getName())
				);
				preInits += String.format(
						"        %s_nether_ore.preInit(event);\n",
						underscoreCase(elements[i].getName())
				);
				renderers += String.format(
						"        %s.%s_nether_ore.registerRenderers();\n",
						mod.getModId(), underscoreCase(elements[i].getName())
				);
				
				ElementUtils.replaceLines(
						String.format(
								ORE_TEMPLATE,
								mod.getMinecraftVersion().getVersion()
						), 
						String.format(
								JAVA_DIRECTORY, 
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
						) + titleCase(elements[i].getName(), false) + "NetherOre.java", 
						new String[] {
								"authorId", mod.getAuthorId(),
								"modId", mod.getModId(),
								"ModName", titleCase(mod.getName(), false),
								
								"ElementHere", titleCase(elements[i].getName(), false) + "Nether",
								"elementHere", underscoreCase(elements[i].getName()) + "_nether",
								"ElementNoOre", titleCase(elements[i].getName(), false),
								"elementNoOre", underscoreCase(elements[i].getName()),
								
								"GenerateNether", String.format(
										"        for(int i = 0; i < %s; i++) {\n" +
										"            int randX = chunkX + random.nextInt(16);\n" +
										"            int randY = random.nextInt(%s) + %s;\n" +
										"            int randZ = chunkZ + random.nextInt(16);\n" +
										"            (new WorldGenMinable(%s.%s.getDefaultState(), %s, BlockMatcher.forBlock(Blocks.NETHERRACK))).generate(world, random, new BlockPos(randX, randY, randZ));\n" +
										"        }\n",
										elements[i].getGenerationData().getNether().getWorldRarity(),
										elements[i].getGenerationData().getNether().getHigh() - elements[i].getGenerationData().getNether().getLow(),
										elements[i].getGenerationData().getNether().getLow(),
										titleCase(elements[i].getName(), false) + "NetherOre",
										underscoreCase(elements[i].getName()) + "_nether_ore",
										elements[i].getGenerationData().getNether().getChunkRarity()
								),
								"GenerateSurface", "",
								"GenerateEnd", "",
								
								"hardness", String.valueOf(elements[i].getOreData().getHardness()),
								"resistance", String.valueOf(elements[i].getOreData().getResistance()),
								"lightLevel", String.valueOf(elements[i].getOreData().getLightLevel()),
								"lightOpacity", String.valueOf(elements[i].getOreData().getLightOpacity()),
								"harvestLevel", String.valueOf(elements[i].getOreData().getHarvestLevel()),
								
								"DropDeclaration", elements[i].doesGemDrop()? (
										"		 Item drop;\n" +
										"		 int meta;\n" +
										"        int least_quantity;\n" +
										"        int most_quantity;\n"
								): "",
								"DropInitiate", elements[i].doesGemDrop()? String.format(
										"            this.drop = %s.%s;\n" +
										"            this.meta = 0;\n" +
										"            this.least_quantity = %s;\n" +
										"            this.most_quantity = %s;\n",
										titleCase(elements[i].getName(), false) + capitalize(elements[i].getGem()),
										underscoreCase(elements[i].getName()) + "_" + underscoreCase(elements[i].getGem()),
										elements[i].getLeastQuantity(), elements[i].getMostQuantity()
								): "",
								"DropMethod", elements[i].doesGemDrop()? (
								        "        @Override\n" +
										"		 public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {\n" +
										"		     return this.drop;\n" +
										"		 }\n" +
										"\n" +
										"	  	 @Override\n" +
										"		 public int damageDropped(IBlockState blockstate) {\n" +
										"		     return this.meta;\n" +
										"		 }\n" +
										"\n" +	
										"		 @Override\n" +
										"		 public int quantityDropped(IBlockState blockstate, int fortune, Random random) {\n" +
										"		     if (this.least_quantity >= this.most_quantity)\n" +
										"		         return this.least_quantity;\n" +
										"		     return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);\n" +
										"		 }\n"
								) : (
										"		 @Override\n" +
										"		 public int quantityDropped(Random random) {\n" +
										"		     return 1;\n" +
										"		 }\n"
								),
										
								"Gem", titleCase(elements[i].getGem(), false),
								"gem", underscoreCase(elements[i].getGem())
						}
				);
				
				
				
			}
			// - Only generate Surface Ore if ore generates on Surface
			if(elements[i].getGenerationData().getSurface().doesGenerate()) {
				
				ElementGenerator.logger.log("ModUtils", "Generating Surface Ore class file for element: " + elements[i].getName());
				
				declarations += String.format(		
						"    %1$sOre %2$s_ore = new %1$sOre();\n",
						titleCase(elements[i].getName(), false), underscoreCase(elements[i].getName())
				);
				generateSurfaces += String.format(
						"            %s_ore.generateSurface(world, random, chunkX, chunkZ);\n",
						underscoreCase(elements[i].getName())
				);
				loads += String.format(
						"        %s_ore.load(event);\n",
						underscoreCase(elements[i].getName())
				);
				serverLoads += String.format(
						"        %s_ore.serverLoad(event);\n",
						underscoreCase(elements[i].getName())
				);
				instances += String.format(
						"        %s_ore.instance = this.instance;\n",
						underscoreCase(elements[i].getName())
				);
				preInits += String.format(
						"        %s_ore.preInit(event);\n",
						underscoreCase(elements[i].getName())
				);
				renderers += String.format(
						"        %s.%s_ore.registerRenderers();\n",
						mod.getModId(), underscoreCase(elements[i].getName())
				);
				
				ElementUtils.replaceLines(
						String.format(
								ORE_TEMPLATE,
								mod.getMinecraftVersion().getVersion()
						), 
						String.format(
								JAVA_DIRECTORY, 
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
						) + titleCase(elements[i].getName(), false) + "Ore.java", 
						new String[] {
								"authorId", mod.getAuthorId(),
								"modId", mod.getModId(),
								"ModName", titleCase(mod.getName(), false),
								
								"ElementHere", titleCase(elements[i].getName(), false),
								"elementHere", underscoreCase(elements[i].getName()),
								"ElementNoOre", titleCase(elements[i].getName(), false),
								"elementNoOre", underscoreCase(elements[i].getName()),
								
								"GenerateSurface", String.format(
										"        for(int i = 0; i < %s; i++) {\n" +
										"            int randX = chunkX + random.nextInt(16);\n" +
										"            int randY = random.nextInt(%s) + %s;\n" +
										"            int randZ = chunkZ + random.nextInt(16);\n" +
										"            (new WorldGenMinable(%s.%s.getDefaultState(), %s)).generate(world, random, new BlockPos(randX, randY, randZ));\n" +
										"        }\n",
										elements[i].getGenerationData().getSurface().getWorldRarity(),
										elements[i].getGenerationData().getSurface().getHigh() - elements[i].getGenerationData().getSurface().getLow(),
										elements[i].getGenerationData().getSurface().getLow(),
										titleCase(elements[i].getName(), false) + "Ore",
										underscoreCase(elements[i].getName()) + "_ore",
										elements[i].getGenerationData().getSurface().getChunkRarity()
								),
								"GenerateNether", "",
								"GenerateEnd", "",
								
								"hardness", String.valueOf(elements[i].getOreData().getHardness()),
								"resistance", String.valueOf(elements[i].getOreData().getResistance()),
								"lightLevel", String.valueOf(elements[i].getOreData().getLightLevel()),
								"lightOpacity", String.valueOf(elements[i].getOreData().getLightOpacity()),
								"harvestLevel", String.valueOf(elements[i].getOreData().getHarvestLevel()),
								
								"DropDeclaration", elements[i].doesGemDrop()? (
										"		 Item drop;\n" +
										"		 int meta;\n" +
										"        int least_quantity;\n" +
										"        int most_quantity;\n"
								): "",
								"DropInitiate", elements[i].doesGemDrop()? String.format(
										"            this.drop = %s.%s;\n" +
										"            this.meta = 0;\n" +
										"            this.least_quantity = %s;\n" +
										"            this.most_quantity = %s;\n",
										titleCase(elements[i].getName(), false) + capitalize(elements[i].getGem()),
										underscoreCase(elements[i].getName()) + "_" + underscoreCase(elements[i].getGem()),
										elements[i].getLeastQuantity(), elements[i].getMostQuantity()
								): "",
								"DropMethod", elements[i].doesGemDrop()? (
								        "         @Override\n" +
										"		  public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {\n" +
										"		      return this.drop;\n" +
										"		  }\n" +
										"\n" +
										"	  	  @Override\n" +
										"		  public int damageDropped(IBlockState blockstate) {\n" +
										"		      return this.meta;\n" +
										"		  }\n" +
										"\n" +	
										"		  @Override\n" +
										"		  public int quantityDropped(IBlockState blockstate, int fortune, Random random) {\n" +
										"		      if (this.least_quantity >= this.most_quantity)\n" +
										"		          return this.least_quantity;\n" +
										"		      return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);\n" +
										"		  }\n"
								) : (
										"		  @Override\n" +
										"		  public int quantityDropped(Random random) {\n" +
										"		      return 1;\n" +
										"		  }\n"
								),
										
								"Gem", titleCase(elements[i].getGem(), false),
								"gem", underscoreCase(elements[i].getGem())
						}
				);
				
				
				
				
			}
			// - Only generate End Ore if ore generates in End
			if(elements[i].getGenerationData().getEnd().doesGenerate()) {
				
				ElementGenerator.logger.log("ModUtils", "Generating End Ore class file for element: " + elements[i].getName());
				
				declarations += String.format(		
						"    %1$sEndOre %2$s_end_ore = new %1$sEndOre();\n",
						titleCase(elements[i].getName(), false), underscoreCase(elements[i].getName())
				);
				generateEnds += String.format(
						"            %s_end_ore.generateEnd(world, random, chunkX, chunkZ);\n",
						underscoreCase(elements[i].getName())
				);
				loads += String.format(
						"        %s_end_ore.load(event);\n",
						underscoreCase(elements[i].getName())
				);
				serverLoads += String.format(
						"        %s_end_ore.serverLoad(event);\n",
						underscoreCase(elements[i].getName())
				);
				instances += String.format(
						"        %s_end_ore.instance = this.instance;\n",
						underscoreCase(elements[i].getName())
				);
				preInits += String.format(
						"        %s_end_ore.preInit(event);\n",
						underscoreCase(elements[i].getName())
				);
				renderers += String.format(
						"        %s.%s_end_ore.registerRenderers();\n",
						mod.getModId(), underscoreCase(elements[i].getName())
				);
				
				ElementUtils.replaceLines(
						String.format(
								ORE_TEMPLATE,
								mod.getMinecraftVersion().getVersion()
						), 
						String.format(
								JAVA_DIRECTORY, 
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
						) + titleCase(elements[i].getName(), false) + "EndOre.java", 
						new String[] {
								"authorId", mod.getAuthorId(),
								"modId", mod.getModId(),
								"ModName", titleCase(mod.getName(), false),
								
								"ElementHere", titleCase(elements[i].getName(), false) + "End",
								"elementHere", underscoreCase(elements[i].getName()) + "_end",
								"ElementNoOre", titleCase(elements[i].getName(), false),
								"elementNoOre", underscoreCase(elements[i].getName()),
								
								"GenerateEnd", String.format(
										"        for(int i = 0; i < %s; i++) {\n" +
										"            int randX = chunkX + random.nextInt(16);\n" +
										"            int randY = random.nextInt(%s) + %s;\n" +
										"            int randZ = chunkZ + random.nextInt(16);\n" +
										"            (new WorldGenMinable(%s.%s.getDefaultState(), %s, BlockMatcher.forBlock(Blocks.END_STONE))).generate(world, random, new BlockPos(randX, randY, randZ));\n" +
										"        }\n",
										elements[i].getGenerationData().getEnd().getWorldRarity(),
										elements[i].getGenerationData().getEnd().getHigh() - elements[i].getGenerationData().getEnd().getLow(),
										elements[i].getGenerationData().getEnd().getLow(),
										titleCase(elements[i].getName(), false) + "EndOre",
										underscoreCase(elements[i].getName()) + "_end_ore",
										elements[i].getGenerationData().getEnd().getChunkRarity()
								),
								"GenerateNether", "",
								"GenerateSurface", "",
								
								"hardness", String.valueOf(elements[i].getOreData().getHardness()),
								"resistance", String.valueOf(elements[i].getOreData().getResistance()),
								"lightLevel", String.valueOf(elements[i].getOreData().getLightLevel()),
								"lightOpacity", String.valueOf(elements[i].getOreData().getLightOpacity()),
								"harvestLevel", String.valueOf(elements[i].getOreData().getHarvestLevel()),
								
								"DropDeclaration", elements[i].doesGemDrop()? (
										"		 Item drop;\n" +
										"		 int meta;\n" +
										"        int least_quantity;\n" +
										"        int most_quantity;\n"
								): "",
								"DropInitiate", elements[i].doesGemDrop()? String.format(
										"            this.drop = %s.%s;\n" +
										"            this.meta = 0;\n" +
										"            this.least_quantity = %s;\n" +
										"            this.most_quantity = %s;\n",
										titleCase(elements[i].getName(), false) + capitalize(elements[i].getGem()),
										underscoreCase(elements[i].getName()) + "_" + underscoreCase(elements[i].getGem()),
										elements[i].getLeastQuantity(), elements[i].getMostQuantity()
								): "",
								"DropMethod", elements[i].doesGemDrop()? (
								        "         @Override\n" +
										"		  public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {\n" +
										"		      return this.drop;\n" +
										"		  }\n" +
										"\n" +
										"	  	  @Override\n" +
										"		  public int damageDropped(IBlockState blockstate) {\n" +
										"		      return this.meta;\n" +
										"		  }\n" +
										"\n" +	
										"		  @Override\n" +
										"		  public int quantityDropped(IBlockState blockstate, int fortune, Random random) {\n" +
										"		      if (this.least_quantity >= this.most_quantity)\n" +
										"		          return this.least_quantity;\n" +
										"		      return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);\n" +
										"		  }\n"
								) : (
										"		  @Override\n" +
										"		  public int quantityDropped(Random random) {\n" +
										"		      return 1;\n" +
										"		  }\n"
								),
										
								"Gem", titleCase(elements[i].getGem(), false),
								"gem", underscoreCase(elements[i].getGem())
						}
				);
				
				
				
				
			}
			
			// Generate Block
			ElementGenerator.logger.log("ModUtils", "Generating block class file for element: " + elements[i].getName());
			
			declarations += String.format(		
					"    %1$sBlock %2$s_block = new %1$sBlock();\n",
					titleCase(elements[i].getName(), false), underscoreCase(elements[i].getName())
			);
			loads += String.format(
					"        %s_block.load(event);\n",
					underscoreCase(elements[i].getName())
			);
			serverLoads += String.format(
					"        %s_block.serverLoad(event);\n",
					underscoreCase(elements[i].getName())
			);
			instances += String.format(
					"        %s_block.instance = this.instance;\n",
					underscoreCase(elements[i].getName())
			);
			preInits += String.format(
					"        %s_block.preInit(event);\n",
					underscoreCase(elements[i].getName())
			);
			renderers += String.format(
					"        %s.%s_block.registerRenderers();\n",
					mod.getModId(), underscoreCase(elements[i].getName())
			);
			
			ElementUtils.replaceLines(
					String.format(
							BLOCK_TEMPLATE,
							mod.getMinecraftVersion().getVersion()
					),
					String.format(
							JAVA_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
					) + titleCase(elements[i].getName(), false) + "Block.java", 
					new String[] {
							"authorId", mod.getAuthorId(),
							"modId", mod.getModId(),
							"ModName", titleCase(mod.getName(), false),
							
							"ElementHere", titleCase(elements[i].getName(), false),
							"elementHere", underscoreCase(elements[i].getName()),
							
							"hardness", String.valueOf(elements[i].getBlockData().getHardness()),
							"resistance", String.valueOf(elements[i].getBlockData().getResistance()),
							"lightLevel", String.valueOf(elements[i].getBlockData().getLightLevel()),
							"lightOpacity", String.valueOf(elements[i].getBlockData().getLightOpacity()),
							"harvestLevel", String.valueOf(elements[i].getBlockData().getHarvestLevel()),
							
							"Gem", titleCase(elements[i].getGem(), false),
							"gem", underscoreCase(elements[i].getGem())
					}
			);
			
			
			
			
			// Generate Gem
			ElementGenerator.logger.log("ModUtils", "Generating gem class file for element: " + elements[i].getName());
			
			declarations += String.format(		
					"    %1$s%2$s %3$s_%4$s = new %1$s%2$s();\n",
					titleCase(elements[i].getName(), false), titleCase(elements[i].getGem(), false),  
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			loads += String.format(
					"        %s_%s.load(event);\n",
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			serverLoads += String.format(
					"        %s_%s.serverLoad(event);\n",
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			instances += String.format(
					"        %s_%s.instance = this.instance;\n",
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			preInits += String.format(
					"        %s_%s.preInit(event);\n",
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			renderers += String.format(
					"        %s.%s_%s.registerRenderers();\n",
					mod.getModId(), underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem())
			);
			
			ElementUtils.replaceLines(
					String.format(
							GEM_TEMPLATE,
							mod.getMinecraftVersion().getVersion()
					), 
					String.format(
							JAVA_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
					) + titleCase(elements[i].getName(), false) + titleCase(elements[i].getGem(), false) + ".java", 
					new String[] {
							"authorId", mod.getAuthorId(),
							"modId", mod.getModId(),
							"ModName", titleCase(mod.getName(), false),
							
							"ElementHere", titleCase(elements[i].getName(), false),
							"elementHere", underscoreCase(elements[i].getName()),
							"GemHere", titleCase(elements[i].getGem(), false),
							"gemHere", underscoreCase(elements[i].getGem())
					}
			);
			
			
			
			
			// Generate Tool Classes
			String[] tools = {"pickaxe", "axe", "hoe", "shovel", "sword"};
			for(String tool: tools) {
				Tool targetTool;
				switch(tool.toLowerCase()) {
					case "pickaxe":
						targetTool = elements[i].getToolData().getPickaxe();
						break;
					case "axe":
						targetTool = elements[i].getToolData().getAxe();
						break;
					case "hoe":
						targetTool = elements[i].getToolData().getHoe();
						break;
					case "shovel":
						targetTool = elements[i].getToolData().getShovel();
						break;
					default:
						targetTool = elements[i].getToolData().getSword();
						break;
				}
				
				ElementGenerator.logger.log("ModUtils", "Generating " + tool + " class file for element: " + elements[i].getName());
				
				declarations += String.format(		
						"    %1$s%2$s %3$s_%4$s = new %1$s%2$s();\n",
						titleCase(elements[i].getName(), false), titleCase(tool, false), 
						underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				loads += String.format(
						"        %s_%s.load(event);\n",
						underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				serverLoads += String.format(
						"        %s_%s.serverLoad(event);\n",
						underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				instances += String.format(
						"        %s_%s.instance = this.instance;\n",
						underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				preInits += String.format(
						"        %s_%s.preInit(event);\n",
						underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				renderers += String.format(
						"        %s.%s_%s.registerRenderers();\n",
						mod.getModId(), underscoreCase(elements[i].getName()), underscoreCase(tool)
				);
				
				ElementUtils.replaceLines(
						String.format(
								TOOL_TEMPLATE,
								mod.getMinecraftVersion().getVersion(), capitalize(tool)
						),
						String.format(
								JAVA_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
						) + titleCase(elements[i].getName(), false) + titleCase(tool, false) + ".java",
						new String[] {
								"authorId", mod.getAuthorId(),
								"modId", mod.getModId(),
								"ModName", titleCase(mod.getName(), false),
								
								"ElementHere", titleCase(elements[i].getName(), false),
								"elementHere", underscoreCase(elements[i].getName()),
								
								"harvestLevel", String.valueOf(targetTool.getHarvestLevel()),
								"uses", String.valueOf(targetTool.getUses()),
								"efficiencyF", String.valueOf(targetTool.getEfficiency()),
								"attackDamage", String.valueOf(targetTool.getAttackDamage()),
								"enchantability", String.valueOf(targetTool.getEnchantability()),
								
								"Gem", titleCase(elements[i].getGem(), false),
								"gem", underscoreCase(elements[i].getGem())
						}
				);
				
				
				
				
			}
			
			// Generate Armor Class
			ElementGenerator.logger.log("ModUtils", "Generating armor class file for element: " + elements[i].getName());
			
			declarations += String.format(		
					"    %1$sArmor %2$s_armor = new %1$sArmor();\n",
					titleCase(elements[i].getName(), false), underscoreCase(elements[i].getName())
			);
			loads += String.format(
					"        %s_armor.load(event);\n",
					underscoreCase(elements[i].getName())
			);
			serverLoads += String.format(
					"        %s_armor.serverLoad(event);\n",
					underscoreCase(elements[i].getName())
			);
			instances += String.format(
					"        %s_armor.instance = this.instance;\n",
					underscoreCase(elements[i].getName())
			);
			preInits += String.format(
					"        %s_armor.preInit(event);\n",
					underscoreCase(elements[i].getName())
			);
			renderers += String.format(
					"        %s.%s_armor.registerRenderers();\n",
					mod.getModId(), underscoreCase(elements[i].getName())
			);
			
			ElementUtils.replaceLines(
					String.format(
							ARMOR_TEMPLATE,
							mod.getMinecraftVersion().getVersion()
					),
					String.format(
							JAVA_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
					) + titleCase(elements[i].getName(), false) + "Armor.java",
					new String[] {
							"authorId", mod.getAuthorId(),
							"modId", mod.getModId(),
							"ModName", titleCase(mod.getName(), false),
							
							"ElementHere", titleCase(elements[i].getName(), false),
							"elementHere", underscoreCase(elements[i].getName()),
							
							"maxDamage", String.valueOf(elements[i].getArmorData().getMaxDamage()),
							"enchantability", String.valueOf(elements[i].getArmorData().getEnchantability()),
							"toughness", String.valueOf(elements[i].getArmorData().getToughness()),
							
							"helmetHealth", String.valueOf(elements[i].getArmorData().getHelmet().getHealth()),
							"chestplateHealth", String.valueOf(elements[i].getArmorData().getChestplate().getHealth()),
							"leggingsHealth", String.valueOf(elements[i].getArmorData().getLeggings().getHealth()),
							"bootsHealth", String.valueOf(elements[i].getArmorData().getBoots().getHealth()),
							
							"helmetPotion", (!(
									elements[i].getArmorData().getHelmet().getEffect().equals("NONE") &&
									elements[i].getArmorData().getHelmet().getLevel() == 0 &&
									elements[i].getArmorData().getHelmet().getDuration() == 0
							))? String.format(
									"((EntityLivingBase)player).addPotionEffect(new PotionEffect(MobEffects.%s, %s, %s));\n",
									elements[i].getArmorData().getHelmet().getEffect(),
									elements[i].getArmorData().getHelmet().getDuration(),
									elements[i].getArmorData().getHelmet().getLevel()
							): "",
							"chestplatePotion", (!(
									elements[i].getArmorData().getChestplate().getEffect().equals("NONE") &&
									elements[i].getArmorData().getChestplate().getLevel() == 0 &&
									elements[i].getArmorData().getChestplate().getDuration() == 0
							))? String.format(
									"((EntityLivingBase)player).addPotionEffect(new PotionEffect(MobEffects.%s, %s, %s));\n",
									elements[i].getArmorData().getChestplate().getEffect(),
									elements[i].getArmorData().getChestplate().getDuration(),
									elements[i].getArmorData().getChestplate().getLevel()
							): "",
							"leggingsPotion", (!(
									elements[i].getArmorData().getLeggings().getEffect().equals("NONE") &&
									elements[i].getArmorData().getLeggings().getLevel() == 0 &&
									elements[i].getArmorData().getLeggings().getDuration() == 0
							))? String.format(
									"((EntityLivingBase)player).addPotionEffect(new PotionEffect(MobEffects.%s, %s, %s));\n",
									elements[i].getArmorData().getLeggings().getEffect(),
									elements[i].getArmorData().getLeggings().getDuration(),
									elements[i].getArmorData().getLeggings().getLevel()
							): "",
							"bootsPotion", (!(
									elements[i].getArmorData().getBoots().getEffect().equals("NONE") &&
									elements[i].getArmorData().getBoots().getLevel() == 0 &&
									elements[i].getArmorData().getBoots().getDuration() == 0
							))? String.format(
									"((EntityLivingBase)player).addPotionEffect(new PotionEffect(MobEffects.%s, %s, %s));\n",
									elements[i].getArmorData().getBoots().getEffect(),
									elements[i].getArmorData().getBoots().getDuration(),
									elements[i].getArmorData().getBoots().getLevel()
							): "",
							
							"Gem", titleCase(elements[i].getGem(), false),
							"gem", underscoreCase(elements[i].getGem())
					}
					
			);
			
			
			
			
		}
		
		// Generate ClientProxy
		ElementGenerator.logger.log("ModUtils", "Generating client proxy class file for mod: " + mod.getName());
		
		ElementUtils.replaceLines(
				String.format(
						CLIENT_PROXY_TEMPLATE,
						mod.getMinecraftVersion().getVersion()
				),
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				) + "ClientProxy" + titleCase(mod.getName(), false) + ".java",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"ModName", titleCase(mod.getName(), false),
						
						"Registers", renderers
				}
		);
		
		
		
		
		// Generate CommonProxy
		ElementGenerator.logger.log("ModUtils", "Generating common proxy class file for mod: " + mod.getName());
		ElementUtils.replaceLines(
				String.format(
						COMMON_PROXY_TEMPLATE,
						mod.getMinecraftVersion().getVersion()
				),
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				) + "CommonProxy" + titleCase(mod.getName(), false) + ".java",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"ModName", titleCase(mod.getName(), false)
				}
		);
		
		
		
		
		// Generate GlobalEvents
		ElementGenerator.logger.log("ModUtils", "Generating global events class file for mod: " + mod.getName());
		ElementUtils.replaceLines(
				String.format(
						GLOBAL_EVENTS_TEMPLATE,
						mod.getMinecraftVersion().getVersion()
				),
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				) + "GlobalEvents" + titleCase(mod.getName(), false) + ".java",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"ModName", titleCase(mod.getName(), false)
				}
		);
		
		
		
		
		// Generate VarList
		ElementGenerator.logger.log("ModUtils", "Generating var list class file for mod: " + mod.getName());
		ElementUtils.replaceLines(
				String.format(
						VAR_LIST_TEMPLATE,
						mod.getMinecraftVersion().getVersion()
				),
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				) + "VarList" + titleCase(mod.getName(), false) + ".java",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"ModName", titleCase(mod.getName(), false)
				}
		);
		
		
		
		
		// Generate Main Class
		ElementGenerator.logger.log("ModUtils", "Generating the main class file for element: " + mod.getName());
		Element iconElement = MainPanel.elements[mod.getCreativeTabIconIndex()];
		ElementUtils.replaceLines(
				String.format(
						CLASS_TEMPLATE,
						mod.getMinecraftVersion().getVersion()
				),
				String.format(
						JAVA_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getAuthorId(), mod.getModId()
				) + titleCase(mod.getName(), false) + ".java",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"ModName", titleCase(mod.getName(), false),
						"versionNumber", mod.getVersion(),
						
						"IconName", titleCase(iconElement.getName(), false) + titleCase(iconElement.getGem()),
						"icon_name", underscoreCase(iconElement.getName()) + "_" + underscoreCase(iconElement.getGem()),
						
						"DeclarationsHere", declarations,
						"FuelsHere", fuels,
						"GenerateNethersHere", generateNethers,
						"GenerateSurfacesHere", generateSurfaces,
						"GenerateEndsHere", generateEnds,
						"LoadsHere", loads,
						"ServerLoadHere", serverLoads,
						"InstancesHere", instances,
						"PreInitsHere", preInits
				}
		);
		
		
		
		
	}
	
	/**
	 * Generates all the JSON files needed for the mod
	 * @param mod the Mod object to generate from
	 */
	public static void generateJSONS(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating jsons for mod: " + mod.getName());
		
		// Create the block models, item models, and block states directory
		File blockStatesDirectory = new File(
				String.format(
						BLOCK_STATES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		File blockModelsDirectory = new File(
				String.format(
						BLOCK_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		File itemModelsDirectory = new File(
				String.format(
						ITEM_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		File metaInfDirectory = new File(
				String.format(
						META_INF_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				)
		);
		
		blockStatesDirectory.mkdirs();
		blockModelsDirectory.mkdirs();
		itemModelsDirectory.mkdirs();
		metaInfDirectory.mkdirs();
		
		// Get an array of the elements
		Element[] elements = mod.getElements();
		
		// Iterate through the elements
		for(int i = 0; i < elements.length; i++) {
			
			ElementGenerator.logger.log("ModUtils", "");
			ElementGenerator.logger.log("ModUtils", "****************************************************************************************************");
			ElementGenerator.logger.log("ModUtils", "Generating JSON files for element: " + elements[i].getName());
			
			GenerationData generationData = elements[i].getGenerationData();
			// Generate the Nether Ore
			if(generationData.getNether().doesGenerate()) {
				
				// Generate block state
				ElementGenerator.logger.log("ModUtils", "Generating block state json file for nether ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_STATE_TEMPLATE,
						String.format(
								BLOCK_STATES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_nether_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_nether_ore"
						}
				);
				// Generate block model
				ElementGenerator.logger.log("ModUtils", "Generating block model json file for nether ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_MODEL_TEMPLATE,
						String.format(
								BLOCK_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_nether_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_nether_ore"
						}
				);
				// Generate item model
				ElementGenerator.logger.log("ModUtils", "Generating item model json file for nether ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						ITEM_MODEL_BLOCK_TEMPLATE,
						String.format(
								ITEM_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_nether_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_item", underscoreCase(elements[i].getName()) + "_nether_ore"
						}
				);
				
				
				
			}
			// Generate the Surface Ore
			if(generationData.getSurface().doesGenerate()) {
				
				// Generate block state
				ElementGenerator.logger.log("ModUtils", "Generating block state json file for surface ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_STATE_TEMPLATE,
						String.format(
								BLOCK_STATES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_ore"
						}
				);
				// Generate block model
				ElementGenerator.logger.log("ModUtils", "Generating block model json file for surface ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_MODEL_TEMPLATE,
						String.format(
								BLOCK_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_ore"
						}
				);
				// Generate item model
				ElementGenerator.logger.log("ModUtils", "Generating item model json file for surface ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						ITEM_MODEL_BLOCK_TEMPLATE,
						String.format(
								ITEM_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_item", underscoreCase(elements[i].getName()) + "_ore"
						}
				);
				
				
				
			}
			// Generate the End Ore
			if(generationData.getEnd().doesGenerate()) {
				
				// Generate block state
				ElementGenerator.logger.log("ModUtils", "Generating block state json file for end ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_STATE_TEMPLATE,
						String.format(
								BLOCK_STATES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_end_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_end_ore"
						}
				);
				// Generate block model
				ElementGenerator.logger.log("ModUtils", "Generating block model json file for end ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						BLOCK_MODEL_TEMPLATE,
						String.format(
								BLOCK_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_end_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_block", underscoreCase(elements[i].getName()) + "_end_ore"
						}
				);
				// Generate item model
				ElementGenerator.logger.log("ModUtils", "Generating item model json file for end ore for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						ITEM_MODEL_BLOCK_TEMPLATE,
						String.format(
								ITEM_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_end_ore.json",
						new String[] {
								"modId", mod.getModId(),
								"element_item", underscoreCase(elements[i].getName()) + "_end_ore"
						}
				);
				
				
				
			}
			
			// Generate block
			ElementGenerator.logger.log("ModUtils", "Generating block state json file for block for element: " + elements[i].getName());
			ElementUtils.replaceLines(
					BLOCK_STATE_TEMPLATE,
					String.format(
							BLOCK_STATES_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(elements[i].getName()) + "_block.json",
					new String[] {
							"modId", mod.getModId(),
							"element_block", underscoreCase(elements[i].getName()) + "_block"
					}
			);
			ElementGenerator.logger.log("ModUtils", "Generating block model json file for block for element: " + elements[i].getName());
			ElementUtils.replaceLines(
					BLOCK_MODEL_TEMPLATE,
					String.format(
							BLOCK_MODELS_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(elements[i].getName()) + "_block.json",
					new String[] {
							"modId", mod.getModId(),
							"element_block", underscoreCase(elements[i].getName()) + "_block"
					}
			);
			ElementGenerator.logger.log("ModUtils", "Generating block state json file for block for element: " + elements[i].getName());
			ElementUtils.replaceLines(
					ITEM_MODEL_BLOCK_TEMPLATE,
					String.format(
							ITEM_MODELS_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(elements[i].getName()) + "_block.json",
					new String[] {
							"modId", mod.getModId(),
							"element_item", underscoreCase(elements[i].getName()) + "_block"
					}
			);
			
			
			
			// Generate gem
			ElementGenerator.logger.log("ModUtils", "Generating item model json file for gem for element: " + elements[i].getName());
			ElementUtils.replaceLines(
					ITEM_MODEL_ITEM_TEMPLATE,
					String.format(
							ITEM_MODELS_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(elements[i].getName()) + "_" + underscoreCase(elements[i].getGem()) + ".json",
					new String[] {
							"modId", mod.getModId(),
							"element_item", underscoreCase(elements[i].getName()) + "_" + underscoreCase(elements[i].getGem())
					}
			);
			
			
			
			
			// Generate tools & armor
			String[] tools = new String[] {"pickaxe", "axe", "hoe", "shovel", "sword", "helmet", "chestplate", "leggings", "boots"};
			for(String tool: tools) {
				ElementGenerator.logger.log("ModUtils", "Generating item model json file for " + tool + " for element: " + elements[i].getName());
				ElementUtils.replaceLines(
						ITEM_MODEL_ITEM_TEMPLATE,
						String.format(
								ITEM_MODELS_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(elements[i].getName()) + "_" + underscoreCase(tool) + ".json",
						new String[] {
								"modId", mod.getModId(),
								"element_item", underscoreCase(elements[i].getName()) + "_" + underscoreCase(tool)
						}
				);
				
				
				
			}
		}
		
		// Copy the standard_item.json
		ElementGenerator.logger.log("ModUtils", "Copying standard_item.json for mod: " + mod.getName());
		ElementUtils.copyFile(
				STANDARD_ITEM, 
				String.format(
						ITEM_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		
		
		
		
		// Copy the block.json
		ElementGenerator.logger.log("ModUtils", "Copying block.json for mod: " + mod.getName());
		ElementUtils.copyFile(
				BLOCK, 
				String.format(
						BLOCK_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		
		
		
		
		// Copy the cube.json
		ElementGenerator.logger.log("ModUtils", "Generating cube.json for mod: " + mod.getName());
		ElementUtils.replaceLines(
				CUBE,
				String.format(
						BLOCK_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				) + "cube.json",
				new String[] {
						"modId", mod.getModId()
				}
		);
		
		
		
		
		// Copy the cube_all.json
		ElementGenerator.logger.log("ModUtils", "Generating cube_all.json for mod: " + mod.getName());
		ElementUtils.replaceLines(
				CUBE_ALL,
				String.format(
						BLOCK_MODELS_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				) + "cube_all.json",
				new String[] {
						"modId", mod.getModId()
				}
		);
		
		
		
		
		// Copy the manifest.mf
		ElementGenerator.logger.log("ModUtils", "Copying manifest.mf for mod: " + mod.getName());
		ElementUtils.copyFile(
				MANIFEST,
				String.format(
						META_INF_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				)
		);
		
		
		
	}
	
	/**
	 * Generates all the mod files needed for the mod (build.gradle, pack.mcmeta, mcmod.info)
	 * @param mod the Mod object to generate from
	 */
	public static void generateModFiles(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating mod files for mod: " + mod.getName());
		
		// Create the modDirectory
		File modDirectory = new File(
				String.format(
						MOD_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				)
		);
		modDirectory.mkdirs();
		
		// Generate build.gradle
		ElementGenerator.logger.log("ModUtils", "Generating build.gradle for mod: " + mod.getName());
		ElementUtils.replaceLines(
				BUILD_GRADLE_TEMPLATE,
				String.format(
						MOD_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				) + "build.gradle",
				new String[] {
						"authorId", mod.getAuthorId(),
						"modId", mod.getModId(),
						"modName", mod.getName(),
						
						"forgeSnapshot", mod.getMinecraftVersion().getForgeSnapshot(),
						"minecraftVersionNoBuild", mod.getMinecraftVersion().getVersion(),
						"javaVersion", mod.getMinecraftVersion().getJava(),
						"minecraftVersionWithBuild", mod.getMinecraftVersion().getVersion(true),
						"snapshotMapping", mod.getMinecraftVersion().getSnapshot()
				}
		);
		
		
		
		
		// Generate mcmod.info
		ElementGenerator.logger.log("ModUtils", "Generating mcmod.info for mod: " + mod.getName());
		ElementUtils.replaceLines(
				MC_MOD_TEMPLATE,
				String.format(
						RESOURCES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				) + "mcmod.info",
				new String[] {
						"modIdHere", mod.getModId(),
						"modNameHere", mod.getName(),
						"descriptionHere", mod.getDescription().replace("\n", "\\n"),
						"versionHere", mod.getVersion(),
						"creditsHere", mod.getCredits(),
						"versionMcHere", mod.getMinecraftVersion().getVersion(),
						"urlHere", mod.getURL(),
						"updateUrlHere", mod.getUpdateURL(),
						"authorsHere", mod.getAuthor().getAuthorAsString()
				}
		);
		
		
		
		
		// Generate pack.mcmeta
		ElementGenerator.logger.log("ModUtils", "Generating pack.mcmeta for mod: " + mod.getName());
		ElementUtils.replaceLines(
				PACK_TEMPLATE,
				String.format(
						RESOURCES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion()
				) + "pack.mcmeta",
				new String[] {
						"MOD_NAME", mod.getName()
				}
		);
		
		
		
	
	}
	
	/**
	 * Generates all the texture files needed for the mod
	 * @param mod the Mod object to generate from
	 */
	public static void generateTextures(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating texture files for mod: " + mod.getName());
		
		// Create the block, item, and armor model texture directories
		File blockTextureDirectory = new File(
				String.format(
						BLOCK_TEXTURES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		File itemTextureDirectory = new File(
				String.format(
						ITEM_TEXTURES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		File armorTextureDirectory = new File(
				String.format(
						ARMOR_TEXTURES_DIRECTORY,
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		
		blockTextureDirectory.mkdirs();
		itemTextureDirectory.mkdirs();
		armorTextureDirectory.mkdirs();
		
		// Get an array of the elements
		Element[] elements = mod.getElements();
		
		// Iterate through the elements
		for(Element element: elements) {
			
			ElementGenerator.logger.log("ModUtils", "");
			ElementGenerator.logger.log("ModUtils", "****************************************************************************************************");
			ElementGenerator.logger.log("ModUtils", "Generating textures for element: " + element.getName());
			
			GenerationData generationData = element.getGenerationData();
			Color[] elementGradient = ElementUtils.getGradient(element.getColorData().getDarkestColor(), element.getColorData().getLightestColor());
			Color[] replacementColors = new Color[elementGradient.length * 2];
			
			for(int i = 0; i < replacementColors.length; i += 2) {
				replacementColors[i] = TEMPLATE_COLORS[i/2];
				replacementColors[i + 1] = elementGradient[i/2];
			}
			
			// Generate Nether Ore
			if(generationData.getNether().doesGenerate()) {
				
				// Generate ore
				ElementGenerator.logger.log("ModUtils", "Generating texture for nether ore for element: " + element.getName());
				ElementUtils.replaceColors(
						String.format(
								NETHER_ORE_TEXTURE,
								underscoreCase(element.getGem())
						),
						String.format(
								BLOCK_TEXTURES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(element.getName()) + "_nether_ore.png",
						replacementColors
				);
				
				
				
				
			}
			// Generate Surface Ore
			if(generationData.getSurface().doesGenerate()) {
				
				// Generate ore
				ElementGenerator.logger.log("ModUtils", "Generating texture for surface ore for element: " + element.getName());
				ElementUtils.replaceColors(
						String.format(
								SURFACE_ORE_TEXTURE,
								underscoreCase(element.getGem())
						),
						String.format(
								BLOCK_TEXTURES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(element.getName()) + "_ore.png",
						replacementColors
				);
				
				
				
				
			}
			// Generate End Ore
			if(generationData.getEnd().doesGenerate()) {
				
				// Generate ore
				ElementGenerator.logger.log("ModUtils", "Generating texture for end ore for element: " + element.getName());
				ElementUtils.replaceColors(
						String.format(
								END_ORE_TEXTURE,
								underscoreCase(element.getGem())
						),
						String.format(
								BLOCK_TEXTURES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(element.getName()) + "_end_ore.png",
						replacementColors
				);
				
				
				
				
			}
			
			// Generate Block
			ElementGenerator.logger.log("ModUtils", "Generating texture for block for element: " + element.getName());
			ElementUtils.replaceColors(
					String.format(
							BLOCK_TEXTURE,
							underscoreCase(element.getGem())
					),
					String.format(
							BLOCK_TEXTURES_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(element.getName()) + "_block.png",
					replacementColors
			);
			
			
			
			
			// Generate Gem
			ElementGenerator.logger.log("ModUtils", "Generating texture for gem for element: " + element.getName());
			ElementUtils.replaceColors(
					String.format(
							GEM_TEXTURE,
							underscoreCase(element.getGem())
					),
					String.format(
							ITEM_TEXTURES_DIRECTORY,
							mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
					) + underscoreCase(element.getName()) + "_" + underscoreCase(element.getGem()) + ".png",
					replacementColors
			);
			
			
			
			
			// Generate Tools & Armor
			String[] tools = {"pickaxe", "axe", "hoe", "shovel", "sword", "helmet", "chestplate", "leggings", "boots"};
			for(String tool: tools) {
				
				// Generate tool (or armor)
				ElementGenerator.logger.log("ModUtils", "Generating texture for " + tool + " for element: " + element.getName());
				ElementUtils.replaceColors(
						String.format(
								TOOL_TEXTURE,
								tool
						),
						String.format(
								ITEM_TEXTURES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(element.getName()) + "_" + underscoreCase(tool) + ".png",
						replacementColors
				);
				
				
				
				
			}
			
			// Generate Armor Layers
			String[] layers = {"1", "2"};
			for(String layer: layers) {
				
				// Generate layer
				ElementGenerator.logger.log("ModUtils", "Generating texture for armor layer " + layer + " for element: " + element.getName());
				ElementUtils.replaceColors(
						String.format(
								ARMOR_MODEL_TEXTURE,
								layer
						),
						String.format(
								ARMOR_TEXTURES_DIRECTORY,
								mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
						) + underscoreCase(element.getName()) + "_layer_" + layer + ".png",
						replacementColors
				);
				
				
				
				
			}
		}
		
	}

	/**
	 * Generates the en_us.lang file needed for the mod
	 * @param mod the Mod object to generate from
	 */
	public static void generateLang(Mod mod) {
		
		ElementGenerator.logger.log("ModUtils", "Generating en_us.lang file for mod: " + mod.getName());
		
		// Create the lang directory
		File langDirectory = new File(
				String.format(
						LANG_DIRECTORY, 
						mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
				)
		);
		langDirectory.mkdirs();
		
		// Get an array of the elements
		Element[] elements = mod.getElements();
		String langLines = "";
		
		// Iterate through the elements
		for(int i = 0; i < elements.length; i++) {
			
			ElementGenerator.logger.log("ModUtils", "");
			ElementGenerator.logger.log("ModUtils", "****************************************************************************************************");
			ElementGenerator.logger.log("ModUtils", "Generating language lines for element: " + elements[i].getName());
			
			// Generate the Nether Ore
			if(elements[i].getGenerationData().getNether().doesGenerate()) {
				ElementGenerator.logger.log("ModUtils", "Generating language line for nether ore for element: " + elements[i].getName());
				langLines += String.format(
						"tile.%s_nether_ore.name=%s Ore\n",
						underscoreCase(elements[i].getName()), titleCase(elements[i].getName())
				);
			}
			// Generate the Surface Ore
			if(elements[i].getGenerationData().getSurface().doesGenerate()) {
				ElementGenerator.logger.log("ModUtils", "Generating language line for surface ore for element: " + elements[i].getName());
				langLines += String.format(
						"tile.%s_ore.name=%s Ore\n",
						underscoreCase(elements[i].getName()), titleCase(elements[i].getName())
				);
			}
			// Generate the End Ore
			if(elements[i].getGenerationData().getEnd().doesGenerate()) {
				ElementGenerator.logger.log("ModUtils", "Generating language line for end ore for element: " + elements[i].getName());
				langLines += String.format(
						"tile.%s_end_ore.name=%s Ore\n",
						underscoreCase(elements[i].getName()), titleCase(elements[i].getName())
				);
			}
			// Generate the Block
			ElementGenerator.logger.log("ModUtils", "Generating language line for block for element: " + elements[i].getName());
			langLines += String.format(
					"tile.%s_block.name=%s Block\n",
					underscoreCase(elements[i].getName()), titleCase(elements[i].getName())
			);
			
			// Generate the Gem
			ElementGenerator.logger.log("ModUtils", "Generating language line for gem for element: " + elements[i].getName());
			langLines += String.format(
					"item.%s_%s.name=%s %s\n",
					underscoreCase(elements[i].getName()), underscoreCase(elements[i].getGem()), 
					titleCase(elements[i].getName()), titleCase(elements[i].getGem())
			);
			
			// Generate the Tools & Armor
			String[] tools = {"pickaxe", "axe", "hoe", "shovel", "sword", "helmet", "chestplate", "leggings", "boots"};
			for(String tool: tools) {
				ElementGenerator.logger.log("ModUtils", "Generating language line for " + tool + " for element: " + elements[i].getName());
				langLines += String.format(
						"item.%s_%s.name=%s %s\n",
						underscoreCase(elements[i].getName()), underscoreCase(tool), 
						titleCase(elements[i].getName()), titleCase(tool)
				);
			}
		}
			
		// Add the Tab Name
		ElementGenerator.logger.log("ModUtils", "Generating language line for creative tab for mod: " + mod.getName());
		langLines += String.format(
				"itemGroup.%s=%s\n",
				mod.getModId(), mod.getName()
		);
		
		// Write lang lines to file
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(
							String.format(
									LANG_DIRECTORY,
									mod.getName(), mod.getMinecraftVersion().getVersion(), mod.getModId()
							) + "en_us.lang"
					)
			);
			
			writer.write(langLines);
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a capitalized String
	 * @param string the String to capitalize
	 * @return String - the capitalized String
	 */
	public static String capitalize(String string) {
		return (
				string.substring(0, 1).toUpperCase() + 	// Capitalize the first letter 
				string.substring(1).toLowerCase() 		// Lower the rest of the string
		);
	}
	
	/**
	 * Returns String with each word lowered and underscores separating them (i.e. "sionis industries" becomes "sionis_industries")
	 * @param string the String to create the underscore case from
	 * @param keepUnderscore whether or not to keep the space in between each word
	 * @return String - the underscore case String
	 */
	public static String underscoreCase(String string, boolean keepUnderscore) {
		String[] words = string.split(" ");
		String result = "";
		for(int i = 0; i < words.length; i++) {
			result += words[i].toLowerCase();
			if(i < words.length - 1 && keepUnderscore)
				result += "_";
		}
		return result;
	}
	
	/**
	 * Returns String with each word lowered and underscores separating them (i.e. "sionis industries" becomes "sionis_industries")
	 * @param string the String to create the underscore case from
	 * @return String - the underscore case String
	 */
	public static String underscoreCase(String string) {
		return underscoreCase(string, true);
	}
	
	/**
	 * Returns a String with each word capitalized (i.e. "sionis industries" becomes "Sionis Industries")
	 * @param string the String to create the title case from
	 * @param keepSpace whether or not to keep the space in between each word
	 * @return String - the title case String
	 */
	public static String titleCase(String string, boolean keepSpace) {
		String[] words = string.split(" ");
		String result = "";
		for(int i = 0; i < words.length; i++) {
			result += capitalize(words[i]);
			if(i < words.length - 1 && keepSpace)
				result += " ";
		}
		return result;
	}
	
	/**
	 * Returns a String with each word capitalized (i.e. "sionis industries" becomes "Sionis Industries")
	 * @param string the String to create the title case from
	 * @return String - the title case String
	 */
	public static String titleCase(String string) {
		return titleCase(string, true);
	}
	
	/**
	 * Appends an item to an array of String's and returns the new array
	 * @param oldArray the array to add to
	 * @param newItem the new item to add
	 * @return String[]
	 */
	public static String[] append(String[] oldArray, String newItem) {
		String[] newArray = new String[oldArray.length + 1];
		for(int i = 0; i < oldArray.length; i++)
			newArray[i] = oldArray[i];
		newArray[newArray.length - 1] = newItem;
		return newArray.clone();
	}

}
