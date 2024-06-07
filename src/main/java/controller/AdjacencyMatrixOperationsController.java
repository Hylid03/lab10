package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import static util.Utility.getRandom;

public class AdjacencyMatrixOperationsController {

    @FXML
    private TextArea txA_AMGO;
    private int[][] adjacencyMatrix = new int[10][10];

    @FXML
    private void handleClear() {
        txA_AMGO.clear();
        txA_AMGO.setText("The clear has been done successfully");
    }

    @FXML
    private void handleRandomize() {
        StringBuilder matrix = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                adjacencyMatrix[i][j] = getRandom(50);
                matrix.append(adjacencyMatrix[i][j]).append(" ");
            }
            matrix.append("\n");
        }
        txA_AMGO.setText(matrix.toString());
    }

    @FXML
    private void handleAddVertex() {
        // Aumentar el tamaño de la matriz de adyacencia
        int newSize = adjacencyMatrix.length + 1;
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix[i].length);
        }
        adjacencyMatrix = newMatrix;
        showMatrix();
    }

    @FXML
    private void handleAddEdgesAndWeights() {
        int vertex1 = getRandom(adjacencyMatrix.length) - 1;
        int vertex2 = getRandom(adjacencyMatrix.length) - 1;
        int weight = getRandom(50);
        adjacencyMatrix[vertex1][vertex2] = weight;
        adjacencyMatrix[vertex2][vertex1] = weight;
        showMatrix();
    }

    @FXML
    private void handleRemoveVertex() {
        int vertexToRemove = getRandom(adjacencyMatrix.length) - 1;
        int newSize = adjacencyMatrix.length - 1;
        int[][] newMatrix = new int[newSize][newSize];
        int newRow = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (i == vertexToRemove) {
                continue;
            }
            int newCol = 0;
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (j != vertexToRemove) {
                    newMatrix[newRow][newCol] = adjacencyMatrix[i][j];
                    newCol++;
                }
            }
            newRow++;
        }
        adjacencyMatrix = newMatrix;
        showMatrix();
    }

    @FXML
    private void handleRemoveEdgesAndWeights() {
        int vertex1 = getRandom(adjacencyMatrix.length) - 1;
        int vertex2 = getRandom(adjacencyMatrix.length) - 1;
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;
        showMatrix();
    }

    private void showMatrix() {
        StringBuilder matrix = new StringBuilder();
        for (int[] row : adjacencyMatrix) {
            for (int cell : row) {
                matrix.append(cell).append(" ");
            }
            matrix.append("\n");
        }
        txA_AMGO.setText(matrix.toString());
    }
}
