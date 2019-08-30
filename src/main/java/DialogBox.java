import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    public DialogBox(Label label, ImageView image) {
        text = label;
        displayPicture = image;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, text);
    }
}