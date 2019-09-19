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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeBox(text);
        displayPicture.setImage(img);
        displayPicture.getImage();
    }

    private void initializeBox(String text) {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(5);
        dialog.setText(text);
        Circle clip = new Circle(50, 50, 37.5);
        displayPicture.setClip(clip);
    }

    private void setBackground(boolean isDuke) {
        Color c = isDuke
                ? Color.LIMEGREEN
                : Color.DEEPSKYBLUE;
        CornerRadii r = new CornerRadii(5);
        Insets in = isDuke
                ? new Insets(5, 40, 20, 20)
                : new Insets(5, 20, 20, 40);
        BackgroundFill bf = new BackgroundFill(c, r, in);
        Background b = new Background(bf);
        this.setBackground(b);
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
     * Gets text input from the user.
     * @param text The user entered text.
     * @param img The image corresponding to the user.
     * @return DialogBox based on the input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(false);
        return db;
    }

    /**
     * Gets text response from Duke.
     * @param text The response from Duke.
     * @param img Duke's image
     * @return Dialog Box corresponding to Duke's output.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(true);
        db.flip();
        return db;
    }
}