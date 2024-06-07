package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AdjacencyListOperationsController {


    @FXML
    private TextArea txA_ALGO;

    private List<Character> vertices = new ArrayList<>();
    private Set<String> edges = new HashSet<>();


    private int getRandom(int bound) {
        return new Random().nextInt(bound) + 1;
    }

    @FXML
    private void randomizeGraph() {
        clearGraph();
        int numVertices = getRandom(10) + 5;
        for (int i = 0; i < numVertices; i++) {
            char vertex = (char) ('A' + i);
            vertices.add(vertex);
            txA_ALGO.appendText("Added vertex: " + vertex + "\n");
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                char source = vertices.get(i);
                char target = vertices.get(j);
                int weight = getRandom(50);
                edges.add(source + "-" + target + ": " + weight);
                txA_ALGO.appendText("Added edge: " + source + " -> " + target + " with weight " + weight + "\n");
            }
        }
    }

    @FXML
    private void addVertex() {
        char vertex = generateRandomVertex();
        if (vertices.contains(vertex)) {
            addVertex();
        }
        vertices.add(vertex);
        txA_ALGO.appendText("Added vertex: " + vertex + "\n");
    }

    @FXML
    private void addEdgesAndWeights() {
        if (vertices.size() < 2) {
            txA_ALGO.appendText("At least 2 vertices are required to add edges.\n");
        }
        for (int i = 0; i < vertices.size(); i++) {
            char source = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); j++) {
                char target = vertices.get(j);
                int weight = getRandom(50);
                edges.add(source + "-" + target + ": " + weight);
                txA_ALGO.appendText("Added edge: " + source + " -> " + target + " with weight " + weight + "\n");
            }
        }
    }

    @FXML
    private void removeVertex() {
        if (vertices.isEmpty()) {
            txA_ALGO.appendText("No vertices to remove.\n");
        }
        char vertex = vertices.remove(vertices.size() - 1);
        txA_ALGO.appendText("Removed vertex: " + vertex + "\n");
        edges.removeIf(edge -> edge.startsWith(vertex + "-") || edge.endsWith("-" + vertex));
    }

    @FXML
    private void removeEdgesAndWeights() {
        if (edges.isEmpty()) {
            txA_ALGO.appendText("No edges to remove.\n");
        }
        edges.clear();
        txA_ALGO.appendText("Removed all edges.\n");
    }

    @FXML
    private void clearGraph() {
        vertices.clear();
        edges.clear();
        txA_ALGO.clear();
        txA_ALGO.appendText("Cleared graph.\n");
    }

    private char generateRandomVertex() {
        Random random = new Random();
        return (char) ('A' + random.nextInt(26));
    }

}
