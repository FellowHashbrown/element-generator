package elementgenerator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import elementgenerator.gui.customcomponents.CheckBox;
import elementgenerator.gui.customcomponents.MenuItem;
import elementgenerator.gui.customcomponents.Message;

/**
 * The Top Menu Bar Class for the Main GUI
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class MenuBar {
	
	// Menu Bar Fields
	public static JMenuBar menubar;
	
	// Mod Menu
	private static JMenu modMenu;
	public static JMenuItem openModMenuItem;
	public static JMenuItem saveModMenuItem;
	public static JMenuItem generateModMenuItem;
	
	public static ImageIcon openModImage;
	public static ImageIcon saveModImage;
	public static ImageIcon generateModImage;
	
	// Element Menu
	private static JMenu elementMenu;
	public static JMenuItem addElementFromFileMenuItem;
	public static JMenuItem addElementMenuItem;
	
	public static ImageIcon addElementFromFileImage;
	public static ImageIcon addElementImage;
	
	// Help Menu
	private static JMenu helpMenu;
	public static JMenuItem setThemeMenuItem;
	public static JCheckBox logToFileMenuItem;
	public static JMenuItem creditsMenuItem;
	
	public static ImageIcon setThemeImage;
	public static ImageIcon creditsImage;
	
	// Other Images
	public static ImageIcon upArrowImage;
	public static ImageIcon downArrowImage;
	
	/**
	 * The icon resolution for the menu bar
	 */
	public static final int ICON_RESOLUTION = 16;
	
	/**
	 * Sets up the menu bar
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("MenuBar", "Setting up menu bar");
		
		setupIcons();
		
		// Setup Menu Bar
		menubar = new JMenuBar();
		
		// Mod Menu
		modMenu = new JMenu("Mod");
		modMenu.setFont(ElementGenerator.getFont());
		menubar.add(modMenu);
		
		// - Open Mod Item
		MenuItem OPEN_MOD = new MenuItem(
				"Open Mod", "Lets you select a mod ELEJSON file to open.", openModImage, null,
				ElementGenerator.getFont(), modMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						MainPanel.loadMod();
						ElementGenerator.logger.log("MenuBar", "Opening Mod File");
					}
				}
		);
		openModMenuItem = OPEN_MOD.getMenuItem();
		
		// - Save Mod Item
		MenuItem SAVE_MOD = new MenuItem(
				"Save Mod", "Lets you save the mod to a predetermined directory.", saveModImage, null,
				ElementGenerator.getFont(), modMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						
						// Make sure the required fields are in the mod before trying to save it
						boolean authorsFilled = MainPanel.authorsTextArea.getText().length() != 0;
						boolean elementsFilled = MainPanel.elements.length != 0;
						boolean modNameFilled = MainPanel.modNameTextField.getText().length() != 0;
						boolean modVersionFilled = MainPanel.modVersionTextField.getText().length() != 0;
						
						// Save the mod if all required fields are there
						if(authorsFilled && elementsFilled && modNameFilled && modVersionFilled) {
							ElementGenerator.logger.log("MenuBar", "Saving Mod File");
							
							// Save mod
							MainPanel.saveMod();
							
							ElementGenerator.logger.log("MenuBar", "Saved Mod File");
						}
						// Tell user what's missing
						else {
							ElementGenerator.logger.log("MenuBar", "Can\'t Save Mod File: Certain Fields Are Missing");
							new Message(JOptionPane.ERROR_MESSAGE, 
									"Failed to save mod.",
									"Errors:",
									(authorsFilled)? "": " - Authors are missing.",
									(elementsFilled)? "": " - There are no elements.",
									(modNameFilled)? "": " - The mod name is missing.",
									(modVersionFilled)? "": " - The mod version is missing."
							).display("Failed");
						}
					}
				}
		);
		saveModMenuItem = SAVE_MOD.getMenuItem();
		
		// - Generate Mod Item
		MenuItem GENERATE_MOD = new MenuItem(
				"Generate Mod", "Generates all the files needed for the mod.", generateModImage, null,
				ElementGenerator.getFont(), modMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						// Change main title so user knows it didn't crash
						ElementGenerator.mainGUI.setTitle("MOD IS GENERATING");
						
						// Make sure the required fields are in the mod before trying to generate it
						boolean authorsFilled = MainPanel.authorsTextArea.getText().length() != 0;
						boolean elementsFilled = MainPanel.elements.length != 0;
						boolean modNameFilled = MainPanel.modNameTextField.getText().length() != 0;
						boolean modVersionFilled = MainPanel.modVersionTextField.getText().length() != 0;
						
						// Generate the mod if all required fields are there
						if(authorsFilled && elementsFilled && modNameFilled && modVersionFilled) {
							ElementGenerator.logger.log("MenuBar", "Generating Mod");
							
							// Generate mod
							MainPanel.generateMod();
							
							ElementGenerator.logger.log("MenuBar", "Generated Mod");
							
						}
						// Tell user what's missing
						else
							new Message(JOptionPane.ERROR_MESSAGE, 
									"Failed to generate mod.",
									"Errors:",
									(authorsFilled)? "": " - Authors are missing.",
									(elementsFilled)? "": " - There are no elements.",
									(modNameFilled)? "": " - The mod name is missing.",
									(modVersionFilled)? "": " - The mod version is missing."
							).display("Failed");
						
						// Change main title back
						ElementGenerator.mainGUI.setTitle("Minecraft Element Generator");
					}
				}
		);
		generateModMenuItem = GENERATE_MOD.getMenuItem();
		
		// Element Menu
		elementMenu = new JMenu("Element");
		elementMenu.setFont(ElementGenerator.getFont());
		menubar.add(elementMenu);
		
		// - Add Element From File Item
		MenuItem ADD_ELEMENT_FROM_FILE = new MenuItem(
				"Add Element From File", "Lets you select an element file to load into the mod.", addElementFromFileImage, null,
				ElementGenerator.getFont(), elementMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						MainPanel.loadElement();
						MainPanel.updateElements();
						
						ElementGenerator.logger.log("MenuBar", "Adding Element From File");
					}
				}
		);
		addElementFromFileMenuItem = ADD_ELEMENT_FROM_FILE.getMenuItem();
		
		// - Add Element Item
		MenuItem ADD_ELEMENT = new MenuItem(
				"Add Element", "Opens up a new element screen to add a new element to the mod.", addElementImage, null,
				ElementGenerator.getFont(), elementMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						BasicPanel.reset();
						GenerationPanel.reset();
						ToolPanel.reset();
						
						ElementGenerator.changeMenu("basic");
						BasicPanel.updateTexturePreviews();
						
						ElementGenerator.logger.log("MenuBar", "Adding Element Manually");
					}
				}
		);
		addElementMenuItem = ADD_ELEMENT.getMenuItem();
		
		// Help Menu
		helpMenu = new JMenu("Help");
		helpMenu.setFont(ElementGenerator.getFont());
		menubar.add(helpMenu);
		
		// - Set Theme Item
		MenuItem SET_THEME = new MenuItem(
				"Set Theme", "Lets you select how the window looks", setThemeImage, null,
				ElementGenerator.getFont(), helpMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						
						// Create a temporary panel for the JOptionPane
						JOptionPane.showMessageDialog(ElementGenerator.mainGUI, ElementGenerator.lookAndFeelComboBox, "Select a theme.", JOptionPane.INFORMATION_MESSAGE);
						MainPanel.updateElements();
						
						ElementGenerator.logger.log("MenuBar", "Setting Theme For The Window To: " + ElementGenerator.lookAndFeelComboBox.getSelectedItem());
					}
				}
		);
		setThemeMenuItem = SET_THEME.getMenuItem();
		
		// - Log To File Item
		CheckBox LOG_TO_FILE_CHECKBOX = new CheckBox(
				"Log to file?", "Whether or not to save the output logs to a log file.", 
				0, 0, 0, 0, 1, 1,
				ElementGenerator.getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						
						// Get boolean value of checkbox and update logger
						boolean loggerActive = logToFileMenuItem.isSelected();
						ElementGenerator.logger.setFileLog(loggerActive);
						
						ElementGenerator.logger.log("MenuBar", "Logging To File To: " + logToFileMenuItem.isSelected());
					}
				}
		);
		logToFileMenuItem = LOG_TO_FILE_CHECKBOX.getCheckBox();
		logToFileMenuItem.setSelected(true);
		@SuppressWarnings("unused")
		MenuItem LOG_TO_FILE = new MenuItem(logToFileMenuItem, helpMenu);
		
		// - Credits Item
		MenuItem CREDITS = new MenuItem(
				"Credits", "Opens up the credits screen.", creditsImage, null,
				ElementGenerator.getFont(), helpMenu, new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						JOptionPane.showMessageDialog(
								ElementGenerator.mainGUI,
								CreditsPanel.panel,
								"Credits", 
								JOptionPane.INFORMATION_MESSAGE
						);
						
						ElementGenerator.logger.log("MenuBar", "Opening Credits Panel");
					}
				}
		);
		creditsMenuItem = CREDITS.getMenuItem();
		
		// Setup Credits menu
		CreditsPanel.setup();
		
	}
	
	/**
	 * Sets up the icons for the menu bar
	 */
	private static void setupIcons() {
		
		ElementGenerator.logger.log("MenuBar", "Setting up icons");
		
		// Open Mod Image
		openModImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/openFile.png"));
		
		// Save Mod Image
		saveModImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/saveFile.png"));
		
		// Generate Mod Image
		generateModImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/generateMod.png"));
		
		// Add Element Image
		addElementImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/addElement.png"));
		
		// Add Element From File Image
		addElementFromFileImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/addElementFromFile.png"));
		
		// Set Theme Image
		setThemeImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/setTheme.png"));
		
		// Credits Image
		creditsImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/credits.png"));
		
		// Up Arrow Image
		upArrowImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/upArrow.png"));
		
		// Down Arrow Image
		downArrowImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/downArrow.png"));
		
	}
	
}
