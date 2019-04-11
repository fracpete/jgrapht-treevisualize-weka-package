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
 * SimpleVertexProvider.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.gui.visualize.plugins.jgrapht;

import org.jgrapht.io.Attribute;
import org.jgrapht.io.VertexProvider;

import java.util.Map;

/**
 * The vertex provider.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SimpleVertexProvider implements VertexProvider<SimpleVertex> {

  @Override
  public SimpleVertex buildVertex(String id, Map<String, Attribute> attributes) {
    return new SimpleVertex(id, attributes);
  }
}
