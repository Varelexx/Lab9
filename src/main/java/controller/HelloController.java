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
    private Text messageText;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void graphicBTreeOnAction(ActionEvent actionEvent) {
        loadPage("BinaryTreeGraphic.fxml");
    }

    @FXML
    public void bTreeTourOnAction(ActionEvent actionEvent) { loadPage("BinaryTreeTour.fxml");
    }

    @FXML
    public void bTreeOperationsOnAction(ActionEvent actionEvent) {  loadPage("BinaryTreeOperations.fxml");
    }

    @FXML
    public void home(ActionEvent actionEvent) {
        this.messageText.setText("Laboratory 9");
        this.bp.setCenter(ap);
    }
}