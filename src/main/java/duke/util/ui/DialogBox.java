package duke.util.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;

    public DialogBox(Label label) {
        text = label;

        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getDialog(Label label) {
        return new DialogBox(label);
    }
}
