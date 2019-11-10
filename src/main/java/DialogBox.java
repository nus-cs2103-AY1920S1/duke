import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            HBox.setHgrow(dialog, Priority.ALWAYS); // box will resize according to text
            HBox.setHgrow(displayPicture, Priority.ALWAYS);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
     * Returns a dialog box with text and image.
     *
     * @param text the text to be shown on the dialog box
     * @param img the image to be shown on the dialog box
     * @return the dialog box with the respective text and img
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill bgFill = new BackgroundFill(Color.PALETURQUOISE, new CornerRadii(25),
                new Insets(3,3,3,3));
        db.setBackground(new Background(bgFill));
        return db;
    }

    /**
     * Returns a dialog box where the image is flipped laterally from the getUserDialog method.
     *
     * @param text the text to be shown on the dialog box
     * @param img the image to be shown on the dialog box
     * @return the dialog box with the respective text and img
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill bgFill = new BackgroundFill(Color.LIGHTPINK, new CornerRadii(25),
                                                   new Insets(3,3,3,3));
        db.setBackground(new Background(bgFill));
        return db;
    }
}