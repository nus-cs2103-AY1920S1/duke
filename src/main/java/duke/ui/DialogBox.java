package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Circle clip;

    private DialogBox(Label l, ImageView iv, boolean isDuke) {
        text = l;
        displayPicture = iv;

        clip = new Circle(30, 30, 30);
        iv.setClip(clip);
        text.setWrapText(true);
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
        Color color = isDuke ? Color.LIGHTYELLOW : Color.LIGHTGREEN;
        BackgroundFill bf = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(bf);
        this.setBackground(background);
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
     * Create a user dialog box.
     * @param l text label
     * @param iv image of the sender
     * @return a DialogBox for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, false);
    }

    /**
     * Create a duke dialog box.
     * @param l text label
     * @param iv image of the sender
     * @return a DialogBox for duke
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, true);
        db.flip();
        return db;
    }
}
