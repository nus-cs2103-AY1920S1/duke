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

import java.io.IOException;

public class DialogBox extends HBox {
    private static final Image USER_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/User.png"));
    private static final Image DUKE_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/Duke.png"));
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

        this.getStylesheets().add("stylesheets/dialogBox.css");
        this.dialog.setText(dialog);
        this.getStyleClass().add(styleClass);
        this.avatar.setImage(avatar);
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
