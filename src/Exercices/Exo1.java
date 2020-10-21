package Exercices;

import org.graphstream.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Exo1 {
    public static void main(String args[]) {
        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = new SingleGraph("Exercice 1");

        graph.addNode("Usine1");
        graph.addNode("s");
        graph.addNode("Usine2");
        graph.addNode("PF1");
        graph.addNode("PF2");
        graph.addNode("PF3");
        graph.addNode("PF4");
        graph.addNode("PF5");
        graph.addNode("Client1");
        graph.addNode("Client2");
        graph.addNode("Client3");
        graph.addNode("t");

        graph.addEdge("sUnise1", "s", "Usine1", true);
        graph.addEdge("sUnise2", "s", "Usine2", true);
        graph.addEdge("Usine1PF1", "Usine1", "PF1", true);
        graph.addEdge("Usine1PF2", "Usine1", "PF2", true);
        graph.addEdge("Usine1PF3", "Usine1", "PF3", true);
        graph.addEdge("Usine2PF2", "Usine2", "PF2", true);
        graph.addEdge("Usine2PF3", "Usine2", "PF3", true);
        graph.addEdge("PF1PF4", "PF1", "PF4", true);
        graph.addEdge("PF1PF2", "PF1", "PF2", true);
        graph.addEdge("PF3PF5", "PF3", "PF5", true);
        graph.addEdge("PF5Client2", "PF5", "Client2", true);
        graph.addEdge("PF5Client3", "PF5", "Client3", true);

        graph.addEdge("PF2Client3", "PF2", "Client3", true);
        graph.addEdge("PF2Client2", "PF2", "Client2", true);
        graph.addEdge("PF4Client2", "PF4", "Client2", true);

        graph.addEdge("Client3t", "Client3", "t", true);
        graph.addEdge("Client2t", "Client2", "t", true);
        graph.addEdge("Client1t", "Client1", "t", true);

        graph.addEdge("PF4Client1", "PF4", "Client1", true);
        graph.addEdge("PF2Client1", "PF2", "Client1", true);

        Node es = graph.getNode("s");
        es.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        es.setAttribute("ui.label", "s");

        Node eU2 = graph.getNode("Usine2");
        eU2.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eU2.setAttribute("ui.label", "Usine2");

        Node eU1 = graph.getNode("Usine1");
        eU1.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eU1.setAttribute("ui.label", "Usine1");

        Node eP3 = graph.getNode("PF3");
        eP3.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eP3.setAttribute("ui.label", "PF3");

        Node eP1 = graph.getNode("PF1");
        eP1.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eP1.setAttribute("ui.label", "PF1");

        Node eP4 = graph.getNode("PF4");
        eP4.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eP4.setAttribute("ui.label", "PF4");

        Node eP2 = graph.getNode("PF2");
        eP2.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eP2.setAttribute("ui.label", "PF2");

        Node eP5 = graph.getNode("PF5");
        eP5.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eP5.setAttribute("ui.label", "PF5");

        Node eC3 = graph.getNode("Client3");
        eC3.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eC3.setAttribute("ui.label", "Client3");

        Node eC2 = graph.getNode("Client2");
        eC2.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eC2.setAttribute("ui.label", "Client2");

        Node eC1 = graph.getNode("Client1");
        eC1.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        eC1.setAttribute("ui.label", "Client1");

        Node et = graph.getNode("t");
        et.setAttribute("ui.style", "shape:circle;fill-color: lightblue;size: 30px; text-alignment: center;");
        et.setAttribute("ui.label", "t");

        graph.display();
    }
}