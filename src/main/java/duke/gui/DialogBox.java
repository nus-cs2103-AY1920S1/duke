package duke.gui;

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
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Represents a Horizontal box with a label and Image.
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
        displayPicture.setClip(new Circle(
                displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2,
                50.0));
    }

    /**
     * Flips the dialog box on the vertical axis.
     *
     * @return the current DialogBox instance.
     */
    private DialogBox flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    /**
     * Creates a new right-justified dialog box.
     *
     * @param text the text to place in the label.
     * @param img the image to place in the box.
     * @return the new dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new left-justified dialog box.
     *
     * @param text the text to place in the label.
     * @param img the image to place in the box.
     * @return the new dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img).flip();
    }
}
