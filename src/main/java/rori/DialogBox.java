import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

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
    private static final double X_TRANSFORM_LABEL = 40;
    private static final double X_TRANSFORM_IMAGE = 20;

    private static final Color USER_COLOR_CIRCLE = Color.valueOf("#96c983");
    private static final Color CIRCLE_COLOR_ERROR = Color.CRIMSON;
    private static final Color CIRCLE_COLOR_NORMAL = Color.DODGERBLUE;
    private static final String LABEL_COLOR_ERROR = "#ffdee2";
    private static final String LABEL_COLOR_NORMAL = "#deedff";

    public static double transformSize = 0;

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
     * @param text The text to be displayed - Which can be the user or Rori.
     * @param img The image to be displayed - Which can be the user or Rori.
     */
    private DialogBox(String text, Image img, boolean isUser, boolean hasError) {
        this.isUser = isUser;
    
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.isUser) {
            dialog.setTranslateX(-X_TRANSFORM_LABEL);
            myCircle.setTranslateX(-X_TRANSFORM_IMAGE);
            dialog.setAlignment(Pos.TOP_RIGHT);
            myCircle.setFill(new ImagePattern(img));
            myCircle.setStroke(USER_COLOR_CIRCLE);
            myCircle.setEffect(new DropShadow(+14d, 0d, +2d, USER_COLOR_CIRCLE));
            dialog.setEffect(new DropShadow(+4d, 0d, +2d, USER_COLOR_CIRCLE));
        } else {
            dialog.setTranslateX(X_TRANSFORM_LABEL);
            myCircle.setTranslateX(X_TRANSFORM_IMAGE);
            dialog.setAlignment(Pos.TOP_LEFT);
            myCircle.setFill(new ImagePattern(img));
            if(hasError) {
                myCircle.setStroke(CIRCLE_COLOR_ERROR);
                myCircle.setEffect(new DropShadow(+4d, 0d, +2d, CIRCLE_COLOR_ERROR));
                dialog.setStyle(dialog.getStyle() + "-fx-background-color:" + LABEL_COLOR_ERROR);
                dialog.setEffect(new DropShadow(+4d, 0d, +2d, CIRCLE_COLOR_ERROR));
            } else {
                myCircle.setStroke(CIRCLE_COLOR_NORMAL);
                myCircle.setEffect(new DropShadow(+4d, 0d, +2d, CIRCLE_COLOR_NORMAL));
                dialog.setStyle(dialog.getStyle() + "-fx-background-color:" + LABEL_COLOR_NORMAL);
                dialog.setEffect(new DropShadow(+4d, 0d, +2d, CIRCLE_COLOR_NORMAL));
            }
        }

        String lines[] = text.split("\\r?\\n");
        
        for(String line : lines) {
            if(line.length() >= 50) {
                dialog.setPrefWidth(668.0);
                System.out.println("yeet");
                break;
            } 
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
        return new DialogBox(text, img, true, false);
    }

    /**
     * Returns DialogBox containing Rori's text and image, and whether the user input has an exception.
     * 
     * @param text Rori's Text
     * @param img Rori's Image
     * @param hasEeror Rori's 
     * @return DialogBox containing Rori's text and image
     */
    public static DialogBox getRoriDialog(String text, Image img, boolean hasError) {
        DialogBox db = new DialogBox(text, img, false, hasError);
        db.flip();
        return db;
    }

}