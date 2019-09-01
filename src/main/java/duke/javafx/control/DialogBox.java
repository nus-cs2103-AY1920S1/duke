package duke.javafx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private HBox text;
    private VBox displayPicture;

    public DialogBox(Label l, ImageView iv, boolean duke) {
        HBox textHolder = new HBox();
        l.setWrapText(true);
        l.setTextFill(Color.web("#dbdbdb"));
        textHolder.getChildren().add(l);
        textHolder.setPadding(new Insets(25, 10, 0, 0));
        text = textHolder;
        HBox holder = new HBox();
        String style_picture = "-fx-border-color: transparent;" +
                "-fx-border-width: 5 10 10 10;";
        holder.setStyle(style_picture);
        Circle clip = new Circle(30, 30, 30);
        iv.setClip(clip);
        iv.setFitWidth(60.0);
        iv.setFitHeight(60.0);
        holder.getChildren().add(iv);

        VBox vHolder = new VBox();
        HBox nameHolder = new HBox();
        Label name = new Label(duke ? "Perry" : "You");
        name.setTextFill(Color.web("#ffffff"));
        String style_name = duke
                ? ("-fx-border-color: transparent;" +
                "-fx-border-width: 10 10 5 20;" +
                "-fx-font-weight: bold;")
                : ("-fx-border-color: transparent;" +
                "-fx-border-width: 10 20 5 30;" +
                "-fx-font-weight: bold;");
        nameHolder.setStyle(style_name);
        nameHolder.getChildren().add(name);
        vHolder.getChildren().add(nameHolder);
        vHolder.getChildren().add(holder);
        displayPicture = vHolder;

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(5, 0, 5, 0));
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
        return new DialogBox(l, iv, false);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, true);
        db.flip();
        return db;
    }
}
