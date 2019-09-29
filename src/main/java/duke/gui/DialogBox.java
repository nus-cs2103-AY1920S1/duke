package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * A DialogBox is drawn whenever the user issues a Command or Duke responds.
 */
public class DialogBox extends HBox {
    private static final Image USER_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/User.jpg"));
    private static final Image DUKE_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/Duke.jpg"));
    @FXML
    private HBox box;
    @FXML
    private Label dialog;
    @FXML
    private ImageView avatar;

    private DialogBox(String dialog, Image avatar, String styleClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(dialog);
        this.box.getStyleClass().add(styleClass);
        this.avatar.setImage(avatar);
        Circle clip = new Circle(32, 32, 32);
        this.avatar.setClip(clip);
    }

    /**
     * Creates a right-justified dialog with the given avatar.
     *
     * @param dialog the content of the dialog
     * @return a right-justified dialog with the given avatar
     */
    public static DialogBox getUserDialog(String dialog) {
        return new DialogBox(dialog, DialogBox.USER_AVATAR, "user-dialog");
    }

    /**
     * Creates a left-justified dialog with the given avatar.
     *
     * @param dialog the content of the dialog
     * @return a left-justified dialog with the given avatar
     */
    public static DialogBox getDukeDialog(String dialog) {
        return DialogBox.makeDukeDialog(dialog, "duke-message-dialog");
    }

    public static DialogBox getDukeWarning(String dialog) {
        return DialogBox.makeDukeDialog(dialog, "duke-warning-dialog");
    }

    public static DialogBox getDukeError(String dialog) {
        return DialogBox.makeDukeDialog(dialog, "duke-error-dialog");
    }

    private static DialogBox makeDukeDialog(String dialog, String type) {
        DialogBox box = new DialogBox(dialog, DialogBox.DUKE_AVATAR, type);
        box.flip();
        return box;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> children = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(children);
        this.getChildren().setAll(children);
    }
}
