/**
 * 
 */
package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * A class to make JMenuItem creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class MenuItem {
	
	// Instance Fields
	private JMenuItem menuitem;
	
	/**
	 * Creates a new MenuItem object
	 * @param text the text to be displayed on the item
	 * @param toolTipText the tool tip text to appear on the item
	 * @param icon the default state icon (when item is not pressed)
	 * @param selectedIcon the selected state icon (when item is pressed)
	 * @param font the font to be used for the item
	 * @param targetMenu the JMenu object to add the item to
	 * @param actionListener an ActionListener to add
	 */
	public MenuItem(String text, String toolTipText, ImageIcon icon, ImageIcon selectedIcon,
			Font font, JMenu targetMenu, ActionListener actionListener) {
		
		// Setup Menu Item
		menuitem = new JMenuItem(text);
		menuitem.setToolTipText(toolTipText);
		menuitem.setIcon(icon);
		menuitem.setSelectedIcon(selectedIcon);
		menuitem.setFont(font);
		menuitem.addActionListener(actionListener);
		
		// Add menu item to menu
		targetMenu.add(menuitem);
		
	}
	
	/**
	 * Creates a new MenuItem object
	 * @param component the already created component to add
	 * @param targetMenu the JMenu object to add the item to
	 */
	public MenuItem(JComponent component, JMenu targetMenu) {
		
		// Add component to targetMenu
		targetMenu.add(component);
		
	}
	
	/**
	 * Returns the JMenuItem object
	 * @return JMenuItem
	 */
	public JMenuItem getMenuItem() {
		return menuitem;
	}

}
