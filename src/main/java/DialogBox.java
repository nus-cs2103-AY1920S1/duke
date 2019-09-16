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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML. This control represents a dialog
 * box consisting of an ImageView to represent the speaker's face and a label
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
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox for the User.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userBox = new DialogBox(text, img);
        userBox.setMinHeight(Region.USE_PREF_SIZE);
        BackgroundFill backGroundFill = new BackgroundFill(Color.rgb(255, 234, 167), new CornerRadii(10), Insets.EMPTY);
        Background backGround = new Background(backGroundFill);
        userBox.setBackground(backGround);
        return userBox;
    }

    /**
     * Returns a DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dukeBox = new DialogBox(text, img);
        dukeBox.flip();
        dukeBox.setMinHeight(Region.USE_PREF_SIZE);
        BackgroundFill backGroundFill = new BackgroundFill(Color.rgb(250, 177, 160), new CornerRadii(10), Insets.EMPTY);
        Background backGround = new Background(backGroundFill);
        dukeBox.setBackground(backGround);
        return dukeBox;
    }

}