package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSlider;

/**
 * A class to make JSlider creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Slider {
	
	// Instance Fields
	private JSlider slider;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Slider object
	 * @param initialValue the value to start the Slider at
	 * @param minimum the minimum value of the Slider
	 * @param maximum the maximum value of the Slider
	 * @param toolTipText the tool tip text for the slider
	 * @param anchor how to anchor the Slider to the panel
	 * @param fill how to fill the Slider to the panel
	 * @param gridx the x location of the Slider on the panel
	 * @param gridy the y location of the Slider on the panel
	 * @param gridw the width of the Slider on the panel
	 * @param gridh the height of the Slider on the panel
	 * @param font the font to use for the Slider
	 */
	public Slider(int initialValue, int minimum, int maximum, String toolTipText,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font) {
		
		// Setup Slider
		slider = new JSlider();
		slider.setToolTipText(toolTipText);
		slider.setValue(initialValue);
		slider.setMinimum(minimum);
		slider.setMaximum(maximum);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setFont(font);
		
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
	 * Returns the JSlider object
	 * @return JSlider
	 */
	public JSlider getSlider() {
		return slider;
	}
	
	/**
	 * Returns the constraints for the panel
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

}
