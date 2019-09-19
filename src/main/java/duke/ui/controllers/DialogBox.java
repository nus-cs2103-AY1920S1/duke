package duke.ui.controllers;

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

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
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
     * Returns a dialog used to display the message entered by the user.
     * @param text the message entered by the user.
     * @param img the user's picture.
     * @return a user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dbx = new DialogBox(text, img);
        dbx.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        dbx.dialog.setPadding(new Insets(8, 0, 0, 10));

        return dbx;
    }

    /**
     * Returns a dialog used to display the response by Duke to the user's input.
     * @param text the response of duke.
     * @param img Duke's picture.
     * @return a Duke dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dbx = new DialogBox(text, img);
        dbx.flip();
        dbx.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        dbx.dialog.setPadding(new Insets(0, 0, 0, 10));
        return dbx;
    }
}