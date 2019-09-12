package seedu.duke.ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/*
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
*/

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialogbox with the specified arguments.
     *
     * @param text String text which will be the label.
     * @param img Image that will be beside the label.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates the User Dialog box.
     *
     * @param text String.
     * @param img Image.
     * @return DialogBox of the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);

        // Sets a padding of 25 above and below the User DialogBox
        db.setPadding(new Insets(25,0,25,0));

        // Adds background colour to DialogBox.
        String styleBackground = "-fx-background-color: #f7fac5;"
                + "-fx-padding: 15;"
                + "fx-spacing: 10;";

        db.setStyle(styleBackground);

        return db;
    }

    /**
     * Creates the Duke Dialog box.
     *
     * @param text String.
     * @param img Image.
     * @return Duke dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {

        var db = new DialogBox(text, img);

        // Adds background colour to DialogBox.
        String styleBackground = "-fx-background-color: #abffcd;"
                + "-fx-padding: 15;"
                + "fx-spacing: 10;";
        db.setStyle(styleBackground);

        // Sets a padding of 25 above and below the Duke DialogBox
        db.setPadding(new Insets(25,0,25,0));

        db.flip();
        return db;
    }
}
