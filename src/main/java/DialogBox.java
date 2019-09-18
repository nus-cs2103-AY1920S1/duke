import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;
        Circle clip = new Circle(50, 50, 50);
        l.setPadding(new Insets(5));
        iv.setClip(clip);

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
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(16));
        var db = new DialogBox(l, iv);
        BackgroundFill backFill = new BackgroundFill(Color.rgb(55, 71, 133), new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backFill);
        db.setBackground(background);
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setFont(Font.font(16));
        var db = new DialogBox(l, iv);
        BackgroundFill backFill = new BackgroundFill(Color.rgb(220, 222, 225), new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backFill);
        db.setBackground(background);
        db.flip();
        return db;
    }
}
