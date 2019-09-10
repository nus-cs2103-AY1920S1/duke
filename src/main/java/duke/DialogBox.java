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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's 
 * face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double X_TRANSFORM_LABEL = 25;
    private static final double X_TRANSFORM_IMAGE = 18;

    @FXML
    private Label dialog;
    @FXML
    private Circle myCircle;

    private Font fontRegular = Font.loadFont(
            this.getClass().getResource("/font/Minecraftia-Regular.ttf").toExternalForm(), 10);

    private boolean isUser;

    /**
     * Constructor for the dialogBox.
     * 
     * @param text The text to be displayed - Which can be the user or Duke.
     * @param img The image to be displayed - Which can be the user or Duke.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        this.isUser = isUser;
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(this.isUser) {
            this.dialog.setTranslateX(-X_TRANSFORM_LABEL);
            this.myCircle.setTranslateX(-X_TRANSFORM_IMAGE);
            this.dialog.setAlignment(Pos.TOP_RIGHT);
            myCircle.setStroke(Color.GREENYELLOW);
            myCircle.setFill(new ImagePattern(img));
            myCircle.setEffect(new DropShadow(+14d, 0d, +2d, Color.SEAGREEN));
        } else {
            this.dialog.setTranslateX(X_TRANSFORM_LABEL);
            this.myCircle.setTranslateX(X_TRANSFORM_IMAGE);
            this.dialog.setAlignment(Pos.TOP_LEFT);
            myCircle.setStroke(Color.CRIMSON);
            myCircle.setFill(new ImagePattern(img));
            myCircle.setEffect(new DropShadow(+14d, 0d, +2d, Color.RED));
        }
        
        dialog.setFont(fontRegular);
        dialog.setTextFill(Color.BLACK);
        dialog.setText(text);
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
     * Returns a DialogBox containing user's text and iamge.
     * 
     * @param text User's Text
     * @param img User's Image
     * @return DialogBox containing user's text and image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Returns DialogBox containing Duke's text and image.
     * 
     * @param text Duke's Text
     * @param img Duke's Image
     * @return DialogBox containing Duke's text and image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

}