import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {
    private final Circle circleDisplay = new Circle(50, 50, 50);

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        Label indentation = new Label("    ");
        indentation.setFont(Font.font("Courier New",15));

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setImage(iv.getImage());
        displayPicture.setClip(circleDisplay);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, indentation, displayPicture);
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
        l.setFont(Font.font("Courier New",15));
        var db = new DialogBox(l, iv);
        db.setStyle("-fx-background-color: #E0E0E0");
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setFont(Font.font("Courier New", 15));
        var db = new DialogBox(l, iv);
        db.setStyle("-fx-background-color: #F4F4F4");
        db.flip();
        return db;
    }

    public static DialogBox getWelcomeDialog(Label l, ImageView iv){
        l.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        var db = new DialogBox(l, iv);
        db.setStyle("-fx-background-color: #F4F4F4");
        db.flip();
        return db;
    }
}
