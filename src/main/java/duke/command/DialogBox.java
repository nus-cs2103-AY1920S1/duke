package duke.command;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

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

    /**
     * Construct the dialog box.
     * @param text text to be displayed.
     * @param img image to be displayed.
     */
    private DialogBox(String text, Image img) {
        try {
            int lineCount = text.split("\n").length;
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setPadding(new Insets(10, 10, 10, 10));
            dialog.setBorder(new Border(new BorderStroke(Color.DIMGREY,
                    BorderStrokeStyle.DASHED, new CornerRadii(10), BorderWidths.DEFAULT)));
            displayPicture.setClip(new Circle(30, 30, 27));
            dialog.setMinHeight((double) (lineCount - 2) * 18.8 + 2 * 30);

        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Label dialog = (Label) db.getChildren().get(0);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.PEACHPUFF, new CornerRadii(10), Insets.EMPTY)));
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        Label dialog = (Label) db.getChildren().get(1);
        dialog.setBackground(
                new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(10), Insets.EMPTY)));
        return db;
    }
}