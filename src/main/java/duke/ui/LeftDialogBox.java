package duke.ui;

import javafx.scene.image.Image;

public class LeftDialogBox extends DialogBox {
    protected LeftDialogBox(String text, Image img) {
        super(text, img);
        dialog.setText("User\n" + dialog.getText());
        this.flip();
    }
}
