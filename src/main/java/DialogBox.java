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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.geometry.Insets;
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

    private static final Color DIALOGBOX_BACKGROUND_RED = Color.rgb(200,0,0);
    private static final Color DIALOGBOX_BACKGROUND_BLUE = Color.rgb(0,0,200);
    private static final Color DIALOGBOX_BACKGROUND_GREEN = Color.rgb(0,150,0);

    private static final CornerRadii DIALOGBOX_BACKGROUND_RADII = new CornerRadii(10);

    private static final Insets DIALOGBOX_BACKGROUND_INSET = new Insets(0,0,0,0);

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

    // sets the background to a blue rounded corner box
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox user = new DialogBox(text, img);
        user.setBackground(
                new Background(
                        new BackgroundFill(
                                DIALOGBOX_BACKGROUND_BLUE,
                                DIALOGBOX_BACKGROUND_RADII,
                                DIALOGBOX_BACKGROUND_INSET)));
        return user;
    }

    //sets the background to green or red depending on error status
    public static DialogBox getDukeDialog(String text, Image img, int errorStatus) {
        DialogBox duke = new DialogBox(text, img);

        Color[] colours =  new Color[]{DIALOGBOX_BACKGROUND_GREEN,
                DIALOGBOX_BACKGROUND_RED};
        Color correctColor = colours[errorStatus];
        duke.setBackground(
                new Background(
                        new BackgroundFill(
                                correctColor,
                                DIALOGBOX_BACKGROUND_RADII,
                                DIALOGBOX_BACKGROUND_INSET)));


        duke.flip();

        return duke;
    }





}