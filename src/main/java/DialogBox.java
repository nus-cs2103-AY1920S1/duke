import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Represents the dialog box in the GUI that display the text of user and Duke.
     *
     * @param l Label object containing text to be displayed in dialog box.
     * @param iv Contains picture to be displayed in dialog box.
     * @param color String code for background color of dialog box.
     */
    public DialogBox(Label l, ImageView iv, String color) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // set text box radius and color
        l.setStyle("-fx-background-color:" + color + "; -fx-background-radius: 20");
        l.setPadding(new Insets(15, 15, 15, 15));

        // Set background color of dialogbox
        this.setStyle("-fx-background-color:PEACHPUFF");

        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );

        clip.setArcWidth(100);
        clip.setArcHeight(100);
        displayPicture.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = displayPicture.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        displayPicture.setClip(null);

        // store the rounded image in the imageView.
        displayPicture.setImage(image);

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
     * Returns a dialog box to display text by the user.
     *
     * @param l Label object containing text from the user to be displayed in dialog box.
     * @param iv Contains the user's picture to be displayed in dialog box.
     * @return dialog box to display text by the user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, "CORAL");
    }

    /**
     * Returns a dialog box to display text by Duke.
     *
     * @param l Label object containing text from Duke to be displayed in dialog box.
     * @param iv Contains the Duke's picture to be displayed in dialog box.
     * @return dialog box to display text by the Duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, "ORANGE");
        db.flip();
        return db;
    }
}
