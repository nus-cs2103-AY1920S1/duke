package duke.command;
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

    private DialogBox(String text, Image img) {
        try {
            loadFxml();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double minHeight = text.split("\n").length * 25 + 75;
        dialog.setStyle("-fx-text-fill: beige");

        setLabelSize(text, minHeight);
        setImageViewSize(img);
    }

    /**
     * Set the content and size of the displayPicture ImageView.
     * @param img Image of User or Duke to be put in this ImageView.
     */
    private void setImageViewSize(Image img) {
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(25, 17, 25));
    }

    /**
     * Set the text and size of dialog Label
     * @param text String to be set as Label text
     * @param minHeight Minimal height of this Label
     */
    private void setLabelSize(String text, double minHeight) {
        dialog.setText(text);
        dialog.setPadding(new Insets(10, 10, 10, 10));
        this.setMinHeight(minHeight);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.rgb( 126, 179, 191), new CornerRadii(10), Insets.EMPTY)));
    }

    /**
     * Set the size of this DialogBox
     * @param minHeight Minimal height for this DialogBox
     */
    private void setDialogBoxSize(double minHeight) {
        this.setMinHeight(minHeight);
    }

    /**
     * Load layout.
     * @throws IOException
     */
    private void loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.rgb(140, 134, 48, 0.8), new CornerRadii(10), Insets.EMPTY)));
    }

    /**
     * Create a new dialog box using with the user picture
     * @param text message String
     * @param img user profile photo Image
     * @return DialogBox with the message String and user profile photo Image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Create a new dialog box with duke photo
     * @param text message String
     * @param img duke profile photo Image
     * @return DialogBox with the message String and duke profile photo Image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}