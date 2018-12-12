package elementgenerator.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import elementgenerator.element.ColorData;
import elementgenerator.element.Element;
import elementgenerator.gui.customcomponents.Button;
import elementgenerator.gui.customcomponents.CheckBox;
import elementgenerator.gui.customcomponents.ComboBox;
import elementgenerator.gui.customcomponents.Label;
import elementgenerator.gui.customcomponents.Separator;
import elementgenerator.gui.customcomponents.Spinner;
import elementgenerator.gui.customcomponents.TextField;
import elementgenerator.util.ElementUtils;
import elementgenerator.util.ModUtils;

/**
 * The BasicPanel for the ElementGenerator GUI
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class BasicPanel {
	
	// Models
	private static final String[] gems = new String[] {
			"Rock", "Ingot", "Nugget", "Dust", "Gem", "Crystal", "Shard", "Pebble", "Star", "Amethyst"
	};
	private static final SpinnerNumberModel DROPS_SPINNER_MODEL = new SpinnerNumberModel(
			1, 		// Initial Value
			1, 		// Minimum
			null,	// Maximum
			1       // Step Value
	);
	
	// Main Panel
	public static JPanel panel;
	
	// Basic Components
	public static JTextField elementNameTextField;
	public static JComboBox<Object>  gemTypeComboBox;
	public static JCheckBox  dropsItselfCheckBox;
	public static JLabel     lowestDropsLabel;
	public static JLabel     highestDropsLabel;
	public static JSpinner   lowestDropsSpinner;
	public static JSpinner   highestDropsSpinner;
	
	// Color Components
	public static JButton    darkestColorButton;
	public static JButton    lightestColorButton;
	
	public static JLabel     netherOrePreview;
	public static JLabel     surfaceOrePreview;
	public static JLabel     endOrePreview;
	public static JLabel     blockPreview;
	public static JLabel     gemPreview;
	public static JLabel     pickaxePreview;
	public static JLabel     axePreview;
	public static JLabel     hoePreview;
	public static JLabel     shovelPreview;
	public static JLabel     swordPreview;
	public static JLabel     helmetPreview;
	public static JLabel     chestplatePreview;
	public static JLabel     leggingsPreview;
	public static JLabel     bootsPreview;
	
	
	/**
	 * Sets up all the JComponents
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("BasicPanel", "setting up BasicPanel");
		
		// Setup Panel
		panel = new JPanel();
		
		// Setup layout
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 20, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 18, 16, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		
		// Set layout
		panel.setLayout(gbl_panel);
		
		// ******************************************
		
		// Basic Title Label
		Label BASIC_LABEL = new Label(
				"Basic", "", null,
				0, 0, 2, 2, 3, 1,
				ElementGenerator.getTitleFont()
		);
		panel.add(BASIC_LABEL.getLabel(), BASIC_LABEL.getConstraints());
		
		// Basic Separator
		Separator BASIC_SEPARATOR = new Separator(
				SwingConstants.VERTICAL,
				0, GridBagConstraints.BOTH, 8, 2, 1, 5
		);
		panel.add(BASIC_SEPARATOR.getSeparator(), BASIC_SEPARATOR.getConstraints());
		
		// Texture Label
		Label TEXTURE_LABEL = new Label(
				"Texture Preview", "", null,
				0, 0, 9, 1, 3, 1,
				ElementGenerator.getTitleFont()
		);
		panel.add(TEXTURE_LABEL.getLabel(), TEXTURE_LABEL.getConstraints());
		
		// Element Label
		Label ELEMENT_NAME_LABEL = new Label(
				"Name", "", null,
				0, 0, 2, 3, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(ELEMENT_NAME_LABEL.getLabel(), ELEMENT_NAME_LABEL.getConstraints());
		
		// Element Text Field
		TextField ELEMENT_NAME_TEXTFIELD = new TextField(
				"", "The name of the element.",
				0, GridBagConstraints.HORIZONTAL, 3, 3, 2, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("BasicPanel", "Changing Element Name To: " + elementNameTextField.getText());
					}
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("BasicPanel", "Changing Element Name To: " + elementNameTextField.getText());
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("BasicPanel", "Changing Element Name To: " + elementNameTextField.getText());
					}
				}
		);
		elementNameTextField = ELEMENT_NAME_TEXTFIELD.getTextField();
		panel.add(elementNameTextField, ELEMENT_NAME_TEXTFIELD.getConstraints());
		
		// Gem Type Label
		Label GEM_TYPE_LABEL = new Label(
				"Gem Type", "", null,
				0, 0, 2, 4, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(GEM_TYPE_LABEL.getLabel(), GEM_TYPE_LABEL.getConstraints());
		
		// Gem Type ComboBox
		ComboBox GEM_TYPE_COMBOBOX = new ComboBox(
				gems, "Lets you select what type of gem the element is.",
				0, GridBagConstraints.HORIZONTAL, 3, 4, 2, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						updateTexturePreviews();
						ElementGenerator.logger.log("BasicPanel", "Changing Gem Type of " + elementNameTextField.getText() + " To: " + gemTypeComboBox.getSelectedItem());
					}
				}
		);
		gemTypeComboBox = GEM_TYPE_COMBOBOX.getComboBox();
		panel.add(gemTypeComboBox, GEM_TYPE_COMBOBOX.getConstraints());
		
		// Darkest Color Label
		Label DARKEST_COLOR_LABEL = new Label(
				"Darkest Color", "", null,
				0, 0, 6, 3, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(DARKEST_COLOR_LABEL.getLabel(), DARKEST_COLOR_LABEL.getConstraints());
		
		// Darkest Color Button
		Button DARKEST_COLOR_BUTTON = new Button(
				"Choose Color...", "The darkest color of the element.",
				0, 0, 7, 3, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						colorChange("Choose the Darkest Color", darkestColorButton);
						ElementGenerator.logger.log("BasicPanel", "Changing Darkest Color of " + elementNameTextField.getText() + " To: " + darkestColorButton.toString());
					}
				}
		);
		darkestColorButton = DARKEST_COLOR_BUTTON.getButton();
		darkestColorButton.setBackground(ModUtils.TEMPLATE_COLORS[0]);
		darkestColorButton.setForeground(ModUtils.TEMPLATE_COLORS[0]);
		panel.add(darkestColorButton, DARKEST_COLOR_BUTTON.getConstraints());
		
		// Lightest Color Label
		Label LIGHTEST_COLOR_LABEL = new Label(
				"Lightest Color", "", null,
				0, 0, 6, 4, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(LIGHTEST_COLOR_LABEL.getLabel(), LIGHTEST_COLOR_LABEL.getConstraints());
		
		// Lightest Color Button
		Button LIGHTEST_COLOR_BUTTON = new Button(
				"Choose Color...", "The lightest color of the element.",
				0, 0, 7, 4, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						colorChange("Choose the Lightest Color", lightestColorButton);
						ElementGenerator.logger.log("BasicPanel", "Changing Lightest Color of " + elementNameTextField.getText() + " To: " + lightestColorButton.toString());
					}
				}
		);
		lightestColorButton = LIGHTEST_COLOR_BUTTON.getButton();
		lightestColorButton.setBackground(ModUtils.TEMPLATE_COLORS[5]);
		lightestColorButton.setForeground(ModUtils.TEMPLATE_COLORS[5]);
		panel.add(lightestColorButton, LIGHTEST_COLOR_BUTTON.getConstraints());
		
		// Drops Itself CheckBox
		CheckBox DROPS_ITSELF_CHECKBOX = new CheckBox(
				"Drops The Ore?", "Whether or not the ore of the element drops itself or the gem.",
				0, 0, 2, 5, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						lowestDropsSpinner.setEnabled(!dropsItselfCheckBox.isSelected());
						highestDropsSpinner.setEnabled(!dropsItselfCheckBox.isSelected());
						
						lowestDropsLabel.setEnabled(!dropsItselfCheckBox.isSelected());
						highestDropsLabel.setEnabled(!dropsItselfCheckBox.isSelected());
						
						ElementGenerator.logger.log("BasicPanel", "Changing \"Drops Ore?\" of " + elementNameTextField.getText() + " To: " + dropsItselfCheckBox.isSelected());
					}
				}
		);
		dropsItselfCheckBox = DROPS_ITSELF_CHECKBOX.getCheckBox();
		panel.add(dropsItselfCheckBox, DROPS_ITSELF_CHECKBOX.getConstraints());
		
		// Lowest Drops Label
		Label LOWEST_DROPS_LABEL = new Label(
				"Lowest amount of drops", "", null,
				0, 0, 3, 5, 1, 1,
				ElementGenerator.getFont()
		);
		lowestDropsLabel = LOWEST_DROPS_LABEL.getLabel();
		lowestDropsLabel.setEnabled(!dropsItselfCheckBox.isSelected());
		panel.add(lowestDropsLabel, LOWEST_DROPS_LABEL.getConstraints());
		
		// Lowest Drops Spinner
		Spinner LOWEST_DROPS_SPINNER = new Spinner(
				ElementGenerator.copy(DROPS_SPINNER_MODEL), "<html>\r\nThe minimum amount of gems the ore drops\r\n<p>\r\n(Only relevant when the \"Drops The Ore\" checkbox is unchecked)\r\n</html>.",
				0, 0, 4, 5, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get Lowest and Highest values
						int lowest = (int)lowestDropsSpinner.getValue();
						int highest = (int)highestDropsSpinner.getValue();
						
						// Make sure lowest drops is less than highest drops
						if(lowest > highest)
							lowest = highest;
						
						// Set lowest value
						lowestDropsSpinner.setValue(lowest);
						
						ElementGenerator.logger.log("BasicPanel", "Changing Lowest Drops of " + elementNameTextField.getText() + " To: " + lowestDropsSpinner.getValue());
					}
				}
		);
		lowestDropsSpinner = LOWEST_DROPS_SPINNER.getSpinner();
		lowestDropsSpinner.setEnabled(!dropsItselfCheckBox.isSelected());
		panel.add(lowestDropsSpinner, LOWEST_DROPS_SPINNER.getConstraints());
		
		// Highest Drops Label
		Label HIGHEST_DROPS_LABEL = new Label(
				"Highest amount of drops", "", null,
				0, 0, 3, 6, 1, 1,
				ElementGenerator.getFont()
		);
		highestDropsLabel = HIGHEST_DROPS_LABEL.getLabel();
		highestDropsLabel.setEnabled(!dropsItselfCheckBox.isSelected());
		panel.add(highestDropsLabel, HIGHEST_DROPS_LABEL.getConstraints());
		
		// Highest Drops Spinner
		Spinner HIGHEST_DROPS_SPINNER = new Spinner(
				ElementGenerator.copy(DROPS_SPINNER_MODEL), "<html>\r\nThe maximum amount of gems the ore drops\r\n<p>\r\n(Only relevant when the \"Drops The Ore\" checkbox is unchecked)\r\n</html>.",
				0, 0, 4, 6, 1, 1,
				ElementGenerator.getFont(), new ChangeListener() {
					public void stateChanged(ChangeEvent changeEvent) {
						// Get Lowest and Highest values
						int lowest = (int)lowestDropsSpinner.getValue();
						int highest = (int)highestDropsSpinner.getValue();
						
						// Make sure highest drops is higher than lowest drops
						if(highest < lowest)
							highest = lowest;
						
						// Set Highest value
						highestDropsSpinner.setValue(highest);
						
						ElementGenerator.logger.log("BasicPanel", "Changing Highest Drops of " + elementNameTextField.getText() + " To: " + highestDropsSpinner.getValue());
					}
				}
		);
		highestDropsSpinner = HIGHEST_DROPS_SPINNER.getSpinner();
		highestDropsSpinner.setEnabled(!dropsItselfCheckBox.isSelected());
		panel.add(highestDropsSpinner, HIGHEST_DROPS_SPINNER.getConstraints());
		
		// Cancel Button
		Button CANCEL_BUTTON = new Button(
				"Cancel", "Cancels making a new element.",
				0, 0, 0, 8, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						BasicPanel.reset();
						GenerationPanel.reset();
						ToolPanel.reset();
						ElementGenerator.changeMenu("main");
						
						ElementGenerator.logger.log("BasicPanel", "Changing Panel To: MainPanel");
					}
				}
		);
		panel.add(CANCEL_BUTTON.getButton(), CANCEL_BUTTON.getConstraints());
		
		// Next Button
		Button NEXT_BUTTON = new Button(
				"Next", "Advances to the next page of the element generator.",
				0, 0, 13, 8, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						ElementGenerator.changeMenu("generation");
						ElementGenerator.logger.log("BasicPanel", "Changing Panel To: GenerationPanel");
					}
				}
		);
		panel.add(NEXT_BUTTON.getButton(), NEXT_BUTTON.getConstraints());
		
		setupOreBlockGemPreviews();
		setupToolPreviews();
		setupArmorPreviews();
		
		updateTexturePreviews();
		
	}
	/**
	 * Sets up the ores, the block, and the gem previews
	 */
	public static void setupOreBlockGemPreviews() {
		
		ElementGenerator.logger.log("BasicPanel", "Setting up the previews for the ores, the block, and the gem");
		
		// Nether Ore Label
		Label NETHER_ORE_LABEL = new Label(
				"", "A preview of the nether ore texture", null,
				0, 0, 9, 2, 1, 1,
				ElementGenerator.getFont()
		);
		netherOrePreview = NETHER_ORE_LABEL.getLabel();
		panel.add(netherOrePreview, NETHER_ORE_LABEL.getConstraints());
		
		// Surface Ore Label
		Label SURFACE_ORE_LABEL = new Label(
				"", "A preview of the surface ore texture.", null,
				0, 0, 9, 3, 1, 1,
				ElementGenerator.getFont()
		);
		surfaceOrePreview = SURFACE_ORE_LABEL.getLabel();
		panel.add(surfaceOrePreview, SURFACE_ORE_LABEL.getConstraints());
		
		// End Ore Label
		Label END_ORE_LABEL = new Label(
				"", "A preview of the end ore texture.", null,
				0, 0, 9, 4, 1, 1,
				ElementGenerator.getFont()
		);
		endOrePreview = END_ORE_LABEL.getLabel();
		panel.add(endOrePreview, END_ORE_LABEL.getConstraints());
		
		// Block Label
		Label BLOCK_LABEL = new Label(
				"", "A preview of the block texture.", null,
				0, 0, 9, 5, 1, 1,
				ElementGenerator.getFont()
		);
		blockPreview = BLOCK_LABEL.getLabel();
		panel.add(blockPreview, BLOCK_LABEL.getConstraints());
		
		// Gem Label
		Label GEM_LABEL = new Label(
				"", "A preview of the gem texture.", null,
				0, 0, 9, 6, 1, 1,
				ElementGenerator.getFont()
		);
		gemPreview = GEM_LABEL.getLabel();
		panel.add(gemPreview, GEM_LABEL.getConstraints());
		
	}
	/**
	 * Sets up the tool previews
	 */
	public static void setupToolPreviews() {
		
		ElementGenerator.logger.log("BasicPanel", "Setting up the previews for the tools");
		
		// Pickaxe Label
		Label PICKAXE_LABEL = new Label(
				"", "A preview of the pickaxe texture.", null,
				0, 0, 10, 2, 1, 1,
				ElementGenerator.getFont()
		);
		pickaxePreview = PICKAXE_LABEL.getLabel();
		panel.add(pickaxePreview, PICKAXE_LABEL.getConstraints());
		
		// Axe Label
		Label AXE_LABEL = new Label(
				"", "A preview of the axe texture.", null,
				0, 0, 10, 3, 1, 1,
				ElementGenerator.getFont()
		);
		axePreview = AXE_LABEL.getLabel();
		panel.add(axePreview, AXE_LABEL.getConstraints());
		
		// Hoe Label
		Label HOE_LABEL = new Label(
				"", "A preview of the hoe texture.", null,
				0, 0, 10, 4, 1, 1,
				ElementGenerator.getFont()
		);
		hoePreview = HOE_LABEL.getLabel();
		panel.add(hoePreview, HOE_LABEL.getConstraints());
		
		// Shovel Label
		Label SHOVEL_LABEL = new Label(
				"", "A preview of the shovel texture.", null,
				0, 0, 10, 5, 1, 1,
				ElementGenerator.getFont()
		);
		shovelPreview = SHOVEL_LABEL.getLabel();
		panel.add(shovelPreview, SHOVEL_LABEL.getConstraints());
		
		// Sword Label
		Label SWORD_LABEL = new Label(
				"", "A preview of the sword texture.", null,
				0, 0, 10, 6, 1, 1,
				ElementGenerator.getFont()
		);
		swordPreview = SWORD_LABEL.getLabel();
		panel.add(swordPreview, SWORD_LABEL.getConstraints());
		
	}
	/**
	 * Sets up the armor previews
	 */
	public static void setupArmorPreviews() {
		
		ElementGenerator.logger.log("BasicPanel", "Setting up the previews for the armor");
		
		// Helmet Label
		Label HELMET_LABEL = new Label(
				"", "A preview of the helmet texture.", null,
				0, 0, 11, 2, 1, 1,
				ElementGenerator.getFont()
		);
		helmetPreview = HELMET_LABEL.getLabel();
		panel.add(helmetPreview, HELMET_LABEL.getConstraints());
		
		// Chestplate Label
		Label CHESTPLATE_LABEL = new Label(
				"", "A preview of the chestplate texture.", null,
				0, 0, 11, 3, 1, 1,
				ElementGenerator.getFont()
		);
		chestplatePreview = CHESTPLATE_LABEL.getLabel();
		panel.add(chestplatePreview, CHESTPLATE_LABEL.getConstraints());
		
		// Leggings Label
		Label LEGGINGS_LABEL = new Label(
				"", "A preview of the leggings texture.", null,
				0, 0, 11, 4, 1, 1,
				ElementGenerator.getFont()
		);
		leggingsPreview = LEGGINGS_LABEL.getLabel();
		panel.add(leggingsPreview, LEGGINGS_LABEL.getConstraints());
		
		// Boots Label
		Label BOOTS_LABEL = new Label(
				"", "A preview of the boots texture.", null,
				0, 0, 11, 5, 1, 1,
				ElementGenerator.getFont()
		);
		bootsPreview = BOOTS_LABEL.getLabel();
		panel.add(bootsPreview, BOOTS_LABEL.getConstraints());
		
	}	
	/**
	 * Updates all the JComponents
	 * @param element the Element to load
	 */
	public static void update(Element element) {
		
		ElementGenerator.logger.log("BasicPanel", "Loading in the element data for " + element.getName());
		
		// Get the basic element information and the color data
		String name = element.getName();
		int gem = element.getGemId();
		boolean dropsGem = element.doesGemDrop();
		int leastQuantity = element.getLeastQuantity();
		int mostQuantity = element.getMostQuantity();
		
		ColorData colorData = element.getColorData();
		
		// Set the basic information
		elementNameTextField.setText(name);
		gemTypeComboBox.setSelectedIndex(gem);
		dropsItselfCheckBox.setSelected(!dropsGem);
		highestDropsLabel.setEnabled(dropsGem);
		lowestDropsLabel.setEnabled(dropsGem);
		highestDropsSpinner.setEnabled(dropsGem);
		lowestDropsSpinner.setEnabled(dropsGem);
		highestDropsSpinner.setValue(mostQuantity);
		lowestDropsSpinner.setValue(leastQuantity);
		
		// Set the color information
		darkestColorButton.setBackground(colorData.getDarkestColor());
		darkestColorButton.setForeground(colorData.getDarkestColor());
		lightestColorButton.setBackground(colorData.getLightestColor());
		lightestColorButton.setForeground(colorData.getLightestColor());
		
		// Update the texture previews
		updateTexturePreviews();
		
	}
	/**
	 * Updates all the texture previews
	 */
	public static void updateTexturePreviews() {
		
		ElementGenerator.logger.log("BasicPanel", "Updating the texture previews");
		
		ImageIcon[] imageIcons = getTexturePreviews(64);
		
		// Set image icon to specific labels
		netherOrePreview.setIcon(imageIcons[0]);
		surfaceOrePreview.setIcon(imageIcons[1]);
		endOrePreview.setIcon(imageIcons[2]);
		blockPreview.setIcon(imageIcons[3]);
		gemPreview.setIcon(imageIcons[4]);
		
		pickaxePreview.setIcon(imageIcons[5]);
		axePreview.setIcon(imageIcons[6]);
		hoePreview.setIcon(imageIcons[7]);
		shovelPreview.setIcon(imageIcons[8]);
		swordPreview.setIcon(imageIcons[9]);
		
		helmetPreview.setIcon(imageIcons[10]);
		chestplatePreview.setIcon(imageIcons[11]);
		leggingsPreview.setIcon(imageIcons[12]);
		bootsPreview.setIcon(imageIcons[13]);
		
	}
	/**
	 * Reset all the JComponents
	 */
	public static void reset() {
		
		ElementGenerator.logger.log("BasicPanel", "Resetting the panel");
		
		// Reset basic information
		elementNameTextField.setText("");
		gemTypeComboBox.setSelectedIndex(0);
		dropsItselfCheckBox.setSelected(true);
		highestDropsLabel.setEnabled(false);
		lowestDropsLabel.setEnabled(false);
		highestDropsSpinner.setEnabled(false);
		lowestDropsSpinner.setEnabled(false);
		highestDropsSpinner.setValue(1);
		lowestDropsSpinner.setValue(1);
		
		// Reset color information
		darkestColorButton.setBackground(ModUtils.TEMPLATE_COLORS[0]);
		darkestColorButton.setForeground(ModUtils.TEMPLATE_COLORS[0]);
		lightestColorButton.setBackground(ModUtils.TEMPLATE_COLORS[5]);
		lightestColorButton.setForeground(ModUtils.TEMPLATE_COLORS[5]);
		
		// Update texture previews
		updateTexturePreviews();
		
	}
	/**
	 * Asks to select a color and then it updates the colors for the previews
	 * @param title the title of the Color Dialog
	 * @param target that target component that it stems from
	 */
	private static void colorChange(String title, JComponent target) {
		
		ElementGenerator.logger.log("BasicPanel", "Asking user for a color. Display title: " + title);
		
		Color color = JColorChooser.showDialog(panel, title, target.getBackground());
		
		// Only set background & foreground to color if it isn't a null object
		if(color != null) {
			// Set target background & foregrounds
			target.setBackground(color);
			target.setForeground(color);
			updateTexturePreviews();
		}
	}
	/**
	 * Returns an array of ImageIcon's for use with the texture previews
	 * @param element the element to get the colors from
	 * @param resolution the size of the previews
	 * @return ImageIcon[]
	 */
	private static ImageIcon[] getTexturePreviews(int resolution) {
		
		// Get the gradient of the colors from the buttons and the current gem to load the proper texture
		Color[] gradient = ElementUtils.getGradient(
				darkestColorButton.getBackground(), 
				lightestColorButton.getForeground()
		);
		String currentGem = gemTypeComboBox.getSelectedItem().toString().toLowerCase();
		
		// Get an array of replacement colors
		Color[] replacementColors = new Color[] {
				ModUtils.TEMPLATE_COLORS[0], gradient[0],
				ModUtils.TEMPLATE_COLORS[1], gradient[1],
				ModUtils.TEMPLATE_COLORS[2], gradient[2],
				ModUtils.TEMPLATE_COLORS[3], gradient[3],
				ModUtils.TEMPLATE_COLORS[4], gradient[4],
				ModUtils.TEMPLATE_COLORS[5], gradient[5]
		};
		
		// Initialize an array of ImageIcons
		ImageIcon[] imageIcons = new ImageIcon[14];
		
		// Nether Ore
		imageIcons[0] = new ImageIcon(
				ElementUtils.replaceColors(
						String.format(
								ModUtils.NETHER_ORE_TEXTURE,
								currentGem
						), 
						replacementColors
				).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
		);
		// Surface Ore
		imageIcons[1] = new ImageIcon(
				ElementUtils.replaceColors(
						String.format(
								ModUtils.SURFACE_ORE_TEXTURE,
								currentGem
								), 
						replacementColors
				).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
		);
		// End Ore
		imageIcons[2] = new ImageIcon(
				ElementUtils.replaceColors(
						String.format(
								ModUtils.END_ORE_TEXTURE,
								currentGem
								), 
						replacementColors
				).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
		);
		// Block
		imageIcons[3] = new ImageIcon(
				ElementUtils.replaceColors(
						String.format(
								ModUtils.BLOCK_TEXTURE,
								currentGem
								), 
						replacementColors
				).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
		);
		// Gem
		imageIcons[4] = new ImageIcon(
				ElementUtils.replaceColors(
						String.format(
								ModUtils.GEM_TEXTURE,
								currentGem
								), 
						replacementColors
				).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
		);
		
		// Add Tools & Armor
		String[] tools = new String[] {
				"pickaxe", "axe", "hoe", "shovel", "sword",
				"helmet", "chestplate", "leggings", "boots"
		};
		
		for(int i = 0; i < tools.length; i++) {
			imageIcons[i + 5] = new ImageIcon(
					ElementUtils.replaceColors(
							String.format(
									ModUtils.TOOL_TEXTURE,
									tools[i].toLowerCase()
							),
							replacementColors
					).getScaledInstance(resolution, resolution, BufferedImage.SCALE_AREA_AVERAGING)
			);
		}
		
		return imageIcons;
	}

}
