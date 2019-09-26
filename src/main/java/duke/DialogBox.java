package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;

    /**
     * Generates a DialogBox for display in the GUI.
     *
     * @param message The message to be shown
     * @param theImage The image to be shown
     */
    public DialogBox(String message, Image theImage) {
        text = new Label(message);
        text.setWrapText(true);
        text.setStyle("-fx-background-radius: 10; -fx-background-color: #9FDB68;");
        text.setPadding(new Insets(10));

        Circle roundedPicture = new Circle(40);
        roundedPicture.setFill(new ImagePattern(theImage));

        FlowPane theFlowPane = new FlowPane();
        theFlowPane.getChildren().addAll(text, roundedPicture);
        theFlowPane.setAlignment(Pos.TOP_RIGHT);
        FlowPane.setMargin(text, new Insets(0, 5, 0, 5));

        getChildren().addAll(theFlowPane);
        HBox.setMargin(theFlowPane, new Insets(10, 10, 0, 10));
    }

    /**
     * Flips the DialogBox to the other side to facilitate a conversational view.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        for (Node node : tmp) {
            if (node instanceof FlowPane) {
                FlowPane theFlowPane = (FlowPane) node;
                FXCollections.reverse(theFlowPane.getChildren());
                theFlowPane.setAlignment(Pos.TOP_LEFT);
            }
        }
    }

    /**
     * Creates a DialogBox to get user input.
     */
    public static DialogBox getUserDialog(String message, Image theImage) {
        return new DialogBox(message, theImage);
    }

    /**
     * Creates a DialogBox for responding.
     */
    public static DialogBox getDukeDialog(String message, Image theImage) {
        DialogBox db = new DialogBox(message, theImage);
        db.flip();
        return db;
    }

}