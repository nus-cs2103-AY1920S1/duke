import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private Circle displayPictureShape;
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox, a custom control.
     * @param l Label component in a DialogBox
     * @param iv image to view
     */
    public DialogBox(Label l, ImageView iv) {
        displayPictureShape = new Circle(75,75,75);
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setClip(displayPictureShape);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that ImageView is on the left and text is on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns DialogBoxes that represents Duke messages.
     * @param l Label component in the DialogBox
     * @param iv Image to be viewed
     * @return DialogBox to contain Duke messages.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
