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

public class BinaryTreeGraphicController
{
    @javafx.fxml.FXML
    private Pane pane;
    BTree bTree = new BTree();
    int x = 531, y = 83, hgap = 225;
    Line levelLine = null;
    Text levelText = null;


    @javafx.fxml.FXML
    public void initialize() {
        pane.getChildren().clear();
        bTree.clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap);
        RandomizeOnAction(new ActionEvent());
    }

    @javafx.fxml.FXML
    public void RandomizeOnAction(ActionEvent actionEvent) {
        bTree.clear();
        pane.getChildren().clear();
        for (int i = 0; i <= 20; i++)
            bTree.add(util.Utility.random(50));
        BTreeNode root = bTree.getRoot();
        drawTree(pane, root, x, y, hgap);
    }

    @javafx.fxml.FXML
    public void InfoOnAction(ActionEvent actionEvent) {
        try {
            String preOrder, inOrder, postOrder;
            int height;
            preOrder = bTree.preOrder();
            inOrder = bTree.inOrder();
            postOrder = bTree.postOrder();
            height = bTree.height();
            String message = "PreOrder: " + preOrder + "\nInOrder: " + inOrder + "\nPostOrder: " + postOrder + "\nHeight: " + height;
            util.FXUtility.alert("Tour Information", message);

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void LevelsOnAction(ActionEvent actionEvent) {
        try {

                // Obtener la altura del árbol
                int maxLevel = bTree.height();

                // Dibujar las líneas de los niveles en el Pane
                double y = 95; // Altura inicial
                double lineSpacing = 55; // Espaciado vertical entre las líneas de nivel

                for (int i = 0; i <= maxLevel; i++) {
                    double yPos = y + i * lineSpacing;
                    levelLine = new Line(0, yPos, pane.getWidth(), yPos);
                    levelLine.setStroke(Color.GRAY);
                    pane.getChildren().add(levelLine);

                    levelText = new Text("Nivel " + i);
                    levelText.setX(5);
                    levelText.setY(yPos - 5); // Ajustar posición del texto
                    pane.getChildren().add(levelText);
                }

        } catch (TreeException e) {
            e.printStackTrace();
        }
    }
    private void drawTree(Pane pane, BTreeNode node, double x, double y, double hGap) {
        if (node != null) {
            // Dibuja la línea hacia el nodo hijo izquierdo.
            if (node.left != null) {
                Line leftLine = new Line(x - hGap, y + 50, x, y);
                pane.getChildren().add(leftLine);

                drawTree(pane, node.left, x - hGap, y + 50, hGap / 2);
            }// End of 'if'.

            // Dibuja la línea hacia el nodo hijo derecho.
            if (node.right != null) {
                Line rightLine = new Line(x + hGap, y + 50, x, y);
                pane.getChildren().add(rightLine);

                drawTree(pane, node.right, x + hGap, y + 50, hGap / 2);
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