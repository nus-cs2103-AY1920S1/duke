import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        //adding font
        dialog.setFont(new Font("Arial", 16));
        displayPicture.setImage(img);

        //adding padding
        this.setPadding(new Insets(10, 10, 10, 10));
        dialog.setPadding(new Insets(10, 10, 10, 10));
        //change image view to circle
        Circle clip = new Circle(50,50,50);
        displayPicture.setClip(clip);
        //Show overflow
        dialog.setMinSize(Label.USE_COMPUTED_SIZE, Label.USE_PREF_SIZE);
        //add border
        setStyle("-fx-border-insets: 5px;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 5;"
                + "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
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
     * Contains the image and message of the user.
     *
     * @param text Text to display.
     * @param img Icon for User.
     * @return Dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGREY,
                CornerRadii.EMPTY,
                new Insets(5))));

        return db;
    }

    /**
     * Contains the image and message of Duke.
     *
     * @param text Text to display.
     * @param img Icon for Duke.
     * @return Flipped horizontal dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        db.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                CornerRadii.EMPTY,
                new Insets(5))));

        return db;
    }
}