package ui.fx;

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
import java.util.Collections;

/**
 * Dialog box for JavaFx ui.
 */
public class FxDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private FxDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeMainWindowController.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
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
     * Returns dialog box flushed to the left.
     * @param text text to be displayed in dialog box
     * @param img image for dialog box
     * @return dialog box instance
     */
    public static FxDialogBox getUserDialog(String text, Image img) {
        return new FxDialogBox(text, img);
    }

    /**
     * Returns dialog box flushed to the right.
     * @param text text to be displayed in dialog box
     * @param img image for dialog box
     * @return dialog box instance
     */
    public static FxDialogBox getDukeDialog(String text, Image img) {
        FxDialogBox db = new FxDialogBox(text, img);
        db.flip();
        return db;
    }
}
