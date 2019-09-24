package duke;

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
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a duke.DialogBox.
     *
     * @param text of the dialog box.
     * @param img of the dialog box, either from the user or duke.
     */
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
        this.dialog.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
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
     * Returns the user dialog box.
     *
     * @param text entered by the user.
     * @param img of the user.
     * @return a Dialog Box to show on the duke.MainWindow.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox current = new DialogBox(text, img);
        current.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(28, 97, 176, 1);"
                       + "-fx-text-fill: white";
        current.dialog.setStyle(style);
        return current;
    }

    /**
     * Returns duke.Duke's dialog box.
     *
     * @param text response from duke.Duke.
     * @param img of duke.Duke.
     * @return a Dialog Box to show on the duke.MainWindow.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        String style = "-fx-background-color: rgba(203, 207, 215, 1);";
        db.dialog.setStyle(style);
        return db;
    }
}