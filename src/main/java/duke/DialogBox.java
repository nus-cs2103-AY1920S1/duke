package duke;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private final Circle circle = new Circle(49.5,50.0,51.0);

    private DialogBox(String text, Image img, Background background) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setBackground(background);
        displayPicture.setClip(circle);
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
     * Returns the User's <code>DialogBox</code>.
     * @param text User's input.
     * @param img User's photo.
     * @return <code>DialogBox</code> for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        Color col = Color.rgb(137, 207, 240, 0.5);
        CornerRadii corn = new CornerRadii(10);
        Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
        return new DialogBox(text, img, background);
    }

    /**
     * Returns the Duke's <code>DialogBox</code>.
     * @param text Duke's response.
     * @param img Duke's photo.
     * @return <code>DialogBox</code> for user.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        Color col = Color.rgb(239,186,247, 0.5);
        CornerRadii corn = new CornerRadii(10);
        Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
        var db = new DialogBox(text, img, background);
        db.flip();
        return db;
    }
}