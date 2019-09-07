import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private TextArea text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for generating a DialogBox. Takes in a string input to display as text and
     * a image as a display picture.
     *
     * @param txt String text input
     * @param img A display image
     */
    private DialogBox(String txt, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(txt);
        displayPicture.setImage(img);
    }

    /**
     * Method for flipping the properties of a DialogBox for display purposes.
     */
    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        getChildren().setAll(temp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Static method for creating a DialogBox for user input.
     *
     * @param txt String text input
     * @param img A display image
     */
    public static DialogBox getUserDialog(String txt, Image img) {
        return new DialogBox(txt, img);
    }

    /**
     * Static method for creating a DialogBox for Duke's messages.
     *
     * @param txt String text input
     * @param img A display image
     */
    public static DialogBox getDukeDialog(String txt, Image img) {
        var db = new DialogBox(txt, img);
        db.flip();
        return db;
    }
}
