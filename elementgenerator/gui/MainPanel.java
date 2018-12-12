package elementgenerator.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import org.apache.commons.io.FileUtils;

import elementgenerator.block.BlockData;
import elementgenerator.element.ColorData;
import elementgenerator.element.Element;
import elementgenerator.generation.Generation;
import elementgenerator.generation.GenerationData;
import elementgenerator.gui.customcomponents.Button;
import elementgenerator.gui.customcomponents.ComboBox;
import elementgenerator.gui.customcomponents.FileChooser;
import elementgenerator.gui.customcomponents.Label;
import elementgenerator.gui.customcomponents.Message;
import elementgenerator.gui.customcomponents.Panel;
import elementgenerator.gui.customcomponents.ScrollPane;
import elementgenerator.gui.customcomponents.Separator;
import elementgenerator.gui.customcomponents.TextArea;
import elementgenerator.gui.customcomponents.TextField;
import elementgenerator.mod.Authors;
import elementgenerator.mod.MinecraftVersion;
import elementgenerator.mod.Mod;
import elementgenerator.tool.Armor;
import elementgenerator.tool.ArmorData;
import elementgenerator.tool.Tool;
import elementgenerator.tool.ToolData;
import elementgenerator.util.ElementUtils;
import elementgenerator.util.Logger;
import elementgenerator.util.ModUtils;
import json.object.JSONObject;
import json.parser.JSON;

