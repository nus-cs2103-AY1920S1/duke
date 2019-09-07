package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Image duke = new Image(this.getClass().getResourceAsStream("../../images/avatar_placeholder.png"));

    /**
     * Dialog box for chat component.
     * @param l Label text
     * @param iv Avatar image
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /** Functions **/

    /**
     * Flip the dialog box horizontally.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Get user dialog box.
     */
    public static DialogBox getUserDialog(String userMessage, Image img) {
        var db = new DialogBox(new Label(userMessage), new ImageView(img));
        return db;
    }

    /**
     * Get duke dialog box.
     * @param dukeMessage Label text
     * @param img Image avatar
     */
    public static DialogBox getDukeDialog(String dukeMessage, Image img) {
        var db = new DialogBox(new Label(dukeMessage), new ImageView(img));
        db.flip();
        return db;
    }
}