package controller;

import domain.queue.QueueException;
import domain.stack.StackException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import domain.AdjacencyListGraph;
import domain.GraphException;
import domain.list.ListException;
import javafx.scene.layout.Pane;
import util.Utility;


public class AdjacencyListGraphController {
    @FXML
    private Pane graphPane;
    @FXML
    public TextArea txA_ALG;
    @FXML
    public Label txf_ALG;

    private AdjacencyListGraph graph;

    @FXML
    public void initialize() {
        AdjacencyListGraph graph = new AdjacencyListGraph(50);
    }

    @FXML
    public void randomize() {
        txA_ALG.clear();
        for (int i = 0; i < 20; i++) {
            int num = Utility.getRandom(51) - 1;
            try {
                graph.addVertex(num);
                txA_ALG.appendText("num " + num + " was added\n");
            } catch (GraphException | ListException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void containsVertex() {
        int randomVertex = getRandomVertex();
        try {
            txf_ALG.setText("Contains vertex " + randomVertex + ": " + graph.containsVertex(randomVertex));
        } catch (GraphException | ListException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void containsEdge() {
        int randomVertex1 = getRandomVertex();
        int randomVertex2 = getRandomVertex();
        try {
            txf_ALG.setText("Contains edge between " + randomVertex1 + " and " + randomVertex2 + ": " + (graph.containsVertex(randomVertex1) && graph.containsVertex(randomVertex2)));
        } catch (GraphException | ListException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void toStringGraph() {
        txA_ALG.setText(graph.toString());
    }

    @FXML
    public void DFSTour() {
        try {
            graph.dfs();
        } catch (GraphException | ListException | StackException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void BFSTour() {
        try {
            graph.bfs();
        } catch (GraphException | QueueException | ListException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRandomVertex () {
            return Utility.getRandom(51) - 1;
        }
    }
