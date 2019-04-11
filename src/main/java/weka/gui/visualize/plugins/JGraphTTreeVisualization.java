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

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.io.ComponentUpdater;
import org.jgrapht.io.DOTImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GraphImporter;
import org.jgrapht.io.GraphMLExporter;
import org.jgrapht.io.VertexProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleComponentUpdater;
import weka.gui.visualize.plugins.jgrapht.SimpleEdge;
import weka.gui.visualize.plugins.jgrapht.SimpleEdgeAttributeProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleEdgeIDProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleEdgeLabelProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleEdgeProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleVertex;
import weka.gui.visualize.plugins.jgrapht.SimpleVertexAttributeProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleVertexIDProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleVertexLabelProvider;
import weka.gui.visualize.plugins.jgrapht.SimpleVertexProvider;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

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
   * Saves the string to a file.
   *
   * @param type	the type of graph to save (used for messages)
   * @param content	the graph to save
   * @param filename 	the file to save the graph to
   * @return		null if successful, otherwise error message
   */
  protected String save(String type, String content, String filename) {
    String		result;
    FileWriter fwriter;
    BufferedWriter bwriter;

    result = null;

    if (DEBUG)
      System.out.println("Saving " + type + " to: " + filename);

    fwriter = null;
    bwriter = null;
    try {
      fwriter = new FileWriter(filename);
      bwriter = new BufferedWriter(fwriter);
      bwriter.write(content);
      bwriter.newLine();
    }
    catch (Exception e) {
      result = "Failed to write " + type + " string to " + filename;
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
   * Saves the dotty string to a file.
   *
   * @param dotty	the graph in dotty notation to turn into image
   * @param filename 	the file to save the dotty data to
   * @return		null if successful, otherwise error message
   */
  public String saveDotty(String dotty, String filename) {
    return save("dotty", dotty, filename);
  }

  /**
   * Converts the dotty string and saves it as graphml file.
   *
   * @param dotty	the graph in dotty notation to turn into graphml
   * @param filename 	the file to save the graphml data to
   * @return		null if successful, otherwise error message
   */
  public String saveGraphml(String dotty, String filename) {
    DirectedPseudograph<SimpleVertex, SimpleEdge> 	graph;
    GraphMLExporter<SimpleVertex, SimpleEdge>		exporter;
    StringWriter					writer;

    try {
      writer = new StringWriter();
      graph = importDotty(dotty);
      exporter = new GraphMLExporter<SimpleVertex, SimpleEdge>(
        new SimpleVertexIDProvider(),
	new SimpleVertexLabelProvider(),
	new SimpleVertexAttributeProvider(),
	new SimpleEdgeIDProvider(),
	new SimpleEdgeLabelProvider(),
	new SimpleEdgeAttributeProvider());
      exporter.exportGraph(graph, writer);
      return save("graphml", writer.toString(), filename);
    }
    catch (Exception e) {
      return "Failed to export dotty to GraphML: " + e;
    }
  }

  /**
   * Imports the dotty graph.
   *
   * @param dotty	the dotty string notation
   * @return		the graph
   * @throws Exception	if parsing fails
   */
  public DirectedPseudograph<SimpleVertex, SimpleEdge> importDotty(String dotty) throws Exception {
    DirectedPseudograph<SimpleVertex, SimpleEdge> 	result;
    VertexProvider<SimpleVertex> 			vp;
    EdgeProvider<SimpleVertex, SimpleEdge> 		ep;
    ComponentUpdater<SimpleVertex> 			cu;
    GraphImporter<SimpleVertex, SimpleEdge> 		importer;

    // based on:
    // https://github.com/jgrapht/jgrapht/blob/master/jgrapht-io/src/test/java/org/jgrapht/io/DOTImporter2Test.java

    result   = new DirectedPseudograph<SimpleVertex,SimpleEdge>(SimpleEdge.class);
    vp       = new SimpleVertexProvider();
    ep       = new SimpleEdgeProvider();
    cu       = new SimpleComponentUpdater();
    importer = new DOTImporter<SimpleVertex,SimpleEdge>(vp, ep, cu);
    importer.importGraph(result, new StringReader(dotty));

    return result;
  }

  /**
   * Displays the dotty graph.
   *
   * @param dotty	the graph
   * @return		null if successful, otherwise error message
   */
  public String displayGraph(String dotty) {
    DirectedPseudograph<SimpleVertex, SimpleEdge> 	graph;
    JGraphXAdapter<SimpleVertex, SimpleEdge> 		jgxAdapter;
    mxGraphComponent 					component;
    JFrame 						frame;
    mxCompactTreeLayout 				layout;

    // based on:
    // https://github.com/jgrapht/jgrapht/blob/master/jgrapht-demo/src/main/java/org/jgrapht/demo/JGraphXAdapterDemo.java

    try {
      graph = importDotty(dotty);
      jgxAdapter = new JGraphXAdapter<SimpleVertex,SimpleEdge>(graph);
      jgxAdapter.setCellsEditable(false);
      jgxAdapter.setLabelsVisible(true);
      component = new mxGraphComponent(jgxAdapter);
      component.getConnectionHandler().setEnabled(false);
      component.setPanning(true);
      component.setConnectable(false);
      //component.setEnabled(false);
      component.setDragEnabled(true);
      component.setZoomFactor(5.0);
      component.setZoomPolicy(mxGraphComponent.ZOOM_POLICY_PAGE);
      frame = new JFrame();
      frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(component);
      frame.setSize(new Dimension(800, 800));
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      layout = new mxCompactTreeLayout(jgxAdapter);
      layout.setHorizontal(false);
      layout.execute(jgxAdapter.getDefaultParent());
      return null;
    }
    catch (Exception e) {
      return "Failed to display graph: " + e;
    }
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
