package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Dialog panel class used for Duke's GUI.
 */
//Reused from nus-cs2103-ay1920s1.github.io/ with minor modifications
public class DialogPanel extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor of DialogPanel.
     * @param label Label
     * @param iv ImageView
     */
    public DialogPanel(Label label, ImageView iv) {
        text = label;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogPanel getUserDialog(Label l, ImageView iv) {
        return new DialogPanel(l, iv);
    }

    /**
     * Get method for user dialog.
     * @param l Label
     * @param iv ImageView
     * @return user DialogPanel
     */
    public static DialogPanel getDukeDialog(Label l, ImageView iv) {
        var db = new DialogPanel(l, iv);
        db.flip();
        return db;
    }

}
