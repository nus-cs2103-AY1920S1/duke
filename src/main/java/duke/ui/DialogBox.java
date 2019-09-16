package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * A class representing a dialog box.
 */
public class DialogBox extends HBox {
    private static final double CIRCLE_RADIUS = 30;
    @FXML
    private Label dialog;
    @FXML
    private ImageView userPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        cropImage(image);
        addShadow();
    }

    private void addShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(138, 138, 138, 0.8));
        dropShadow.setWidth(2);
        dropShadow.setOffsetY(2);
        dropShadow.setOffsetX(2);
        dialog.setEffect(dropShadow);
    }

    private void cropImage(Image image) {
        double centerX = image.getWidth() / 2;
        double centerY = image.getHeight() / 2;
        double radius = Math.min(centerX, centerY) * 0.5;
        Rectangle2D viewPort = new Rectangle2D(centerX - radius, centerY - radius, radius * 2, radius * 2);
        userPicture.setImage(image);
        userPicture.setViewport(viewPort);
        userPicture.setFitWidth(CIRCLE_RADIUS * 2);
        Circle circle = new Circle(CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_RADIUS);
        userPicture.setClip(circle);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a user dialog box.
     * @param text text in the dialog box.
     * @param image user image for the text.
     * @return a user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        dialogBox.setAlignment(Pos.TOP_RIGHT);
        return dialogBox;
    }

    /**
     * Returns a duke dialog box.
     * @param text text in the dialog box.
     * @param image duke image for the text.
     * @return a duke dialog box.
    */
    public static DialogBox getDukeDialog(String text, Image image) {
        return new DialogBox(text, image);
    }
}