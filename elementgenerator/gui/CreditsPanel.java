package elementgenerator.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import elementgenerator.gui.customcomponents.Button;
import elementgenerator.gui.customcomponents.Label;
import elementgenerator.gui.customcomponents.Panel;
import elementgenerator.gui.customcomponents.Separator;
import elementgenerator.util.Logger;

/**
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class CreditsPanel {
	
	// Main Panel
	public static JPanel panel;
	
	/**
	 * Sets up the components needed for the credits panel
	 */
	public static void setup() {
		
		ElementGenerator.logger.log("CreditsPanel", "Setting up the credits panel");
		
		// Setup panel
		Panel CREDITS_PANEL = new Panel(
				16, 2,
				0, 0, 0, 0, 1, 1
		);
		panel = CREDITS_PANEL.getPanel();
		
		// Developer GitHub Link (Fellow Hashbrown)
		Label DEVELOPER_LABEL = new Label(
				"Developed By", "", null,
				0, GridBagConstraints.HORIZONTAL, 0, 0, 1, 1,
				ElementGenerator.getFont()
		);
		Button DEVELOPER_BUTTON = new Button(
				"Fellow Hashbrown", "https://www.github.com/FellowHashbrown/",
				0, GridBagConstraints.HORIZONTAL, 1, 0, 1, 1,
				ElementGenerator.getFont(ElementGenerator.UNDERLINE), new HyperLink("https://www.github.com/FellowHashbrown")
		);
		DEVELOPER_BUTTON.getButton().setBorderPainted(false);
		DEVELOPER_BUTTON.getButton().setOpaque(false);
		DEVELOPER_BUTTON.getButton().setBackground(Color.WHITE);
		DEVELOPER_BUTTON.getButton().setForeground(Color.BLUE);
		panel.add(DEVELOPER_LABEL.getLabel(), DEVELOPER_LABEL.getConstraints());
		panel.add(DEVELOPER_BUTTON.getButton(), DEVELOPER_BUTTON.getConstraints());
		
		// Separator
		Separator SEPARATOR_1 = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 1, 2, 1
		);
		panel.add(SEPARATOR_1.getSeparator(), SEPARATOR_1.getConstraints());
		
		/* Alphabetize Icon Authors
		 * --> Anton Saputro
		 * --> Dave Gandy
		 * --> Plainicons
		 * --> Smashicons
		 */
		Label CREDITS_LABEL = new Label(
				"Credits", "", null,
				GridBagConstraints.CENTER, 0, 0, 2, 2, 1,
				ElementGenerator.getTitleFont()
		);
		
		// Separator
		Separator SEPARATOR_2 = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 3, 2, 1
		);
		panel.add(SEPARATOR_2.getSeparator(), SEPARATOR_2.getConstraints());
		
		Button ANTON_SAPUTRO_BUTTON = new Button(
				"Anton Saputro", "https://www.flaticon.com/authors/anton-saputro/",
				0, GridBagConstraints.HORIZONTAL, 0, 4, 1, 1,
				ElementGenerator.getFont(ElementGenerator.UNDERLINE), new HyperLink("https://www.flaticon.com/authors/anton-saputro")
		);
		ANTON_SAPUTRO_BUTTON.getButton().setBorderPainted(false);
		ANTON_SAPUTRO_BUTTON.getButton().setOpaque(false);
		ANTON_SAPUTRO_BUTTON.getButton().setBackground(Color.WHITE);
		ANTON_SAPUTRO_BUTTON.getButton().setForeground(Color.BLUE);
		Label ADD_ELEMENT_ICON_LABEL = new Label(
				"Add Element Icon", "", MenuBar.addElementImage,
				0, GridBagConstraints.HORIZONTAL, 1, 4, 1, 1,
				ElementGenerator.getFont()
		);
		Label ADD_ELEMENT_FROM_FILE_ICON_LABEL = new Label(
				"Add Element From File Icon", "", MenuBar.addElementFromFileImage,
				0, GridBagConstraints.HORIZONTAL, 1, 5, 1, 1,
				ElementGenerator.getFont()
		);
		Label GENERATE_MOD_ICON_LABEL = new Label(
				"Generate Mod Icon", "", MenuBar.generateModImage,
				0, GridBagConstraints.HORIZONTAL, 1, 6, 1, 1,
				ElementGenerator.getFont()
		);
		Label SAVE_FILE_ICON_LABEL = new Label(
				"Save File Icon", "", MenuBar.saveModImage,
				0, GridBagConstraints.HORIZONTAL, 1, 7, 1, 1,
				ElementGenerator.getFont()
		);
		
		// Separator
		Separator SEPARATOR_3 = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.HORIZONTAL, 0, 8, 2, 1
		);
		panel.add(SEPARATOR_3.getSeparator(), SEPARATOR_3.getConstraints());
		
		Button DAVE_GANDY_BUTTON = new Button(
				"Dave Gandy", "https://www.flaticon.com/authors/dave-gandy/",
				0, GridBagConstraints.HORIZONTAL, 0, 9, 1, 1,
				ElementGenerator.getFont(ElementGenerator.UNDERLINE), new HyperLink("https://www.flaticon.com/authors/dave-gandy/")
		);
		DAVE_GANDY_BUTTON.getButton().setBorderPainted(false);
		DAVE_GANDY_BUTTON.getButton().setOpaque(false);
		DAVE_GANDY_BUTTON.getButton().setBackground(Color.WHITE);
		DAVE_GANDY_BUTTON.getButton().setForeground(Color.BLUE);
		Label OPEN_FILE_ICON_LABEL = new Label(
				"Open File Icon", "", MenuBar.openModImage,
				0, GridBagConstraints.HORIZONTAL, 1, 9, 1, 1,
				ElementGenerator.getFont()
		);
		Label UP_ARROW_ICON_LABEL = new Label(
				"Up Arrow Icon", "", MenuBar.upArrowImage,
				0, GridBagConstraints.HORIZONTAL, 1, 10, 1, 1,
				ElementGenerator.getFont()
		);
		Label DOWN_ARROW_ICON_LABEL = new Label(
				"Down Arrow Icon", "", MenuBar.downArrowImage,
				0, GridBagConstraints.HORIZONTAL, 1, 11, 1, 1,
				ElementGenerator.getFont()
		);
		
		// Separator
		Separator SEPARATOR_4 = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 12, 2, 1
		);
		panel.add(SEPARATOR_4.getSeparator(), SEPARATOR_4.getConstraints());
		
		Button PLAIN_ICONS_BUTTON = new Button(
				"Plainicon", "https://www.flaticon.com/authors/plainicon",
				0, GridBagConstraints.HORIZONTAL, 0, 13, 1, 1,
				ElementGenerator.getFont(ElementGenerator.UNDERLINE), new HyperLink("https://www.flaticon.com/authors/plainicon/")
		);
		PLAIN_ICONS_BUTTON.getButton().setBorderPainted(false);
		PLAIN_ICONS_BUTTON.getButton().setOpaque(false);
		PLAIN_ICONS_BUTTON.getButton().setBackground(Color.WHITE);
		PLAIN_ICONS_BUTTON.getButton().setForeground(Color.BLUE);
		Label CREDITS_ICON_LABEL = new Label(
				"Credits Icon", "", MenuBar.creditsImage,
				0, GridBagConstraints.HORIZONTAL, 1, 13, 1, 1,
				ElementGenerator.getFont()
		);
		
		// Separator
		Separator SEPARATOR_5 = new Separator(
				SwingConstants.HORIZONTAL,
				0, GridBagConstraints.BOTH, 0, 14, 2, 1
		);
		panel.add(SEPARATOR_5.getSeparator(), SEPARATOR_5.getConstraints());
		
		Button SMASH_ICONS_BUTTON = new Button(
				"Smashicons", "https://www.flaticon.com/authors/smashicons",
				0, GridBagConstraints.HORIZONTAL, 0, 15, 1, 1,
				ElementGenerator.getFont(ElementGenerator.UNDERLINE), new HyperLink("https://www.flaticon.com/authors/smashicons/")
		);
		SMASH_ICONS_BUTTON.getButton().setBorderPainted(false);
		SMASH_ICONS_BUTTON.getButton().setOpaque(false);
		SMASH_ICONS_BUTTON.getButton().setBackground(Color.WHITE);
		SMASH_ICONS_BUTTON.getButton().setForeground(Color.BLUE);
		Label OPTIONS_ICON_LABEL = new Label(
				"Options Icon", "", MenuBar.setThemeImage,
				0, GridBagConstraints.HORIZONTAL, 1, 15, 1, 1,
				ElementGenerator.getFont()
		);
		
		// Add Components to panel
		panel.add(CREDITS_LABEL.getLabel(), CREDITS_LABEL.getConstraints());
		
		panel.add(ANTON_SAPUTRO_BUTTON.getButton(), ANTON_SAPUTRO_BUTTON.getConstraints());
		panel.add(ADD_ELEMENT_ICON_LABEL.getLabel(), ADD_ELEMENT_ICON_LABEL.getConstraints());
		panel.add(ADD_ELEMENT_FROM_FILE_ICON_LABEL.getLabel(), ADD_ELEMENT_FROM_FILE_ICON_LABEL.getConstraints());
		panel.add(GENERATE_MOD_ICON_LABEL.getLabel(), GENERATE_MOD_ICON_LABEL.getConstraints());
		panel.add(SAVE_FILE_ICON_LABEL.getLabel(), SAVE_FILE_ICON_LABEL.getConstraints());
		
		panel.add(DAVE_GANDY_BUTTON.getButton(), DAVE_GANDY_BUTTON.getConstraints());
		panel.add(OPEN_FILE_ICON_LABEL.getLabel(), OPEN_FILE_ICON_LABEL.getConstraints());
		panel.add(UP_ARROW_ICON_LABEL.getLabel(), UP_ARROW_ICON_LABEL.getConstraints());
		panel.add(DOWN_ARROW_ICON_LABEL.getLabel(), DOWN_ARROW_ICON_LABEL.getConstraints());
		
		panel.add(PLAIN_ICONS_BUTTON.getButton(), PLAIN_ICONS_BUTTON.getConstraints());
		panel.add(CREDITS_ICON_LABEL.getLabel(), CREDITS_ICON_LABEL.getConstraints());
		
		panel.add(SMASH_ICONS_BUTTON.getButton(), SMASH_ICONS_BUTTON.getConstraints());
		panel.add(OPTIONS_ICON_LABEL.getLabel(), OPTIONS_ICON_LABEL.getConstraints());
	
	}
	
	/**
	 * A HyperLink class for the credits menu
	 * @author Jonah Pierce (Fellow Hashbrown)
	 *
	 */
	private static class HyperLink implements ActionListener {
		
		// Instance Fields
		private String link;
		
		/**
		 * Creates a new HyperLink action listener
		 * @param link the link to open when a button is clicked
		 */
		public HyperLink(String link) {
			this.link = link;
		}

		/**
		 * Called when a button is pressed
		 * @param e
		 */
		public void actionPerformed(ActionEvent actionEvent) {
			boolean showError = false;
			// Only try to open link if Desktop is supported
			if(Desktop.isDesktopSupported()) {
				// Try to open link
				try {
					ElementGenerator.logger.log("CreditsPanel", "Attempting to open URL: " + link, Logger.ATTEMPT);
					
					Desktop.getDesktop().browse(new URI(link));
					
					ElementGenerator.logger.log("CreditsPanel", "Successfully opened URL: " + link, Logger.SUCCESS);
					
				} catch (Exception exception) {
					ElementGenerator.logger.log("CreditsPanel", "Failed to open URL: " + link + "\n    Error: " + exception.getMessage(), Logger.FAILED);
					showError = true;
				}
			}
			else {
				showError = true;
			}
			
			if(showError) {
				JLabel failedMessage = new JLabel("Failed to open URL");
				failedMessage.setFont(ElementGenerator.getFont());
				JOptionPane.showMessageDialog(ElementGenerator.mainGUI, failedMessage, "Failed", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

}
