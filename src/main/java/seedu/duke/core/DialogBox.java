package seedu.duke.core;

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

    private ImageView displayPicture;
    private Circle clip;
    private HBox textBox = new HBox();

    public DialogBox(Label text, ImageView iv) {
        super(10);
        text.setWrapText(true);
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().add(text);

        displayPicture = iv;
        displayPicture.setFitHeight(100);
        displayPicture.setFitWidth(100);
        clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        this.setStyle("-fx-background-color: #CCCCCC");
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textBox, displayPicture);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections
                .observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
