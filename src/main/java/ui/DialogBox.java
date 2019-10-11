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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

import static javafx.scene.paint.Color.WHITESMOKE;


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
        dialog.setWrapText(true);
        dialog.setTextAlignment(TextAlignment.JUSTIFY);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
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
     * Get a specially customised user DialogBox.
     *
     * @param text is the input from the user
     * @param img is the predefined icon for the user
     * @return DialogBox
     */

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setTextColor(Color.BLACK);
        BackgroundFill myTemp = new BackgroundFill(Color.GOLDENROD,null,null);
        Background b = new Background(myTemp);
        db.setBackground(b);
        return db;
    }

    /**
     * Get a specially customised duke DialogBox.
     *
     * @param text is the response from Duke
     * @param img is the predefined icon for the Duke
     * @return DialogBox
     */

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setTextColor(WHITESMOKE);
        BackgroundFill myTemp = new BackgroundFill(Color.BLACK,null,null);
        Background b = new Background(myTemp);
        db.setBackground(b);
        db.flip();
        return db;
    }

    public void setTextColor(Paint x) {
        dialog.setTextFill(x);
    }



}