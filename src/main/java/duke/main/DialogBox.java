package duke.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
//    public DialogBox(Label l, ImageView iv) {
//        text = l;
//        displayPicture = iv;
//
//        text.setWrapText(true);
//        text.setPadding(new Insets(10.0,20.0,10.0,20.0));
//        text.setBackground(
//                new Background(
//                        new BackgroundFill(
//                                Color.LIGHTGREY,
//                                CornerRadii.EMPTY,
//                                Insets.EMPTY
//                        )
//                )
//        );
//        text.setMinHeight(100.0);
//        text.setTextAlignment(TextAlignment.LEFT);
//        displayPicture.setFitWidth(100.0);
//        displayPicture.setFitHeight(100.0);
//        displayPicture.setClip(new Circle(50.0,50.0,50.0));
//
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, displayPicture);
//
//        this.setPadding(new Insets(20,20,20,20));
//
//    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}