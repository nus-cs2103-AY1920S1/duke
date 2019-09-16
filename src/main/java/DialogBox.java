import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private Circle imageDisplayCircle;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set Dialog Box Style
        this.setStyle("-fx-background-color: rgba(50, 50, 50, 1.0); -fx-background-radius: 10;");

        // Set Label style
        dynamicallySetHeight(text, dialog);
        dialog.setTextFill(Color.WHITE);
        dialog.setStyle("-fx-background-color: rgba(75, 75, 75, 1.0); -fx-background-radius: 10;");
        
        dialog.setText(text);
        setImageInsideCircle(img);
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
     * Returns a new DialogBox, containing the user image and input text.
     * 
     * @return A new DialogBox, containing the user image and input text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a new DialogBox, containing Duke's image and reply text.
     * 
     * @return A new DialogBox, containing Duke's image and reply text.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Sets the height of the DialogBox to be sufficiently large that the text will not overrun.
     */
    private void dynamicallySetHeight(String text, Label dialog) {
        final double minHeight = 30;
        final double incrementHeight = 16;

        double finalHeight = minHeight;

        int numberOfLines = text.split("\r\n|\r|\n").length;

        for (int i = 1; i < numberOfLines; i++) {
            finalHeight += incrementHeight;
        }

        dialog.setMinHeight(finalHeight);
    }

    /**
     * Places the image within the imageDisplayCircle.
     * 
     * @param image The image to be placed within the imageDisplayCircle.
     */
    private void setImageInsideCircle(Image img) {
        double shadowRadius = 10;
        double shadowOffset = 0;

        imageDisplayCircle.setStroke(Color.PURPLE);
        imageDisplayCircle.setFill(new ImagePattern(img));
        imageDisplayCircle.setEffect(new DropShadow(shadowRadius, shadowOffset, shadowOffset, Color.PLUM));
    }
}
