package dose.util.gui.messagebox;

import dose.util.gui.ColourScheme;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// todo: custom styling for exceptions, including exclamation mark etc
public class ExceptionMessageBox extends MessageBox {
    public ExceptionMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        messageBox.setStyle("-fx-background-color: " + colourScheme.getUserShadowColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getUserShadowColour()));
        messageBox.setEffect(dropShadow);
    }
}
