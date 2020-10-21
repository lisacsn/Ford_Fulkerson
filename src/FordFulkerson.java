
import java.util.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import org.graphstream.graph.implementations.SingleGraph;

public class FordFulkerson extends FlowAlgorithmBase {

    @Override
    public void compute() {

    }

    @Override
    public void init(Graph G, String s, String t) {

    }

    @Override
    public void setCapacityAttribute(String fluxMax) {

    }

    public static Node obtenirSource(Graph G) {
        int i = 0;
        Node n = G.getNode(0);

        while ((n.getInDegree() != 0) && (i <= G.getNodeCount())) {
            n = G.getNode(i);
            i++;
        }
        return n;
    }

    public static Node obtenirPuit(Graph G) {
        int i = 0;
        Node n = G.getNode(0);

        while ((n.getOutDegree() != 0) && (i <= G.getNodeCount())) {
            n = G.getNode(i);
            i++;
        }
        return n;
    }

    public static List<Node> obtenirSuccesseurs(Node sommet) {

        List<Node> list = new ArrayList<Node>();

        for (int i = 0; i <= sommet.getDegree(); i++) {
            if (sommet.getEdge(i).getSourceNode() == sommet) {
                list.add(sommet.getEdge(i).getTargetNode());
            }
        }
        return list;
    }

    public static List<Node> obtenirPredecesseurs(Node sommet) {

        List<Node> list = new ArrayList<Node>();

        for (int i = 0; i <= sommet.getDegree(); i++) {
            if (sommet.getEdge(i).getTargetNode() == sommet) {
                list.add(sommet.getEdge(i).getSourceNode());
            }
        }
        return list;
    }

    public static Node successeurNonSature(Node s, int[][] tableau) {

        List<Node> successeurs = obtenirSuccesseurs(s);
        for (Node succ : successeurs) {
        }

        return n;
    }

    public static Path chaineAmeliorante(Graph G, int[][] tableau) {

        List<Node> sommetsEmpruntes = new ArrayList<Node>();
        Path chaine = new Path();

        Node s = obtenirSource(G);
        Node successeur = successeurNonSature(s, tableau);

        return chaine;
    }

    public static int CalculerFlot(Path chaine) {

        int delta = 0;

        return delta;
    }

    public static int augmenterFlotAvants(int F, int[][] tableau, int delta, Path chaine) {

        return F;
    }

    public static void diminuerFlotsArrieres(int[][] tableau, int delta, Path chaine) {

    }

    public static int algoFF(Graph G) {

        int nbNode = G.getNodeCount();
        int[][] tableauFlux = new int[nbNode][nbNode];
        int F = 0;
        int delta = 0; // l'augmentation

        Path chaine;

        do {
            chaine = chaineAmeliorante(G, tableauFlux);

            if (chaine.size() != 0) {
                delta = CalculerFlot(chaine);
                F = augmenterFlotAvants(F, tableauFlux, delta, chaine);
                diminuerFlotsArrieres(tableauFlux, delta, chaine);
            }

        } while (chaine.size() != 0);

        return F;
    }
}