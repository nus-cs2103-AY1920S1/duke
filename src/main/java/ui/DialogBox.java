package ui;

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

//@@author jaesimin-reused
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart1.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart2.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart3.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
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
     * Returns the user dialog box.
     * @param text Text to be displayed.
     * @param img Image to be represented.
     * @return Dialog box object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns the duke dialog box.
     * @param text Text to be displayed.
     * @param img Image to be represented.
     * @return Dialog box object.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

//@@author