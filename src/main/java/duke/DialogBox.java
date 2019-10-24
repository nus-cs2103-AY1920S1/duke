package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label progressNum;
    @FXML
    private ImageView displayPicture;
    @FXML
    private ProgressBar progressBar;

    /**
     * Constructor for DialogBox.
     * @param img the image for dialog box
     * @param text text for dialog box
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

    public static DialogBox getUserDialog(String text, Image img) {

        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color:AQUA");
        return db;
    }

    public static DialogBox getWelcomeDukeDialog(String text, Image img, int num1, int num2) {
        String fraction = num1 + "/" + num2 + " tasks";
        var db = new DialogBox(text, img);
        db.flip();
        ProgressBar progressBar = new ProgressBar(); //customises progress bar
        db.getChildren().add(progressBar);
        progressBar.setPrefSize(200,  30);
        progressBar.setPadding(new Insets(0,0,0,50));
        progressBar.setProgress(((double) num1/ (double) num2));
        Text progressNum = new Text("    " + fraction + "\n    completed!"); //customises label beside progress bar
        db.getChildren().add(progressNum);
        progressNum.setFont(Font.font ("Verdana", 18));
        progressNum.setFill(Color.RED);
        return db;
    }

    /**
     * Creates a new dialog box for duke.
     * @param text text response by duke
     * @param img image for duke
     * @return DialogBox for duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}