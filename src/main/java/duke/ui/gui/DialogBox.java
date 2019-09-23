package duke.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    // Alignment Settings for the Display
    private static final Pos DEFAULT_ALIGNMENT = Pos.TOP_RIGHT;
    private static final Pos ALTERNATE_ALIGNMENT = Pos.TOP_LEFT;

    private DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // The default alignment is to the TOP_RIGHT
        this.setAlignment(DEFAULT_ALIGNMENT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the alignment from the default alignment to the alternate alignment.
     */
    public void flip() {
        this.setAlignment(ALTERNATE_ALIGNMENT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets the dialog box for the user.
     *
     * @param label The label including the user's text input.
     * @param imageView The display picture of the user.
     * @return The dialog box for the user containing the text input and display picture in default alignment.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Gets the dialog box for the duke program.
     *
     * @param label The label including the program's text output.
     * @param imageView The display picture of the duke program.
     * @return The dialog box for the duke program containing the output and display picture in alternate alignment.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}
