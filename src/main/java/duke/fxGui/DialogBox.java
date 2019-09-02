package duke.fxGui;

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
    private String type;

    public DialogBox(Label l, ImageView iv, String type) {
        text = l;
        displayPicture = iv;
        this.type = type;

        text.setWrapText(true);
        // Bottom, right, top, left
        text.setPadding(new Insets(5, 15, 5, 15));

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        // Circle clipping starts from top left corner of image
        displayPicture.setClip(new Circle(50, 50, 50));

        this.setAlignment(Pos.TOP_RIGHT);
        if ("user".equals(type)) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(20.0),
                    new Insets(5, 5, 5 ,5))));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.KHAKI, new CornerRadii(20.0),
                    new Insets(5, 5, 5 ,5))));
        }
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

    public static DialogBox getUserDialog(Label l, ImageView iv, String type) {
        return new DialogBox(l, iv, type);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv, String type) {
        var db = new DialogBox(l, iv, type);
        db.flip();
        return db;
    }
}