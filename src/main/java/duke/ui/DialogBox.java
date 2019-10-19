package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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

        int lineCount = text.split("\n").length;
        dialog.setMinHeight((double) (lineCount - 2) * 19 + 2 * 22);
        dialog.setText(text);

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(25, 25, 25));
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
     * Returns a dialog box.
     *
     * @param text the text for the dialog box
     * @param img the image for the dialog box
     * @return returns a dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);

        Label dialog = (Label) db.getChildren().get(0);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.rgb(255, 213, 171), new CornerRadii(10), Insets.EMPTY)));
        return db;
    }

    /**
     * Flips a dialog box an returns it.
     *
     * @param text the text for the dialog box
     * @param img the image for the dialog box
     * @return return a flipped dialog box
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        Label dialog = (Label) db.getChildren().get(1);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.rgb(179, 224, 255), new CornerRadii(10), Insets.EMPTY)));

        return db;
    }
}