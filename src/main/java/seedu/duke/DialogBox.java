package seedu.duke;

import com.sun.javafx.menu.CheckMenuItemBase;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

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

        // Add padding between label and image
        l.setPadding(new Insets(20,50,20,50));

        // Clips the imageView into a circle
         Circle clip = new Circle(50,35,35);
         iv.setClip(clip);

        // Creates the DialogBox object.
        var db = new DialogBox(l, iv);

        /*
        // Outer border
        HBox hBox_outer = new HBox();
        String style_outer = "-fx-border-color: black;"
                + "-fx-border-width: 5;";
        hBox_outer.setStyle(style_outer);

        hBox_outer.getChildren().add(iv);
        db.getChildren().add(hBox_outer);
        */


        // Sets a padding of 25 above and below the User DialogBox
        db.setPadding(new Insets(25,0,25,0));

        // Adds background colour to DialogBox.
        String style_background = "-fx-background-color: #f7fac5;"
                + "-fx-padding: 15;"
                + "fx-spacing: 10;";

        db.setStyle(style_background);


        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {

        // Add padding between label and image
        l.setPadding(new Insets(20,50,20,50));

        // Creates the DialogBox object.
        var db = new DialogBox(l, iv);

        // Outer border
        HBox hBox_outer = new HBox();
        String style_outer = "-fx-border-color: black;"
                + "-fx-border-width:5;";
        hBox_outer.setStyle(style_outer);

        hBox_outer.getChildren().add(iv);
        db.getChildren().add(hBox_outer);

        // Sets a padding of 25 above and below the Duke DialogBox
        db.setPadding(new Insets(25,0,25,0));

        // Adds background colour to DialogBox.
        String style_background = "-fx-background-color: #abffcd;"
                + "-fx-padding: 15;"
                + "fx-spacing: 10;";

        db.setStyle(style_background);

        db.flip();
        return db;
    }
}
