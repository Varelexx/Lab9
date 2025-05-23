package controller;

import domain.BTree;
import domain.BTreeNode;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinaryTreeOperationsController
{
    @javafx.fxml.FXML
    private Pane pane;
    // coordenadas del arbol
    int x = 531, y = 83, hgap = 225;
    BTree bTree = new BTree();

    @javafx.fxml.FXML
    public void initialize() {
        pane.getChildren().clear();
        bTree.clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap, "root");
        randomizeOnAction(new ActionEvent());
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) throws TreeException {
        if (bTree.isEmpty()) initialize();
        int value;
        do {
            value = util.Utility.random(50);
        } while(!bTree.contains(value));
        bTree.remove(value);
        pane.getChildren().clear(); // Resetea el pane principal.
        drawTree(pane, bTree.getRoot(), x, y, hgap, "root"); // Dibuja el árbol con valores actualizados.

    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        int value = util.Utility.random(50);
        bTree.add(value);
        pane.getChildren().clear(); // Resetea el pane principal.
        drawTree(pane, bTree.getRoot(), x, y, hgap,"root");
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        bTree.clear();
        pane.getChildren().clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap, "root");
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) throws TreeException {
        String contains = String.valueOf(util.FXUtility.dialog("Contains", "¿Cuál elemento desea consultar?"));
        if (bTree.contains(Integer.parseInt(contains))) util.FXUtility.alert("Contains", "El objeto está incluido en el árbol.");
        else util.FXUtility.
                alert("Contains", "El objeto no está incluido en el árbol.");
    }

    @javafx.fxml.FXML
    public void treeHeightOnAction(ActionEvent actionEvent) throws TreeException {
        int height = bTree.height();
        util.FXUtility.alert("Height", "La altura del árbol es: " + height);
    }

    @javafx.fxml.FXML
    public void heightOnAction(ActionEvent actionEvent) throws TreeException {
        String element = String.valueOf(util.FXUtility.dialog("Height", "¿Cuál elemento desea evaluar?"));
        if (bTree.contains(Integer.parseInt(element)))
            util.FXUtility.alert("Height","La altura del elemento es: " + bTree.height(Integer.parseInt(element)));
        else util.FXUtility.alert("Height", "El objeto no está incluido en el árbol.");
    }
    private void drawTree(Pane pane, BTreeNode node, double x, double y, double hGap, String route) {
        if (node != null) {
            // Dibuja la línea hacia el nodo hijo izquierdo.
            if (node.left != null) {
                Line leftLine = new Line(x - hGap, y + 50, x, y);
                pane.getChildren().add(leftLine);
                String newRoute = route.equals("root") ? "root/left" : route + "/left";
                drawTree(pane, node.left, x - hGap, y + 50, hGap / 2, newRoute);
            }// End of 'if'.

            // Dibuja la línea hacia el nodo hijo derecho.
            if (node.right != null) {
                Line rightLine = new Line(x + hGap, y + 50, x, y);
                pane.getChildren().add(rightLine);
                String newRoute = route.equals("root") ? "root/right" : route + "/right";
                drawTree(pane, node.right, x + hGap, y + 50, hGap / 2, newRoute);
            }// End of 'if'.

            Circle circle = new Circle(x, y, 15);
            // Dibujar el círculo para el nodo actual
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);

            // Dibuja el texto para el nodo actual.
            Text text = new Text(String.valueOf(node.data));
            text.setX(x - 5);  // Ajustar posición del texto
            text.setY(y + 5);  // Ajustar posición del texto
            pane.getChildren().add(text);
        }// End of 'if'.
    }// End of method [drawTree].
}