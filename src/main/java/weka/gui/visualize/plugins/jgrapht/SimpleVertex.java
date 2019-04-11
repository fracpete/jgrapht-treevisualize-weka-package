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
 * SimpleVertex.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.gui.visualize.plugins.jgrapht;

import org.jgrapht.io.Attribute;

import java.io.Serializable;
import java.util.Map;

/**
 * Vertex class.
 */
public class SimpleVertex
  implements Serializable {

  private static final long serialVersionUID = -5274928994354192155L;

  protected String m_ID;

  protected Map<String, Attribute> m_Attributes;

  public SimpleVertex(String id, Map<String, Attribute> attributes) {
    super();
    m_ID = id;
    m_Attributes = attributes;
  }

  public void setAttributes(Map<String, Attribute> attributes) {
    m_Attributes = attributes;
  }

  @Override
  public String toString() {
    if ((m_Attributes != null) && m_Attributes.containsKey("label"))
      return m_Attributes.get("label").toString();
    else
      return m_ID;
  }
}
