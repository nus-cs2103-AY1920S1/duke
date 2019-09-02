package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * DialogBox class which represents each dialog box on the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param l The label for DialogBox representing text.
     * @param iv The ImageView icon of the message sender.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setPadding(new Insets(5));
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);
        Circle circle = new Circle();
        circle.setCenterX(35);
        circle.setCenterY(35);
        circle.setRadius(35);
        displayPicture.setClip(circle);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets the DialogBox of the user.
     *
     * @param l Label representing the user's text.
     * @param iv ImageView representing the user's profile picture.
     * @return DialogBox Node.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setPadding(new Insets(5));
        db.setStyle("-fx-background-color: rgba(60, 179, 113, 0.5);");
        return db;
    }

    /**
     * Gets the DialogBox of the bot.
     *
     * @param l Label representing the bot's text.
     * @param iv ImageView representing the bot's profile picture.
     * @return DialogBox Node.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setPadding(new Insets(5));
        db.setStyle("-fx-background-color: rgba(255, 165, 0, 0.5);");
        db.flip();
        return db;
    }
}