package duke.view;

import duke.main.Duke;
import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static Image USER_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/user.png"));

    private static Image DUKE_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/duke.png"));

    private static Duke DUKE = new Duke();
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setId("dialogText");
    }

    private static DialogBox UserDialogBox(String inputMessage) {
        return new DialogBox(inputMessage, USER_IMAGE);
    }

    private static DialogBox DukeDialogBox(String outputMessage) {
        return new DialogBox(outputMessage, DUKE_IMAGE).flip();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private DialogBox flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        this.flipDialogTextAlignment();
        return this;
    }

    public void flipDialogTextAlignment() {
        if(dialog.getTextAlignment() == TextAlignment.LEFT) {
            dialog.setTextAlignment(TextAlignment.RIGHT);
        } else {
            dialog.setTextAlignment(TextAlignment.LEFT);
        }
    }

    public static DialogBox getUserDialog(String inputMessage) {
        return UserDialogBox(inputMessage);
    }

    public static DialogBox getDukeDialog(String inputMessage) {
        String outputMessage = DUKE.runWithUserInput(inputMessage);
        return DukeDialogBox(outputMessage);
    }

}
