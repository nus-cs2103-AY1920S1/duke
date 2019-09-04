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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog = new Label();
    @FXML
    private ImageView displayPicture = new ImageView();

    /**
     * DialogBox Constructor to initialize Label and ImageView.
     *
     * @param text text for the Label.
     * @param img Image for the ImageView.
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

        dialog.setWrapText(true);
        dialog.setPadding(new Insets(5));
        Circle circle = new Circle();
        circle.setCenterX(35);
        circle.setCenterY(35);
        circle.setRadius(35);
        displayPicture.setClip(circle);
        dialog.setText(text);
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
     * Gets the DialogBox of the user.
     *
     * @param text user's text.
     * @param img user's profile picture.
     * @return DialogBox Node
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPadding(new Insets(5));
        db.setStyle("-fx-background-color: rgba(60, 179, 113, 0.5);");
        return db;
    }

    /**
     * Gets the DialogBox of the bot.
     *
     * @param text Bot's text.
     * @param img Bot's profile picture.
     * @return DialogBox Node
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPadding(new Insets(5));
        db.flip();
        db.setStyle("-fx-background-color: rgba(255, 165, 0, 0.5);");
        return db;
    }
}