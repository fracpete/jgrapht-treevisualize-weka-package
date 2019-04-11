/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * JGraphTTreeVisualizationPlugin.java
 * Copyright (C) 2019 University of Waikato, Hamilton, New Zealand
 */
package weka.gui.visualize.plugins;

import weka.gui.ExtensionFileFilter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Displays a graph in dotty format using JGraphT.
 * 
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 */
public class JGraphTTreeVisualizationPlugin
  implements TreeVisualizePlugin {

  /** the filechooser for saving the dotty graph. */
  protected JFileChooser m_FileChooserDotty;

  /**
   * Returns the filechooser to use for saving the dotty graph.
   *
   * @return		the filechooser
   */
  protected JFileChooser getFileChooserDotty() {
    ExtensionFileFilter		filter;

    if (m_FileChooserDotty == null) {
      filter             = new ExtensionFileFilter(
	new String[]{".dotty", ".dot"},
	"GraphViz dot file");
      m_FileChooserDotty = new JFileChooser();
      m_FileChooserDotty.addChoosableFileFilter(filter);
      m_FileChooserDotty.setFileFilter(filter);
      m_FileChooserDotty.setFileSelectionMode(JFileChooser.FILES_ONLY);
      m_FileChooserDotty.setAcceptAllFileFilterUsed(false);
    }

    return m_FileChooserDotty;
  }

  /**
   * Get a JMenu or JMenuItem which contain action listeners
   * that perform the visualization of the tree in GraphViz's dotty format.  
   * Exceptions thrown because of changes in Weka since compilation need to 
   * be caught by the implementer.
   *
   * @see NoClassDefFoundError
   * @see IncompatibleClassChangeError
   *
   * @param dotty 	the tree in dotty format
   * @param name	the name of the item (in the Explorer's history list)
   * @return menuitem 	for opening visualization(s), or null
   *         		to indicate no visualization is applicable for the input
   */
  public JMenuItem getVisualizeMenuItem(final String dotty, final String name) {
    JMenu	result;
    JMenuItem	menuitem;

    result = new JMenu("Visualize tree (JGraphT)");

    menuitem = new JMenuItem("Show graph...");
    menuitem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String msg = JGraphTTreeVisualization.getSingleton().displayGraph(dotty);
	if (msg != null)
	  JOptionPane.showMessageDialog(null, msg, "Error displaying graph", JOptionPane.ERROR_MESSAGE);
      }
    });
    result.add(menuitem);

    menuitem = new JMenuItem("Save graph data...");
    menuitem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	JFileChooser fileChooser = getFileChooserDotty();
	int retVal = fileChooser.showSaveDialog(null);
	if (retVal != JFileChooser.APPROVE_OPTION)
	  return;
	String msg = JGraphTTreeVisualization.getSingleton().saveDotty(dotty, "" + fileChooser.getSelectedFile());
	if (msg != null)
	  JOptionPane.showMessageDialog(null, msg, "Error saving graph", JOptionPane.ERROR_MESSAGE);
      }
    });
    result.add(menuitem);

    return result;
  }
  
  /**
   * Get the minimum version of Weka, inclusive, the class
   * is designed to work with.  eg: <code>3.5.0</code>
   * 
   * @return		the minimum version
   */
  public String getMinVersion() {
    return "3.7.9";
  }
  
  /**
   * Get the maximum version of Weka, exclusive, the class
   * is designed to work with.  eg: <code>3.6.0</code>
   * 
   * @return		the maximum version
   */
  public String getMaxVersion() {
    return "4.0.0";
  }
  
  /**
   * Get the specific version of Weka the class is designed for.
   * eg: <code>3.5.1</code>
   * 
   * @return		the version the plugin was designed for
   */
  public String getDesignVersion() {
    return "3.7.11";
  }
}
