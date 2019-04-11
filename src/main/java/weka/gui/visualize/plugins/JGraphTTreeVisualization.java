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
 * JGraphTTreeVisualization.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.gui.visualize.plugins;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Utility class.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class JGraphTTreeVisualization {

  /** whether to output some debugging information. */
  protected boolean DEBUG = false;

  /** the singleton instance. */
  protected static JGraphTTreeVisualization m_Singleton;

  /**
   * To avoid instantiation of singleton.
   */
  protected JGraphTTreeVisualization() {
    super();
  }

  /**
   * Saves the dotty string to a file.
   *
   * @param dotty	the graph in dotty notation to turn into image
   * @param filename 	the file to save the dotty data to
   * @return		null if successful, otherwise error message
   */
  public String saveDotty(String dotty, String filename) {
    String		result;
    FileWriter fwriter;
    BufferedWriter bwriter;

    result = null;

    if (DEBUG)
      System.out.println("Saving dotty to: " + filename);

    fwriter = null;
    bwriter = null;
    try {
      fwriter = new FileWriter(filename);
      bwriter = new BufferedWriter(fwriter);
      bwriter.write(dotty);
      bwriter.newLine();
    }
    catch (Exception e) {
      result = "Failed to write dotty string to " + filename;
      System.err.println(result);
      e.printStackTrace();
      result += "\n" + e;
    }
    finally {
      if (bwriter != null) {
	try {
	  bwriter.flush();
	  bwriter.close();
	}
	catch (Exception e){
	  // ignored
	}
      }
      if (fwriter != null) {
	try {
	  fwriter.flush();
	  fwriter.close();
	}
	catch (Exception e){
	  // ignored
	}
      }
    }

    return result;
  }

  /**
   * Return the singleton.
   *
   * @return		the singleton
   */
  public static synchronized JGraphTTreeVisualization getSingleton() {
    if (m_Singleton == null)
      m_Singleton = new JGraphTTreeVisualization();
    return m_Singleton;
  }
}
