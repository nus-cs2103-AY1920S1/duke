package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Initializes a DialogBox with Label as text content & ImageView as Profile Image.
     * DialogBox always force enable word wrap for Label and prevents truncations.
     *
     * @param l  String textContent
     * @param iv Profile Image for DialogBox
     */
    public DialogBox(Label l, ImageView iv) {
        this.setStyle("-fx-background-color: #222");
        this.setMinHeight(Region.USE_PREF_SIZE);
        l.setWrapText(true);
        l.setFont(Font.font("DejaVu Sans"));

        text = l;
        text.setStyle("-fx-background-color: #666; -fx-border-color: #222; -fx-border-width: 10; -fx-padding: 5");

        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);

        double length = displayPicture.fitHeightProperty().get() / 2;
        Circle clip = new Circle();

        clip.setRadius(length);
        clip.setCenterX(length);
        clip.setCenterY(length);

        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren()
            .addAll(text, displayPicture);
        text.setFocusTraversable(true);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren()
            .setAll(tmp);
    }

    /**
     * Returns UserDialog Box formatted with ObservableList as [Label, ImageView].
     *
     * @param l  String textContent
     * @param iv Profile Image for DialogBox
     * @return DialogBox of [Label, ImageView]
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns UserDialog Box formatted with ObservableList as [ImageView, Label].
     *
     * @param l  String textContent
     * @param iv Profile Image for DialogBox
     * @return DialogBox of [Label, ImageView]
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
