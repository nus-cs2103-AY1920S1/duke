import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The element that displays tbe user's input and Duke's response in the window.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox object.
     *
     * @param l The label containing text to be displayed.
     * @param iv The profile picture of the user or Duke.
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

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Performs the same action as getUserDialog(),
     * but flips it so that the DialogBox is on the left side of the window.
     *
     * @param l The Label containing Duke's response.
     * @param iv The profile picture of Duke.
     * @return a Dialog Box that is on the left side of the window.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}