package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /** 
     * Constructs a <code>DialogBox</code> element to render a display picture to the right of a <code>Label</code>
     * text container.
     * 
     * @param label <code>Label</code> element containing text to render alongside picture.
     * @param image <code>ImageView</code> <code>Node</code> containing an image to render.
     */
    private DialogBox(Label label, ImageView image) {
        this.text = label;
        this.displayPicture = image;

        this.text.setWrapText(true);
        this.text.setStyle("-fx-aligment: center-left;");
        this.text.setPadding(new Insets(5, 10, 5, 10));
        this.text.setMinHeight(75.0);
        this.displayPicture.setFitWidth(75.0);
        this.displayPicture.setFitHeight(75.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10, 10, 10, 10));
    }
    
    /**
     * Flips layout of elements in this <code>DialogBox</code> such that the image appears to the left of the text.
     * 
     * @return the original <code>DialogBox</code> with the horizontal alignment of its elements flipped.
     */
    private DialogBox setLeft() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    /**
     * Returns a <code>DialogBox</code> element for displaying user input.
     * 
     * @param text a <code>Label</code> instance containing the text to display.
     * @param image a <code>ImageView</code> instance containing the image to display.
     * @return a <code>DialogBox</code> for displaying user input.
     */
    public static DialogBox getUserDialog(Label text, ImageView image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a <code>DialogBox</code> element for displaying output from the <code>Duke</code> instance.
     * 
     * @param text a <code>Label</code> instance containing the text to display.
     * @param image a <code>ImageView</code> instance containing the image to display.
     * @return a <code>DialogBox</code> for displaying messages from Duke.
     */
    public static DialogBox getDukeDialog(Label text, ImageView image) {
        return new DialogBox(text, image).setLeft();
    }
}