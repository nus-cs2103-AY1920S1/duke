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

    private static final Image USER_IMAGE = new Image(
        DialogBox.class.getResourceAsStream("/images/User.png"));
    private static final Image NORMAL_RORI_IMAGE = new Image(
        DialogBox.class.getResourceAsStream("/images/NormalRori.png"));
    private static final Image ERROR_RORI_IMAGE = new Image(
        DialogBox.class.getResourceAsStream("/images/AngryRori.png"));

    private static final String USER_COLOR_CIRCLE = "#96c983";
    private static final String USER_COLOR_LABEL = "#e1ffd6";
    private static final String NORMAL_COLOR_CIRCLE = "#1e90ff";
    private static final String NORMAL_COLOR_LABEL = "#deedff";
    private static final String ERROR_COLOR_CIRCLE = "#DC143C";
    private static final String ERROR_COLOR_LABEL = "#ffdee2";

    public static double transformSize = 0;

    @FXML
    private Label dialog;
    @FXML
    private Circle myCircle;

    private Font fontRegular = Font.loadFont(
            this.getClass().getResource("/font/Minecraftia-Regular.ttf").toExternalForm(), 10);

    /**
     * Constructor for the dialog box.
     * 
     * @param text The user/rori messages that is output
     * @param img The image of user or rori
     * @param translateImageX The image that is to be translated for better positioning
     * @param translateLabelX The label that is translated for better positioning
     * @param circleColor The outline circle of the image color
     * @param boxColor The text box color
     * @param position The Alignment of the box
     */       
    private DialogBox(String text, 
            Image img, 
            double translateImageX, 
            double translateLabelX,
            String circleColor, 
            String boxColor,
            Pos position) {
    
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        editDialogBox(text, img, translateImageX, translateLabelX, circleColor, boxColor, position);
    }

    /**
     * Edits the dialog box to make it looks nicer and output the text.
     * 
     * @param text The user/rori messages that is output
     * @param img The image of user or rori
     * @param translateImageX The image that is to be translated for better positioning
     * @param translateLabelX The label that is translated for better positioning
     * @param circleColor The outline circle of the image color
     * @param boxColor The text box color
     * @param position The Alignment of the box
     */   
    private void editDialogBox(String text,
            Image img, 
            double translateImageX, 
            double translateLabelX,
            String circleColor, 
            String boxColor,
            Pos position) {

        dialog.setTranslateX(translateLabelX);
        myCircle.setTranslateX(translateImageX);
        dialog.setAlignment(position);
        myCircle.setFill(new ImagePattern(img));
        myCircle.setStroke(Color.valueOf(circleColor));
        myCircle.setEffect(new DropShadow(+14d, 0d, +2d, Color.valueOf(circleColor)));
        dialog.setStyle(dialog.getStyle() + "-fx-background-color:" + boxColor);
        dialog.setEffect(new DropShadow(+4d, 0d, +2d, Color.valueOf(circleColor)));

        String[] lines = text.split("\\r?\\n");   
        for (String line : lines) {
            if (line.length() > 100) {
                dialog.setPrefWidth(668.0);
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
     * Returns a constructor for the user's DialogBox.
     * @param text The user's input
     * @return a constructor for the user's DialogBox.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, 
                USER_IMAGE, 
                -X_TRANSFORM_IMAGE, 
                -X_TRANSFORM_LABEL, 
                USER_COLOR_CIRCLE, 
                USER_COLOR_LABEL, 
                Pos.TOP_RIGHT);
    }


    /**
     * Returns a constructor for Rori's normal DialogBox. 
     * @param text Rori's output
     * @return a constructor for the Rori's normal DialogBox.
     */
    public static DialogBox getRoriNormalDialog(String text) {
        DialogBox db = new DialogBox(text, 
                NORMAL_RORI_IMAGE, 
                X_TRANSFORM_IMAGE, 
                X_TRANSFORM_LABEL, 
                NORMAL_COLOR_CIRCLE, 
                NORMAL_COLOR_LABEL, 
                Pos.TOP_LEFT);
        db.flip();
        return db;
    }

    /**
     * Returns a constructor for the Rori's Error DialogBox.
     * @param text Rori's output
     * @return a constructor for the Rori's Error DialogBox.
     */
    public static DialogBox getRoriErrorDialog(String text) {
        DialogBox db = new DialogBox(text, 
                ERROR_RORI_IMAGE, 
                X_TRANSFORM_IMAGE, 
                X_TRANSFORM_LABEL, 
                ERROR_COLOR_CIRCLE, 
                ERROR_COLOR_LABEL, 
                Pos.TOP_LEFT);
        db.flip();
        return db;
    }
    

}