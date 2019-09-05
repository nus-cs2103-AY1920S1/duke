package duke.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;

/**
 * To get the dialog box of user and duke.
 *
 * @author TeoShyanJie
 *
 */
public class DialogBox extends HBox {
    /** Text Label. */
    private Label text;

    /** User Image or Duke Image. */
    private ImageView displayPicture;

    /**
     * Construct the Dialog Box of Text Label and Image.
     * @param l Text Label.
     * @param iv Image of Duke/User.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        final Circle clip = new Circle(50, 50, 50);
        iv.setClip(clip);
        displayPicture = iv;
        text.setWrapText(true);
        text.setPadding(new Insets(10,20,0,20));
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

    /**
     * To get User Dialog.
     * @param l Text Label.
     * @param iv Image of User.
     * @return The Dialog box of user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * To get Duke Dialog.
     * @param l Text Label.
     * @param iv Image of Duke.
     * @return The Dialog box of Duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}