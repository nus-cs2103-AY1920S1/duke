package duke.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private Circle displayPicture;

    private DialogBox(String text, Image img, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setStyle("-fx-background-color:" + color + "; -fx-background-radius: 20");
        dialog.setText(text);
        getCircleImg(img, displayPicture);

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
     * Returns a DialogBox that represents user's input, with their image.
     * @param text user's input.
     * @param img user's image
     * @return DialogBox representing user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "MISTYROSE");
    }

    /**
     * Returns a DialogBox that represents Duke's response to user's command, with their image.
     * @param text Duke's response to user's command.
     * @param img Duke's image.
     * @return DialogBox representing Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "LAVENDER");
        db.flip();
        return db;
    }

    /**
     * Fills a circle with the image given to obtain a circular image.
     * @param img image to be filled in the circle.
     * @param cir circle which will be filled by image.
     */
    public void getCircleImg(Image img, Circle cir) {
        cir.setStroke(Color.LIGHTPINK);
        cir.setFill(new ImagePattern(img));
    }
}