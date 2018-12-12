package elementgenerator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import elementgenerator.element.Element;
import elementgenerator.gui.customcomponents.Button;
import elementgenerator.gui.customcomponents.ComboBox;
import elementgenerator.gui.customcomponents.Label;
import elementgenerator.gui.customcomponents.Separator;
import elementgenerator.gui.customcomponents.Spinner;
import elementgenerator.tool.Armor;
import elementgenerator.tool.ArmorData;
import elementgenerator.tool.Tool;

/**
 * A ToolPanel to be used in the ElementGenerator GUI class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ToolPanel {
	
	// Spinner Models
	// - Tool Spinners
	private static final SpinnerNumberModel HARVEST_LEVEL_SPINNER_MODEL = new SpinnerNumberModel(
			0, // Initial Value
			0, // Minimum
			7, // Maximum
			1  // Step Value
	);
	private static final SpinnerNumberModel EFFICIENCY_SPINNER_MODEL = new SpinnerNumberModel(
			0,  // Initial Value
			0,  // Minimum
			50,	// Maximum
			1	// Step Value
	);
	private static final SpinnerNumberModel ATTACK_DAMAGE_SPINNER_MODEL = new SpinnerNumberModel(
			0,	// Initial Value
			0,	// Minimum
			30, // Maximum
			1	// Step Value
	);
	private static final SpinnerNumberModel USES_SPINNER_MODEL = new SpinnerNumberModel(
			0,		// Initial Value
			0,		// Minimum
			16384,	// Maximum
			1 		// Step Value
	);
	// - Armor Models
	private static final SpinnerNumberModel MAX_DAMAGE_SPINNER_MODEL = new SpinnerNumberModel(
			0,		// Initial Value
			0,		// Minimum
			16384,	// Maximum
			1 		// Step Value
	);
	private static final SpinnerNumberModel TOUGHNESS_SPINNER_MODEL = new SpinnerNumberModel(
			0.0,	// Initial Value
			0.0,	// Minimum
			60.0,	// Maximum
			1.0		// Step Value
	);
	private static final SpinnerNumberModel HEALTH_SPINNER_MODEL = new SpinnerNumberModel(
			0,		// Initial Value
			0,		// Minimum
			null,	// Maximum
			1		// Step Value
	);
	private static final SpinnerNumberModel LEVEL_SPINNER_MODEL = new SpinnerNumberModel(
			0,		// Initial Value
			0,		// Minimum
			null,	// Maximum
			1		// Step Value
	);
	private static final SpinnerNumberModel DURATION_SPINNER_MODEL = new SpinnerNumberModel(
			0,		// Initial Value
			0,		// Minimum
			null,	// Maximum
			1		// Step Value
	);
	
	// Combo Box Arrays
	public static String[] ARMOR_EFFECTS = new String[] {
			"NONE",
			"ABSORPTION", "BLINDNESS", "FIRE_RESISTANCE", "GLOWING", "HASTE", "HEALTH_BOOST",
			"INSTANT_DAMAGE", "INSTANT_HEALTH", "INVISIBILITY", "JUMP_BOOST", "LEVITATION", "LUCK",
			"MINING_FATIGUE", "NAUSEA", "NIGHT_VISION", "POISON", "REGENERATION", "RESISTANCE", "SATURATION",
			"SLOWNESS", "SPEED", "STRENGTH", "UNLUCK", "WATER_BREATHING", "WEAKNESS", "WITHER"
	};
	
	// Main Panel
	public static JPanel panel;
	
	public static JButton toolFinishButton;
	
	// Tool Panel
	private static JPanel toolPanel;
	
	public static JSpinner pickaxeHarvestLevelSpinner;
	public static JSpinner pickaxeEfficiencySpinner;
	public static JSpinner pickaxeAttackDamageSpinner;
	public static JSpinner pickaxeUsesSpinner;
	
	public static JSpinner axeHarvestLevelSpinner;
	public static JSpinner axeEfficiencySpinner;
	public static JSpinner axeAttackDamageSpinner;
	public static JSpinner axeUsesSpinner;
	
	public static JSpinner hoeHarvestLevelSpinner;
	public static JSpinner hoeEfficiencySpinner;
	public static JSpinner hoeAttackDamageSpinner;
	public static JSpinner hoeUsesSpinner;
	
	public static JSpinner shovelHarvestLevelSpinner;
	public static JSpinner shovelEfficiencySpinner;
	public static JSpinner shovelAttackDamageSpinner;
	public static JSpinner shovelUsesSpinner;
	
	public static JSpinner swordHarvestLevelSpinner;
	public static JSpinner swordEfficiencySpinner;
	public static JSpinner swordAttackDamageSpinner;
	public static JSpinner swordUsesSpinner;
	
	// Armor Panel
	private static JPanel armorPanel;
	
	public static JSpinner maxDamageSpinner;
	public static JSpinner toughnessSpinner;
	
	public static JSpinner helmetHealthSpinner;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox helmetEffectComboBox;
	public static JSpinner helmetLevelSpinner;
	public static JSpinner helmetDurationSpinner;
	
	public static JSpinner chestplateHealthSpinner;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox chestplateEffectComboBox;
	public static JSpinner chestplateLevelSpinner;
	public static JSpinner chestplateDurationSpinner;
	
	public static JSpinner leggingsHealthSpinner;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox leggingsEffectComboBox;
	public static JSpinner leggingsLevelSpinner;
	public static JSpinner leggingsDurationSpinner;
	
	public static JSpinner bootsHealthSpinner;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox bootsEffectComboBox;
	public static JSpinner bootsLevelSpinner;
	public static JSpinner bootsDurationSpinner;
	
	/**
	 * Sets up the JComponents in the panel
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the panel");
		
		// Setup panel
		panel = new JPanel();
		
		// Setup layout
		GridBagLayout gbl_toolsArmorPanel = new GridBagLayout();
		gbl_toolsArmorPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_toolsArmorPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_toolsArmorPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_toolsArmorPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		
		// Set Layout
		panel.setLayout(gbl_toolsArmorPanel);
		
		// *****************************************************
		
		// Back Button
		Button BACK_BUTTON = new Button(
				"Back", "Goes to previous page of the element generator.",
				GridBagConstraints.WEST, 0, 0, 5, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						ElementGenerator.changeMenu("generation");
					}
				}
		);
		panel.add(BACK_BUTTON.getButton(), BACK_BUTTON.getConstraints());
		
		// Finish Button
		Button FINISH_BUTTON = new Button(
				"Finish", "Creates the new element.",
				GridBagConstraints.EAST, 0, 2, 5, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						MainPanel.addElement(MainPanel.INDEX);
						ElementGenerator.changeMenu("main");
					}
				}
		);
		toolFinishButton = FINISH_BUTTON.getButton();
		panel.add(toolFinishButton, FINISH_BUTTON.getConstraints());
		
		// Cancel Button
		Button CANCEL_BUTTON = new Button(
				"Cancel", "Cancels making a new element.",
				0, 0, 1, 5, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						BasicPanel.reset();
						GenerationPanel.reset();
						ToolPanel.reset();
						ElementGenerator.changeMenu("main");
					}
				}
		);
		panel.add(CANCEL_BUTTON.getButton(), CANCEL_BUTTON.getConstraints());
		
		setupTools();
		setupArmor();
		
	}
	
	/**
	 * Sets up the JComponents in the tool panel
	 */
	public static void setupTools() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the tool panel");
		
		// Setup Tool Panel
		toolPanel = new JPanel();
		
		// Setup constraints for the panel
		GridBagConstraints gbc_toolPanel = new GridBagConstraints();
		gbc_toolPanel.insets = new Insets(0, 0, 5, 5);
		gbc_toolPanel.gridx = 1;
		gbc_toolPanel.gridy = 0;
		
		// Setup layout
		GridBagLayout gbl_toolPanel = new GridBagLayout();
		gbl_toolPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_toolPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_toolPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_toolPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		toolPanel.setLayout(gbl_toolPanel);
		
		// Add Tool Panel to panel
		panel.add(toolPanel, gbc_toolPanel);
		
		// ***************************************************************
		
		// Tools Title Label
		Label TOOLS_TITLE_LABEL = new Label(
				"Tools", "", null,
				GridBagConstraints.EAST, 0, 0, 0, 7, 1,
				ElementGenerator.getTitleFont()
		);
		toolPanel.add(TOOLS_TITLE_LABEL.getLabel(), TOOLS_TITLE_LABEL.getConstraints());
		
		// Auto Fill Tools Button
		Button AUTO_FILL_TOOLS_BUTTON = new Button(
				"Autofill Tools", "Autofill the tool spinners base on the harvest level of the ore.",
				GridBagConstraints.WEST, 0, 7, 0, 7, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						autoFillTools();
					}
				}
		);
		toolPanel.add(AUTO_FILL_TOOLS_BUTTON.getButton(), AUTO_FILL_TOOLS_BUTTON.getConstraints());
		
		// Tool Upper Separator
		Separator TOOL_UPPER_SEPARATOR = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 1, 14, 1
		);
		toolPanel.add(TOOL_UPPER_SEPARATOR.getSeparator(), TOOL_UPPER_SEPARATOR.getConstraints());
		
		// Setup the pickaxe Components
		setupPickaxe();
		
		// Pickaxe and Axe Separator
		Separator PICKAXE_AXE_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 2, 2, 1, 5
		);
		toolPanel.add(PICKAXE_AXE_SEPARATOR.getSeparator(), PICKAXE_AXE_SEPARATOR.getConstraints());
		
		// Setup the axe Components
		setupAxe();
		
		// Axe and Hoe Separator
		Separator AXE_HOE_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 5, 2, 1, 5
		);
		toolPanel.add(AXE_HOE_SEPARATOR.getSeparator(), AXE_HOE_SEPARATOR.getConstraints());
		
		// Setup the hoe Components
		setupHoe();
		
		// Hoe and Shovel Separator
		Separator HOE_SHOVEL_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 8, 2, 1, 5
		);
		toolPanel.add(HOE_SHOVEL_SEPARATOR.getSeparator(), HOE_SHOVEL_SEPARATOR.getConstraints());
		
		// Setup the shovel Components
		setupShovel();
		
		// Shovel and Sword Separator
		Separator SHOVEL_SWORD_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 11, 2, 1, 5
		);
		toolPanel.add(SHOVEL_SWORD_SEPARATOR.getSeparator(), SHOVEL_SWORD_SEPARATOR.getConstraints());
		
		// Setup the sword Components
		setupSword();
		
	}
	
	/**
	 * Sets up all the pickaxe components
	 */
	public static void setupPickaxe() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the pickaxe components");
		
		// Pickaxe Subtitle Label
		Label PICKAXE_LABEL = new Label(
				"Pickaxe", "", null,
				0, 0, 0, 2, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		toolPanel.add(PICKAXE_LABEL.getLabel(), PICKAXE_LABEL.getConstraints());
		
		// Pickaxe Harvest Level Label
		Label PICKAXE_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				GridBagConstraints.WEST, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(PICKAXE_HARVEST_LEVEL_LABEL.getLabel(), PICKAXE_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Pickaxe Harvest Level Spinner
		Spinner PICKAXE_HARVEST_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(HARVEST_LEVEL_SPINNER_MODEL), "<html>\r\nThe harvest level of the pickaxe\r\n<p>\r\n0 is like a wooden pickaxe\r\n<p>\r\n1 is like a stone pickaxe\r\n<p>\r\n2 is like an iron or gold pickaxe\r\n<p>\r\n3 is like a diamond pickaxe\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		pickaxeHarvestLevelSpinner = PICKAXE_HARVEST_LEVEL_SPINNER.getSpinner();
		toolPanel.add(pickaxeHarvestLevelSpinner, PICKAXE_HARVEST_LEVEL_SPINNER.getConstraints());
		
		// Pickaxe efficiency label
		Label PICKAXE_EFFICIENCY_LABEL = new Label(
				"Efficiency", "", null,
				GridBagConstraints.WEST, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(PICKAXE_EFFICIENCY_LABEL.getLabel(), PICKAXE_EFFICIENCY_LABEL.getConstraints());
		
		// Pickaxe Efficiency spinner
		Spinner PICKAXE_EFFICIENCY_SPINNER = new Spinner(
				ElementGenerator.copy(EFFICIENCY_SPINNER_MODEL), "The efficiency of the pickaxe.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		pickaxeEfficiencySpinner = PICKAXE_EFFICIENCY_SPINNER.getSpinner();
		toolPanel.add(pickaxeEfficiencySpinner, PICKAXE_EFFICIENCY_SPINNER.getConstraints());
		
		// Pickaxe Attack Damage label
		Label PICKAXE_ATTACK_DAMAGE_LABEL = new Label(
				"Attack damage", "", null,
				GridBagConstraints.WEST, 0, 0, 5, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(PICKAXE_ATTACK_DAMAGE_LABEL.getLabel(), PICKAXE_ATTACK_DAMAGE_LABEL.getConstraints());
		
		// Pickaxe Attack Damage Spinner
		Spinner PICKAXE_ATTACK_DAMAGE_SPINNER = new Spinner(
				ElementGenerator.copy(ATTACK_DAMAGE_SPINNER_MODEL), "The amount of damage dealt when attacking an entity.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		pickaxeAttackDamageSpinner = PICKAXE_ATTACK_DAMAGE_SPINNER.getSpinner();
		toolPanel.add(pickaxeAttackDamageSpinner, PICKAXE_ATTACK_DAMAGE_SPINNER.getConstraints());
		
		// Pickaxe Uses Label
		Label PICKAXE_USES_LABEL = new Label(
				"Uses", "", null,
				GridBagConstraints.WEST, 0, 0, 6, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(PICKAXE_USES_LABEL.getLabel(), PICKAXE_USES_LABEL.getConstraints());
		
		// Pickaxe Uses Spinner
		Spinner PICKAXE_USES_SPINNER = new Spinner(
				ElementGenerator.copy(USES_SPINNER_MODEL), "The amount of uses until the pickaxe breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		pickaxeUsesSpinner = PICKAXE_USES_SPINNER.getSpinner();
		toolPanel.add(pickaxeUsesSpinner, PICKAXE_USES_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the axe components
	 */
	public static void setupAxe() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the axe components");
		
		// Axe Title Label
		Label AXE_LABEL = new Label(
				"Axe", "", null,
				0, 0, 3, 2, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		toolPanel.add(AXE_LABEL.getLabel(), AXE_LABEL.getConstraints());
		
		// Axe Harvest Level Label
		Label AXE_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				GridBagConstraints.WEST, 0, 3, 3, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(AXE_HARVEST_LEVEL_LABEL.getLabel(), AXE_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Axe Harvest Level Spinner
		Spinner AXE_HARVEST_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(HARVEST_LEVEL_SPINNER_MODEL), "<html>\r\nThe harvest level of the axe\r\n<p>\r\n0 is like a wooden axe\r\n<p>\r\n1 is like a stone axe\r\n<p>\r\n2 is like an iron or gold axe\r\n<p>\r\n3 is like a diamond axe\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		axeHarvestLevelSpinner = AXE_HARVEST_LEVEL_SPINNER.getSpinner();
		toolPanel.add(axeHarvestLevelSpinner, AXE_HARVEST_LEVEL_SPINNER.getConstraints());
		
		// Axe Efficiency Label
		Label AXE_EFFICIENCY_LABEL = new Label(
				"Efficiency", "", null,
				GridBagConstraints.WEST, 0, 3, 4, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(AXE_EFFICIENCY_LABEL.getLabel(), AXE_EFFICIENCY_LABEL.getConstraints());
		
		// Axe Efficiency Spinner
		Spinner AXE_EFFICIENCY_SPINNER = new Spinner(
				ElementGenerator.copy(EFFICIENCY_SPINNER_MODEL), "The efficiency of the axe.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		axeEfficiencySpinner = AXE_EFFICIENCY_SPINNER.getSpinner();
		toolPanel.add(axeEfficiencySpinner, AXE_EFFICIENCY_SPINNER.getConstraints());
		
		// Axe Attack Damage Label
		Label AXE_ATTACK_DAMAGE_LABEL = new Label(
				"Attack Damage", "", null,
				GridBagConstraints.WEST, 0, 3, 5, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(AXE_ATTACK_DAMAGE_LABEL.getLabel(), AXE_ATTACK_DAMAGE_LABEL.getConstraints());
		
		// Axe Attack Damage Spinner
		Spinner AXE_ATTACK_DAMAGE_SPINNER = new Spinner(
				ElementGenerator.copy(ATTACK_DAMAGE_SPINNER_MODEL), "The amount of damage dealth when attacking an entity.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		axeAttackDamageSpinner = AXE_ATTACK_DAMAGE_SPINNER.getSpinner();
		toolPanel.add(axeAttackDamageSpinner, AXE_ATTACK_DAMAGE_SPINNER.getConstraints());
		
		// Axe Uses Label
		Label AXE_USES_LABEL = new Label(
				"Uses", "", null,
				GridBagConstraints.WEST, 0, 3, 6, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(AXE_USES_LABEL.getLabel(), AXE_USES_LABEL.getConstraints());
		
		// Axe Uses Spinner
		Spinner AXE_USES_SPINNER = new Spinner(
				ElementGenerator.copy(USES_SPINNER_MODEL), "The amount of uses until the axe breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		axeUsesSpinner = AXE_USES_SPINNER.getSpinner();
		toolPanel.add(axeUsesSpinner, AXE_USES_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the hoe components
	 */
	public static void setupHoe() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the hoe components");
		
		// Hoe Title Label
		Label HOE_LABEL = new Label(
				"Hoe", "", null,
				0, 0, 6, 2, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		toolPanel.add(HOE_LABEL.getLabel(), HOE_LABEL.getConstraints());
		
		// Hoe Harvest Level Label
		Label HOE_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				GridBagConstraints.WEST, 0, 6, 3, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(HOE_HARVEST_LEVEL_LABEL.getLabel(), HOE_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Hoe Harvest Level Spinner
		Spinner HOE_HARVEST_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(HARVEST_LEVEL_SPINNER_MODEL), "<html>\r\nThe harvest level of the hoe\r\n<p>\r\n0 is like a wooden hoe\r\n<p>\r\n1 is like a stone hoe\r\n<p>\r\n2 is like an iron or gold hoe\r\n<p>\r\n3 is like a diamond hoe\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		hoeHarvestLevelSpinner = HOE_HARVEST_LEVEL_SPINNER.getSpinner();
		toolPanel.add(hoeHarvestLevelSpinner, HOE_HARVEST_LEVEL_SPINNER.getConstraints());
		
		// Hoe Efficiency Label
		Label HOE_EFFICIENCY_LABEL = new Label(
				"Efficiency", "", null,
				GridBagConstraints.WEST, 0, 6, 4, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(HOE_EFFICIENCY_LABEL.getLabel(), HOE_EFFICIENCY_LABEL.getConstraints());
		
		// Hoe Efficiency Spinner
		Spinner HOE_EFFICIENCY_SPINNER = new Spinner(
				ElementGenerator.copy(EFFICIENCY_SPINNER_MODEL), "The efficiency of the hoe.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		hoeEfficiencySpinner = HOE_EFFICIENCY_SPINNER.getSpinner();
		toolPanel.add(hoeEfficiencySpinner, HOE_EFFICIENCY_SPINNER.getConstraints());
		
		// Hoe Attack Damage Label
		Label HOE_ATTACK_DAMAGE_LABEL = new Label(
				"Attack Damage", "", null,
				GridBagConstraints.WEST, 0, 6, 5, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(HOE_ATTACK_DAMAGE_LABEL.getLabel(), HOE_ATTACK_DAMAGE_LABEL.getConstraints());
		
		// Hoe Attack Damage Spinner
		Spinner HOE_ATTACK_DAMAGE_SPINNER = new Spinner(
				ElementGenerator.copy(ATTACK_DAMAGE_SPINNER_MODEL), "The amount of damage dealt when attacking an entity.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		hoeAttackDamageSpinner = HOE_ATTACK_DAMAGE_SPINNER.getSpinner();
		toolPanel.add(hoeAttackDamageSpinner, HOE_ATTACK_DAMAGE_SPINNER.getConstraints());
		
		// Hoe Uses Label
		Label HOE_USES_LABEL = new Label(
				"Uses", "", null,
				GridBagConstraints.WEST, 0, 6, 6, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(HOE_USES_LABEL.getLabel(), HOE_USES_LABEL.getConstraints());
		
		// Hoe Uses Spinner 
		Spinner HOE_USES_SPINNER = new Spinner(
				ElementGenerator.copy(USES_SPINNER_MODEL), "The amount of uses until the hoe breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		hoeUsesSpinner = HOE_USES_SPINNER.getSpinner();
		toolPanel.add(hoeUsesSpinner, HOE_USES_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the hoe components
	 */
	public static void setupShovel() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the shovel components");
		
		// Shovel Title Label
		Label SHOVEL_LABEL = new Label(
				"Shovel", "", null,
				GridBagConstraints.WEST, 0, 9, 2, 1, 1,
				ElementGenerator.getSubTitleFont()
		);
		toolPanel.add(SHOVEL_LABEL.getLabel(), SHOVEL_LABEL.getConstraints());
		
		// Shovel Harvest Level Label
		Label SHOVEL_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				GridBagConstraints.WEST, 0, 9, 3, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SHOVEL_HARVEST_LEVEL_LABEL.getLabel(), SHOVEL_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Shovel Harvest Level Spinner
		Spinner SHOVEL_HARVEST_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(HARVEST_LEVEL_SPINNER_MODEL), "<html>\r\nThe harvest level of the shovel\r\n<p>\r\n0 is like a wooden shovel\r\n<p>\r\n1 is like a stone shovel\r\n<p>\r\n2 is like an iron or gold shovel\r\n<p>\r\n3 is like a diamond shovel\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		shovelHarvestLevelSpinner = SHOVEL_HARVEST_LEVEL_SPINNER.getSpinner();
		toolPanel.add(shovelHarvestLevelSpinner, SHOVEL_HARVEST_LEVEL_SPINNER.getConstraints());
		
		// Shovel Efficiency Label
		Label SHOVEL_EFFICIENCY_LABEL = new Label(
				"Efficiency", "", null,
				GridBagConstraints.WEST, 0, 9, 4, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SHOVEL_EFFICIENCY_LABEL.getLabel(), SHOVEL_EFFICIENCY_LABEL.getConstraints());
		
		// Shovel Efficiency Spinner
		Spinner SHOVEL_EFFICIENCY_SPINNER = new Spinner(
				ElementGenerator.copy(EFFICIENCY_SPINNER_MODEL), "The efficiency of the shovel.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		shovelEfficiencySpinner = SHOVEL_EFFICIENCY_SPINNER.getSpinner();
		toolPanel.add(shovelEfficiencySpinner, SHOVEL_EFFICIENCY_SPINNER.getConstraints());
		
		// Shovel Attack Damage Label
		Label SHOVEL_ATTACK_DAMAGE_LABEL = new Label(
				"Attack Damage", "", null,
				GridBagConstraints.WEST, 0, 9, 5, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SHOVEL_ATTACK_DAMAGE_LABEL.getLabel(), SHOVEL_ATTACK_DAMAGE_LABEL.getConstraints());
		
		// Shovel Attack Damage Spinner
		Spinner SHOVEL_ATTACK_DAMAGE_SPINNER = new Spinner(
				ElementGenerator.copy(ATTACK_DAMAGE_SPINNER_MODEL), "The amount of damage dealt when attacking an entity.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		shovelAttackDamageSpinner = SHOVEL_ATTACK_DAMAGE_SPINNER.getSpinner();
		toolPanel.add(shovelAttackDamageSpinner, SHOVEL_ATTACK_DAMAGE_SPINNER.getConstraints());
		
		// Shovel Uses Label
		Label SHOVEL_USES_LABEL = new Label(
				"Uses", "", null,
				GridBagConstraints.WEST, 0, 9, 6, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SHOVEL_USES_LABEL.getLabel(), SHOVEL_USES_LABEL.getConstraints());
		
		// Shovel Uses Spinner
		Spinner SHOVEL_USES_SPINNER = new Spinner(
				ElementGenerator.copy(USES_SPINNER_MODEL), "The amount of uses until the shovel breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		shovelUsesSpinner = SHOVEL_USES_SPINNER.getSpinner();
		toolPanel.add(shovelUsesSpinner, SHOVEL_USES_SPINNER.getConstraints());
	}
	
	/**
	 * Sets up the sword components
	 */
	public static void setupSword() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the sword components");
		
		// Sword Title Label
		Label SWORD_LABEL = new Label(
				"Sword", "", null,
				0, 0, 12, 2, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		toolPanel.add(SWORD_LABEL.getLabel(), SWORD_LABEL.getConstraints());
		
		// Sword Harvest Level Label
		Label SWORD_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				GridBagConstraints.WEST, 0, 12, 3, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SWORD_HARVEST_LEVEL_LABEL.getLabel(), SWORD_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Sword Harvest Level Spinner
		Spinner SWORD_HARVEST_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(HARVEST_LEVEL_SPINNER_MODEL), "<html>\r\nThe harvest level of the sword\r\n<p>\r\n0 is like a wooden sword\r\n<p>\r\n1 is like a stone sword\r\n<p>\r\n2 is like an iron or gold sword\r\n<p>\r\n3 is like a diamond sword\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 13, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		swordHarvestLevelSpinner = SWORD_HARVEST_LEVEL_SPINNER.getSpinner();
		toolPanel.add(swordHarvestLevelSpinner, SWORD_HARVEST_LEVEL_SPINNER.getConstraints());
		
		// Sword Efficiency Label
		Label SWORD_EFFICIENCY_LABEL = new Label(
				"Efficiency", "", null,
				GridBagConstraints.WEST, 0, 12, 4, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SWORD_EFFICIENCY_LABEL.getLabel(), SWORD_EFFICIENCY_LABEL.getConstraints());
		
		// Sword Efficiency Spinner
		Spinner SWORD_EFFICIENCY_SPINNER = new Spinner(
				ElementGenerator.copy(EFFICIENCY_SPINNER_MODEL), "The efficiency of the sword.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 13, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		swordEfficiencySpinner = SWORD_EFFICIENCY_SPINNER.getSpinner();
		toolPanel.add(swordEfficiencySpinner, SWORD_EFFICIENCY_SPINNER.getConstraints());
		
		// Sword Attack Damage Label
		Label SWORD_ATTACK_DAMAGE_LABEL = new Label(
				"Attack Damage", "", null,
				GridBagConstraints.WEST, 0, 12, 5, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SWORD_ATTACK_DAMAGE_LABEL.getLabel(), SWORD_ATTACK_DAMAGE_LABEL.getConstraints());
		
		// Sword Attack Damage Spinner
		Spinner SWORD_ATTACK_DAMAGE_SPINNER = new Spinner(
				ElementGenerator.copy(ATTACK_DAMAGE_SPINNER_MODEL), "The amount of damage dealt when attacking an entity.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 13, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		swordAttackDamageSpinner = SWORD_ATTACK_DAMAGE_SPINNER.getSpinner();
		toolPanel.add(swordAttackDamageSpinner, SWORD_ATTACK_DAMAGE_SPINNER.getConstraints());
		
		// Sword Uses Label
		Label SWORD_USES_LABEL = new Label(
				"Uses", "", null,
				GridBagConstraints.WEST, 0, 12, 6, 1, 1,
				ElementGenerator.getFont()
		);
		toolPanel.add(SWORD_USES_LABEL.getLabel(), SWORD_USES_LABEL.getConstraints());
		
		// Sword Uses Spinner
		Spinner SWORD_USES_SPINNER = new Spinner(
				ElementGenerator.copy(USES_SPINNER_MODEL), "The amount of uses until the sword breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 13, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		swordUsesSpinner = SWORD_USES_SPINNER.getSpinner();
		toolPanel.add(swordUsesSpinner, SWORD_USES_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the JComponents in the armor panel
	 */
	public static void setupArmor() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the armor panel");
		
		// Armor Panel
		armorPanel = new JPanel();
		
		// Setup constraints for main panel
		GridBagConstraints gbc_armorPanel = new GridBagConstraints();
		gbc_armorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_armorPanel.gridx = 1;
		gbc_armorPanel.gridy = 2;
		
		// Add armor panel to main panel
		panel.add(armorPanel, gbc_armorPanel);
		
		// Setup layout
		GridBagLayout gbl_armorPanel = new GridBagLayout();
		gbl_armorPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_armorPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_armorPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_armorPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set layout
		armorPanel.setLayout(gbl_armorPanel);
		
		// Armor Title Label
		Label ARMOR_LABEL = new Label(
				"Armor", "", null,
				GridBagConstraints.EAST, 0, 0, 0, 5, 1,
				ElementGenerator.getTitleFont()
		);
		armorPanel.add(ARMOR_LABEL.getLabel(), ARMOR_LABEL.getConstraints());
		
		// Auto Fill Armor Button
		Button AUTO_FILL_ARMOR_BUTTON = new Button(
				"Autofill Armor", "Automatically fills the armor based on the harvest level of the ore.",
				GridBagConstraints.WEST, 0, 6, 0, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						autoFillArmor();
					}
				}
		);
		armorPanel.add(AUTO_FILL_ARMOR_BUTTON.getButton(), AUTO_FILL_ARMOR_BUTTON.getConstraints());
		
		// Max Damage Label
		Label MAX_DAMAGE_LABEL = new Label(
				"Max Damage", "", null,
				GridBagConstraints.EAST, 0, 3, 1, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(MAX_DAMAGE_LABEL.getLabel(), MAX_DAMAGE_LABEL.getConstraints());
		
		// Max Damage Spinner
		Spinner MAX_DAMAGE_SPINNER = new Spinner(
				MAX_DAMAGE_SPINNER_MODEL, "The amount of damage the armor can take it until it breaks.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 1, 1, 1,
				ElementGenerator.getFont(), null
		);
		maxDamageSpinner = MAX_DAMAGE_SPINNER.getSpinner();
		armorPanel.add(maxDamageSpinner, MAX_DAMAGE_SPINNER.getConstraints());
		
		// Toughness Label
		Label TOUGHNESS_LABEL = new Label(
				"Toughness", "", null,
				GridBagConstraints.EAST, 0, 6, 1, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(TOUGHNESS_LABEL.getLabel(), TOUGHNESS_LABEL.getConstraints());
		
		// Toughness Spinner
		Spinner TOUGHNESS_SPINNER = new Spinner(
				TOUGHNESS_SPINNER_MODEL, "<html>\r\nThe toughness of the armor\r\n<p>\r\n0 is like leather armor\r\n<p>\r\n20 is like diamond armor\r\n</html>",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 1, 1, 1,
				ElementGenerator.getFont(), null
		);
		toughnessSpinner = TOUGHNESS_SPINNER.getSpinner();
		armorPanel.add(toughnessSpinner, TOUGHNESS_SPINNER.getConstraints());
		
		setupHelmet();
		
		// Helmet and Chestplate Separatpr
		Separator HELMET_CHESTPLATE_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 2, 2, 1, 6
		);
		armorPanel.add(HELMET_CHESTPLATE_SEPARATOR.getSeparator(), HELMET_CHESTPLATE_SEPARATOR.getConstraints());
		
		setupChestplate();

		// Chestplate and Leggings Separatpr
		Separator CHESTPLATE_LEGGINGS_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 5, 2, 1, 6
		);
		armorPanel.add(CHESTPLATE_LEGGINGS_SEPARATOR.getSeparator(), CHESTPLATE_LEGGINGS_SEPARATOR.getConstraints());
		
		setupLeggings();

		// Leggings and Boots Separatpr
		Separator LEGGINGS_BOOTS_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 8, 2, 1, 6
		);
		armorPanel.add(LEGGINGS_BOOTS_SEPARATOR.getSeparator(), LEGGINGS_BOOTS_SEPARATOR.getConstraints());
		
		setupBoots();
		
	}
	
	/**
	 * Sets up the helmet components
	 */
	public static void setupHelmet() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the helmet components");
		
		// Helmet Title Label
		Label HELMET_LABEL = new Label(
				"Helmet", "", null,
				0, 0, 0, 3, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		armorPanel.add(HELMET_LABEL.getLabel(), HELMET_LABEL.getConstraints());
		
		// Helmet Health Label
		Label HELMET_HEALTH_LABEL = new Label(
				"Health", "", null,
				GridBagConstraints.EAST, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(HELMET_HEALTH_LABEL.getLabel(), HELMET_HEALTH_LABEL.getConstraints());
		
		// Helmet Health Spinner
		Spinner HELMET_HEALTH_SPINNER = new Spinner(
				ElementGenerator.copy(HEALTH_SPINNER_MODEL), "The amount of health the helmet adds.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		helmetHealthSpinner = HELMET_HEALTH_SPINNER.getSpinner();
		armorPanel.add(helmetHealthSpinner, HELMET_HEALTH_SPINNER.getConstraints());
		
		// Helmet Effect Label
		Label HELMET_EFFECT_LABEL = new Label(
				"Effect", "", null,
				GridBagConstraints.WEST, 0, 0, 5, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(HELMET_EFFECT_LABEL.getLabel(), HELMET_EFFECT_LABEL.getConstraints());
	
		// Helmet Effect ComboBox
		ComboBox HELMET_EFFECT_COMBOBOX = new ComboBox(
				ARMOR_EFFECTS, "The effect given to the player when they wear the helmet.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		helmetEffectComboBox = HELMET_EFFECT_COMBOBOX.getComboBox();
		armorPanel.add(helmetEffectComboBox, HELMET_EFFECT_COMBOBOX.getConstraints());
		
		// Helmet Level Label
		Label HELMET_LEVEL_LABEL = new Label(
				"Level", "", null,
				GridBagConstraints.WEST, 0, 0, 6, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(HELMET_LEVEL_LABEL.getLabel(), HELMET_LEVEL_LABEL.getConstraints());
		
		// Helmet Level Spinner
		Spinner HELMET_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(LEVEL_SPINNER_MODEL), "The level of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		helmetLevelSpinner = HELMET_LEVEL_SPINNER.getSpinner();
		armorPanel.add(helmetLevelSpinner, HELMET_LEVEL_SPINNER.getConstraints());
		
		// Helmet Duration Label
		Label HELMET_DURATION_LABEL = new Label(
				"Duration", "", null,
				GridBagConstraints.WEST, 0, 0, 7, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(HELMET_DURATION_LABEL.getLabel(), HELMET_DURATION_LABEL.getConstraints());
		
		// Helmet Duration Spinner
		Spinner HELMET_DURATION_SPINNER = new Spinner(
				ElementGenerator.copy(DURATION_SPINNER_MODEL), "The duration of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 7, 1, 1,
				ElementGenerator.getFont(), null
		);
		helmetDurationSpinner = HELMET_DURATION_SPINNER.getSpinner();
		armorPanel.add(helmetDurationSpinner, HELMET_DURATION_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the chestplate components
	 */
	public static void setupChestplate() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the chestplate components");
		
		// Chestplate Title Label
		Label CHESTPLATE_LABEL = new Label(
				"Chestplate", "", null,
				0, 0, 3, 3, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		armorPanel.add(CHESTPLATE_LABEL.getLabel(), CHESTPLATE_LABEL.getConstraints());
		
		// Chestplate Health Label
		Label CHESTPLATE_HEALTH_LABEL = new Label(
				"Health", "", null,
				GridBagConstraints.WEST, 0, 3, 4, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(CHESTPLATE_HEALTH_LABEL.getLabel(), CHESTPLATE_HEALTH_LABEL.getConstraints());
		
		// Chestplate Health Spinner
		Spinner CHESTPLATE_HEALTH_SPINNER = new Spinner(
				ElementGenerator.copy(HEALTH_SPINNER_MODEL), "The amount of armor health the chestplate adds.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		chestplateHealthSpinner = CHESTPLATE_HEALTH_SPINNER.getSpinner();
		armorPanel.add(chestplateHealthSpinner, CHESTPLATE_HEALTH_SPINNER.getConstraints());
		
		// Chestplate Effect Label
		Label CHESTPLATE_EFFECT_LABEL = new Label(
				"Effect", "", null,
				GridBagConstraints.WEST, 0, 3, 5, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(CHESTPLATE_EFFECT_LABEL.getLabel(), CHESTPLATE_EFFECT_LABEL.getConstraints());
		
		// Chestplate Effect ComboBox
		ComboBox CHESTPLATE_EFFECT_COMBOBOX = new ComboBox(
				ARMOR_EFFECTS, "The effect given to the player when they wear the chestplate.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		chestplateEffectComboBox = CHESTPLATE_EFFECT_COMBOBOX.getComboBox();
		armorPanel.add(chestplateEffectComboBox, CHESTPLATE_EFFECT_COMBOBOX.getConstraints());
		
		// Chestplate Level Label
		Label CHESTPLATE_LEVEL_LABEL = new Label(
				"Level", "", null,
				GridBagConstraints.WEST, 0, 3, 6, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(CHESTPLATE_LEVEL_LABEL.getLabel(), CHESTPLATE_LEVEL_LABEL.getConstraints());
		
		// Chestplate Level Spinner
		Spinner CHESTPLATE_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(LEVEL_SPINNER_MODEL), "The level of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		chestplateLevelSpinner = CHESTPLATE_LEVEL_SPINNER.getSpinner();
		armorPanel.add(chestplateLevelSpinner, CHESTPLATE_LEVEL_SPINNER.getConstraints());
		
		// Chestplate Duration Label
		Label CHESTPLATE_DURATION_LABEL = new Label(
				"Duration", "", null,
				GridBagConstraints.WEST, 0, 3, 7, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(CHESTPLATE_DURATION_LABEL.getLabel(), CHESTPLATE_DURATION_LABEL.getConstraints());
		
		// Chestplate Duration Spinner
		Spinner CHESTPLATE_DURATION_SPINNER = new Spinner(
				ElementGenerator.copy(DURATION_SPINNER_MODEL), "The duration of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 4, 7, 1, 1,
				ElementGenerator.getFont(), null
		);
		chestplateDurationSpinner = CHESTPLATE_DURATION_SPINNER.getSpinner();
		armorPanel.add(chestplateDurationSpinner, CHESTPLATE_DURATION_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up all the leggings components
	 */
	public static void setupLeggings() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the leggings components");
		
		// Leggings Title Label
		Label LEGGINGS_LABEL = new Label(
				"Leggings", "", null,
				0, 0, 6, 3, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		armorPanel.add(LEGGINGS_LABEL.getLabel(), LEGGINGS_LABEL.getConstraints());
		
		// Leggings Health Label
		Label LEGGINGS_HEALTH_LABEL = new Label(
				"Health", "", null,
				GridBagConstraints.WEST, 0, 6, 4, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(LEGGINGS_HEALTH_LABEL.getLabel(), LEGGINGS_HEALTH_LABEL.getConstraints());
		
		// Leggings Health Spinner
		Spinner LEGGINGS_HEALTH_SPINNER = new Spinner(
				ElementGenerator.copy(HEALTH_SPINNER_MODEL), "The amount of armor health the leggings adds.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		leggingsHealthSpinner = LEGGINGS_HEALTH_SPINNER.getSpinner();
		armorPanel.add(leggingsHealthSpinner, LEGGINGS_HEALTH_SPINNER.getConstraints());
		
		// Leggings Effect Label
		Label LEGGINGS_EFFECT_LABEL = new Label(
				"Effect", "", null,
				GridBagConstraints.WEST, 0, 6, 5, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(LEGGINGS_EFFECT_LABEL.getLabel(), LEGGINGS_EFFECT_LABEL.getConstraints());
		
		// Leggings Effect Combobox
		ComboBox LEGGINGS_EFFECT_COMBOBOX = new ComboBox(
				ARMOR_EFFECTS, "The effect given to the player when they wear the leggings.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		leggingsEffectComboBox = LEGGINGS_EFFECT_COMBOBOX.getComboBox();
		armorPanel.add(leggingsEffectComboBox, LEGGINGS_EFFECT_COMBOBOX.getConstraints());
		
		// Leggings Level Label
		Label LEGGINGS_LEVEL_LABEL = new Label(
				"Level", "", null,
				GridBagConstraints.WEST, 0, 6, 6, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(LEGGINGS_LEVEL_LABEL.getLabel(), LEGGINGS_LEVEL_LABEL.getConstraints());
		
		// Leggings Level Spinner
		Spinner LEGGINGS_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(LEVEL_SPINNER_MODEL), "The level of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		leggingsLevelSpinner = LEGGINGS_LEVEL_SPINNER.getSpinner();
		armorPanel.add(leggingsLevelSpinner, LEGGINGS_LEVEL_SPINNER.getConstraints());
		
		// Leggings Duration Label
		Label LEGGINGS_DURATION_LABEL = new Label(
				"Duration", "", null,
				GridBagConstraints.WEST, 0, 6, 7, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(LEGGINGS_DURATION_LABEL.getLabel(), LEGGINGS_DURATION_LABEL.getConstraints());
		
		// Leggings Duration Spinner
		Spinner LEGGINGS_DURATION_SPINNER = new Spinner(
				ElementGenerator.copy(DURATION_SPINNER_MODEL), "The duration of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 7, 7, 1, 1,
				ElementGenerator.getFont(), null
		);
		leggingsDurationSpinner = LEGGINGS_DURATION_SPINNER.getSpinner();
		armorPanel.add(leggingsDurationSpinner, LEGGINGS_DURATION_SPINNER.getConstraints());
		
	}
	
	/**
	 * Sets up the boots components
	 */
	public static void setupBoots() {
		
		ElementGenerator.logger.log("ToolPanel", "Setting up the boots components");
		
		// Boots Title Label
		Label BOOTS_LABEL = new Label(
				"Boots", "", null,
				0, 0, 9, 3, 2, 1,
				ElementGenerator.getSubTitleFont()
		);
		armorPanel.add(BOOTS_LABEL.getLabel(), BOOTS_LABEL.getConstraints());
		
		// Boots Health Label
		Label BOOTS_HEALTH_LABEL = new Label(
				"Health", "", null,
				GridBagConstraints.WEST, 0, 9, 4, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(BOOTS_HEALTH_LABEL.getLabel(), BOOTS_HEALTH_LABEL.getConstraints());
		
		// Boots Health Spinner
		Spinner BOOTS_HEALTH_SPINNER = new Spinner(
				ElementGenerator.copy(HEALTH_SPINNER_MODEL), "The amount of armor health the boots adds.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		bootsHealthSpinner = BOOTS_HEALTH_SPINNER.getSpinner();
		armorPanel.add(bootsHealthSpinner, BOOTS_HEALTH_SPINNER.getConstraints());
		
		// Boots Effect Label
		Label BOOTS_EFFECT_LABEL = new Label(
				"Effect", "", null,
				GridBagConstraints.WEST, 0, 9, 5, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(BOOTS_EFFECT_LABEL.getLabel(), BOOTS_EFFECT_LABEL.getConstraints());
		
		// Boots Effect ComboBox
		ComboBox BOOTS_EFFECT_COMBOBOX = new ComboBox(
				ARMOR_EFFECTS, "The effect given to the player when they wear the boots.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 5, 1, 1,
				ElementGenerator.getFont(), null
		);
		bootsEffectComboBox = BOOTS_EFFECT_COMBOBOX.getComboBox();
		armorPanel.add(bootsEffectComboBox, BOOTS_EFFECT_COMBOBOX.getConstraints());
		
		// Boots Level Label
		Label BOOTS_LEVEL_LABEL = new Label(
				"Level", "", null,
				GridBagConstraints.WEST, 0, 9, 6, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(BOOTS_LEVEL_LABEL.getLabel(), BOOTS_LEVEL_LABEL.getConstraints());
		
		// Boots Level Spinner
		Spinner BOOTS_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(LEVEL_SPINNER_MODEL), "The level of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 6, 1, 1,
				ElementGenerator.getFont(), null
		);
		bootsLevelSpinner = BOOTS_LEVEL_SPINNER.getSpinner();
		armorPanel.add(bootsLevelSpinner, BOOTS_LEVEL_SPINNER.getConstraints());
		
		// Boots Duration Label
		Label BOOTS_DURATION_LABEL = new Label(
				"Duration", "", null,
				GridBagConstraints.WEST, 0, 9, 7, 1, 1,
				ElementGenerator.getFont()
		);
		armorPanel.add(BOOTS_DURATION_LABEL.getLabel(), BOOTS_DURATION_LABEL.getConstraints());
		
		// Boots Duration Spinner
		Spinner BOOTS_DURATION_SPINNER = new Spinner(
				ElementGenerator.copy(DURATION_SPINNER_MODEL), "The duration of the effect.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 10, 7, 1, 1,
				ElementGenerator.getFont(), null
		);
		bootsDurationSpinner = BOOTS_DURATION_SPINNER.getSpinner();
		armorPanel.add(bootsDurationSpinner, BOOTS_DURATION_SPINNER.getConstraints());
		
	}
	
	/**
	 * Updates all the tools and armors in the panel
	 * @param element the Element object to use
	 */
	public static void update(Element element) {
		
		ElementGenerator.logger.log("ToolPanel", "Updating the tool panel");
		
		updateTools(element);
		updateArmor(element);
		
	}
	
	/**
	 * Updates all the tools in the tool panel
	 * @param element the Element object to use
	 */
	public static void updateTools(Element element) {
		
		ElementGenerator.logger.log("ToolPanel", "Updating the tool components");
		
		// Pickaxe Components
		Tool pickaxe = element.getToolData().getPickaxe();
		pickaxeHarvestLevelSpinner.setValue(pickaxe.getHarvestLevel());
		pickaxeEfficiencySpinner.setValue(pickaxe.getEfficiency());
		pickaxeAttackDamageSpinner.setValue(pickaxe.getAttackDamage());
		pickaxeUsesSpinner.setValue(pickaxe.getUses());
		
		// Axe Components
		Tool axe = element.getToolData().getAxe();
		axeHarvestLevelSpinner.setValue(axe.getHarvestLevel());
		axeEfficiencySpinner.setValue(axe.getEfficiency());
		axeAttackDamageSpinner.setValue(axe.getAttackDamage());
		axeUsesSpinner.setValue(axe.getUses());
		
		// Hoe Components
		Tool hoe = element.getToolData().getHoe();
		hoeHarvestLevelSpinner.setValue(hoe.getHarvestLevel());
		hoeEfficiencySpinner.setValue(hoe.getEfficiency());
		hoeAttackDamageSpinner.setValue(hoe.getAttackDamage());
		hoeUsesSpinner.setValue(hoe.getUses());
		
		// Shovel Components
		Tool shovel = element.getToolData().getShovel();
		shovelHarvestLevelSpinner.setValue(shovel.getHarvestLevel());
		shovelEfficiencySpinner.setValue(shovel.getEfficiency());
		shovelAttackDamageSpinner.setValue(shovel.getAttackDamage());
		shovelUsesSpinner.setValue(shovel.getUses());
		
		// Sword Components
		Tool sword = element.getToolData().getSword();
		swordHarvestLevelSpinner.setValue(sword.getHarvestLevel());
		swordEfficiencySpinner.setValue(sword.getEfficiency());
		swordAttackDamageSpinner.setValue(sword.getAttackDamage());
		swordUsesSpinner.setValue(sword.getUses());
		
	}
	
	/**
	 * Updates the armor in the armor panel
	 * @param element the Element object to use
	 */
	public static void updateArmor(Element element) {
		
		ElementGenerator.logger.log("ToolPanel", "Updating the armor components");

		// Basic Armor information
		ArmorData armorData = element.getArmorData();
		maxDamageSpinner.setValue(armorData.getMaxDamage());
		toughnessSpinner.setValue(armorData.getToughness() * 10.0);
		
		// Helmet Information
		Armor helmet = armorData.getHelmet();
		helmetHealthSpinner.setValue(helmet.getHealth());
		helmetEffectComboBox.setSelectedItem(helmet.getEffect());
		helmetLevelSpinner.setValue(helmet.getLevel());
		helmetDurationSpinner.setValue(helmet.getDuration());
		
		// Chestplate Information
		Armor chestplate = armorData.getChestplate();
		chestplateHealthSpinner.setValue(chestplate.getHealth());
		chestplateEffectComboBox.setSelectedItem(chestplate.getEffect());
		chestplateLevelSpinner.setValue(chestplate.getLevel());
		chestplateDurationSpinner.setValue(chestplate.getDuration());
		
		// Leggings Information
		Armor leggings = armorData.getLeggings();
		leggingsHealthSpinner.setValue(leggings.getHealth());
		leggingsEffectComboBox.setSelectedItem(leggings.getEffect());
		leggingsLevelSpinner.setValue(leggings.getLevel());
		leggingsDurationSpinner.setValue(leggings.getDuration());
		
		// Boots Information
		Armor boots = armorData.getBoots();
		bootsHealthSpinner.setValue(boots.getHealth());
		bootsEffectComboBox.setSelectedItem(boots.getEffect());
		bootsLevelSpinner.setValue(boots.getLevel());
		bootsDurationSpinner.setValue(boots.getDuration());
		
	}
	
	/**
	 * Resets all the tools and armors in the panel
	 */
	public static void reset() {
		
		ElementGenerator.logger.log("ToolPanel", "Resetting the tool panel");
		
		resetTools();
		resetArmor();
		
	}
	
	/**
	 * Resets all the tools in the tool panel
	 */
	public static void resetTools() {
		
		ElementGenerator.logger.log("ToolPanel", "Resetting the tool components");
		
		// Reset Tool Components
		pickaxeHarvestLevelSpinner.setValue(0);
		pickaxeEfficiencySpinner.setValue(0);
		pickaxeAttackDamageSpinner.setValue(0);
		pickaxeUsesSpinner.setValue(0);
		
		axeHarvestLevelSpinner.setValue(0);
		axeEfficiencySpinner.setValue(0);
		axeAttackDamageSpinner.setValue(0);
		axeUsesSpinner.setValue(0);
		
		hoeHarvestLevelSpinner.setValue(0);
		hoeEfficiencySpinner.setValue(0);
		hoeAttackDamageSpinner.setValue(0);
		hoeUsesSpinner.setValue(0);
		
		shovelHarvestLevelSpinner.setValue(0);
		shovelEfficiencySpinner.setValue(0);
		shovelAttackDamageSpinner.setValue(0);
		shovelUsesSpinner.setValue(0);
		
		swordHarvestLevelSpinner.setValue(0);
		swordEfficiencySpinner.setValue(0);
		swordAttackDamageSpinner.setValue(0);
		swordUsesSpinner.setValue(0);
		
	}
	
	/**
	 * Reset the armor in the armor panel
	 */
	public static void resetArmor() {
		
		ElementGenerator.logger.log("ToolPanel", "Resetting the armor components");
		
		// Reset Basic Armor Components
		maxDamageSpinner.setValue(0);
		toughnessSpinner.setValue(0.0);
		
		// Reset Specific Components
		helmetHealthSpinner.setValue(0);
		helmetEffectComboBox.setSelectedItem("NONE");
		helmetLevelSpinner.setValue(0);
		helmetDurationSpinner.setValue(0);
		
		chestplateHealthSpinner.setValue(0);
		chestplateEffectComboBox.setSelectedItem("NONE");
		chestplateLevelSpinner.setValue(0);
		chestplateDurationSpinner.setValue(0);
		
		leggingsHealthSpinner.setValue(0);
		leggingsEffectComboBox.setSelectedItem("NONE");
		leggingsLevelSpinner.setValue(0);
		leggingsDurationSpinner.setValue(0);
		
		bootsHealthSpinner.setValue(0);
		bootsEffectComboBox.setSelectedItem("NONE");
		bootsLevelSpinner.setValue(0);
		bootsDurationSpinner.setValue(0);
		
	}
	
	/**
	 * Auto fills the tools
	 */
	private static void autoFillTools() {
		
		// Get Element Harvest Level
		int harvestLevel = GenerationPanel.oreHarvestLevelSlider.getValue();
		
		// Harvest Level, Efficiency, and Uses are all the same
		int toolHarvestLevel = harvestLevel + 1;				// Tool Harvest Level is x + 1, x = harvestLevel
		int toolEfficiency = (harvestLevel + 4) * 2;			// Tool Efficiency is 2(x + 4), x = harvestLevel
		int toolUses = (int)(Math.pow(2, harvestLevel + 8));	// Tool Uses is 2^(x + 8), x = harvestLevel
		
		// Attack Damages are different for each one
		int pickaxeAttackDamage = (harvestLevel > 2)? (harvestLevel + 3): 5; 	// Keep pickaxe damage at 5 until harvest level is greater than 2
		int axeAttackDamage = harvestLevel + 8;									// Axe damage is x + 8, x = harvestLevel
		int hoeAttackDamage = (harvestLevel > 2)? (harvestLevel + 5): 7;		// Keep hoe damage at 7 until harvest level is greater than 2
		int shovelAttackDamage = (harvestLevel > 2)? (harvestLevel + 4): 6;		// Keep shovel damage at 6 until harvest level is greater than 2
		int swordAttackDamage = (harvestLevel * 2) + 10;						// Sword damage is 2x + 10, x = harvestLevel
		
		// Pickaxe spinners
		pickaxeHarvestLevelSpinner.setValue(toolHarvestLevel);
		pickaxeEfficiencySpinner.setValue(toolEfficiency);
		pickaxeAttackDamageSpinner.setValue(pickaxeAttackDamage);
		pickaxeUsesSpinner.setValue(toolUses);
		
		// Axe spinners
		axeHarvestLevelSpinner.setValue(toolHarvestLevel);
		axeEfficiencySpinner.setValue(toolEfficiency);
		axeAttackDamageSpinner.setValue(axeAttackDamage);
		axeUsesSpinner.setValue(toolUses);
		
		// Hoe Spinners
		hoeHarvestLevelSpinner.setValue(toolHarvestLevel);
		hoeEfficiencySpinner.setValue(toolEfficiency);
		hoeAttackDamageSpinner.setValue(hoeAttackDamage);
		hoeUsesSpinner.setValue(toolUses);
		
		// Shovel spinners
		shovelHarvestLevelSpinner.setValue(toolHarvestLevel);
		shovelEfficiencySpinner.setValue(toolEfficiency);
		shovelAttackDamageSpinner.setValue(shovelAttackDamage);
		shovelUsesSpinner.setValue(toolUses);
		
		// Sword spinners
		swordHarvestLevelSpinner.setValue(toolHarvestLevel);
		swordEfficiencySpinner.setValue(toolEfficiency);
		swordAttackDamageSpinner.setValue(swordAttackDamage);
		swordUsesSpinner.setValue(toolUses);
		
	}
	
	/**
	 * Auto fills the armor
	 */
	private static void autoFillArmor() {
		
		// Get Element Harvest Level
		int harvestLevel = GenerationPanel.oreHarvestLevelSlider.getValue();
		
		// Armor info
		int maxDamage = (int)(Math.pow(2, harvestLevel + 8));	// Max Damage is 2^(x + 8), x = harvestLevel
		double toughness = harvestLevel * 10.0;					// Toughness ix 10x, x = harvestLevel
		
		// Armor healths - harvestLevels  0  1  2  3   4   5   6   7 
		int helmetHealth = 	   new int[] {1, 1, 2, 6,  7, 11, 12, 16}[harvestLevel];
		int chestplateHealth = new int[] {4, 4, 7, 9, 12, 14, 17, 19}[harvestLevel];
		int leggingsHealth =   new int[] {3, 3, 6, 8, 11, 13, 16, 18}[harvestLevel];
		int bootsHealth =      new int[] {2, 2, 6, 7, 10, 12, 15, 17}[harvestLevel];
		
		// Armor info spinners
		maxDamageSpinner.setValue(maxDamage);
		toughnessSpinner.setValue(toughness);
		
		// Armor health spinners
		helmetHealthSpinner.setValue(helmetHealth);
		chestplateHealthSpinner.setValue(chestplateHealth);
		leggingsHealthSpinner.setValue(leggingsHealth);
		bootsHealthSpinner.setValue(bootsHealth);
		
	}

}
