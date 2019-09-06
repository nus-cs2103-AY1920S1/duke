import javafx.scene.image.Image;

public class FlippedDialogBox extends DialogBox {
    protected FlippedDialogBox(String text, Image img) {
        super(text, img);
        this.flip();
    }
}
