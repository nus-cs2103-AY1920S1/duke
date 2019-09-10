package driver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;





public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);


        this.setSpacing(20);

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
        Circle x = new Circle(50.0f,53.0f,50.0f);
        iv.setClip(x);
        l.setFont(Font.font("Times New Roman"));

        BackgroundFill myTemp = new BackgroundFill((Paint)Color.rgb(254,194,33),null,null);
        Background b = new Background(myTemp);

        var db = new DialogBox(l,iv);
        db.setBackground(b);


        return db;
    }



    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setFont(Font.font("Times New Roman"));
        Circle x = new Circle(40.0f,50.0f,50.0f);
        iv.setClip(x);
        l.setTextFill(Color.WHITE);

        BackgroundFill myTemp = new BackgroundFill((Paint)Color.BLACK,null,null);
        Background b = new Background(myTemp);
        var db = new DialogBox(l, iv);
        db.setBackground(b);
        db.flip();
        return db;
    }
}