package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text txtMessage;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Home(ActionEvent event) {
        this.txtMessage.setText("Laboratory No. 10");
        this.bp.setCenter(ap);
    }

    @FXML
    void adjacencyListGraphOnAction(ActionEvent event) {
        loadPage("adjacencyListGraph.fxml");

    }

    @FXML
    void adjacencyMatrixGraphOnAction(ActionEvent event) {
        loadPage("adjacencyMatrixGraph.fxml");
    }

    @FXML
    void listOperationsOnAction(ActionEvent event) {
        loadPage("adjacencyListOperations.fxml");
    }

    @FXML
    void matrixOperationsOnAction(ActionEvent event) {
        loadPage("adjacencyMatrixOperations.fxml");
    }

}