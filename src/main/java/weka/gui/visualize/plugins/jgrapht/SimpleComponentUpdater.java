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
 * SimpleComponentUpdater.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.gui.visualize.plugins.jgrapht;

import org.jgrapht.io.Attribute;
import org.jgrapht.io.ComponentUpdater;

import java.util.Map;

/**
 * For updating SimpleVertex.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SimpleComponentUpdater implements ComponentUpdater<SimpleVertex> {

  @Override
  public void update(SimpleVertex component, Map<String, Attribute> attributes) {
    component.setAttributes(attributes);
  }
}
