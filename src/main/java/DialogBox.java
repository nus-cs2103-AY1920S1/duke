import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A simple Dialog Box which takes returns a picture and the text involved.
 */
public class DialogBox extends HBox {

    /**
     * The text to be shown to the user.
     */
    private Label text;

    /**
     * The display picture to be shown to the user.
     */
    private ImageView displayPicture;

    /**
     * Constructs a new Dialog Box which shows the text and image involved.
     * @param label the label also known as the text;
     * @param image the image to be shown to the user;
     */
    private DialogBox(Label label, ImageView image) {
        text = label;
        displayPicture = image;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);
        ;
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, text);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_RIGHT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox based on user output and photo.
     * @param label the text involved.
     * @param image the photo involved.
     * @return a new Dialog Box based on user output and photo.
     */
    public static DialogBox getUserDialog(Label label, ImageView image) {
        return new DialogBox(label, image);
    }

    /**
     * Creates a DialogBox based on duke output and photo.
     * @param label the text involved.
     * @param image the photo involved.
     * @return a new Dialog Box based on duke output and photo.
     */
    public static DialogBox getDukeDialog(Label label, ImageView image) {
        var dialogBox = new DialogBox(label, image);
        dialogBox.flip();
        return dialogBox;
    }
}