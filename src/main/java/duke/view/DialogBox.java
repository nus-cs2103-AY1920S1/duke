package duke.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's avatar
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Text dialog;
    @FXML
    private StackPane dialogWrapper;
    @FXML
    private ImageView displayPicture;

    /**
     * Displays a new DialogBox by setting the text and image.
     * @param text The text that forms the speaker's dialogue.
     * @param img The speaker's avatar.
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
        // set width of text message
        double currWrappingWidth = dialog.getLayoutBounds().getWidth();
        double maxWrappingWidth = this.getPrefWidth() - displayPicture.getFitWidth() * 2;
        dialog.setWrappingWidth(Math.min(currWrappingWidth, maxWrappingWidth));

        dialogWrapper.setAlignment(Pos.TOP_RIGHT);
        // set background color of text message
        dialogWrapper.setBackground(new Background(
                new BackgroundFill(
                    Paint.valueOf(Color.GAINSBORO.toString()),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
        ));

        displayPicture.setImage(img);
        // clip display picture of speakers into a circle
        displayPicture.setClip(new Circle(
                displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2,
                displayPicture.getFitWidth() / 2
        ));
    }

    /**
     * Returns a new DialogBox for the user's input.
     * @param text The Label representing the user's text input.
     * @param img The ImageView representing the user's avatar.
     * @return A DialogBox containing the user's text input and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a new DialogBox for Duke's response to user input.
     * @param text The Label representing the text in Duke's response.
     * @param img The ImageView representing Duke's avatar.
     * @return A DialogBox aligned to the left, containing Duke's text response and avatar.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Flips a dialog box such that the ImageView is on the left and the text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialogWrapper.setAlignment(Pos.TOP_LEFT);
    }
}
