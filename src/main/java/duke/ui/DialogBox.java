package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * User interface element to show messages with a display picture.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImagePattern displayPic;
    private Circle icon;

    /**
     * Constructs the dialog box
     * @param l The text to be displayed in the dialog box
     * @param iV The image to be used as the display picture
     */
    public DialogBox(Label l, ImagePattern iV) {
        // formats the text field
        text = l;
        text.setWrapText(true);
        text.setPadding(new Insets(10,15,10,47.5));

        // formats the display picture
        displayPic = iV;
        icon = new Circle();
        icon.setRadius(37.5);
        icon.setFill(displayPic);

        // formats the dialog box
        setMinHeight(100);
        setAlignment(Pos.CENTER_RIGHT);
        getChildren().addAll(text, icon);
    }

    // mirrors the dialog box elements orientation
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);

        text.setPadding(new Insets(10,47.5,10,15));
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the dialog box formatted to display the user's message
     * @param l The user's message
     * @param ip The user's display picture
     * @return The dialog box formatted to display the user's message
     */
    public static DialogBox getUserDialog(Label l, ImagePattern ip) {
        DialogBox user = new DialogBox(l, ip);

        // sets the background to a green rounded corner box
        user.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(0,100, 0),
                                new CornerRadii(10),
                                new Insets(5,37.5,5,37.5))));
        return user;
    }

    /**
     * Returns the dialog box formatted to display duke's message
     * @param l Duke's message
     * @param ip Duke's display picture
     * @return The dialog box formatted to display duke's message
     */
    public static DialogBox getDukeNormalDialog(Label l, ImagePattern ip) {
        DialogBox duke = new DialogBox(l, ip);

        // sets the background to a blue rounded corner box
        duke.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(0,0, 100),
                                new CornerRadii(10),
                                new Insets(5,37.5,5,37.5))));
        duke.flip();

        return duke;
    }

    /**
     * Returns the dialog box formatted to display duke's error
     * @param l Duke's error message
     * @param ip Duke's display picture
     * @return The dialog box formatted to display duke's error message
     */
    public static DialogBox getDukeErrorDialog(Label l, ImagePattern ip) {
        DialogBox duke = new DialogBox(l, ip);

        // sets the background to a red rounded corner box
        duke.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(100,0,0),
                                new CornerRadii(10),
                                new Insets(5,37.5,5,37.5))));
        duke.flip();

        return duke;
    }

}

