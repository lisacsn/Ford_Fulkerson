
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import java.util.stream.Stream;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;

public class FordFulkerson extends FlowAlgorithmBase {

    @Override
    public void compute() {
        Node source = this.flowGraph.getNode(sourceId);
        Node puit = this.flowGraph.getNode(sinkId);
        Path chaine;
        this.maximumFlow = 0;
        int nbAretes = this.flowGraph.getEdgeCount();

        for (int i = 0; i < nbAretes; i++) {
            Node noeud1 = this.flowGraph.getEdge(i).getSourceNode();
            Node noeud2 = this.flowGraph.getEdge(i).getTargetNode();
            this.setFlow(noeud1, noeud2, 0.0);
        }

        loadCapacitiesFromAttribute(); // valuation des arcs

        do {
            chaine = chaineAmeliorante(source, puit);

            if (chaine != null) {
                double delta = CalculerFlot(chaine);
                augmenterFlotAvants(delta, chaine);
            }

        } while (chaine != null);
    }

    public Path chaineAmeliorante(Node noeud, Node t) {
        // Si source = puit càd pas de graphe alors on retourne chaine vide
        if (noeud.equals(t))
            return new Path();
        else {
            return successeurNonSature(noeud).map(aretes -> {
                Path chaine = chaineAmeliorante(aretes.getTargetNode(), t);
                if ((aretes.getTargetNode() != t) || (chaine != null))
                    chaine.add(aretes);
                return chaine;
            }).filter(aretes -> aretes != null).findFirst().orElse(null);
        }
    }

    public Stream<Edge> successeurNonSature(Node sommet) {
        Stream<Edge> l = sommet.leavingEdges().filter(s -> this.capacities[s.getIndex()] > this.flows[s.getIndex()]);

        return l;
    }

    public double CalculerFlot(Path chaine) {
        Iterator<Edge> edgeIt = chaine.getEdgeIterator();
        double delta = this.capacities[edgeIt.next().getIndex()] - this.flows[edgeIt.next().getIndex()];

        while (edgeIt.hasNext()) {
            double temp = this.capacities[edgeIt.next().getIndex()] - this.flows[edgeIt.next().getIndex()];
            if (temp < delta)
                delta = temp;
        }
        return delta;
    }

    public void augmenterFlotAvants(double delta, Path chaine) {
        double newFlow;

        for (int i = 0; i < chaine.getEdgeCount(); i++) {
            Node noeud1 = this.flowGraph.getEdge(i).getSourceNode();
            Node noeud2 = this.flowGraph.getEdge(i).getTargetNode();
            newFlow = delta + this.flows[i];
            this.setFlow(noeud1, noeud2, newFlow);
        }
    }

    /********************************************************************************/

    public Node obtenirSource(Graph G) {
        int i = 0;
        Node n = G.getNode(0);

        while ((n.getInDegree() != 0) && (i <= G.getNodeCount())) {
            n = G.getNode(i);
            i++;
        }
        return n;
    }

    public Node obtenirPuit(Graph G) {
        int i = 0;
        Node n = G.getNode(0);

        while ((n.getOutDegree() != 0) && (i <= G.getNodeCount())) {
            n = G.getNode(i);
            i++;
        }
        return n;
    }

    public List<Node> obtenirSuccesseurs(Node sommet) {

        List<Node> list = new ArrayList<Node>();

        for (int i = 0; i <= sommet.getDegree(); i++) {
            if (sommet.getEdge(i).getSourceNode() == sommet) {
                list.add(sommet.getEdge(i).getTargetNode());
            }
        }
        return list;
    }

    public List<Node> obtenirPredecesseurs(Node sommet) {

        List<Node> list = new ArrayList<Node>();

        for (int i = 0; i <= sommet.getDegree(); i++) {
            if (sommet.getEdge(i).getTargetNode() == sommet) {
                list.add(sommet.getEdge(i).getSourceNode());
            }
        }
        return list;
    }

}