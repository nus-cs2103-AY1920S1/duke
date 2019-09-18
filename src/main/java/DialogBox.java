import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Collections;


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
        displayPicture.setClip(new Circle(50, 50, 50));//img set to circle
        dialog.setPadding(new Insets(10, 10, 10, 10));

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
        DialogBox user = new DialogBox(text, img);

        // sets the background to a blue rounded corner box
        user.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(0,0, 100),
                                new CornerRadii(10),
                                new Insets(5,37.5,5,37.5))));
        return user;
    }

    //sets the background to green or red depending on error status
    public static DialogBox getDukeDialog(String text, Image img, int errorStatus) {
        DialogBox duke = new DialogBox(text, img);

        Color[] colours =  new Color[]{Color.rgb(0,100, 0),
                Color.rgb(100,0,0)};
        Color correctColor = colours[errorStatus];
        duke.setBackground(
                new Background(
                        new BackgroundFill(
                                correctColor,
                                new CornerRadii(10),
                                new Insets(5,37.5,5,37.5))));


        duke.flip();

        return duke;
    }





}