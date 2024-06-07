package controller;

import domain.GraphException;
import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.*;

import static util.Utility.getRandom;

public class AdjacencyMatrixGraphController {

    @FXML
    public TextArea txA_AMG;
    @FXML
    public Label txf_AMG;

    @FXML
    public void randomizeGraph() {
        generateRandomGraph(10, 99, 50);
    }
    @FXML
    public void containsVertex() {
        String graphText = txA_AMG.getText();
        int vertexToFind = getRandom(99);
        if (graphText.contains(String.valueOf(vertexToFind))) {
            txf_AMG.setText("El grafo contiene el vértice " + vertexToFind);
            txf_AMG.setVisible(true);
        } else {
            txf_AMG.setText("El grafo no contiene el vértice " + vertexToFind);
            txf_AMG.setVisible(true);
        }
    }

    @FXML
    public void containsEdge() {
        String graphText = txA_AMG.getText();
        int vertex1 = getRandom(10);
        int vertex2 = getRandom(10);
        if (graphText.contains(String.valueOf(vertex1)) && graphText.contains(String.valueOf(vertex2))) {
            txf_AMG.setText("El grafo contiene la arista entre " + vertex1 + " y " + vertex2);
            txf_AMG.setVisible(true);
        } else {
            txf_AMG.setText("El grafo no contiene la arista entre " + vertex1 + " y " + vertex2);
            txf_AMG.setVisible(true);
        }
    }

    @FXML
    public void toStringGraph() {
        StringBuilder sb = new StringBuilder();
        int[][] adjacencyMatrix = parseAdjacencyMatrix(txA_AMG.getText());
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                sb.append(adjacencyMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        txf_AMG.setText(sb.toString());
        txf_AMG.setVisible(true);
    }

    @FXML
    public String dfs() throws GraphException, StackException, ListException {
        boolean[] visited = new boolean[10]; // Marca todos los vértices como no visitados
        String info = ""; // Variable para almacenar el resultado del recorrido
        Stack<Integer> stack = new Stack<>(); // Pila para realizar el recorrido DFS
        stack.push(0); // Inicia en el vértice 0

        while (!stack.isEmpty()) {
            int vertexIndex = stack.pop(); // Obtiene el vértice de la pila
            if (!visited[vertexIndex]) {
                visited[vertexIndex] = true; // Marca el vértice como visitado
                info += vertexIndex + ", "; // Agrega el vértice al resultado
                // Agrega los vecinos no visitados del vértice a la pila
                for (int i = 0; i < 10; i++) {
                    if (i != vertexIndex && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return info;
    }

    @FXML
    public String bfs() throws GraphException, QueueException, ListException {
        boolean[] visited = new boolean[10]; // Marca todos los vértices como no visitados
        String info = ""; // Variable para almacenar el resultado del recorrido
        Queue<Integer> queue = new LinkedList<>(); // Cola para realizar el recorrido BFS
        queue.add(0); // Inicia en el vértice 0

        while (!queue.isEmpty()) {
            int vertexIndex = queue.remove(); // Obtiene el vértice de la cola
            if (!visited[vertexIndex]) {
                visited[vertexIndex] = true; // Marca el vértice como visitado
                info += vertexIndex + ", "; // Agrega el vértice al resultado
                // Agrega los vecinos no visitados del vértice a la cola
                for (int i = 0; i < 10; i++) {
                    if (i != vertexIndex && !visited[i]) {
                        queue.add(i);
                    }
                }
            }
        }
        return info;
    }



    private void generateRandomGraph(int numVertices, int maxValue, int maxWeight) {
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        Set<Integer> vertices = new HashSet<>();
        while (vertices.size() < numVertices) {
            vertices.add(getRandom(maxValue));
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = getRandom(maxWeight);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                sb.append(adjacencyMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        txA_AMG.setText(sb.toString());
    }
    private int[][] parseAdjacencyMatrix(String text) {
        String[] lines = text.split("\n");
        int numRows = lines.length;
        int[][] matrix = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            String[] elements = lines[i].trim().split("\\s+");
            for (int j = 0; j < elements.length; j++) {
                matrix[i][j] = Integer.parseInt(elements[j]);
            }
        }
        return matrix;
    }
}
