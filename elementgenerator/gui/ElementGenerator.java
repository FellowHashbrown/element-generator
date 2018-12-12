package elementgenerator.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import elementgenerator.gui.customcomponents.ComboBox;
import elementgenerator.util.Logger;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;

/**
 * The Main GUI Class for the Element Generator
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ElementGenerator {
	
	// File Names
	public static final String ELEMENT_FILE = "element";
	
	// Font Fields
	protected static final String PRIMARY_FONT = "Georgia";
	
	protected static final int SMALL_FONT_SIZE = 12;
	protected static final int MEDIUM_FONT_SIZE = 15;
	protected static final int LARGE_FONT_SIZE = 18;
	
	protected static final int BOLD = Font.BOLD;
	protected static final int ITALIC = Font.ITALIC;
	protected static final int PLAIN = Font.PLAIN;
	protected static final int UNDERLINE = 3; 
	
	public static Logger logger = new Logger(true, true);
	
	/**
	 * An Array of the supported look and feels
	 */
	public static String[] lookAndFeels = new String[] {
			"Metal",    "Nimbus",   "Motif",
			"Windows",  "Classic",  "Acryl",
			"Aluminum", "Aero",     "HiFi",
			"Luna",     "Noire",    "Mint",
			"Fast",     "Graphite", "McWin",
			"Texture",  "Bernstein"
	};
	private static LookAndFeel[] lookAndFeelObjects = new LookAndFeel[] {
			new MetalLookAndFeel(),     new NimbusLookAndFeel(),         new MotifLookAndFeel(),
			new WindowsLookAndFeel(),   new WindowsClassicLookAndFeel(), new AcrylLookAndFeel(),
			new AluminiumLookAndFeel(), new AeroLookAndFeel(),           new HiFiLookAndFeel(),
			new LunaLookAndFeel(),      new NoireLookAndFeel(),          new MintLookAndFeel(),
			new FastLookAndFeel(),      new GraphiteLookAndFeel(),       new McWinLookAndFeel(),
			new TextureLookAndFeel(),   new BernsteinLookAndFeel()
	};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox  lookAndFeelComboBox = new JComboBox(lookAndFeels);
	
	// Frame Fields
	
	/**
	 * The resolution of the display monitor used to center the application on the screen
	 */
	private static Dimension monitorResolution = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * The global JFrame object
	 */
	public static JFrame mainGUI;
	
	/**
	 * The Main Application Image
	 */
	protected static ImageIcon applicationImage;
	
	/**
	 * The Main Method
	 */
	public static void main(String[] args) {
		
		try {
			
			// Setup Frame
			mainGUI = new JFrame();
			mainGUI.getContentPane().setLayout(new CardLayout(0, 0));
			mainGUI.addWindowListener(new WindowListener() {
				// Activated when the window is opened
				public void windowOpened(WindowEvent windowEvent) {
					
					logger.log("ElementGenerator", "window was opened");
					
					// Get an array of icon sizes
					ArrayList<Image> icons = new ArrayList<Image>();
					String[] iconSizes = {"32x32", "64x64", "512x512"};
					for(String size: iconSizes)
						icons.add(
								new ImageIcon(
										ElementGenerator.class.getResource(
												String.format(
														"/resources/images/application/modGeneratorLogo%s.png",
														size
												)
										)
								).getImage()
						);
					
					// Set Icon Images
					mainGUI.setIconImages(icons);
				}
				// Activated when the window is closing
				public void windowClosing(WindowEvent windowEvent) {
					logger.log("ElementGenerator", "Window was closed");
					
					// Close Logger
					logger.close();
					
					// Exit current runtime
					Runtime runtime = Runtime.getRuntime();
					runtime.exit(0);
					System.exit(0);
				}
				// Activated when the window is minimized
				public void windowIconified(WindowEvent windowEvent) {
					logger.log("ElementGenerator", "Window was minimized");
				}
				// Activated when the window is restored
				public void windowDeiconified(WindowEvent windowEvent) {
					logger.log("ElementGenerator", "Window was restored");
				}
				// Activated when the window has user focus
				public void windowActivated(WindowEvent windowEvent) {
					logger.log("ElementGenerator", "Window has focus");
				}
				// Activated when the window does not have user focus
				public void windowDeactivated(WindowEvent windowEvent) {
					logger.log("ElementGenerator", "Window does not have focus");
				}	
				// Activated when the window is closed
				public void windowClosed(WindowEvent windowEvent) { }
				
			});
			
			// Setup Other Panels
			setup();
			
			// Get preferred size
			Dimension preferred = new Dimension(
					mainGUI.getPreferredSize().width + 250,
					mainGUI.getPreferredSize().height + 100
			);
			
			// Get the centered position for the application
			Point centeredLocation = new Point(
					monitorResolution.width  / 2 - preferred.width  / 2,
					monitorResolution.height / 2 - preferred.height / 2
			);
			
			// Set up the look and feel
			try {
				UIManager.setLookAndFeel(
						lookAndFeelObjects[lookAndFeelComboBox.getSelectedIndex()]
				);
			} catch (Exception e) { }
			
			// Set up the window information
			mainGUI.setTitle("Minecraft Element Generator");
			
			mainGUI.setSize(preferred);
			mainGUI.setLocation(centeredLocation);
			
			mainGUI.setVisible(true);
			mainGUI.setResizable(true);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, t.getClass().getSimpleName() + ": " + t.getMessage());
			throw t;
		}
		
	}
	
	/**
	 * Sets up the panels
	 */
	private static void setup() {
		
		logger.log("ElementGenerator", "Setting up main GUI");
		
		// Setup comboboxes
		ComboBox LOOK_AND_FEEL_COMBOBOX = new ComboBox(
				lookAndFeels, "Lets you select a new window theme.",
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0, 0, 1, 1,
				getFont(), new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						try {
							
							logger.log("ElementGenerator", "Attempting to change look and feel to " + lookAndFeelComboBox.getSelectedItem(), Logger.ATTEMPT);
							UIManager.setLookAndFeel(
									lookAndFeelObjects[lookAndFeelComboBox.getSelectedIndex()]
							);
							logger.log("ElementGenerator", "Successfully changed look and feel to " + lookAndFeelComboBox.getSelectedItem(), Logger.SUCCESS);
							
						} catch (Exception e) { 
							logger.log("ElementGenerator", "Failed to change look and feel to " + lookAndFeelComboBox.getSelectedItem() + ". \n    Error: " + e.getMessage(), Logger.FAILED);
						}
						
						// Update Components
						SwingUtilities.updateComponentTreeUI(ElementGenerator.mainGUI);
					}
				}
		);
		lookAndFeelComboBox = LOOK_AND_FEEL_COMBOBOX.getComboBox();
		
		// Load Application Image
		applicationImage = new ImageIcon(ElementGenerator.class.getResource("/resources/images/application/modGeneratorLogo32x32.png"));
		
		// Setup Panels
		MainPanel.setup();
		BasicPanel.setup();
		GenerationPanel.setup();
		ToolPanel.setup();
		MenuBar.setup();
		
		// Add Panels to GUI
		mainGUI.getContentPane().add(MainPanel.panel, "main");
		mainGUI.getContentPane().add(BasicPanel.panel, "basic");
		mainGUI.getContentPane().add(GenerationPanel.panel, "generation");
		mainGUI.getContentPane().add(ToolPanel.panel, "tools");
		mainGUI.setJMenuBar(MenuBar.menubar);
		
	}

	/**
	 * Changes the menu to the selected item
	 * @param menu the menu to change to
	 */
	public static void changeMenu(String menu) {
		
		logger.log("ElementGenerator", "Changing current menu to " + menu);
		
		// Use switch-case to process menu
		String titleText = "";
		if(menu.equals("main")) {
			MainPanel.INDEX = -1;
			titleText = "";
			// Enabled the menu items
			MenuBar.openModMenuItem.setEnabled(true);
			MenuBar.saveModMenuItem.setEnabled(true);
			MenuBar.generateModMenuItem.setEnabled(true);
			MenuBar.addElementMenuItem.setEnabled(true);
			MenuBar.addElementFromFileMenuItem.setEnabled(true);
		}
		else {
			titleText = (MainPanel.INDEX == -1)? "Add New Element": "Edit " + MainPanel.elements[MainPanel.INDEX].getName();
			if(MainPanel.INDEX != -1) {
				ToolPanel.toolFinishButton.setText("Save \"" + BasicPanel.elementNameTextField.getText() + "\"");
				ToolPanel.toolFinishButton.setToolTipText("Updates \"" + BasicPanel.elementNameTextField.getText() + "\"");
			}
			// Disable the menu items
			// - Note: done to eliminate errors, bugs, or glitches that
			//         may arise from loading a mod in the middle of modifying an element
			MenuBar.openModMenuItem.setEnabled(false);
			MenuBar.saveModMenuItem.setEnabled(false);
			MenuBar.generateModMenuItem.setEnabled(false);
			MenuBar.addElementMenuItem.setEnabled(false);
			MenuBar.addElementFromFileMenuItem.setEnabled(false);
		}
		switch(menu) {
			case "main": 
				titleText += "Minecraft Element Generator";
				break;
			case "basic":
				titleText += " - [Basic Info & Color] |  Generation, Ore & Block  |  Tools & Armor ";
				break;
			case "generation":
				titleText += " -  Basic Info & Color  | [Generation, Ore & Block] |  Tools & Armor ";
				break;
			case "tools":
				titleText += " -  Basic Info & Color  |  Generation, Ore & Block  | [Tools & Armor] ";
				break;
			default:
				titleText += " I don't know how the fuck you got to this screen. Please send the log report to me!";
				break;
		}
		
		// Change menus
		CardLayout currentLayout = (CardLayout) mainGUI.getContentPane().getLayout();
		currentLayout.show(mainGUI.getContentPane(), menu);
		
		// Set the title
		mainGUI.setTitle(titleText);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Returns the title font used specified by the comboboxes
	 * @param style the style of font ({@code PLAIN, ITALIC, BOLD})
	 * @return Font
	 */
	public static Font getTitleFont(int style) {
		if(style == UNDERLINE) {
			Font font = new Font(
					PRIMARY_FONT,
					Font.PLAIN,
					LARGE_FONT_SIZE
			);
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			font = font.deriveFont(attributes);
			return font;
		}
		return new Font(
				PRIMARY_FONT,
				style,
				LARGE_FONT_SIZE	
		);
	}
	
	/**
	 * Returns the title font used specified by the comboboxes
	 * @return Font
	 */
	public static Font getTitleFont() {
		return getTitleFont(BOLD);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Returns the subtitle font used specified by the comboboxes
	 * @param style the style of font ({@code PLAIN, ITALIC, BOLD})
	 * @return Font
	 */
	public static Font getSubTitleFont(int style) {
		if(style == UNDERLINE) {
			Font font = new Font(
					PRIMARY_FONT,
					Font.PLAIN,
					MEDIUM_FONT_SIZE
			);
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			font = font.deriveFont(attributes);
			return font;
		}
		return new Font(
				PRIMARY_FONT,
				style,
				MEDIUM_FONT_SIZE
		);
	}
	
	/**
	 * Returns the subtitle font used specified by the comboboxes
	 * @return Font
	 */
	public static Font getSubTitleFont() {
		return getSubTitleFont(BOLD);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	/**
	 * Returns the normal font used specified by the comboboxes
	 * @param style the style of font ({@code PLAIN, ITALIC, BOLD})
	 * @return Font
	 */
	public static Font getFont(int style) {
		if(style == UNDERLINE) {
			Font font = new Font(
					PRIMARY_FONT,
					Font.PLAIN,
					SMALL_FONT_SIZE
			);
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			font = font.deriveFont(attributes);
			return font;
		}
		return new Font(
				PRIMARY_FONT,
				style,
				SMALL_FONT_SIZE
		);
	}
	
	/**
	 * Returns the normal font used specified by the comboBoxes
	 * @return Font
	 */
	public static Font getFont() {
		return getFont(PLAIN);
	}
	
	/**
	 * Returns a copy of a spinner number model object
	 * @param model the model to copy
	 * @return SpinnerNumberModel
	 */
	protected static SpinnerNumberModel copy(SpinnerNumberModel model) {
		
		try {
			return new SpinnerNumberModel(
					(int)model.getValue(),
					model.getMinimum(),
					model.getMaximum(),
					model.getStepSize()
			);
		} catch (Exception inte) {
			
			try {
				return new SpinnerNumberModel(
						(double)model.getValue(),
						model.getMinimum(),
						model.getMaximum(),
						model.getStepSize()
				);
			} catch (Exception longe) {
				
				try {
					return new SpinnerNumberModel(
							(long)model.getValue(),
							model.getMinimum(),
							model.getMaximum(),
							model.getStepSize()
					);
				} catch (Exception floate) {
					
					return new SpinnerNumberModel(
							(float)model.getValue(),
							model.getMinimum(),
							model.getMaximum(),
							model.getStepSize()
					);
					
				}
				
			}
			
		}
		
	}
	
}
