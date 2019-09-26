import javafx.scene.image.Image;

/**
 * Creates a Left Dialog Box.
 */
public class LeftDialogBox extends DialogBox {
    public LeftDialogBox(String text, Image img) {
        super(text, img);
        this.flip();
    }
}
