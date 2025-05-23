package controller;

import domain.BTree;
import domain.BTreeNode;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinaryTreeTourController
{
    @javafx.fxml.FXML
    private Pane pane;
    @javafx.fxml.FXML
    private Text nameTag;
    // coordenadas del arbol
    int x = 531, y = 83, hgap = 225, nodeCounter = 0;
    BTree bTree = new BTree();


    @javafx.fxml.FXML
    public void initialize() {
        pane.getChildren().clear();
        bTree.clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap);

        randomizeOnAction(new ActionEvent());
        nameTag.setVisible(true);
        nameTag.setText("");
    }

    @javafx.fxml.FXML
    public void postOrderOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
        drawTree(pane, bTree.getRoot(), x, y, hgap);
        nodeCounter = 1;  // Reiniciar el contador de nodos
        postOrderUsage(bTree.getRoot(), x, y, hgap);

        pane.getChildren().add(nameTag);
        nameTag.setVisible(true);
        nameTag.setText("PostOrder \nI-D-R");
    }
    private void postOrderUsage(BTreeNode node, double x, double y, double hGap) {

        if (node != null) {
            if (node.left != null) {
                postOrderUsage(node.left, x - hGap, y + 50, hGap / 2);
            }
            if (node.right != null) {
                postOrderUsage(node.right, x + hGap, y + 50, hGap / 2);
            }
            Text text = new Text(String.valueOf(nodeCounter));
            text.setX(x - 5);  // Ajustar posición del texto
            text.setY(y + 30);  // Ajustar posición del texto
            pane.getChildren().add(text);
            nodeCounter++;
        }
    }

    @javafx.fxml.FXML
    public void inOrderOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
        drawTree(pane, bTree.getRoot(), x, y, hgap);
        nodeCounter = 1;  // Reiniciar el contador de nodos
        inOrderUsage(bTree.getRoot(), x, y, hgap);

        pane.getChildren().add(nameTag);
        nameTag.setVisible(true);
        nameTag.setText("InOrder \nI-R-D");
    }
    private void inOrderUsage(BTreeNode node, double x, double y, double hGap) {
        if (node != null) {
            if (node.left != null) {
                inOrderUsage(node.left, x - hGap, y + 50, hGap / 2);
            }
            Text text = new Text(String.valueOf(nodeCounter));
            text.setX(x - 5);  // Ajustar posición del texto
            text.setY(y + 30);  // Ajustar posición del texto
            pane.getChildren().add(text);
            nodeCounter++;
            if (node.right != null) {
                inOrderUsage(node.right, x + hGap, y + 50, hGap / 2);
            }
        }
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        bTree.clear();
        pane.getChildren().clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap);
    }

    @javafx.fxml.FXML
    public void preOrderOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
        drawTree(pane, bTree.getRoot(), x, y, hgap);
        nodeCounter = 1;  // Reiniciar el contador de nodos
        preOrderUsage(bTree.getRoot(), x, y, hgap);

        pane.getChildren().add(nameTag);
        nameTag.setVisible(true);
        nameTag.setText("PreOrder \nR-I-D");
    }
    private void preOrderUsage(BTreeNode node, double x, double y, double hGap) {
        if (node != null) {
            Text text = new Text(String.valueOf(nodeCounter));
            text.setX(x - 5);  // Ajustar posición del texto
            text.setY(y + 30);  // Ajustar posición del texto
            pane.getChildren().add(text);
            nodeCounter++;
            if (node.left != null) {
                preOrderUsage(node.left, x - hGap, y + 50, hGap / 2);
            }
            if (node.right != null) {
                preOrderUsage(node.right, x + hGap, y + 50, hGap / 2);
            }
        }
    }

    private void drawTree(Pane pane, BTreeNode node, double x, double y, double hGap) {
        if (node != null) {
            // Dibujar la línea hacia el nodo hijo izquierdo
            if (node.left != null) {
                Line leftLine = new Line(x - hGap, y + 50, x, y);
                pane.getChildren().add(leftLine);
                drawTree(pane, node.left, x - hGap, y + 50, hGap / 2);
            }

            // Dibujar la línea hacia el nodo hijo derecho
            if (node.right != null) {
                Line rightLine = new Line(x + hGap, y + 50, x, y);
                pane.getChildren().add(rightLine);
                drawTree(pane, node.right, x + hGap, y + 50, hGap / 2);
            }

            Circle circle = new Circle(x, y, 15);
            // Dibujar el círculo para el nodo actual
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);

            // Dibujar el texto para el nodo actual
            Text text = new Text(String.valueOf(node.data)); // Asigna la data al texto
            text.setX(x - 5);  // Ajustar posición del texto
            text.setY(y + 5);  // Ajustar posición del texto
            pane.getChildren().add(text);

        }
    }
}