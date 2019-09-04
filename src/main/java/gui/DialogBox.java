package gui;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setStyle("-fx-background-color:black;" +
                "-fx-label-padding: 10;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 4;");
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        text.setStyle("-fx-background-color:white;" +
                "-fx-label-padding: 10;" +
                "-fx-text-fill: black;" +
                "-fx-background-radius: 4;");
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox dukeDialog = new DialogBox(l, iv);
        dukeDialog.text.setTranslateY(10.0);
        return dukeDialog;
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox userDialog = new DialogBox(l, iv);
        userDialog.flip();
        userDialog.text.setTranslateX(10.0);
        userDialog.text.setTranslateY(10.0);
        return userDialog;
    }
}
