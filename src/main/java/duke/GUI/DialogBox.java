package duke.gui;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
     * Returns a DialogBox object containing user inputs to MainWindow.
     * @param text String of user inputs.
     * @param img Image of the user.
     * @return a DialogBox object containing user inputs
     */
    static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #C2DFFF;"
                + "-fx-background-radius: 15;");
        return db;
    }

    /**
     * Returns a DialogBox object containing duke's responses to MainWindow.
     * @param text String of duke's response.
     * @param img Image of duke
     * @return a DialogBox object containing duke's responses
     */
    static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #E5E4E2;"
                + "-fx-background-radius: 15;");
        return db;
    }
}