package dukegui.controller;

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
import javafx.scene.layout.Region;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogueBox extends HBox {

    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayPicture;

    private DialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogue.setText(text);
        displayPicture.setImage(img);
        int lines = this.countLines(text);
        if (lines < 5) {
            this.setPrefHeight(Region.USE_COMPUTED_SIZE);
        } else {
            this.setPrefHeight(lines * 20);
        }
    }

    private int countLines(String text) {
        return text.split("\n", -1).length;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns the dialogueBox containing user commands.
     *
     * @param text User command.
     * @param img User profile picture.
     * @return DialogueBox containing user commands.
     */
    public static DialogueBox getUserDialogue(String text, Image img) {
        return new DialogueBox(text, img);
    }

    /**
     * Returns the dialogueBox containing Duke's response.
     *
     * @param text Duke's response.
     * @param img Profile picture of Duke.
     * @return DialogueBox containing Duke's response.
     */
    public static DialogueBox getDukeDialogue(String text, Image img) {
        DialogueBox db = new DialogueBox(text, img);
        db.flip();
        return db;
    }
}

