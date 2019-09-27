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
import javafx.scene.transform.Translate;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    
    @FXML
    private Label dialog;
    @FXML
    private HBox mainView;
    @FXML
    private Circle userImage;


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
        //displayPicture.setImage(img);
        userImage.setFill(new ImagePattern(img));
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
     * Creates a dialogbox design used for Duke user's.
     * @param text The text to be displayed in the dialogbox. 
     * @param img The user's image to be shown in the dialogbox.
     * @return The dialog box as designed for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        //Translate translate = new Translate();
        //translate.setX(10);
        db.getStyleClass().add("hboxUser");
        db.setTranslateX(10);
        return db;
    }

    /**
     * Creates a dialogbox design used for Duke.
     * @param text The text to be displayed in the dialogbox. 
     * @param img Duke's image to be shown in the dialogbox.
     * @return The dialog box as designed for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("hboxDuke");
        db.flip();
        db.setTranslateX(-10);
        return db;
    }
}