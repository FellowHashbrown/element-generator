package elementgenerator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import elementgenerator.block.BlockData;
import elementgenerator.element.Element;
import elementgenerator.generation.Generation;
import elementgenerator.gui.customcomponents.*;

/** 
 * The GenerationPanel for the ElementGenerator GUI
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class GenerationPanel {
	
	// Grid Values
	private static final int NETHER_GRID_X = 1;
	private static final int NETHER_GRID_Y = 0;
	private static final int SURFACE_GRID_X = 3;
	private static final int SURFACE_GRID_Y = 0;
	private static final int END_GRID_X = 5;
	private static final int END_GRID_Y = 0;
	private static final int ORE_BLOCK_GRID_X = 1;
	private static final int ORE_BLOCK_GRID_Y = 2;
	private static final int ORE_BLOCK_GRID_W = 5;
	private static final int ORE_BLOCK_GRID_H = 6;

	private static final int MAXIMUM_HARVEST_LEVEL = 6;
	
	private static final SpinnerNumberModel LOWEST_GENERATION_SPINNER_MODEL = new SpinnerNumberModel(
			10,  // Initial Value
			0,   // Minimum
			255, // Maximum
			1    // Step Value
	);
	private static final SpinnerNumberModel HIGHEST_GENERATION_SPINNER_MODEL = new SpinnerNumberModel(
			100, // Initial Value
			0,	 // Minimum
			255, // Maximum
			1    // Step Value
	);
	private static final SpinnerNumberModel HARDNESS_SPINNER_MODEL = new SpinnerNumberModel(
			15.0, 	// Initial Value
			0.0,  	// Minimum
			1000.0, // Maximum
			1.0   	// Step Value
	);
	private static final SpinnerNumberModel RESISTANCE_SPINNER_MODEL = new SpinnerNumberModel(
			30.0,	// Initial Value
			0.0,	// Minimum
			5000.0, // Maximum
			1.0		// Step Value
	);
	private static final SpinnerNumberModel LIGHT_LEVEL_SPINNER_MODEL = new SpinnerNumberModel(
			0.0,	// Initial Value
			0.0,	// Minimum
			100.0, 	// Maximum
			1.0 	// Step Value
	);
	private static final SpinnerNumberModel LIGHT_OPACITY_SPINNER_MODEL = new SpinnerNumberModel(
			16,	// Initial Value
			0,	// Minimum
			16,	// Maximum
			1 	// Step Value
	);

	// Main Panel
	public static JPanel panel;
	
	public static JButton backButton;
	public static JButton cancelButton;
	public static JButton nextButton;
	
	// Nether Generation Panel
	public static JPanel netherPanel;
	
	public static JCheckBox generateInNetherCheckBox;
	
	public static JLabel lowestNetherGenerationLabel;
	public static JLabel highestNetherGenerationLabel;
	public static JSpinner lowestNetherGenerationSpinner;
	public static JSpinner highestNetherGenerationSpinner;
	
	public static JLabel netherRarityLabel;
	public static JLabel netherChunkRarityLabel;
	public static JSlider netherRaritySlider;
	public static JSlider netherChunkRaritySlider;
	
	// Surface Generation Panel
	public static JPanel surfacePanel;
	
	public static JCheckBox generateOnSurfaceCheckBox;
	
	public static JLabel lowestSurfaceGenerationLabel;
	public static JLabel highestSurfaceGenerationLabel;
	public static JSpinner lowestSurfaceGenerationSpinner;
	public static JSpinner highestSurfaceGenerationSpinner;
	
	public static JLabel surfaceRarityLabel;
	public static JLabel surfaceChunkRarityLabel;
	public static JSlider surfaceRaritySlider;
	public static JSlider surfaceChunkRaritySlider;
	
	// End Generation Panel
	public static JPanel endPanel;
	
	public static JCheckBox generateInEndCheckBox;
	
	public static JLabel lowestEndGenerationLabel;
	public static JLabel highestEndGenerationLabel;
	public static JSpinner lowestEndGenerationSpinner;
	public static JSpinner highestEndGenerationSpinner;
	
	public static JLabel endRarityLabel;
	public static JLabel endChunkRarityLabel;
	public static JSlider endRaritySlider;
	public static JSlider endChunkRaritySlider;
	
	// Ore & Block Panel
	public static JPanel oreBlockPanel;
	
	public static JSpinner oreHardnessSpinner;
	public static JSpinner oreResistanceSpinner;
	public static JSpinner oreLightLevelSpinner;
	public static JSpinner oreLightOpacitySpinner;
	public static JSlider  oreHarvestLevelSlider;
	
	public static JSpinner blockHardnessSpinner;
	public static JSpinner blockResistanceSpinner;
	public static JSpinner blockLightLevelSpinner;
	public static JSpinner blockLightOpacitySpinner;
	public static JSlider  blockHarvestLevelSlider;
	
	/**
	 * Sets up all the JComponents
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("GenerationPanel", "Setting up the panel");
		
		// Setup panel
		panel = new JPanel();
		
		// Setup Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 81, 0, 0, 0, 0, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		panel.setLayout(layout);
		
		// Setup sub-panels
		setupNether();
		
		// - Add nether and surface separator
		Separator NETHER_SURFACE_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 2, 0, 1, 1
		);
		panel.add(NETHER_SURFACE_SEPARATOR.getSeparator(), NETHER_SURFACE_SEPARATOR.getConstraints());
		
		setupSurface();
		
		// - Add surface and end separator
		Separator SURFACE_END_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 4, 0, 1, 1
		);
		panel.add(SURFACE_END_SEPARATOR.getSeparator(), SURFACE_END_SEPARATOR.getConstraints());
		
		setupEnd();
		
		setupOreBlock();
		
		// Back Button
		Button BACK_BUTTON = new Button(
				"Back", "Goes to previous page of the element generator",
				0, 0, 0, 8, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						ElementGenerator.changeMenu("basic");
						ElementGenerator.logger.log("GenerationPanel", "Changing Panel To: BasicPanel");
					}
				}
		);
		backButton = BACK_BUTTON.getButton();
		panel.add(backButton, BACK_BUTTON.getConstraints());
		
		// Cancel Button
		Button CANCEL_BUTTON = new Button(
				"Cancel", "Cancels making a new element",
				0, 0, 1, 8, 5, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						BasicPanel.reset();
						GenerationPanel.reset();
						ToolPanel.reset();
						ElementGenerator.changeMenu("main");
					}
				}
		);
		cancelButton = CANCEL_BUTTON.getButton();
		panel.add(cancelButton, CANCEL_BUTTON.getConstraints());
		
		// Next Button
		Button NEXT_BUTTON = new Button(
				"Next", "Advances to the next page of the element generator",
				0, 0, 6, 8, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						ElementGenerator.changeMenu("tools");
					}
				}
		);
		nextButton = NEXT_BUTTON.getButton();
		panel.add(nextButton, NEXT_BUTTON.getConstraints());
		
	}
	/**
	 * Sets up the Nether Panel and all its components
	 */
	public static void setupNether() {
		
		ElementGenerator.logger.log("GenerationPanel", "Setting up the Nether Generation panel");
		
		// Setup Nether Panel
		netherPanel = new JPanel();
		
		// Set up GridBagConstraints for main panel
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = NETHER_GRID_X;
		constraints.gridy = NETHER_GRID_Y;
		
		// Add Nether Panel to main panel
		panel.add(netherPanel, constraints);
		
		// Setup Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		netherPanel.setLayout(layout);
		
		// *****************************************************
		
		// Nether Title Label
		Label NETHER_GENERATION_LABEL = new Label(
				"Nether", "", null,
				0, 0, 0, 0, 2, 1,
				ElementGenerator.getTitleFont()
		);
		netherPanel.add(NETHER_GENERATION_LABEL.getLabel(), NETHER_GENERATION_LABEL.getConstraints());
		
		// Nether Generate Checkbox
		CheckBox NETHER_GENERATE_CHECKBOX = new CheckBox(
				"Generate In Nether?", "Whether or not the ore generates in the nether",
				0, 0, 0, 1, 2, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						enableNether(generateInNetherCheckBox.isSelected());
					}
				}
		);
		generateInNetherCheckBox = NETHER_GENERATE_CHECKBOX.getCheckBox();
		netherPanel.add(generateInNetherCheckBox, NETHER_GENERATE_CHECKBOX.getConstraints());
		
		// Nether Upper Separator
		// - Setup Separator
		JSeparator netherGenerateSeparator = new JSeparator();
		// - Setup constraints for nether panel
		GridBagConstraints gbc_netherGenerateSeparator = new GridBagConstraints();
		gbc_netherGenerateSeparator.gridwidth = 2;
		gbc_netherGenerateSeparator.fill = GridBagConstraints.BOTH;
		gbc_netherGenerateSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_netherGenerateSeparator.gridx = 0;
		gbc_netherGenerateSeparator.gridy = 2;
		// - Add Separator to nether panel
		netherPanel.add(netherGenerateSeparator, gbc_netherGenerateSeparator);
		
		// Nether Low Generation Label
		Label NETHER_LOW_GENERATION_LABEL = new Label(
				"Lowest point of generation", "", null,
				GridBagConstraints.EAST, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		lowestNetherGenerationLabel = NETHER_LOW_GENERATION_LABEL.getLabel();
		netherPanel.add(lowestNetherGenerationLabel, NETHER_LOW_GENERATION_LABEL.getConstraints());
		
		// Nether Low Generation Spinner
		Spinner NETHER_LOW_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(LOWEST_GENERATION_SPINNER_MODEL), "The lowest point of the ore's nether generation",
				GridBagConstraints.WEST, 0, 1, 3, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest spinner values
						int lowest = (int)lowestNetherGenerationSpinner.getValue();
						int highest = (int)highestNetherGenerationSpinner.getValue();
						// Make sure lowest is lower than highest
						if(lowest >= highest)
							lowest = highest - 1;
						// Set the lowest value
						lowestNetherGenerationSpinner.setValue(lowest);
					}
				}
		);
		lowestNetherGenerationSpinner = NETHER_LOW_GENERATION_SPINNER.getSpinner();
		netherPanel.add(lowestNetherGenerationSpinner, NETHER_LOW_GENERATION_SPINNER.getConstraints());
		
		// Nether High Generation Label
		Label NETHER_HIGH_GENERATION_LABEL = new Label(
				"Highest point of generation", "", null,
				GridBagConstraints.EAST, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		highestNetherGenerationLabel = NETHER_HIGH_GENERATION_LABEL.getLabel();
		netherPanel.add(highestNetherGenerationLabel, NETHER_HIGH_GENERATION_LABEL.getConstraints());
		
		// Nether High Generation Spinner
		Spinner NETHER_HIGH_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(HIGHEST_GENERATION_SPINNER_MODEL), "The highest point of the nether ore's generation",
				GridBagConstraints.WEST, 0, 1, 4, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest value spinner values
						int lowest = (int)lowestNetherGenerationSpinner.getValue();
						int highest = (int)highestNetherGenerationSpinner.getValue();
						// Make sure highest is higher than lowest
						if(highest <= lowest)
							highest = lowest + 1;
						// Set the highest value
						highestNetherGenerationSpinner.setValue(highest);
					}
				}
		);
		highestNetherGenerationSpinner = NETHER_HIGH_GENERATION_SPINNER.getSpinner();
		netherPanel.add(highestNetherGenerationSpinner, NETHER_HIGH_GENERATION_SPINNER.getConstraints());
		
		// Nether Lower Separator
		// - Setup Separator
		JSeparator netherRaritySeparator = new JSeparator();
		// - Setup constraints for nether panel
		GridBagConstraints gbc_netherRaritySeparator = new GridBagConstraints();
		gbc_netherRaritySeparator.gridwidth = 2;
		gbc_netherRaritySeparator.fill = GridBagConstraints.BOTH;
		gbc_netherRaritySeparator.insets = new Insets(0, 0, 5, 0);
		gbc_netherRaritySeparator.gridx = 0;
		gbc_netherRaritySeparator.gridy = 5;
		// - Add Separator to nether panel
		netherPanel.add(netherRaritySeparator, gbc_netherRaritySeparator);
		
		// Nether Rarity Label
		Label NETHER_RARITY_LABEL = new Label(
				"Nether Rarity", "", null,
				0, 0, 0, 6, 2, 1,
				ElementGenerator.getFont()
		);
		netherRarityLabel = NETHER_RARITY_LABEL.getLabel();
		netherPanel.add(netherRarityLabel, NETHER_RARITY_LABEL.getConstraints());
		
		// Nether Rarity Slider
		Slider NETHER_RARITY_SLIDER = new Slider(
				10, 0, 20, "How rare the ore generates across the Nether dimension",
				0, GridBagConstraints.BOTH, 0, 7, 2, 1,
				ElementGenerator.getFont()
		);
		netherRaritySlider = NETHER_RARITY_SLIDER.getSlider();
		netherPanel.add(netherRaritySlider, NETHER_RARITY_SLIDER.getConstraints());
		
		// Nether Chunk Rarity Label
		Label NETHER_CHUNK_RARITY_LABEL = new Label(
				"Nether Chunk Rarity", "", null,
				0, 0, 0, 8, 2, 1,
				ElementGenerator.getFont()
		);
		netherChunkRarityLabel = NETHER_CHUNK_RARITY_LABEL.getLabel();
		netherPanel.add(netherChunkRarityLabel, NETHER_CHUNK_RARITY_LABEL.getConstraints());
		
		// Nether Chunk Rarity Slider
		Slider NETHER_CHUNK_RARITY_SLIDER = new Slider(
				16, 0, 20, "How rare the ore generates in a specific chunk in the Nether dimension",
				0, GridBagConstraints.BOTH, 0, 9, 2, 1,
				ElementGenerator.getFont()
		);
		netherChunkRaritySlider = NETHER_CHUNK_RARITY_SLIDER.getSlider();
		netherPanel.add(netherChunkRaritySlider, NETHER_CHUNK_RARITY_SLIDER.getConstraints());
		
	}
	/**
	 * Sets up the Surface Panel and all its components
	 */
	public static void setupSurface() {
		
		ElementGenerator.logger.log("GenerationPanel", "Setting up the Surface Generation panel");
		
		// Setup Surface Panel
		surfacePanel = new JPanel();
		
		// Set up GridBagConstraints for main panel
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 0, 5, 5);
		constraints.gridx = SURFACE_GRID_X;
		constraints.gridy = SURFACE_GRID_Y;
		
		// Add Surface Panel to main panel
		panel.add(surfacePanel, constraints);
		
		// Setup Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{53, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		surfacePanel.setLayout(layout);
		
		// ************************************************
		
		// Surface Title Label
		Label SURFACE_GENERATION_LABEL = new Label(
				"Surface", "", null,
				0, 0, 0, 0, 2, 1,
				ElementGenerator.getTitleFont()
		);
		surfacePanel.add(SURFACE_GENERATION_LABEL.getLabel(), SURFACE_GENERATION_LABEL.getConstraints());
		
		// Surface Generate Checkbox
		CheckBox SURFACE_GENERATE_CHECKBOX = new CheckBox(
				"Generate On Surface?", "Whether or not the ore generates on the surface",
				0, 0, 0, 1, 2, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						enableSurface(generateOnSurfaceCheckBox.isSelected());
					}
				}
		);
		generateOnSurfaceCheckBox = SURFACE_GENERATE_CHECKBOX.getCheckBox();
		surfacePanel.add(generateOnSurfaceCheckBox, SURFACE_GENERATE_CHECKBOX.getConstraints());
		
		// Surface Upper Separator
		// - Setup Separator
		JSeparator surfaceGenerateSeparator = new JSeparator();
		// - Setup constraints for surface panel
		GridBagConstraints gbc_surfaceGenerateSeparator = new GridBagConstraints();
		gbc_surfaceGenerateSeparator.gridwidth = 2;
		gbc_surfaceGenerateSeparator.fill = GridBagConstraints.BOTH;
		gbc_surfaceGenerateSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_surfaceGenerateSeparator.gridx = 0;
		gbc_surfaceGenerateSeparator.gridy = 2;
		// - Add Separator to surface panel
		surfacePanel.add(surfaceGenerateSeparator, gbc_surfaceGenerateSeparator);
		
		// Surface Low Generation Label
		Label SURFACE_LOW_GENERATION_LABEL = new Label(
				"Lowest point of generation", "", null,
				GridBagConstraints.EAST, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		lowestSurfaceGenerationLabel = SURFACE_LOW_GENERATION_LABEL.getLabel();
		surfacePanel.add(lowestSurfaceGenerationLabel, SURFACE_LOW_GENERATION_LABEL.getConstraints());
		
		// Surface Low Generation Spinner
		Spinner SURFACE_LOW_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(LOWEST_GENERATION_SPINNER_MODEL), "The lowest point of the ore's surface generation",
				GridBagConstraints.WEST, 0, 1, 3, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest spinner values
						int lowest = (int)lowestSurfaceGenerationSpinner.getValue();
						int highest = (int)highestSurfaceGenerationSpinner.getValue();
						// Make sure lowest is lower than highest
						if(lowest >= highest)
							lowest = highest - 1;
						// Set the lowest value
						lowestSurfaceGenerationSpinner.setValue(lowest);
					}
				}
		);
		lowestSurfaceGenerationSpinner = SURFACE_LOW_GENERATION_SPINNER.getSpinner();
		surfacePanel.add(lowestSurfaceGenerationSpinner, SURFACE_LOW_GENERATION_SPINNER.getConstraints());
		
		// Surface High Generation Label
		Label SURFACE_HIGH_GENERATION_LABEL = new Label(
				"Highest point of generation.", "", null,
				GridBagConstraints.EAST, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		highestSurfaceGenerationLabel = SURFACE_HIGH_GENERATION_LABEL.getLabel();
		surfacePanel.add(highestSurfaceGenerationLabel, SURFACE_HIGH_GENERATION_LABEL.getConstraints());
		
		// Surface High Generation Spinner
		Spinner SURFACE_HIGH_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(HIGHEST_GENERATION_SPINNER_MODEL), "The highest point of the surface ore's generation",
				GridBagConstraints.WEST, 0, 1, 4, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest value spinner values
						int lowest = (int)lowestSurfaceGenerationSpinner.getValue();
						int highest = (int)highestSurfaceGenerationSpinner.getValue();
						// Make sure highest is higher than lowest
						if(highest <= lowest)
							highest = lowest + 1;
						// Set the highest value
						highestSurfaceGenerationSpinner.setValue(highest);
					}
				}
		);
		highestSurfaceGenerationSpinner = SURFACE_HIGH_GENERATION_SPINNER.getSpinner();
		surfacePanel.add(highestSurfaceGenerationSpinner, SURFACE_HIGH_GENERATION_SPINNER.getConstraints());
		
		// Surface Lower Separator
		// - Setup Separator
		JSeparator surfaceRaritySeparator = new JSeparator();
		// - Setup constraints for surface panel
		GridBagConstraints gbc_surfaceRaritySeparator = new GridBagConstraints();
		gbc_surfaceRaritySeparator.gridwidth = 2;
		gbc_surfaceRaritySeparator.fill = GridBagConstraints.BOTH;
		gbc_surfaceRaritySeparator.insets = new Insets(0, 0, 5, 0);
		gbc_surfaceRaritySeparator.gridx = 0;
		gbc_surfaceRaritySeparator.gridy = 5;
		// - Add Separator to surface panel
		surfacePanel.add(surfaceRaritySeparator, gbc_surfaceRaritySeparator);
		
		// Surface Rarity Label
		Label SURFACE_RARITY_LABEL = new Label(
				"Surface Rarity", "", null,
				0, 0, 0, 6, 2, 1,
				ElementGenerator.getFont()
		);
		surfaceRarityLabel = SURFACE_RARITY_LABEL.getLabel();
		surfacePanel.add(surfaceRarityLabel, SURFACE_RARITY_LABEL.getConstraints());
		
		// Surface Rarity Slider
		Slider SURFACE_RARITY_SLIDER = new Slider(
				10, 0, 20, "How rare the ore generates across the Surface dimension",
				0, GridBagConstraints.BOTH, 0, 7, 2, 1,
				ElementGenerator.getFont()
		);
		surfaceRaritySlider = SURFACE_RARITY_SLIDER.getSlider();
		surfacePanel.add(surfaceRaritySlider, SURFACE_RARITY_SLIDER.getConstraints());
		
		// Surface Chunk Rarity Label
		Label SURFACE_CHUNK_RARITY_LABEL = new Label(
				"Surface Chunk Rarity", "", null,
				0, 0, 0, 8, 2, 1,
				ElementGenerator.getFont()
		);
		surfaceChunkRarityLabel = SURFACE_CHUNK_RARITY_LABEL.getLabel();
		surfacePanel.add(surfaceChunkRarityLabel, SURFACE_CHUNK_RARITY_LABEL.getConstraints());
		
		// Surface Chunk Rarity Slider
		Slider SURFACE_CHUNK_RARITY_SLIDER = new Slider(
				16, 0, 20, "How rare the ore generates in a specific chunk in the Surface dimension",
				0, GridBagConstraints.BOTH, 0, 9, 2, 1,
				ElementGenerator.getFont()
		);
		surfaceChunkRaritySlider = SURFACE_CHUNK_RARITY_SLIDER.getSlider();
		surfacePanel.add(surfaceChunkRaritySlider, SURFACE_CHUNK_RARITY_SLIDER.getConstraints());
		
	}
	/**
	 * Sets up the End Panel and all its components
	 */
	public static void setupEnd() {
		
		ElementGenerator.logger.log("GenerationPanel", "Setting up the End Generation panel");
		
		// Setup End Panel
		endPanel = new JPanel();
		
		 // Setup GridBagConstraints for main panel
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 0, 5, 5);
		constraints.gridx = END_GRID_X;
		constraints.gridy = END_GRID_Y;
		
		// Add End Panel to main panel
		panel.add(endPanel, constraints);
		
		// Setup Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		endPanel.setLayout(layout);
		
		// *****************************************************
		
		// End Title Label
		Label END_GENERATION_LABEL = new Label(
				"End", "", null,
				0, 0, 0, 0, 2, 1,
				ElementGenerator.getTitleFont()
		);
		endPanel.add(END_GENERATION_LABEL.getLabel(), END_GENERATION_LABEL.getConstraints());
		
		// End Generate Checkbox
		CheckBox END_GENERATE_CHECKBOX = new CheckBox(
				"Generate In End?", "Whether or not the ore generates in the end",
				0, 0, 0, 1, 2, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						enableEnd(generateInEndCheckBox.isSelected());
					}
				}
		);
		generateInEndCheckBox = END_GENERATE_CHECKBOX.getCheckBox();
		endPanel.add(generateInEndCheckBox, END_GENERATE_CHECKBOX.getConstraints());
		
		// End Upper Separator
		// - Setup Separator
		JSeparator endGenerateSeparator = new JSeparator();
		// - Setup constraints for end panel
		GridBagConstraints gbc_endGenerateSeparator = new GridBagConstraints();
		gbc_endGenerateSeparator.gridwidth = 2;
		gbc_endGenerateSeparator.fill = GridBagConstraints.BOTH;
		gbc_endGenerateSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_endGenerateSeparator.gridx = 0;
		gbc_endGenerateSeparator.gridy = 2;
		// - Add Separator to end panel
		endPanel.add(endGenerateSeparator, gbc_endGenerateSeparator);
		
		// End Low Generation Label
		Label END_LOW_GENERATION_LABEL = new Label(
				"Lowest point of generation", "", null,
				GridBagConstraints.EAST, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		lowestEndGenerationLabel = END_LOW_GENERATION_LABEL.getLabel();
		endPanel.add(lowestEndGenerationLabel, END_LOW_GENERATION_LABEL.getConstraints());
		
		// End Low Generation Spinner
		Spinner END_LOW_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(LOWEST_GENERATION_SPINNER_MODEL), "The lowest point of the ore's end generation",
				GridBagConstraints.WEST, 0, 1, 3, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest spinner values
						int lowest = (int)lowestEndGenerationSpinner.getValue();
						int highest = (int)highestEndGenerationSpinner.getValue();
						// Make sure lowest is lower than highest
						if(lowest >= highest)
							lowest = highest - 1;
						// Set the lowest value
						lowestEndGenerationSpinner.setValue(lowest);
					}
				}
		);
		lowestEndGenerationSpinner = END_LOW_GENERATION_SPINNER.getSpinner();
		endPanel.add(lowestEndGenerationSpinner, END_LOW_GENERATION_SPINNER.getConstraints());
		
		// End High Generation Label
		Label END_HIGH_GENERATION_LABEL = new Label(
				"Highest point of generation.", "", null,
				GridBagConstraints.EAST, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		highestEndGenerationLabel = END_HIGH_GENERATION_LABEL.getLabel();
		endPanel.add(highestEndGenerationLabel, END_HIGH_GENERATION_LABEL.getConstraints());
		
		// End High Generation Spinner
		Spinner END_HIGH_GENERATION_SPINNER = new Spinner(
				ElementGenerator.copy(HIGHEST_GENERATION_SPINNER_MODEL), "The highest point of the end ore's generation",
				GridBagConstraints.WEST, 0, 1, 4, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get lowest and highest value spinner values
						int lowest = (int)lowestEndGenerationSpinner.getValue();
						int highest = (int)highestEndGenerationSpinner.getValue();
						// Make sure highest is higher than lowest
						if(highest <= lowest)
							highest = lowest + 1;
						// Set the highest value
						highestEndGenerationSpinner.setValue(highest);
					}
				}
		);
		highestEndGenerationSpinner = END_HIGH_GENERATION_SPINNER.getSpinner();
		endPanel.add(highestEndGenerationSpinner, END_HIGH_GENERATION_SPINNER.getConstraints());
		
		// End Lower Separator
		// - Setup Separator
		JSeparator endRaritySeparator = new JSeparator();
		// - Setup constraints for end panel
		GridBagConstraints gbc_endRaritySeparator = new GridBagConstraints();
		gbc_endRaritySeparator.gridwidth = 2;
		gbc_endRaritySeparator.fill = GridBagConstraints.BOTH;
		gbc_endRaritySeparator.insets = new Insets(0, 0, 5, 0);
		gbc_endRaritySeparator.gridx = 0;
		gbc_endRaritySeparator.gridy = 5;
		// - Add Separator to end panel
		endPanel.add(endRaritySeparator, gbc_endRaritySeparator);
		
		// End Rarity Label
		Label END_RARITY_LABEL = new Label(
				"End Rarity", "", null,
				0, 0, 0, 6, 2, 1,
				ElementGenerator.getFont()
		);
		endRarityLabel = END_RARITY_LABEL.getLabel();
		endPanel.add(endRarityLabel, END_RARITY_LABEL.getConstraints());
		
		// End Rarity Slider
		Slider END_RARITY_SLIDER = new Slider(
				10, 0, 20, "How rare the ore generates across the End dimension",
				0, GridBagConstraints.BOTH, 0, 7, 2, 1,
				ElementGenerator.getFont()
		);
		endRaritySlider = END_RARITY_SLIDER.getSlider();
		endPanel.add(endRaritySlider, END_RARITY_SLIDER.getConstraints());
		
		// End Chunk Rarity Label
		Label END_CHUNK_RARITY_LABEL = new Label(
				"End Chunk Rarity", "", null,
				0, 0, 0, 8, 2, 1,
				ElementGenerator.getFont()
		);
		endChunkRarityLabel = END_CHUNK_RARITY_LABEL.getLabel();
		endPanel.add(endChunkRarityLabel, END_CHUNK_RARITY_LABEL.getConstraints());
		
		// End Chunk Rarity Slider
		Slider END_CHUNK_RARITY_SLIDER = new Slider(
				16, 0, 20, "How rare the ore generates in a specific chunk of the End dimension",
				0, GridBagConstraints.BOTH, 0, 9, 2, 1,
				ElementGenerator.getFont()
		);
		endChunkRaritySlider = END_CHUNK_RARITY_SLIDER.getSlider();
		endPanel.add(endChunkRaritySlider, END_CHUNK_RARITY_SLIDER.getConstraints());
		
	}
	/**
	 * Sets up the Ore & Block Panel and all its components
	 */
	public static void setupOreBlock() {
		
		ElementGenerator.logger.log("GenerationPanel", "Setting up the Ore and Block panel");
		
		// Setup Ore & Block Panel
		oreBlockPanel = new JPanel();
		
		// Setup GridBagConstraints for the main panel
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridheight = ORE_BLOCK_GRID_H;
		constraints.gridwidth = ORE_BLOCK_GRID_W;
		constraints.insets = new Insets(0, 0, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = ORE_BLOCK_GRID_X;
		constraints.gridy = ORE_BLOCK_GRID_Y;
		
		// Add Ore & block Panel to main panel
		panel.add(oreBlockPanel, constraints);
		
		// Setup Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		// Set the layout
		oreBlockPanel.setLayout(layout);
		
		// ****************************************************************
		
		// Ore Title Label
		Label ORE_LABEL = new Label(
				"Ore", "", null,
				0, 0, 0, 0, 2, 1,
				ElementGenerator.getTitleFont()
		);
		oreBlockPanel.add(ORE_LABEL.getLabel(), ORE_LABEL.getConstraints());
		
		// Ore And Block Separator
		// - Setup Separator
		JSeparator oreBlockSeparator = new JSeparator();
		oreBlockSeparator.setOrientation(SwingConstants.VERTICAL);
		// - Setup constraints for ore panel
		GridBagConstraints gbc_oreBlockSeparator = new GridBagConstraints();
		gbc_oreBlockSeparator.fill = GridBagConstraints.BOTH;
		gbc_oreBlockSeparator.gridheight = 6;
		gbc_oreBlockSeparator.insets = new Insets(0, 0, 0, 5);
		gbc_oreBlockSeparator.gridx = 2;
		gbc_oreBlockSeparator.gridy = 0;
		// - Add Separator to ore panel
		oreBlockPanel.add(oreBlockSeparator, gbc_oreBlockSeparator);
		
		// Block Title Label
		Label BLOCK_LABEL = new Label(
				"Block", "", null,
				0, 0, 3, 0, 2, 1,
				ElementGenerator.getTitleFont()
		);
		oreBlockPanel.add(BLOCK_LABEL.getLabel(), BLOCK_LABEL.getConstraints());
		
		// Ore Hardness Label
		Label ORE_HARDNESS_LABEL = new Label(
				"Hardness", "", null,
				0, 0, 0, 1, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(ORE_HARDNESS_LABEL.getLabel(), ORE_HARDNESS_LABEL.getConstraints());
		
		// Ore Hardness Spinner
		Spinner ORE_HARDNESS_SPINNER = new Spinner(
				ElementGenerator.copy(HARDNESS_SPINNER_MODEL), "<html>\r\nThe hardness of the ore\r\n<p>\r\n15 is like Stone\r\n<p>\r\n500 is like Obsidian\r\n</html>",
				0, 0, 1, 1, 1, 1,
				ElementGenerator.getFont(), null
		);
		oreHardnessSpinner = ORE_HARDNESS_SPINNER.getSpinner();
		oreBlockPanel.add(oreHardnessSpinner, ORE_HARDNESS_SPINNER.getConstraints());
		
		// Block Hardness Label
		Label BLOCK_HARDNESS_LABEL = new Label(
				"Hardness", "", null,
				0, 0, 3, 1, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(BLOCK_HARDNESS_LABEL.getLabel(), BLOCK_HARDNESS_LABEL.getConstraints());
		
		// Block Hardness Spinner
		Spinner BLOCK_HARDNESS_SPINNER = new Spinner(
				ElementGenerator.copy(HARDNESS_SPINNER_MODEL), "<html>\r\nThe hardness of the block\r\n<p>\r\n15 is like Stone\r\n<p>\r\n500 is like Obsidian\r\n</html>",
				0, 0, 4, 1, 1, 1,
				ElementGenerator.getFont(), null
		);
		blockHardnessSpinner = BLOCK_HARDNESS_SPINNER.getSpinner();
		oreBlockPanel.add(blockHardnessSpinner, BLOCK_HARDNESS_SPINNER.getConstraints());
		
		// Ore Resistance Label
		Label ORE_RESISTANCE_LABEL = new Label(
				"Resistance", "", null,
				0, 0, 0, 2, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(ORE_RESISTANCE_LABEL.getLabel(), ORE_RESISTANCE_LABEL.getConstraints());
		
		// Ore Resistance Spinner
		Spinner ORE_RESISTANCE_SPINNER = new Spinner(
				ElementGenerator.copy(RESISTANCE_SPINNER_MODEL), "<html>\r\nHow resistant the ore is to explosions\r\n<p>\r\n10 is like Stone\r\n<p>\r\n2000 is like Obsidian\r\n</html>",
				0, 0, 1, 2, 1, 1,
				ElementGenerator.getFont(), null
		);
		oreResistanceSpinner = ORE_RESISTANCE_SPINNER.getSpinner();
		oreBlockPanel.add(oreResistanceSpinner, ORE_RESISTANCE_SPINNER.getConstraints());
		
		// Block Resistance Label
		Label BLOCK_RESISTANCE_LABEL = new Label(
				"Resistance", "", null,
				0, 0, 3, 2, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(BLOCK_RESISTANCE_LABEL.getLabel(), BLOCK_RESISTANCE_LABEL.getConstraints());
		
		// Block Resistance Spinner
		Spinner BLOCK_RESISTANCE_SPINNER = new Spinner(
				ElementGenerator.copy(RESISTANCE_SPINNER_MODEL), "<html>\r\nHow resistant the block is to explosions\r\n<p>\r\n10 is like Stone\r\n<p>\r\n2000 is like Obsidian\r\n</html>",
				0, 0, 4, 2, 1, 1,
				ElementGenerator.getFont(), null
		);
		blockResistanceSpinner = BLOCK_RESISTANCE_SPINNER.getSpinner();
		oreBlockPanel.add(blockResistanceSpinner, BLOCK_RESISTANCE_SPINNER.getConstraints());
		
		// Ore Light Level Label
		Label ORE_LIGHT_LEVEL_LABEL = new Label(
				"Light Level", "", null,
				0, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(ORE_LIGHT_LEVEL_LABEL.getLabel(), ORE_LIGHT_LEVEL_LABEL.getConstraints());
		
		// Ore Light Level Spinner
		Spinner ORE_LIGHT_LEVEL_SPINNER = new Spinner(
				LIGHT_LEVEL_SPINNER_MODEL, "<html>\r\nHow much light is emitted from the ore\r\n<p>\r\n0 emits no light\r\n<p>\r\n16 emits light as if full sunlight\r\n</html>",
				0, 0, 1, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		oreLightLevelSpinner = ORE_LIGHT_LEVEL_SPINNER.getSpinner();
		oreBlockPanel.add(oreLightLevelSpinner, ORE_LIGHT_LEVEL_SPINNER.getConstraints());
		
		// Block Light Level Label
		Label BLOCK_LIGHT_LEVEL_LABEL = new Label(
				"Light Level", "", null,
				0, 0, 3, 3, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(BLOCK_LIGHT_LEVEL_LABEL.getLabel(), BLOCK_LIGHT_LEVEL_LABEL.getConstraints());
		
		// Block Light Level Spinner
		Spinner BLOCK_LIGHT_LEVEL_SPINNER = new Spinner(
				ElementGenerator.copy(LIGHT_LEVEL_SPINNER_MODEL), "<html>\r\nHow much light is emitted from the block\r\n<p>\r\n0 emits no light\r\n<p>\r\n16 emits light as if full sunlight\r\n</html>",
				0, 0, 4, 3, 1, 1,
				ElementGenerator.getFont(), null
		);
		blockLightLevelSpinner = BLOCK_LIGHT_LEVEL_SPINNER.getSpinner();
		oreBlockPanel.add(blockLightLevelSpinner, BLOCK_LIGHT_LEVEL_SPINNER.getConstraints());
		
		// Ore Light Opacity Label
		Label ORE_LIGHT_OPACITY_LABEL = new Label(
				"Light Opacity", "", null,
				0, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(ORE_LIGHT_OPACITY_LABEL.getLabel(), ORE_LIGHT_OPACITY_LABEL.getConstraints());
		
		// Ore Light Opacity Spinner
		Spinner ORE_LIGHT_OPACITY_SPINNER = new Spinner(
				ElementGenerator.copy(LIGHT_OPACITY_SPINNER_MODEL), "<html>\r\nHow much light can pass through the ore\r\n<p>\r\n0 lets all light pass through (transparent)\r\n<p>\r\n16 lets no light pass through (opaque)\r\n</html>",
				0, 0, 1, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		oreLightOpacitySpinner = ORE_LIGHT_OPACITY_SPINNER.getSpinner();
		oreBlockPanel.add(oreLightOpacitySpinner, ORE_LIGHT_OPACITY_SPINNER.getConstraints());
		
		// Block Light Opacity Label
		Label BLOCK_LIGHT_OPACITY_LABEL = new Label(
				"Light Opacity", "", null,
				0, 0, 3, 4, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(BLOCK_LIGHT_OPACITY_LABEL.getLabel(), BLOCK_LIGHT_OPACITY_LABEL.getConstraints());
		
		// Block Light Opacity Spinner
		Spinner BLOCK_LIGHT_OPACITY_SPINNER = new Spinner(
				ElementGenerator.copy(LIGHT_OPACITY_SPINNER_MODEL), "<html>\r\nHow much light can pass through the block\r\n<p>\r\n0 lets all light pass through (transparent)\r\n<p>\r\n16 lets no light pass through (opaque)\r\n</html>",
				0, 0, 4, 4, 1, 1,
				ElementGenerator.getFont(), null
		);
		blockLightOpacitySpinner = BLOCK_LIGHT_OPACITY_SPINNER.getSpinner();
		oreBlockPanel.add(blockLightOpacitySpinner, BLOCK_LIGHT_OPACITY_SPINNER.getConstraints());
		
		// Ore Harvest Level Label
		Label ORE_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				0, 0, 0, 5, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(ORE_HARVEST_LEVEL_LABEL.getLabel(), ORE_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Ore Harvest Level Slider
		Slider ORE_HARVEST_LEVEL_SLIDER = new Slider(
				0, 0, MAXIMUM_HARVEST_LEVEL, "<html>\r\nThe type of tool it takes to harvest the ore\r\n<p>\r\n0 takes a wood tool\r\n<p>\r\n1 takes a stone tool\r\n<p>\r\n2 takes an iron or gold tool\r\n<p>\r\n3 takes a diamond tool\r\n</html>",
				0, GridBagConstraints.HORIZONTAL, 1, 5, 1, 1,
				ElementGenerator.getFont()
		);
		oreHarvestLevelSlider = ORE_HARVEST_LEVEL_SLIDER.getSlider();
		oreBlockPanel.add(oreHarvestLevelSlider, ORE_HARVEST_LEVEL_SLIDER.getConstraints());
		
		// Block Harvest Level Label
		Label BLOCK_HARVEST_LEVEL_LABEL = new Label(
				"Harvest Level", "", null,
				0, 0, 3, 5, 1, 1,
				ElementGenerator.getFont()
		);
		oreBlockPanel.add(BLOCK_HARVEST_LEVEL_LABEL.getLabel(), BLOCK_HARVEST_LEVEL_LABEL.getConstraints());
		
		// Block Harvest Level Slider
		Slider BLOCK_HARVEST_LEVEL_SLIDER = new Slider(
				0, 0, MAXIMUM_HARVEST_LEVEL, "<html>\r\nThe type of tool it takes to harvest the block\r\n<p>\r\n0 takes a wood tool\r\n<p>\r\n1 takes a stone tool\r\n<p>\r\n2 takes an iron or gold tool\r\n<p>\r\n3 takes a diamond tool\r\n</html>",
				0, 0, 4, 5, 1, 1,
				ElementGenerator.getFont()
		);
		blockHarvestLevelSlider = BLOCK_HARVEST_LEVEL_SLIDER.getSlider();
		oreBlockPanel.add(blockHarvestLevelSlider, BLOCK_HARVEST_LEVEL_SLIDER.getConstraints());
		
	}
	
	/**
	 * Updates all the JComponents in this panel
	 */
	public static void update(Element element) {
		
		ElementGenerator.logger.log("GenerationPanel", "Updating the panel");
		
		updateNether(element);
		updateSurface(element);
		updateEnd(element);
		updateOreBlock(element);
	}
	
	/**
	 * Updates the Nether Panel and all its components
	 */
	public static void updateNether(Element element) {
		
		ElementGenerator.logger.log("GenerationPanel", "Updating the nether generation panel");
		
		// Get Nether generation from element
		Generation nether = element.getGenerationData().getNether();
		
		// Set Checkbox
		generateInNetherCheckBox.setSelected(nether.doesGenerate());
		
		// Set High and Low Spinners
		highestNetherGenerationSpinner.setValue(nether.getHigh());
		lowestNetherGenerationSpinner.setValue(nether.getLow());
		
		// Set Rarity Sliders
		netherRaritySlider.setValue(nether.getWorldRarity());
		netherChunkRaritySlider.setValue(nether.getChunkRarity());
		
		// Enable or disable
		enableNether(nether.doesGenerate());
	}
	
	/**
	 * Updates the Surface Panel and all its components
	 */
	public static void updateSurface(Element element) {
		
		ElementGenerator.logger.log("GenerationPanel", "Updating the surface generation panel");
		
		// Get Surface generation from element
		Generation surface = element.getGenerationData().getSurface();
		
		// Set CheckBox
		generateOnSurfaceCheckBox.setSelected(surface.doesGenerate());
		
		// Set High and Low Spinners
		highestSurfaceGenerationSpinner.setValue(surface.getHigh());
		lowestSurfaceGenerationSpinner.setValue(surface.getLow());
		
		// Set Rarity Sliders
		surfaceRaritySlider.setValue(surface.getWorldRarity());
		surfaceChunkRaritySlider.setValue(surface.getChunkRarity());
		
		// Enable or disable
		enableSurface(surface.doesGenerate());
	}
	
	/**
	 * Updates the End Panel and all its components
	 */
	public static void updateEnd(Element element) {
		
		ElementGenerator.logger.log("GenerationPanel", "Updating the end generation panel");
		
		// Get End generation from element
		Generation end = element.getGenerationData().getEnd();
		
		// Set CheckBox
		generateInEndCheckBox.setSelected(end.doesGenerate());
		
		// Set High and Low Spinners
		highestEndGenerationSpinner.setValue(end.getHigh());
		lowestEndGenerationSpinner.setValue(end.getLow());
		
		// Set Rarity Sliders
		endRaritySlider.setValue(end.getWorldRarity());
		endChunkRaritySlider.setValue(end.getChunkRarity());
		
		// Enable or disable
		enableEnd(end.doesGenerate());
	}
	
	/**
	 * Updates the Ore & Block Panel and all its components
	 */
	public static void updateOreBlock(Element element) {
		
		ElementGenerator.logger.log("GenerationPanel", "Updating the ore and block panel");
		
		// Get Ore an Block data from element
		BlockData oreData = element.getOreData();
		BlockData blockData = element.getBlockData();
		
		// Set Ore Spinners and Sliders
		oreHardnessSpinner.setValue(oreData.getHardness() * 10.0);
		oreResistanceSpinner.setValue(oreData.getResistance());
		oreLightLevelSpinner.setValue(oreData.getLightLevel() * 100.0);
		oreLightOpacitySpinner.setValue(oreData.getLightOpacity());
		oreHarvestLevelSlider.setValue(oreData.getHarvestLevel());
		
		// Set Block Spinners and Sliders
		blockHardnessSpinner.setValue(blockData.getHardness() * 10.0);
		blockResistanceSpinner.setValue(blockData.getResistance());
		blockLightLevelSpinner.setValue(blockData.getLightLevel() * 100.0);
		blockLightOpacitySpinner.setValue(blockData.getLightOpacity());
		blockHarvestLevelSlider.setValue(blockData.getHarvestLevel());
		
	}
	
	/**
	 * Resets all the JComponents in this panel
	 */
	public static void reset() {
		
		ElementGenerator.logger.log("GenerationPanel", "Resetting the panel");
		
		resetNether();
		resetSurface();
		resetEnd();
		resetOreBlock();
		
	}
	
	/**
	 * Resets the Nether Panel and all its components
	 */
	public static void resetNether() {
		
		ElementGenerator.logger.log("GenerationPanel", "Resetting the nether panel");
		
		// Set CheckBox
		generateInNetherCheckBox.setSelected(false);
		
		// Set High and Low
		highestNetherGenerationSpinner.setValue(100);
		lowestNetherGenerationSpinner.setValue(10);
		
		// Set Rarity Sliders
		netherRaritySlider.setValue(10);
		netherChunkRaritySlider.setValue(16);
		
		// Enable or disable
		enableNether(false);
		
	}
	
	/**
	 * Resets the Surface Panel and all its components
	 */
	public static void resetSurface() {
		
		ElementGenerator.logger.log("GenerationPanel", "Resetting the surface panel");
		
		// Set CheckBox
		generateOnSurfaceCheckBox.setSelected(true);
		
		// Set High and Low
		highestSurfaceGenerationSpinner.setValue(100);
		lowestSurfaceGenerationSpinner.setValue(10);
		
		// Set Rarity Sliders
		surfaceRaritySlider.setValue(10);
		surfaceChunkRaritySlider.setValue(16);
		
		// Enable or disable
		enableSurface(true);
		
	}
	
	/**
	 * Resets the End Panel and all its components
	 */
	public static void resetEnd() {
		
		ElementGenerator.logger.log("GenerationPanel", "Resetting the end panel");
		
		// Set CheckBox
		generateInEndCheckBox.setSelected(false);
		
		// Set High and Low
		highestEndGenerationSpinner.setValue(100);
		lowestEndGenerationSpinner.setValue(10);
		
		// Set Rarity Sliders
		endRaritySlider.setValue(10);
		endChunkRaritySlider.setValue(16);
		
		// Enable or disable
		enableEnd(false);
		
	}
	
	/**
	 * Resets the Ore & Block Panel and all its components
	 */
	public static void resetOreBlock() {
		
		ElementGenerator.logger.log("GenerationPanel", "Resetting the ore and block panel");
		
		// Reset Ore Spinners and Sliders
		oreHardnessSpinner.setValue(HARDNESS_SPINNER_MODEL.getValue());
		oreResistanceSpinner.setValue(RESISTANCE_SPINNER_MODEL.getValue());
		oreLightLevelSpinner.setValue(LIGHT_LEVEL_SPINNER_MODEL.getValue());
		oreLightOpacitySpinner.setValue(LIGHT_OPACITY_SPINNER_MODEL.getValue());
		oreHarvestLevelSlider.setValue(0);
		
		// Reset Block Spinners and Sliders
		blockHardnessSpinner.setValue(HARDNESS_SPINNER_MODEL.getValue());
		blockResistanceSpinner.setValue(RESISTANCE_SPINNER_MODEL.getValue());
		blockLightLevelSpinner.setValue(LIGHT_LEVEL_SPINNER_MODEL.getValue());
		blockLightOpacitySpinner.setValue(LIGHT_OPACITY_SPINNER_MODEL.getValue());
		blockHarvestLevelSlider.setValue(0);
		
	}

	/**
	 * Enables or disables the JComponents under the nether panel
	 * @param enabled whether or not to enable the JComponents
	 */
	public static void enableNether(boolean enabled) {
		
		// Enables/disables the labels
		lowestNetherGenerationLabel.setEnabled(enabled);
		highestNetherGenerationLabel.setEnabled(enabled);
		netherRarityLabel.setEnabled(enabled);
		netherChunkRarityLabel.setEnabled(enabled);
		// Enables/disables the spinners and sliders
		lowestNetherGenerationSpinner.setEnabled(enabled);
		highestNetherGenerationSpinner.setEnabled(enabled);
		netherRaritySlider.setEnabled(enabled);
		netherChunkRaritySlider.setEnabled(enabled);
		
	}
	
	/**
	 * Enables or disables the JComponents under the surface panel
	 * @param enabled whether or not the enable the JComponents
	 */
	public static void enableSurface(boolean enabled) {

		// Enables/disables the labels
		lowestSurfaceGenerationLabel.setEnabled(enabled);
		highestSurfaceGenerationLabel.setEnabled(enabled);
		surfaceRarityLabel.setEnabled(enabled);
		surfaceChunkRarityLabel.setEnabled(enabled);
		// Enables/disables the spinners and sliders
		lowestSurfaceGenerationSpinner.setEnabled(enabled);
		highestSurfaceGenerationSpinner.setEnabled(enabled);
		surfaceRaritySlider.setEnabled(enabled);
		surfaceChunkRaritySlider.setEnabled(enabled);
		
	}
	
	/**
	 * Enables or disables the JComponents under the end panel
	 * @param enabled whether or not the enable the JComponents
	 */
	public static void enableEnd(boolean enabled) {

		// Enables/disables the labels
		lowestEndGenerationLabel.setEnabled(enabled);
		highestEndGenerationLabel.setEnabled(enabled);
		endRarityLabel.setEnabled(enabled);
		endChunkRarityLabel.setEnabled(enabled);
		// Enables/disables the spinners and sliders
		lowestEndGenerationSpinner.setEnabled(enabled);
		highestEndGenerationSpinner.setEnabled(enabled);
		endRaritySlider.setEnabled(enabled);
		endChunkRaritySlider.setEnabled(enabled);
		
	}
}
