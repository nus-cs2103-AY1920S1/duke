package duke;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.text.Font;

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

    private DialogBox(String text, Image img, Background background, Color textColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setBackground(background);
            dialog.setTextFill(textColor);
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
     * Returns a <code>DialogBox</code> for the user.
     * @param text The user's input.
     * @param img The user's image.
     * @return DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        Color bgColor = Color.rgb(222,246,255, 0.9);
        CornerRadii cornerRadii = new CornerRadii(5);
        Background background = new Background(new BackgroundFill(bgColor, cornerRadii, Insets.EMPTY));
        Color textColor = Color.rgb(16,19,57);
        return new DialogBox(text, img, background, textColor);
    }

    /**
     * Returns a <code>DialogBox</code> for Duke.
     * @param text Duke's response.
     * @param img Duke's image.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        Color color = Color.rgb(255,238,250, 0.9);
        CornerRadii cornerRadii = new CornerRadii(5);
        Background background = new Background(new BackgroundFill(color, cornerRadii, Insets.EMPTY));
        Color textColor = Color.rgb(57,5,44);
        var db = new DialogBox(text, img, background, textColor);
        db.flip();
        return db;
    }
}