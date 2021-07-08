

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogLineController extends HBox {
    @FXML
    public Label dialog = new Label();
    @FXML
    public ImageView displayPicture = new ImageView();
    public DialogLineController(){
    }
    private DialogLineController(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatScreenController.class.getResource("DialogLine.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setMinHeight(0);
        dialog.setVisible(true);
        displayPicture.setVisible(true);
        dialog.setText(text);
        displayPicture.setImage(img);
        //System.out.println(dialog.getText() + displayPicture.getImage());
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

    public static DialogLineController getDukeDialog(String text, Image img) {
        var dl = new DialogLineController(text, img);
        dl.dialog.setFont(new Font("Consolas", 10));
        dl.setMinHeight(dl.dialog.getMinHeight());
        dl.displayPicture.setFitHeight(dl.getHeight());
        return dl;
    }

    public static DialogLineController getUserDialog(String text, Image img) {
        var dl = new DialogLineController(text, img);
        dl.setMinHeight(dl.dialog.getMinHeight());
        dl.displayPicture.setFitHeight(dl.getHeight());
        dl.flip();
        return dl;
    }
}