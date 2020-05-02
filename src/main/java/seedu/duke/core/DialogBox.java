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

    /**
     * Subclass of HBox, which contains 2 dialogs in a single box.
     * @param text String value that will be displayed in the label.
     * @param iv ImageView object which displays the profile image.
     */
    public DialogBox(Label text, ImageView iv) {
        super(10);
        text.setWrapText(true);
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().add(text);

        displayPicture = iv;
        displayPicture.setFitHeight(70);
        displayPicture.setFitWidth(70);
        clip = new Circle(35, 35, 35);
        displayPicture.setClip(clip);

        this.setStyle("-fx-background-color: #CCCCCC");
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textBox, displayPicture);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * returns Duke dialog which profile image will be in located in the opposite from User dialog.
     * @param label label component of the HBox, which contains text value for display.
     * @param imageView ImageView component of HBox, which displays profile image of Duke.
     * @return DialogBox, which is a component of javaFX GUI.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView);
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
