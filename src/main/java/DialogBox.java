import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    private static final Circle CLIP1 = new Circle(50,50,50);
    private static final Circle CLIP2 = new Circle(50,50,50);
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(40, 7, 0, 0));
        var db = new DialogBox(l, iv);
        db.setPadding(new Insets(5, 10, 5, 10));
        iv.setClip(CLIP1);
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(40, 0, 0, 7));
        var db = new DialogBox(l, iv);
        db.flip();
        db.setPadding(new Insets(5, 10, 5, 10));
        iv.setClip(CLIP2);
        return db;
    }
}
