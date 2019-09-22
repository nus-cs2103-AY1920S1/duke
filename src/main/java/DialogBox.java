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
import javafx.scene.control.OverrunStyle;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeBox(text);
        displayPicture.setImage(img);
        displayPicture.getImage();
    }

    private void initializeBox(String text) {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(5);
        formatDialog(text);
        Circle clip = new Circle(50, 50, 37.5);
        dialog.setMinSize(200, Label.USE_PREF_SIZE + 50);
        displayPicture.setClip(clip);
    }

    private void formatDialog(String text) {
        dialog.setText(text);
        dialog.setFont(Font.font("Calibri", 14));
        dialog.setTextFill(Color.WHITE);
        dialog.setTextOverrun(OverrunStyle.CLIP);
    }

    private String generateCssLayout(boolean isDuke) {
        String insets = isDuke
                ? "-fx-border-insets: 5 40 20 20;\n"
                : "-fx-border-insets: 5 20 20 40;\n";

        String cssLayout = "-fx-border-color: white;\n"
                + insets
                + "-fx-border-width: 2;\n"
                + "-fx-border-radius: 5;\n"
                + "-fx-border-style: solid;\n"
                + "-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.9), 10, 0, 0, 0);\n";

        return cssLayout;
    }

    private void setBorder(boolean isDuke) {
        String cssLayout = generateCssLayout(isDuke);
        this.setStyle(cssLayout);
    }

    private void setBackground(boolean isDuke) {
        Color c = isDuke
                ? Color.DARKSLATEBLUE
                : Color.DARKORANGE;
        CornerRadii r = new CornerRadii(5);
        Insets in = isDuke
                ? new Insets(5, 40, 20, 20)
                : new Insets(5, 20, 20, 40);
        BackgroundFill bf = new BackgroundFill(c, r, in);
        Background b = new Background(bf);
        this.setBackground(b);
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
     * Gets text input from the user.
     * @param text The user entered text.
     * @param img The image corresponding to the user.
     * @return DialogBox based on the input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPrefHeight(db.getPrefHeight());
        db.setBackground(false);
        db.setBorder(false);
        return db;
    }

    /**
     * Gets text response from Duke.
     * @param text The response from Duke.
     * @param img Duke's image
     * @return Dialog Box corresponding to Duke's output.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPrefHeight(db.getPrefHeight());
        db.setBackground(true);
        db.setBorder(true);
        db.flip();
        return db;
    }
}