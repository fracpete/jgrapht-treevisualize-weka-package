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
 * SimpleEdge.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.gui.visualize.plugins.jgrapht;

import org.jgrapht.graph.DefaultEdge;

/**
 * Edge class.
 */
public class SimpleEdge
  extends DefaultEdge {

  private static final long serialVersionUID = -3953505380251686524L;

  protected String m_Label;

  public SimpleEdge(String label) {
    super();
    m_Label = label;
  }

  @Override
  public String toString()
  {
    if ((m_Label == null) || m_Label.isEmpty())
      return "(" + getSource() + " : " + getTarget() + ")";
    else
      return m_Label;
  }
}
