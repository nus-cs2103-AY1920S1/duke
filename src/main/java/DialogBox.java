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
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Collections;


public class DialogBox extends HBox {
    @FXML
    private Label header;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        header.setText(name);
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
     * Gets the user dialog that has been input by the user and will be displayed.
     * @param text the user-command that is given.
     * @param img takes the image of the user.
     * @return Dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "User");
        db.dialog.setFont(new Font("Consolas", 10));
        db.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return db;
    }

    /**
     * Gets the Duke dialog that is a reply to the user input.
     * @param text the Duke execution that is given.
     * @param img takes the image Duke.
     * @return Dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Jarvis");
        db.flip();
        db.dialog.setFont(new Font("Consolas", 10));
        db.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return db;
    }
}
