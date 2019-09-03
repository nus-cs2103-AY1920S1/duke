package duke.fxGui;

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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private String type;

    public DialogBox(String text, Image img, String type) {
        this.type = type;

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
//        this.setAlignment(Pos.TOP_RIGHT);
//        text.setWrapText(true);
//        // Bottom, right, top, left
//        text.setPadding(new Insets(5, 15, 5, 15));
//
//        displayPicture.setFitWidth(100.0);
//        displayPicture.setFitHeight(100.0);

        // Circle clipping starts from top left corner of image
        displayPicture.setClip(new Circle(50, 50, 50));

        if ("user".equals(type)) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(20.0),
                    new Insets(5, 5, 5, 5))));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.KHAKI, new CornerRadii(20.0),
                    new Insets(5, 5, 5, 5))));
        }
//        this.getChildren().addAll(text, displayPicture);
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

    public static DialogBox getUserDialog(String text, Image img, String type) {
        return new DialogBox(text, img, type);
    }

    public static DialogBox getDukeDialog(String text, Image img, String type) {
        var db = new DialogBox(text, img, type);
        db.flip();
        return db;
    }
}
