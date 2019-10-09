package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox based on given text and avatar for Duke output.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setMinWidth(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generates a DialogBox based on given text and avatar for user input.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
     * @return JavaFX DialogBox containing the given text and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill backFill = new BackgroundFill(
            Color.web("#c5dae3"),
            new CornerRadii(20),
            new Insets(5, 5, 5, 5)
        );
        Background background = new Background(backFill);
        db.setBackground(background);
        return db;
    }

    /**
     * Generates a DialogBox based on given text and avatar for Duke output.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
     * @return JavaFX DialogBox containing the given text and avatar.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill backFill = new BackgroundFill(
            Color.web("#cfe8d5"),
            new CornerRadii(20),
            new Insets(5, 5, 5, 5)
        );
        Background background = new Background(backFill);
        db.setBackground(background);
        return db;
    }
}