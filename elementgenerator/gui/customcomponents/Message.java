package elementgenerator.gui.customcomponents;

import java.awt.GridBagConstraints;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import elementgenerator.gui.ElementGenerator;

/**
 * A class to make messages much easier
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Message {
	
	// Instance Fields
	private JPanel panel;
	private int messageType;
	
	/**
	 * Creates a new Message object
	 * @param messageType the type of message to display
	 * @param messages an array or list of messages to display
	 */
	public Message(int messageType, String... messages) {
		
		// Setup Panel
		Panel PANEL = new Panel(
				messages.length, 1,
				0, 0, 0, 0, 1, 1
		);
		panel = PANEL.getPanel();
		
		// Setup Label
		for(int i = 0; i < messages.length; i++) {
			Label MESSAGE_LABEL = new Label(
					messages[i], "", null,
					GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, i, 1, 1,
					ElementGenerator.getSubTitleFont()
			);
			// Only add label if the message is not empty
			if(! messages[i].isEmpty())
				panel.add(MESSAGE_LABEL.getLabel(), MESSAGE_LABEL.getConstraints());
		}
		
		this.messageType = messageType;
	}
	
	/**
	 * Creates a new Message object
	 * @param messages
	 */
	public Message(String... messages) {
		this(JOptionPane.INFORMATION_MESSAGE, messages);
	}
	
	/**
	 * Creates a new Message object
	 * @param messageType the type of message to display
	 * @param message the message to display
	 */
	public Message(int messageType, String message) {
		this(messageType, new String[] { message });
	}
	
	/**
	 * Creates a new Message object
	 * @param message
	 */
	public Message(String message) {
		this(JOptionPane.INFORMATION_MESSAGE, message);
	}
	
	/**
	 * Displays the message in a JOptionPane dialog box
	 */
	public void display(String title) {
		JOptionPane.showMessageDialog(ElementGenerator.mainGUI, panel, title, messageType);
	}

}
