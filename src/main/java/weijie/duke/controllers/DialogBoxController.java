package weijie.duke.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


class DialogBoxController extends HBox {
    @FXML
    private Text text;
    @FXML
    private ImageView displayPicture;

    private DialogBoxController(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the text box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    static DialogBoxController getUserDialog(String text, Image img) {
        return new DialogBoxController(text, img);
    }

    static DialogBoxController getDukeDialog(String text, Image img) {
        var db = new DialogBoxController(text, img);
        db.flip();
        return db;
    }
}
