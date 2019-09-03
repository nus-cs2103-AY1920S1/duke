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
import javafx.scene.shape.Rectangle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    final Circle clip;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setPadding(new Insets(0, 10, 0, 0));
        text.setWrapText(true);

        displayPicture = iv;
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);
        clip = new Circle(50, 50,50);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(text, displayPicture);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220),
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        text.setPadding(new Insets(0, 0, 0, 10));
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
