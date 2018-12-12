package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * A Class to make JCheckBox creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ComboBox {
	
	// Instance Fields
	private JComboBox<Object> combobox;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a ComboBox object
	 * @param content the content of the ComboBox
	 * @param toolTipText the tool tip text of the ComboBox
	 * @param anchor how to anchor the ComboBox on the panel
	 * @param fill how to fill the ComboBox on the panel
	 * @param gridx the x location of the ComboBox on the panel
	 * @param gridy the y location of the ComboBox on the panel
	 * @param gridw the width of the ComboBox on the panel
	 * @param gridh the height of the ComboBox on the panel
	 * @param font the Font for the ComboBox
	 * @param actionListener the ActionListener connected to the ComboBox
	 */
	public ComboBox(Object[] content, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ActionListener actionListener) {
		
		// Setup combobox
		combobox = new JComboBox<Object>(content);
		combobox.setFont(font);
		combobox.setToolTipText(toolTipText);
		combobox.addActionListener(actionListener);
		
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
	 * Creates a ComboBox object
	 * @param content the content of the ComboBox
	 * @param toolTipText the tool tip text of the ComboBox
	 * @param anchor how to anchor the ComboBox on the panel
	 * @param fill how to fill the ComboBox on the panel
	 * @param gridx the x location of the ComboBox on the panel
	 * @param gridy the y location of the ComboBox on the panel
	 * @param gridw the width of the ComboBox on the panel
	 * @param gridh the height of the ComboBox on the panel
	 * @param font the Font for the ComboBox
	 * @param actionListener the ActionListener connected to the ComboBox
	 */
	public ComboBox(String[] content, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ActionListener actionListener) {
		
		this(new Object[0], toolTipText, anchor, fill, gridx, gridy, gridw, gridh, font, actionListener);
		DefaultComboBoxModel<Object> temp = new DefaultComboBoxModel<Object>();
		for(String c: content)
			temp.addElement(c);
		combobox.setModel(temp);
	}
	/**
	 * Creates a ComboBox object
	 * @param content the content of the ComboBox
	 * @param toolTipText the tool tip text of the ComboBox
	 * @param anchor how to anchor the ComboBox on the panel
	 * @param fill how to fill the ComboBox on the panel
	 * @param gridx the x location of the ComboBox on the panel
	 * @param gridy the y location of the ComboBox on the panel
	 * @param gridw the width of the ComboBox on the panel
	 * @param gridh the height of the ComboBox on the panel
	 * @param font the Font for the ComboBox
	 * @param actionListener the ActionListener connected to the ComboBox
	 */
	public ComboBox(int[] content, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ActionListener actionListener) {
		
		this(new Object[0], toolTipText, anchor, fill, gridx, gridy, gridw, gridh, font, actionListener);
		DefaultComboBoxModel<Object> temp = new DefaultComboBoxModel<Object>();
		for(int c: content)
			temp.addElement(c);
		combobox.setModel(temp);
		
	}
	
	/**
	 * Returns the JComboBox object
	 * @return JComboBox
	 */
	public JComboBox<Object> getComboBox() {
		return combobox;
	}
	
	/**
	 * Returns the constraints for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
}
