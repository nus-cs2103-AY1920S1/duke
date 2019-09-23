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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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

    private static final Color DUKE_COLOR = Color.POWDERBLUE;
    private static final Color USER_COLOR = Color.LIGHTGREEN;
    private static final CornerRadii DIALOG_CORNERS = new CornerRadii(10,10,10,10,false);

    private DialogBox(String text, Image img, Color fill) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        dialog.setText(text);
        dialog.setPadding(new Insets(5));
        dialog.setBackground(new Background(new BackgroundFill(fill, DIALOG_CORNERS, Insets.EMPTY)));

        Circle profile = new Circle(49.5, 49.5, 49.5);
        profile.setFill(new ImagePattern(img));
        displayPicture.setImage(img);
        displayPicture.setClip(profile);
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
     * Returns a horizontal box containing user input and image.
     *
     * @param text User input.
     * @param img User image.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) throws IOException {
        return new DialogBox(text, img, USER_COLOR);
    }

    /**
     * Returns a horizontal box containing chatbot input and image.
     *
     * @param text Chatbot input.
     * @param img Chatbot image.
     * @return Chatbot dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) throws IOException {
        var db = new DialogBox(text, img, DUKE_COLOR);
        db.flip();
        return db;
    }
}