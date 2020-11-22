package FordFulkerson;
import java.io.IOException;
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.flow.FlowAlgorithm;
import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.GraphParseException;

public class Flow {

    public static void main(String args[]) throws IOException, GraphParseException {
        System.setProperty("org.graphstream.ui", "swing");

        Graph G = new DefaultGraph("Graphe");
        G.read("ressource/graph2.dgs");
        G.display(false);

        FlowAlgorithm algorithm = new FordFulkerson();


        algorithm.init(G, "S", "T");
        algorithm.setCapacityAttribute("weight");
        algorithm.compute();

        System.out.println("Le flot maximal de ce graphe est : " + algorithm.getMaximumFlow());

    }
}
