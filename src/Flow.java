import java.io.IOException;
import java.util.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.flow.FlowAlgorithm;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

public class Flow {

    public static void main(String args[]) throws IOException, GraphParseException {
        System.setProperty("org.graphstream.ui", "swing");

        Graph G = new DefaultGraph("Graphe"); // regarder différence avec SingleGraph
        G.read("nom du fichier");

        // G.setAttribute("ui.stylesheet", "url(file://resource/stylesheet.css");
        G.display(); // G.display(false);

        FlowAlgorithm algorithm = new FordFulkerson();

        algorithm.init(G, "s", "t"); // On initialise en précisent bien s et t
        algorithm.setCapacityAttribute("fluxMax"); // On initialise les flux maximal sur chaque arc
        algorithm.compute();

        System.out.println("Le flot maximal de ce graphe est : " + algorithm.getMaximumFlow());
    }
}
