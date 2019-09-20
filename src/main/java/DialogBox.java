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
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

//@@author {chanjunren}-reused
//Adapted from JavaFX tutorial
//@@author
public class DialogBox extends HBox {
    private final Circle CLIP = new Circle(50,50,50);
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String  text, Image img) {
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
        displayPicture.setClip(CLIP);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String input, Image img) {
        return new DialogBox(input, img);
    }

    public static DialogBox getDukeDialog(String response, Image img) {
        var db = new DialogBox(response, img);
        db.flip();
        return db;
    }
}
