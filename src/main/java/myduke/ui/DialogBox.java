package myduke.ui;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A JavaFx Control representing a dialog box consisting of
 * an account image and text from a user.
 */
public class DialogBox extends HBox {

    //Class Variables
    @FXML
    private VBox dialogViewBox;
    @FXML
    private Label userName;
    @FXML
    private Label message;
    @FXML
    private ImageView displayPicture;

    private static final Background BG_GREY = new Background(
            new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));

    /**
     * Constructor for Dialog Box.
     *
     * @param user user name.
     * @param text user message.
     * @param img  user image.
     */
    private DialogBox(String user, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userName.setText(user);
        message.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 45));
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
     * Gets the User dialog.
     *
     * @param text Text for user.
     * @param img  Image of user.
     *
     * @return Instance of a dialog box for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("You", text, img);
    }

    /**
     * Gets the Duke dialog.
     *
     * @param text Text for Duke.
     * @param img  Image of Duke.
     *
     * @return Instance of a dialog box for Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialog = new DialogBox("Duke", text, img);
        dialog.flip();
        dialog.setBackground(BG_GREY);
        return dialog;
    }
}

