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
     * @param avatar the avatar to display with the dialog
     * @return a right-justified dialog with the given avatar
     */
    public static DialogBox getUserDialog(String dialog, Image avatar) {
        return new DialogBox(dialog, avatar, "user-dialog");
    }

    /**
     * Creates a left-justified dialog with the given avatar.
     *
     * @param dialog the content of the dialog
     * @param avatar the avatar to display with the dialog
     * @return a left-justified dialog with the given avatar
     */
    public static DialogBox getDukeDialog(String dialog, Image avatar) {
        return DialogBox.makeDukeDialog(dialog, avatar, "duke-message-dialog");
    }

    public static DialogBox getDukeWarning(String dialog, Image avatar) {
        return DialogBox.makeDukeDialog(dialog, avatar, "duke-warning-dialog");
    }

    public static DialogBox getDukeError(String dialog, Image avatar) {
        return DialogBox.makeDukeDialog(dialog, avatar, "duke-error-dialog");
    }

    private static DialogBox makeDukeDialog(String dialog, Image avatar, String type) {
        DialogBox box = new DialogBox(dialog, avatar, type);
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
