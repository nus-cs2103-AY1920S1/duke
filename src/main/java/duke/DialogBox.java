package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * A custom control that displays text and an image side by side.
 */
public class DialogBox extends HBox {

    private static final double DISPLAY_PICTURE_WIDTH = 60.0;
    private static final double DISPLAY_PICTURE_HEIGHT = 60.0;

    private Label text;
    private ImageView displayPicture;

    /**
     * Instantiates a new DialogBox by setting the text and image.
     * @param label The text that forms the speaker's dialogue.
     * @param imageView The speaker's avatar.
     */
    public DialogBox(Label label, ImageView imageView) {
        this.text = label;
        this.displayPicture = imageView;

        text.setWrapText(true);
        text.setPadding(new Insets(5, 10, 5, 10));
        text.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf(Color.GAINSBORO.toString()),
                        CornerRadii.EMPTY,
                        new Insets(0, 5, 0, 5)
                )
        ));

        displayPicture.setFitWidth(DISPLAY_PICTURE_WIDTH);
        displayPicture.setFitHeight(DISPLAY_PICTURE_HEIGHT);
        displayPicture.setClip(new Circle(
                DISPLAY_PICTURE_WIDTH / 2,
                DISPLAY_PICTURE_HEIGHT / 2,
                DISPLAY_PICTURE_WIDTH / 2
        ));

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 5, 0, 5));
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Returns a new DialogBox for the user's input.
     * @param label The Label representing the user's text input.
     * @param imageView The ImageView representing the user's avatar.
     * @return A DialogBox containing the user's text input and avatar.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Returns a new DialogBox for Duke's response to user input.
     * @param label The Label representing the text in Duke's response.
     * @param imageView The ImageView representing Duke's avatar.
     * @return A DialogBox aligned to the left, containing Duke's text response and avatar.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }

    /**
     * Changes a DialogBox's alignment from right to left.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
