package FordFulkerson;
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.stream.Collectors;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;

public class FordFulkerson extends FlowAlgorithmBase {

    @Override
    public void compute() {
        Node source = this.flowGraph.getNode(sourceId);
        Node puit = this.flowGraph.getNode(sinkId);
        List<Edge> chaine;
        this.maximumFlow = 0;
        int nbAretes = this.flowGraph.getEdgeCount();

        for (int i = 0; i < nbAretes; i++) {
            Node noeud1 = this.flowGraph.getEdge(i).getSourceNode();
            Node noeud2 = this.flowGraph.getEdge(i).getTargetNode();
            this.setFlow(noeud1, noeud2, 0.0);
        }

        loadCapacitiesFromAttribute(); // valuation des arcs
        this.flowGraph.edges().forEach(e -> {
        
            double cap = this.capacities[e.getIndex()];
            e.setAttribute("label",  cap);
        });
        do {
            chaine = chaineAmeliorante(source, puit);

            if (chaine != null) {
                System.out.println("Chaine améliorante : " + chaine);
                double delta = calculerFlot(chaine);
                augmenterFlotAvants(delta, chaine);
                this.maximumFlow += delta;
            }

        } while (chaine != null);
    }

    public List<Edge> chaineAmeliorante(Node noeud, Node t) {
        if (noeud.equals(t))
            return new LinkedList<Edge>();
        else {
            for (Edge arete : successeurNonSature(noeud)) {
                List<Edge> ret = chaineAmeliorante(arete.getOpposite(noeud), t);
                if (ret != null) {
                    ret.add(arete);
                    return ret;
                }
            }
        }
        return null;
    }

    public List<Edge> successeurNonSature(Node sommet) {
        return sommet
                .leavingEdges().filter(e -> this.getCapacity(e.getSourceNode(), e.getTargetNode()) > this
                        .getFlow(e.getSourceNode(), e.getTargetNode()))
                .filter(s -> s != null).collect(Collectors.toList());
    }

    public double calculerFlot(List<Edge> chaine) {
        double delta = Double.MAX_VALUE;

        for (Edge i : chaine) {
            double temp = this.getCapacity(i.getSourceNode(), i.getTargetNode())
                    - this.getFlow(i.getSourceNode(), i.getTargetNode());

            if (temp < delta) {
                delta = temp;
            }
        }
        return delta;
    }

    public void augmenterFlotAvants(double delta, List<Edge> chaine) {
        double newFlow;

        for (Edge i : chaine) {
            Node noeud1 = i.getSourceNode();
            Node noeud2 = i.getTargetNode();
            newFlow = delta + this.getFlow(noeud1, noeud2);
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
