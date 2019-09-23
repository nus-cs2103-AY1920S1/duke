package com.gui;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
    @FXML
    private Circle myCircle;

    /**
     * Construct with the text and image for the dialog box.
     * @param text  text
     * @param img   accompanying image
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
        dialog.setStyle("-fx-background-color: #545F66; -fx-background-radius: 5");
        dialog.setWrapText(true);
        //displayPicture.setImage(img);
        myCircle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip(Boolean lastError) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        if (lastError) {
            dialog.setStyle("-fx-background-color: #F45866; -fx-background-radius: 5");
        } else {
            dialog.setStyle("-fx-background-color: #659B5E; -fx-background-radius: 5");
        }
    }

    /**
     * Create standard dialog box.
     * @param text  text for dialog box
     * @param img   image for dialog box
     * @return      dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Create flipped dialog box for duke responses.
     * @param text  text for dialog box
     * @param img   image for dialog box
     * @return      dialog box
     */
    public static DialogBox getDukeDialog(String text, Image img, Boolean lastError) {
        var db = new DialogBox(text, img);
        db.flip(lastError);
        return db;
    }
}