/**
 * The MainPanel to be used underneath the ElementGeneration GUI class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class MainPanel {
	
	// Minecraft Versions
	public static final String[] minecraftVersions = new String[] {
			"1.10.2",
			"1.11.2"
			// "1.12.2"
	};
	public static final MinecraftVersion[] minecraftVersionObjects = new MinecraftVersion[] {
			new MinecraftVersion("1.10.2", "12.18.3.2185", "1.6", "snapshot_20161111", "2.2"),
			new MinecraftVersion("1.11.2", "13.20.1.2386", "1.6", "snapshot_20161220", "2.2")
			// new MinecraftVersion("1.12.2", "14.23.1.2555", "1.8", "snapshot_20171003", "2.3")
	};
	
	// Main Panel
	public static JPanel panel;
	protected static GridBagConstraints panelConstraints;
	
	private static JPanel elementPanel;
	private static JScrollPane elementScrollPanel;
	
	private static Map<Object, Icon> iconImages;
	public static JComboBox<Object> creativeTabIconComboBox;
	private static int creativeTabIndex;
	
	public static JTextField modNameTextField;
	public static JTextArea  authorsTextArea;
	public static JTextField modVersionTextField;
	public static JComboBox<Object>  minecraftVersionComboBox;
	public static JTextField creditsTextField;
	public static JTextField urlTextField;
	public static JTextField updateUrlTextField;
	public static JTextArea  descriptionTextArea;
	
	public static Element[] elements = new Element[0];
	
	private static Point previousPosition;
	
	/**
	 * Used to either add or edit an element
	 * <p>
	 * <tab> When {@code INDEX} is -1, an element will be added
	 * <p>
	 * <tab> When {@code INDEX} is greater than 1, an element will be updated at the specified index
	 */
	public static int INDEX = -1; // Used to add (-1) or edit (! -1) an element
	
	/**
	 * Sets up all the JComponents needed for the MainPanel
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("MainPanel", "Setting up JPanel for the main menu");
		
		// Setup Panel
		Panel PANEL = new Panel(
				10, 6,
				0, GridBagConstraints.BOTH, 0, 0, 1, 1
		);
		PANEL.setGrow(new int[] {1, 3, 5}, new int[] {9});
		panel = PANEL.getPanel();
		panelConstraints = PANEL.getConstraints();
		
		// *************************************************************
		
		// Mod Name Label
		Label MOD_NAME_LABEL = new Label(
				"Mod Name", "", null,
				0, 0, 0, 0, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(MOD_NAME_LABEL.getLabel(), MOD_NAME_LABEL.getConstraints());
		
		// Mod Name TextField
		TextField MOD_NAME_TEXTFIELD = new TextField(
				"", "The name of the mod",
				0, GridBagConstraints.HORIZONTAL, 1, 0, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Name To: " + modNameTextField.getText());
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Name To: " + modNameTextField.getText());
					}
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Name To: " + modNameTextField.getText());
					}
				}
		);
		modNameTextField = MOD_NAME_TEXTFIELD.getTextField();
		panel.add(modNameTextField, MOD_NAME_TEXTFIELD.getConstraints());
		
		// Mod Author Label
		Label MOD_AUTHOR_LABEL = new Label(
				"Mod Authors", "", null,
				0, 0, 2, 0, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(MOD_AUTHOR_LABEL.getLabel(), MOD_AUTHOR_LABEL.getConstraints());
		
		// Mod Authors ScrollPane
		ScrollPane AUTHORS_SCROLL_PANE = new ScrollPane(
				8, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED,
				0, GridBagConstraints.BOTH, 3, 0, 1, 6
		);
		JScrollPane authorsScrollPane = AUTHORS_SCROLL_PANE.getScrollPane();
		panel.add(authorsScrollPane, AUTHORS_SCROLL_PANE.getConstraints());
		
		// - Authors Text Area
		TextArea AUTHORS_TEXTAREA = new TextArea(
				"", "A list of authors of the mod",
				0, 0, 0, 0, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Authors To: " + authorsTextArea.getText().replace("\n", " | "));
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Authors To: " + authorsTextArea.getText().replace("\n", " | "));
					}
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Authors To: " + authorsTextArea.getText().replace("\n", " | "));
					}
				}
		);
		authorsTextArea = AUTHORS_TEXTAREA.getTextArea();
		authorsScrollPane.setViewportView(authorsTextArea);
		
		// Description Label
		Label DESCRIPTION_LABEL = new Label(
				"Description", "", null,
				0, 0, 4, 0, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(DESCRIPTION_LABEL.getLabel(), DESCRIPTION_LABEL.getConstraints());
		
		// Description ScrollPane
		ScrollPane DESCRIPTION_SCROLL_PANE = new ScrollPane(
				2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED,
				0, GridBagConstraints.BOTH, 5, 0, 1, 6
		);
		JScrollPane descriptionScrollPane = DESCRIPTION_SCROLL_PANE.getScrollPane();
		panel.add(descriptionScrollPane, DESCRIPTION_SCROLL_PANE.getConstraints());
		
		// - Description Text Area
		TextArea DESCRIPTION_TEXTAREA = new TextArea(
				"", "The description of the mod",
				0, 0, 0, 0, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Description To: " + descriptionTextArea.getText().replace("\n", " | "));
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Description To: " + descriptionTextArea.getText().replace("\n", " | "));
					}
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Description To: " + descriptionTextArea.getText().replace("\n", " | "));
					}
				}
		);
		descriptionTextArea = DESCRIPTION_TEXTAREA.getTextArea();
		descriptionScrollPane.setViewportView(descriptionTextArea);
		
		// Mod Version Label
		Label MOD_VERSION_LABEL = new Label(
				"Mod Version", "", null,
				0, 0, 0, 1, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(MOD_VERSION_LABEL.getLabel(), MOD_VERSION_LABEL.getConstraints());
		
		// Mod Version TextField
		TextField MOD_VERSION_TEXTFIELD = new TextField(
				"", "The version number of the mod",
				0, GridBagConstraints.HORIZONTAL, 1, 1, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Version To: " + modVersionTextField.getText());
					}
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Version To: " + modVersionTextField.getText());
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Mod Version To: " + modVersionTextField.getText());
					}
				}
		);
		modVersionTextField = MOD_VERSION_TEXTFIELD.getTextField();
		panel.add(modVersionTextField, MOD_VERSION_TEXTFIELD.getConstraints());
		
		// Minecraft Version Label
		Label MINECRAFT_VERSION_LABEL = new Label(
				"Minecraft Version", "", null,
				0, 0, 0, 2, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(MINECRAFT_VERSION_LABEL.getLabel(), MINECRAFT_VERSION_LABEL.getConstraints());
		
		// Minecraft Version ComboBox
		ComboBox MINECRAFT_VERSION_COMBOBOX = new ComboBox(
				minecraftVersions, "Lets you select a Minecraft Version for the mod",
				0, GridBagConstraints.HORIZONTAL, 1, 2, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Version To: " + minecraftVersionComboBox.getSelectedItem());
					}
				}
		);
		minecraftVersionComboBox = MINECRAFT_VERSION_COMBOBOX.getComboBox();
		panel.add(minecraftVersionComboBox, MINECRAFT_VERSION_COMBOBOX.getConstraints());
		
		// Credits Label
		Label CREDITS_LABEL = new Label(
				"Credits", "", null,
				0, 0, 0, 3, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(CREDITS_LABEL.getLabel(), CREDITS_LABEL.getConstraints());
		
		// Credits Text Field
		TextField CREDITS_TEXTFIELD = new TextField(
				"", "Any credits to give for the mod",
				0, GridBagConstraints.HORIZONTAL, 1, 3, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Credits To: " + creditsTextField.getText().replace("\n", " | "));
					}
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Credits To: " + creditsTextField.getText().replace("\n", " | "));
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Credits To: " + creditsTextField.getText().replace("\n", " | "));
					}
				}
		);
		creditsTextField = CREDITS_TEXTFIELD.getTextField();
		panel.add(creditsTextField, CREDITS_TEXTFIELD.getConstraints());
		
		// URL Label
		Label URL_LABEL = new Label(
				"URL", "", null,
				0, 0, 0, 4, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(URL_LABEL.getLabel(), URL_LABEL.getConstraints());
		
		// URL TextField
		TextField URL_TEXTFIELD = new TextField(
				"", "A URL for the mod, the authors, or your own URL",
				0, GridBagConstraints.HORIZONTAL, 1, 4, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + urlTextField.getText());
					}
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + urlTextField.getText());
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + urlTextField.getText());
					}
				}
		);
		urlTextField = URL_TEXTFIELD.getTextField();
		panel.add(urlTextField, URL_TEXTFIELD.getConstraints());
		
		// Update URL Label
		Label UPDATE_URL_LABEL = new Label(
				"Update URL", "", null,
				GridBagConstraints.NORTH, 0, 0, 5, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(UPDATE_URL_LABEL.getLabel(), UPDATE_URL_LABEL.getConstraints());
		
		// Update URL TextField
		TextField UPDATE_URL_TEXTFIELD = new TextField(
				"", "A URL to detect any updates automatically",
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 1, 5, 1, 1,
				ElementGenerator.getFont(), new DocumentListener() {
					public void changedUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + updateUrlTextField.getText());
					}
					public void insertUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + updateUrlTextField.getText());
					}
					public void removeUpdate(DocumentEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing URL To: " + updateUrlTextField.getText());
					}
				}
		);
		updateUrlTextField = UPDATE_URL_TEXTFIELD.getTextField();
		panel.add(updateUrlTextField, UPDATE_URL_TEXTFIELD.getConstraints());
		
		// Element Separator
		Separator ELEMENT_SEPARATOR = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 6, 6, 1
		);
		panel.add(ELEMENT_SEPARATOR.getSeparator(), ELEMENT_SEPARATOR.getConstraints());
		
		// Element ScrollPane
		ScrollPane ELEMENT_SCROLL_PANE = new ScrollPane(
				8, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED, 
				0, GridBagConstraints.BOTH, 1, 7, 5, 3
		);
		elementScrollPanel = ELEMENT_SCROLL_PANE.getScrollPane();
		previousPosition = elementScrollPanel.getViewport().getViewPosition();
		panel.add(elementScrollPanel, ELEMENT_SCROLL_PANE.getConstraints());
		
		// - Element Panel
		Panel ELEMENT_PANEL = new Panel(
				3, 1,
				0, 0, 0, 0, 1, 1
		);
		elementPanel = ELEMENT_PANEL.getPanel();
		elementScrollPanel.setViewportView(elementPanel);
		
		// Creative Tab Icon Label
		Label CREATIVE_TAB_ICON_LABEL = new Label(
				"Creative Tab Icon", "", null,
				0, 0, 0, 7, 1, 1,
				ElementGenerator.getFont()
		);
		panel.add(CREATIVE_TAB_ICON_LABEL.getLabel(), CREATIVE_TAB_ICON_LABEL.getConstraints());
		
		// Creative Tab Icon Combo Box
		ComboBox CREATIVE_TAB_ICON_COMBOBOX = new ComboBox(
				new Object[0], "Lets you select what image is used for the Creative Tab",
				0, GridBagConstraints.HORIZONTAL, 0, 8, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ElementGenerator.logger.log("MainPanel", "Changing Creative Tab Icon To: " + creativeTabIconComboBox.getSelectedItem());
					}
				}
		);
		creativeTabIconComboBox = CREATIVE_TAB_ICON_COMBOBOX.getComboBox();
		panel.add(creativeTabIconComboBox, CREATIVE_TAB_ICON_COMBOBOX.getConstraints());
		
		updateElements();
		
	}
	
	/**
	 * Updates the JComponents in the MainPanel
	 */
	public static void update(Mod mod) {
		
		ElementGenerator.logger.log("MainPanel", "Loading mod (" + mod.getName() + ") into generator");
		
		// Get the mod information
		String name = mod.getName();
		String authors = mod.getAuthor().getAuthorAsString(true);
		String version = mod.getVersion();
		String minecraftVersion = mod.getMinecraftVersion().getVersion();
		int creativeTabIconIndex = mod.getCreativeTabIconIndex();
		String credits = mod.getCredits();
		String url = mod.getURL();
		String updateUrl = mod.getUpdateURL();
		String description = mod.getDescription().replace("\\n", "\n");
		
		// Set the mod information
		modNameTextField.setText(name);
		authorsTextArea.setText(authors);
		modVersionTextField.setText(version);
		minecraftVersionComboBox.setSelectedItem(minecraftVersion);
		creativeTabIndex = creativeTabIconIndex;
		creditsTextField.setText(credits);
		urlTextField.setText(url);
		updateUrlTextField.setText(updateUrl);
		descriptionTextArea.setText(description);
		
		// Update elements
		elements = mod.getElements();
		updateElements();
		
	}
	
	/**
	 * Updates the JComponents in the element panel
	 */
	public static void updateElements() {
		
		ElementGenerator.logger.log("MainPanel", "Updating elements in the element panel");
		
		final int GRID_ORIGIN_Y = 0;
		
		// Reset elementPanel
		while(elementPanel.getComponentCount() != 0)
			elementPanel.remove(0);
		
		// Add on to elementPanel
		int[] columnWidths = {
				0, 0,			// Element Button and Delete Button
				0, 0, 0, 0, 0,	// Ores, Block and Gem Previews
				0, 0, 0, 0, 0,	// Tool Previews
				0, 0, 0, 0,		// Armor Previews
				0, 0			// Move Up and Down buttons
		};
		double[] columnWeights = {
				1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 
				0.0, 0.0
				};
		int[] rowHeights= new int[elements.length];
		double[] rowWeights = new double[elements.length];
		
		for(int i = 0; i < elements.length; i++) {
			rowHeights[i] = 0;
			rowWeights[i] = 0.0;
		}
		
		// Set GridBagLayout to elementPanel
		elementScrollPanel.revalidate();
		elementScrollPanel.repaint();
		elementScrollPanel.getViewport().setViewPosition(previousPosition);
		GridBagLayout gbl_elementPanel = new GridBagLayout();
		gbl_elementPanel.columnWidths = columnWidths;
		gbl_elementPanel.rowHeights = rowHeights;
		gbl_elementPanel.columnWeights = columnWeights;
		gbl_elementPanel.rowWeights = rowWeights;
		elementPanel.setLayout(gbl_elementPanel);
		
		// Setup Creative Tab icon Map
		iconImages = new HashMap<Object, Icon>();
		creativeTabIconComboBox.removeAllItems();
		
		// Remake buttons
		for(int i = 0; i < elements.length; i++) {
			
			final int currentIndex = i;
			
			int currentGridY = GRID_ORIGIN_Y + i;
			
			// Create element button
			Button ELEMENT_BUTTON = new Button(
					elements[i].getName(), "Edits " + elements[i].getName(),
					0, GridBagConstraints.HORIZONTAL, 0, currentGridY, 1, 1,
					ElementGenerator.getFont(), new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							editElement(currentIndex);
							ElementGenerator.changeMenu("basic");
							ElementGenerator.logger.log("MainPanel", "Editing " + elements[currentIndex].getName());
						}
					}
			);
			JButton elementButton = ELEMENT_BUTTON.getButton();
			elementButton.setBackground(new Color(120, 120, 120));
			elementButton.setForeground(new Color(0, 0, 0));
			elementPanel.add(elementButton, ELEMENT_BUTTON.getConstraints());
			
			// Create delete button
			Button DELETE_BUTTON = new Button(
					"X", "Deletes " + elements[i].getName(),
					0, GridBagConstraints.HORIZONTAL, 1, currentGridY, 1, 1,
					ElementGenerator.getFont(), new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							removeElement(currentIndex);
							elementPanel.invalidate();
							elementPanel.validate();
							elementPanel.repaint();
							ElementGenerator.logger.log("MainPanel", "Deleting " + elements[currentIndex].getName());
						}
					}
			);
			JButton deleteButton = DELETE_BUTTON.getButton();
			deleteButton.setBackground(new Color(120, 120, 120));
			deleteButton.setForeground(new Color(0, 0, 0));
			elementPanel.add(deleteButton, DELETE_BUTTON.getConstraints());
			
			// Set labels to image icons
			String[] labelToolTips = new String[] {
					"nether ore", "surface ore", "end ore", "block", "gem",
					"pickaxe", "axe", "hoe", "shovel", "sword",
					"helmet", "chestplate", "leggings", "boots"
			};
			ImageIcon[] imageIcons = getTexturePreviews(elements[i], 32);
			
			for(int j = 0; j < imageIcons.length; j++) {
				
				// Image Icon Label
				Label IMAGE_ICON_LABEL = new Label(
						"", "The preview of the " + elements[i].getName() + "\'s " + labelToolTips[j] + " texture.", imageIcons[j],
						0, GridBagConstraints.HORIZONTAL, j + 2, currentGridY, 1, 1,
						ElementGenerator.getFont()
				);
				elementPanel.add(IMAGE_ICON_LABEL.getLabel(), IMAGE_ICON_LABEL.getConstraints());
				
			}
			
			// Add the gem texture of the current element to the Icon Map
			// - Note: the gem texture is placed at the 4th index of the resulting ImageIcon array
			ImageIcon gemTexture = imageIcons[4];
			iconImages.put(elements[i].getName() + " " + elements[i].getGem(), gemTexture);
			creativeTabIconComboBox.addItem(elements[i].getName() + " " + elements[i].getGem());
			
			// Up Arrow
			Button UP_ARROW_BUTTON = new Button(
					"", "Moves " + elements[i].getName() + " up.",
					0, GridBagConstraints.HORIZONTAL, 16, currentGridY, 1, 1,
					ElementGenerator.getFont(), new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							// Swap element above and this element
							Element temp = elements[currentIndex];
							elements[currentIndex] = elements[currentIndex - 1];
							elements[currentIndex - 1] = temp;
							
							// Change creative tab icon combobox position if currentIndex == creative tab selected index
							if(creativeTabIndex == currentIndex)
								creativeTabIndex = currentIndex - 1;
							
							// Keep track of the current position for the scrollbar
							previousPosition = elementScrollPanel.getViewport().getViewPosition();
							updateElements();
							elementScrollPanel.getViewport().setViewPosition(previousPosition);
							
							ElementGenerator.logger.log("MainPanel", "Moving Element Up: " + elements[currentIndex].getName());
						}
					}
			);
			JButton upArrowButton = UP_ARROW_BUTTON.getButton();
			upArrowButton.setIcon(MenuBar.upArrowImage);
			if(i == 0)
				upArrowButton.setEnabled(false);
			elementPanel.add(upArrowButton, UP_ARROW_BUTTON.getConstraints());
			
			// Down Arrow
			Button DOWN_ARROW_BUTTON = new Button(
					"", "Moves " + elements[i].getName() + " down.",
					0, GridBagConstraints.HORIZONTAL, 17, currentGridY, 1, 1,
					ElementGenerator.getFont(), new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							// Swap element below and this element
							Element temp = elements[currentIndex];
							elements[currentIndex] = elements[currentIndex + 1];
							elements[currentIndex + 1] = temp;
							
							// Change creative tab icon combobox position if currentIndex == creative tab selected index
							if(creativeTabIndex == currentIndex)
								creativeTabIndex = currentIndex + 1;
							
							// Keep track of the current position for the scroll bar
							previousPosition = elementScrollPanel.getViewport().getViewPosition();
							updateElements();
							elementScrollPanel.getViewport().setViewPosition(previousPosition);
							
							ElementGenerator.logger.log("MainPanel", "Moving Element Down: " + elements[currentIndex].getName());
						}
					}
			);
			JButton downArrowButton = DOWN_ARROW_BUTTON.getButton();
			downArrowButton.setIcon(MenuBar.downArrowImage);
			if(currentGridY == elements.length - 1)
				downArrowButton.setEnabled(false);
			elementPanel.add(downArrowButton, DOWN_ARROW_BUTTON.getConstraints());

		}
		
		// Setup ComboBox renderer
		creativeTabIconComboBox.setRenderer(new CreativeTabIconRenderer(iconImages));
		try {
			creativeTabIconComboBox.setSelectedIndex(creativeTabIndex);
		} catch (Exception e) {
			
		}
		
		// If there are no elements
		if(elements.length == 0) {
			// Add an info message saying there's no elements
			//    tell user to either create one under the "Element" menu or
			//    open a mod file under the "Mod" menu
			Label NO_ELEMENTS_LABEL = new Label(
					"There are no elements in the mod.", "", null,
					0, 0, 0, 0, 1, 1,
					ElementGenerator.getTitleFont()
			);
			elementPanel.add(NO_ELEMENTS_LABEL.getLabel(), NO_ELEMENTS_LABEL.getConstraints());
			
			Label ADD_ELEMENT_LABEL = new Label(
					"Add a new element under the \"Element\" tab or", "", null,
					0, 0, 0, 1, 1, 1,
					ElementGenerator.getTitleFont()
			);
			elementPanel.add(ADD_ELEMENT_LABEL.getLabel(), ADD_ELEMENT_LABEL.getConstraints());
			
			Label ADD_MOD_LABEL = new Label(
					"Open an existing mod under the \"Mod\" tab.", "", null,
					0, 0, 0, 2, 1, 1,
					ElementGenerator.getTitleFont()
			);
			elementPanel.add(ADD_MOD_LABEL.getLabel(), ADD_MOD_LABEL.getConstraints());
		}
		
	}
	
	/**
	 * Resets all the JComponents
	 */
	public static void reset() {
		
		ElementGenerator.logger.log("MainPanel", "Resetting elements");
		
		// Reset Mod Information
		modNameTextField.setText("");
		authorsTextArea.setText("");
		modVersionTextField.setText("");
		minecraftVersionComboBox.setSelectedIndex(0);
		creditsTextField.setText("");
		urlTextField.setText("");
		updateUrlTextField.setText("");
		descriptionTextArea.setText("");
		
		// Update Elements
		elements = new Element[0];
		updateElements();
		
	}

	/**
	 * Loads a mod from a file by asking the user for the file
	 */
	public static void loadMod() {
		
		ElementGenerator.logger.log("MainPanel", "Asking user for mod ." + ElementGenerator.ELEMENT_FILE + " file");
		
		// Create File chooser
		FileChooser modChooser = new FileChooser(
				"Choose a mod JSON file", ModUtils.MOD_SAVE_DIRECTORY,
				new FileNameExtensionFilter(
						"Mod Files", ElementGenerator.ELEMENT_FILE
				)
		);
		int action = modChooser.getFileChooser().showOpenDialog(ElementGenerator.mainGUI);
		
		// File was selected
		if(action == JFileChooser.APPROVE_OPTION) {
			
			ElementGenerator.logger.log("MainPanel", "User chose a file");
			
			// Try to load mod
			try {
				// Load mod from file
				JSONObject jsonMod = (JSONObject) JSON.read(modChooser.getFileChooser().getSelectedFile() + "");
				Mod customMod = ModUtils.loadMod(jsonMod);
				
				// Update mod
				ElementGenerator.logger.log("MainPanel", "Mod file was successfully loaded");
				
				update(customMod);
				updateElements();
			} catch (Exception e) {
				ElementGenerator.logger.log("MainPanel", "Mod file was not loaded.\n    Error: " + e.getMessage(), Logger.ERROR);
			}
		}
		
		// File choosing was canceled
		else
			ElementGenerator.logger.log("MainPanel", "User canceled loading a file", Logger.WARNING);
	}
	
	/**
	 * Saves the mod to a json file
	 * @param showMessage whether or not to show the success message
	 */
	public static void saveMod(boolean showMessage) {
		
		ElementGenerator.logger.log("MainPanel", "Saving mod");

		// Load mod specifications
		String modName = modNameTextField.getText();
		Authors authorNames = new Authors(authorsTextArea.getText().split("\n"));
		String modVersion = modVersionTextField.getText();
		int minecraftVersion = minecraftVersionComboBox.getSelectedIndex();
		int creativeTabIconIndex = creativeTabIconComboBox.getSelectedIndex();
		String credits = creditsTextField.getText();
		String url = urlTextField.getText();
		String updateUrl = updateUrlTextField.getText();
		String description = descriptionTextArea.getText();
		
		// Create local customized mod
		Mod customMod = new Mod(
				modName, authorNames,
				modVersion, minecraftVersionObjects[minecraftVersion],
				description, credits,
				url, updateUrl,
				elements
		);
		customMod.setCreativeTabIconIndex(creativeTabIconIndex);
		
		// Save mod
		ModUtils.saveMod(customMod);

	}
	
	/**
	 * Saves the mod to a json file
	 */
	public static void saveMod() {
		saveMod(true);
	}
	
	/**
	 * Generates the Mod specified
	 */
	public static void generateMod() {
		
		ElementGenerator.logger.log("MainPanel", "Generating mod");
		
		// Load mod specifications
		String modName = modNameTextField.getText();
		Authors authorNames = new Authors(authorsTextArea.getText().split("\n"));
		String modVersion = modVersionTextField.getText();
		int minecraftVersion = minecraftVersionComboBox.getSelectedIndex();
		int creativeTabIconIndex = creativeTabIconComboBox.getSelectedIndex();
		String credits = creditsTextField.getText();
		String url = urlTextField.getText();
		String updateUrl = updateUrlTextField.getText();
		String description = descriptionTextArea.getText();
		
		// Create local customized mod
		Mod customMod = new Mod(
				modName, authorNames,
				modVersion, minecraftVersionObjects[minecraftVersion],
				description, credits,
				url, updateUrl,
				elements
		);
		customMod.setCreativeTabIconIndex(creativeTabIconIndex);
		
		// Remove old mod directory
		File modDirectory = new File(
				String.format(
						ModUtils.MOD_DIRECTORY,
						customMod.getName(), customMod.getMinecraftVersion().getVersion()
				)
		);

		// Remove old mod directory
		FileUtils.deleteQuietly(modDirectory);
		
		// Generate Mod
		ModUtils.generateMod(customMod);
		
		// Save Mod
		ModUtils.saveMod(customMod, false);
		
	}
	
	/**
	 * Asks the user to select a JSON file and loads an element from it
	 */
	public static void loadElement() {
		
		ElementGenerator.logger.log("MainPanel", "Asking user for an element ." + ElementGenerator.ELEMENT_FILE + " file");
		
		// Create File chooser
		FileChooser elementChooser = new FileChooser(
				"Choose element JSON file", ModUtils.ELEMENT_SAVE_DIRECTORY,
				new FileNameExtensionFilter(
						"Element File", ElementGenerator.ELEMENT_FILE
				)
		);
		int action = elementChooser.getFileChooser().showOpenDialog(ElementGenerator.mainGUI);
		
		// File was selected
		if(action == JFileChooser.APPROVE_OPTION) {
			// Try to load element
			try {
				ElementGenerator.logger.log("MainPanel", "Attempting to load element file.", Logger.ATTEMPT);
				
				// Load element
				JSONObject jsonElement = (JSONObject) JSON.read(elementChooser.getFileChooser().getSelectedFile() + "");
				Element customElement = ModUtils.loadElement(jsonElement, true);
				
				// Only add to array if element is not null
				ElementGenerator.logger.log("MainPanel", "Successfully loaded element file", Logger.SUCCESS);
				
				if(customElement != null)
					elements = addElement(elements, customElement);
				
				updateElements();
				
			} catch (Exception e) {
				ElementGenerator.logger.log("MainPanel", "Failed to load element file.\n    Error: " + e.getMessage(), Logger.ERROR);
				
				// Element load failed, tell user what happened
				new Message(JOptionPane.ERROR_MESSAGE,
						"Failed to load element.",
						e.getMessage()
				).display("Failed");
			}
		}
		
		// File choosing was canceled
		else
			ElementGenerator.logger.log("MainPanel", "Element json file selection was canceled", Logger.WARNING);
	}
	/**
	 * Sets all the JComponents to the element information at index
	 * @param index the index of the element to set
	 */
	public static void editElement(int index) {
		
		// Set all the information for the elements
		BasicPanel.update(elements[index]);
		GenerationPanel.update(elements[index]);
		ToolPanel.update(elements[index]);
		
		// Update the previews
		BasicPanel.updateTexturePreviews();
		
		// Set INDEX to index (used for updating an element rather than adding a new element in #addElement)
		INDEX = index;
	}
	
	/**
	 * Adds an element to the mod (or saves an element if index != -1)
	 * @param index the index to add (if -1) or to edit (if ! -1)
	 */
	public static void addElement(int index) {
		
		// Load element specifications
		String elementName;
		int gem;
		boolean dropsGem;
		int leastQuantity;
		int mostQuantity;
		ColorData colorData;
		ToolData toolData;
		ArmorData armorData;
		GenerationData generationData;
		BlockData oreData;
		BlockData blockData;
		
		elementName = BasicPanel.elementNameTextField.getText();
		
		switch(String.valueOf(BasicPanel.gemTypeComboBox.getSelectedItem())) {
			case "Rock":
				gem = Element.ROCK;
				break;
			case "Ingot":
				gem = Element.INGOT;
				break;
			case "Nugget":
				gem = Element.NUGGET;
				break;
			case "Dust":
				gem = Element.DUST;
				break;
			case "Gem":
				gem = Element.GEM;
				break;
			case "Crystal":
				gem = Element.CRYSTAL;
				break;
			case "Shard":
				gem = Element.SHARD;
				break;
			default:
				gem = new int[] {Element.ROCK, Element.GEM, Element.INGOT, Element.NUGGET, Element.CRYSTAL, Element.SHARD, Element.DUST}[(int) Math.random()*(6 - 0 + 1)];
				break;
		}
		
		dropsGem = !BasicPanel.dropsItselfCheckBox.isSelected();
		leastQuantity = (int)BasicPanel.lowestDropsSpinner.getValue();
		mostQuantity = (int)BasicPanel.highestDropsSpinner.getValue();
		
		colorData = new ColorData(
				BasicPanel.darkestColorButton.getBackground(),
				BasicPanel.lightestColorButton.getBackground()
		);
		
		toolData = new ToolData(
				new Tool(
						Tool.PICKAXE,
						(int) ToolPanel.pickaxeHarvestLevelSpinner.getValue(),
						(int) ToolPanel.pickaxeEfficiencySpinner.getValue(),
						Tool.DEFAULT_ENCHANTABILITY,
						(int) ToolPanel.pickaxeAttackDamageSpinner.getValue(),
						(int) ToolPanel.pickaxeUsesSpinner.getValue()
				),
				new Tool(
						Tool.AXE,
						(int) ToolPanel.axeHarvestLevelSpinner.getValue(),
						(int) ToolPanel.axeEfficiencySpinner.getValue(),
						Tool.DEFAULT_ENCHANTABILITY,
						(int) ToolPanel.axeAttackDamageSpinner.getValue(),
						(int) ToolPanel.axeUsesSpinner.getValue()
				),
				new Tool(
						Tool.HOE,
						(int) ToolPanel.hoeHarvestLevelSpinner.getValue(),
						(int) ToolPanel.hoeEfficiencySpinner.getValue(),
						Tool.DEFAULT_ENCHANTABILITY,
						(int) ToolPanel.hoeAttackDamageSpinner.getValue(),
						(int) ToolPanel.hoeUsesSpinner.getValue()
				),
				new Tool(
						Tool.SHOVEL,
						(int) ToolPanel.shovelHarvestLevelSpinner.getValue(),
						(int) ToolPanel.shovelEfficiencySpinner.getValue(),
						Tool.DEFAULT_ENCHANTABILITY,
						(int) ToolPanel.shovelAttackDamageSpinner.getValue(),
						(int) ToolPanel.shovelUsesSpinner.getValue()
				),
				new Tool(
						Tool.SWORD,
						(int) ToolPanel.swordHarvestLevelSpinner.getValue(),
						(int) ToolPanel.swordEfficiencySpinner.getValue(),
						Tool.DEFAULT_ENCHANTABILITY,
						(int) ToolPanel.swordAttackDamageSpinner.getValue(),
						(int) ToolPanel.swordUsesSpinner.getValue()
				)
		);
		
		armorData = new ArmorData(
				(int) ToolPanel.maxDamageSpinner.getValue(),
				ArmorData.DEFAULT_ENCHANTABILITY,
				((double) ToolPanel.toughnessSpinner.getValue()) / 10.0,
				new Armor(
						Armor.HELMET,
						(int) ToolPanel.helmetHealthSpinner.getValue(),
						String.valueOf(ToolPanel.helmetEffectComboBox.getSelectedItem()),
						(int) ToolPanel.helmetLevelSpinner.getValue(),
						(int) ToolPanel.helmetDurationSpinner.getValue()
				),
				new Armor(
						Armor.CHESTPLATE,
						(int) ToolPanel.chestplateHealthSpinner.getValue(),
						String.valueOf(ToolPanel.chestplateEffectComboBox.getSelectedItem()),
						(int) ToolPanel.chestplateLevelSpinner.getValue(),
						(int) ToolPanel.chestplateDurationSpinner.getValue()
				),
				new Armor(
						Armor.LEGGINGS,
						(int) ToolPanel.leggingsHealthSpinner.getValue(),
						String.valueOf(ToolPanel.leggingsEffectComboBox.getSelectedItem()),
						(int) ToolPanel.leggingsLevelSpinner.getValue(),
						(int) ToolPanel.leggingsDurationSpinner.getValue()
				),
				new Armor(
						Armor.BOOTS,
						(int) ToolPanel.bootsHealthSpinner.getValue(),
						String.valueOf(ToolPanel.bootsEffectComboBox.getSelectedItem()),
						(int) ToolPanel.bootsLevelSpinner.getValue(),
						(int) ToolPanel.bootsDurationSpinner.getValue()
				)
		);
		
		generationData = new GenerationData(
				new Generation(
						GenerationPanel.generateInNetherCheckBox.isSelected(),
						(int) GenerationPanel.lowestNetherGenerationSpinner.getValue(),
						(int) GenerationPanel.highestNetherGenerationSpinner.getValue(),
						(int) GenerationPanel.netherRaritySlider.getValue(),
						(int) GenerationPanel.netherChunkRaritySlider.getValue()
				),
				new Generation(
						GenerationPanel.generateOnSurfaceCheckBox.isSelected(),
						(int) GenerationPanel.lowestSurfaceGenerationSpinner.getValue(),
						(int) GenerationPanel.highestSurfaceGenerationSpinner.getValue(),
						(int) GenerationPanel.surfaceRaritySlider.getValue(),
						(int) GenerationPanel.surfaceChunkRaritySlider.getValue()
				),
				new Generation(
						GenerationPanel.generateInEndCheckBox.isSelected(),
						(int) GenerationPanel.lowestEndGenerationSpinner.getValue(),
						(int) GenerationPanel.highestEndGenerationSpinner.getValue(),
						(int) GenerationPanel.endRaritySlider.getValue(),
						(int) GenerationPanel.endChunkRaritySlider.getValue()
				)
		);
		
		oreData = new BlockData(
				((double) GenerationPanel.oreHardnessSpinner.getValue()) / 10.0,
				(double) GenerationPanel.oreResistanceSpinner.getValue(),
				((double) GenerationPanel.oreLightLevelSpinner.getValue()) / 100.0,
				(int) GenerationPanel.oreLightOpacitySpinner.getValue(),
				GenerationPanel.oreHarvestLevelSlider.getValue()
		);
		
		blockData = new BlockData(
				((double) GenerationPanel.blockHardnessSpinner.getValue()) / 10.0,
				(double) GenerationPanel.blockResistanceSpinner.getValue(),
				((double) GenerationPanel.blockLightLevelSpinner.getValue()) / 100.0,
				(int) GenerationPanel.blockLightOpacitySpinner.getValue(),
				GenerationPanel.blockHarvestLevelSlider.getValue()
		);
		
		Element newElement = new Element(
				elementName,
				gem,
				dropsGem,
				colorData,
				generationData,
				toolData,
				armorData,
				oreData,
				blockData
		);
		
		newElement.setLeastQuantity(leastQuantity);
		newElement.setMostQuantity(mostQuantity);
		
		// Add element to array if index is -1
		if(index == -1)
			elements = addElement(elements, newElement);
		else
			elements[index] = newElement;
		
		INDEX = -1;
		updateElements();
		
	}
	
	/**
	 * Adds a new Element to the array specified
	 * @param oldArray the array to add on to
	 * @param newElement what to add to the array
	 * @return Element[]
	 */
	private static Element[] addElement(Element[] oldArray, Element newElement) {
		Element[] newArray = new Element[oldArray.length + 1];
		for(int i = 0; i < oldArray.length; i++)
			newArray[i] = oldArray[i];
		newArray[newArray.length - 1] = newElement;
		return newArray;
	}
	
	/**
	 * Deletes the element at index [index]
	 * @param index index of the element to delete
	 */
	private static void removeElement(int index) {
		Element[] newArray = new Element[0];
		for(int i = 0; i < elements.length; i++) {
			if(i != index)
				newArray = addElement(newArray, elements[i]);
		}
		elements = newArray.clone();
		
		// Update elements
		updateElements();
	}
	
	/**
	 * Returns an array of ImageIcon's for use with the texture previews
	 * @param element the element to get the colors from
	 * @param resolution the size of the previews
	 * @return ImageIcon[]
	 */
	private static ImageIcon[] getTexturePreviews(Element element, int resolution) {
		
		Color[] gradient = null;
		String currentGem = "";
		if(element != null) {
			currentGem = element.getGem();
			gradient = ElementUtils.getGradient(
					element.getColorData().getDarkestColor(), 
					element.getColorData().getLightestColor()
			);
		}
		else {
			currentGem = BasicPanel.gemTypeComboBox.getSelectedItem().toString().toUpperCase();
			gradient = ElementUtils.getGradient(
					BasicPanel.darkestColorButton.getBackground(), 
					BasicPanel.lightestColorButton.getForeground()
			);
		}
		Color[] replacementColors = new Color[] {
				ModUtils.TEMPLATE_COLORS[0], gradient[0],
				ModUtils.TEMPLATE_COLORS[1], gradient[1],
				ModUtils.TEMPLATE_COLORS[2], gradient[2],
				ModUtils.TEMPLATE_COLORS[3], gradient[3],
				ModUtils.TEMPLATE_COLORS[4], gradient[4],
				ModUtils.TEMPLATE_COLORS[5], gradient[5]
		};
		
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
	
	/**
	 * An Icon Renderer class to use inside the Creative Tab Icon Combobox
	 * @author Jonah Pierce (Fellow Hashbrown)
	 *
	 */
	private static class CreativeTabIconRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		
		// Instance Fields
		private Map<Object, Icon> icons;
		
		/**
		 * Creates a new Renderer for a ComboBox
		 * @param icons the array of icons to use
		 */
		public CreativeTabIconRenderer(Map<Object, Icon> icons) {
			this.icons = icons;
		}
		
		@Override
		@SuppressWarnings("rawtypes")
		/**
		 * Returns the Component used for a Renderer in a JComboBox
		 */
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			
			// Create JLabel
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			// Get selected Icon
			Icon icon = icons.get(value);
			
			// Set icon to label and return
			label.setIcon(icon);
			
			return label;
		}
	
	}

}
