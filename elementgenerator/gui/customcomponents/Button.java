package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A class to make JButton creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Button {
	
	// Instance Fields
	private JButton button;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Button object
	 * @param text the text on the button
	 * @param toolTipText the tool tip of the button
	 * @param anchor how to anchor the button on the panel
	 * @param fill how to fill the button on the panel
	 * @param gridx the x location of the button on the panel
	 * @param gridy the y location of the button on the panel
	 * @param gridw the width of the button on the panel
	 * @param gridh the height of the button on the panel
	 * @param font the Font for the button
	 * @param actionListener the ActionListener for the button
	 */
	public Button(String text, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ActionListener actionListener) {
		
		// Setup Button
		button = new JButton(text);
		button.setFont(font);
		button.setToolTipText(toolTipText);
		button.addActionListener(actionListener);
		
		// Setup Constraints
		constraints = new GridBagConstraints();
		if(anchor != 0)
			constraints.anchor = anchor;
		if(fill != 0)
			constraints.fill = fill;
		constraints.gridwidth = gridw;
		constraints.gridheight = gridh;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		
	}
	
	/**
	 * Returns the JButton object
	 * @return JButton
	 */
	public JButton getButton() {
		return button;
	}
	
	/**
	 * Returns the constraints for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Adds a new ActionListener to the object
	 * @param actionListener the ActionListener to add
	 */
	public void addActionListener(ActionListener actionListener) {
		button.addActionListener(actionListener);
	}

}
