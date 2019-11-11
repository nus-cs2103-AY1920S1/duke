package duke.ui;

import javafx.scene.image.Image;

public class RightDialogBox extends DialogBox {
    RightDialogBox(String text, Image img) {
        super(text, img);
        dialog.setText("Duke\n" + dialog.getText());
    }
}
