package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

/**
 * A class to make JSpinner creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Spinner {
	
	// Instance Fields
	private JSpinner spinner;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Spinner object
	 * @param spinnerModel the SpinnerNumberModel object to be used (or null)
	 * @param toolTipText the tool tip text
	 * @param anchor how to anchor the JSpinner
	 * @param fill how to fill the JSpinner on the panel
	 * @param gridx the x location of the JSpinner on the panel
	 * @param gridy the y location of the JSpinner on the panel
	 * @param gridw the width of the JSpinner on the panel
	 * @param gridh the height of the JSpinner on the panel
	 * @param font the Font to be used for the JSpinner
	 * @param changeListener the ChangeListener to be added to the Spinner
	 */
	public Spinner(SpinnerNumberModel spinnerModel, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ChangeListener changeListener) {
		
		// Setup Spinner
		spinner = new JSpinner();
		spinner.setFont(font);
		spinner.setToolTipText(toolTipText);
		spinner.setModel(spinnerModel);
		if(changeListener != null)
			spinner.addChangeListener(changeListener);
		
		// Setup constraints
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
	 * Returns the JSpinner object
	 * @return JSpinner
	 */
	public JSpinner getSpinner() {
		return spinner;
	}
	
	/**
	 * Returns the constraints for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Sets the ChangeListener for the spinner
	 * @param changeListener the change listener
	 */
	public void addChangeListener(ChangeListener changeListener) {
		spinner.addChangeListener(changeListener);
	}

}
