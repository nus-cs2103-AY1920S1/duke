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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static boolean isDuke;
    @FXML
    private static final Background DUKE_BACKGROUND = new Background(new BackgroundFill(Color.rgb(
            237, 227, 255), CornerRadii.EMPTY, Insets.EMPTY));
    @FXML
    private static final Background USER_BACKGROUND = new Background(new BackgroundFill(Color.rgb(
            203, 240, 255), CornerRadii.EMPTY, Insets.EMPTY));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    private DialogBox(String text, Image img, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isDuke) {
            this.setBackground(DUKE_BACKGROUND);
        } else {
            this.setBackground(USER_BACKGROUND);
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
     * Gets the DialogBox for UI (User).
     * @param text User input.
     * @param img Icon for user.
     * @return DialogBox with designated UI features.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        isDuke = false;
        return new DialogBox(text, img, isDuke);
    }

    /**
     * Gets the DialogBox for UI (Duke).
     * @param text Duke output.
     * @param img Icon for Duke.
     * @return DialogBox with designated UI features.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        isDuke = true;
        var db = new DialogBox(text, img, isDuke);
        db.flip();
        return db;
    }
}