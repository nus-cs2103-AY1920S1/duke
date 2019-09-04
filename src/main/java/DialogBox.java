import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private DialogBox(Label l, ImageView iv) {

        l.setWrapText(true);
        iv.setFitWidth(150.0);
        iv.setFitHeight(150.0);
        // Clip the ImageView into a circle
        iv.setClip(new Circle(75, 75, 75));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
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

    static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setSpacing(35.0);
        return db;
    }

    static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}