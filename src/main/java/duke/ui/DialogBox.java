package duke.ui;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * DialogBox class used to create the input from user and output from Duke.
 */
public class DialogBox extends HBox {

    /** Label to display text. **/
    @FXML
    private Label dialog;
    /** View to display the profile picture. **/
    @FXML
    private ImageView displayPicture;
    /** Container to display the conversation. **/
    @FXML
    private VBox dialogContainer;

    /**
     * Initializes a DialogBox.
     * @param text text that the dialog box contains.
     * @param img profile picture attached to the dialog box.
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
        displayPicture.setImage(img);
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
     * Gets the user dialog box and formats it accordingly.
     * @param text text that the user has written.
     * @param img profile picture of the user.
     * @return DialogBox containing the user text and profile picture.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var userDB = new DialogBox(text, img);
        BackgroundFill bgFill = new BackgroundFill(Color.web("#fa983a"),
                new CornerRadii(20.0, 0.0, 0.0, 20.0, false),
                new Insets(0));
        userDB.setBackground(new Background(bgFill));
        VBox.setMargin(userDB, new Insets(5, 0, 5, 10));

        return userDB;
    }

    /**
     * Gets the duke dialog box and formats it accordingly.
     * @param text response of duke.
     * @param img profile picture of duke.
     * @return DialogBox containing duke's response and profile picture.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dukeDB = new DialogBox(text, img);
        BackgroundFill bgFill = new BackgroundFill(Color.web("#7f8c8d"),
                new CornerRadii(0.0, 20.0, 20.0, 0.0, false),
                new Insets(0));
        dukeDB.setBackground(new Background(bgFill));
        VBox.setMargin(dukeDB, new Insets(5, 10, 5, 0));
        HBox.setHgrow(dukeDB, Priority.ALWAYS);
        dukeDB.flip();

        return dukeDB;
    }
}