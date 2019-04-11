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
import org.jgrapht.io.Attribute;

import java.util.Map;

/**
 * Edge class.
 */
public class SimpleEdge
  extends DefaultEdge {

  private static final long serialVersionUID = -3953505380251686524L;

  protected SimpleVertex m_From;

  protected SimpleVertex m_To;

  protected String m_Label;

  protected Map<String, Attribute> m_Attributes;

  public SimpleEdge(SimpleVertex from, SimpleVertex to, String label, Map<String, Attribute> attributes) {
    super();
    m_From = from;
    m_To = to;
    m_Label = label;
    m_Attributes = attributes;
  }

  public SimpleVertex getFrom() {
    return m_From;
  }

  public SimpleVertex getTo() {
    return m_To;
  }

  public String getLabel() {
    return m_Label;
  }

  public Map<String, Attribute> getAttributes() {
    return m_Attributes;
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
