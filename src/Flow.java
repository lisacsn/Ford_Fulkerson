import java.io.IOException;
import java.util.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.flow.FlowAlgorithm;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.GraphParseException;

public class Flow {

    public static void main(String args[]) throws IOException, GraphParseException {
        System.setProperty("org.graphstream.ui", "swing");

        Graph G = new DefaultGraph("Graphe");
        G.read("src/graph2.dgs");
        G.display();

        FlowAlgorithm algorithm = new FordFulkerson();

        algorithm.init(G, "S", "T"); // On initialise en précisant bien s et t
        algorithm.setCapacityAttribute("fluxMax"); // On initialise les flux maximal sur chaque arc
        algorithm.compute();

        System.out.println("Le flot maximal de ce graphe est : " + algorithm.getMaximumFlow());
    }
}
