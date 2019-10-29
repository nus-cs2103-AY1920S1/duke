package duke.ui;

import java.io.IOException;
import java.net.URL;
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

public class DialogBox extends HBox {

    private static final String URL_DIALOG_BOX_VIEW  = "/views/dialog_box.fxml";

    @FXML private Text dialog;
    @FXML private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            URL dialogBoxViewUrl = DialogBox.class.getResource(DialogBox.URL_DIALOG_BOX_VIEW);
            FXMLLoader fxmlLoader = new FXMLLoader(dialogBoxViewUrl);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(image);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Get the dialog box for user.
     * This method always return a {@link DialogBox} object
     * corresponding to the dialog box for user.
     *
     * @param text the message written by user
     * @param img the image of user
     * @return the dialog box of user saying the message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Get the dialog box for Duke.
     * This method always return a {@link DialogBox} object
     * corresponding to the dialog box for Duke.
     *
     * @param text the message written by Duke
     * @param img the image of Duke
     * @return the dialog box of Duke saying the message
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }

}