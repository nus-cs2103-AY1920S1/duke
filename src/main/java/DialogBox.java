import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox, a custom control.
     * @param l Label component in a DialogBox
     * @param iv image to view
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
}
