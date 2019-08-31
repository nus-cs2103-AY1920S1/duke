package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's profile image
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {


    private static final Background USER_BACKGROUND = new Background(new BackgroundFill(
            Color.rgb(152, 251, 152), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background DUKE_BACKGROUND = new Background(new BackgroundFill(
            Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a dialog box with the ImageView on the right and the text on the left.
     *
     * @param text  The view object that contains the message string.
     * @param image The image representing the user profile.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setWrapText(true);
        dialog.setPadding(new Insets(0, 8, 0, 8));
        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Returns a new dialog box with the specified message and image for the user.
     *
     * @param label     The view object that contains the string of text sent by the user.
     * @param imageView The image representing the user profile.
     * @return A Dialog box with the user's imageView on the right and text on the left.
     */
    public static DialogBox getUserDialog(String label, Image imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.setBackground(USER_BACKGROUND);
        return dialogBox;
    }

    /**
     * Returns a new dialog box with the specified message and image for Duke.
     *
     * @param label     The view object that contains the string of text sent by Duke.
     * @param imageView The image representing Duke's profile.
     * @return A Dialog box with Duke's imageView on the left and text on the right.
     */
    public static DialogBox getDukeDialog(String label, Image imageView) {
        var dialogBox = new DialogBox(label, imageView);
        dialogBox.setBackground(DUKE_BACKGROUND);
        dialogBox.flip();
        return dialogBox;
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

}